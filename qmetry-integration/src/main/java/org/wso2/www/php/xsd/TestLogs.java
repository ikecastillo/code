/**
 * TestLogs.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestLogs  implements java.io.Serializable {
    private org.wso2.www.php.xsd.AttachmentEntity[] testLog;

    public TestLogs() {
    }

    public TestLogs(
           org.wso2.www.php.xsd.AttachmentEntity[] testLog) {
           this.testLog = testLog;
    }


    /**
     * Gets the testLog value for this TestLogs.
     * 
     * @return testLog
     */
    public org.wso2.www.php.xsd.AttachmentEntity[] getTestLog() {
        return testLog;
    }


    /**
     * Sets the testLog value for this TestLogs.
     * 
     * @param testLog
     */
    public void setTestLog(org.wso2.www.php.xsd.AttachmentEntity[] testLog) {
        this.testLog = testLog;
    }

    public org.wso2.www.php.xsd.AttachmentEntity getTestLog(int i) {
        return this.testLog[i];
    }

    public void setTestLog(int i, org.wso2.www.php.xsd.AttachmentEntity _value) {
        this.testLog[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestLogs)) return false;
        TestLogs other = (TestLogs) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.testLog==null && other.getTestLog()==null) || 
             (this.testLog!=null &&
              java.util.Arrays.equals(this.testLog, other.getTestLog())));
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
        if (getTestLog() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestLog());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestLog(), i);
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
        new org.apache.axis.description.TypeDesc(TestLogs.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestLogs"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testLog");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testLog"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "AttachmentEntity"));
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
