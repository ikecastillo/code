/**
 * CreateTestCaseStep.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class CreateTestCaseStep  implements java.io.Serializable {
    private java.lang.String token;

    private int testcaseId;

    private java.lang.String stepName;

    private java.lang.String stepDesc;

    private java.lang.String expectedResult;

    private java.lang.String inputData;

    private org.wso2.www.php.xsd.KeyValuePair[] udfInfo;

    public CreateTestCaseStep() {
    }

    public CreateTestCaseStep(
           java.lang.String token,
           int testcaseId,
           java.lang.String stepName,
           java.lang.String stepDesc,
           java.lang.String expectedResult,
           java.lang.String inputData,
           org.wso2.www.php.xsd.KeyValuePair[] udfInfo) {
           this.token = token;
           this.testcaseId = testcaseId;
           this.stepName = stepName;
           this.stepDesc = stepDesc;
           this.expectedResult = expectedResult;
           this.inputData = inputData;
           this.udfInfo = udfInfo;
    }


    /**
     * Gets the token value for this CreateTestCaseStep.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this CreateTestCaseStep.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the testcaseId value for this CreateTestCaseStep.
     * 
     * @return testcaseId
     */
    public int getTestcaseId() {
        return testcaseId;
    }


    /**
     * Sets the testcaseId value for this CreateTestCaseStep.
     * 
     * @param testcaseId
     */
    public void setTestcaseId(int testcaseId) {
        this.testcaseId = testcaseId;
    }


    /**
     * Gets the stepName value for this CreateTestCaseStep.
     * 
     * @return stepName
     */
    public java.lang.String getStepName() {
        return stepName;
    }


    /**
     * Sets the stepName value for this CreateTestCaseStep.
     * 
     * @param stepName
     */
    public void setStepName(java.lang.String stepName) {
        this.stepName = stepName;
    }


    /**
     * Gets the stepDesc value for this CreateTestCaseStep.
     * 
     * @return stepDesc
     */
    public java.lang.String getStepDesc() {
        return stepDesc;
    }


    /**
     * Sets the stepDesc value for this CreateTestCaseStep.
     * 
     * @param stepDesc
     */
    public void setStepDesc(java.lang.String stepDesc) {
        this.stepDesc = stepDesc;
    }


    /**
     * Gets the expectedResult value for this CreateTestCaseStep.
     * 
     * @return expectedResult
     */
    public java.lang.String getExpectedResult() {
        return expectedResult;
    }


    /**
     * Sets the expectedResult value for this CreateTestCaseStep.
     * 
     * @param expectedResult
     */
    public void setExpectedResult(java.lang.String expectedResult) {
        this.expectedResult = expectedResult;
    }


    /**
     * Gets the inputData value for this CreateTestCaseStep.
     * 
     * @return inputData
     */
    public java.lang.String getInputData() {
        return inputData;
    }


    /**
     * Sets the inputData value for this CreateTestCaseStep.
     * 
     * @param inputData
     */
    public void setInputData(java.lang.String inputData) {
        this.inputData = inputData;
    }


    /**
     * Gets the udfInfo value for this CreateTestCaseStep.
     * 
     * @return udfInfo
     */
    public org.wso2.www.php.xsd.KeyValuePair[] getUdfInfo() {
        return udfInfo;
    }


    /**
     * Sets the udfInfo value for this CreateTestCaseStep.
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
        if (!(obj instanceof CreateTestCaseStep)) return false;
        CreateTestCaseStep other = (CreateTestCaseStep) obj;
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
            this.testcaseId == other.getTestcaseId() &&
            ((this.stepName==null && other.getStepName()==null) || 
             (this.stepName!=null &&
              this.stepName.equals(other.getStepName()))) &&
            ((this.stepDesc==null && other.getStepDesc()==null) || 
             (this.stepDesc!=null &&
              this.stepDesc.equals(other.getStepDesc()))) &&
            ((this.expectedResult==null && other.getExpectedResult()==null) || 
             (this.expectedResult!=null &&
              this.expectedResult.equals(other.getExpectedResult()))) &&
            ((this.inputData==null && other.getInputData()==null) || 
             (this.inputData!=null &&
              this.inputData.equals(other.getInputData()))) &&
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
        _hashCode += getTestcaseId();
        if (getStepName() != null) {
            _hashCode += getStepName().hashCode();
        }
        if (getStepDesc() != null) {
            _hashCode += getStepDesc().hashCode();
        }
        if (getExpectedResult() != null) {
            _hashCode += getExpectedResult().hashCode();
        }
        if (getInputData() != null) {
            _hashCode += getInputData().hashCode();
        }
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
        new org.apache.axis.description.TypeDesc(CreateTestCaseStep.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createTestCaseStep"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcaseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcaseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expectedResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "expectedResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inputData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "inputData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
