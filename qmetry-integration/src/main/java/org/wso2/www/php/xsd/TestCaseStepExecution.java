/**
 * TestCaseStepExecution.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestCaseStepExecution  implements java.io.Serializable {
    private int stepId;

    private int stepExecutionId;

    private java.lang.String stepName;

    private java.lang.String stepDesc;

    private java.lang.String stepInputData;

    private java.lang.String stepExpectedOutcome;

    private java.lang.String stepExecutionStatus;

    private org.wso2.www.php.xsd.UserDefinedFieldArray[] stepUdfs;

    private java.lang.String stepExecutionComments;

    private org.wso2.www.php.xsd.TestLogs stepTestLogs;

    public TestCaseStepExecution() {
    }

    public TestCaseStepExecution(
           int stepId,
           int stepExecutionId,
           java.lang.String stepName,
           java.lang.String stepDesc,
           java.lang.String stepInputData,
           java.lang.String stepExpectedOutcome,
           java.lang.String stepExecutionStatus,
           org.wso2.www.php.xsd.UserDefinedFieldArray[] stepUdfs,
           java.lang.String stepExecutionComments,
           org.wso2.www.php.xsd.TestLogs stepTestLogs) {
           this.stepId = stepId;
           this.stepExecutionId = stepExecutionId;
           this.stepName = stepName;
           this.stepDesc = stepDesc;
           this.stepInputData = stepInputData;
           this.stepExpectedOutcome = stepExpectedOutcome;
           this.stepExecutionStatus = stepExecutionStatus;
           this.stepUdfs = stepUdfs;
           this.stepExecutionComments = stepExecutionComments;
           this.stepTestLogs = stepTestLogs;
    }


    /**
     * Gets the stepId value for this TestCaseStepExecution.
     * 
     * @return stepId
     */
    public int getStepId() {
        return stepId;
    }


    /**
     * Sets the stepId value for this TestCaseStepExecution.
     * 
     * @param stepId
     */
    public void setStepId(int stepId) {
        this.stepId = stepId;
    }


    /**
     * Gets the stepExecutionId value for this TestCaseStepExecution.
     * 
     * @return stepExecutionId
     */
    public int getStepExecutionId() {
        return stepExecutionId;
    }


    /**
     * Sets the stepExecutionId value for this TestCaseStepExecution.
     * 
     * @param stepExecutionId
     */
    public void setStepExecutionId(int stepExecutionId) {
        this.stepExecutionId = stepExecutionId;
    }


    /**
     * Gets the stepName value for this TestCaseStepExecution.
     * 
     * @return stepName
     */
    public java.lang.String getStepName() {
        return stepName;
    }


    /**
     * Sets the stepName value for this TestCaseStepExecution.
     * 
     * @param stepName
     */
    public void setStepName(java.lang.String stepName) {
        this.stepName = stepName;
    }


    /**
     * Gets the stepDesc value for this TestCaseStepExecution.
     * 
     * @return stepDesc
     */
    public java.lang.String getStepDesc() {
        return stepDesc;
    }


    /**
     * Sets the stepDesc value for this TestCaseStepExecution.
     * 
     * @param stepDesc
     */
    public void setStepDesc(java.lang.String stepDesc) {
        this.stepDesc = stepDesc;
    }


    /**
     * Gets the stepInputData value for this TestCaseStepExecution.
     * 
     * @return stepInputData
     */
    public java.lang.String getStepInputData() {
        return stepInputData;
    }


    /**
     * Sets the stepInputData value for this TestCaseStepExecution.
     * 
     * @param stepInputData
     */
    public void setStepInputData(java.lang.String stepInputData) {
        this.stepInputData = stepInputData;
    }


    /**
     * Gets the stepExpectedOutcome value for this TestCaseStepExecution.
     * 
     * @return stepExpectedOutcome
     */
    public java.lang.String getStepExpectedOutcome() {
        return stepExpectedOutcome;
    }


    /**
     * Sets the stepExpectedOutcome value for this TestCaseStepExecution.
     * 
     * @param stepExpectedOutcome
     */
    public void setStepExpectedOutcome(java.lang.String stepExpectedOutcome) {
        this.stepExpectedOutcome = stepExpectedOutcome;
    }


    /**
     * Gets the stepExecutionStatus value for this TestCaseStepExecution.
     * 
     * @return stepExecutionStatus
     */
    public java.lang.String getStepExecutionStatus() {
        return stepExecutionStatus;
    }


    /**
     * Sets the stepExecutionStatus value for this TestCaseStepExecution.
     * 
     * @param stepExecutionStatus
     */
    public void setStepExecutionStatus(java.lang.String stepExecutionStatus) {
        this.stepExecutionStatus = stepExecutionStatus;
    }


    /**
     * Gets the stepUdfs value for this TestCaseStepExecution.
     * 
     * @return stepUdfs
     */
    public org.wso2.www.php.xsd.UserDefinedFieldArray[] getStepUdfs() {
        return stepUdfs;
    }


    /**
     * Sets the stepUdfs value for this TestCaseStepExecution.
     * 
     * @param stepUdfs
     */
    public void setStepUdfs(org.wso2.www.php.xsd.UserDefinedFieldArray[] stepUdfs) {
        this.stepUdfs = stepUdfs;
    }

    public org.wso2.www.php.xsd.UserDefinedFieldArray getStepUdfs(int i) {
        return this.stepUdfs[i];
    }

    public void setStepUdfs(int i, org.wso2.www.php.xsd.UserDefinedFieldArray _value) {
        this.stepUdfs[i] = _value;
    }


    /**
     * Gets the stepExecutionComments value for this TestCaseStepExecution.
     * 
     * @return stepExecutionComments
     */
    public java.lang.String getStepExecutionComments() {
        return stepExecutionComments;
    }


    /**
     * Sets the stepExecutionComments value for this TestCaseStepExecution.
     * 
     * @param stepExecutionComments
     */
    public void setStepExecutionComments(java.lang.String stepExecutionComments) {
        this.stepExecutionComments = stepExecutionComments;
    }


    /**
     * Gets the stepTestLogs value for this TestCaseStepExecution.
     * 
     * @return stepTestLogs
     */
    public org.wso2.www.php.xsd.TestLogs getStepTestLogs() {
        return stepTestLogs;
    }


    /**
     * Sets the stepTestLogs value for this TestCaseStepExecution.
     * 
     * @param stepTestLogs
     */
    public void setStepTestLogs(org.wso2.www.php.xsd.TestLogs stepTestLogs) {
        this.stepTestLogs = stepTestLogs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestCaseStepExecution)) return false;
        TestCaseStepExecution other = (TestCaseStepExecution) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.stepId == other.getStepId() &&
            this.stepExecutionId == other.getStepExecutionId() &&
            ((this.stepName==null && other.getStepName()==null) || 
             (this.stepName!=null &&
              this.stepName.equals(other.getStepName()))) &&
            ((this.stepDesc==null && other.getStepDesc()==null) || 
             (this.stepDesc!=null &&
              this.stepDesc.equals(other.getStepDesc()))) &&
            ((this.stepInputData==null && other.getStepInputData()==null) || 
             (this.stepInputData!=null &&
              this.stepInputData.equals(other.getStepInputData()))) &&
            ((this.stepExpectedOutcome==null && other.getStepExpectedOutcome()==null) || 
             (this.stepExpectedOutcome!=null &&
              this.stepExpectedOutcome.equals(other.getStepExpectedOutcome()))) &&
            ((this.stepExecutionStatus==null && other.getStepExecutionStatus()==null) || 
             (this.stepExecutionStatus!=null &&
              this.stepExecutionStatus.equals(other.getStepExecutionStatus()))) &&
            ((this.stepUdfs==null && other.getStepUdfs()==null) || 
             (this.stepUdfs!=null &&
              java.util.Arrays.equals(this.stepUdfs, other.getStepUdfs()))) &&
            ((this.stepExecutionComments==null && other.getStepExecutionComments()==null) || 
             (this.stepExecutionComments!=null &&
              this.stepExecutionComments.equals(other.getStepExecutionComments()))) &&
            ((this.stepTestLogs==null && other.getStepTestLogs()==null) || 
             (this.stepTestLogs!=null &&
              this.stepTestLogs.equals(other.getStepTestLogs())));
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
        _hashCode += getStepId();
        _hashCode += getStepExecutionId();
        if (getStepName() != null) {
            _hashCode += getStepName().hashCode();
        }
        if (getStepDesc() != null) {
            _hashCode += getStepDesc().hashCode();
        }
        if (getStepInputData() != null) {
            _hashCode += getStepInputData().hashCode();
        }
        if (getStepExpectedOutcome() != null) {
            _hashCode += getStepExpectedOutcome().hashCode();
        }
        if (getStepExecutionStatus() != null) {
            _hashCode += getStepExecutionStatus().hashCode();
        }
        if (getStepUdfs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStepUdfs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStepUdfs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getStepExecutionComments() != null) {
            _hashCode += getStepExecutionComments().hashCode();
        }
        if (getStepTestLogs() != null) {
            _hashCode += getStepTestLogs().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TestCaseStepExecution.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepExecution"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepExecutionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepExecutionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepInputData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepInputData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepExpectedOutcome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepExpectedOutcome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepExecutionStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepExecutionStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepUdfs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepUdfs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefinedFieldArray"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepExecutionComments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepExecutionComments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepTestLogs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepTestLogs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestLogs"));
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
