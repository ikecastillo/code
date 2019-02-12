/**
 * GetListIdFromValue.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class GetListIdFromValue  implements java.io.Serializable {
    private java.lang.String token;

    private java.lang.String listName;

    private java.lang.String listValue;

    public GetListIdFromValue() {
    }

    public GetListIdFromValue(
           java.lang.String token,
           java.lang.String listName,
           java.lang.String listValue) {
           this.token = token;
           this.listName = listName;
           this.listValue = listValue;
    }


    /**
     * Gets the token value for this GetListIdFromValue.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this GetListIdFromValue.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the listName value for this GetListIdFromValue.
     * 
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }


    /**
     * Sets the listName value for this GetListIdFromValue.
     * 
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }


    /**
     * Gets the listValue value for this GetListIdFromValue.
     * 
     * @return listValue
     */
    public java.lang.String getListValue() {
        return listValue;
    }


    /**
     * Sets the listValue value for this GetListIdFromValue.
     * 
     * @param listValue
     */
    public void setListValue(java.lang.String listValue) {
        this.listValue = listValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListIdFromValue)) return false;
        GetListIdFromValue other = (GetListIdFromValue) obj;
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
            ((this.listName==null && other.getListName()==null) || 
             (this.listName!=null &&
              this.listName.equals(other.getListName()))) &&
            ((this.listValue==null && other.getListValue()==null) || 
             (this.listValue!=null &&
              this.listValue.equals(other.getListValue())));
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
        if (getListName() != null) {
            _hashCode += getListName().hashCode();
        }
        if (getListValue() != null) {
            _hashCode += getListValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListIdFromValue.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getListIdFromValue"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "listValue"));
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
