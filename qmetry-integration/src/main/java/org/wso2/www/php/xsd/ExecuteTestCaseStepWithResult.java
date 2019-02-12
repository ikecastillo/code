/**
 * ExecuteTestCaseStepWithResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ExecuteTestCaseStepWithResult  implements java.io.Serializable {
    private java.lang.String token;

    private int testsuiteId;

    private int platformId;

    private int testcaseId;

    private int testcaseStepId;

    private java.lang.String status;

    private java.lang.String actualResult;

    public ExecuteTestCaseStepWithResult() {
    }

    public ExecuteTestCaseStepWithResult(
           java.lang.String token,
           int testsuiteId,
           int platformId,
           int testcaseId,
           int testcaseStepId,
           java.lang.String status,
           java.lang.String actualResult) {
           this.token = token;
           this.testsuiteId = testsuiteId;
           this.platformId = platformId;
           this.testcaseId = testcaseId;
           this.testcaseStepId = testcaseStepId;
           this.status = status;
           this.actualResult = actualResult;
    }


    /**
     * Gets the token value for this ExecuteTestCaseStepWithResult.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this ExecuteTestCaseStepWithResult.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the testsuiteId value for this ExecuteTestCaseStepWithResult.
     * 
     * @return testsuiteId
     */
    public int getTestsuiteId() {
        return testsuiteId;
    }


    /**
     * Sets the testsuiteId value for this ExecuteTestCaseStepWithResult.
     * 
     * @param testsuiteId
     */
    public void setTestsuiteId(int testsuiteId) {
        this.testsuiteId = testsuiteId;
    }


    /**
     * Gets the platformId value for this ExecuteTestCaseStepWithResult.
     * 
     * @return platformId
     */
    public int getPlatformId() {
        return platformId;
    }


    /**
     * Sets the platformId value for this ExecuteTestCaseStepWithResult.
     * 
     * @param platformId
     */
    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }


    /**
     * Gets the testcaseId value for this ExecuteTestCaseStepWithResult.
     * 
     * @return testcaseId
     */
    public int getTestcaseId() {
        return testcaseId;
    }


    /**
     * Sets the testcaseId value for this ExecuteTestCaseStepWithResult.
     * 
     * @param testcaseId
     */
    public void setTestcaseId(int testcaseId) {
        this.testcaseId = testcaseId;
    }


    /**
     * Gets the testcaseStepId value for this ExecuteTestCaseStepWithResult.
     * 
     * @return testcaseStepId
     */
    public int getTestcaseStepId() {
        return testcaseStepId;
    }


    /**
     * Sets the testcaseStepId value for this ExecuteTestCaseStepWithResult.
     * 
     * @param testcaseStepId
     */
    public void setTestcaseStepId(int testcaseStepId) {
        this.testcaseStepId = testcaseStepId;
    }


    /**
     * Gets the status value for this ExecuteTestCaseStepWithResult.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ExecuteTestCaseStepWithResult.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the actualResult value for this ExecuteTestCaseStepWithResult.
     * 
     * @return actualResult
     */
    public java.lang.String getActualResult() {
        return actualResult;
    }


    /**
     * Sets the actualResult value for this ExecuteTestCaseStepWithResult.
     * 
     * @param actualResult
     */
    public void setActualResult(java.lang.String actualResult) {
        this.actualResult = actualResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExecuteTestCaseStepWithResult)) return false;
        ExecuteTestCaseStepWithResult other = (ExecuteTestCaseStepWithResult) obj;
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
            this.testsuiteId == other.getTestsuiteId() &&
            this.platformId == other.getPlatformId() &&
            this.testcaseId == other.getTestcaseId() &&
            this.testcaseStepId == other.getTestcaseStepId() &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.actualResult==null && other.getActualResult()==null) || 
             (this.actualResult!=null &&
              this.actualResult.equals(other.getActualResult())));
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
        _hashCode += getTestsuiteId();
        _hashCode += getPlatformId();
        _hashCode += getTestcaseId();
        _hashCode += getTestcaseStepId();
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getActualResult() != null) {
            _hashCode += getActualResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExecuteTestCaseStepWithResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseStepWithResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testsuiteId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testsuiteId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("platformId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "platformId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcaseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcaseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcaseStepId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcaseStepId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "actualResult"));
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
