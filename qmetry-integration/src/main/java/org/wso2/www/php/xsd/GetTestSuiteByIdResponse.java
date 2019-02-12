/**
 * GetTestSuiteByIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class GetTestSuiteByIdResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.TestSuiteEntity testsuite;

    public GetTestSuiteByIdResponse() {
    }

    public GetTestSuiteByIdResponse(
           org.wso2.www.php.xsd.TestSuiteEntity testsuite) {
           this.testsuite = testsuite;
    }


    /**
     * Gets the testsuite value for this GetTestSuiteByIdResponse.
     * 
     * @return testsuite
     */
    public org.wso2.www.php.xsd.TestSuiteEntity getTestsuite() {
        return testsuite;
    }


    /**
     * Sets the testsuite value for this GetTestSuiteByIdResponse.
     * 
     * @param testsuite
     */
    public void setTestsuite(org.wso2.www.php.xsd.TestSuiteEntity testsuite) {
        this.testsuite = testsuite;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTestSuiteByIdResponse)) return false;
        GetTestSuiteByIdResponse other = (GetTestSuiteByIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.testsuite==null && other.getTestsuite()==null) || 
             (this.testsuite!=null &&
              this.testsuite.equals(other.getTestsuite())));
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
        if (getTestsuite() != null) {
            _hashCode += getTestsuite().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetTestSuiteByIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteByIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testsuite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testsuite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteEntity"));
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
