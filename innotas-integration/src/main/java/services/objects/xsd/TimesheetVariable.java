/**
 * TimesheetVariable.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class TimesheetVariable  implements java.io.Serializable {
    private services.objects.xsd.ValuePair[] levelVariable;

    private java.lang.Long variableTypeId;

    public TimesheetVariable() {
    }

    public TimesheetVariable(
           services.objects.xsd.ValuePair[] levelVariable,
           java.lang.Long variableTypeId) {
           this.levelVariable = levelVariable;
           this.variableTypeId = variableTypeId;
    }


    /**
     * Gets the levelVariable value for this TimesheetVariable.
     * 
     * @return levelVariable
     */
    public services.objects.xsd.ValuePair[] getLevelVariable() {
        return levelVariable;
    }


    /**
     * Sets the levelVariable value for this TimesheetVariable.
     * 
     * @param levelVariable
     */
    public void setLevelVariable(services.objects.xsd.ValuePair[] levelVariable) {
        this.levelVariable = levelVariable;
    }

    public services.objects.xsd.ValuePair getLevelVariable(int i) {
        return this.levelVariable[i];
    }

    public void setLevelVariable(int i, services.objects.xsd.ValuePair _value) {
        this.levelVariable[i] = _value;
    }


    /**
     * Gets the variableTypeId value for this TimesheetVariable.
     * 
     * @return variableTypeId
     */
    public java.lang.Long getVariableTypeId() {
        return variableTypeId;
    }


    /**
     * Sets the variableTypeId value for this TimesheetVariable.
     * 
     * @param variableTypeId
     */
    public void setVariableTypeId(java.lang.Long variableTypeId) {
        this.variableTypeId = variableTypeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TimesheetVariable)) return false;
        TimesheetVariable other = (TimesheetVariable) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.levelVariable==null && other.getLevelVariable()==null) || 
             (this.levelVariable!=null &&
              java.util.Arrays.equals(this.levelVariable, other.getLevelVariable()))) &&
            ((this.variableTypeId==null && other.getVariableTypeId()==null) || 
             (this.variableTypeId!=null &&
              this.variableTypeId.equals(other.getVariableTypeId())));
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
        if (getLevelVariable() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLevelVariable());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLevelVariable(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getVariableTypeId() != null) {
            _hashCode += getVariableTypeId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TimesheetVariable.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "TimesheetVariable"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("levelVariable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "levelVariable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ValuePair"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("variableTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "variableTypeId"));
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
