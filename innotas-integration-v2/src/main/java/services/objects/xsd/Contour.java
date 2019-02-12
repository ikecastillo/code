/**
 * Contour.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class Contour  implements java.io.Serializable {
    private java.lang.String allocationEndDate;

    private java.lang.String allocationStartDate;

    private java.lang.Long entityId;

    private java.lang.Long entityTypeId;

    private services.objects.xsd.ContourEntry[] entries;

    public Contour() {
    }

    public Contour(
           java.lang.String allocationEndDate,
           java.lang.String allocationStartDate,
           java.lang.Long entityId,
           java.lang.Long entityTypeId,
           services.objects.xsd.ContourEntry[] entries) {
           this.allocationEndDate = allocationEndDate;
           this.allocationStartDate = allocationStartDate;
           this.entityId = entityId;
           this.entityTypeId = entityTypeId;
           this.entries = entries;
    }


    /**
     * Gets the allocationEndDate value for this Contour.
     * 
     * @return allocationEndDate
     */
    public java.lang.String getAllocationEndDate() {
        return allocationEndDate;
    }


    /**
     * Sets the allocationEndDate value for this Contour.
     * 
     * @param allocationEndDate
     */
    public void setAllocationEndDate(java.lang.String allocationEndDate) {
        this.allocationEndDate = allocationEndDate;
    }


    /**
     * Gets the allocationStartDate value for this Contour.
     * 
     * @return allocationStartDate
     */
    public java.lang.String getAllocationStartDate() {
        return allocationStartDate;
    }


    /**
     * Sets the allocationStartDate value for this Contour.
     * 
     * @param allocationStartDate
     */
    public void setAllocationStartDate(java.lang.String allocationStartDate) {
        this.allocationStartDate = allocationStartDate;
    }


    /**
     * Gets the entityId value for this Contour.
     * 
     * @return entityId
     */
    public java.lang.Long getEntityId() {
        return entityId;
    }


    /**
     * Sets the entityId value for this Contour.
     * 
     * @param entityId
     */
    public void setEntityId(java.lang.Long entityId) {
        this.entityId = entityId;
    }


    /**
     * Gets the entityTypeId value for this Contour.
     * 
     * @return entityTypeId
     */
    public java.lang.Long getEntityTypeId() {
        return entityTypeId;
    }


    /**
     * Sets the entityTypeId value for this Contour.
     * 
     * @param entityTypeId
     */
    public void setEntityTypeId(java.lang.Long entityTypeId) {
        this.entityTypeId = entityTypeId;
    }


    /**
     * Gets the entries value for this Contour.
     * 
     * @return entries
     */
    public services.objects.xsd.ContourEntry[] getEntries() {
        return entries;
    }


    /**
     * Sets the entries value for this Contour.
     * 
     * @param entries
     */
    public void setEntries(services.objects.xsd.ContourEntry[] entries) {
        this.entries = entries;
    }

    public services.objects.xsd.ContourEntry getEntries(int i) {
        return this.entries[i];
    }

    public void setEntries(int i, services.objects.xsd.ContourEntry _value) {
        this.entries[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Contour)) return false;
        Contour other = (Contour) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.allocationEndDate==null && other.getAllocationEndDate()==null) || 
             (this.allocationEndDate!=null &&
              this.allocationEndDate.equals(other.getAllocationEndDate()))) &&
            ((this.allocationStartDate==null && other.getAllocationStartDate()==null) || 
             (this.allocationStartDate!=null &&
              this.allocationStartDate.equals(other.getAllocationStartDate()))) &&
            ((this.entityId==null && other.getEntityId()==null) || 
             (this.entityId!=null &&
              this.entityId.equals(other.getEntityId()))) &&
            ((this.entityTypeId==null && other.getEntityTypeId()==null) || 
             (this.entityTypeId!=null &&
              this.entityTypeId.equals(other.getEntityTypeId()))) &&
            ((this.entries==null && other.getEntries()==null) || 
             (this.entries!=null &&
              java.util.Arrays.equals(this.entries, other.getEntries())));
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
        if (getAllocationEndDate() != null) {
            _hashCode += getAllocationEndDate().hashCode();
        }
        if (getAllocationStartDate() != null) {
            _hashCode += getAllocationStartDate().hashCode();
        }
        if (getEntityId() != null) {
            _hashCode += getEntityId().hashCode();
        }
        if (getEntityTypeId() != null) {
            _hashCode += getEntityTypeId().hashCode();
        }
        if (getEntries() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEntries());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEntries(), i);
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
        new org.apache.axis.description.TypeDesc(Contour.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "Contour"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allocationEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "allocationEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allocationStartDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "allocationStartDate"));
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
        elemField.setFieldName("entries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "entries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ContourEntry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
