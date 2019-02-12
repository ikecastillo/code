/**
 * TestSuitePlatformExecution.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestSuitePlatformExecution  implements java.io.Serializable {
    private int platformId;

    private int platformExecutionId;

    private java.lang.String platformName;

    private java.lang.String executionStatus;

    private java.lang.String executionStartTime;

    private java.lang.String executionEndTime;

    private org.wso2.www.php.xsd.PlatformAttributes platformAttributes;

    private org.wso2.www.php.xsd.TestCaseExecutions testCaseExecutions;

    public TestSuitePlatformExecution() {
    }

    public TestSuitePlatformExecution(
           int platformId,
           int platformExecutionId,
           java.lang.String platformName,
           java.lang.String executionStatus,
           java.lang.String executionStartTime,
           java.lang.String executionEndTime,
           org.wso2.www.php.xsd.PlatformAttributes platformAttributes,
           org.wso2.www.php.xsd.TestCaseExecutions testCaseExecutions) {
           this.platformId = platformId;
           this.platformExecutionId = platformExecutionId;
           this.platformName = platformName;
           this.executionStatus = executionStatus;
           this.executionStartTime = executionStartTime;
           this.executionEndTime = executionEndTime;
           this.platformAttributes = platformAttributes;
           this.testCaseExecutions = testCaseExecutions;
    }


    /**
     * Gets the platformId value for this TestSuitePlatformExecution.
     * 
     * @return platformId
     */
    public int getPlatformId() {
        return platformId;
    }


    /**
     * Sets the platformId value for this TestSuitePlatformExecution.
     * 
     * @param platformId
     */
    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }


    /**
     * Gets the platformExecutionId value for this TestSuitePlatformExecution.
     * 
     * @return platformExecutionId
     */
    public int getPlatformExecutionId() {
        return platformExecutionId;
    }


    /**
     * Sets the platformExecutionId value for this TestSuitePlatformExecution.
     * 
     * @param platformExecutionId
     */
    public void setPlatformExecutionId(int platformExecutionId) {
        this.platformExecutionId = platformExecutionId;
    }


    /**
     * Gets the platformName value for this TestSuitePlatformExecution.
     * 
     * @return platformName
     */
    public java.lang.String getPlatformName() {
        return platformName;
    }


    /**
     * Sets the platformName value for this TestSuitePlatformExecution.
     * 
     * @param platformName
     */
    public void setPlatformName(java.lang.String platformName) {
        this.platformName = platformName;
    }


    /**
     * Gets the executionStatus value for this TestSuitePlatformExecution.
     * 
     * @return executionStatus
     */
    public java.lang.String getExecutionStatus() {
        return executionStatus;
    }


    /**
     * Sets the executionStatus value for this TestSuitePlatformExecution.
     * 
     * @param executionStatus
     */
    public void setExecutionStatus(java.lang.String executionStatus) {
        this.executionStatus = executionStatus;
    }


    /**
     * Gets the executionStartTime value for this TestSuitePlatformExecution.
     * 
     * @return executionStartTime
     */
    public java.lang.String getExecutionStartTime() {
        return executionStartTime;
    }


    /**
     * Sets the executionStartTime value for this TestSuitePlatformExecution.
     * 
     * @param executionStartTime
     */
    public void setExecutionStartTime(java.lang.String executionStartTime) {
        this.executionStartTime = executionStartTime;
    }


    /**
     * Gets the executionEndTime value for this TestSuitePlatformExecution.
     * 
     * @return executionEndTime
     */
    public java.lang.String getExecutionEndTime() {
        return executionEndTime;
    }


    /**
     * Sets the executionEndTime value for this TestSuitePlatformExecution.
     * 
     * @param executionEndTime
     */
    public void setExecutionEndTime(java.lang.String executionEndTime) {
        this.executionEndTime = executionEndTime;
    }


    /**
     * Gets the platformAttributes value for this TestSuitePlatformExecution.
     * 
     * @return platformAttributes
     */
    public org.wso2.www.php.xsd.PlatformAttributes getPlatformAttributes() {
        return platformAttributes;
    }


    /**
     * Sets the platformAttributes value for this TestSuitePlatformExecution.
     * 
     * @param platformAttributes
     */
    public void setPlatformAttributes(org.wso2.www.php.xsd.PlatformAttributes platformAttributes) {
        this.platformAttributes = platformAttributes;
    }


    /**
     * Gets the testCaseExecutions value for this TestSuitePlatformExecution.
     * 
     * @return testCaseExecutions
     */
    public org.wso2.www.php.xsd.TestCaseExecutions getTestCaseExecutions() {
        return testCaseExecutions;
    }


    /**
     * Sets the testCaseExecutions value for this TestSuitePlatformExecution.
     * 
     * @param testCaseExecutions
     */
    public void setTestCaseExecutions(org.wso2.www.php.xsd.TestCaseExecutions testCaseExecutions) {
        this.testCaseExecutions = testCaseExecutions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestSuitePlatformExecution)) return false;
        TestSuitePlatformExecution other = (TestSuitePlatformExecution) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.platformId == other.getPlatformId() &&
            this.platformExecutionId == other.getPlatformExecutionId() &&
            ((this.platformName==null && other.getPlatformName()==null) || 
             (this.platformName!=null &&
              this.platformName.equals(other.getPlatformName()))) &&
            ((this.executionStatus==null && other.getExecutionStatus()==null) || 
             (this.executionStatus!=null &&
              this.executionStatus.equals(other.getExecutionStatus()))) &&
            ((this.executionStartTime==null && other.getExecutionStartTime()==null) || 
             (this.executionStartTime!=null &&
              this.executionStartTime.equals(other.getExecutionStartTime()))) &&
            ((this.executionEndTime==null && other.getExecutionEndTime()==null) || 
             (this.executionEndTime!=null &&
              this.executionEndTime.equals(other.getExecutionEndTime()))) &&
            ((this.platformAttributes==null && other.getPlatformAttributes()==null) || 
             (this.platformAttributes!=null &&
              this.platformAttributes.equals(other.getPlatformAttributes()))) &&
            ((this.testCaseExecutions==null && other.getTestCaseExecutions()==null) || 
             (this.testCaseExecutions!=null &&
              this.testCaseExecutions.equals(other.getTestCaseExecutions())));
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
        _hashCode += getPlatformId();
        _hashCode += getPlatformExecutionId();
        if (getPlatformName() != null) {
            _hashCode += getPlatformName().hashCode();
        }
        if (getExecutionStatus() != null) {
            _hashCode += getExecutionStatus().hashCode();
        }
        if (getExecutionStartTime() != null) {
            _hashCode += getExecutionStartTime().hashCode();
        }
        if (getExecutionEndTime() != null) {
            _hashCode += getExecutionEndTime().hashCode();
        }
        if (getPlatformAttributes() != null) {
            _hashCode += getPlatformAttributes().hashCode();
        }
        if (getTestCaseExecutions() != null) {
            _hashCode += getTestCaseExecutions().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TestSuitePlatformExecution.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuitePlatformExecution"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("platformId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "platformId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("platformExecutionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "platformExecutionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("platformName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "platformName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executionStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executionStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executionStartTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executionStartTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executionEndTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executionEndTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("platformAttributes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "platformAttributes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "PlatformAttributes"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseExecutions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseExecutions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseExecutions"));
        elemField.setNillable(false);
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
