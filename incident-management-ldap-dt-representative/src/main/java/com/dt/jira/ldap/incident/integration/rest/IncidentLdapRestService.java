package com.dt.jira.ldap.incident.integration.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.crowd.embedded.api.User;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.jira.component.ComponentAccessor;
/**
 * The REST service for LDAP and Incident Management DT Representative
 *
 * @author Firoz Khan
 */
@Path("/incident-ldap")
public class IncidentLdapRestService {

	private final Logger logger = Logger.getLogger(IncidentLdapRestService.class);	
	private static final String PLUGIN_STORAGE_KEY = IncidentLdapConfig.class.getName();
	private final UserManager userManager;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final TransactionTemplate transactionTemplate;

	private String userName = "";
	private String passWord = "";
	private String initCtx = "";
	private String serverName = "";	
	private String baseDn = "" ;
	
	/**
     * Constructor
     * @param userManager the UserManager to be injected
     * @param pluginSettingsFactory the PluginSettingsFactory to be injected
     * @param transactionTemplate the TransactionTemplate to be injected
     */
	public IncidentLdapRestService(UserManager userManager, PluginSettingsFactory pluginSettingsFactory, TransactionTemplate transactionTemplate)
	  {
	    this.userManager = userManager;
	    this.pluginSettingsFactory = pluginSettingsFactory;
	    this.transactionTemplate = transactionTemplate;
	    setConfiguration();
	  }
  
    private void setConfiguration(){
      	PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
      	String initCtx  	= (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapInitCtx");
   		String serverName  	= (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapServerName");
   		String baseDn 		= (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapBaseDn");
  	    String username  	= (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapUid");
  		String password  	= (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapPwd");
  		
  		logger.info(IncidentLdapRestService.class.getName() + ".ldapInitCtx"+ initCtx);
  		logger.info(IncidentLdapRestService.class.getName() + ".ldapServerName"+ serverName);
  		logger.info(IncidentLdapRestService.class.getName() + ".ldapBaseDn"+ baseDn);
  		logger.info(IncidentLdapRestService.class.getName() + ".ldapUid"+ username);
  		logger.info(IncidentLdapRestService.class.getName() + ".ldapPwd"+ password);
  		
  		logger.info("Set plugin configuration");
  		
  		setInitCtx(initCtx);
  		setServerName(serverName);
  		setBaseDn(baseDn);
  		setUserName(username);
  		setPassWord(password);
      		
       }
    
    
    
    @GET
	@Path("/readUserDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("userName") String userName)
	{
    	List accDetails = new ArrayList();
		Map<String, String> responseMap = new HashMap<String, String>();
		
		IncidentLdap dtIncidentLdap=new IncidentLdap();		   
		   try {
					
				logger.info("----------------User Name : "+userName);
				accDetails = getUserMailIdAndPhoneByName(userName);
				int acc = Integer.parseInt((String) accDetails.get(0));
				if (acc == 0) {
					dtIncidentLdap=(IncidentLdap) accDetails.get(2);
					logger.info("----------------LDAP UserProfile User Name : "+dtIncidentLdap.getUserName());
					logger.info("-----------------LDAP UserProfile User MailID : "+dtIncidentLdap.getUserMailId());
					logger.info("----------------LDAP UserProfile User Phone Number : "+dtIncidentLdap.getPhoneNo());				
				} 
				responseMap.put("dt_rpt_email", dtIncidentLdap.getUserMailId());	
				responseMap.put("dt_rpt_phone", dtIncidentLdap.getPhoneNo());
				
				//Custom Field in 49
				CustomFieldManager  customFieldManager = ComponentAccessor.getCustomFieldManager();
				
				CustomField  emailCustomField = customFieldManager.getCustomFieldObjectByName("DT Representative Email");
				String emailfield = emailCustomField.getHiddenFieldId();
				responseMap.put(emailfield, dtIncidentLdap.getUserMailId());
				
				CustomField  phoneCustomField = customFieldManager.getCustomFieldObjectByName("DT Representative Phone");
				String phonefield = phoneCustomField.getHiddenFieldId();
				responseMap.put(phonefield, dtIncidentLdap.getPhoneNo());
					
			} catch (Exception e) {
			logger.info("----------------Errror occurs inside IncidentLdapRestService.get()--------------"+e.getMessage());
				//e.printStackTrace();
			}

       return Response.ok(responseMap).build();
	}
    
    
    @GET
	@Path("/read")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Context HttpServletRequest request)
	{
	  String username = userManager.getRemoteUsername(request);
	  if (username == null || !userManager.isSystemAdmin(username))
	  {
	    return Response.status(Status.UNAUTHORIZED).build();
	  }

	  return Response.ok(transactionTemplate.execute(new TransactionCallback()
	  {
	    public Object doInTransaction()
	    {
	      PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
	      IncidentLdapConfig incidentLdapConfig = new IncidentLdapConfig();
	      incidentLdapConfig.setLdapInitCtx((String) settings.get(IncidentLdapConfig.class.getName() + ".ldapInitCtx"));
	      incidentLdapConfig.setLdapServerName((String) settings.get(IncidentLdapConfig.class.getName() + ".ldapServerName"));
	      incidentLdapConfig.setLdapBaseDn((String) settings.get(IncidentLdapConfig.class.getName() + ".ldapBaseDn"));
	      incidentLdapConfig.setLdapUid((String) settings.get(IncidentLdapConfig.class.getName() + ".ldapUid"));
	      incidentLdapConfig.setLdapPwd((String) settings.get(IncidentLdapConfig.class.getName() + ".ldapPwd"));
	      logger.info("************* " + incidentLdapConfig.getLdapUid());
	      return incidentLdapConfig;
	    }
	  })).build();
	}
	
	@PUT
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(final IncidentLdapConfig incidentLdapConfig, @Context HttpServletRequest request)
	{
		
	  logger.info("calling incident ldap save");
	  String username = userManager.getRemoteUsername(request);
	  if (username == null || !userManager.isSystemAdmin(username))
	  {
	    return Response.status(Status.UNAUTHORIZED).build();
	  }

	  transactionTemplate.execute(new TransactionCallback()
	  {
	    public Object doInTransaction()
	    {
	      PluginSettings pluginSettings = pluginSettingsFactory.createGlobalSettings();
	      
	      pluginSettings.put(IncidentLdapConfig.class.getName() + ".ldapInitCtx", incidentLdapConfig.getLdapInitCtx());
	      pluginSettings.put(IncidentLdapConfig.class.getName() + ".ldapServerName", incidentLdapConfig.getLdapServerName());
	      pluginSettings.put(IncidentLdapConfig.class.getName() + ".ldapBaseDn", incidentLdapConfig.getLdapBaseDn());
	      pluginSettings.put(IncidentLdapConfig.class.getName() + ".ldapUid", incidentLdapConfig.getLdapUid());
		  pluginSettings.put(IncidentLdapConfig.class.getName() + ".ldapPwd", incidentLdapConfig.getLdapPwd());
	      return null;
	    }
	  });
	  return Response.noContent().build();
	}
	
	public DirContext getDirectoryContext()
	  {
		 Properties properties = new Properties();
		 InitialDirContext initialdircontext = null;
				 try
				 {					
					  properties.put("java.naming.factory.initial",getInitCtx());
					  properties.put("java.naming.provider.url", getServerName());
					  properties.put("java.naming.security.principal",getUserName());
					  properties.put("java.naming.security.credentials",getPassWord());
					  initialdircontext = new InitialDirContext(properties);
					  logger.info("LDAP connection object from Primary LDAP Server[DirectoryContextFactory][getDirectoryContext]::"+initialdircontext);
					  return initialdircontext;
				}
				catch (Exception e)
				{
						e.printStackTrace();
						return null;
					
				}
		}
	
	
    /**
     * Gets User mail Id and Phone Number by Name
     * @param userName the String text
     * @return the list of result
     */
    public  List getUserMailIdAndPhoneByName(String userName) {
    	List result = new ArrayList();
		DirContext dircontext = null;
		NamingEnumeration namingenumeration = null;	
		String basedn =getBaseDn();
		String userMailId = null;
		String userPhone = null;
		dircontext = getDirectoryContext();

		try {

			SearchControls searchcontrols = new SearchControls();
			String[] attributes = {"fn","sn", "givenname", "mobile","telephoneNumber", "mail","cn"};
			searchcontrols.setReturningAttributes(attributes);
			searchcontrols.setSearchScope(SearchControls.SUBTREE_SCOPE);
			namingenumeration = dircontext.search(basedn, "sAMAccountName="+ userName, searchcontrols);

			if (namingenumeration != null && namingenumeration.hasMoreElements()) {

				IncidentLdap dtIncidentLdap=new IncidentLdap();
				SearchResult searchresult = (SearchResult) namingenumeration.nextElement();
				dtIncidentLdap.setFirstName((String) getAttribsForKey(searchresult,attributes[0]).get(0));
				dtIncidentLdap.setLastName((String) getAttribsForKey(searchresult,attributes[1]).get(0));
				dtIncidentLdap.setGivenName((String) getAttribsForKey(searchresult,attributes[2]).get(0));
				dtIncidentLdap.setMobileNo((String) getAttribsForKey(searchresult,attributes[3]).get(0));
				dtIncidentLdap.setPhoneNo((String) getAttribsForKey(searchresult, attributes[4]).get(0));
				dtIncidentLdap.setUserMailId((String) getAttribsForKey(searchresult,attributes[5]).get(0));
				dtIncidentLdap.setUserName((String) getAttribsForKey(searchresult,attributes[6]).get(0));	
				result.add("0");
				result.add("successful");
				result.add(dtIncidentLdap);
				
			} else {
				result.add("5");
				result.add("User Mail ID " + userName + " doesnot exist");
				return result;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			result.add("1");
			result.add("LDAP operation failed while retrieving User Profile for userName "
					+ userName);
			return result;
		} finally {
			try {
				if (namingenumeration != null)
					namingenumeration.close();
				if (dircontext != null)
					dircontext.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

    /**
     * Gets User Basic Attributes by user name
     * @param username the String text
     */
    
    private void getUserBasicAttributes(String username) {
        User user=null;
        DirContext dircontext = getDirectoryContext();
        String basedn = getBaseDn();
        try {
 
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attrIDs = { "distinguishedName",
                    "sn",
                    "givenname",
                    "mail",
                    "telephonenumber"};
            constraints.setReturningAttributes(attrIDs);
            NamingEnumeration answer = dircontext.search(basedn, "sAMAccountName="+ username, constraints);
            if (answer.hasMore()) {
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
                logger.info("distinguishedName "+ attrs.get("distinguishedName"));
                logger.info("givenname "+ attrs.get("givenname"));
                logger.info("sn "+ attrs.get("sn"));
                logger.info("mail "+ attrs.get("mail"));
                logger.info("telephonenumber "+ attrs.get("telephonenumber"));
            }else{
                throw new Exception("Invalid User");
            }
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       // return user;
    }

    /**
     * Gets User mail Id and Phone Number by Name
     * @param searchresult the SearchResult
     * @param s the String text
     * @return the list of attributes for key
     */
		private List getAttribsForKey(SearchResult searchresult, String s)
				throws Exception {
			String as[] = new String[1];
			as[0] = s;
			Attributes attributes = searchresult.getAttributes();

			ArrayList arraylist = new ArrayList();
			if (attributes == null)
				throw new Exception("Entry " + searchresult.getName()+ " has none of the specified attributes\n");
			Attribute attribute = attributes.get(s);

			if (attribute != null) {
				for (int i = 0; i < attribute.size(); i++) {
					String name = (String) attribute.get(i);
					arraylist.add(name);
				}

			} else {
				arraylist.add(attribute);
			}

			return arraylist;
		}
		
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassWord() {
			return passWord;
		}

		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}

		public String getInitCtx() {
			return initCtx;
		}

		public void setInitCtx(String initCtx) {
			this.initCtx = initCtx;
		}

		public String getServerName() {
			return serverName;
		}

		public void setServerName(String serverName) {
			this.serverName = serverName;
		}

		public String getBaseDn() {
			return baseDn;
		}

		public void setBaseDn(String baseDn) {
			this.baseDn = baseDn;
		}
}