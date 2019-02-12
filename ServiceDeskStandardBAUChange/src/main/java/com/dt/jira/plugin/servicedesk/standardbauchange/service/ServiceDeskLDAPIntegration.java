package com.dt.jira.plugin.servicedesk.standardbauchange.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;
import com.dt.jira.plugin.servicedesk.standardbauchange.rest.ITSMCHGConfig;

public class ServiceDeskLDAPIntegration {

	private final Logger logger = Logger.getLogger(ServiceDeskLDAPIntegration.class);
	  private static final String PLUGIN_STORAGE_KEY = ITSMCHGConfig.class.getName();
	  private static final String LDAP_INITIAL_CTX = "com.sun.jndi.ldap.LdapCtxFactory";
	private final UserManager userManager;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final TransactionTemplate transactionTemplate;

	private String userName = "";
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

	public String getSrvrName() {
		return srvrName;
	}

	public void setSrvrName(String srvrName) {
		this.srvrName = srvrName;
	}

	public String getBaseDn() {
		return baseDn;
	}

	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	private String passWord = "";
	private String initCtx = "";
	private String srvrName = "";	
	private String baseDn = "" ;
	
	public ServiceDeskLDAPIntegration(UserManager userManager, PluginSettingsFactory pluginSettingsFactory, TransactionTemplate transactionTemplate)
	  {
	    this.userManager = userManager;
	    this.pluginSettingsFactory = pluginSettingsFactory;
	    this.transactionTemplate = transactionTemplate;
	    setConfiguration();
	  }
	  
   
    
    private void setConfiguration(){
   	 PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
   	 	String initCtx  = LDAP_INITIAL_CTX;
		String srvrName  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapSrvrname");
		String baseDn = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapBasedn");
   		String username  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapUid");
   		String password  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapPwd");
   		logger.info("initCtx: "+ initCtx);
   		logger.info("srvrName: "+srvrName);
   		logger.info("baseDn: "+baseDn);
   		
   		
   		logger.info("Set plugin configuration");
   		
   		setInitCtx(initCtx);
   		setSrvrName(srvrName);
   		setBaseDn(baseDn);
   		setUserName(username);
   		setPassWord(password);
   		
   		
   		
    }
	public DirContext getDirectoryContext()
	  {
		 Properties properties = new Properties();
		 InitialDirContext initialdircontext = null;
				 try
				 {
					  properties.put("java.naming.factory.initial",getInitCtx());
					  properties.put("java.naming.provider.url", getSrvrName());
					  properties.put("java.naming.security.principal",getUserName());
					  properties.put("java.naming.security.credentials",getPassWord());
					  initialdircontext = new InitialDirContext(properties);
					  logger.info("LDAP connection object from  LDAP Server[DirectoryContextFactory][getDirectoryContext]::"+initialdircontext);
					 

					  return initialdircontext;
				}catch (Exception e)
					{
						e.printStackTrace();
						return null;
					}
				
		}
		
		public List getUserAccountDetailsByMailId(String userMailId) {
			logger.info("About to initialize getUserAccountDetailsByMailId");
			List result = new ArrayList();
			DirContext ctx = null;
			String line;
			final int lhs = 0;
			final int rhs = 1;
			NamingEnumeration namingenumeration = null;
			ctx = getDirectoryContext();

			if (ctx == null) {
				result.add("1");
				result.add("LDAP instance down");

				return result;
			}

			try {
					//String basedn = "dc=dt,dc=inc";
				    String basedn = getBaseDn();
					String s2 = "(mail=" + userMailId + ")";
					String as[] = {"fn","sn", "givenname", "mobile","telephoneNumber", "mail", "cn", "manager", "department", "title" };
	
					SearchControls searchcontrols = new SearchControls();
					searchcontrols.setSearchScope(2);
					namingenumeration = ctx.search(basedn, s2, searchcontrols);
					logger.info("Inside getUserAccountDetailsByMailId after searchcontrols");
					if (namingenumeration != null && namingenumeration.hasMoreElements()) {
	
						LDAPDetails myRestResourceModel=new LDAPDetails();
						SearchResult searchresult = (SearchResult) namingenumeration.nextElement();
						String dn = searchresult.getName() + "," + basedn;
						myRestResourceModel.setFirstName((String) getAttribsForKey(searchresult,as[0]).get(0));
						myRestResourceModel.setLastName((String) getAttribsForKey(searchresult,as[1]).get(0));
						myRestResourceModel.setGivenName((String) getAttribsForKey(searchresult,as[2]).get(0));
						myRestResourceModel.setMobileNo((String) getAttribsForKey(searchresult,as[3]).get(0));
						myRestResourceModel.setPhoneNo((String) getAttribsForKey(searchresult, as[4]).get(0));
						myRestResourceModel.setUserMailId((String) getAttribsForKey(searchresult,as[5]).get(0));
						myRestResourceModel.setUserName((String) getAttribsForKey(searchresult, as[6]).get(0));
						myRestResourceModel.setMngrDetails((String) getAttribsForKey(searchresult, as[7]).get(0));
						myRestResourceModel.setDeptName((String) getAttribsForKey(searchresult, as[8]).get(0));
						myRestResourceModel.setJobTitle((String) getAttribsForKey(searchresult, as[9]).get(0));
						
					    String[] strings = myRestResourceModel.getMngrDetails().split(",");
				          
			            TreeMap<String, String> map = new TreeMap<String, String>();
						for (String string : strings) {
							 String[] pair = string.trim().split("=");
				                map.put(pair[lhs].trim(), pair[rhs].trim());	 						       
						}
						
						myRestResourceModel.setMngrName(map.get("CN"));					
						List result1 = getUserManagerMailIdByName(myRestResourceModel.getMngrName());
						myRestResourceModel.setMngrMailId((String) result1.get(1));
						myRestResourceModel.setMngrAccountName((String) result1.get(2));								
								
						result.add("0");
						result.add("successful");
						result.add(myRestResourceModel);
						
					} else {
						result.add("5");
						result.add("User Mail ID " + userMailId + " doesnot exist");
						return result;
					}

			} catch (Exception exception) {
				exception.printStackTrace();
				result.add("1");
				result.add("LDAP operation failed while retrieving User Profile for user Mail Id "
						+ userMailId);
				return result;
			} finally {
				try {
					if (namingenumeration != null)
						namingenumeration.close();
					if (ctx != null)
						ctx.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		public  ArrayList getUserManagerMailIdByName(String userMngrName) {
			ArrayList arraylist = new ArrayList();
			DirContext dircontext = null;
			NamingEnumeration namingenumeration = null;		
			//String basedn = "dc=dt,dc=inc";
			 String basedn = getBaseDn();
			String managermailid = null;
			String manageraccountname = null;
			dircontext = getDirectoryContext();

			try {

				SearchControls searchcontrols = new SearchControls();
				String[] attributes = { "mail","sAMAccountName" };
				searchcontrols.setReturningAttributes(attributes);
				searchcontrols.setSearchScope(SearchControls.SUBTREE_SCOPE);
				namingenumeration = dircontext.search(basedn, "(&(cn=" + userMngrName + ")(mail=*))", searchcontrols);

				if (namingenumeration != null && namingenumeration.hasMoreElements()) {
					SearchResult searchresult = (SearchResult) namingenumeration.nextElement();
					managermailid = (String) getAttribsForKey(searchresult,attributes[0]).get(0);
					manageraccountname = (String) getAttribsForKey(searchresult,attributes[1]).get(0);
					if (getAttribsForKey(searchresult, attributes[0]).get(0).equals(null)|| 
					getAttribsForKey(searchresult, attributes[0]).get(0).equals("")) {					
						arraylist.add("5");
						arraylist.add("empty");
					} else {
						arraylist.add("0");
						arraylist.add(managermailid);
						arraylist.add(manageraccountname);
					}
				} else {
					arraylist.add("3");
					arraylist.add("Manager MailId " + userMngrName + " does not exist");
				}
			} catch (NullPointerException nullpointerexception) {
				nullpointerexception.toString();
			} catch (Exception exception) {
				exception.printStackTrace();
				arraylist.add("4");
				arraylist.add("LDAP operation failed while retrieving mailId info for Manager "+ userMngrName);
				return arraylist;
			} finally {
				try {
					if (namingenumeration != null) {
						namingenumeration.close();
					}
					if (dircontext != null) {
						dircontext.close();
					}
				} catch (Exception exception2) {
					exception2.printStackTrace();
				}
			}
			return arraylist;
		}

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
		
		
	
}
