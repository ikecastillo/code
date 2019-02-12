/**
 * ExecuteTestCaseUsingRunId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ExecuteTestCaseUsingRunId  implements java.io.Serializable {
    private java.lang.String token;

    private int testCaseRunId;

    private java.lang.String status;

    private java.lang.String comments;

    public ExecuteTestCaseUsingRunId() {
    }

    public ExecuteTestCaseUsingRunId(
           java.lang.String token,
           int testCaseRunId,
           java.lang.String status,
           java.lang.String comments) {
           this.token = token;
           this.testCaseRunId = testCaseRunId;
           this.status = status;
           this.comments = comments;
    }


    /**
     * Gets the token value for this ExecuteTestCaseUsingRunId.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this ExecuteTestCaseUsingRunId.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the testCaseRunId value for this ExecuteTestCaseUsingRunId.
     * 
     * @return testCaseRunId
     */
    public int getTestCaseRunId() {
        return testCaseRunId;
    }


    /**
     * Sets the testCaseRunId value for this ExecuteTestCaseUsingRunId.
     * 
     * @param testCaseRunId
     */
    public void setTestCaseRunId(int testCaseRunId) {
        this.testCaseRunId = testCaseRunId;
    }


    /**
     * Gets the status value for this ExecuteTestCaseUsingRunId.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ExecuteTestCaseUsingRunId.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the comments value for this ExecuteTestCaseUsingRunId.
     * 
     * @return comments
     */
    public java.lang.String getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this ExecuteTestCaseUsingRunId.
     * 
     * @param comments
     */
    public void setComments(java.lang.String comments) {
        this.comments = comments;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExecuteTestCaseUsingRunId)) return false;
        ExecuteTestCaseUsingRunId other = (ExecuteTestCaseUsingRunId) obj;
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
            this.testCaseRunId == other.getTestCaseRunId() &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              this.comments.equals(other.getComments())));
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
        _hashCode += getTestCaseRunId();
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getComments() != null) {
            _hashCode += getComments().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExecuteTestCaseUsingRunId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseUsingRunId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseRunId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseRunId"));
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
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "comments"));
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
