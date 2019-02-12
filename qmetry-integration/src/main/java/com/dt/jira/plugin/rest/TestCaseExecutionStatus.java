package com.dt.jira.plugin.rest;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.wso2.www.php.QMetryWS;
import org.wso2.www.php.QMetryWSLocator;
import org.wso2.www.php.QMetryWSPortType;
import org.wso2.www.php.xsd.Entity;
import org.wso2.www.php.xsd.GetDefectsFromTestCaseId;
import org.wso2.www.php.xsd.GetDefectsFromTestCaseIdResponse;
import org.wso2.www.php.xsd.GetTestSuiteExecutions;
import org.wso2.www.php.xsd.GetTestSuiteExecutionsResponse;
import org.wso2.www.php.xsd.KeyValuePair;
import org.wso2.www.php.xsd.ListBuilds;
import org.wso2.www.php.xsd.ListBuildsResponse;
import org.wso2.www.php.xsd.ListProjects;
import org.wso2.www.php.xsd.ListProjectsResponse;
import org.wso2.www.php.xsd.ListReleases;
import org.wso2.www.php.xsd.ListReleasesResponse;
import org.wso2.www.php.xsd.ListTestCases;
import org.wso2.www.php.xsd.ListTestCasesResponse;
import org.wso2.www.php.xsd.ListTestSuites;
import org.wso2.www.php.xsd.ListTestSuitesResponse;
import org.wso2.www.php.xsd.Login;
import org.wso2.www.php.xsd.LoginResponse;
import org.wso2.www.php.xsd.Logout;
import org.wso2.www.php.xsd.SetProject;
import org.wso2.www.php.xsd.SetProjectResponse;
import org.wso2.www.php.xsd.SetRelease;
import org.wso2.www.php.xsd.SetScope;
import org.wso2.www.php.xsd.SetScopeResponse;
import org.wso2.www.php.xsd.TestCaseExecution;
import org.wso2.www.php.xsd.TestCaseExecutions;
import org.wso2.www.php.xsd.TestSuiteEntity;
import org.wso2.www.php.xsd.TestSuiteExecution;
import org.wso2.www.php.xsd.TestSuitePlatformExecution;
import org.wso2.www.php.xsd.TestSuitePlatformExecutions;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.jira.plugin.rest.ConfigResource.Config;

/**
 * A resource of message.
 */
@Path("/testexecutionstatus")
public class TestCaseExecutionStatus {


	private static String QMETRY_USERNAME = "kiran.muthoju11";
	private static String QMETRY_PASSWORD = "xxxxxxx";
	private static final String PLUGIN_STORAGE_KEY = Config.class.getName();
	private static PluginSettingsFactory pluginSettingsFactory;
	private static URL url = null;
	
	public TestCaseExecutionStatus(PluginSettingsFactory psf){
		this.pluginSettingsFactory = psf;		
	}
	
	
    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version,@QueryParam("sprintId") String sprintId) throws Exception{
    	  
    	 QMetryWS qmetryService  = new QMetryWSLocator();
      	 QMetryWSPortType qmetryPort = qmetryService.getQMetryWSSOAPPort_Http();
		PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
		String username = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".qmetryUid");
		String password =  (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".qmetryPwd");
         String AuthToken = null;
        
   	 	Login login =new Login();
   	 	login.setUsername(username);
   	 	login.setPassword(password);
   	 	//System.out.println("Time Stamp in seconds before login(): "+ convertToSeconds(System.currentTimeMillis()));
           LoginResponse loginResponse = qmetryPort.login (login);
           //System.out.println("Time Stamp in seconds after  login(): "+ convertToSeconds(System.currentTimeMillis()));
           AuthToken = loginResponse.getToken();
           //System.out.println ("Logged into QMetry with Token: " + loginResponse.getToken());
       
           
        	// set project as a current scope			
           SetProject setProject = new SetProject(AuthToken, projectkey);
           //System.out.println("Time Stamp in seconds before setProject(): "+ convertToSeconds(System.currentTimeMillis()));
           SetProjectResponse setProjectResponse  = qmetryPort.setProject(setProject);
           //System.out.println("Time Stamp in seconds after setProject(): "+ convertToSeconds(System.currentTimeMillis()));
         
           // get the list of releases of the current scope project      
           SetRelease setRelease = new SetRelease(AuthToken, version);
		   //System.out.println("Time Stamp in seconds before setRelease(): "+ convertToSeconds(System.currentTimeMillis()));;
           qmetryPort.setRelease(setRelease);
		   //System.out.println("Time Stamp in seconds after setRelease(): "+ convertToSeconds(System.currentTimeMillis()));
           
		   ListBuilds listBuilds = new ListBuilds(AuthToken);
           //System.out.println("Time Stamp before get list of release listBuilds(): "+ convertToSeconds(System.currentTimeMillis()));
           ListBuildsResponse listBuildsResponse = qmetryPort.listBuilds(listBuilds);
           //System.out.println("Time Stamp after get list of release listBuilds(): "+ convertToSeconds(System.currentTimeMillis()));
           
           
           // returns list of cycles which set to current scope              
           ArrayList<TestCaseExecutionStatusModel> list = new ArrayList<TestCaseExecutionStatusModel>();
           Entity[] cycles =listBuildsResponse.getBuilds();
           for(Entity e: cycles){
        	 //System.out.println("Time Stamp before build name: " + e.getName() +  " ***** "+ convertToSeconds(System.currentTimeMillis())); 
           	TestCaseExecutionStatusModel caseExecutionStatusModel = buildCycles(AuthToken, projectkey, version, e.getName());
            //System.out.println("Time Stamp after build name: " + e.getName() + " ***** "+ convertToSeconds(System.currentTimeMillis())); 
           	list.add(caseExecutionStatusModel);
           }
     
         
         Logout logout= new Logout();
         logout.setToken(AuthToken);
         //System.out.println ("Logout msg: " + qmetryPort.logout(logout));
         return Response.ok(list).build();  
    	}
    @GET
    @Path ("/getProjects") 
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getProjects()
    {
		PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
		String username = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".qmetryUid");
		String password =  (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".qmetryPwd");
		
    	 ProjectInfoModel infoModel = null;
    	 String AuthToken = null;
    	
    	try   {
       List<ProjectInfo> list = new ArrayList<ProjectInfo>();
        //list.add(new ProjectInfo("SSP1", "SSSP1"));
       // list.add(new ProjectInfo("SSP2", "SSSP2"));
    	QMetryWS qmetryService  = new QMetryWSLocator();
   	    QMetryWSPortType qmetryPort = qmetryService.getQMetryWSSOAPPort_Http();
   	 Login login =new Login();
	 	login.setUsername(username);
	 	login.setPassword(password);
     LoginResponse loginResponse = qmetryPort.login (login);
     AuthToken = loginResponse.getToken();
   	 
     ListProjects listProjects = new ListProjects();
     listProjects.setToken(AuthToken);
    	ListProjectsResponse projectsResponse = qmetryPort.listProjects(listProjects);
    	Entity[] projects = projectsResponse.getProjects();
        for(Entity e: projects){
        	//System.out.println("id: "+ e.getId());
        	//System.out.println("Name: "+ e.getName());
        	
        	  list.add(new ProjectInfo(e.getName(), e.getName()));
        }
    	
        infoModel = new ProjectInfoModel("Projects", list);
        Logout logout= new Logout();
        logout.setToken(AuthToken);
        //System.out.println ("Logout msg: " + qmetryPort.logout(logout));
    	}catch(Exception e){
			System.out.println ("********************Error Logging in to getProjects********************");
				e.printStackTrace();
    		}
 
        return Response.ok(infoModel).build();
        
 
    }
	@GET
    @Path ("/getVersions") 
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getVersions(@QueryParam("project") String project)
    {
	PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
	String username = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".qmetryUid");
	String password =  (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".qmetryPwd");
	
		
        List<VersionInfoModel> list = new ArrayList<VersionInfoModel>();
        String AuthToken = null;
    	try   {
    		QMetryWS qmetryService  = new QMetryWSLocator();
       	    QMetryWSPortType qmetryPort = qmetryService.getQMetryWSSOAPPort_Http();
       	    Login login =new Login();
    	 	login.setUsername(username);
    	 	login.setPassword(password);
    	 	LoginResponse loginResponse = qmetryPort.login (login);
    	 	AuthToken = loginResponse.getToken();
         
    	 	SetProject setProject = new SetProject(AuthToken, project);
    	 	SetProjectResponse setProjectResponse  = qmetryPort.setProject(setProject);
         
    	 	ListReleases listReleases = new ListReleases(AuthToken);
    	 	ListReleasesResponse listReleasesResponse = qmetryPort.listReleases(listReleases);
    	 	// returns list of release which set to current scope 
    	 	Entity[] releases = listReleasesResponse.getReleases();
    	 	for(Entity e: releases){
	          	//System.out.println("id: "+ e.getId());
	          	//System.out.println("Name: "+ e.getName()); 
	          	list.add(new VersionInfoModel(e.getName(),  e.getName()));
    	 	}   
    	 	Logout logout= new Logout();
            logout.setToken(AuthToken);
            //System.out.println ("Logout msg: " + qmetryPort.logout(logout));
    	}catch(Exception e){
			System.out.println ("********************Error Logging in to getVersions********************");
				e.printStackTrace();
    		}
    
        return Response.ok(list).build();
 
    }
	public TestCaseExecutionStatusModel buildCycles(String authToken, String project, String release, String build){
		TestCaseExecutionStatusModel caseExecutionStatusModel = null;

		try   {
				 QMetryWS qmetryService  = new QMetryWSLocator();
			     QMetryWSPortType qmetryPort = qmetryService.getQMetryWSSOAPPort_Http(); 
			     String AuthToken = authToken;
			     long totDefects = 0;
			     long totTestCases = 0;
			     long totTestCasesExec = 0;
			     long totPassed = 0;
			     long totFailed = 0;
			     long totNotRun = 0;
			     long totNA = 0;
			     long totBlocked = 0;
				  List testCaseIdlst = new ArrayList();
	     		 try{
	     			 // set scope again with each build name
	     			//System.out.println("Time Stamp before set scope for current project, release and build : "+ convertToSeconds(System.currentTimeMillis())); 
		            SetScope setscope = new SetScope(authToken,project,release,build);
		            SetScopeResponse setScopeResponse  = qmetryPort.setScope(setscope);
		            //System.out.println("Time Stamp after set scope for current project, release and build : "+ convertToSeconds(System.currentTimeMillis()));
	     		 }catch(Exception e){
		    	   	 System.out.println("Scope is not set: "+ e.getMessage() + " build: "+build);
		    	   	 caseExecutionStatusModel = new TestCaseExecutionStatusModel(build,totDefects,totTestCases,totTestCasesExec, totPassed, totFailed, totNotRun, totBlocked, totNA);
		    	   	 return caseExecutionStatusModel;
	     		 }
	            // get the Test Cases      
	             ListTestCases listTestCases = new ListTestCases(AuthToken,new KeyValuePair[1]);
	             KeyValuePair searchText =new KeyValuePair();
	             searchText.setKey("currentscope");
	             searchText.setValue("1");
	             listTestCases.setSearchText(0, searchText);
	             try{
	            	 //System.out.println("Time Stamp before get the list of test cases qmetryPort.listTestCases() : "+ convertToSeconds(System.currentTimeMillis()));
		             ListTestCasesResponse testCaseList = qmetryPort.listTestCases(listTestCases);
		             //System.out.println("Time Stamp after get the list of test cases qmetryPort.listTestCases() : "+ convertToSeconds(System.currentTimeMillis()));
		             if(testCaseList!=null && testCaseList.getTestcases()!=null && testCaseList.getTestcases().length >0 ){
		            	 //System.out.println("total test cases : "+ testCaseList.getTestcases().length);
		            	 totTestCases =  testCaseList.getTestcases().length;
		             }
	             }catch(Exception e){
	            	 System.out.println("No test cases found : "+ e.getMessage());
	             }
	             
	            // get the test suites
	            TestSuiteEntity[] suiteEntities = null;
	            ListTestSuites listTestSuites = new ListTestSuites();
	            listTestSuites.setToken(AuthToken);
	            listTestSuites.setSearchText("");
	            try{
	            	//System.out.println("Time Stamp before get the list of test suites qmetryPort.listTestSuites() : "+ convertToSeconds(System.currentTimeMillis()));
		            ListTestSuitesResponse listTestSuitesResponse = qmetryPort.listTestSuites(listTestSuites);
		            //System.out.println("Time Stamp before get the list of test suites qmetryPort.listTestSuites() : "+ convertToSeconds(System.currentTimeMillis()));
		            suiteEntities = listTestSuitesResponse.getTestsuites();
	            }catch(Exception e){
	            	System.out.println("No test suite found  "+ e.getMessage());
	            }
	           // System.out.println("test suites lenght: "+ listTestSuitesResponse.getTestsuites().length);
	           
	            if(suiteEntities!=null && suiteEntities.length>0){
	            for(TestSuiteEntity suiteEntity: suiteEntities){
	         	   	//System.out.println("Test Suite Id: "+ suiteEntity.getId());
	         	   //	System.out.println("Test Suite name: "+ suiteEntity.getName());
	         	   //	System.out.println("Test Suite Folder Path: "+ suiteEntity.getFolderPath());
	         	   	//System.out.println("Test Suite Status: "+ suiteEntity.getStatus());
	         	   
	         	   	GetTestSuiteExecutions getTestSuiteExecutions  = new GetTestSuiteExecutions(AuthToken, suiteEntity.getId());
	         	   //System.out.println("Time Stamp before get the test suite excecutions qmetryPort.getTestSuiteExecutions() : "+ convertToSeconds(System.currentTimeMillis()));
	         	   	GetTestSuiteExecutionsResponse getTestSuiteExecutionsResponse = qmetryPort.getTestSuiteExecutions(getTestSuiteExecutions);
	         	   //System.out.println("Time Stamp after get the test suite excecutions qmetryPort.getTestSuiteExecutions() : "+ convertToSeconds(System.currentTimeMillis()));
	         	   	TestSuiteExecution[] testSuiteExecutions = getTestSuiteExecutionsResponse.getTestSuiteExecutions();
	         	   	if(testSuiteExecutions!=null && testSuiteExecutions.length>0){
	         	 	for(TestSuiteExecution suiteExecutions: testSuiteExecutions){
	         	 		TestSuitePlatformExecutions platformExecutions = suiteExecutions.getPlatformExecutions();
	         	 		TestSuitePlatformExecution[] platformExecution = platformExecutions.getPlatformExecution();
	         	 		 if(platformExecution!=null && platformExecution.length>0){
	             	 	  for(TestSuitePlatformExecution testSuitePlatformExecution: platformExecution){
	             	 		 TestCaseExecutions testCaseExecutions = testSuitePlatformExecution.getTestCaseExecutions();
	             	 		 TestCaseExecution[] testCaseExecutionList =  testCaseExecutions.getTestCaseExecution();
	             	 		 if(testCaseExecutionList!=null && testCaseExecutionList.length>0){
	             	 		 for(TestCaseExecution testCaseExecution: testCaseExecutionList){
	            			   //	System.out.println("test case id: "+testCaseExecution.getTestCaseId());
	            			   	String tcId = String.valueOf(testCaseExecution.getTestCaseId());
									if(!testCaseIdlst.contains(tcId)){
										testCaseIdlst.add(tcId);
									} else {
										System.out.println("test case id linked to more than one: "+ tcId);
										continue;
									}
	            			   	try{
	            			   		//GetDefectsFromTestCaseId defectsFromTestCaseId = new GetDefectsFromTestCaseId(AuthToken, tcId);
	            			   	 //System.out.println("Time Stamp before get the defect count from testCaseId qmetryPort.getDefectsFromTestCaseId() : "+ convertToSeconds(System.currentTimeMillis()));
	            			   		//GetDefectsFromTestCaseIdResponse getDefectsFromTestCaseIdResponse = qmetryPort.getDefectsFromTestCaseId(defectsFromTestCaseId);
	            			   	 //System.out.println("Time Stamp after get the defect count from testCaseId qmetryPort.getDefectsFromTestCaseId() : "+ convertToSeconds(System.currentTimeMillis()));
	            			   		String[] lstDefects = null ; //getDefectsFromTestCaseIdResponse.getDefectIds();
	                			   	if(lstDefects!=null && lstDefects.length>0){
	                			   		totDefects = totDefects + lstDefects.length;
	                			   	}
	            			   	}catch(Exception exception){
	        			   			//System.out.println("No defects linked to the  : "+tcId);
	            			   	}
	                				//System.out.println("test case name : "+testCaseExecution.getTestCaseName());
	                			   	//System.out.println("test case status: "+testCaseExecution.getExecutionStatus());
	                			   	String status = testCaseExecution.getExecutionStatus();
	                			   	if(status.equalsIgnoreCase("Pass")){
	                			   		totPassed = totPassed + 1;
	                			   	} else if (status.equalsIgnoreCase("Fail")){
	                			   		totFailed = totFailed + 1;
	                			   	} else if(status.equalsIgnoreCase("NotRun")){
	                			   		totNotRun = totNotRun + 1;
	                			   	} else if(status.equalsIgnoreCase("Blocked")){
	                			   		totBlocked = totBlocked + 1;
	                			   	} else if(status.equalsIgnoreCase("NotApplicable")){
	                			   		totNA = totNA + 1;
	                			   	}
	                			   	
	             	 		 }// testcase executions      
	             	 		 }// end if test case  
	             	 	  }// test suite platform executions
	         	 		 }// end if 
	         	 	}// end for test suite executions   
	         	   	}// end if 
	            }// end for test suites
		}// end if test suites
	            totTestCasesExec = testCaseIdlst!=null?testCaseIdlst.size(): 0 ;
	            //System.out.println("Cycle Name: "+ build+ " totDefects: "+ totDefects + " totTestcases: "+ totTestCases + " totPassed: "+ totPassed + " totFailed: "+  totFailed +" totNotRun: "+ totNotRun + " totBlocked: "+ totBlocked +" totNA: "+ totNA );
	            caseExecutionStatusModel = new TestCaseExecutionStatusModel(build,totDefects,totTestCases,totTestCasesExec, totPassed, totFailed, totNotRun, totBlocked, totNA);     
	            
	     }catch(Exception e){
					System.out.println ("********************Error Logging in to QMetry Service********************");
					
	    	 e.printStackTrace();
	     }	
		return caseExecutionStatusModel;
	}
	/**
	 * A utility method to convert from milliseconds to seconds.
	 * @param milliseconds<double> - milliseconds
	 * @return<String> - Returns decimal format 
	 */
	public  String convertToSeconds(double milliseconds)
	{
		  double diff = milliseconds;
		  double diffSeconds = diff / 1000;
		  NumberFormat nfd = new DecimalFormat("#.##");
		   return nfd.format(diffSeconds);
	}
}