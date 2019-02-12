/**
 * GetResourceProfileSetting.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class GetResourceProfileSetting  implements java.io.Serializable {
    private java.lang.String sessionId;

    private long[] resourceId;

    public GetResourceProfileSetting() {
    }

    public GetResourceProfileSetting(
           java.lang.String sessionId,
           long[] resourceId) {
           this.sessionId = sessionId;
           this.resourceId = resourceId;
    }


    /**
     * Gets the sessionId value for this GetResourceProfileSetting.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this GetResourceProfileSetting.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the resourceId value for this GetResourceProfileSetting.
     * 
     * @return resourceId
     */
    public long[] getResourceId() {
        return resourceId;
    }


    /**
     * Sets the resourceId value for this GetResourceProfileSetting.
     * 
     * @param resourceId
     */
    public void setResourceId(long[] resourceId) {
        this.resourceId = resourceId;
    }

    public long getResourceId(int i) {
        return this.resourceId[i];
    }

    public void setResourceId(int i, long _value) {
        this.resourceId[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetResourceProfileSetting)) return false;
        GetResourceProfileSetting other = (GetResourceProfileSetting) obj;
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
            ((this.resourceId==null && other.getResourceId()==null) || 
             (this.resourceId!=null &&
              java.util.Arrays.equals(this.resourceId, other.getResourceId())));
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
        if (getResourceId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResourceId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResourceId(), i);
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
        new org.apache.axis.description.TypeDesc(GetResourceProfileSetting.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">getResourceProfileSetting"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resourceId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "resourceId"));
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
