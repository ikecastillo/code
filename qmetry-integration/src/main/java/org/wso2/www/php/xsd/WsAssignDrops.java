/**
 * WsAssignDrops.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class WsAssignDrops  implements java.io.Serializable {
    private java.lang.String token;

    private int dropId;

    private java.lang.String tcrIds;

    public WsAssignDrops() {
    }

    public WsAssignDrops(
           java.lang.String token,
           int dropId,
           java.lang.String tcrIds) {
           this.token = token;
           this.dropId = dropId;
           this.tcrIds = tcrIds;
    }


    /**
     * Gets the token value for this WsAssignDrops.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this WsAssignDrops.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the dropId value for this WsAssignDrops.
     * 
     * @return dropId
     */
    public int getDropId() {
        return dropId;
    }


    /**
     * Sets the dropId value for this WsAssignDrops.
     * 
     * @param dropId
     */
    public void setDropId(int dropId) {
        this.dropId = dropId;
    }


    /**
     * Gets the tcrIds value for this WsAssignDrops.
     * 
     * @return tcrIds
     */
    public java.lang.String getTcrIds() {
        return tcrIds;
    }


    /**
     * Sets the tcrIds value for this WsAssignDrops.
     * 
     * @param tcrIds
     */
    public void setTcrIds(java.lang.String tcrIds) {
        this.tcrIds = tcrIds;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsAssignDrops)) return false;
        WsAssignDrops other = (WsAssignDrops) obj;
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
            this.dropId == other.getDropId() &&
            ((this.tcrIds==null && other.getTcrIds()==null) || 
             (this.tcrIds!=null &&
              this.tcrIds.equals(other.getTcrIds())));
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
        _hashCode += getDropId();
        if (getTcrIds() != null) {
            _hashCode += getTcrIds().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsAssignDrops.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsAssignDrops"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dropId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "dropId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tcrIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "tcrIds"));
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
