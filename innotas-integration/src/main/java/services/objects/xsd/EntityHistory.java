/**
 * EntityHistory.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class EntityHistory  implements java.io.Serializable {
    private java.lang.String dateModified;

    private java.lang.Long entityId;

    private java.lang.Long entityTypeId;

    private java.lang.Long modifiedBy;

    public EntityHistory() {
    }

    public EntityHistory(
           java.lang.String dateModified,
           java.lang.Long entityId,
           java.lang.Long entityTypeId,
           java.lang.Long modifiedBy) {
           this.dateModified = dateModified;
           this.entityId = entityId;
           this.entityTypeId = entityTypeId;
           this.modifiedBy = modifiedBy;
    }


    /**
     * Gets the dateModified value for this EntityHistory.
     * 
     * @return dateModified
     */
    public java.lang.String getDateModified() {
        return dateModified;
    }


    /**
     * Sets the dateModified value for this EntityHistory.
     * 
     * @param dateModified
     */
    public void setDateModified(java.lang.String dateModified) {
        this.dateModified = dateModified;
    }


    /**
     * Gets the entityId value for this EntityHistory.
     * 
     * @return entityId
     */
    public java.lang.Long getEntityId() {
        return entityId;
    }


    /**
     * Sets the entityId value for this EntityHistory.
     * 
     * @param entityId
     */
    public void setEntityId(java.lang.Long entityId) {
        this.entityId = entityId;
    }


    /**
     * Gets the entityTypeId value for this EntityHistory.
     * 
     * @return entityTypeId
     */
    public java.lang.Long getEntityTypeId() {
        return entityTypeId;
    }


    /**
     * Sets the entityTypeId value for this EntityHistory.
     * 
     * @param entityTypeId
     */
    public void setEntityTypeId(java.lang.Long entityTypeId) {
        this.entityTypeId = entityTypeId;
    }


    /**
     * Gets the modifiedBy value for this EntityHistory.
     * 
     * @return modifiedBy
     */
    public java.lang.Long getModifiedBy() {
        return modifiedBy;
    }


    /**
     * Sets the modifiedBy value for this EntityHistory.
     * 
     * @param modifiedBy
     */
    public void setModifiedBy(java.lang.Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntityHistory)) return false;
        EntityHistory other = (EntityHistory) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dateModified==null && other.getDateModified()==null) || 
             (this.dateModified!=null &&
              this.dateModified.equals(other.getDateModified()))) &&
            ((this.entityId==null && other.getEntityId()==null) || 
             (this.entityId!=null &&
              this.entityId.equals(other.getEntityId()))) &&
            ((this.entityTypeId==null && other.getEntityTypeId()==null) || 
             (this.entityTypeId!=null &&
              this.entityTypeId.equals(other.getEntityTypeId()))) &&
            ((this.modifiedBy==null && other.getModifiedBy()==null) || 
             (this.modifiedBy!=null &&
              this.modifiedBy.equals(other.getModifiedBy())));
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
        if (getDateModified() != null) {
            _hashCode += getDateModified().hashCode();
        }
        if (getEntityId() != null) {
            _hashCode += getEntityId().hashCode();
        }
        if (getEntityTypeId() != null) {
            _hashCode += getEntityTypeId().hashCode();
        }
        if (getModifiedBy() != null) {
            _hashCode += getModifiedBy().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntityHistory.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "EntityHistory"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateModified");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "dateModified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "entityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "entityTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "modifiedBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
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
