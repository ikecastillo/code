/**
 * GetListValues.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class GetListValues  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long fieldTypeId;

    private java.lang.Long fieldSubTypeId;

    public GetListValues() {
    }

    public GetListValues(
           java.lang.String sessionId,
           java.lang.Long fieldTypeId,
           java.lang.Long fieldSubTypeId) {
           this.sessionId = sessionId;
           this.fieldTypeId = fieldTypeId;
           this.fieldSubTypeId = fieldSubTypeId;
    }


    /**
     * Gets the sessionId value for this GetListValues.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this GetListValues.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the fieldTypeId value for this GetListValues.
     * 
     * @return fieldTypeId
     */
    public java.lang.Long getFieldTypeId() {
        return fieldTypeId;
    }


    /**
     * Sets the fieldTypeId value for this GetListValues.
     * 
     * @param fieldTypeId
     */
    public void setFieldTypeId(java.lang.Long fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }


    /**
     * Gets the fieldSubTypeId value for this GetListValues.
     * 
     * @return fieldSubTypeId
     */
    public java.lang.Long getFieldSubTypeId() {
        return fieldSubTypeId;
    }


    /**
     * Sets the fieldSubTypeId value for this GetListValues.
     * 
     * @param fieldSubTypeId
     */
    public void setFieldSubTypeId(java.lang.Long fieldSubTypeId) {
        this.fieldSubTypeId = fieldSubTypeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListValues)) return false;
        GetListValues other = (GetListValues) obj;
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
            ((this.fieldTypeId==null && other.getFieldTypeId()==null) || 
             (this.fieldTypeId!=null &&
              this.fieldTypeId.equals(other.getFieldTypeId()))) &&
            ((this.fieldSubTypeId==null && other.getFieldSubTypeId()==null) || 
             (this.fieldSubTypeId!=null &&
              this.fieldSubTypeId.equals(other.getFieldSubTypeId())));
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
        if (getFieldTypeId() != null) {
            _hashCode += getFieldTypeId().hashCode();
        }
        if (getFieldSubTypeId() != null) {
            _hashCode += getFieldSubTypeId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListValues.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">getListValues"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "fieldTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldSubTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "fieldSubTypeId"));
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
