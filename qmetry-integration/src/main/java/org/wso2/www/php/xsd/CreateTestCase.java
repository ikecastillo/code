/**
 * CreateTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class CreateTestCase  implements java.io.Serializable {
    private java.lang.String token;

    private int parentFolderId;

    private java.lang.String testcaseName;

    private java.lang.String testcaseObjective;

    private int component;

    private int testcaseStatus;

    private int assignTo;

    private java.lang.String preCondition;

    private java.lang.String postCondition;

    private int priority;

    private java.lang.String executionTime;

    private int testcaseType;

    private int modifiedReason;

    private int testingType;

    private org.wso2.www.php.xsd.KeyValuePair[] udfInfo;

    public CreateTestCase() {
    }

    public CreateTestCase(
           java.lang.String token,
           int parentFolderId,
           java.lang.String testcaseName,
           java.lang.String testcaseObjective,
           int component,
           int testcaseStatus,
           int assignTo,
           java.lang.String preCondition,
           java.lang.String postCondition,
           int priority,
           java.lang.String executionTime,
           int testcaseType,
           int modifiedReason,
           int testingType,
           org.wso2.www.php.xsd.KeyValuePair[] udfInfo) {
           this.token = token;
           this.parentFolderId = parentFolderId;
           this.testcaseName = testcaseName;
           this.testcaseObjective = testcaseObjective;
           this.component = component;
           this.testcaseStatus = testcaseStatus;
           this.assignTo = assignTo;
           this.preCondition = preCondition;
           this.postCondition = postCondition;
           this.priority = priority;
           this.executionTime = executionTime;
           this.testcaseType = testcaseType;
           this.modifiedReason = modifiedReason;
           this.testingType = testingType;
           this.udfInfo = udfInfo;
    }


    /**
     * Gets the token value for this CreateTestCase.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this CreateTestCase.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the parentFolderId value for this CreateTestCase.
     * 
     * @return parentFolderId
     */
    public int getParentFolderId() {
        return parentFolderId;
    }


    /**
     * Sets the parentFolderId value for this CreateTestCase.
     * 
     * @param parentFolderId
     */
    public void setParentFolderId(int parentFolderId) {
        this.parentFolderId = parentFolderId;
    }


    /**
     * Gets the testcaseName value for this CreateTestCase.
     * 
     * @return testcaseName
     */
    public java.lang.String getTestcaseName() {
        return testcaseName;
    }


    /**
     * Sets the testcaseName value for this CreateTestCase.
     * 
     * @param testcaseName
     */
    public void setTestcaseName(java.lang.String testcaseName) {
        this.testcaseName = testcaseName;
    }


    /**
     * Gets the testcaseObjective value for this CreateTestCase.
     * 
     * @return testcaseObjective
     */
    public java.lang.String getTestcaseObjective() {
        return testcaseObjective;
    }


    /**
     * Sets the testcaseObjective value for this CreateTestCase.
     * 
     * @param testcaseObjective
     */
    public void setTestcaseObjective(java.lang.String testcaseObjective) {
        this.testcaseObjective = testcaseObjective;
    }


    /**
     * Gets the component value for this CreateTestCase.
     * 
     * @return component
     */
    public int getComponent() {
        return component;
    }


    /**
     * Sets the component value for this CreateTestCase.
     * 
     * @param component
     */
    public void setComponent(int component) {
        this.component = component;
    }


    /**
     * Gets the testcaseStatus value for this CreateTestCase.
     * 
     * @return testcaseStatus
     */
    public int getTestcaseStatus() {
        return testcaseStatus;
    }


    /**
     * Sets the testcaseStatus value for this CreateTestCase.
     * 
     * @param testcaseStatus
     */
    public void setTestcaseStatus(int testcaseStatus) {
        this.testcaseStatus = testcaseStatus;
    }


    /**
     * Gets the assignTo value for this CreateTestCase.
     * 
     * @return assignTo
     */
    public int getAssignTo() {
        return assignTo;
    }


    /**
     * Sets the assignTo value for this CreateTestCase.
     * 
     * @param assignTo
     */
    public void setAssignTo(int assignTo) {
        this.assignTo = assignTo;
    }


    /**
     * Gets the preCondition value for this CreateTestCase.
     * 
     * @return preCondition
     */
    public java.lang.String getPreCondition() {
        return preCondition;
    }


    /**
     * Sets the preCondition value for this CreateTestCase.
     * 
     * @param preCondition
     */
    public void setPreCondition(java.lang.String preCondition) {
        this.preCondition = preCondition;
    }


    /**
     * Gets the postCondition value for this CreateTestCase.
     * 
     * @return postCondition
     */
    public java.lang.String getPostCondition() {
        return postCondition;
    }


    /**
     * Sets the postCondition value for this CreateTestCase.
     * 
     * @param postCondition
     */
    public void setPostCondition(java.lang.String postCondition) {
        this.postCondition = postCondition;
    }


    /**
     * Gets the priority value for this CreateTestCase.
     * 
     * @return priority
     */
    public int getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this CreateTestCase.
     * 
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }


    /**
     * Gets the executionTime value for this CreateTestCase.
     * 
     * @return executionTime
     */
    public java.lang.String getExecutionTime() {
        return executionTime;
    }


    /**
     * Sets the executionTime value for this CreateTestCase.
     * 
     * @param executionTime
     */
    public void setExecutionTime(java.lang.String executionTime) {
        this.executionTime = executionTime;
    }


    /**
     * Gets the testcaseType value for this CreateTestCase.
     * 
     * @return testcaseType
     */
    public int getTestcaseType() {
        return testcaseType;
    }


    /**
     * Sets the testcaseType value for this CreateTestCase.
     * 
     * @param testcaseType
     */
    public void setTestcaseType(int testcaseType) {
        this.testcaseType = testcaseType;
    }


    /**
     * Gets the modifiedReason value for this CreateTestCase.
     * 
     * @return modifiedReason
     */
    public int getModifiedReason() {
        return modifiedReason;
    }


    /**
     * Sets the modifiedReason value for this CreateTestCase.
     * 
     * @param modifiedReason
     */
    public void setModifiedReason(int modifiedReason) {
        this.modifiedReason = modifiedReason;
    }


    /**
     * Gets the testingType value for this CreateTestCase.
     * 
     * @return testingType
     */
    public int getTestingType() {
        return testingType;
    }


    /**
     * Sets the testingType value for this CreateTestCase.
     * 
     * @param testingType
     */
    public void setTestingType(int testingType) {
        this.testingType = testingType;
    }


    /**
     * Gets the udfInfo value for this CreateTestCase.
     * 
     * @return udfInfo
     */
    public org.wso2.www.php.xsd.KeyValuePair[] getUdfInfo() {
        return udfInfo;
    }


    /**
     * Sets the udfInfo value for this CreateTestCase.
     * 
     * @param udfInfo
     */
    public void setUdfInfo(org.wso2.www.php.xsd.KeyValuePair[] udfInfo) {
        this.udfInfo = udfInfo;
    }

    public org.wso2.www.php.xsd.KeyValuePair getUdfInfo(int i) {
        return this.udfInfo[i];
    }

    public void setUdfInfo(int i, org.wso2.www.php.xsd.KeyValuePair _value) {
        this.udfInfo[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateTestCase)) return false;
        CreateTestCase other = (CreateTestCase) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken()))) &&
            this.parentFolderId == other.getParentFolderId() &&
            ((this.testcaseName==null && other.getTestcaseName()==null) || 
             (this.testcaseName!=null &&
              this.testcaseName.equals(other.getTestcaseName()))) &&
            ((this.testcaseObjective==null && other.getTestcaseObjective()==null) || 
             (this.testcaseObjective!=null &&
              this.testcaseObjective.equals(other.getTestcaseObjective()))) &&
            this.component == other.getComponent() &&
            this.testcaseStatus == other.getTestcaseStatus() &&
            this.assignTo == other.getAssignTo() &&
            ((this.preCondition==null && other.getPreCondition()==null) || 
             (this.preCondition!=null &&
              this.preCondition.equals(other.getPreCondition()))) &&
            ((this.postCondition==null && other.getPostCondition()==null) || 
             (this.postCondition!=null &&
              this.postCondition.equals(other.getPostCondition()))) &&
            this.priority == other.getPriority() &&
            ((this.executionTime==null && other.getExecutionTime()==null) || 
             (this.executionTime!=null &&
              this.executionTime.equals(other.getExecutionTime()))) &&
            this.testcaseType == other.getTestcaseType() &&
            this.modifiedReason == other.getModifiedReason() &&
            this.testingType == other.getTestingType() &&
            ((this.udfInfo==null && other.getUdfInfo()==null) || 
             (this.udfInfo!=null &&
              java.util.Arrays.equals(this.udfInfo, other.getUdfInfo())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        _hashCode += getParentFolderId();
        if (getTestcaseName() != null) {
            _hashCode += getTestcaseName().hashCode();
        }
        if (getTestcaseObjective() != null) {
            _hashCode += getTestcaseObjective().hashCode();
        }
        _hashCode += getComponent();
        _hashCode += getTestcaseStatus();
        _hashCode += getAssignTo();
        if (getPreCondition() != null) {
            _hashCode += getPreCondition().hashCode();
        }
        if (getPostCondition() != null) {
            _hashCode += getPostCondition().hashCode();
        }
        _hashCode += getPriority();
        if (getExecutionTime() != null) {
            _hashCode += getExecutionTime().hashCode();
        }
        _hashCode += getTestcaseType();
        _hashCode += getModifiedReason();
        _hashCode += getTestingType();
        if (getUdfInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUdfInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUdfInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateTestCase.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestCase"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentFolderId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "parentFolderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcaseName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcaseName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcaseObjective");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcaseObjective"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("component");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "component"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcaseStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcaseStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assignTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "assignTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preCondition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "preCondition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postCondition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "postCondition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executionTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executionTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcaseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcaseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "modifiedReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testingType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testingType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "KeyValuePair"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
