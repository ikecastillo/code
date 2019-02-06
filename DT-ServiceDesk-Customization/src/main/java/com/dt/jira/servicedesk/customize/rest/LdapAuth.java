package com.dt.jira.servicedesk.customize.rest;

import java.util.*;
import javax.naming.*;

import java.util.regex.*;
import javax.naming.directory.*;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import org.apache.log4j.Logger;

	/**
	 * The REST Service for Automation Portal 
	 *
	 * @author Srinadh.Garlapati
	 */
	@Path("/ldapAuthentication")
	public class LdapAuth {
		
		private final PluginSettingsFactory pluginSettingsFactory;
		private final Logger logger = Logger
				.getLogger(LdapAuth.class);
		
			
		public LdapAuth(PluginSettingsFactory pluginSettingsFactory){
			this.pluginSettingsFactory=pluginSettingsFactory;
		}
		
		/**
		 * Authenticate  LDAP User and password .
		 * Bind user ,if no exception thrown Authenticate.
		 * @param dn
		 * @param password
		 * @return
		 * @throws Exception
		 */
		private  DirContext testBind (String dn, String password) throws Exception {
			  PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
		      ServiceDeskLdap ldapSrvc = new ServiceDeskLdap();
		      String ldapInitCtx=(String) settings.get(ServiceDeskLdap.class.getName() + ".ldapInitCtx");
		      String ldapSrvrName=(String) settings.get(ServiceDeskLdap.class.getName() + ".ldapSrvrName");
		      DirContext context =null;
		      Properties properties = new Properties();
		        properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,ldapInitCtx);
		        properties.put(javax.naming.Context.PROVIDER_URL,"ldap://"+ldapSrvrName);  //"ldap://nhpads002.dt.inc"); 
		        properties.put(javax.naming.Context.SECURITY_PRINCIPAL, dn);
		        properties.put(javax.naming.Context.SECURITY_CREDENTIALS, password);
			try {
				
				context = new InitialDirContext(properties);
				return context;
			}
			catch (javax.naming.AuthenticationException e) {
				e.printStackTrace();
				return null;
			}
			
			
		}

		/**
		 * authenticateUser
		 * @param user
		 * @param password
		 * @return
		 * @throws Exception
		 */
		public  String authenticateUser(String user,String password) throws Exception {
			String msg=" Please verify user and password ";
			if (user==null || password==null || user.isEmpty() || password.isEmpty()) {
				msg="Missing requried username and password" ;
				
			}

			String dn = getDistinguishedNamebyUserName(user);

			if (dn != null) {
				/* Found user - test password */
				if ( testBind( dn, password )!=null ) {
					msg= "User " + user + " authentication succeeded" ;
					
				}
				else {
					msg="User " + user + " authentication failed" ;
					
				}
			}
			else {
				msg="User " + user + " not found" ;
				
			}
			return msg;
		}
		
		
		/**
		 * LDAPContext
		 * @return
		 */
		private  DirContext getLDAPContext(){
			
			  DirContext context =null;
			  PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
		      ServiceDeskLdap ldapSrvc = new ServiceDeskLdap();
		      String ldapInitCtx=(String) settings.get(ServiceDeskLdap.class.getName() + ".ldapInitCtx");
		      logger.info("*********ldapInitCtx*********" + ldapInitCtx);
		      String ldapSrvrName=(String) settings.get(ServiceDeskLdap.class.getName() + ".ldapSrvrName");
		      logger.info("*********ldapSrvrName*********" + ldapSrvrName);
		      String ldapBaseDn=(String) settings.get(ServiceDeskLdap.class.getName() + ".ldapBaseDn");
		      logger.info("*********ldapBaseDn*********" + ldapBaseDn);
		      String ldapUid=(String) settings.get(ServiceDeskLdap.class.getName() + ".ldapUid");
		      logger.info("*********ldapUid*********" + ldapUid);
		      String ldapPwd=(String) settings.get(ServiceDeskLdap.class.getName() + ".ldapPwd");
		      logger.info("*********ldapPwd*********" + ldapPwd);
			    Properties properties = new Properties();
		        properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,ldapInitCtx);
		        properties.put(javax.naming.Context.PROVIDER_URL,"ldap://"+ldapSrvrName);  
		        properties.put(javax.naming.Context.SECURITY_PRINCIPAL,ldapUid);
		        properties.put(javax.naming.Context.SECURITY_CREDENTIALS, ldapPwd);
			

			try {
				
				context = new InitialDirContext(properties);
				
				} catch (NamingException e) {

				e.printStackTrace();

			}
			
			return context;
		}
		
		
		/**
		 * get DistinguishedName by UserName
		 * @param username
		 * @return
		 */
		public   String getDistinguishedNamebyUserName(String username){
			
			DirContext initialdircontext = null;
					 try
					 {
						initialdircontext = getLDAPContext();
						 PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
						 String ldapBaseDn=(String) settings.get(ServiceDeskLdap.class.getName() + ".ldapBaseDn");
						 
						 SearchControls constraints = new SearchControls();
				            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
				            String[] attrIDs = { "distinguishedName",
				                    "sn",
				                    "givenname",
				                    "mail",
				                    "telephonenumber"};
				            constraints.setReturningAttributes(attrIDs);
				            //First input parameter is search bas, it can be "CN=Users,DC=YourDomain,DC=com"
				            //Second Attribute can be uid=username
				            NamingEnumeration answer = initialdircontext.search(ldapBaseDn, "sAMAccountName="
				                    + username, constraints);
				            if (answer.hasMore()) {
				                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				               // displayAttributes(attrs);
				                logger.info("distinguishedName "+ attrs.get("distinguishedName").get(0));
				                
				               return  ""+attrs.get("distinguishedName").get(0);
				            }else{
				                throw new Exception("Invalid User");
				            }
				 
				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
						
					 return null;
					
			}

		/**
		 * adding user to LDAP group
		 * @param userNameDn
		 * @param groupDn
		 * @param issueParent
		 */
		public void addEntry(String userNameDn,String groupDn,DirContext context) throws Exception{

				logger.info("USER DN :" + userNameDn);
				logger.info("GROUP DN " + groupDn);
				logger.info(" ====================context==================== "+context);
				logger.info(userNameDn+" ====================addEntry==================== "+groupDn);
				ModificationItem[] mods = new ModificationItem[1];
				//String userDn="cn=Performance Assessment,OU=Service Accounts,OU=Lake Success,DC=dt,DC=inc";
				//String groupDn="cn=jira-users,OU=Permissions Groups,OU=Lake Success,DC=dt,DC=inc";
				Attribute mod =new BasicAttribute("member",userNameDn);
				mods[0] =new ModificationItem(DirContext.ADD_ATTRIBUTE, mod);
				context.modifyAttributes(groupDn, mods);
				logger.info("==================executed=============");
		}
		
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/validate")	
		public  Response validateUser(@QueryParam("user") String  user,@QueryParam("password") String password) throws Exception {
			List<String> resultList=new ArrayList<String>();
			String result=authenticateUser(user,password);
			logger.info("result :   "+result);
			resultList.add(result);
			logger.info("resultList :   "+resultList);
			return Response.ok(resultList).build();
		}
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/testPermission")	
		public  Response validatePermission(@QueryParam("user") String  user,@QueryParam("password") String password ,@QueryParam("username") String userName,@QueryParam("group") String group) throws Exception {
			// user name enter in pop to add to group
			List<String> resultList=new ArrayList<String>();
			String userNameDn = getDistinguishedNamebyUserName(userName);
			if (userNameDn != null) {
				logger.info("userNameDn :   "+userNameDn);
				
				String userDn=getDistinguishedNamebyUserName(user);
				if(userDn!=null){
				DirContext context=testBind(userDn,password);
				if(context!=null){
					//System.out.println("------groupDn1---------------------"+getDistinguishedNamebyUserName(group));
					String[] groups = group.split(",");
					for (String grp : groups) {
						String groupDn=getDistinguishedNamebyUserName(grp);
						try{
							addEntry(userNameDn,groupDn,context);
							resultList.add("User "+user+" has permissions to add user "+userName+" to group "+group + "\n");
						} catch (Exception e) {
							//email needs to be send as failure occurs
							e.printStackTrace();
							resultList.add("Error while adding user "+ user +" to group " + grp +" : "+e.getMessage() + "\n" );
						}
					}
				}else{
					logger.info("UserDn/Password  are  nopt valid"+userNameDn);
					resultList.add("Please verify UserDn / Password");	
				}
			}else{
				logger.info("User Name Dn is not valid one "+user);
				resultList.add("Please verify User Dn");
			}
				
			}else{
				logger.info("User Name Dn is not valid one "+userNameDn);
				resultList.add("Please verify User Name");
			}
			
			return Response.ok(resultList).build();
		}
		
		
		
		
		
		
		
	}


