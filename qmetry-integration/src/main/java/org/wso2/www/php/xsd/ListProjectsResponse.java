/**
 * ListProjectsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListProjectsResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.Entity[] projects;

    public ListProjectsResponse() {
    }

    public ListProjectsResponse(
           org.wso2.www.php.xsd.Entity[] projects) {
           this.projects = projects;
    }


    /**
     * Gets the projects value for this ListProjectsResponse.
     * 
     * @return projects
     */
    public org.wso2.www.php.xsd.Entity[] getProjects() {
        return projects;
    }


    /**
     * Sets the projects value for this ListProjectsResponse.
     * 
     * @param projects
     */
    public void setProjects(org.wso2.www.php.xsd.Entity[] projects) {
        this.projects = projects;
    }

    public org.wso2.www.php.xsd.Entity getProjects(int i) {
        return this.projects[i];
    }

    public void setProjects(int i, org.wso2.www.php.xsd.Entity _value) {
        this.projects[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListProjectsResponse)) return false;
        ListProjectsResponse other = (ListProjectsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.projects==null && other.getProjects()==null) || 
             (this.projects!=null &&
              java.util.Arrays.equals(this.projects, other.getProjects())));
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
        if (getProjects() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProjects());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProjects(), i);
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
        new org.apache.axis.description.TypeDesc(ListProjectsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listProjectsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projects");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "projects"));
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
