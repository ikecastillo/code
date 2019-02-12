/**
 * ListTestCasesFromFolderId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListTestCasesFromFolderId  implements java.io.Serializable {
    private java.lang.String token;

    private int folderId;

    private int currentscope;

    public ListTestCasesFromFolderId() {
    }

    public ListTestCasesFromFolderId(
           java.lang.String token,
           int folderId,
           int currentscope) {
           this.token = token;
           this.folderId = folderId;
           this.currentscope = currentscope;
    }


    /**
     * Gets the token value for this ListTestCasesFromFolderId.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this ListTestCasesFromFolderId.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the folderId value for this ListTestCasesFromFolderId.
     * 
     * @return folderId
     */
    public int getFolderId() {
        return folderId;
    }


    /**
     * Sets the folderId value for this ListTestCasesFromFolderId.
     * 
     * @param folderId
     */
    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }


    /**
     * Gets the currentscope value for this ListTestCasesFromFolderId.
     * 
     * @return currentscope
     */
    public int getCurrentscope() {
        return currentscope;
    }


    /**
     * Sets the currentscope value for this ListTestCasesFromFolderId.
     * 
     * @param currentscope
     */
    public void setCurrentscope(int currentscope) {
        this.currentscope = currentscope;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListTestCasesFromFolderId)) return false;
        ListTestCasesFromFolderId other = (ListTestCasesFromFolderId) obj;
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
            this.folderId == other.getFolderId() &&
            this.currentscope == other.getCurrentscope();
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
        _hashCode += getFolderId();
        _hashCode += getCurrentscope();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListTestCasesFromFolderId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestCasesFromFolderId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "folderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentscope");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "currentscope"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
