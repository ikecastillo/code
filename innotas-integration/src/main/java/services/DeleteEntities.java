/**
 * DeleteEntities.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class DeleteEntities  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long entityTypeId;

    private long[] entityIds;

    public DeleteEntities() {
    }

    public DeleteEntities(
           java.lang.String sessionId,
           java.lang.Long entityTypeId,
           long[] entityIds) {
           this.sessionId = sessionId;
           this.entityTypeId = entityTypeId;
           this.entityIds = entityIds;
    }


    /**
     * Gets the sessionId value for this DeleteEntities.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this DeleteEntities.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the entityTypeId value for this DeleteEntities.
     * 
     * @return entityTypeId
     */
    public java.lang.Long getEntityTypeId() {
        return entityTypeId;
    }


    /**
     * Sets the entityTypeId value for this DeleteEntities.
     * 
     * @param entityTypeId
     */
    public void setEntityTypeId(java.lang.Long entityTypeId) {
        this.entityTypeId = entityTypeId;
    }


    /**
     * Gets the entityIds value for this DeleteEntities.
     * 
     * @return entityIds
     */
    public long[] getEntityIds() {
        return entityIds;
    }


    /**
     * Sets the entityIds value for this DeleteEntities.
     * 
     * @param entityIds
     */
    public void setEntityIds(long[] entityIds) {
        this.entityIds = entityIds;
    }

    public long getEntityIds(int i) {
        return this.entityIds[i];
    }

    public void setEntityIds(int i, long _value) {
        this.entityIds[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeleteEntities)) return false;
        DeleteEntities other = (DeleteEntities) obj;
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
            ((this.entityIds==null && other.getEntityIds()==null) || 
             (this.entityIds!=null &&
              java.util.Arrays.equals(this.entityIds, other.getEntityIds())));
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
        if (getEntityIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEntityIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEntityIds(), i);
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
        new org.apache.axis.description.TypeDesc(DeleteEntities.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">deleteEntities"));
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
        elemField.setFieldName("entityIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "entityIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
