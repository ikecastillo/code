/**
 * TestCaseStepRunDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestCaseStepRunDetails  implements java.io.Serializable {
    private int stepId;

    private java.lang.String actualResult;

    private java.lang.String status;

    public TestCaseStepRunDetails() {
    }

    public TestCaseStepRunDetails(
           int stepId,
           java.lang.String actualResult,
           java.lang.String status) {
           this.stepId = stepId;
           this.actualResult = actualResult;
           this.status = status;
    }


    /**
     * Gets the stepId value for this TestCaseStepRunDetails.
     * 
     * @return stepId
     */
    public int getStepId() {
        return stepId;
    }


    /**
     * Sets the stepId value for this TestCaseStepRunDetails.
     * 
     * @param stepId
     */
    public void setStepId(int stepId) {
        this.stepId = stepId;
    }


    /**
     * Gets the actualResult value for this TestCaseStepRunDetails.
     * 
     * @return actualResult
     */
    public java.lang.String getActualResult() {
        return actualResult;
    }


    /**
     * Sets the actualResult value for this TestCaseStepRunDetails.
     * 
     * @param actualResult
     */
    public void setActualResult(java.lang.String actualResult) {
        this.actualResult = actualResult;
    }


    /**
     * Gets the status value for this TestCaseStepRunDetails.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this TestCaseStepRunDetails.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestCaseStepRunDetails)) return false;
        TestCaseStepRunDetails other = (TestCaseStepRunDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.stepId == other.getStepId() &&
            ((this.actualResult==null && other.getActualResult()==null) || 
             (this.actualResult!=null &&
              this.actualResult.equals(other.getActualResult()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus())));
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
        if (getActualResult() != null) {
            _hashCode += getActualResult().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TestCaseStepRunDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepRunDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "actualResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
