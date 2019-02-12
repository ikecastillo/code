/**
 * GetTestcaseStepsFromTestcaseIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class GetTestcaseStepsFromTestcaseIdResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.TestCaseStepsEntity[] testcasesteps;

    public GetTestcaseStepsFromTestcaseIdResponse() {
    }

    public GetTestcaseStepsFromTestcaseIdResponse(
           org.wso2.www.php.xsd.TestCaseStepsEntity[] testcasesteps) {
           this.testcasesteps = testcasesteps;
    }


    /**
     * Gets the testcasesteps value for this GetTestcaseStepsFromTestcaseIdResponse.
     * 
     * @return testcasesteps
     */
    public org.wso2.www.php.xsd.TestCaseStepsEntity[] getTestcasesteps() {
        return testcasesteps;
    }


    /**
     * Sets the testcasesteps value for this GetTestcaseStepsFromTestcaseIdResponse.
     * 
     * @param testcasesteps
     */
    public void setTestcasesteps(org.wso2.www.php.xsd.TestCaseStepsEntity[] testcasesteps) {
        this.testcasesteps = testcasesteps;
    }

    public org.wso2.www.php.xsd.TestCaseStepsEntity getTestcasesteps(int i) {
        return this.testcasesteps[i];
    }

    public void setTestcasesteps(int i, org.wso2.www.php.xsd.TestCaseStepsEntity _value) {
        this.testcasesteps[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTestcaseStepsFromTestcaseIdResponse)) return false;
        GetTestcaseStepsFromTestcaseIdResponse other = (GetTestcaseStepsFromTestcaseIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.testcasesteps==null && other.getTestcasesteps()==null) || 
             (this.testcasesteps!=null &&
              java.util.Arrays.equals(this.testcasesteps, other.getTestcasesteps())));
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
        if (getTestcasesteps() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestcasesteps());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestcasesteps(), i);
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
        new org.apache.axis.description.TypeDesc(GetTestcaseStepsFromTestcaseIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestcaseStepsFromTestcaseIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcasesteps");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcasesteps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepsEntity"));
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
