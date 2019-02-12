/**
 * CreateOrReplaceRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class CreateOrReplaceRequest  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long requestTypeId;

    private services.objects.xsd.ValuePair[] valuePairs;

    public CreateOrReplaceRequest() {
    }

    public CreateOrReplaceRequest(
           java.lang.String sessionId,
           java.lang.Long requestTypeId,
           services.objects.xsd.ValuePair[] valuePairs) {
           this.sessionId = sessionId;
           this.requestTypeId = requestTypeId;
           this.valuePairs = valuePairs;
    }


    /**
     * Gets the sessionId value for this CreateOrReplaceRequest.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this CreateOrReplaceRequest.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the requestTypeId value for this CreateOrReplaceRequest.
     * 
     * @return requestTypeId
     */
    public java.lang.Long getRequestTypeId() {
        return requestTypeId;
    }


    /**
     * Sets the requestTypeId value for this CreateOrReplaceRequest.
     * 
     * @param requestTypeId
     */
    public void setRequestTypeId(java.lang.Long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }


    /**
     * Gets the valuePairs value for this CreateOrReplaceRequest.
     * 
     * @return valuePairs
     */
    public services.objects.xsd.ValuePair[] getValuePairs() {
        return valuePairs;
    }


    /**
     * Sets the valuePairs value for this CreateOrReplaceRequest.
     * 
     * @param valuePairs
     */
    public void setValuePairs(services.objects.xsd.ValuePair[] valuePairs) {
        this.valuePairs = valuePairs;
    }

    public services.objects.xsd.ValuePair getValuePairs(int i) {
        return this.valuePairs[i];
    }

    public void setValuePairs(int i, services.objects.xsd.ValuePair _value) {
        this.valuePairs[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateOrReplaceRequest)) return false;
        CreateOrReplaceRequest other = (CreateOrReplaceRequest) obj;
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
            ((this.requestTypeId==null && other.getRequestTypeId()==null) || 
             (this.requestTypeId!=null &&
              this.requestTypeId.equals(other.getRequestTypeId()))) &&
            ((this.valuePairs==null && other.getValuePairs()==null) || 
             (this.valuePairs!=null &&
              java.util.Arrays.equals(this.valuePairs, other.getValuePairs())));
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
        if (getRequestTypeId() != null) {
            _hashCode += getRequestTypeId().hashCode();
        }
        if (getValuePairs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValuePairs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValuePairs(), i);
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
        new org.apache.axis.description.TypeDesc(CreateOrReplaceRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">createOrReplaceRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "requestTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valuePairs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "valuePairs"));
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
