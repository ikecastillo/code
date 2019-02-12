/**
 * AllocateContour.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class AllocateContour  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long entityTypeId;

    private java.lang.Long entityId;

    private services.objects.xsd.ContourEntry[] contourEntries;

    public AllocateContour() {
    }

    public AllocateContour(
           java.lang.String sessionId,
           java.lang.Long entityTypeId,
           java.lang.Long entityId,
           services.objects.xsd.ContourEntry[] contourEntries) {
           this.sessionId = sessionId;
           this.entityTypeId = entityTypeId;
           this.entityId = entityId;
           this.contourEntries = contourEntries;
    }


    /**
     * Gets the sessionId value for this AllocateContour.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this AllocateContour.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the entityTypeId value for this AllocateContour.
     * 
     * @return entityTypeId
     */
    public java.lang.Long getEntityTypeId() {
        return entityTypeId;
    }


    /**
     * Sets the entityTypeId value for this AllocateContour.
     * 
     * @param entityTypeId
     */
    public void setEntityTypeId(java.lang.Long entityTypeId) {
        this.entityTypeId = entityTypeId;
    }


    /**
     * Gets the entityId value for this AllocateContour.
     * 
     * @return entityId
     */
    public java.lang.Long getEntityId() {
        return entityId;
    }


    /**
     * Sets the entityId value for this AllocateContour.
     * 
     * @param entityId
     */
    public void setEntityId(java.lang.Long entityId) {
        this.entityId = entityId;
    }


    /**
     * Gets the contourEntries value for this AllocateContour.
     * 
     * @return contourEntries
     */
    public services.objects.xsd.ContourEntry[] getContourEntries() {
        return contourEntries;
    }


    /**
     * Sets the contourEntries value for this AllocateContour.
     * 
     * @param contourEntries
     */
    public void setContourEntries(services.objects.xsd.ContourEntry[] contourEntries) {
        this.contourEntries = contourEntries;
    }

    public services.objects.xsd.ContourEntry getContourEntries(int i) {
        return this.contourEntries[i];
    }

    public void setContourEntries(int i, services.objects.xsd.ContourEntry _value) {
        this.contourEntries[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AllocateContour)) return false;
        AllocateContour other = (AllocateContour) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sessionId==null && other.getSessionId()==null) || 
             (this.sessionId!=null &&
              this.sessionId.equals(other.getSessionId()))) &&
            ((this.entityTypeId==null && other.getEntityTypeId()==null) || 
             (this.entityTypeId!=null &&
              this.entityTypeId.equals(other.getEntityTypeId()))) &&
            ((this.entityId==null && other.getEntityId()==null) || 
             (this.entityId!=null &&
              this.entityId.equals(other.getEntityId()))) &&
            ((this.contourEntries==null && other.getContourEntries()==null) || 
             (this.contourEntries!=null &&
              java.util.Arrays.equals(this.contourEntries, other.getContourEntries())));
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
        if (getSessionId() != null) {
            _hashCode += getSessionId().hashCode();
        }
        if (getEntityTypeId() != null) {
            _hashCode += getEntityTypeId().hashCode();
        }
        if (getEntityId() != null) {
            _hashCode += getEntityId().hashCode();
        }
        if (getContourEntries() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContourEntries());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContourEntries(), i);
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
        new org.apache.axis.description.TypeDesc(AllocateContour.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">allocateContour"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "entityTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "entityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contourEntries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "contourEntries"));
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
