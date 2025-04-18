/**
 * GetTestCasesFromJiraDefectIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class GetTestCasesFromJiraDefectIdResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.TestCaseEntity[] testcases;

    public GetTestCasesFromJiraDefectIdResponse() {
    }

    public GetTestCasesFromJiraDefectIdResponse(
           org.wso2.www.php.xsd.TestCaseEntity[] testcases) {
           this.testcases = testcases;
    }


    /**
     * Gets the testcases value for this GetTestCasesFromJiraDefectIdResponse.
     * 
     * @return testcases
     */
    public org.wso2.www.php.xsd.TestCaseEntity[] getTestcases() {
        return testcases;
    }


    /**
     * Sets the testcases value for this GetTestCasesFromJiraDefectIdResponse.
     * 
     * @param testcases
     */
    public void setTestcases(org.wso2.www.php.xsd.TestCaseEntity[] testcases) {
        this.testcases = testcases;
    }

    public org.wso2.www.php.xsd.TestCaseEntity getTestcases(int i) {
        return this.testcases[i];
    }

    public void setTestcases(int i, org.wso2.www.php.xsd.TestCaseEntity _value) {
        this.testcases[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTestCasesFromJiraDefectIdResponse)) return false;
        GetTestCasesFromJiraDefectIdResponse other = (GetTestCasesFromJiraDefectIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.testcases==null && other.getTestcases()==null) || 
             (this.testcases!=null &&
              java.util.Arrays.equals(this.testcases, other.getTestcases())));
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
        if (getTestcases() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestcases());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestcases(), i);
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
        new org.apache.axis.description.TypeDesc(GetTestCasesFromJiraDefectIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestCasesFromJiraDefectIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcases");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseEntity"));
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
