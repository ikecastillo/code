/**
 * UpdateAnEntityByMethodId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class UpdateAnEntityByMethodId  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long entityTypeId;

    private java.lang.Long entityId;

    private java.lang.Long methodId;

    private java.lang.String methodValue;

    public UpdateAnEntityByMethodId() {
    }

    public UpdateAnEntityByMethodId(
           java.lang.String sessionId,
           java.lang.Long entityTypeId,
           java.lang.Long entityId,
           java.lang.Long methodId,
           java.lang.String methodValue) {
           this.sessionId = sessionId;
           this.entityTypeId = entityTypeId;
           this.entityId = entityId;
           this.methodId = methodId;
           this.methodValue = methodValue;
    }


    /**
     * Gets the sessionId value for this UpdateAnEntityByMethodId.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this UpdateAnEntityByMethodId.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the entityTypeId value for this UpdateAnEntityByMethodId.
     * 
     * @return entityTypeId
     */
    public java.lang.Long getEntityTypeId() {
        return entityTypeId;
    }


    /**
     * Sets the entityTypeId value for this UpdateAnEntityByMethodId.
     * 
     * @param entityTypeId
     */
    public void setEntityTypeId(java.lang.Long entityTypeId) {
        this.entityTypeId = entityTypeId;
    }


    /**
     * Gets the entityId value for this UpdateAnEntityByMethodId.
     * 
     * @return entityId
     */
    public java.lang.Long getEntityId() {
        return entityId;
    }


    /**
     * Sets the entityId value for this UpdateAnEntityByMethodId.
     * 
     * @param entityId
     */
    public void setEntityId(java.lang.Long entityId) {
        this.entityId = entityId;
    }


    /**
     * Gets the methodId value for this UpdateAnEntityByMethodId.
     * 
     * @return methodId
     */
    public java.lang.Long getMethodId() {
        return methodId;
    }


    /**
     * Sets the methodId value for this UpdateAnEntityByMethodId.
     * 
     * @param methodId
     */
    public void setMethodId(java.lang.Long methodId) {
        this.methodId = methodId;
    }


    /**
     * Gets the methodValue value for this UpdateAnEntityByMethodId.
     * 
     * @return methodValue
     */
    public java.lang.String getMethodValue() {
        return methodValue;
    }


    /**
     * Sets the methodValue value for this UpdateAnEntityByMethodId.
     * 
     * @param methodValue
     */
    public void setMethodValue(java.lang.String methodValue) {
        this.methodValue = methodValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateAnEntityByMethodId)) return false;
        UpdateAnEntityByMethodId other = (UpdateAnEntityByMethodId) obj;
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
            ((this.methodId==null && other.getMethodId()==null) || 
             (this.methodId!=null &&
              this.methodId.equals(other.getMethodId()))) &&
            ((this.methodValue==null && other.getMethodValue()==null) || 
             (this.methodValue!=null &&
              this.methodValue.equals(other.getMethodValue())));
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
        if (getMethodId() != null) {
            _hashCode += getMethodId().hashCode();
        }
        if (getMethodValue() != null) {
            _hashCode += getMethodValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateAnEntityByMethodId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">updateAnEntityByMethodId"));
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
        elemField.setFieldName("methodId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "methodId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("methodValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "methodValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
