/**
 * TestCaseExecutions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestCaseExecutions  implements java.io.Serializable {
    private org.wso2.www.php.xsd.TestCaseExecution[] testCaseExecution;

    public TestCaseExecutions() {
    }

    public TestCaseExecutions(
           org.wso2.www.php.xsd.TestCaseExecution[] testCaseExecution) {
           this.testCaseExecution = testCaseExecution;
    }


    /**
     * Gets the testCaseExecution value for this TestCaseExecutions.
     * 
     * @return testCaseExecution
     */
    public org.wso2.www.php.xsd.TestCaseExecution[] getTestCaseExecution() {
        return testCaseExecution;
    }


    /**
     * Sets the testCaseExecution value for this TestCaseExecutions.
     * 
     * @param testCaseExecution
     */
    public void setTestCaseExecution(org.wso2.www.php.xsd.TestCaseExecution[] testCaseExecution) {
        this.testCaseExecution = testCaseExecution;
    }

    public org.wso2.www.php.xsd.TestCaseExecution getTestCaseExecution(int i) {
        return this.testCaseExecution[i];
    }

    public void setTestCaseExecution(int i, org.wso2.www.php.xsd.TestCaseExecution _value) {
        this.testCaseExecution[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestCaseExecutions)) return false;
        TestCaseExecutions other = (TestCaseExecutions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.testCaseExecution==null && other.getTestCaseExecution()==null) || 
             (this.testCaseExecution!=null &&
              java.util.Arrays.equals(this.testCaseExecution, other.getTestCaseExecution())));
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
        if (getTestCaseExecution() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestCaseExecution());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestCaseExecution(), i);
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
        new org.apache.axis.description.TypeDesc(TestCaseExecutions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseExecutions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseExecution");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseExecution"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseExecution"));
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
