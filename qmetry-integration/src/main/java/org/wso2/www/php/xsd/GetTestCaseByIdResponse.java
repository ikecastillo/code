/**
 * GetTestCaseByIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class GetTestCaseByIdResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.TestCaseEntity testcase;

    public GetTestCaseByIdResponse() {
    }

    public GetTestCaseByIdResponse(
           org.wso2.www.php.xsd.TestCaseEntity testcase) {
           this.testcase = testcase;
    }


    /**
     * Gets the testcase value for this GetTestCaseByIdResponse.
     * 
     * @return testcase
     */
    public org.wso2.www.php.xsd.TestCaseEntity getTestcase() {
        return testcase;
    }


    /**
     * Sets the testcase value for this GetTestCaseByIdResponse.
     * 
     * @param testcase
     */
    public void setTestcase(org.wso2.www.php.xsd.TestCaseEntity testcase) {
        this.testcase = testcase;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTestCaseByIdResponse)) return false;
        GetTestCaseByIdResponse other = (GetTestCaseByIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.testcase==null && other.getTestcase()==null) || 
             (this.testcase!=null &&
              this.testcase.equals(other.getTestcase())));
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
        if (getTestcase() != null) {
            _hashCode += getTestcase().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetTestCaseByIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCaseByIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcase");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseEntity"));
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
