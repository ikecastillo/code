/**
 * QMetryWSSOAPBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php;

public class QMetryWSSOAPBindingStub extends org.apache.axis.client.Stub implements org.wso2.www.php.QMetryWSPortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[102];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
        _initOperationDesc6();
        _initOperationDesc7();
        _initOperationDesc8();
        _initOperationDesc9();
        _initOperationDesc10();
        _initOperationDesc11();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("linkPlatformToTestSuite");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "linkPlatformToTestSuite"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkPlatformToTestSuite"), org.wso2.www.php.xsd.LinkPlatformToTestSuite.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkPlatformToTestSuiteResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.LinkPlatformToTestSuiteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "linkPlatformToTestSuiteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("downloadAttachment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "downloadAttachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">downloadAttachment"), org.wso2.www.php.xsd.DownloadAttachment.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">downloadAttachmentResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.DownloadAttachmentResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "downloadAttachmentResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRelease");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getRelease"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRelease"), org.wso2.www.php.xsd.GetRelease.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getReleaseResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetReleaseResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getReleaseResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTestCaseWithStepsUsingRunId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseWithStepsUsingRunId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithStepsUsingRunId"), org.wso2.www.php.xsd.ExecuteTestCaseWithStepsUsingRunId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithStepsUsingRunIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ExecuteTestCaseWithStepsUsingRunIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseWithStepsUsingRunIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createReleaseBuild");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createReleaseBuild"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createReleaseBuild"), org.wso2.www.php.xsd.CreateReleaseBuild.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createReleaseBuildResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.CreateReleaseBuildResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createReleaseBuildResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listDefectTrackers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listDefectTrackers"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listDefectTrackers"), org.wso2.www.php.xsd.ListDefectTrackers.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listDefectTrackersResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListDefectTrackersResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listDefectTrackersResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setScope");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setScope"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setScope"), org.wso2.www.php.xsd.SetScope.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setScopeResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.SetScopeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setScopeResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listTestCasesFromFolderId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listTestCasesFromFolderId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestCasesFromFolderId"), org.wso2.www.php.xsd.ListTestCasesFromFolderId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestCasesFromFolderIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListTestCasesFromFolderIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listTestCasesFromFolderIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addTestLog");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "addTestLog"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addTestLog"), org.wso2.www.php.xsd.AddTestLog.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addTestLogResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.AddTestLogResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "addTestLogResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listFolders");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listFolders"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFolders"), org.wso2.www.php.xsd.ListFolders.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListFoldersResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listFoldersResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setRelease");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setRelease"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setRelease"), org.wso2.www.php.xsd.SetRelease.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setReleaseResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.SetReleaseResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setReleaseResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listUsers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listUsers"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listUsers"), org.wso2.www.php.xsd.ListUsers.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listUsersResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListUsersResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listUsersResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTestSuiteStatusByPlatform");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestSuiteStatusByPlatform"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteStatusByPlatform"), org.wso2.www.php.xsd.GetTestSuiteStatusByPlatform.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteStatusByPlatformResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetTestSuiteStatusByPlatformResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestSuiteStatusByPlatformResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTestCase");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCase"), org.wso2.www.php.xsd.ExecuteTestCase.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ExecuteTestCaseResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRequirementsFromDefectId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getRequirementsFromDefectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementsFromDefectId"), org.wso2.www.php.xsd.GetRequirementsFromDefectId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementsFromDefectIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetRequirementsFromDefectIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getRequirementsFromDefectIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listReleasesWithTargetDate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listReleasesWithTargetDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listReleasesWithTargetDate"), org.wso2.www.php.xsd.ListReleasesWithTargetDate.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listReleasesWithTargetDateResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListReleasesWithTargetDateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listReleasesWithTargetDateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTestSuiteById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestSuiteById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteById"), org.wso2.www.php.xsd.GetTestSuiteById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteByIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetTestSuiteByIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestSuiteByIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addTestLogUsingRunId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "addTestLogUsingRunId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addTestLogUsingRunId"), org.wso2.www.php.xsd.AddTestLogUsingRunId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addTestLogUsingRunIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.AddTestLogUsingRunIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "addTestLogUsingRunIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTestCaseUsingRunId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseUsingRunId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseUsingRunId"), org.wso2.www.php.xsd.ExecuteTestCaseUsingRunId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseUsingRunIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ExecuteTestCaseUsingRunIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseUsingRunIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getProject"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getProject"), org.wso2.www.php.xsd.GetProject.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getProjectResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetProjectResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getProjectResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listAttachments");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listAttachments"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listAttachments"), org.wso2.www.php.xsd.ListAttachments.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listAttachmentsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListAttachmentsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listAttachmentsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createPlatform");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createPlatform"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createPlatform"), org.wso2.www.php.xsd.CreatePlatform.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createPlatformResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.CreatePlatformResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createPlatformResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRequirementById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getRequirementById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementById"), org.wso2.www.php.xsd.GetRequirementById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementByIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetRequirementByIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getRequirementByIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("login");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "login"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">login"), org.wso2.www.php.xsd.Login.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">loginResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.LoginResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "loginResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("logout");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "logout"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">logout"), org.wso2.www.php.xsd.Logout.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">logoutResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.LogoutResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "logoutResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDefectsFromTestCaseId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getDefectsFromTestCaseId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getDefectsFromTestCaseId"), org.wso2.www.php.xsd.GetDefectsFromTestCaseId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getDefectsFromTestCaseIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetDefectsFromTestCaseIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getDefectsFromTestCaseIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteAttachment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteAttachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteAttachment"), org.wso2.www.php.xsd.DeleteAttachment.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteAttachmentResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.DeleteAttachmentResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteAttachmentResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listRequirementsFromFolderId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listRequirementsFromFolderId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listRequirementsFromFolderId"), org.wso2.www.php.xsd.ListRequirementsFromFolderId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listRequirementsFromFolderIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListRequirementsFromFolderIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listRequirementsFromFolderIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listCustomizedList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listCustomizedList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listCustomizedList"), org.wso2.www.php.xsd.ListCustomizedList.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listCustomizedListResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListCustomizedListResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listCustomizedListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setProject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setProject"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setProject"), org.wso2.www.php.xsd.SetProject.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setProjectResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.SetProjectResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setProjectResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteBuild");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteBuild"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteBuild"), org.wso2.www.php.xsd.DeleteBuild.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteBuildResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.DeleteBuildResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteBuildResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTestCaseById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestCaseById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCaseById"), org.wso2.www.php.xsd.GetTestCaseById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCaseByIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetTestCaseByIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestCaseByIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listProjects");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listProjects"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listProjects"), org.wso2.www.php.xsd.ListProjects.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listProjectsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListProjectsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listProjectsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("logExecutionHistory");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "logExecutionHistory"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">logExecutionHistory"), org.wso2.www.php.xsd.LogExecutionHistory.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">logExecutionHistoryResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.LogExecutionHistoryResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "logExecutionHistoryResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("QMetryVersion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "QMetryVersion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">QMetryVersion"), org.wso2.www.php.xsd.QMetryVersion.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">QMetryVersionResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.QMetryVersionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "QMetryVersionResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listFoldersWithStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listFoldersWithStatus"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersWithStatus"), org.wso2.www.php.xsd.ListFoldersWithStatus.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersWithStatusResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListFoldersWithStatusResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listFoldersWithStatusResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateTestSuite");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "updateTestSuite"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateTestSuite"), org.wso2.www.php.xsd.UpdateTestSuite.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateTestSuiteResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.UpdateTestSuiteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "updateTestSuiteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteTestCase");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteTestCase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteTestCase"), org.wso2.www.php.xsd.DeleteTestCase.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteTestCaseResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.DeleteTestCaseResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteTestCaseResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteFolder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteFolder"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteFolder"), org.wso2.www.php.xsd.DeleteFolder.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteFolderResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.DeleteFolderResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteFolderResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createFolder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createFolder"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createFolder"), org.wso2.www.php.xsd.CreateFolder.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createFolderResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.CreateFolderResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createFolderResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBuild");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getBuild"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getBuild"), org.wso2.www.php.xsd.GetBuild.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getBuildResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetBuildResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getBuildResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTestCaseWithComments");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseWithComments"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithComments"), org.wso2.www.php.xsd.ExecuteTestCaseWithComments.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithCommentsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseWithCommentsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTestCaseRunIds");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestCaseRunIds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCaseRunIds"), org.wso2.www.php.xsd.GetTestCaseRunIds.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCaseRunIdsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetTestCaseRunIdsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestCaseRunIdsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteRelease");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteRelease"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteRelease"), org.wso2.www.php.xsd.DeleteRelease.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteReleaseResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.DeleteReleaseResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteReleaseResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listFoldersFromParentId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listFoldersFromParentId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersFromParentId"), org.wso2.www.php.xsd.ListFoldersFromParentId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersFromParentIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListFoldersFromParentIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listFoldersFromParentIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listTestSuitesFromFolderId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listTestSuitesFromFolderId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestSuitesFromFolderId"), org.wso2.www.php.xsd.ListTestSuitesFromFolderId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestSuitesFromFolderIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListTestSuitesFromFolderIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listTestSuitesFromFolderIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listStatuses");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listStatuses"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listStatuses"), org.wso2.www.php.xsd.ListStatuses.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listStatusesResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListStatusesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listStatusesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[46] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTestCaseWithSteps");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseWithSteps"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithSteps"), org.wso2.www.php.xsd.ExecuteTestCaseWithSteps.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithStepsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ExecuteTestCaseWithStepsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseWithStepsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[47] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTestcaseStepsStausByPlatform");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestcaseStepsStausByPlatform"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestcaseStepsStausByPlatform"), org.wso2.www.php.xsd.GetTestcaseStepsStausByPlatform.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestcaseStepsStausByPlatformResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetTestcaseStepsStausByPlatformResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestcaseStepsStausByPlatformResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[48] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("assignProject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "assignProject"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">assignProject"), org.wso2.www.php.xsd.AssignProject.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">assignProjectResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.AssignProjectResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "assignProjectResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[49] = oper;

    }

    private static void _initOperationDesc6(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setBuild");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setBuild"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setBuild"), org.wso2.www.php.xsd.SetBuild.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setBuildResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.SetBuildResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setBuildResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[50] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTestCaseSteps");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseSteps"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseSteps"), org.wso2.www.php.xsd.ExecuteTestCaseSteps.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseStepsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ExecuteTestCaseStepsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseStepsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[51] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createSchedule");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createSchedule"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createSchedule"), org.wso2.www.php.xsd.CreateSchedule.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createScheduleResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.CreateScheduleResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createScheduleResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[52] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTestCasesFromJiraDefectId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestCasesFromJiraDefectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCasesFromJiraDefectId"), org.wso2.www.php.xsd.GetTestCasesFromJiraDefectId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCasesFromJiraDefectIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetTestCasesFromJiraDefectIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestCasesFromJiraDefectIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[53] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTestCaseStepWithResult");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseStepWithResult"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseStepWithResult"), org.wso2.www.php.xsd.ExecuteTestCaseStepWithResult.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseStepWithResultResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ExecuteTestCaseStepWithResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseStepWithResultResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[54] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("associateDefect");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "associateDefect"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">associateDefect"), org.wso2.www.php.xsd.AssociateDefect.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">associateDefectResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.AssociateDefectResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "associateDefectResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[55] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("activateProject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "activateProject"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">activateProject"), org.wso2.www.php.xsd.ActivateProject.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">activateProjectResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ActivateProjectResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "activateProjectResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[56] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateSchedulerComments");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "updateSchedulerComments"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateSchedulerComments"), org.wso2.www.php.xsd.UpdateSchedulerComments.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateSchedulerCommentsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.UpdateSchedulerCommentsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "updateSchedulerCommentsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[57] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("wsSearchDrops");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "wsSearchDrops"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsSearchDrops"), org.wso2.www.php.xsd.WsSearchDrops.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsSearchDropsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.WsSearchDropsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "wsSearchDropsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[58] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteProject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteProject"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteProject"), org.wso2.www.php.xsd.DeleteProject.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteProjectResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.DeleteProjectResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteProjectResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[59] = oper;

    }

    private static void _initOperationDesc7(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listTestCases");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listTestCases"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestCases"), org.wso2.www.php.xsd.ListTestCases.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestCasesResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListTestCasesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listTestCasesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[60] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createTestCaseStep");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createTestCaseStep"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestCaseStep"), org.wso2.www.php.xsd.CreateTestCaseStep.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestCaseStepResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.CreateTestCaseStepResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createTestCaseStepResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[61] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("linkTestCaseWithTestSuite");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "linkTestCaseWithTestSuite"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkTestCaseWithTestSuite"), org.wso2.www.php.xsd.LinkTestCaseWithTestSuite.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkTestCaseWithTestSuiteResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.LinkTestCaseWithTestSuiteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "linkTestCaseWithTestSuiteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[62] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listReleases");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listReleases"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listReleases"), org.wso2.www.php.xsd.ListReleases.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listReleasesResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListReleasesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listReleasesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[63] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addAttachment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "addAttachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addAttachment"), org.wso2.www.php.xsd.AddAttachment.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addAttachmentResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.AddAttachmentResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "addAttachmentResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[64] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createUser");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createUser"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createUser"), org.wso2.www.php.xsd.CreateUser.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createUserResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.CreateUserResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createUserResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[65] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTestDataVariables");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestDataVariables"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestDataVariables"), org.wso2.www.php.xsd.GetTestDataVariables.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestDataVariablesResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetTestDataVariablesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestDataVariablesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[66] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setTestSuiteStartTime");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setTestSuiteStartTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setTestSuiteStartTime"), org.wso2.www.php.xsd.SetTestSuiteStartTime.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setTestSuiteStartTimeResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.SetTestSuiteStartTimeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setTestSuiteStartTimeResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[67] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("wsArchiveDrops");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "wsArchiveDrops"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsArchiveDrops"), org.wso2.www.php.xsd.WsArchiveDrops.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsArchiveDropsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.WsArchiveDropsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "wsArchiveDropsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[68] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPlatformStatusByPlatformId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getPlatformStatusByPlatformId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getPlatformStatusByPlatformId"), org.wso2.www.php.xsd.GetPlatformStatusByPlatformId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getPlatformStatusByPlatformIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetPlatformStatusByPlatformIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getPlatformStatusByPlatformIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[69] = oper;

    }

    private static void _initOperationDesc8(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRequirementsJiraFromDefectId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getRequirementsJiraFromDefectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementsJiraFromDefectId"), org.wso2.www.php.xsd.GetRequirementsJiraFromDefectId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementsJiraFromDefectIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetRequirementsJiraFromDefectIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getRequirementsJiraFromDefectIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[70] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listFoldersWithStatusFromParentId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listFoldersWithStatusFromParentId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersWithStatusFromParentId"), org.wso2.www.php.xsd.ListFoldersWithStatusFromParentId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersWithStatusFromParentIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListFoldersWithStatusFromParentIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listFoldersWithStatusFromParentIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[71] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listPlatformsByTestSuite");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listPlatformsByTestSuite"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listPlatformsByTestSuite"), org.wso2.www.php.xsd.ListPlatformsByTestSuite.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listPlatformsByTestSuiteResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListPlatformsByTestSuiteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listPlatformsByTestSuiteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[72] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTestCaseWithCommentsAndTester");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseWithCommentsAndTester"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithCommentsAndTester"), org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsAndTester.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithCommentsAndTesterResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsAndTesterResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseWithCommentsAndTesterResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[73] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setTestSuiteEndTime");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setTestSuiteEndTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setTestSuiteEndTime"), org.wso2.www.php.xsd.SetTestSuiteEndTime.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setTestSuiteEndTimeResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.SetTestSuiteEndTimeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "setTestSuiteEndTimeResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[74] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTestSuite");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestSuite"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestSuite"), org.wso2.www.php.xsd.ExecuteTestSuite.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestSuiteResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ExecuteTestSuiteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestSuiteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[75] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createTestSuite");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createTestSuite"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestSuite"), org.wso2.www.php.xsd.CreateTestSuite.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestSuiteResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.CreateTestSuiteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createTestSuiteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[76] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listBuilds");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listBuilds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listBuilds"), org.wso2.www.php.xsd.ListBuilds.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listBuildsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListBuildsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listBuildsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[77] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateTestCase");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "updateTestCase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateTestCase"), org.wso2.www.php.xsd.UpdateTestCase.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateTestCaseResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.UpdateTestCaseResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "updateTestCaseResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[78] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listUserRoles");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listUserRoles"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listUserRoles"), org.wso2.www.php.xsd.ListUserRoles.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listUserRolesResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListUserRolesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listUserRolesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[79] = oper;

    }

    private static void _initOperationDesc9(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listRequirements");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listRequirements"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listRequirements"), org.wso2.www.php.xsd.ListRequirements.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listRequirementsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListRequirementsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listRequirementsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[80] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listBuildsWithTargetDate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listBuildsWithTargetDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listBuildsWithTargetDate"), org.wso2.www.php.xsd.ListBuildsWithTargetDate.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listBuildsWithTargetDateResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListBuildsWithTargetDateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listBuildsWithTargetDateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[81] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("linkTestCasesWithTestSuite");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "linkTestCasesWithTestSuite"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkTestCasesWithTestSuite"), org.wso2.www.php.xsd.LinkTestCasesWithTestSuite.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkTestCasesWithTestSuiteResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.LinkTestCasesWithTestSuiteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "linkTestCasesWithTestSuiteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[82] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUserDefinedFields");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getUserDefinedFields"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getUserDefinedFields"), org.wso2.www.php.xsd.GetUserDefinedFields.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getUserDefinedFieldsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetUserDefinedFieldsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getUserDefinedFieldsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[83] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createRequirement");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createRequirement"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createRequirement"), org.wso2.www.php.xsd.CreateRequirement.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createRequirementResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.CreateRequirementResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createRequirementResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[84] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("wsCreateDrops");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "wsCreateDrops"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsCreateDrops"), org.wso2.www.php.xsd.WsCreateDrops.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsCreateDropsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.WsCreateDropsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "wsCreateDropsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[85] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTestcaseStepsFromTestcaseId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestcaseStepsFromTestcaseId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestcaseStepsFromTestcaseId"), org.wso2.www.php.xsd.GetTestcaseStepsFromTestcaseId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestcaseStepsFromTestcaseIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetTestcaseStepsFromTestcaseIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestcaseStepsFromTestcaseIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[86] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createProject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createProject"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createProject"), org.wso2.www.php.xsd.CreateProject.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createProjectResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.CreateProjectResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createProjectResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[87] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTestCaseStep");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseStep"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseStep"), org.wso2.www.php.xsd.ExecuteTestCaseStep.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseStepResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ExecuteTestCaseStepResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executeTestCaseStepResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[88] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listCustomizedListValues");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listCustomizedListValues"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listCustomizedListValues"), org.wso2.www.php.xsd.ListCustomizedListValues.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listCustomizedListValuesResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListCustomizedListValuesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listCustomizedListValuesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[89] = oper;

    }

    private static void _initOperationDesc10(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createTestCase");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createTestCase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestCase"), org.wso2.www.php.xsd.CreateTestCase.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestCaseResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.CreateTestCaseResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "createTestCaseResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[90] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTestSuiteExecutions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestSuiteExecutions"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteExecutions"), org.wso2.www.php.xsd.GetTestSuiteExecutions.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteExecutionsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetTestSuiteExecutionsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestSuiteExecutionsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[91] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deactivateProject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deactivateProject"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deactivateProject"), org.wso2.www.php.xsd.DeactivateProject.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deactivateProjectResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.DeactivateProjectResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deactivateProjectResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[92] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("associateDefectTracker");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "associateDefectTracker"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">associateDefectTracker"), org.wso2.www.php.xsd.AssociateDefectTracker.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">associateDefectTrackerResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.AssociateDefectTrackerResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "associateDefectTrackerResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[93] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateRequirement");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "updateRequirement"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateRequirement"), org.wso2.www.php.xsd.UpdateRequirement.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateRequirementResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.UpdateRequirementResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "updateRequirementResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[94] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("wsAssignDrops");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "wsAssignDrops"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsAssignDrops"), org.wso2.www.php.xsd.WsAssignDrops.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsAssignDropsResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.WsAssignDropsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "wsAssignDropsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[95] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listTestSuites");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listTestSuites"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestSuites"), org.wso2.www.php.xsd.ListTestSuites.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestSuitesResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.ListTestSuitesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listTestSuitesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[96] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("disablePlatform");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "disablePlatform"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">disablePlatform"), org.wso2.www.php.xsd.DisablePlatform.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">disablePlatformResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.DisablePlatformResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "disablePlatformResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[97] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getScope");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getScope"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getScope"), org.wso2.www.php.xsd.GetScope.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getScopeResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetScopeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getScopeResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[98] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getListIdFromValue");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getListIdFromValue"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getListIdFromValue"), org.wso2.www.php.xsd.GetListIdFromValue.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getListIdFromValueResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetListIdFromValueResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getListIdFromValueResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[99] = oper;

    }

    private static void _initOperationDesc11(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTestCasesFromDefectId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestCasesFromDefectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCasesFromDefectId"), org.wso2.www.php.xsd.GetTestCasesFromDefectId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCasesFromDefectIdResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.GetTestCasesFromDefectIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "getTestCasesFromDefectIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[100] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteTestSuite");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteTestSuite"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteTestSuite"), org.wso2.www.php.xsd.DeleteTestSuite.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteTestSuiteResponse"));
        oper.setReturnClass(org.wso2.www.php.xsd.DeleteTestSuiteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "deleteTestSuiteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[101] = oper;

    }

    public QMetryWSSOAPBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public QMetryWSSOAPBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public QMetryWSSOAPBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        addBindings0();
        addBindings1();
        addBindings2();
    }

    private void addBindings0() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">activateProject");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ActivateProject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">activateProjectResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ActivateProjectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addAttachment");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AddAttachment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addAttachmentResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AddAttachmentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addTestLog");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AddTestLog.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addTestLogResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AddTestLogResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addTestLogUsingRunId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AddTestLogUsingRunId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addTestLogUsingRunIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AddTestLogUsingRunIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">assignProject");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AssignProject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">assignProjectResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AssignProjectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">associateDefect");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AssociateDefect.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">associateDefectResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AssociateDefectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">associateDefectTracker");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AssociateDefectTracker.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">associateDefectTrackerResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AssociateDefectTrackerResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createFolder");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateFolder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createFolderResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateFolderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createPlatform");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreatePlatform.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createPlatformResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreatePlatformResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createProject");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateProject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createProjectResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateProjectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createReleaseBuild");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateReleaseBuild.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createReleaseBuildResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateReleaseBuildResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createRequirement");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateRequirement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createRequirementResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateRequirementResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createSchedule");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateSchedule.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createScheduleResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateScheduleResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestCase");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateTestCase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestCaseResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateTestCaseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestCaseStep");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateTestCaseStep.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestCaseStepResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateTestCaseStepResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestSuite");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateTestSuite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestSuiteResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateTestSuiteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createUser");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createUserResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.CreateUserResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deactivateProject");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeactivateProject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deactivateProjectResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeactivateProjectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteAttachment");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteAttachment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteAttachmentResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteAttachmentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteBuild");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteBuild.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteBuildResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteBuildResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteFolder");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteFolder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteFolderResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteFolderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteProject");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteProject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteProjectResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteProjectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteRelease");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteRelease.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteReleaseResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteReleaseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteTestCase");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteTestCase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteTestCaseResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteTestCaseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteTestSuite");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteTestSuite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteTestSuiteResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DeleteTestSuiteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">disablePlatform");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DisablePlatform.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">disablePlatformResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DisablePlatformResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">downloadAttachment");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DownloadAttachment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">downloadAttachmentResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DownloadAttachmentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCase");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseStep");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseStep.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseStepResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseStepResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseSteps");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseSteps.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseStepsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseStepsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseStepWithResult");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseStepWithResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseStepWithResultResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseStepWithResultResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseUsingRunId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseUsingRunId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseUsingRunIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseUsingRunIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithComments");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseWithComments.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithCommentsAndTester");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsAndTester.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithCommentsAndTesterResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsAndTesterResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithCommentsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithSteps");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseWithSteps.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithStepsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseWithStepsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithStepsUsingRunId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseWithStepsUsingRunId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithStepsUsingRunIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestCaseWithStepsUsingRunIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestSuite");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestSuite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestSuiteResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ExecuteTestSuiteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getBuild");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetBuild.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getBuildResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetBuildResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getDefectsFromTestCaseId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetDefectsFromTestCaseId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getDefectsFromTestCaseIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetDefectsFromTestCaseIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getListIdFromValue");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetListIdFromValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getListIdFromValueResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetListIdFromValueResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getPlatformStatusByPlatformId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetPlatformStatusByPlatformId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getPlatformStatusByPlatformIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetPlatformStatusByPlatformIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getProject");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetProject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getProjectResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetProjectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRelease");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetRelease.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getReleaseResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetReleaseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementById");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetRequirementById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementByIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetRequirementByIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementsFromDefectId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetRequirementsFromDefectId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementsFromDefectIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetRequirementsFromDefectIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementsJiraFromDefectId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetRequirementsJiraFromDefectId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementsJiraFromDefectIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetRequirementsJiraFromDefectIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getScope");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetScope.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getScopeResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetScopeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCaseById");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestCaseById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCaseByIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestCaseByIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCaseRunIds");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestCaseRunIds.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCaseRunIdsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestCaseRunIdsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCasesFromDefectId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestCasesFromDefectId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCasesFromDefectIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestCasesFromDefectIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCasesFromJiraDefectId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestCasesFromJiraDefectId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCasesFromJiraDefectIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestCasesFromJiraDefectIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestcaseStepsFromTestcaseId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestcaseStepsFromTestcaseId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestcaseStepsFromTestcaseIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestcaseStepsFromTestcaseIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestcaseStepsStausByPlatform");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestcaseStepsStausByPlatform.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestcaseStepsStausByPlatformResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestcaseStepsStausByPlatformResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestDataVariables");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestDataVariables.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestDataVariablesResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestDataVariablesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteById");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestSuiteById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteByIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestSuiteByIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteExecutions");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestSuiteExecutions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteExecutionsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestSuiteExecutionsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteStatusByPlatform");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestSuiteStatusByPlatform.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteStatusByPlatformResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetTestSuiteStatusByPlatformResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getUserDefinedFields");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetUserDefinedFields.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getUserDefinedFieldsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.GetUserDefinedFieldsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkPlatformToTestSuite");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.LinkPlatformToTestSuite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkPlatformToTestSuiteResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.LinkPlatformToTestSuiteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkTestCasesWithTestSuite");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.LinkTestCasesWithTestSuite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkTestCasesWithTestSuiteResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.LinkTestCasesWithTestSuiteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkTestCaseWithTestSuite");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.LinkTestCaseWithTestSuite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkTestCaseWithTestSuiteResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.LinkTestCaseWithTestSuiteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listAttachments");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListAttachments.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listAttachmentsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListAttachmentsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listBuilds");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListBuilds.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listBuildsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListBuildsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listBuildsWithTargetDate");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListBuildsWithTargetDate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listBuildsWithTargetDateResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListBuildsWithTargetDateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listCustomizedList");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListCustomizedList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listCustomizedListResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListCustomizedListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listCustomizedListValues");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListCustomizedListValues.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listCustomizedListValuesResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListCustomizedListValuesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listDefectTrackers");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListDefectTrackers.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listDefectTrackersResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListDefectTrackersResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFolders");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListFolders.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersFromParentId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListFoldersFromParentId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersFromParentIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListFoldersFromParentIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListFoldersResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersWithStatus");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListFoldersWithStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersWithStatusFromParentId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListFoldersWithStatusFromParentId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersWithStatusFromParentIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListFoldersWithStatusFromParentIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersWithStatusResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListFoldersWithStatusResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listPlatformsByTestSuite");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListPlatformsByTestSuite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listPlatformsByTestSuiteResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListPlatformsByTestSuiteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listProjects");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListProjects.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listProjectsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListProjectsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listReleases");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListReleases.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listReleasesResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListReleasesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listReleasesWithTargetDate");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListReleasesWithTargetDate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listReleasesWithTargetDateResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListReleasesWithTargetDateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listRequirements");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListRequirements.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listRequirementsFromFolderId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListRequirementsFromFolderId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listRequirementsFromFolderIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListRequirementsFromFolderIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listRequirementsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListRequirementsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listStatuses");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListStatuses.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listStatusesResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListStatusesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestCases");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListTestCases.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestCasesFromFolderId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListTestCasesFromFolderId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestCasesFromFolderIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListTestCasesFromFolderIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestCasesResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListTestCasesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestSuites");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListTestSuites.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestSuitesFromFolderId");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListTestSuitesFromFolderId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestSuitesFromFolderIdResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListTestSuitesFromFolderIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestSuitesResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListTestSuitesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listUserRoles");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListUserRoles.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listUserRolesResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListUserRolesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listUsers");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListUsers.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listUsersResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ListUsersResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">logExecutionHistory");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.LogExecutionHistory.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">logExecutionHistoryResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.LogExecutionHistoryResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">login");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.Login.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">loginResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.LoginResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">logout");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.Logout.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">logoutResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.LogoutResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">QMetryVersion");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.QMetryVersion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">QMetryVersionResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.QMetryVersionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setBuild");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetBuild.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setBuildResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetBuildResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setProject");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetProject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setProjectResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetProjectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setRelease");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetRelease.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setReleaseResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetReleaseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setScope");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetScope.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setScopeResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetScopeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setTestSuiteEndTime");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetTestSuiteEndTime.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setTestSuiteEndTimeResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetTestSuiteEndTimeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setTestSuiteStartTime");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetTestSuiteStartTime.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setTestSuiteStartTimeResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.SetTestSuiteStartTimeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateRequirement");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.UpdateRequirement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateRequirementResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.UpdateRequirementResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateSchedulerComments");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.UpdateSchedulerComments.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateSchedulerCommentsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.UpdateSchedulerCommentsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateTestCase");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.UpdateTestCase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateTestCaseResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.UpdateTestCaseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateTestSuite");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.UpdateTestSuite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">updateTestSuiteResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.UpdateTestSuiteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsArchiveDrops");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.WsArchiveDrops.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsArchiveDropsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.WsArchiveDropsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsAssignDrops");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.WsAssignDrops.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsAssignDropsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.WsAssignDropsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings2() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsCreateDrops");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.WsCreateDrops.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsCreateDropsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.WsCreateDropsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsSearchDrops");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.WsSearchDrops.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsSearchDropsResponse");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.WsSearchDropsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "AttachmentEntity");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.AttachmentEntity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "DefectTracker");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.DefectTracker.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "Entity");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.Entity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "FolderDetails");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.FolderDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "FolderStatus");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.FolderStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "KeyValuePair");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.KeyValuePair.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "PlatformAttribute");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.PlatformAttribute.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "PlatformAttributes");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.PlatformAttributes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "RequirementElements");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.RequirementElements.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "RequirementEntity");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.RequirementEntity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "ScopeInfo");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.ScopeInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "StatusEntity");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.StatusEntity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseDomainAssociation");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestCaseDomainAssociation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseElements");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestCaseElements.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseEntity");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestCaseEntity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseExecution");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestCaseExecution.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseExecutions");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestCaseExecutions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseRunDetails");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestCaseRunDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepExecution");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestCaseStepExecution.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepExecutions");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestCaseStepExecutions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepRunDetails");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestCaseStepRunDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepsEntity");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestCaseStepsEntity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestLogs");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestLogs.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteEntity");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestSuiteEntity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteExecution");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestSuiteExecution.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuitePlatformExecution");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestSuitePlatformExecution.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuitePlatformExecutions");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestSuitePlatformExecutions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteRunByPlatform");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestSuiteRunByPlatform.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteRunDetails");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.TestSuiteRunDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefinedField");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.UserDefinedField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefinedFieldArray");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.UserDefinedFieldArray.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefinedFieldDefination");
            cachedSerQNames.add(qName);
            cls = org.wso2.www.php.xsd.UserDefinedFieldDefination.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public org.wso2.www.php.xsd.LinkPlatformToTestSuiteResponse linkPlatformToTestSuite(org.wso2.www.php.xsd.LinkPlatformToTestSuite parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/linkPlatformToTestSuite");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "linkPlatformToTestSuite"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.LinkPlatformToTestSuiteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.LinkPlatformToTestSuiteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.LinkPlatformToTestSuiteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.DownloadAttachmentResponse downloadAttachment(org.wso2.www.php.xsd.DownloadAttachment parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/downloadAttachment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "downloadAttachment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.DownloadAttachmentResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.DownloadAttachmentResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.DownloadAttachmentResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetReleaseResponse getRelease(org.wso2.www.php.xsd.GetRelease parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getRelease");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getRelease"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetReleaseResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetReleaseResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetReleaseResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ExecuteTestCaseWithStepsUsingRunIdResponse executeTestCaseWithStepsUsingRunId(org.wso2.www.php.xsd.ExecuteTestCaseWithStepsUsingRunId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/executeTestCaseWithStepsUsingRunId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executeTestCaseWithStepsUsingRunId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ExecuteTestCaseWithStepsUsingRunIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ExecuteTestCaseWithStepsUsingRunIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ExecuteTestCaseWithStepsUsingRunIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.CreateReleaseBuildResponse createReleaseBuild(org.wso2.www.php.xsd.CreateReleaseBuild parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/createReleaseBuild");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createReleaseBuild"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.CreateReleaseBuildResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.CreateReleaseBuildResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.CreateReleaseBuildResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListDefectTrackersResponse listDefectTrackers(org.wso2.www.php.xsd.ListDefectTrackers parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listDefectTrackers");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listDefectTrackers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListDefectTrackersResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListDefectTrackersResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListDefectTrackersResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.SetScopeResponse setScope(org.wso2.www.php.xsd.SetScope parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/setScope");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setScope"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.SetScopeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.SetScopeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.SetScopeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListTestCasesFromFolderIdResponse listTestCasesFromFolderId(org.wso2.www.php.xsd.ListTestCasesFromFolderId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listTestCasesFromFolderId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listTestCasesFromFolderId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListTestCasesFromFolderIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListTestCasesFromFolderIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListTestCasesFromFolderIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.AddTestLogResponse addTestLog(org.wso2.www.php.xsd.AddTestLog parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/addTestLog");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "addTestLog"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.AddTestLogResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.AddTestLogResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.AddTestLogResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListFoldersResponse listFolders(org.wso2.www.php.xsd.ListFolders parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listFolders");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listFolders"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListFoldersResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListFoldersResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListFoldersResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.SetReleaseResponse setRelease(org.wso2.www.php.xsd.SetRelease parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/setRelease");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setRelease"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.SetReleaseResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.SetReleaseResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.SetReleaseResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListUsersResponse listUsers(org.wso2.www.php.xsd.ListUsers parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listUsers");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listUsers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListUsersResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListUsersResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListUsersResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetTestSuiteStatusByPlatformResponse getTestSuiteStatusByPlatform(org.wso2.www.php.xsd.GetTestSuiteStatusByPlatform parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getTestSuiteStatusByPlatform");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTestSuiteStatusByPlatform"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetTestSuiteStatusByPlatformResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetTestSuiteStatusByPlatformResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetTestSuiteStatusByPlatformResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ExecuteTestCaseResponse executeTestCase(org.wso2.www.php.xsd.ExecuteTestCase parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/executeTestCase");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executeTestCase"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ExecuteTestCaseResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ExecuteTestCaseResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ExecuteTestCaseResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetRequirementsFromDefectIdResponse getRequirementsFromDefectId(org.wso2.www.php.xsd.GetRequirementsFromDefectId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getRequirementsFromDefectId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getRequirementsFromDefectId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetRequirementsFromDefectIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetRequirementsFromDefectIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetRequirementsFromDefectIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListReleasesWithTargetDateResponse listReleasesWithTargetDate(org.wso2.www.php.xsd.ListReleasesWithTargetDate parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listReleasesWithTargetDate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listReleasesWithTargetDate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListReleasesWithTargetDateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListReleasesWithTargetDateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListReleasesWithTargetDateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetTestSuiteByIdResponse getTestSuiteById(org.wso2.www.php.xsd.GetTestSuiteById parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getTestSuiteById");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTestSuiteById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetTestSuiteByIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetTestSuiteByIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetTestSuiteByIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.AddTestLogUsingRunIdResponse addTestLogUsingRunId(org.wso2.www.php.xsd.AddTestLogUsingRunId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/addTestLogUsingRunId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "addTestLogUsingRunId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.AddTestLogUsingRunIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.AddTestLogUsingRunIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.AddTestLogUsingRunIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ExecuteTestCaseUsingRunIdResponse executeTestCaseUsingRunId(org.wso2.www.php.xsd.ExecuteTestCaseUsingRunId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/executeTestCaseUsingRunId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executeTestCaseUsingRunId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ExecuteTestCaseUsingRunIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ExecuteTestCaseUsingRunIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ExecuteTestCaseUsingRunIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetProjectResponse getProject(org.wso2.www.php.xsd.GetProject parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getProject");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getProject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetProjectResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetProjectResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetProjectResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListAttachmentsResponse listAttachments(org.wso2.www.php.xsd.ListAttachments parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listAttachments");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listAttachments"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListAttachmentsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListAttachmentsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListAttachmentsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.CreatePlatformResponse createPlatform(org.wso2.www.php.xsd.CreatePlatform parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/createPlatform");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createPlatform"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.CreatePlatformResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.CreatePlatformResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.CreatePlatformResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetRequirementByIdResponse getRequirementById(org.wso2.www.php.xsd.GetRequirementById parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getRequirementById");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getRequirementById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetRequirementByIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetRequirementByIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetRequirementByIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.LoginResponse login(org.wso2.www.php.xsd.Login parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/login");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "login"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.LoginResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.LoginResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.LoginResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.LogoutResponse logout(org.wso2.www.php.xsd.Logout parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/logout");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "logout"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.LogoutResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.LogoutResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.LogoutResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetDefectsFromTestCaseIdResponse getDefectsFromTestCaseId(org.wso2.www.php.xsd.GetDefectsFromTestCaseId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getDefectsFromTestCaseId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getDefectsFromTestCaseId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetDefectsFromTestCaseIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetDefectsFromTestCaseIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetDefectsFromTestCaseIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.DeleteAttachmentResponse deleteAttachment(org.wso2.www.php.xsd.DeleteAttachment parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/deleteAttachment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteAttachment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.DeleteAttachmentResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.DeleteAttachmentResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.DeleteAttachmentResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListRequirementsFromFolderIdResponse listRequirementsFromFolderId(org.wso2.www.php.xsd.ListRequirementsFromFolderId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listRequirementsFromFolderId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listRequirementsFromFolderId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListRequirementsFromFolderIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListRequirementsFromFolderIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListRequirementsFromFolderIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListCustomizedListResponse listCustomizedList(org.wso2.www.php.xsd.ListCustomizedList parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listCustomizedList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listCustomizedList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListCustomizedListResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListCustomizedListResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListCustomizedListResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.SetProjectResponse setProject(org.wso2.www.php.xsd.SetProject parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/setProject");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setProject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.SetProjectResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.SetProjectResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.SetProjectResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.DeleteBuildResponse deleteBuild(org.wso2.www.php.xsd.DeleteBuild parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/deleteBuild");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteBuild"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.DeleteBuildResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.DeleteBuildResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.DeleteBuildResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetTestCaseByIdResponse getTestCaseById(org.wso2.www.php.xsd.GetTestCaseById parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getTestCaseById");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTestCaseById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetTestCaseByIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetTestCaseByIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetTestCaseByIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListProjectsResponse listProjects(org.wso2.www.php.xsd.ListProjects parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listProjects");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listProjects"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListProjectsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListProjectsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListProjectsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.LogExecutionHistoryResponse logExecutionHistory(org.wso2.www.php.xsd.LogExecutionHistory parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/logExecutionHistory");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "logExecutionHistory"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.LogExecutionHistoryResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.LogExecutionHistoryResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.LogExecutionHistoryResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.QMetryVersionResponse QMetryVersion(org.wso2.www.php.xsd.QMetryVersion parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/QMetryVersion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "QMetryVersion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.QMetryVersionResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.QMetryVersionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.QMetryVersionResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListFoldersWithStatusResponse listFoldersWithStatus(org.wso2.www.php.xsd.ListFoldersWithStatus parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listFoldersWithStatus");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listFoldersWithStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListFoldersWithStatusResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListFoldersWithStatusResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListFoldersWithStatusResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.UpdateTestSuiteResponse updateTestSuite(org.wso2.www.php.xsd.UpdateTestSuite parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/updateTestSuite");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "updateTestSuite"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.UpdateTestSuiteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.UpdateTestSuiteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.UpdateTestSuiteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.DeleteTestCaseResponse deleteTestCase(org.wso2.www.php.xsd.DeleteTestCase parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/deleteTestCase");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteTestCase"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.DeleteTestCaseResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.DeleteTestCaseResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.DeleteTestCaseResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.DeleteFolderResponse deleteFolder(org.wso2.www.php.xsd.DeleteFolder parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/deleteFolder");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteFolder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.DeleteFolderResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.DeleteFolderResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.DeleteFolderResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.CreateFolderResponse createFolder(org.wso2.www.php.xsd.CreateFolder parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/createFolder");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createFolder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.CreateFolderResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.CreateFolderResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.CreateFolderResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetBuildResponse getBuild(org.wso2.www.php.xsd.GetBuild parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getBuild");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getBuild"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetBuildResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetBuildResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetBuildResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsResponse executeTestCaseWithComments(org.wso2.www.php.xsd.ExecuteTestCaseWithComments parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/executeTestCaseWithComments");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executeTestCaseWithComments"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetTestCaseRunIdsResponse getTestCaseRunIds(org.wso2.www.php.xsd.GetTestCaseRunIds parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getTestCaseRunIds");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTestCaseRunIds"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetTestCaseRunIdsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetTestCaseRunIdsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetTestCaseRunIdsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.DeleteReleaseResponse deleteRelease(org.wso2.www.php.xsd.DeleteRelease parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/deleteRelease");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteRelease"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.DeleteReleaseResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.DeleteReleaseResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.DeleteReleaseResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListFoldersFromParentIdResponse listFoldersFromParentId(org.wso2.www.php.xsd.ListFoldersFromParentId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listFoldersFromParentId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listFoldersFromParentId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListFoldersFromParentIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListFoldersFromParentIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListFoldersFromParentIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListTestSuitesFromFolderIdResponse listTestSuitesFromFolderId(org.wso2.www.php.xsd.ListTestSuitesFromFolderId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listTestSuitesFromFolderId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listTestSuitesFromFolderId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListTestSuitesFromFolderIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListTestSuitesFromFolderIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListTestSuitesFromFolderIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListStatusesResponse listStatuses(org.wso2.www.php.xsd.ListStatuses parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listStatuses");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listStatuses"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListStatusesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListStatusesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListStatusesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ExecuteTestCaseWithStepsResponse executeTestCaseWithSteps(org.wso2.www.php.xsd.ExecuteTestCaseWithSteps parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[47]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/executeTestCaseWithSteps");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executeTestCaseWithSteps"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ExecuteTestCaseWithStepsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ExecuteTestCaseWithStepsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ExecuteTestCaseWithStepsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetTestcaseStepsStausByPlatformResponse getTestcaseStepsStausByPlatform(org.wso2.www.php.xsd.GetTestcaseStepsStausByPlatform parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[48]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getTestcaseStepsStausByPlatform");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTestcaseStepsStausByPlatform"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetTestcaseStepsStausByPlatformResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetTestcaseStepsStausByPlatformResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetTestcaseStepsStausByPlatformResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.AssignProjectResponse assignProject(org.wso2.www.php.xsd.AssignProject parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[49]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/assignProject");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "assignProject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.AssignProjectResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.AssignProjectResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.AssignProjectResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.SetBuildResponse setBuild(org.wso2.www.php.xsd.SetBuild parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[50]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/setBuild");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setBuild"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.SetBuildResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.SetBuildResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.SetBuildResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ExecuteTestCaseStepsResponse executeTestCaseSteps(org.wso2.www.php.xsd.ExecuteTestCaseSteps parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[51]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/executeTestCaseSteps");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executeTestCaseSteps"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ExecuteTestCaseStepsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ExecuteTestCaseStepsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ExecuteTestCaseStepsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.CreateScheduleResponse createSchedule(org.wso2.www.php.xsd.CreateSchedule parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[52]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/createSchedule");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createSchedule"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.CreateScheduleResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.CreateScheduleResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.CreateScheduleResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetTestCasesFromJiraDefectIdResponse getTestCasesFromJiraDefectId(org.wso2.www.php.xsd.GetTestCasesFromJiraDefectId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[53]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getTestCasesFromJiraDefectId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTestCasesFromJiraDefectId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetTestCasesFromJiraDefectIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetTestCasesFromJiraDefectIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetTestCasesFromJiraDefectIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ExecuteTestCaseStepWithResultResponse executeTestCaseStepWithResult(org.wso2.www.php.xsd.ExecuteTestCaseStepWithResult parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[54]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/executeTestCaseStepWithResult");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executeTestCaseStepWithResult"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ExecuteTestCaseStepWithResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ExecuteTestCaseStepWithResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ExecuteTestCaseStepWithResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.AssociateDefectResponse associateDefect(org.wso2.www.php.xsd.AssociateDefect parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[55]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/associateDefect");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "associateDefect"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.AssociateDefectResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.AssociateDefectResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.AssociateDefectResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ActivateProjectResponse activateProject(org.wso2.www.php.xsd.ActivateProject parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[56]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/activateProject");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "activateProject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ActivateProjectResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ActivateProjectResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ActivateProjectResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.UpdateSchedulerCommentsResponse updateSchedulerComments(org.wso2.www.php.xsd.UpdateSchedulerComments parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[57]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/updateSchedulerComments");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "updateSchedulerComments"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.UpdateSchedulerCommentsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.UpdateSchedulerCommentsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.UpdateSchedulerCommentsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.WsSearchDropsResponse wsSearchDrops(org.wso2.www.php.xsd.WsSearchDrops parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[58]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/wsSearchDrops");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "wsSearchDrops"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.WsSearchDropsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.WsSearchDropsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.WsSearchDropsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.DeleteProjectResponse deleteProject(org.wso2.www.php.xsd.DeleteProject parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[59]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/deleteProject");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteProject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.DeleteProjectResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.DeleteProjectResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.DeleteProjectResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListTestCasesResponse listTestCases(org.wso2.www.php.xsd.ListTestCases parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[60]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listTestCases");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listTestCases"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListTestCasesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListTestCasesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListTestCasesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.CreateTestCaseStepResponse createTestCaseStep(org.wso2.www.php.xsd.CreateTestCaseStep parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[61]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/createTestCaseStep");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createTestCaseStep"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.CreateTestCaseStepResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.CreateTestCaseStepResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.CreateTestCaseStepResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.LinkTestCaseWithTestSuiteResponse linkTestCaseWithTestSuite(org.wso2.www.php.xsd.LinkTestCaseWithTestSuite parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[62]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/linkTestCaseWithTestSuite");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "linkTestCaseWithTestSuite"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.LinkTestCaseWithTestSuiteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.LinkTestCaseWithTestSuiteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.LinkTestCaseWithTestSuiteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListReleasesResponse listReleases(org.wso2.www.php.xsd.ListReleases parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[63]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listReleases");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listReleases"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListReleasesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListReleasesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListReleasesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.AddAttachmentResponse addAttachment(org.wso2.www.php.xsd.AddAttachment parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[64]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/addAttachment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "addAttachment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.AddAttachmentResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.AddAttachmentResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.AddAttachmentResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.CreateUserResponse createUser(org.wso2.www.php.xsd.CreateUser parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[65]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/createUser");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createUser"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.CreateUserResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.CreateUserResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.CreateUserResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetTestDataVariablesResponse getTestDataVariables(org.wso2.www.php.xsd.GetTestDataVariables parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[66]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getTestDataVariables");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTestDataVariables"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetTestDataVariablesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetTestDataVariablesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetTestDataVariablesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.SetTestSuiteStartTimeResponse setTestSuiteStartTime(org.wso2.www.php.xsd.SetTestSuiteStartTime parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[67]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/setTestSuiteStartTime");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setTestSuiteStartTime"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.SetTestSuiteStartTimeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.SetTestSuiteStartTimeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.SetTestSuiteStartTimeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.WsArchiveDropsResponse wsArchiveDrops(org.wso2.www.php.xsd.WsArchiveDrops parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[68]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/wsArchiveDrops");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "wsArchiveDrops"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.WsArchiveDropsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.WsArchiveDropsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.WsArchiveDropsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetPlatformStatusByPlatformIdResponse getPlatformStatusByPlatformId(org.wso2.www.php.xsd.GetPlatformStatusByPlatformId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[69]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getPlatformStatusByPlatformId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getPlatformStatusByPlatformId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetPlatformStatusByPlatformIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetPlatformStatusByPlatformIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetPlatformStatusByPlatformIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetRequirementsJiraFromDefectIdResponse getRequirementsJiraFromDefectId(org.wso2.www.php.xsd.GetRequirementsJiraFromDefectId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[70]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getRequirementsJiraFromDefectId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getRequirementsJiraFromDefectId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetRequirementsJiraFromDefectIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetRequirementsJiraFromDefectIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetRequirementsJiraFromDefectIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListFoldersWithStatusFromParentIdResponse listFoldersWithStatusFromParentId(org.wso2.www.php.xsd.ListFoldersWithStatusFromParentId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[71]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listFoldersWithStatusFromParentId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listFoldersWithStatusFromParentId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListFoldersWithStatusFromParentIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListFoldersWithStatusFromParentIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListFoldersWithStatusFromParentIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListPlatformsByTestSuiteResponse listPlatformsByTestSuite(org.wso2.www.php.xsd.ListPlatformsByTestSuite parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[72]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listPlatformsByTestSuite");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listPlatformsByTestSuite"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListPlatformsByTestSuiteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListPlatformsByTestSuiteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListPlatformsByTestSuiteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsAndTesterResponse executeTestCaseWithCommentsAndTester(org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsAndTester parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[73]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/executeTestCaseWithCommentsAndTester");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executeTestCaseWithCommentsAndTester"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsAndTesterResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsAndTesterResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ExecuteTestCaseWithCommentsAndTesterResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.SetTestSuiteEndTimeResponse setTestSuiteEndTime(org.wso2.www.php.xsd.SetTestSuiteEndTime parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[74]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/setTestSuiteEndTime");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setTestSuiteEndTime"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.SetTestSuiteEndTimeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.SetTestSuiteEndTimeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.SetTestSuiteEndTimeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ExecuteTestSuiteResponse executeTestSuite(org.wso2.www.php.xsd.ExecuteTestSuite parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[75]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/executeTestSuite");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executeTestSuite"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ExecuteTestSuiteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ExecuteTestSuiteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ExecuteTestSuiteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.CreateTestSuiteResponse createTestSuite(org.wso2.www.php.xsd.CreateTestSuite parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[76]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/createTestSuite");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createTestSuite"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.CreateTestSuiteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.CreateTestSuiteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.CreateTestSuiteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListBuildsResponse listBuilds(org.wso2.www.php.xsd.ListBuilds parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[77]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listBuilds");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listBuilds"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListBuildsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListBuildsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListBuildsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.UpdateTestCaseResponse updateTestCase(org.wso2.www.php.xsd.UpdateTestCase parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[78]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/updateTestCase");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "updateTestCase"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.UpdateTestCaseResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.UpdateTestCaseResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.UpdateTestCaseResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListUserRolesResponse listUserRoles(org.wso2.www.php.xsd.ListUserRoles parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[79]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listUserRoles");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listUserRoles"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListUserRolesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListUserRolesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListUserRolesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListRequirementsResponse listRequirements(org.wso2.www.php.xsd.ListRequirements parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[80]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listRequirements");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listRequirements"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListRequirementsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListRequirementsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListRequirementsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListBuildsWithTargetDateResponse listBuildsWithTargetDate(org.wso2.www.php.xsd.ListBuildsWithTargetDate parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[81]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listBuildsWithTargetDate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listBuildsWithTargetDate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListBuildsWithTargetDateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListBuildsWithTargetDateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListBuildsWithTargetDateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.LinkTestCasesWithTestSuiteResponse linkTestCasesWithTestSuite(org.wso2.www.php.xsd.LinkTestCasesWithTestSuite parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[82]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/linkTestCasesWithTestSuite");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "linkTestCasesWithTestSuite"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.LinkTestCasesWithTestSuiteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.LinkTestCasesWithTestSuiteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.LinkTestCasesWithTestSuiteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetUserDefinedFieldsResponse getUserDefinedFields(org.wso2.www.php.xsd.GetUserDefinedFields parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[83]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getUserDefinedFields");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getUserDefinedFields"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetUserDefinedFieldsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetUserDefinedFieldsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetUserDefinedFieldsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.CreateRequirementResponse createRequirement(org.wso2.www.php.xsd.CreateRequirement parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[84]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/createRequirement");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createRequirement"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.CreateRequirementResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.CreateRequirementResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.CreateRequirementResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.WsCreateDropsResponse wsCreateDrops(org.wso2.www.php.xsd.WsCreateDrops parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[85]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/wsCreateDrops");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "wsCreateDrops"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.WsCreateDropsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.WsCreateDropsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.WsCreateDropsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetTestcaseStepsFromTestcaseIdResponse getTestcaseStepsFromTestcaseId(org.wso2.www.php.xsd.GetTestcaseStepsFromTestcaseId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[86]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getTestcaseStepsFromTestcaseId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTestcaseStepsFromTestcaseId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetTestcaseStepsFromTestcaseIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetTestcaseStepsFromTestcaseIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetTestcaseStepsFromTestcaseIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.CreateProjectResponse createProject(org.wso2.www.php.xsd.CreateProject parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[87]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/createProject");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createProject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.CreateProjectResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.CreateProjectResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.CreateProjectResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ExecuteTestCaseStepResponse executeTestCaseStep(org.wso2.www.php.xsd.ExecuteTestCaseStep parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[88]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/executeTestCaseStep");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executeTestCaseStep"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ExecuteTestCaseStepResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ExecuteTestCaseStepResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ExecuteTestCaseStepResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListCustomizedListValuesResponse listCustomizedListValues(org.wso2.www.php.xsd.ListCustomizedListValues parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[89]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listCustomizedListValues");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listCustomizedListValues"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListCustomizedListValuesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListCustomizedListValuesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListCustomizedListValuesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.CreateTestCaseResponse createTestCase(org.wso2.www.php.xsd.CreateTestCase parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[90]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/createTestCase");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createTestCase"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.CreateTestCaseResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.CreateTestCaseResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.CreateTestCaseResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetTestSuiteExecutionsResponse getTestSuiteExecutions(org.wso2.www.php.xsd.GetTestSuiteExecutions parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[91]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getTestSuiteExecutions");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTestSuiteExecutions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetTestSuiteExecutionsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetTestSuiteExecutionsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetTestSuiteExecutionsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.DeactivateProjectResponse deactivateProject(org.wso2.www.php.xsd.DeactivateProject parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[92]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/deactivateProject");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deactivateProject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.DeactivateProjectResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.DeactivateProjectResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.DeactivateProjectResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.AssociateDefectTrackerResponse associateDefectTracker(org.wso2.www.php.xsd.AssociateDefectTracker parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[93]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/associateDefectTracker");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "associateDefectTracker"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.AssociateDefectTrackerResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.AssociateDefectTrackerResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.AssociateDefectTrackerResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.UpdateRequirementResponse updateRequirement(org.wso2.www.php.xsd.UpdateRequirement parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[94]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/updateRequirement");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "updateRequirement"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.UpdateRequirementResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.UpdateRequirementResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.UpdateRequirementResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.WsAssignDropsResponse wsAssignDrops(org.wso2.www.php.xsd.WsAssignDrops parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[95]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/wsAssignDrops");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "wsAssignDrops"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.WsAssignDropsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.WsAssignDropsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.WsAssignDropsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.ListTestSuitesResponse listTestSuites(org.wso2.www.php.xsd.ListTestSuites parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[96]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/listTestSuites");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listTestSuites"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.ListTestSuitesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.ListTestSuitesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.ListTestSuitesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.DisablePlatformResponse disablePlatform(org.wso2.www.php.xsd.DisablePlatform parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[97]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/disablePlatform");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "disablePlatform"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.DisablePlatformResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.DisablePlatformResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.DisablePlatformResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetScopeResponse getScope(org.wso2.www.php.xsd.GetScope parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[98]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getScope");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getScope"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetScopeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetScopeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetScopeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetListIdFromValueResponse getListIdFromValue(org.wso2.www.php.xsd.GetListIdFromValue parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[99]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getListIdFromValue");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getListIdFromValue"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetListIdFromValueResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetListIdFromValueResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetListIdFromValueResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.GetTestCasesFromDefectIdResponse getTestCasesFromDefectId(org.wso2.www.php.xsd.GetTestCasesFromDefectId parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[100]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/getTestCasesFromDefectId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTestCasesFromDefectId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.GetTestCasesFromDefectIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.GetTestCasesFromDefectIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.GetTestCasesFromDefectIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.wso2.www.php.xsd.DeleteTestSuiteResponse deleteTestSuite(org.wso2.www.php.xsd.DeleteTestSuite parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[101]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://10.134.8.17/qmetry/WEB-INF/ws/service.php/deleteTestSuite");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteTestSuite"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.wso2.www.php.xsd.DeleteTestSuiteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.wso2.www.php.xsd.DeleteTestSuiteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.wso2.www.php.xsd.DeleteTestSuiteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
