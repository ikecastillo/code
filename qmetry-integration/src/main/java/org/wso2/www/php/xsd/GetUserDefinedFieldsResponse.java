/**
 * GetUserDefinedFieldsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class GetUserDefinedFieldsResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.UserDefinedFieldDefination[] udfs;

    public GetUserDefinedFieldsResponse() {
    }

    public GetUserDefinedFieldsResponse(
           org.wso2.www.php.xsd.UserDefinedFieldDefination[] udfs) {
           this.udfs = udfs;
    }


    /**
     * Gets the udfs value for this GetUserDefinedFieldsResponse.
     * 
     * @return udfs
     */
    public org.wso2.www.php.xsd.UserDefinedFieldDefination[] getUdfs() {
        return udfs;
    }


    /**
     * Sets the udfs value for this GetUserDefinedFieldsResponse.
     * 
     * @param udfs
     */
    public void setUdfs(org.wso2.www.php.xsd.UserDefinedFieldDefination[] udfs) {
        this.udfs = udfs;
    }

    public org.wso2.www.php.xsd.UserDefinedFieldDefination getUdfs(int i) {
        return this.udfs[i];
    }

    public void setUdfs(int i, org.wso2.www.php.xsd.UserDefinedFieldDefination _value) {
        this.udfs[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetUserDefinedFieldsResponse)) return false;
        GetUserDefinedFieldsResponse other = (GetUserDefinedFieldsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.udfs==null && other.getUdfs()==null) || 
             (this.udfs!=null &&
              java.util.Arrays.equals(this.udfs, other.getUdfs())));
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
        if (getUdfs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUdfs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUdfs(), i);
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
        new org.apache.axis.description.TypeDesc(GetUserDefinedFieldsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getUserDefinedFieldsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefinedFieldDefination"));
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
