/**
 * ListTestCases.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListTestCases  implements java.io.Serializable {
    private java.lang.String token;

    private org.wso2.www.php.xsd.KeyValuePair[] searchText;

    public ListTestCases() {
    }

    public ListTestCases(
           java.lang.String token,
           org.wso2.www.php.xsd.KeyValuePair[] searchText) {
           this.token = token;
           this.searchText = searchText;
    }


    /**
     * Gets the token value for this ListTestCases.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this ListTestCases.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the searchText value for this ListTestCases.
     * 
     * @return searchText
     */
    public org.wso2.www.php.xsd.KeyValuePair[] getSearchText() {
        return searchText;
    }


    /**
     * Sets the searchText value for this ListTestCases.
     * 
     * @param searchText
     */
    public void setSearchText(org.wso2.www.php.xsd.KeyValuePair[] searchText) {
        this.searchText = searchText;
    }

    public org.wso2.www.php.xsd.KeyValuePair getSearchText(int i) {
        return this.searchText[i];
    }

    public void setSearchText(int i, org.wso2.www.php.xsd.KeyValuePair _value) {
        this.searchText[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListTestCases)) return false;
        ListTestCases other = (ListTestCases) obj;
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
            ((this.searchText==null && other.getSearchText()==null) || 
             (this.searchText!=null &&
              java.util.Arrays.equals(this.searchText, other.getSearchText())));
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
        if (getSearchText() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSearchText());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSearchText(), i);
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
        new org.apache.axis.description.TypeDesc(ListTestCases.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestCases"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "searchText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "KeyValuePair"));
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
