package com.dt.jira.plugin.utils;

import java.text.DecimalFormat;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.atlassian.jira.JiraException;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
/**
 * Class is generic class implemented to use the methods in across all the other classes in the module.
 * @author kiran.muthoju
 *
 */
public class CommonService {
	/**
	 * Get the search service object from jira api.
	 * @return - Returns <SearchService>
	 */
	public static SearchService getSearchService(){
		final SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
		return searchServ;
	}
	/**
	 * Get the custom field manager object from jira api.
	 * @return - Returns <CustomFieldManager>
	 */
	public static CustomFieldManager getCustomFieldManager(){
		 final CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
		 return fieldManager;
		}
	/**
	 * Get the change history  manager object from jira api.
	 * @return - Returns <ChangeHistoryManager>
	 */
	public static ChangeHistoryManager getChangeHistoryManager(){
		 final  ChangeHistoryManager changeHistoryManager = ComponentAccessor.getChangeHistoryManager();
		 return changeHistoryManager;
		}
	/**
	 * Get the jira authentication context manager object from jira api.
	 * @return - Returns <JiraAuthenticationContext>
	 */
	public static JiraAuthenticationContext getJiraAuthenticationContext(){
		 final  JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
		 return authenticationContext;
		}
	/**
	 * The Method is implement to  build the jql query with given input parmeters
	 * @param issuetypes<String> - type of the issue
	 * @param projectkey<String> - project key
	 * @param version<String> - fixed version or release
	 * @param sprintId<String> - sprint id
	 * @param appendQuery<StringBuffer> - Returns jql query string
	 * @return
	 */
	public static StringBuffer buildJQLQuery(String issuetypes,String projectkey,String version,String sprintId, String appendQuery){
		  //String jql="project = SSP and fixVersion in ('Version 2.0') and Sprint in (2) ORDER BY Rank ASC";
		  StringBuffer jql = new StringBuffer("issueType in (" + issuetypes + ") ");
			if(appendQuery!=null && !appendQuery.equals("")){
				jql.append(" and " + appendQuery);
			}
		  
		  if(projectkey!=null){
			  jql.append(" and project = '"+projectkey+"'");		 
		  } 
		  if(projectkey!=null  && version!=null && !version.equals("All")){
			  jql.append(" and fixVersion in ("+ version +")");
		  } 
		  if(projectkey!=null  && sprintId != null && !sprintId.equals("All")){
			   jql.append(" and Sprint in ("+ sprintId +")");
		  }
		return jql;
	}
	/**
	 * Get the search  results from jira 
	 * @param jql<String> - jql query string
	 * @return <SearchResults> Returns SearchResults objects
	 * @throws JiraException
	 */
	public static SearchResults getSerarchResults(String jql) throws JiraException{
		 SearchService searchServ = CommonService.getSearchService();   
		 JiraAuthenticationContext authenticationContext = CommonService.getJiraAuthenticationContext();
		 SearchResults results = null;;
		 final SearchService.ParseResult parseResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
		    if (parseResult.isValid())
		    {         
		          results = searchServ.search(authenticationContext.getLoggedInUser(),parseResult.getQuery(), PagerFilter.getUnlimitedFilter());
		          // long totValidDef = results1.getIssues().size();
		    }
		   return results;
	}
	
	/**
	 * A utility method to convert from milliseconds to days.
	 * @param milliseconds<double> - milliseconds
	 * @return<String> - Returns decimal format 
	 */
	public static String convertToDays(double milliseconds)
	{
	  double diff = milliseconds;
	  double diffDays = diff / (24 * 60 * 60 * 1000);
	  NumberFormat nfd = new DecimalFormat("#.##");
	   
	   return nfd.format(diffDays);
	}
	/**
	 * A utility method to convert from milliseconds to hours.
	 * @param milliseconds<double> - milliseconds
	 * @return<String> - Returns decimal format 
	 */
	public static String convertToHours(double milliseconds)
	{
	  double diff = milliseconds;
	  double diffHours = diff / (60 * 60 * 1000);
	  NumberFormat nfd = new DecimalFormat("#.##");
	   return nfd.format(diffHours);
	}
	/**
	 * A utility method to convert from milliseconds to minutes.
	 * @param milliseconds<double> - milliseconds
	 * @return<String> - Returns decimal format 
	 */
	public static String convertToMinutes(double milliseconds)
	{
	  double diff = milliseconds;
	  double diffMinutes = diff / (60 * 1000);
	  NumberFormat nfd = new DecimalFormat("#.##");
	  return nfd.format(diffMinutes);
	}
	/**
	 * A utility method to convert from milliseconds to seconds.
	 * @param milliseconds<double> - milliseconds
	 * @return<String> - Returns decimal format 
	 */
	public static String convertToSeconds(double milliseconds)
	{
		  double diff = milliseconds;
		  double diffSeconds = diff / 1000;
		  //long diffMinutes = diff / (60 * 1000);
		  //long diffHours = diff / (60 * 60 * 1000);
		  //double diffDays = diff / (24 * 60 * 60 * 1000);
		  NumberFormat nfd = new DecimalFormat("#.##");
		   return nfd.format(diffSeconds);
	}
	/**
	 * Get the custom field value from issue object
	 * @param issue<Issue> - Issue object
	 * @param field<String> - name of the custom filed. 
	 * @return<String> - Returns the value
	 */
	public static String getCustomFieldValue(Issue issue,String field){
		 CustomField  customField= getCustomFieldManager().getCustomFieldObjectByName(field);
		 Option selectedVal = (Option) issue.getCustomFieldValue(customField);
			String key= "";
			key = (selectedVal!=null)?selectedVal.getValue():"Other";   
		 
		return key;	
	}
	/**
	 * Method convert from the one format(yyyy-MM-dd) to another format(dd-MMM-yyyy)
	 * 
	 */
	public static String convertDateFormat(String sprintdate){
		String endate=sprintdate;
		endate = endate.replace('T', ' ');
		Date date =null;
		String format= "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dat = null;		
		try {
			date = formatter.parse(endate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
		String date2 = formatter1.format(date);
		//System.out.println(date2);
		return date2;
	}
	/**
	 * Method to get the count of holidays from the given input start date and end date
	 * 
	 */
	public static int getHolidays(String startdate,String endate){
		 int count = 0;
		
		try{
			 SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yy" );
			   String start_date=startdate;
			   SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				Date startd = formatter.parse(start_date);
				
			   String end_date = endate;
			   Date endd = formatter.parse(end_date);
			   int interval = 1; //days
			   
			   Date d = startd;
			   while (d.before(endd)) {			   
		           
		            df.applyPattern( "EEE" );  
		            String day= df.format( d ); 
		            if(day.compareTo("Sat")==0 || day.compareTo("Sun")==0)
		            {
		                  count++;
		            }
				   d=addDays(d, interval);
			   }
			   
				   
				}catch(Exception e){
					e.printStackTrace();
				}
		return count;
	}
	public static Date addDays(Date d, int days)
    {
		Date date = d;
		date.setTime(d.getTime() + (days * 1000 * 60 * 60 * 24));
	
        return date;
    }
	
	/**
	 * Get a diff between two dates
	 * @param date1 the oldest date
	 * @param date2 the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	public static SimpleDateFormat getDefaultFormatter(){
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		 return formatter;
	}
	public static  SimpleDateFormat getJiraFormatter(){
		SimpleDateFormat jira_formatter = new SimpleDateFormat("yyyy-MM-dd");
		 return jira_formatter;
	}
}
