/**
 * WsSearchDropsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class WsSearchDropsResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.Entity[] drops;

    public WsSearchDropsResponse() {
    }

    public WsSearchDropsResponse(
           org.wso2.www.php.xsd.Entity[] drops) {
           this.drops = drops;
    }


    /**
     * Gets the drops value for this WsSearchDropsResponse.
     * 
     * @return drops
     */
    public org.wso2.www.php.xsd.Entity[] getDrops() {
        return drops;
    }


    /**
     * Sets the drops value for this WsSearchDropsResponse.
     * 
     * @param drops
     */
    public void setDrops(org.wso2.www.php.xsd.Entity[] drops) {
        this.drops = drops;
    }

    public org.wso2.www.php.xsd.Entity getDrops(int i) {
        return this.drops[i];
    }

    public void setDrops(int i, org.wso2.www.php.xsd.Entity _value) {
        this.drops[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsSearchDropsResponse)) return false;
        WsSearchDropsResponse other = (WsSearchDropsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.drops==null && other.getDrops()==null) || 
             (this.drops!=null &&
              java.util.Arrays.equals(this.drops, other.getDrops())));
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
        if (getDrops() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDrops());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDrops(), i);
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
        new org.apache.axis.description.TypeDesc(WsSearchDropsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsSearchDropsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("drops");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "drops"));
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
