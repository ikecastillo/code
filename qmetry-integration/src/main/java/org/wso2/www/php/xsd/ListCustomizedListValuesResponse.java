/**
 * ListCustomizedListValuesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListCustomizedListValuesResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.Entity[] customizedListValues;

    public ListCustomizedListValuesResponse() {
    }

    public ListCustomizedListValuesResponse(
           org.wso2.www.php.xsd.Entity[] customizedListValues) {
           this.customizedListValues = customizedListValues;
    }


    /**
     * Gets the customizedListValues value for this ListCustomizedListValuesResponse.
     * 
     * @return customizedListValues
     */
    public org.wso2.www.php.xsd.Entity[] getCustomizedListValues() {
        return customizedListValues;
    }


    /**
     * Sets the customizedListValues value for this ListCustomizedListValuesResponse.
     * 
     * @param customizedListValues
     */
    public void setCustomizedListValues(org.wso2.www.php.xsd.Entity[] customizedListValues) {
        this.customizedListValues = customizedListValues;
    }

    public org.wso2.www.php.xsd.Entity getCustomizedListValues(int i) {
        return this.customizedListValues[i];
    }

    public void setCustomizedListValues(int i, org.wso2.www.php.xsd.Entity _value) {
        this.customizedListValues[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListCustomizedListValuesResponse)) return false;
        ListCustomizedListValuesResponse other = (ListCustomizedListValuesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.customizedListValues==null && other.getCustomizedListValues()==null) || 
             (this.customizedListValues!=null &&
              java.util.Arrays.equals(this.customizedListValues, other.getCustomizedListValues())));
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
        if (getCustomizedListValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCustomizedListValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCustomizedListValues(), i);
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
        new org.apache.axis.description.TypeDesc(ListCustomizedListValuesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listCustomizedListValuesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customizedListValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "customizedListValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "Entity"));
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
