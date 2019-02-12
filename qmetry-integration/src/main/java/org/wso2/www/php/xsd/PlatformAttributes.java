/**
 * PlatformAttributes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class PlatformAttributes  implements java.io.Serializable {
    private org.wso2.www.php.xsd.PlatformAttribute[] platformAttribute;

    public PlatformAttributes() {
    }

    public PlatformAttributes(
           org.wso2.www.php.xsd.PlatformAttribute[] platformAttribute) {
           this.platformAttribute = platformAttribute;
    }


    /**
     * Gets the platformAttribute value for this PlatformAttributes.
     * 
     * @return platformAttribute
     */
    public org.wso2.www.php.xsd.PlatformAttribute[] getPlatformAttribute() {
        return platformAttribute;
    }


    /**
     * Sets the platformAttribute value for this PlatformAttributes.
     * 
     * @param platformAttribute
     */
    public void setPlatformAttribute(org.wso2.www.php.xsd.PlatformAttribute[] platformAttribute) {
        this.platformAttribute = platformAttribute;
    }

    public org.wso2.www.php.xsd.PlatformAttribute getPlatformAttribute(int i) {
        return this.platformAttribute[i];
    }

    public void setPlatformAttribute(int i, org.wso2.www.php.xsd.PlatformAttribute _value) {
        this.platformAttribute[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PlatformAttributes)) return false;
        PlatformAttributes other = (PlatformAttributes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.platformAttribute==null && other.getPlatformAttribute()==null) || 
             (this.platformAttribute!=null &&
              java.util.Arrays.equals(this.platformAttribute, other.getPlatformAttribute())));
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
        if (getPlatformAttribute() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPlatformAttribute());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPlatformAttribute(), i);
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
        new org.apache.axis.description.TypeDesc(PlatformAttributes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "PlatformAttributes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("platformAttribute");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "platformAttribute"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "PlatformAttribute"));
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
