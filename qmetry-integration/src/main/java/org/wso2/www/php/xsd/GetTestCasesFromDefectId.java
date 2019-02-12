/**
 * GetTestCasesFromDefectId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class GetTestCasesFromDefectId  implements java.io.Serializable {
    private java.lang.String token;

    private java.lang.String defectId;

    private boolean all;

    public GetTestCasesFromDefectId() {
    }

    public GetTestCasesFromDefectId(
           java.lang.String token,
           java.lang.String defectId,
           boolean all) {
           this.token = token;
           this.defectId = defectId;
           this.all = all;
    }


    /**
     * Gets the token value for this GetTestCasesFromDefectId.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this GetTestCasesFromDefectId.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the defectId value for this GetTestCasesFromDefectId.
     * 
     * @return defectId
     */
    public java.lang.String getDefectId() {
        return defectId;
    }


    /**
     * Sets the defectId value for this GetTestCasesFromDefectId.
     * 
     * @param defectId
     */
    public void setDefectId(java.lang.String defectId) {
        this.defectId = defectId;
    }


    /**
     * Gets the all value for this GetTestCasesFromDefectId.
     * 
     * @return all
     */
    public boolean isAll() {
        return all;
    }


    /**
     * Sets the all value for this GetTestCasesFromDefectId.
     * 
     * @param all
     */
    public void setAll(boolean all) {
        this.all = all;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTestCasesFromDefectId)) return false;
        GetTestCasesFromDefectId other = (GetTestCasesFromDefectId) obj;
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
            ((this.defectId==null && other.getDefectId()==null) || 
             (this.defectId!=null &&
              this.defectId.equals(other.getDefectId()))) &&
            this.all == other.isAll();
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
        if (getDefectId() != null) {
            _hashCode += getDefectId().hashCode();
        }
        _hashCode += (isAll() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetTestCasesFromDefectId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCasesFromDefectId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defectId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "defectId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("all");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "all"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
