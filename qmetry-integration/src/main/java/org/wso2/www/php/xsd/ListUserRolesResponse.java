/**
 * ListUserRolesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListUserRolesResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.Entity[] roles;

    public ListUserRolesResponse() {
    }

    public ListUserRolesResponse(
           org.wso2.www.php.xsd.Entity[] roles) {
           this.roles = roles;
    }


    /**
     * Gets the roles value for this ListUserRolesResponse.
     * 
     * @return roles
     */
    public org.wso2.www.php.xsd.Entity[] getRoles() {
        return roles;
    }


    /**
     * Sets the roles value for this ListUserRolesResponse.
     * 
     * @param roles
     */
    public void setRoles(org.wso2.www.php.xsd.Entity[] roles) {
        this.roles = roles;
    }

    public org.wso2.www.php.xsd.Entity getRoles(int i) {
        return this.roles[i];
    }

    public void setRoles(int i, org.wso2.www.php.xsd.Entity _value) {
        this.roles[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListUserRolesResponse)) return false;
        ListUserRolesResponse other = (ListUserRolesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.roles==null && other.getRoles()==null) || 
             (this.roles!=null &&
              java.util.Arrays.equals(this.roles, other.getRoles())));
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
        if (getRoles() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRoles());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRoles(), i);
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
        new org.apache.axis.description.TypeDesc(ListUserRolesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listUserRolesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("roles");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "roles"));
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
