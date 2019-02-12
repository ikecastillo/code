/**
 * MainService_SOAP11HTTPS_client2_new_Interface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public interface MainService_SOAP11HTTPS_client2_new_Interface extends java.rmi.Remote {
    public services.objects.xsd.ResourceProfileSetting[] createOrReplaceResourceProfileSetting(java.lang.String sessionId, services.objects.xsd.ResourceProfileSetting[] resourceProfileSettings) throws java.rmi.RemoteException;
    public java.lang.Boolean logout(java.lang.String sessionId) throws java.rmi.RemoteException;
    public java.lang.Long insertEntity(java.lang.String sessionId, java.lang.Long entityTypeId, services.objects.xsd.ValuePair[] valuePairs) throws java.rmi.RemoteException;
    public java.lang.Long insertNote(java.lang.String sessionId, java.lang.Long parentEntityTypeId, java.lang.Long parentId, java.lang.String title, java.lang.String content) throws java.rmi.RemoteException;
    public services.objects.xsd.ObjectMethod[] getRequestFields(java.lang.String sessionId, java.lang.Long requestTypeId) throws java.rmi.RemoteException;
    public services.objects.xsd.Timesheet[] getTimesheetForUser(java.lang.String sessionId, java.lang.Long userId, java.lang.String startYYYYMMDD, java.lang.String endYYYYMMDD) throws java.rmi.RemoteException;
    public java.lang.Long createOrReplaceRequest(java.lang.String sessionId, java.lang.Long requestTypeId, services.objects.xsd.ValuePair[] valuePairs) throws java.rmi.RemoteException;
    public java.lang.Long getTeamMemberId(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityInstanceId, java.lang.Long resourceId) throws java.rmi.RemoteException;
    public java.lang.Boolean deleteEntities(java.lang.String sessionId, java.lang.Long entityTypeId, long[] entityIds) throws java.rmi.RemoteException;
    public java.lang.Long allocateContour(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId, services.objects.xsd.ContourEntry[] contourEntries) throws java.rmi.RemoteException;
    public java.lang.String allocateResourceTime(java.lang.String sessionId, long[] taskIds, long[] resourceIds, long[] projectRoleIds, java.lang.Float estimateHours, java.lang.Float scheduleHours, java.lang.Float percentOfTime, java.lang.Float hoursToComplete) throws java.rmi.RemoteException;
    public services.objects.xsd.Timesheet[] getTimesheetForUsers(java.lang.String sessionId, long[] userIds, java.lang.String startYYYYMMDD, java.lang.String endYYYYMMDD) throws java.rmi.RemoteException;
    public services.objects.xsd.ValuePair[] getRequestTypes(java.lang.String sessionId, java.lang.String idOrTitle) throws java.rmi.RemoteException;
    public services.objects.xsd.EntityHistory[] getUpdateHistory(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.String startYYYYMMDD, java.lang.String endYYYYMMDD, java.lang.Boolean showRepeatingPerDay, java.lang.String simpleDateFormat) throws java.rmi.RemoteException;
    public services.objects.xsd.Timesheet getTimesheet(java.lang.String sessionId, java.lang.Long timesheetId) throws java.rmi.RemoteException;
    public java.lang.Boolean updateAnEntityByMethodId(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId, java.lang.Long methodId, java.lang.String methodValue) throws java.rmi.RemoteException;
    public java.lang.Boolean submitRequest(java.lang.String sessionId, java.lang.Long requestId) throws java.rmi.RemoteException;
    public services.objects.xsd.EntityHistory[] getDeleteHistory(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.String startYYYYMMDD, java.lang.String endYYYYMMDD, java.lang.String simpleDateFormat) throws java.rmi.RemoteException;
    public java.lang.Boolean updateEntity(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId, services.objects.xsd.ValuePair[] valuePairs) throws java.rmi.RemoteException;
    public services.objects.xsd.Timesheet[] createOrReplaceTimesheets(java.lang.String sessionId, services.objects.xsd.Timesheet[] timesheets, java.lang.Boolean autoSubmitAll) throws java.rmi.RemoteException;
    public services.objects.xsd.TimesheetVariable[] getTimesheetVariables(java.lang.String sessionId, java.lang.Long userId, java.lang.Long periodId) throws java.rmi.RemoteException;
    public services.objects.xsd.Timesheet[] createOrReplaceTimesheetEntries(java.lang.String sessionId, services.objects.xsd.Timesheet timesheet, java.lang.Boolean autoSubmit) throws java.rmi.RemoteException;
    public java.lang.String login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
    public services.objects.xsd.ObjectMethod[] getPortfolioFields(java.lang.String sessionId, java.lang.Long portfolioTypeId) throws java.rmi.RemoteException;
    public java.lang.Long createOrReplacePortfolio(java.lang.String sessionId, java.lang.Long portfolioTypeId, services.objects.xsd.ValuePair[] valuePairs) throws java.rmi.RemoteException;
    public java.lang.Boolean approveTimesheet(java.lang.String sessionId, java.lang.Long timesheetId, java.lang.Integer approvalLevel) throws java.rmi.RemoteException;
    public java.lang.Boolean deleteEntity(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId) throws java.rmi.RemoteException;
    public services.objects.xsd.EntityObj selectEntity(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId, long[] fieldsRequest) throws java.rmi.RemoteException;
    public services.objects.xsd.Timesheet[] getTimesheets(java.lang.String sessionId, long[] timesheetIds) throws java.rmi.RemoteException;
    public java.lang.Boolean submitTimesheet(java.lang.String sessionId, java.lang.Long timesheetId) throws java.rmi.RemoteException;
    public services.objects.xsd.ObjectMethod[] getEntityFields(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long mode) throws java.rmi.RemoteException;
    public java.lang.Boolean deleteTimesheetEntries(java.lang.String sessionId, java.lang.Long timesheetId, long[] timesheetEntryId) throws java.rmi.RemoteException;
    public services.objects.xsd.ValuePair[] getListValues(java.lang.String sessionId, java.lang.Long fieldTypeId, java.lang.Long fieldSubTypeId) throws java.rmi.RemoteException;
    public services.objects.xsd.Contour getContour(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId, java.lang.String startDate, java.lang.String endDate) throws java.rmi.RemoteException;
    public java.lang.String createOrReplace(java.lang.String sessionId, services.objects.xsd.EntityObj newObj, services.objects.xsd.ValuePair[] searchValuePairs) throws java.rmi.RemoteException;
    public java.lang.Boolean submitTimesheets(java.lang.String sessionId, long[] timesheetIds) throws java.rmi.RemoteException;
    public java.lang.Boolean submitRequests(java.lang.String sessionId, long[] requestIds) throws java.rmi.RemoteException;
    public services.objects.xsd.ServerInfoResponse serverInfo(java.lang.String login) throws java.rmi.RemoteException;
    public java.lang.Boolean updateTimesheetEntries(java.lang.String sessionId, services.objects.xsd.TimesheetEntry[] timesheetEntries) throws java.rmi.RemoteException;
    public services.objects.xsd.ProfileVariable[] getSkillCategories(java.lang.String sessionId) throws java.rmi.RemoteException;
    public services.objects.xsd.ResourceProfileSetting[] getResourceProfileSetting(java.lang.String sessionId, long[] resourceId) throws java.rmi.RemoteException;
    public services.objects.xsd.ValuePair[] getPortfolioTypes(java.lang.String sessionId, java.lang.String idOrTitle) throws java.rmi.RemoteException;
    public services.objects.xsd.EntityObj[] findEntity(java.lang.String sessionId, java.lang.Long entityTypeId, services.objects.xsd.ValuePair[] searchValuePairs, long[] fieldsRequest) throws java.rmi.RemoteException;
    public services.objects.xsd.EntityObj[] getEntitiesByFilter(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.String filter, long[] fieldsRequest) throws java.rmi.RemoteException;
    public java.lang.Long getMemberId(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityInstanceId, java.lang.Long resourceId) throws java.rmi.RemoteException;
}
