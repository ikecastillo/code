/**
 * TestCaseStepsEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestCaseStepsEntity  implements java.io.Serializable {
    private int stepId;

    private int testCaseId;

    private int stepNumber;

    private java.lang.String stepName;

    private java.lang.String stepDescription;

    private java.lang.String expectedOutcome;

    private java.lang.String inputData;

    private int testCaseversion;

    private java.lang.String userDefined01;

    private java.lang.String userDefined02;

    private java.lang.String userDefined03;

    private java.lang.String userDefined04;

    private java.lang.String userDefined05;

    private int testCaseVersion;

    private java.lang.String stepStatus;

    private int testCaseStepRunId;

    private java.lang.String actualResults;

    private org.wso2.www.php.xsd.UserDefinedFieldArray[] stepUdfs;

    public TestCaseStepsEntity() {
    }

    public TestCaseStepsEntity(
           int stepId,
           int testCaseId,
           int stepNumber,
           java.lang.String stepName,
           java.lang.String stepDescription,
           java.lang.String expectedOutcome,
           java.lang.String inputData,
           int testCaseversion,
           java.lang.String userDefined01,
           java.lang.String userDefined02,
           java.lang.String userDefined03,
           java.lang.String userDefined04,
           java.lang.String userDefined05,
           int testCaseVersion,
           java.lang.String stepStatus,
           int testCaseStepRunId,
           java.lang.String actualResults,
           org.wso2.www.php.xsd.UserDefinedFieldArray[] stepUdfs) {
           this.stepId = stepId;
           this.testCaseId = testCaseId;
           this.stepNumber = stepNumber;
           this.stepName = stepName;
           this.stepDescription = stepDescription;
           this.expectedOutcome = expectedOutcome;
           this.inputData = inputData;
           this.testCaseversion = testCaseversion;
           this.userDefined01 = userDefined01;
           this.userDefined02 = userDefined02;
           this.userDefined03 = userDefined03;
           this.userDefined04 = userDefined04;
           this.userDefined05 = userDefined05;
           this.testCaseVersion = testCaseVersion;
           this.stepStatus = stepStatus;
           this.testCaseStepRunId = testCaseStepRunId;
           this.actualResults = actualResults;
           this.stepUdfs = stepUdfs;
    }


    /**
     * Gets the stepId value for this TestCaseStepsEntity.
     * 
     * @return stepId
     */
    public int getStepId() {
        return stepId;
    }


    /**
     * Sets the stepId value for this TestCaseStepsEntity.
     * 
     * @param stepId
     */
    public void setStepId(int stepId) {
        this.stepId = stepId;
    }


    /**
     * Gets the testCaseId value for this TestCaseStepsEntity.
     * 
     * @return testCaseId
     */
    public int getTestCaseId() {
        return testCaseId;
    }


    /**
     * Sets the testCaseId value for this TestCaseStepsEntity.
     * 
     * @param testCaseId
     */
    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }


    /**
     * Gets the stepNumber value for this TestCaseStepsEntity.
     * 
     * @return stepNumber
     */
    public int getStepNumber() {
        return stepNumber;
    }


    /**
     * Sets the stepNumber value for this TestCaseStepsEntity.
     * 
     * @param stepNumber
     */
    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }


    /**
     * Gets the stepName value for this TestCaseStepsEntity.
     * 
     * @return stepName
     */
    public java.lang.String getStepName() {
        return stepName;
    }


    /**
     * Sets the stepName value for this TestCaseStepsEntity.
     * 
     * @param stepName
     */
    public void setStepName(java.lang.String stepName) {
        this.stepName = stepName;
    }


    /**
     * Gets the stepDescription value for this TestCaseStepsEntity.
     * 
     * @return stepDescription
     */
    public java.lang.String getStepDescription() {
        return stepDescription;
    }


    /**
     * Sets the stepDescription value for this TestCaseStepsEntity.
     * 
     * @param stepDescription
     */
    public void setStepDescription(java.lang.String stepDescription) {
        this.stepDescription = stepDescription;
    }


    /**
     * Gets the expectedOutcome value for this TestCaseStepsEntity.
     * 
     * @return expectedOutcome
     */
    public java.lang.String getExpectedOutcome() {
        return expectedOutcome;
    }


    /**
     * Sets the expectedOutcome value for this TestCaseStepsEntity.
     * 
     * @param expectedOutcome
     */
    public void setExpectedOutcome(java.lang.String expectedOutcome) {
        this.expectedOutcome = expectedOutcome;
    }


    /**
     * Gets the inputData value for this TestCaseStepsEntity.
     * 
     * @return inputData
     */
    public java.lang.String getInputData() {
        return inputData;
    }


    /**
     * Sets the inputData value for this TestCaseStepsEntity.
     * 
     * @param inputData
     */
    public void setInputData(java.lang.String inputData) {
        this.inputData = inputData;
    }


    /**
     * Gets the testCaseversion value for this TestCaseStepsEntity.
     * 
     * @return testCaseversion
     */
    public int getTestCaseversion() {
        return testCaseversion;
    }


    /**
     * Sets the testCaseversion value for this TestCaseStepsEntity.
     * 
     * @param testCaseversion
     */
    public void setTestCaseversion(int testCaseversion) {
        this.testCaseversion = testCaseversion;
    }


    /**
     * Gets the userDefined01 value for this TestCaseStepsEntity.
     * 
     * @return userDefined01
     */
    public java.lang.String getUserDefined01() {
        return userDefined01;
    }


    /**
     * Sets the userDefined01 value for this TestCaseStepsEntity.
     * 
     * @param userDefined01
     */
    public void setUserDefined01(java.lang.String userDefined01) {
        this.userDefined01 = userDefined01;
    }


    /**
     * Gets the userDefined02 value for this TestCaseStepsEntity.
     * 
     * @return userDefined02
     */
    public java.lang.String getUserDefined02() {
        return userDefined02;
    }


    /**
     * Sets the userDefined02 value for this TestCaseStepsEntity.
     * 
     * @param userDefined02
     */
    public void setUserDefined02(java.lang.String userDefined02) {
        this.userDefined02 = userDefined02;
    }


    /**
     * Gets the userDefined03 value for this TestCaseStepsEntity.
     * 
     * @return userDefined03
     */
    public java.lang.String getUserDefined03() {
        return userDefined03;
    }


    /**
     * Sets the userDefined03 value for this TestCaseStepsEntity.
     * 
     * @param userDefined03
     */
    public void setUserDefined03(java.lang.String userDefined03) {
        this.userDefined03 = userDefined03;
    }


    /**
     * Gets the userDefined04 value for this TestCaseStepsEntity.
     * 
     * @return userDefined04
     */
    public java.lang.String getUserDefined04() {
        return userDefined04;
    }


    /**
     * Sets the userDefined04 value for this TestCaseStepsEntity.
     * 
     * @param userDefined04
     */
    public void setUserDefined04(java.lang.String userDefined04) {
        this.userDefined04 = userDefined04;
    }


    /**
     * Gets the userDefined05 value for this TestCaseStepsEntity.
     * 
     * @return userDefined05
     */
    public java.lang.String getUserDefined05() {
        return userDefined05;
    }


    /**
     * Sets the userDefined05 value for this TestCaseStepsEntity.
     * 
     * @param userDefined05
     */
    public void setUserDefined05(java.lang.String userDefined05) {
        this.userDefined05 = userDefined05;
    }


    /**
     * Gets the testCaseVersion value for this TestCaseStepsEntity.
     * 
     * @return testCaseVersion
     */
    public int getTestCaseVersion() {
        return testCaseVersion;
    }


    /**
     * Sets the testCaseVersion value for this TestCaseStepsEntity.
     * 
     * @param testCaseVersion
     */
    public void setTestCaseVersion(int testCaseVersion) {
        this.testCaseVersion = testCaseVersion;
    }


    /**
     * Gets the stepStatus value for this TestCaseStepsEntity.
     * 
     * @return stepStatus
     */
    public java.lang.String getStepStatus() {
        return stepStatus;
    }


    /**
     * Sets the stepStatus value for this TestCaseStepsEntity.
     * 
     * @param stepStatus
     */
    public void setStepStatus(java.lang.String stepStatus) {
        this.stepStatus = stepStatus;
    }


    /**
     * Gets the testCaseStepRunId value for this TestCaseStepsEntity.
     * 
     * @return testCaseStepRunId
     */
    public int getTestCaseStepRunId() {
        return testCaseStepRunId;
    }


    /**
     * Sets the testCaseStepRunId value for this TestCaseStepsEntity.
     * 
     * @param testCaseStepRunId
     */
    public void setTestCaseStepRunId(int testCaseStepRunId) {
        this.testCaseStepRunId = testCaseStepRunId;
    }


    /**
     * Gets the actualResults value for this TestCaseStepsEntity.
     * 
     * @return actualResults
     */
    public java.lang.String getActualResults() {
        return actualResults;
    }


    /**
     * Sets the actualResults value for this TestCaseStepsEntity.
     * 
     * @param actualResults
     */
    public void setActualResults(java.lang.String actualResults) {
        this.actualResults = actualResults;
    }


    /**
     * Gets the stepUdfs value for this TestCaseStepsEntity.
     * 
     * @return stepUdfs
     */
    public org.wso2.www.php.xsd.UserDefinedFieldArray[] getStepUdfs() {
        return stepUdfs;
    }


    /**
     * Sets the stepUdfs value for this TestCaseStepsEntity.
     * 
     * @param stepUdfs
     */
    public void setStepUdfs(org.wso2.www.php.xsd.UserDefinedFieldArray[] stepUdfs) {
        this.stepUdfs = stepUdfs;
    }

    public org.wso2.www.php.xsd.UserDefinedFieldArray getStepUdfs(int i) {
        return this.stepUdfs[i];
    }

    public void setStepUdfs(int i, org.wso2.www.php.xsd.UserDefinedFieldArray _value) {
        this.stepUdfs[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestCaseStepsEntity)) return false;
        TestCaseStepsEntity other = (TestCaseStepsEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.stepId == other.getStepId() &&
            this.testCaseId == other.getTestCaseId() &&
            this.stepNumber == other.getStepNumber() &&
            ((this.stepName==null && other.getStepName()==null) || 
             (this.stepName!=null &&
              this.stepName.equals(other.getStepName()))) &&
            ((this.stepDescription==null && other.getStepDescription()==null) || 
             (this.stepDescription!=null &&
              this.stepDescription.equals(other.getStepDescription()))) &&
            ((this.expectedOutcome==null && other.getExpectedOutcome()==null) || 
             (this.expectedOutcome!=null &&
              this.expectedOutcome.equals(other.getExpectedOutcome()))) &&
            ((this.inputData==null && other.getInputData()==null) || 
             (this.inputData!=null &&
              this.inputData.equals(other.getInputData()))) &&
            this.testCaseversion == other.getTestCaseversion() &&
            ((this.userDefined01==null && other.getUserDefined01()==null) || 
             (this.userDefined01!=null &&
              this.userDefined01.equals(other.getUserDefined01()))) &&
            ((this.userDefined02==null && other.getUserDefined02()==null) || 
             (this.userDefined02!=null &&
              this.userDefined02.equals(other.getUserDefined02()))) &&
            ((this.userDefined03==null && other.getUserDefined03()==null) || 
             (this.userDefined03!=null &&
              this.userDefined03.equals(other.getUserDefined03()))) &&
            ((this.userDefined04==null && other.getUserDefined04()==null) || 
             (this.userDefined04!=null &&
              this.userDefined04.equals(other.getUserDefined04()))) &&
            ((this.userDefined05==null && other.getUserDefined05()==null) || 
             (this.userDefined05!=null &&
              this.userDefined05.equals(other.getUserDefined05()))) &&
            this.testCaseVersion == other.getTestCaseVersion() &&
            ((this.stepStatus==null && other.getStepStatus()==null) || 
             (this.stepStatus!=null &&
              this.stepStatus.equals(other.getStepStatus()))) &&
            this.testCaseStepRunId == other.getTestCaseStepRunId() &&
            ((this.actualResults==null && other.getActualResults()==null) || 
             (this.actualResults!=null &&
              this.actualResults.equals(other.getActualResults()))) &&
            ((this.stepUdfs==null && other.getStepUdfs()==null) || 
             (this.stepUdfs!=null &&
              java.util.Arrays.equals(this.stepUdfs, other.getStepUdfs())));
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
        _hashCode += getStepId();
        _hashCode += getTestCaseId();
        _hashCode += getStepNumber();
        if (getStepName() != null) {
            _hashCode += getStepName().hashCode();
        }
        if (getStepDescription() != null) {
            _hashCode += getStepDescription().hashCode();
        }
        if (getExpectedOutcome() != null) {
            _hashCode += getExpectedOutcome().hashCode();
        }
        if (getInputData() != null) {
            _hashCode += getInputData().hashCode();
        }
        _hashCode += getTestCaseversion();
        if (getUserDefined01() != null) {
            _hashCode += getUserDefined01().hashCode();
        }
        if (getUserDefined02() != null) {
            _hashCode += getUserDefined02().hashCode();
        }
        if (getUserDefined03() != null) {
            _hashCode += getUserDefined03().hashCode();
        }
        if (getUserDefined04() != null) {
            _hashCode += getUserDefined04().hashCode();
        }
        if (getUserDefined05() != null) {
            _hashCode += getUserDefined05().hashCode();
        }
        _hashCode += getTestCaseVersion();
        if (getStepStatus() != null) {
            _hashCode += getStepStatus().hashCode();
        }
        _hashCode += getTestCaseStepRunId();
        if (getActualResults() != null) {
            _hashCode += getActualResults().hashCode();
        }
        if (getStepUdfs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStepUdfs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStepUdfs(), i);
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
        new org.apache.axis.description.TypeDesc(TestCaseStepsEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepsEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "StepId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "StepNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "StepName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "StepDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expectedOutcome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "ExpectedOutcome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inputData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "InputData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseversion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseversion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userDefined01");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefined01"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userDefined02");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefined02"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userDefined03");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefined03"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userDefined04");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefined04"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userDefined05");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefined05"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "StepStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseStepRunId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepRunId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualResults");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "ActualResults"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepUdfs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepUdfs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefinedFieldArray"));
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
