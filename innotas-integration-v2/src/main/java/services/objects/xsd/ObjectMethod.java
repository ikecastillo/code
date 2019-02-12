/**
 * ObjectMethod.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class ObjectMethod  implements java.io.Serializable {
    private java.lang.Object defaultValue;

    private java.lang.Long fieldLength;

    private java.lang.Long fieldSubTypeId;

    private java.lang.Long fieldTypeId;

    private java.lang.Boolean isRequired;

    private java.lang.String label;

    private java.lang.String method;

    private java.lang.Long methodId;

    private java.lang.Boolean onInsert;

    private java.lang.Boolean onSelect;

    private java.lang.Boolean onUpdate;

    private java.lang.Integer sequence;

    public ObjectMethod() {
    }

    public ObjectMethod(
           java.lang.Object defaultValue,
           java.lang.Long fieldLength,
           java.lang.Long fieldSubTypeId,
           java.lang.Long fieldTypeId,
           java.lang.Boolean isRequired,
           java.lang.String label,
           java.lang.String method,
           java.lang.Long methodId,
           java.lang.Boolean onInsert,
           java.lang.Boolean onSelect,
           java.lang.Boolean onUpdate,
           java.lang.Integer sequence) {
           this.defaultValue = defaultValue;
           this.fieldLength = fieldLength;
           this.fieldSubTypeId = fieldSubTypeId;
           this.fieldTypeId = fieldTypeId;
           this.isRequired = isRequired;
           this.label = label;
           this.method = method;
           this.methodId = methodId;
           this.onInsert = onInsert;
           this.onSelect = onSelect;
           this.onUpdate = onUpdate;
           this.sequence = sequence;
    }


    /**
     * Gets the defaultValue value for this ObjectMethod.
     * 
     * @return defaultValue
     */
    public java.lang.Object getDefaultValue() {
        return defaultValue;
    }


    /**
     * Sets the defaultValue value for this ObjectMethod.
     * 
     * @param defaultValue
     */
    public void setDefaultValue(java.lang.Object defaultValue) {
        this.defaultValue = defaultValue;
    }


    /**
     * Gets the fieldLength value for this ObjectMethod.
     * 
     * @return fieldLength
     */
    public java.lang.Long getFieldLength() {
        return fieldLength;
    }


    /**
     * Sets the fieldLength value for this ObjectMethod.
     * 
     * @param fieldLength
     */
    public void setFieldLength(java.lang.Long fieldLength) {
        this.fieldLength = fieldLength;
    }


    /**
     * Gets the fieldSubTypeId value for this ObjectMethod.
     * 
     * @return fieldSubTypeId
     */
    public java.lang.Long getFieldSubTypeId() {
        return fieldSubTypeId;
    }


    /**
     * Sets the fieldSubTypeId value for this ObjectMethod.
     * 
     * @param fieldSubTypeId
     */
    public void setFieldSubTypeId(java.lang.Long fieldSubTypeId) {
        this.fieldSubTypeId = fieldSubTypeId;
    }


    /**
     * Gets the fieldTypeId value for this ObjectMethod.
     * 
     * @return fieldTypeId
     */
    public java.lang.Long getFieldTypeId() {
        return fieldTypeId;
    }


    /**
     * Sets the fieldTypeId value for this ObjectMethod.
     * 
     * @param fieldTypeId
     */
    public void setFieldTypeId(java.lang.Long fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }


    /**
     * Gets the isRequired value for this ObjectMethod.
     * 
     * @return isRequired
     */
    public java.lang.Boolean getIsRequired() {
        return isRequired;
    }


    /**
     * Sets the isRequired value for this ObjectMethod.
     * 
     * @param isRequired
     */
    public void setIsRequired(java.lang.Boolean isRequired) {
        this.isRequired = isRequired;
    }


    /**
     * Gets the label value for this ObjectMethod.
     * 
     * @return label
     */
    public java.lang.String getLabel() {
        return label;
    }


    /**
     * Sets the label value for this ObjectMethod.
     * 
     * @param label
     */
    public void setLabel(java.lang.String label) {
        this.label = label;
    }


    /**
     * Gets the method value for this ObjectMethod.
     * 
     * @return method
     */
    public java.lang.String getMethod() {
        return method;
    }


    /**
     * Sets the method value for this ObjectMethod.
     * 
     * @param method
     */
    public void setMethod(java.lang.String method) {
        this.method = method;
    }


    /**
     * Gets the methodId value for this ObjectMethod.
     * 
     * @return methodId
     */
    public java.lang.Long getMethodId() {
        return methodId;
    }


    /**
     * Sets the methodId value for this ObjectMethod.
     * 
     * @param methodId
     */
    public void setMethodId(java.lang.Long methodId) {
        this.methodId = methodId;
    }


    /**
     * Gets the onInsert value for this ObjectMethod.
     * 
     * @return onInsert
     */
    public java.lang.Boolean getOnInsert() {
        return onInsert;
    }


    /**
     * Sets the onInsert value for this ObjectMethod.
     * 
     * @param onInsert
     */
    public void setOnInsert(java.lang.Boolean onInsert) {
        this.onInsert = onInsert;
    }


    /**
     * Gets the onSelect value for this ObjectMethod.
     * 
     * @return onSelect
     */
    public java.lang.Boolean getOnSelect() {
        return onSelect;
    }


    /**
     * Sets the onSelect value for this ObjectMethod.
     * 
     * @param onSelect
     */
    public void setOnSelect(java.lang.Boolean onSelect) {
        this.onSelect = onSelect;
    }


    /**
     * Gets the onUpdate value for this ObjectMethod.
     * 
     * @return onUpdate
     */
    public java.lang.Boolean getOnUpdate() {
        return onUpdate;
    }


    /**
     * Sets the onUpdate value for this ObjectMethod.
     * 
     * @param onUpdate
     */
    public void setOnUpdate(java.lang.Boolean onUpdate) {
        this.onUpdate = onUpdate;
    }


    /**
     * Gets the sequence value for this ObjectMethod.
     * 
     * @return sequence
     */
    public java.lang.Integer getSequence() {
        return sequence;
    }


    /**
     * Sets the sequence value for this ObjectMethod.
     * 
     * @param sequence
     */
    public void setSequence(java.lang.Integer sequence) {
        this.sequence = sequence;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObjectMethod)) return false;
        ObjectMethod other = (ObjectMethod) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.defaultValue==null && other.getDefaultValue()==null) || 
             (this.defaultValue!=null &&
              this.defaultValue.equals(other.getDefaultValue()))) &&
            ((this.fieldLength==null && other.getFieldLength()==null) || 
             (this.fieldLength!=null &&
              this.fieldLength.equals(other.getFieldLength()))) &&
            ((this.fieldSubTypeId==null && other.getFieldSubTypeId()==null) || 
             (this.fieldSubTypeId!=null &&
              this.fieldSubTypeId.equals(other.getFieldSubTypeId()))) &&
            ((this.fieldTypeId==null && other.getFieldTypeId()==null) || 
             (this.fieldTypeId!=null &&
              this.fieldTypeId.equals(other.getFieldTypeId()))) &&
            ((this.isRequired==null && other.getIsRequired()==null) || 
             (this.isRequired!=null &&
              this.isRequired.equals(other.getIsRequired()))) &&
            ((this.label==null && other.getLabel()==null) || 
             (this.label!=null &&
              this.label.equals(other.getLabel()))) &&
            ((this.method==null && other.getMethod()==null) || 
             (this.method!=null &&
              this.method.equals(other.getMethod()))) &&
            ((this.methodId==null && other.getMethodId()==null) || 
             (this.methodId!=null &&
              this.methodId.equals(other.getMethodId()))) &&
            ((this.onInsert==null && other.getOnInsert()==null) || 
             (this.onInsert!=null &&
              this.onInsert.equals(other.getOnInsert()))) &&
            ((this.onSelect==null && other.getOnSelect()==null) || 
             (this.onSelect!=null &&
              this.onSelect.equals(other.getOnSelect()))) &&
            ((this.onUpdate==null && other.getOnUpdate()==null) || 
             (this.onUpdate!=null &&
              this.onUpdate.equals(other.getOnUpdate()))) &&
            ((this.sequence==null && other.getSequence()==null) || 
             (this.sequence!=null &&
              this.sequence.equals(other.getSequence())));
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
        if (getDefaultValue() != null) {
            _hashCode += getDefaultValue().hashCode();
        }
        if (getFieldLength() != null) {
            _hashCode += getFieldLength().hashCode();
        }
        if (getFieldSubTypeId() != null) {
            _hashCode += getFieldSubTypeId().hashCode();
        }
        if (getFieldTypeId() != null) {
            _hashCode += getFieldTypeId().hashCode();
        }
        if (getIsRequired() != null) {
            _hashCode += getIsRequired().hashCode();
        }
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        if (getMethod() != null) {
            _hashCode += getMethod().hashCode();
        }
        if (getMethodId() != null) {
            _hashCode += getMethodId().hashCode();
        }
        if (getOnInsert() != null) {
            _hashCode += getOnInsert().hashCode();
        }
        if (getOnSelect() != null) {
            _hashCode += getOnSelect().hashCode();
        }
        if (getOnUpdate() != null) {
            _hashCode += getOnUpdate().hashCode();
        }
        if (getSequence() != null) {
            _hashCode += getSequence().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObjectMethod.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ObjectMethod"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "defaultValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldLength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "fieldLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldSubTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "fieldSubTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "fieldTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "isRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "label"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("method");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "method"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("methodId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "methodId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("onInsert");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "onInsert"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("onSelect");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "onSelect"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("onUpdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "onUpdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequence");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "sequence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
