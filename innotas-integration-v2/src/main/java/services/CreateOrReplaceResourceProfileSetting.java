/**
 * CreateOrReplaceResourceProfileSetting.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class CreateOrReplaceResourceProfileSetting  implements java.io.Serializable {
    private java.lang.String sessionId;

    private services.objects.xsd.ResourceProfileSetting[] resourceProfileSettings;

    public CreateOrReplaceResourceProfileSetting() {
    }

    public CreateOrReplaceResourceProfileSetting(
           java.lang.String sessionId,
           services.objects.xsd.ResourceProfileSetting[] resourceProfileSettings) {
           this.sessionId = sessionId;
           this.resourceProfileSettings = resourceProfileSettings;
    }


    /**
     * Gets the sessionId value for this CreateOrReplaceResourceProfileSetting.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this CreateOrReplaceResourceProfileSetting.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the resourceProfileSettings value for this CreateOrReplaceResourceProfileSetting.
     * 
     * @return resourceProfileSettings
     */
    public services.objects.xsd.ResourceProfileSetting[] getResourceProfileSettings() {
        return resourceProfileSettings;
    }


    /**
     * Sets the resourceProfileSettings value for this CreateOrReplaceResourceProfileSetting.
     * 
     * @param resourceProfileSettings
     */
    public void setResourceProfileSettings(services.objects.xsd.ResourceProfileSetting[] resourceProfileSettings) {
        this.resourceProfileSettings = resourceProfileSettings;
    }

    public services.objects.xsd.ResourceProfileSetting getResourceProfileSettings(int i) {
        return this.resourceProfileSettings[i];
    }

    public void setResourceProfileSettings(int i, services.objects.xsd.ResourceProfileSetting _value) {
        this.resourceProfileSettings[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateOrReplaceResourceProfileSetting)) return false;
        CreateOrReplaceResourceProfileSetting other = (CreateOrReplaceResourceProfileSetting) obj;
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
            ((this.resourceProfileSettings==null && other.getResourceProfileSettings()==null) || 
             (this.resourceProfileSettings!=null &&
              java.util.Arrays.equals(this.resourceProfileSettings, other.getResourceProfileSettings())));
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
        if (getResourceProfileSettings() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResourceProfileSettings());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResourceProfileSettings(), i);
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
        new org.apache.axis.description.TypeDesc(CreateOrReplaceResourceProfileSetting.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">createOrReplaceResourceProfileSetting"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resourceProfileSettings");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "resourceProfileSettings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ResourceProfileSetting"));
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
