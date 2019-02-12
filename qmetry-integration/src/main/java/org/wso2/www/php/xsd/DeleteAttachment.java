/**
 * DeleteAttachment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class DeleteAttachment  implements java.io.Serializable {
    private java.lang.String token;

    private int attachmentId;

    private int attachmentsParentId;

    private int attachmentParentTypeId;

    public DeleteAttachment() {
    }

    public DeleteAttachment(
           java.lang.String token,
           int attachmentId,
           int attachmentsParentId,
           int attachmentParentTypeId) {
           this.token = token;
           this.attachmentId = attachmentId;
           this.attachmentsParentId = attachmentsParentId;
           this.attachmentParentTypeId = attachmentParentTypeId;
    }


    /**
     * Gets the token value for this DeleteAttachment.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this DeleteAttachment.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the attachmentId value for this DeleteAttachment.
     * 
     * @return attachmentId
     */
    public int getAttachmentId() {
        return attachmentId;
    }


    /**
     * Sets the attachmentId value for this DeleteAttachment.
     * 
     * @param attachmentId
     */
    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }


    /**
     * Gets the attachmentsParentId value for this DeleteAttachment.
     * 
     * @return attachmentsParentId
     */
    public int getAttachmentsParentId() {
        return attachmentsParentId;
    }


    /**
     * Sets the attachmentsParentId value for this DeleteAttachment.
     * 
     * @param attachmentsParentId
     */
    public void setAttachmentsParentId(int attachmentsParentId) {
        this.attachmentsParentId = attachmentsParentId;
    }


    /**
     * Gets the attachmentParentTypeId value for this DeleteAttachment.
     * 
     * @return attachmentParentTypeId
     */
    public int getAttachmentParentTypeId() {
        return attachmentParentTypeId;
    }


    /**
     * Sets the attachmentParentTypeId value for this DeleteAttachment.
     * 
     * @param attachmentParentTypeId
     */
    public void setAttachmentParentTypeId(int attachmentParentTypeId) {
        this.attachmentParentTypeId = attachmentParentTypeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeleteAttachment)) return false;
        DeleteAttachment other = (DeleteAttachment) obj;
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
            this.attachmentId == other.getAttachmentId() &&
            this.attachmentsParentId == other.getAttachmentsParentId() &&
            this.attachmentParentTypeId == other.getAttachmentParentTypeId();
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
        _hashCode += getAttachmentId();
        _hashCode += getAttachmentsParentId();
        _hashCode += getAttachmentParentTypeId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeleteAttachment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">deleteAttachment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "AttachmentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentsParentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "AttachmentsParentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentParentTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "AttachmentParentTypeId"));
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
