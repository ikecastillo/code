/**
 * ResourceProfileSetting.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class ResourceProfileSetting  implements java.io.Serializable {
    private services.objects.xsd.ProfileSetting[] profileSetting;

    private java.lang.Long resourceId;

    public ResourceProfileSetting() {
    }

    public ResourceProfileSetting(
           services.objects.xsd.ProfileSetting[] profileSetting,
           java.lang.Long resourceId) {
           this.profileSetting = profileSetting;
           this.resourceId = resourceId;
    }


    /**
     * Gets the profileSetting value for this ResourceProfileSetting.
     * 
     * @return profileSetting
     */
    public services.objects.xsd.ProfileSetting[] getProfileSetting() {
        return profileSetting;
    }


    /**
     * Sets the profileSetting value for this ResourceProfileSetting.
     * 
     * @param profileSetting
     */
    public void setProfileSetting(services.objects.xsd.ProfileSetting[] profileSetting) {
        this.profileSetting = profileSetting;
    }

    public services.objects.xsd.ProfileSetting getProfileSetting(int i) {
        return this.profileSetting[i];
    }

    public void setProfileSetting(int i, services.objects.xsd.ProfileSetting _value) {
        this.profileSetting[i] = _value;
    }


    /**
     * Gets the resourceId value for this ResourceProfileSetting.
     * 
     * @return resourceId
     */
    public java.lang.Long getResourceId() {
        return resourceId;
    }


    /**
     * Sets the resourceId value for this ResourceProfileSetting.
     * 
     * @param resourceId
     */
    public void setResourceId(java.lang.Long resourceId) {
        this.resourceId = resourceId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResourceProfileSetting)) return false;
        ResourceProfileSetting other = (ResourceProfileSetting) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.profileSetting==null && other.getProfileSetting()==null) || 
             (this.profileSetting!=null &&
              java.util.Arrays.equals(this.profileSetting, other.getProfileSetting()))) &&
            ((this.resourceId==null && other.getResourceId()==null) || 
             (this.resourceId!=null &&
              this.resourceId.equals(other.getResourceId())));
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
        if (getProfileSetting() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProfileSetting());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProfileSetting(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResourceId() != null) {
            _hashCode += getResourceId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResourceProfileSetting.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ResourceProfileSetting"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileSetting");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "profileSetting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ProfileSetting"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resourceId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "resourceId"));
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
