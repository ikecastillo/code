/**
 * AddAttachment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class AddAttachment  implements java.io.Serializable {
    private java.lang.String token;

    private java.lang.String entityType;

    private int entityId;

    private java.lang.String attachmentType;

    private java.lang.String fileName;

    private java.lang.String description;

    private java.lang.String content;

    public AddAttachment() {
    }

    public AddAttachment(
           java.lang.String token,
           java.lang.String entityType,
           int entityId,
           java.lang.String attachmentType,
           java.lang.String fileName,
           java.lang.String description,
           java.lang.String content) {
           this.token = token;
           this.entityType = entityType;
           this.entityId = entityId;
           this.attachmentType = attachmentType;
           this.fileName = fileName;
           this.description = description;
           this.content = content;
    }


    /**
     * Gets the token value for this AddAttachment.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this AddAttachment.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the entityType value for this AddAttachment.
     * 
     * @return entityType
     */
    public java.lang.String getEntityType() {
        return entityType;
    }


    /**
     * Sets the entityType value for this AddAttachment.
     * 
     * @param entityType
     */
    public void setEntityType(java.lang.String entityType) {
        this.entityType = entityType;
    }


    /**
     * Gets the entityId value for this AddAttachment.
     * 
     * @return entityId
     */
    public int getEntityId() {
        return entityId;
    }


    /**
     * Sets the entityId value for this AddAttachment.
     * 
     * @param entityId
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }


    /**
     * Gets the attachmentType value for this AddAttachment.
     * 
     * @return attachmentType
     */
    public java.lang.String getAttachmentType() {
        return attachmentType;
    }


    /**
     * Sets the attachmentType value for this AddAttachment.
     * 
     * @param attachmentType
     */
    public void setAttachmentType(java.lang.String attachmentType) {
        this.attachmentType = attachmentType;
    }


    /**
     * Gets the fileName value for this AddAttachment.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this AddAttachment.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the description value for this AddAttachment.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this AddAttachment.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the content value for this AddAttachment.
     * 
     * @return content
     */
    public java.lang.String getContent() {
        return content;
    }


    /**
     * Sets the content value for this AddAttachment.
     * 
     * @param content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddAttachment)) return false;
        AddAttachment other = (AddAttachment) obj;
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
            ((this.entityType==null && other.getEntityType()==null) || 
             (this.entityType!=null &&
              this.entityType.equals(other.getEntityType()))) &&
            this.entityId == other.getEntityId() &&
            ((this.attachmentType==null && other.getAttachmentType()==null) || 
             (this.attachmentType!=null &&
              this.attachmentType.equals(other.getAttachmentType()))) &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent())));
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
        if (getEntityType() != null) {
            _hashCode += getEntityType().hashCode();
        }
        _hashCode += getEntityId();
        if (getAttachmentType() != null) {
            _hashCode += getAttachmentType().hashCode();
        }
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddAttachment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addAttachment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "entityType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "entityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "attachmentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "fileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "content"));
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
