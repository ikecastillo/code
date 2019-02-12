/**
 * AttachmentEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class AttachmentEntity  implements java.io.Serializable {
    private int attachmentId;

    private java.lang.String attachmentFileName;

    private java.lang.String attachmentType;

    private java.lang.String attachmentdesc;

    private int attachmentSize;

    private java.lang.String attachmentData;

    public AttachmentEntity() {
    }

    public AttachmentEntity(
           int attachmentId,
           java.lang.String attachmentFileName,
           java.lang.String attachmentType,
           java.lang.String attachmentdesc,
           int attachmentSize,
           java.lang.String attachmentData) {
           this.attachmentId = attachmentId;
           this.attachmentFileName = attachmentFileName;
           this.attachmentType = attachmentType;
           this.attachmentdesc = attachmentdesc;
           this.attachmentSize = attachmentSize;
           this.attachmentData = attachmentData;
    }


    /**
     * Gets the attachmentId value for this AttachmentEntity.
     * 
     * @return attachmentId
     */
    public int getAttachmentId() {
        return attachmentId;
    }


    /**
     * Sets the attachmentId value for this AttachmentEntity.
     * 
     * @param attachmentId
     */
    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }


    /**
     * Gets the attachmentFileName value for this AttachmentEntity.
     * 
     * @return attachmentFileName
     */
    public java.lang.String getAttachmentFileName() {
        return attachmentFileName;
    }


    /**
     * Sets the attachmentFileName value for this AttachmentEntity.
     * 
     * @param attachmentFileName
     */
    public void setAttachmentFileName(java.lang.String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }


    /**
     * Gets the attachmentType value for this AttachmentEntity.
     * 
     * @return attachmentType
     */
    public java.lang.String getAttachmentType() {
        return attachmentType;
    }


    /**
     * Sets the attachmentType value for this AttachmentEntity.
     * 
     * @param attachmentType
     */
    public void setAttachmentType(java.lang.String attachmentType) {
        this.attachmentType = attachmentType;
    }


    /**
     * Gets the attachmentdesc value for this AttachmentEntity.
     * 
     * @return attachmentdesc
     */
    public java.lang.String getAttachmentdesc() {
        return attachmentdesc;
    }


    /**
     * Sets the attachmentdesc value for this AttachmentEntity.
     * 
     * @param attachmentdesc
     */
    public void setAttachmentdesc(java.lang.String attachmentdesc) {
        this.attachmentdesc = attachmentdesc;
    }


    /**
     * Gets the attachmentSize value for this AttachmentEntity.
     * 
     * @return attachmentSize
     */
    public int getAttachmentSize() {
        return attachmentSize;
    }


    /**
     * Sets the attachmentSize value for this AttachmentEntity.
     * 
     * @param attachmentSize
     */
    public void setAttachmentSize(int attachmentSize) {
        this.attachmentSize = attachmentSize;
    }


    /**
     * Gets the attachmentData value for this AttachmentEntity.
     * 
     * @return attachmentData
     */
    public java.lang.String getAttachmentData() {
        return attachmentData;
    }


    /**
     * Sets the attachmentData value for this AttachmentEntity.
     * 
     * @param attachmentData
     */
    public void setAttachmentData(java.lang.String attachmentData) {
        this.attachmentData = attachmentData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AttachmentEntity)) return false;
        AttachmentEntity other = (AttachmentEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.attachmentId == other.getAttachmentId() &&
            ((this.attachmentFileName==null && other.getAttachmentFileName()==null) || 
             (this.attachmentFileName!=null &&
              this.attachmentFileName.equals(other.getAttachmentFileName()))) &&
            ((this.attachmentType==null && other.getAttachmentType()==null) || 
             (this.attachmentType!=null &&
              this.attachmentType.equals(other.getAttachmentType()))) &&
            ((this.attachmentdesc==null && other.getAttachmentdesc()==null) || 
             (this.attachmentdesc!=null &&
              this.attachmentdesc.equals(other.getAttachmentdesc()))) &&
            this.attachmentSize == other.getAttachmentSize() &&
            ((this.attachmentData==null && other.getAttachmentData()==null) || 
             (this.attachmentData!=null &&
              this.attachmentData.equals(other.getAttachmentData())));
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
        _hashCode += getAttachmentId();
        if (getAttachmentFileName() != null) {
            _hashCode += getAttachmentFileName().hashCode();
        }
        if (getAttachmentType() != null) {
            _hashCode += getAttachmentType().hashCode();
        }
        if (getAttachmentdesc() != null) {
            _hashCode += getAttachmentdesc().hashCode();
        }
        _hashCode += getAttachmentSize();
        if (getAttachmentData() != null) {
            _hashCode += getAttachmentData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AttachmentEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "AttachmentEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "attachmentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "attachmentFileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "attachmentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentdesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "attachmentdesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "attachmentSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "attachmentData"));
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
