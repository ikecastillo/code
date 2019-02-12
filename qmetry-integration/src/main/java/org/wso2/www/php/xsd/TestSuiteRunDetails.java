/**
 * TestSuiteRunDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestSuiteRunDetails  implements java.io.Serializable {
    private org.wso2.www.php.xsd.TestSuiteRunByPlatform platform;

    private org.wso2.www.php.xsd.TestCaseRunDetails[] details;

    public TestSuiteRunDetails() {
    }

    public TestSuiteRunDetails(
           org.wso2.www.php.xsd.TestSuiteRunByPlatform platform,
           org.wso2.www.php.xsd.TestCaseRunDetails[] details) {
           this.platform = platform;
           this.details = details;
    }


    /**
     * Gets the platform value for this TestSuiteRunDetails.
     * 
     * @return platform
     */
    public org.wso2.www.php.xsd.TestSuiteRunByPlatform getPlatform() {
        return platform;
    }


    /**
     * Sets the platform value for this TestSuiteRunDetails.
     * 
     * @param platform
     */
    public void setPlatform(org.wso2.www.php.xsd.TestSuiteRunByPlatform platform) {
        this.platform = platform;
    }


    /**
     * Gets the details value for this TestSuiteRunDetails.
     * 
     * @return details
     */
    public org.wso2.www.php.xsd.TestCaseRunDetails[] getDetails() {
        return details;
    }


    /**
     * Sets the details value for this TestSuiteRunDetails.
     * 
     * @param details
     */
    public void setDetails(org.wso2.www.php.xsd.TestCaseRunDetails[] details) {
        this.details = details;
    }

    public org.wso2.www.php.xsd.TestCaseRunDetails getDetails(int i) {
        return this.details[i];
    }

    public void setDetails(int i, org.wso2.www.php.xsd.TestCaseRunDetails _value) {
        this.details[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestSuiteRunDetails)) return false;
        TestSuiteRunDetails other = (TestSuiteRunDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.platform==null && other.getPlatform()==null) || 
             (this.platform!=null &&
              this.platform.equals(other.getPlatform()))) &&
            ((this.details==null && other.getDetails()==null) || 
             (this.details!=null &&
              java.util.Arrays.equals(this.details, other.getDetails())));
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
        if (getPlatform() != null) {
            _hashCode += getPlatform().hashCode();
        }
        if (getDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetails(), i);
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
        new org.apache.axis.description.TypeDesc(TestSuiteRunDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteRunDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("platform");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "platform"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteRunByPlatform"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseRunDetails"));
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
