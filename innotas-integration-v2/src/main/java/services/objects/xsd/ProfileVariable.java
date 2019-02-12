/**
 * ProfileVariable.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class ProfileVariable  implements java.io.Serializable {
    private java.lang.String elementValue;

    private java.lang.Long id;

    private java.lang.String skillRangeTitle;

    private services.objects.xsd.ValuePair[] skillRangeValues;

    private services.objects.xsd.ValuePair[] subCategories;

    public ProfileVariable() {
    }

    public ProfileVariable(
           java.lang.String elementValue,
           java.lang.Long id,
           java.lang.String skillRangeTitle,
           services.objects.xsd.ValuePair[] skillRangeValues,
           services.objects.xsd.ValuePair[] subCategories) {
           this.elementValue = elementValue;
           this.id = id;
           this.skillRangeTitle = skillRangeTitle;
           this.skillRangeValues = skillRangeValues;
           this.subCategories = subCategories;
    }


    /**
     * Gets the elementValue value for this ProfileVariable.
     * 
     * @return elementValue
     */
    public java.lang.String getElementValue() {
        return elementValue;
    }


    /**
     * Sets the elementValue value for this ProfileVariable.
     * 
     * @param elementValue
     */
    public void setElementValue(java.lang.String elementValue) {
        this.elementValue = elementValue;
    }


    /**
     * Gets the id value for this ProfileVariable.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this ProfileVariable.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the skillRangeTitle value for this ProfileVariable.
     * 
     * @return skillRangeTitle
     */
    public java.lang.String getSkillRangeTitle() {
        return skillRangeTitle;
    }


    /**
     * Sets the skillRangeTitle value for this ProfileVariable.
     * 
     * @param skillRangeTitle
     */
    public void setSkillRangeTitle(java.lang.String skillRangeTitle) {
        this.skillRangeTitle = skillRangeTitle;
    }


    /**
     * Gets the skillRangeValues value for this ProfileVariable.
     * 
     * @return skillRangeValues
     */
    public services.objects.xsd.ValuePair[] getSkillRangeValues() {
        return skillRangeValues;
    }


    /**
     * Sets the skillRangeValues value for this ProfileVariable.
     * 
     * @param skillRangeValues
     */
    public void setSkillRangeValues(services.objects.xsd.ValuePair[] skillRangeValues) {
        this.skillRangeValues = skillRangeValues;
    }

    public services.objects.xsd.ValuePair getSkillRangeValues(int i) {
        return this.skillRangeValues[i];
    }

    public void setSkillRangeValues(int i, services.objects.xsd.ValuePair _value) {
        this.skillRangeValues[i] = _value;
    }


    /**
     * Gets the subCategories value for this ProfileVariable.
     * 
     * @return subCategories
     */
    public services.objects.xsd.ValuePair[] getSubCategories() {
        return subCategories;
    }


    /**
     * Sets the subCategories value for this ProfileVariable.
     * 
     * @param subCategories
     */
    public void setSubCategories(services.objects.xsd.ValuePair[] subCategories) {
        this.subCategories = subCategories;
    }

    public services.objects.xsd.ValuePair getSubCategories(int i) {
        return this.subCategories[i];
    }

    public void setSubCategories(int i, services.objects.xsd.ValuePair _value) {
        this.subCategories[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProfileVariable)) return false;
        ProfileVariable other = (ProfileVariable) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.elementValue==null && other.getElementValue()==null) || 
             (this.elementValue!=null &&
              this.elementValue.equals(other.getElementValue()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.skillRangeTitle==null && other.getSkillRangeTitle()==null) || 
             (this.skillRangeTitle!=null &&
              this.skillRangeTitle.equals(other.getSkillRangeTitle()))) &&
            ((this.skillRangeValues==null && other.getSkillRangeValues()==null) || 
             (this.skillRangeValues!=null &&
              java.util.Arrays.equals(this.skillRangeValues, other.getSkillRangeValues()))) &&
            ((this.subCategories==null && other.getSubCategories()==null) || 
             (this.subCategories!=null &&
              java.util.Arrays.equals(this.subCategories, other.getSubCategories())));
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
        if (getElementValue() != null) {
            _hashCode += getElementValue().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getSkillRangeTitle() != null) {
            _hashCode += getSkillRangeTitle().hashCode();
        }
        if (getSkillRangeValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSkillRangeValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSkillRangeValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSubCategories() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubCategories());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubCategories(), i);
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
        new org.apache.axis.description.TypeDesc(ProfileVariable.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ProfileVariable"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "elementValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("skillRangeTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "skillRangeTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("skillRangeValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "skillRangeValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ValuePair"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subCategories");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "subCategories"));
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
