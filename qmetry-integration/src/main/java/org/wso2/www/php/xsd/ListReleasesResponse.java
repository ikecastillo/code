/**
 * ListReleasesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListReleasesResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.Entity[] releases;

    public ListReleasesResponse() {
    }

    public ListReleasesResponse(
           org.wso2.www.php.xsd.Entity[] releases) {
           this.releases = releases;
    }


    /**
     * Gets the releases value for this ListReleasesResponse.
     * 
     * @return releases
     */
    public org.wso2.www.php.xsd.Entity[] getReleases() {
        return releases;
    }


    /**
     * Sets the releases value for this ListReleasesResponse.
     * 
     * @param releases
     */
    public void setReleases(org.wso2.www.php.xsd.Entity[] releases) {
        this.releases = releases;
    }

    public org.wso2.www.php.xsd.Entity getReleases(int i) {
        return this.releases[i];
    }

    public void setReleases(int i, org.wso2.www.php.xsd.Entity _value) {
        this.releases[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListReleasesResponse)) return false;
        ListReleasesResponse other = (ListReleasesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.releases==null && other.getReleases()==null) || 
             (this.releases!=null &&
              java.util.Arrays.equals(this.releases, other.getReleases())));
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
        if (getReleases() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReleases());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReleases(), i);
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
        new org.apache.axis.description.TypeDesc(ListReleasesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listReleasesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("releases");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "releases"));
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
