/**
 * TestCaseStepExecutions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestCaseStepExecutions  implements java.io.Serializable {
    private org.wso2.www.php.xsd.TestCaseStepExecution[] testCaseStepExecution;

    public TestCaseStepExecutions() {
    }

    public TestCaseStepExecutions(
           org.wso2.www.php.xsd.TestCaseStepExecution[] testCaseStepExecution) {
           this.testCaseStepExecution = testCaseStepExecution;
    }


    /**
     * Gets the testCaseStepExecution value for this TestCaseStepExecutions.
     * 
     * @return testCaseStepExecution
     */
    public org.wso2.www.php.xsd.TestCaseStepExecution[] getTestCaseStepExecution() {
        return testCaseStepExecution;
    }


    /**
     * Sets the testCaseStepExecution value for this TestCaseStepExecutions.
     * 
     * @param testCaseStepExecution
     */
    public void setTestCaseStepExecution(org.wso2.www.php.xsd.TestCaseStepExecution[] testCaseStepExecution) {
        this.testCaseStepExecution = testCaseStepExecution;
    }

    public org.wso2.www.php.xsd.TestCaseStepExecution getTestCaseStepExecution(int i) {
        return this.testCaseStepExecution[i];
    }

    public void setTestCaseStepExecution(int i, org.wso2.www.php.xsd.TestCaseStepExecution _value) {
        this.testCaseStepExecution[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestCaseStepExecutions)) return false;
        TestCaseStepExecutions other = (TestCaseStepExecutions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.testCaseStepExecution==null && other.getTestCaseStepExecution()==null) || 
             (this.testCaseStepExecution!=null &&
              java.util.Arrays.equals(this.testCaseStepExecution, other.getTestCaseStepExecution())));
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
        if (getTestCaseStepExecution() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestCaseStepExecution());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestCaseStepExecution(), i);
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
        new org.apache.axis.description.TypeDesc(TestCaseStepExecutions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepExecutions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseStepExecution");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseStepExecution"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepExecution"));
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
