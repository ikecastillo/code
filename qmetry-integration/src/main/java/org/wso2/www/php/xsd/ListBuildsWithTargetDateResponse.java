/**
 * ListBuildsWithTargetDateResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListBuildsWithTargetDateResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.Entity[] builds;

    public ListBuildsWithTargetDateResponse() {
    }

    public ListBuildsWithTargetDateResponse(
           org.wso2.www.php.xsd.Entity[] builds) {
           this.builds = builds;
    }


    /**
     * Gets the builds value for this ListBuildsWithTargetDateResponse.
     * 
     * @return builds
     */
    public org.wso2.www.php.xsd.Entity[] getBuilds() {
        return builds;
    }


    /**
     * Sets the builds value for this ListBuildsWithTargetDateResponse.
     * 
     * @param builds
     */
    public void setBuilds(org.wso2.www.php.xsd.Entity[] builds) {
        this.builds = builds;
    }

    public org.wso2.www.php.xsd.Entity getBuilds(int i) {
        return this.builds[i];
    }

    public void setBuilds(int i, org.wso2.www.php.xsd.Entity _value) {
        this.builds[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListBuildsWithTargetDateResponse)) return false;
        ListBuildsWithTargetDateResponse other = (ListBuildsWithTargetDateResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.builds==null && other.getBuilds()==null) || 
             (this.builds!=null &&
              java.util.Arrays.equals(this.builds, other.getBuilds())));
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
        if (getBuilds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBuilds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBuilds(), i);
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
        new org.apache.axis.description.TypeDesc(ListBuildsWithTargetDateResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listBuildsWithTargetDateResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("builds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "builds"));
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
