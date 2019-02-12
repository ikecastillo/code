/**
 * GetTestDataVariablesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class GetTestDataVariablesResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.KeyValuePair[] testData;

    public GetTestDataVariablesResponse() {
    }

    public GetTestDataVariablesResponse(
           org.wso2.www.php.xsd.KeyValuePair[] testData) {
           this.testData = testData;
    }


    /**
     * Gets the testData value for this GetTestDataVariablesResponse.
     * 
     * @return testData
     */
    public org.wso2.www.php.xsd.KeyValuePair[] getTestData() {
        return testData;
    }


    /**
     * Sets the testData value for this GetTestDataVariablesResponse.
     * 
     * @param testData
     */
    public void setTestData(org.wso2.www.php.xsd.KeyValuePair[] testData) {
        this.testData = testData;
    }

    public org.wso2.www.php.xsd.KeyValuePair getTestData(int i) {
        return this.testData[i];
    }

    public void setTestData(int i, org.wso2.www.php.xsd.KeyValuePair _value) {
        this.testData[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTestDataVariablesResponse)) return false;
        GetTestDataVariablesResponse other = (GetTestDataVariablesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.testData==null && other.getTestData()==null) || 
             (this.testData!=null &&
              java.util.Arrays.equals(this.testData, other.getTestData())));
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
        if (getTestData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestData(), i);
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
        new org.apache.axis.description.TypeDesc(GetTestDataVariablesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getTestDataVariablesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "KeyValuePair"));
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
