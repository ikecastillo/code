/**
 * TestCaseExecution.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestCaseExecution  implements java.io.Serializable {
    private int testCaseId;

    private int testCaseExecutionId;

    private java.lang.String testCaseName;

    private java.lang.String testCaseObjective;

    private org.wso2.www.php.xsd.UserDefinedFieldArray[] testCaseUdfs;

    private java.lang.String executionStartTime;

    private java.lang.String executionEndTime;

    private java.lang.String executionStatus;

    private java.lang.String executionComments;

    private org.wso2.www.php.xsd.TestLogs testLogs;

    private org.wso2.www.php.xsd.TestCaseStepExecutions[] testCaseStepExecutions;

    public TestCaseExecution() {
    }

    public TestCaseExecution(
           int testCaseId,
           int testCaseExecutionId,
           java.lang.String testCaseName,
           java.lang.String testCaseObjective,
           org.wso2.www.php.xsd.UserDefinedFieldArray[] testCaseUdfs,
           java.lang.String executionStartTime,
           java.lang.String executionEndTime,
           java.lang.String executionStatus,
           java.lang.String executionComments,
           org.wso2.www.php.xsd.TestLogs testLogs,
           org.wso2.www.php.xsd.TestCaseStepExecutions[] testCaseStepExecutions) {
           this.testCaseId = testCaseId;
           this.testCaseExecutionId = testCaseExecutionId;
           this.testCaseName = testCaseName;
           this.testCaseObjective = testCaseObjective;
           this.testCaseUdfs = testCaseUdfs;
           this.executionStartTime = executionStartTime;
           this.executionEndTime = executionEndTime;
           this.executionStatus = executionStatus;
           this.executionComments = executionComments;
           this.testLogs = testLogs;
           this.testCaseStepExecutions = testCaseStepExecutions;
    }


    /**
     * Gets the testCaseId value for this TestCaseExecution.
     * 
     * @return testCaseId
     */
    public int getTestCaseId() {
        return testCaseId;
    }


    /**
     * Sets the testCaseId value for this TestCaseExecution.
     * 
     * @param testCaseId
     */
    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }


    /**
     * Gets the testCaseExecutionId value for this TestCaseExecution.
     * 
     * @return testCaseExecutionId
     */
    public int getTestCaseExecutionId() {
        return testCaseExecutionId;
    }


    /**
     * Sets the testCaseExecutionId value for this TestCaseExecution.
     * 
     * @param testCaseExecutionId
     */
    public void setTestCaseExecutionId(int testCaseExecutionId) {
        this.testCaseExecutionId = testCaseExecutionId;
    }


    /**
     * Gets the testCaseName value for this TestCaseExecution.
     * 
     * @return testCaseName
     */
    public java.lang.String getTestCaseName() {
        return testCaseName;
    }


    /**
     * Sets the testCaseName value for this TestCaseExecution.
     * 
     * @param testCaseName
     */
    public void setTestCaseName(java.lang.String testCaseName) {
        this.testCaseName = testCaseName;
    }


    /**
     * Gets the testCaseObjective value for this TestCaseExecution.
     * 
     * @return testCaseObjective
     */
    public java.lang.String getTestCaseObjective() {
        return testCaseObjective;
    }


    /**
     * Sets the testCaseObjective value for this TestCaseExecution.
     * 
     * @param testCaseObjective
     */
    public void setTestCaseObjective(java.lang.String testCaseObjective) {
        this.testCaseObjective = testCaseObjective;
    }


    /**
     * Gets the testCaseUdfs value for this TestCaseExecution.
     * 
     * @return testCaseUdfs
     */
    public org.wso2.www.php.xsd.UserDefinedFieldArray[] getTestCaseUdfs() {
        return testCaseUdfs;
    }


    /**
     * Sets the testCaseUdfs value for this TestCaseExecution.
     * 
     * @param testCaseUdfs
     */
    public void setTestCaseUdfs(org.wso2.www.php.xsd.UserDefinedFieldArray[] testCaseUdfs) {
        this.testCaseUdfs = testCaseUdfs;
    }

    public org.wso2.www.php.xsd.UserDefinedFieldArray getTestCaseUdfs(int i) {
        return this.testCaseUdfs[i];
    }

    public void setTestCaseUdfs(int i, org.wso2.www.php.xsd.UserDefinedFieldArray _value) {
        this.testCaseUdfs[i] = _value;
    }


    /**
     * Gets the executionStartTime value for this TestCaseExecution.
     * 
     * @return executionStartTime
     */
    public java.lang.String getExecutionStartTime() {
        return executionStartTime;
    }


    /**
     * Sets the executionStartTime value for this TestCaseExecution.
     * 
     * @param executionStartTime
     */
    public void setExecutionStartTime(java.lang.String executionStartTime) {
        this.executionStartTime = executionStartTime;
    }


    /**
     * Gets the executionEndTime value for this TestCaseExecution.
     * 
     * @return executionEndTime
     */
    public java.lang.String getExecutionEndTime() {
        return executionEndTime;
    }


    /**
     * Sets the executionEndTime value for this TestCaseExecution.
     * 
     * @param executionEndTime
     */
    public void setExecutionEndTime(java.lang.String executionEndTime) {
        this.executionEndTime = executionEndTime;
    }


    /**
     * Gets the executionStatus value for this TestCaseExecution.
     * 
     * @return executionStatus
     */
    public java.lang.String getExecutionStatus() {
        return executionStatus;
    }


    /**
     * Sets the executionStatus value for this TestCaseExecution.
     * 
     * @param executionStatus
     */
    public void setExecutionStatus(java.lang.String executionStatus) {
        this.executionStatus = executionStatus;
    }


    /**
     * Gets the executionComments value for this TestCaseExecution.
     * 
     * @return executionComments
     */
    public java.lang.String getExecutionComments() {
        return executionComments;
    }


    /**
     * Sets the executionComments value for this TestCaseExecution.
     * 
     * @param executionComments
     */
    public void setExecutionComments(java.lang.String executionComments) {
        this.executionComments = executionComments;
    }


    /**
     * Gets the testLogs value for this TestCaseExecution.
     * 
     * @return testLogs
     */
    public org.wso2.www.php.xsd.TestLogs getTestLogs() {
        return testLogs;
    }


    /**
     * Sets the testLogs value for this TestCaseExecution.
     * 
     * @param testLogs
     */
    public void setTestLogs(org.wso2.www.php.xsd.TestLogs testLogs) {
        this.testLogs = testLogs;
    }


    /**
     * Gets the testCaseStepExecutions value for this TestCaseExecution.
     * 
     * @return testCaseStepExecutions
     */
    public org.wso2.www.php.xsd.TestCaseStepExecutions[] getTestCaseStepExecutions() {
        return testCaseStepExecutions;
    }


    /**
     * Sets the testCaseStepExecutions value for this TestCaseExecution.
     * 
     * @param testCaseStepExecutions
     */
    public void setTestCaseStepExecutions(org.wso2.www.php.xsd.TestCaseStepExecutions[] testCaseStepExecutions) {
        this.testCaseStepExecutions = testCaseStepExecutions;
    }

    public org.wso2.www.php.xsd.TestCaseStepExecutions getTestCaseStepExecutions(int i) {
        return this.testCaseStepExecutions[i];
    }

    public void setTestCaseStepExecutions(int i, org.wso2.www.php.xsd.TestCaseStepExecutions _value) {
        this.testCaseStepExecutions[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestCaseExecution)) return false;
        TestCaseExecution other = (TestCaseExecution) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.testCaseId == other.getTestCaseId() &&
            this.testCaseExecutionId == other.getTestCaseExecutionId() &&
            ((this.testCaseName==null && other.getTestCaseName()==null) || 
             (this.testCaseName!=null &&
              this.testCaseName.equals(other.getTestCaseName()))) &&
            ((this.testCaseObjective==null && other.getTestCaseObjective()==null) || 
             (this.testCaseObjective!=null &&
              this.testCaseObjective.equals(other.getTestCaseObjective()))) &&
            ((this.testCaseUdfs==null && other.getTestCaseUdfs()==null) || 
             (this.testCaseUdfs!=null &&
              java.util.Arrays.equals(this.testCaseUdfs, other.getTestCaseUdfs()))) &&
            ((this.executionStartTime==null && other.getExecutionStartTime()==null) || 
             (this.executionStartTime!=null &&
              this.executionStartTime.equals(other.getExecutionStartTime()))) &&
            ((this.executionEndTime==null && other.getExecutionEndTime()==null) || 
             (this.executionEndTime!=null &&
              this.executionEndTime.equals(other.getExecutionEndTime()))) &&
            ((this.executionStatus==null && other.getExecutionStatus()==null) || 
             (this.executionStatus!=null &&
              this.executionStatus.equals(other.getExecutionStatus()))) &&
            ((this.executionComments==null && other.getExecutionComments()==null) || 
             (this.executionComments!=null &&
              this.executionComments.equals(other.getExecutionComments()))) &&
            ((this.testLogs==null && other.getTestLogs()==null) || 
             (this.testLogs!=null &&
              this.testLogs.equals(other.getTestLogs()))) &&
            ((this.testCaseStepExecutions==null && other.getTestCaseStepExecutions()==null) || 
             (this.testCaseStepExecutions!=null &&
              java.util.Arrays.equals(this.testCaseStepExecutions, other.getTestCaseStepExecutions())));
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
        _hashCode += getTestCaseExecutionId();
        if (getTestCaseName() != null) {
            _hashCode += getTestCaseName().hashCode();
        }
        if (getTestCaseObjective() != null) {
            _hashCode += getTestCaseObjective().hashCode();
        }
        if (getTestCaseUdfs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestCaseUdfs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestCaseUdfs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExecutionStartTime() != null) {
            _hashCode += getExecutionStartTime().hashCode();
        }
        if (getExecutionEndTime() != null) {
            _hashCode += getExecutionEndTime().hashCode();
        }
        if (getExecutionStatus() != null) {
            _hashCode += getExecutionStatus().hashCode();
        }
        if (getExecutionComments() != null) {
            _hashCode += getExecutionComments().hashCode();
        }
        if (getTestLogs() != null) {
            _hashCode += getTestLogs().hashCode();
        }
        if (getTestCaseStepExecutions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestCaseStepExecutions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestCaseStepExecutions(), i);
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
        new org.apache.axis.description.TypeDesc(TestCaseExecution.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseExecution"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseExecutionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseExecutionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseObjective");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseObjective"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseUdfs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseUdfs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefinedFieldArray"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
        elemField.setFieldName("executionStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executionStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executionComments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executionComments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testLogs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testLogs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestLogs"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseStepExecutions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseStepExecutions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepExecutions"));
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
