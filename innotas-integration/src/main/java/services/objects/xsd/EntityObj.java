/**
 * EntityObj.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class EntityObj  implements java.io.Serializable {
    private java.lang.Long entityId;

    private java.lang.Long entityTypeId;

    private services.objects.xsd.ValuePair[] methodValues;

    public EntityObj() {
    }

    public EntityObj(
           java.lang.Long entityId,
           java.lang.Long entityTypeId,
           services.objects.xsd.ValuePair[] methodValues) {
           this.entityId = entityId;
           this.entityTypeId = entityTypeId;
           this.methodValues = methodValues;
    }


    /**
     * Gets the entityId value for this EntityObj.
     * 
     * @return entityId
     */
    public java.lang.Long getEntityId() {
        return entityId;
    }


    /**
     * Sets the entityId value for this EntityObj.
     * 
     * @param entityId
     */
    public void setEntityId(java.lang.Long entityId) {
        this.entityId = entityId;
    }


    /**
     * Gets the entityTypeId value for this EntityObj.
     * 
     * @return entityTypeId
     */
    public java.lang.Long getEntityTypeId() {
        return entityTypeId;
    }


    /**
     * Sets the entityTypeId value for this EntityObj.
     * 
     * @param entityTypeId
     */
    public void setEntityTypeId(java.lang.Long entityTypeId) {
        this.entityTypeId = entityTypeId;
    }


    /**
     * Gets the methodValues value for this EntityObj.
     * 
     * @return methodValues
     */
    public services.objects.xsd.ValuePair[] getMethodValues() {
        return methodValues;
    }


    /**
     * Sets the methodValues value for this EntityObj.
     * 
     * @param methodValues
     */
    public void setMethodValues(services.objects.xsd.ValuePair[] methodValues) {
        this.methodValues = methodValues;
    }

    public services.objects.xsd.ValuePair getMethodValues(int i) {
        return this.methodValues[i];
    }

    public void setMethodValues(int i, services.objects.xsd.ValuePair _value) {
        this.methodValues[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntityObj)) return false;
        EntityObj other = (EntityObj) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entityId==null && other.getEntityId()==null) || 
             (this.entityId!=null &&
              this.entityId.equals(other.getEntityId()))) &&
            ((this.entityTypeId==null && other.getEntityTypeId()==null) || 
             (this.entityTypeId!=null &&
              this.entityTypeId.equals(other.getEntityTypeId()))) &&
            ((this.methodValues==null && other.getMethodValues()==null) || 
             (this.methodValues!=null &&
              java.util.Arrays.equals(this.methodValues, other.getMethodValues())));
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
        if (getEntityId() != null) {
            _hashCode += getEntityId().hashCode();
        }
        if (getEntityTypeId() != null) {
            _hashCode += getEntityTypeId().hashCode();
        }
        if (getMethodValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMethodValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMethodValues(), i);
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
        new org.apache.axis.description.TypeDesc(EntityObj.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "EntityObj"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("methodValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "methodValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ValuePair"));
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
