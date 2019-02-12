/**
 * ListAttachments.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListAttachments  implements java.io.Serializable {
    private java.lang.String token;

    private int entitryId;

    private java.lang.String entityType;

    public ListAttachments() {
    }

    public ListAttachments(
           java.lang.String token,
           int entitryId,
           java.lang.String entityType) {
           this.token = token;
           this.entitryId = entitryId;
           this.entityType = entityType;
    }


    /**
     * Gets the token value for this ListAttachments.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this ListAttachments.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the entitryId value for this ListAttachments.
     * 
     * @return entitryId
     */
    public int getEntitryId() {
        return entitryId;
    }


    /**
     * Sets the entitryId value for this ListAttachments.
     * 
     * @param entitryId
     */
    public void setEntitryId(int entitryId) {
        this.entitryId = entitryId;
    }


    /**
     * Gets the entityType value for this ListAttachments.
     * 
     * @return entityType
     */
    public java.lang.String getEntityType() {
        return entityType;
    }


    /**
     * Sets the entityType value for this ListAttachments.
     * 
     * @param entityType
     */
    public void setEntityType(java.lang.String entityType) {
        this.entityType = entityType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListAttachments)) return false;
        ListAttachments other = (ListAttachments) obj;
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
            this.entitryId == other.getEntitryId() &&
            ((this.entityType==null && other.getEntityType()==null) || 
             (this.entityType!=null &&
              this.entityType.equals(other.getEntityType())));
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
        _hashCode += getEntitryId();
        if (getEntityType() != null) {
            _hashCode += getEntityType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListAttachments.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listAttachments"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entitryId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "EntitryId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "EntityType"));
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
