/**
 * GetTestSuiteStatusByPlatformResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class GetTestSuiteStatusByPlatformResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.TestSuiteRunDetails[] testSuiteExecutionDetails;

    public GetTestSuiteStatusByPlatformResponse() {
    }

    public GetTestSuiteStatusByPlatformResponse(
           org.wso2.www.php.xsd.TestSuiteRunDetails[] testSuiteExecutionDetails) {
           this.testSuiteExecutionDetails = testSuiteExecutionDetails;
    }


    /**
     * Gets the testSuiteExecutionDetails value for this GetTestSuiteStatusByPlatformResponse.
     * 
     * @return testSuiteExecutionDetails
     */
    public org.wso2.www.php.xsd.TestSuiteRunDetails[] getTestSuiteExecutionDetails() {
        return testSuiteExecutionDetails;
    }


    /**
     * Sets the testSuiteExecutionDetails value for this GetTestSuiteStatusByPlatformResponse.
     * 
     * @param testSuiteExecutionDetails
     */
    public void setTestSuiteExecutionDetails(org.wso2.www.php.xsd.TestSuiteRunDetails[] testSuiteExecutionDetails) {
        this.testSuiteExecutionDetails = testSuiteExecutionDetails;
    }

    public org.wso2.www.php.xsd.TestSuiteRunDetails getTestSuiteExecutionDetails(int i) {
        return this.testSuiteExecutionDetails[i];
    }

    public void setTestSuiteExecutionDetails(int i, org.wso2.www.php.xsd.TestSuiteRunDetails _value) {
        this.testSuiteExecutionDetails[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTestSuiteStatusByPlatformResponse)) return false;
        GetTestSuiteStatusByPlatformResponse other = (GetTestSuiteStatusByPlatformResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.testSuiteExecutionDetails==null && other.getTestSuiteExecutionDetails()==null) || 
             (this.testSuiteExecutionDetails!=null &&
              java.util.Arrays.equals(this.testSuiteExecutionDetails, other.getTestSuiteExecutionDetails())));
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
        if (getTestSuiteExecutionDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestSuiteExecutionDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestSuiteExecutionDetails(), i);
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
        new org.apache.axis.description.TypeDesc(GetTestSuiteStatusByPlatformResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestSuiteStatusByPlatformResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testSuiteExecutionDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testSuiteExecutionDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteRunDetails"));
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
