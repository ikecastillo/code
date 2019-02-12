/**
 * ListUsersResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListUsersResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.Entity[] users;

    public ListUsersResponse() {
    }

    public ListUsersResponse(
           org.wso2.www.php.xsd.Entity[] users) {
           this.users = users;
    }


    /**
     * Gets the users value for this ListUsersResponse.
     * 
     * @return users
     */
    public org.wso2.www.php.xsd.Entity[] getUsers() {
        return users;
    }


    /**
     * Sets the users value for this ListUsersResponse.
     * 
     * @param users
     */
    public void setUsers(org.wso2.www.php.xsd.Entity[] users) {
        this.users = users;
    }

    public org.wso2.www.php.xsd.Entity getUsers(int i) {
        return this.users[i];
    }

    public void setUsers(int i, org.wso2.www.php.xsd.Entity _value) {
        this.users[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListUsersResponse)) return false;
        ListUsersResponse other = (ListUsersResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.users==null && other.getUsers()==null) || 
             (this.users!=null &&
              java.util.Arrays.equals(this.users, other.getUsers())));
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
        if (getUsers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsers(), i);
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
        new org.apache.axis.description.TypeDesc(ListUsersResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listUsersResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("users");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "users"));
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
