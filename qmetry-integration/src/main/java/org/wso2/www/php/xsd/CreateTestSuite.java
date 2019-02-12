/**
 * CreateTestSuite.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class CreateTestSuite  implements java.io.Serializable {
    private java.lang.String token;

    private java.lang.String testsuiteName;

    private java.lang.String testsuiteDesc;

    private int parentFolderId;

    private org.wso2.www.php.xsd.KeyValuePair[] udfInfo;

    public CreateTestSuite() {
    }

    public CreateTestSuite(
           java.lang.String token,
           java.lang.String testsuiteName,
           java.lang.String testsuiteDesc,
           int parentFolderId,
           org.wso2.www.php.xsd.KeyValuePair[] udfInfo) {
           this.token = token;
           this.testsuiteName = testsuiteName;
           this.testsuiteDesc = testsuiteDesc;
           this.parentFolderId = parentFolderId;
           this.udfInfo = udfInfo;
    }


    /**
     * Gets the token value for this CreateTestSuite.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this CreateTestSuite.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the testsuiteName value for this CreateTestSuite.
     * 
     * @return testsuiteName
     */
    public java.lang.String getTestsuiteName() {
        return testsuiteName;
    }


    /**
     * Sets the testsuiteName value for this CreateTestSuite.
     * 
     * @param testsuiteName
     */
    public void setTestsuiteName(java.lang.String testsuiteName) {
        this.testsuiteName = testsuiteName;
    }


    /**
     * Gets the testsuiteDesc value for this CreateTestSuite.
     * 
     * @return testsuiteDesc
     */
    public java.lang.String getTestsuiteDesc() {
        return testsuiteDesc;
    }


    /**
     * Sets the testsuiteDesc value for this CreateTestSuite.
     * 
     * @param testsuiteDesc
     */
    public void setTestsuiteDesc(java.lang.String testsuiteDesc) {
        this.testsuiteDesc = testsuiteDesc;
    }


    /**
     * Gets the parentFolderId value for this CreateTestSuite.
     * 
     * @return parentFolderId
     */
    public int getParentFolderId() {
        return parentFolderId;
    }


    /**
     * Sets the parentFolderId value for this CreateTestSuite.
     * 
     * @param parentFolderId
     */
    public void setParentFolderId(int parentFolderId) {
        this.parentFolderId = parentFolderId;
    }


    /**
     * Gets the udfInfo value for this CreateTestSuite.
     * 
     * @return udfInfo
     */
    public org.wso2.www.php.xsd.KeyValuePair[] getUdfInfo() {
        return udfInfo;
    }


    /**
     * Sets the udfInfo value for this CreateTestSuite.
     * 
     * @param udfInfo
     */
    public void setUdfInfo(org.wso2.www.php.xsd.KeyValuePair[] udfInfo) {
        this.udfInfo = udfInfo;
    }

    public org.wso2.www.php.xsd.KeyValuePair getUdfInfo(int i) {
        return this.udfInfo[i];
    }

    public void setUdfInfo(int i, org.wso2.www.php.xsd.KeyValuePair _value) {
        this.udfInfo[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateTestSuite)) return false;
        CreateTestSuite other = (CreateTestSuite) obj;
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
            ((this.testsuiteName==null && other.getTestsuiteName()==null) || 
             (this.testsuiteName!=null &&
              this.testsuiteName.equals(other.getTestsuiteName()))) &&
            ((this.testsuiteDesc==null && other.getTestsuiteDesc()==null) || 
             (this.testsuiteDesc!=null &&
              this.testsuiteDesc.equals(other.getTestsuiteDesc()))) &&
            this.parentFolderId == other.getParentFolderId() &&
            ((this.udfInfo==null && other.getUdfInfo()==null) || 
             (this.udfInfo!=null &&
              java.util.Arrays.equals(this.udfInfo, other.getUdfInfo())));
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
        if (getTestsuiteName() != null) {
            _hashCode += getTestsuiteName().hashCode();
        }
        if (getTestsuiteDesc() != null) {
            _hashCode += getTestsuiteDesc().hashCode();
        }
        _hashCode += getParentFolderId();
        if (getUdfInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUdfInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUdfInfo(), i);
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
        new org.apache.axis.description.TypeDesc(CreateTestSuite.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestSuite"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testsuiteName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testsuiteName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testsuiteDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testsuiteDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentFolderId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "parentFolderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfInfo"));
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
