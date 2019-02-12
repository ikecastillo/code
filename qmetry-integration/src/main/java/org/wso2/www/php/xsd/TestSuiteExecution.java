/**
 * TestSuiteExecution.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestSuiteExecution  implements java.io.Serializable {
    private int testSuiteId;

    private java.lang.String testSuiteName;

    private org.wso2.www.php.xsd.UserDefinedFieldArray testSuiteUdfs;

    private java.lang.String executionStatus;

    private org.wso2.www.php.xsd.TestSuitePlatformExecutions platformExecutions;

    public TestSuiteExecution() {
    }

    public TestSuiteExecution(
           int testSuiteId,
           java.lang.String testSuiteName,
           org.wso2.www.php.xsd.UserDefinedFieldArray testSuiteUdfs,
           java.lang.String executionStatus,
           org.wso2.www.php.xsd.TestSuitePlatformExecutions platformExecutions) {
           this.testSuiteId = testSuiteId;
           this.testSuiteName = testSuiteName;
           this.testSuiteUdfs = testSuiteUdfs;
           this.executionStatus = executionStatus;
           this.platformExecutions = platformExecutions;
    }


    /**
     * Gets the testSuiteId value for this TestSuiteExecution.
     * 
     * @return testSuiteId
     */
    public int getTestSuiteId() {
        return testSuiteId;
    }


    /**
     * Sets the testSuiteId value for this TestSuiteExecution.
     * 
     * @param testSuiteId
     */
    public void setTestSuiteId(int testSuiteId) {
        this.testSuiteId = testSuiteId;
    }


    /**
     * Gets the testSuiteName value for this TestSuiteExecution.
     * 
     * @return testSuiteName
     */
    public java.lang.String getTestSuiteName() {
        return testSuiteName;
    }


    /**
     * Sets the testSuiteName value for this TestSuiteExecution.
     * 
     * @param testSuiteName
     */
    public void setTestSuiteName(java.lang.String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }


    /**
     * Gets the testSuiteUdfs value for this TestSuiteExecution.
     * 
     * @return testSuiteUdfs
     */
    public org.wso2.www.php.xsd.UserDefinedFieldArray getTestSuiteUdfs() {
        return testSuiteUdfs;
    }


    /**
     * Sets the testSuiteUdfs value for this TestSuiteExecution.
     * 
     * @param testSuiteUdfs
     */
    public void setTestSuiteUdfs(org.wso2.www.php.xsd.UserDefinedFieldArray testSuiteUdfs) {
        this.testSuiteUdfs = testSuiteUdfs;
    }


    /**
     * Gets the executionStatus value for this TestSuiteExecution.
     * 
     * @return executionStatus
     */
    public java.lang.String getExecutionStatus() {
        return executionStatus;
    }


    /**
     * Sets the executionStatus value for this TestSuiteExecution.
     * 
     * @param executionStatus
     */
    public void setExecutionStatus(java.lang.String executionStatus) {
        this.executionStatus = executionStatus;
    }


    /**
     * Gets the platformExecutions value for this TestSuiteExecution.
     * 
     * @return platformExecutions
     */
    public org.wso2.www.php.xsd.TestSuitePlatformExecutions getPlatformExecutions() {
        return platformExecutions;
    }


    /**
     * Sets the platformExecutions value for this TestSuiteExecution.
     * 
     * @param platformExecutions
     */
    public void setPlatformExecutions(org.wso2.www.php.xsd.TestSuitePlatformExecutions platformExecutions) {
        this.platformExecutions = platformExecutions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestSuiteExecution)) return false;
        TestSuiteExecution other = (TestSuiteExecution) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.testSuiteId == other.getTestSuiteId() &&
            ((this.testSuiteName==null && other.getTestSuiteName()==null) || 
             (this.testSuiteName!=null &&
              this.testSuiteName.equals(other.getTestSuiteName()))) &&
            ((this.testSuiteUdfs==null && other.getTestSuiteUdfs()==null) || 
             (this.testSuiteUdfs!=null &&
              this.testSuiteUdfs.equals(other.getTestSuiteUdfs()))) &&
            ((this.executionStatus==null && other.getExecutionStatus()==null) || 
             (this.executionStatus!=null &&
              this.executionStatus.equals(other.getExecutionStatus()))) &&
            ((this.platformExecutions==null && other.getPlatformExecutions()==null) || 
             (this.platformExecutions!=null &&
              this.platformExecutions.equals(other.getPlatformExecutions())));
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
        _hashCode += getTestSuiteId();
        if (getTestSuiteName() != null) {
            _hashCode += getTestSuiteName().hashCode();
        }
        if (getTestSuiteUdfs() != null) {
            _hashCode += getTestSuiteUdfs().hashCode();
        }
        if (getExecutionStatus() != null) {
            _hashCode += getExecutionStatus().hashCode();
        }
        if (getPlatformExecutions() != null) {
            _hashCode += getPlatformExecutions().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TestSuiteExecution.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteExecution"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testSuiteId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testSuiteId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testSuiteName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testSuiteName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testSuiteUdfs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testSuiteUdfs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefinedFieldArray"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executionStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executionStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("platformExecutions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "platformExecutions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuitePlatformExecutions"));
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
