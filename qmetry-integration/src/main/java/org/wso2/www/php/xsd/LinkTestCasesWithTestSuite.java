/**
 * LinkTestCasesWithTestSuite.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class LinkTestCasesWithTestSuite  implements java.io.Serializable {
    private java.lang.String token;

    private int[] testcaseIds;

    private int testsuiteId;

    public LinkTestCasesWithTestSuite() {
    }

    public LinkTestCasesWithTestSuite(
           java.lang.String token,
           int[] testcaseIds,
           int testsuiteId) {
           this.token = token;
           this.testcaseIds = testcaseIds;
           this.testsuiteId = testsuiteId;
    }


    /**
     * Gets the token value for this LinkTestCasesWithTestSuite.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this LinkTestCasesWithTestSuite.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the testcaseIds value for this LinkTestCasesWithTestSuite.
     * 
     * @return testcaseIds
     */
    public int[] getTestcaseIds() {
        return testcaseIds;
    }


    /**
     * Sets the testcaseIds value for this LinkTestCasesWithTestSuite.
     * 
     * @param testcaseIds
     */
    public void setTestcaseIds(int[] testcaseIds) {
        this.testcaseIds = testcaseIds;
    }

    public int getTestcaseIds(int i) {
        return this.testcaseIds[i];
    }

    public void setTestcaseIds(int i, int _value) {
        this.testcaseIds[i] = _value;
    }


    /**
     * Gets the testsuiteId value for this LinkTestCasesWithTestSuite.
     * 
     * @return testsuiteId
     */
    public int getTestsuiteId() {
        return testsuiteId;
    }


    /**
     * Sets the testsuiteId value for this LinkTestCasesWithTestSuite.
     * 
     * @param testsuiteId
     */
    public void setTestsuiteId(int testsuiteId) {
        this.testsuiteId = testsuiteId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LinkTestCasesWithTestSuite)) return false;
        LinkTestCasesWithTestSuite other = (LinkTestCasesWithTestSuite) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken()))) &&
            ((this.testcaseIds==null && other.getTestcaseIds()==null) || 
             (this.testcaseIds!=null &&
              java.util.Arrays.equals(this.testcaseIds, other.getTestcaseIds()))) &&
            this.testsuiteId == other.getTestsuiteId();
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
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        if (getTestcaseIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestcaseIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestcaseIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getTestsuiteId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LinkTestCasesWithTestSuite.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">linkTestCasesWithTestSuite"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcaseIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcaseIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testsuiteId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testsuiteId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
