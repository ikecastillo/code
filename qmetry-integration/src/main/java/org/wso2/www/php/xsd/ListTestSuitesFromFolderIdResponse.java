/**
 * ListTestSuitesFromFolderIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListTestSuitesFromFolderIdResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.TestSuiteEntity[] testsuites;

    public ListTestSuitesFromFolderIdResponse() {
    }

    public ListTestSuitesFromFolderIdResponse(
           org.wso2.www.php.xsd.TestSuiteEntity[] testsuites) {
           this.testsuites = testsuites;
    }


    /**
     * Gets the testsuites value for this ListTestSuitesFromFolderIdResponse.
     * 
     * @return testsuites
     */
    public org.wso2.www.php.xsd.TestSuiteEntity[] getTestsuites() {
        return testsuites;
    }


    /**
     * Sets the testsuites value for this ListTestSuitesFromFolderIdResponse.
     * 
     * @param testsuites
     */
    public void setTestsuites(org.wso2.www.php.xsd.TestSuiteEntity[] testsuites) {
        this.testsuites = testsuites;
    }

    public org.wso2.www.php.xsd.TestSuiteEntity getTestsuites(int i) {
        return this.testsuites[i];
    }

    public void setTestsuites(int i, org.wso2.www.php.xsd.TestSuiteEntity _value) {
        this.testsuites[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListTestSuitesFromFolderIdResponse)) return false;
        ListTestSuitesFromFolderIdResponse other = (ListTestSuitesFromFolderIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.testsuites==null && other.getTestsuites()==null) || 
             (this.testsuites!=null &&
              java.util.Arrays.equals(this.testsuites, other.getTestsuites())));
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
        if (getTestsuites() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestsuites());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestsuites(), i);
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
        new org.apache.axis.description.TypeDesc(ListTestSuitesFromFolderIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listTestSuitesFromFolderIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testsuites");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testsuites"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteEntity"));
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
