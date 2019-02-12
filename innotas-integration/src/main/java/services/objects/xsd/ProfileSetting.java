/**
 * ProfileSetting.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class ProfileSetting  implements java.io.Serializable {
    private java.lang.Long categoryId;

    private java.lang.Long profileSettingId;

    private java.lang.Long rangeValueId;

    private java.lang.Long subCategoryId;

    public ProfileSetting() {
    }

    public ProfileSetting(
           java.lang.Long categoryId,
           java.lang.Long profileSettingId,
           java.lang.Long rangeValueId,
           java.lang.Long subCategoryId) {
           this.categoryId = categoryId;
           this.profileSettingId = profileSettingId;
           this.rangeValueId = rangeValueId;
           this.subCategoryId = subCategoryId;
    }


    /**
     * Gets the categoryId value for this ProfileSetting.
     * 
     * @return categoryId
     */
    public java.lang.Long getCategoryId() {
        return categoryId;
    }


    /**
     * Sets the categoryId value for this ProfileSetting.
     * 
     * @param categoryId
     */
    public void setCategoryId(java.lang.Long categoryId) {
        this.categoryId = categoryId;
    }


    /**
     * Gets the profileSettingId value for this ProfileSetting.
     * 
     * @return profileSettingId
     */
    public java.lang.Long getProfileSettingId() {
        return profileSettingId;
    }


    /**
     * Sets the profileSettingId value for this ProfileSetting.
     * 
     * @param profileSettingId
     */
    public void setProfileSettingId(java.lang.Long profileSettingId) {
        this.profileSettingId = profileSettingId;
    }


    /**
     * Gets the rangeValueId value for this ProfileSetting.
     * 
     * @return rangeValueId
     */
    public java.lang.Long getRangeValueId() {
        return rangeValueId;
    }


    /**
     * Sets the rangeValueId value for this ProfileSetting.
     * 
     * @param rangeValueId
     */
    public void setRangeValueId(java.lang.Long rangeValueId) {
        this.rangeValueId = rangeValueId;
    }


    /**
     * Gets the subCategoryId value for this ProfileSetting.
     * 
     * @return subCategoryId
     */
    public java.lang.Long getSubCategoryId() {
        return subCategoryId;
    }


    /**
     * Sets the subCategoryId value for this ProfileSetting.
     * 
     * @param subCategoryId
     */
    public void setSubCategoryId(java.lang.Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProfileSetting)) return false;
        ProfileSetting other = (ProfileSetting) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.categoryId==null && other.getCategoryId()==null) || 
             (this.categoryId!=null &&
              this.categoryId.equals(other.getCategoryId()))) &&
            ((this.profileSettingId==null && other.getProfileSettingId()==null) || 
             (this.profileSettingId!=null &&
              this.profileSettingId.equals(other.getProfileSettingId()))) &&
            ((this.rangeValueId==null && other.getRangeValueId()==null) || 
             (this.rangeValueId!=null &&
              this.rangeValueId.equals(other.getRangeValueId()))) &&
            ((this.subCategoryId==null && other.getSubCategoryId()==null) || 
             (this.subCategoryId!=null &&
              this.subCategoryId.equals(other.getSubCategoryId())));
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
        if (getCategoryId() != null) {
            _hashCode += getCategoryId().hashCode();
        }
        if (getProfileSettingId() != null) {
            _hashCode += getProfileSettingId().hashCode();
        }
        if (getRangeValueId() != null) {
            _hashCode += getRangeValueId().hashCode();
        }
        if (getSubCategoryId() != null) {
            _hashCode += getSubCategoryId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProfileSetting.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ProfileSetting"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoryId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "categoryId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileSettingId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "profileSettingId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rangeValueId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "rangeValueId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subCategoryId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "subCategoryId"));
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
