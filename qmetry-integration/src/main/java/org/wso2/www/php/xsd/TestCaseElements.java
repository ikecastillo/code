/**
 * TestCaseElements.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestCaseElements  implements java.io.Serializable {
    private int testCaseId;

    private java.lang.String testCaseName;

    private java.lang.String testCaseDescription;

    private int component;

    private int status;

    private int assignedUser;

    private java.lang.String testCasePreCondition;

    private java.lang.String testCasePostCondition;

    private int priority;

    private int executionTime;

    private int testCaseType;

    private int modificationFor;

    private int testingType;

    private java.lang.String testScriptName;

    private org.wso2.www.php.xsd.KeyValuePair[] udfInfo;

    public TestCaseElements() {
    }

    public TestCaseElements(
           int testCaseId,
           java.lang.String testCaseName,
           java.lang.String testCaseDescription,
           int component,
           int status,
           int assignedUser,
           java.lang.String testCasePreCondition,
           java.lang.String testCasePostCondition,
           int priority,
           int executionTime,
           int testCaseType,
           int modificationFor,
           int testingType,
           java.lang.String testScriptName,
           org.wso2.www.php.xsd.KeyValuePair[] udfInfo) {
           this.testCaseId = testCaseId;
           this.testCaseName = testCaseName;
           this.testCaseDescription = testCaseDescription;
           this.component = component;
           this.status = status;
           this.assignedUser = assignedUser;
           this.testCasePreCondition = testCasePreCondition;
           this.testCasePostCondition = testCasePostCondition;
           this.priority = priority;
           this.executionTime = executionTime;
           this.testCaseType = testCaseType;
           this.modificationFor = modificationFor;
           this.testingType = testingType;
           this.testScriptName = testScriptName;
           this.udfInfo = udfInfo;
    }


    /**
     * Gets the testCaseId value for this TestCaseElements.
     * 
     * @return testCaseId
     */
    public int getTestCaseId() {
        return testCaseId;
    }


    /**
     * Sets the testCaseId value for this TestCaseElements.
     * 
     * @param testCaseId
     */
    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }


    /**
     * Gets the testCaseName value for this TestCaseElements.
     * 
     * @return testCaseName
     */
    public java.lang.String getTestCaseName() {
        return testCaseName;
    }


    /**
     * Sets the testCaseName value for this TestCaseElements.
     * 
     * @param testCaseName
     */
    public void setTestCaseName(java.lang.String testCaseName) {
        this.testCaseName = testCaseName;
    }


    /**
     * Gets the testCaseDescription value for this TestCaseElements.
     * 
     * @return testCaseDescription
     */
    public java.lang.String getTestCaseDescription() {
        return testCaseDescription;
    }


    /**
     * Sets the testCaseDescription value for this TestCaseElements.
     * 
     * @param testCaseDescription
     */
    public void setTestCaseDescription(java.lang.String testCaseDescription) {
        this.testCaseDescription = testCaseDescription;
    }


    /**
     * Gets the component value for this TestCaseElements.
     * 
     * @return component
     */
    public int getComponent() {
        return component;
    }


    /**
     * Sets the component value for this TestCaseElements.
     * 
     * @param component
     */
    public void setComponent(int component) {
        this.component = component;
    }


    /**
     * Gets the status value for this TestCaseElements.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this TestCaseElements.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the assignedUser value for this TestCaseElements.
     * 
     * @return assignedUser
     */
    public int getAssignedUser() {
        return assignedUser;
    }


    /**
     * Sets the assignedUser value for this TestCaseElements.
     * 
     * @param assignedUser
     */
    public void setAssignedUser(int assignedUser) {
        this.assignedUser = assignedUser;
    }


    /**
     * Gets the testCasePreCondition value for this TestCaseElements.
     * 
     * @return testCasePreCondition
     */
    public java.lang.String getTestCasePreCondition() {
        return testCasePreCondition;
    }


    /**
     * Sets the testCasePreCondition value for this TestCaseElements.
     * 
     * @param testCasePreCondition
     */
    public void setTestCasePreCondition(java.lang.String testCasePreCondition) {
        this.testCasePreCondition = testCasePreCondition;
    }


    /**
     * Gets the testCasePostCondition value for this TestCaseElements.
     * 
     * @return testCasePostCondition
     */
    public java.lang.String getTestCasePostCondition() {
        return testCasePostCondition;
    }


    /**
     * Sets the testCasePostCondition value for this TestCaseElements.
     * 
     * @param testCasePostCondition
     */
    public void setTestCasePostCondition(java.lang.String testCasePostCondition) {
        this.testCasePostCondition = testCasePostCondition;
    }


    /**
     * Gets the priority value for this TestCaseElements.
     * 
     * @return priority
     */
    public int getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this TestCaseElements.
     * 
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }


    /**
     * Gets the executionTime value for this TestCaseElements.
     * 
     * @return executionTime
     */
    public int getExecutionTime() {
        return executionTime;
    }


    /**
     * Sets the executionTime value for this TestCaseElements.
     * 
     * @param executionTime
     */
    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }


    /**
     * Gets the testCaseType value for this TestCaseElements.
     * 
     * @return testCaseType
     */
    public int getTestCaseType() {
        return testCaseType;
    }


    /**
     * Sets the testCaseType value for this TestCaseElements.
     * 
     * @param testCaseType
     */
    public void setTestCaseType(int testCaseType) {
        this.testCaseType = testCaseType;
    }


    /**
     * Gets the modificationFor value for this TestCaseElements.
     * 
     * @return modificationFor
     */
    public int getModificationFor() {
        return modificationFor;
    }


    /**
     * Sets the modificationFor value for this TestCaseElements.
     * 
     * @param modificationFor
     */
    public void setModificationFor(int modificationFor) {
        this.modificationFor = modificationFor;
    }


    /**
     * Gets the testingType value for this TestCaseElements.
     * 
     * @return testingType
     */
    public int getTestingType() {
        return testingType;
    }


    /**
     * Sets the testingType value for this TestCaseElements.
     * 
     * @param testingType
     */
    public void setTestingType(int testingType) {
        this.testingType = testingType;
    }


    /**
     * Gets the testScriptName value for this TestCaseElements.
     * 
     * @return testScriptName
     */
    public java.lang.String getTestScriptName() {
        return testScriptName;
    }


    /**
     * Sets the testScriptName value for this TestCaseElements.
     * 
     * @param testScriptName
     */
    public void setTestScriptName(java.lang.String testScriptName) {
        this.testScriptName = testScriptName;
    }


    /**
     * Gets the udfInfo value for this TestCaseElements.
     * 
     * @return udfInfo
     */
    public org.wso2.www.php.xsd.KeyValuePair[] getUdfInfo() {
        return udfInfo;
    }


    /**
     * Sets the udfInfo value for this TestCaseElements.
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
        if (!(obj instanceof TestCaseElements)) return false;
        TestCaseElements other = (TestCaseElements) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.testCaseId == other.getTestCaseId() &&
            ((this.testCaseName==null && other.getTestCaseName()==null) || 
             (this.testCaseName!=null &&
              this.testCaseName.equals(other.getTestCaseName()))) &&
            ((this.testCaseDescription==null && other.getTestCaseDescription()==null) || 
             (this.testCaseDescription!=null &&
              this.testCaseDescription.equals(other.getTestCaseDescription()))) &&
            this.component == other.getComponent() &&
            this.status == other.getStatus() &&
            this.assignedUser == other.getAssignedUser() &&
            ((this.testCasePreCondition==null && other.getTestCasePreCondition()==null) || 
             (this.testCasePreCondition!=null &&
              this.testCasePreCondition.equals(other.getTestCasePreCondition()))) &&
            ((this.testCasePostCondition==null && other.getTestCasePostCondition()==null) || 
             (this.testCasePostCondition!=null &&
              this.testCasePostCondition.equals(other.getTestCasePostCondition()))) &&
            this.priority == other.getPriority() &&
            this.executionTime == other.getExecutionTime() &&
            this.testCaseType == other.getTestCaseType() &&
            this.modificationFor == other.getModificationFor() &&
            this.testingType == other.getTestingType() &&
            ((this.testScriptName==null && other.getTestScriptName()==null) || 
             (this.testScriptName!=null &&
              this.testScriptName.equals(other.getTestScriptName()))) &&
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
        _hashCode += getTestCaseId();
        if (getTestCaseName() != null) {
            _hashCode += getTestCaseName().hashCode();
        }
        if (getTestCaseDescription() != null) {
            _hashCode += getTestCaseDescription().hashCode();
        }
        _hashCode += getComponent();
        _hashCode += getStatus();
        _hashCode += getAssignedUser();
        if (getTestCasePreCondition() != null) {
            _hashCode += getTestCasePreCondition().hashCode();
        }
        if (getTestCasePostCondition() != null) {
            _hashCode += getTestCasePostCondition().hashCode();
        }
        _hashCode += getPriority();
        _hashCode += getExecutionTime();
        _hashCode += getTestCaseType();
        _hashCode += getModificationFor();
        _hashCode += getTestingType();
        if (getTestScriptName() != null) {
            _hashCode += getTestScriptName().hashCode();
        }
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
        new org.apache.axis.description.TypeDesc(TestCaseElements.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseElements"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("component");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "Component"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assignedUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "AssignedUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCasePreCondition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCasePreCondition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCasePostCondition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCasePostCondition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "Priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executionTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "ExecutionTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modificationFor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "ModificationFor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testingType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestingType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testScriptName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestScriptName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
