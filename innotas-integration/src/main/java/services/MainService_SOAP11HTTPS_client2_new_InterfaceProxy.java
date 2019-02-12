package services;

public class MainService_SOAP11HTTPS_client2_new_InterfaceProxy implements services.MainService_SOAP11HTTPS_client2_new_Interface {
  private String _endpoint = null;
  private services.MainService_SOAP11HTTPS_client2_new_Interface mainService_SOAP11HTTPS_client2_new_Interface = null;
  
  public MainService_SOAP11HTTPS_client2_new_InterfaceProxy() {
    _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
  }
  
  public MainService_SOAP11HTTPS_client2_new_InterfaceProxy(String endpoint) {
    _endpoint = endpoint;
    _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
  }
  
  private void _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy() {
    try {
      mainService_SOAP11HTTPS_client2_new_Interface = (new services.MainService_SOAP11HTTPS_client2_newLocator()).getMainService_SOAP11HTTPS_client2_new();
      if (mainService_SOAP11HTTPS_client2_new_Interface != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mainService_SOAP11HTTPS_client2_new_Interface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mainService_SOAP11HTTPS_client2_new_Interface)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mainService_SOAP11HTTPS_client2_new_Interface != null)
      ((javax.xml.rpc.Stub)mainService_SOAP11HTTPS_client2_new_Interface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public services.MainService_SOAP11HTTPS_client2_new_Interface getMainService_SOAP11HTTPS_client2_new_Interface() {
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface;
  }
  
  public services.objects.xsd.ResourceProfileSetting[] createOrReplaceResourceProfileSetting(java.lang.String sessionId, services.objects.xsd.ResourceProfileSetting[] resourceProfileSettings) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.createOrReplaceResourceProfileSetting(sessionId, resourceProfileSettings);
  }
  
  public java.lang.Boolean logout(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.logout(sessionId);
  }
  
  public java.lang.Long insertEntity(java.lang.String sessionId, java.lang.Long entityTypeId, services.objects.xsd.ValuePair[] valuePairs) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.insertEntity(sessionId, entityTypeId, valuePairs);
  }
  
  public java.lang.Long insertNote(java.lang.String sessionId, java.lang.Long parentEntityTypeId, java.lang.Long parentId, java.lang.String title, java.lang.String content) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.insertNote(sessionId, parentEntityTypeId, parentId, title, content);
  }
  
  public services.objects.xsd.ObjectMethod[] getRequestFields(java.lang.String sessionId, java.lang.Long requestTypeId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getRequestFields(sessionId, requestTypeId);
  }
  
  public services.objects.xsd.Timesheet[] getTimesheetForUser(java.lang.String sessionId, java.lang.Long userId, java.lang.String startYYYYMMDD, java.lang.String endYYYYMMDD) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getTimesheetForUser(sessionId, userId, startYYYYMMDD, endYYYYMMDD);
  }
  
  public java.lang.Long createOrReplaceRequest(java.lang.String sessionId, java.lang.Long requestTypeId, services.objects.xsd.ValuePair[] valuePairs) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.createOrReplaceRequest(sessionId, requestTypeId, valuePairs);
  }
  
  public java.lang.Long getTeamMemberId(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityInstanceId, java.lang.Long resourceId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getTeamMemberId(sessionId, entityTypeId, entityInstanceId, resourceId);
  }
  
  public java.lang.Boolean deleteEntities(java.lang.String sessionId, java.lang.Long entityTypeId, long[] entityIds) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.deleteEntities(sessionId, entityTypeId, entityIds);
  }
  
  public java.lang.Long allocateContour(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId, services.objects.xsd.ContourEntry[] contourEntries) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.allocateContour(sessionId, entityTypeId, entityId, contourEntries);
  }
  
  public java.lang.String allocateResourceTime(java.lang.String sessionId, long[] taskIds, long[] resourceIds, long[] projectRoleIds, java.lang.Float estimateHours, java.lang.Float scheduleHours, java.lang.Float percentOfTime, java.lang.Float hoursToComplete) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.allocateResourceTime(sessionId, taskIds, resourceIds, projectRoleIds, estimateHours, scheduleHours, percentOfTime, hoursToComplete);
  }
  
  public services.objects.xsd.Timesheet[] getTimesheetForUsers(java.lang.String sessionId, long[] userIds, java.lang.String startYYYYMMDD, java.lang.String endYYYYMMDD) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getTimesheetForUsers(sessionId, userIds, startYYYYMMDD, endYYYYMMDD);
  }
  
  public services.objects.xsd.ValuePair[] getRequestTypes(java.lang.String sessionId, java.lang.String idOrTitle) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getRequestTypes(sessionId, idOrTitle);
  }
  
  public services.objects.xsd.EntityHistory[] getUpdateHistory(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.String startYYYYMMDD, java.lang.String endYYYYMMDD, java.lang.Boolean showRepeatingPerDay, java.lang.String simpleDateFormat) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getUpdateHistory(sessionId, entityTypeId, startYYYYMMDD, endYYYYMMDD, showRepeatingPerDay, simpleDateFormat);
  }
  
  public services.objects.xsd.Timesheet getTimesheet(java.lang.String sessionId, java.lang.Long timesheetId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getTimesheet(sessionId, timesheetId);
  }
  
  public java.lang.Boolean updateAnEntityByMethodId(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId, java.lang.Long methodId, java.lang.String methodValue) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.updateAnEntityByMethodId(sessionId, entityTypeId, entityId, methodId, methodValue);
  }
  
  public java.lang.Boolean submitRequest(java.lang.String sessionId, java.lang.Long requestId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.submitRequest(sessionId, requestId);
  }
  
  public services.objects.xsd.EntityHistory[] getDeleteHistory(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.String startYYYYMMDD, java.lang.String endYYYYMMDD, java.lang.String simpleDateFormat) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getDeleteHistory(sessionId, entityTypeId, startYYYYMMDD, endYYYYMMDD, simpleDateFormat);
  }
  
  public java.lang.Boolean updateEntity(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId, services.objects.xsd.ValuePair[] valuePairs) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.updateEntity(sessionId, entityTypeId, entityId, valuePairs);
  }
  
  public services.objects.xsd.Timesheet[] createOrReplaceTimesheets(java.lang.String sessionId, services.objects.xsd.Timesheet[] timesheets, java.lang.Boolean autoSubmitAll) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.createOrReplaceTimesheets(sessionId, timesheets, autoSubmitAll);
  }
  
  public services.objects.xsd.TimesheetVariable[] getTimesheetVariables(java.lang.String sessionId, java.lang.Long userId, java.lang.Long periodId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getTimesheetVariables(sessionId, userId, periodId);
  }
  
  public services.objects.xsd.Timesheet[] createOrReplaceTimesheetEntries(java.lang.String sessionId, services.objects.xsd.Timesheet timesheet, java.lang.Boolean autoSubmit) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.createOrReplaceTimesheetEntries(sessionId, timesheet, autoSubmit);
  }
  
  public java.lang.String login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.login(username, password);
  }
  
  public services.objects.xsd.ObjectMethod[] getPortfolioFields(java.lang.String sessionId, java.lang.Long portfolioTypeId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getPortfolioFields(sessionId, portfolioTypeId);
  }
  
  public java.lang.Long createOrReplacePortfolio(java.lang.String sessionId, java.lang.Long portfolioTypeId, services.objects.xsd.ValuePair[] valuePairs) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.createOrReplacePortfolio(sessionId, portfolioTypeId, valuePairs);
  }
  
  public java.lang.Boolean approveTimesheet(java.lang.String sessionId, java.lang.Long timesheetId, java.lang.Integer approvalLevel) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.approveTimesheet(sessionId, timesheetId, approvalLevel);
  }
  
  public java.lang.Boolean deleteEntity(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.deleteEntity(sessionId, entityTypeId, entityId);
  }
  
  public services.objects.xsd.EntityObj selectEntity(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId, long[] fieldsRequest) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.selectEntity(sessionId, entityTypeId, entityId, fieldsRequest);
  }
  
  public services.objects.xsd.Timesheet[] getTimesheets(java.lang.String sessionId, long[] timesheetIds) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getTimesheets(sessionId, timesheetIds);
  }
  
  public java.lang.Boolean submitTimesheet(java.lang.String sessionId, java.lang.Long timesheetId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.submitTimesheet(sessionId, timesheetId);
  }
  
  public services.objects.xsd.ObjectMethod[] getEntityFields(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long mode) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getEntityFields(sessionId, entityTypeId, mode);
  }
  
  public java.lang.Boolean deleteTimesheetEntries(java.lang.String sessionId, java.lang.Long timesheetId, long[] timesheetEntryId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.deleteTimesheetEntries(sessionId, timesheetId, timesheetEntryId);
  }
  
  public services.objects.xsd.ValuePair[] getListValues(java.lang.String sessionId, java.lang.Long fieldTypeId, java.lang.Long fieldSubTypeId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getListValues(sessionId, fieldTypeId, fieldSubTypeId);
  }
  
  public services.objects.xsd.Contour getContour(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityId, java.lang.String startDate, java.lang.String endDate) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getContour(sessionId, entityTypeId, entityId, startDate, endDate);
  }
  
  public java.lang.String createOrReplace(java.lang.String sessionId, services.objects.xsd.EntityObj newObj, services.objects.xsd.ValuePair[] searchValuePairs) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.createOrReplace(sessionId, newObj, searchValuePairs);
  }
  
  public java.lang.Boolean submitTimesheets(java.lang.String sessionId, long[] timesheetIds) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.submitTimesheets(sessionId, timesheetIds);
  }
  
  public java.lang.Boolean submitRequests(java.lang.String sessionId, long[] requestIds) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.submitRequests(sessionId, requestIds);
  }
  
  public services.objects.xsd.ServerInfoResponse serverInfo(java.lang.String login) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.serverInfo(login);
  }
  
  public java.lang.Boolean updateTimesheetEntries(java.lang.String sessionId, services.objects.xsd.TimesheetEntry[] timesheetEntries) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.updateTimesheetEntries(sessionId, timesheetEntries);
  }
  
  public services.objects.xsd.ProfileVariable[] getSkillCategories(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getSkillCategories(sessionId);
  }
  
  public services.objects.xsd.ResourceProfileSetting[] getResourceProfileSetting(java.lang.String sessionId, long[] resourceId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getResourceProfileSetting(sessionId, resourceId);
  }
  
  public services.objects.xsd.ValuePair[] getPortfolioTypes(java.lang.String sessionId, java.lang.String idOrTitle) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getPortfolioTypes(sessionId, idOrTitle);
  }
  
  public services.objects.xsd.EntityObj[] findEntity(java.lang.String sessionId, java.lang.Long entityTypeId, services.objects.xsd.ValuePair[] searchValuePairs, long[] fieldsRequest) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.findEntity(sessionId, entityTypeId, searchValuePairs, fieldsRequest);
  }
  
  public services.objects.xsd.EntityObj[] getEntitiesByFilter(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.String filter, long[] fieldsRequest) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getEntitiesByFilter(sessionId, entityTypeId, filter, fieldsRequest);
  }
  
  public java.lang.Long getMemberId(java.lang.String sessionId, java.lang.Long entityTypeId, java.lang.Long entityInstanceId, java.lang.Long resourceId) throws java.rmi.RemoteException{
    if (mainService_SOAP11HTTPS_client2_new_Interface == null)
      _initMainService_SOAP11HTTPS_client2_new_InterfaceProxy();
    return mainService_SOAP11HTTPS_client2_new_Interface.getMemberId(sessionId, entityTypeId, entityInstanceId, resourceId);
  }
  
  
}