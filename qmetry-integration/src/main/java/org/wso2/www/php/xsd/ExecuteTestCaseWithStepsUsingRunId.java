/**
 * ExecuteTestCaseWithStepsUsingRunId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ExecuteTestCaseWithStepsUsingRunId  implements java.io.Serializable {
    private java.lang.String token;

    private int testcaseRunId;

    private java.lang.String status;

    private java.lang.String comments;

    private org.wso2.www.php.xsd.TestCaseStepRunDetails[] stepRuns;

    public ExecuteTestCaseWithStepsUsingRunId() {
    }

    public ExecuteTestCaseWithStepsUsingRunId(
           java.lang.String token,
           int testcaseRunId,
           java.lang.String status,
           java.lang.String comments,
           org.wso2.www.php.xsd.TestCaseStepRunDetails[] stepRuns) {
           this.token = token;
           this.testcaseRunId = testcaseRunId;
           this.status = status;
           this.comments = comments;
           this.stepRuns = stepRuns;
    }


    /**
     * Gets the token value for this ExecuteTestCaseWithStepsUsingRunId.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this ExecuteTestCaseWithStepsUsingRunId.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the testcaseRunId value for this ExecuteTestCaseWithStepsUsingRunId.
     * 
     * @return testcaseRunId
     */
    public int getTestcaseRunId() {
        return testcaseRunId;
    }


    /**
     * Sets the testcaseRunId value for this ExecuteTestCaseWithStepsUsingRunId.
     * 
     * @param testcaseRunId
     */
    public void setTestcaseRunId(int testcaseRunId) {
        this.testcaseRunId = testcaseRunId;
    }


    /**
     * Gets the status value for this ExecuteTestCaseWithStepsUsingRunId.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ExecuteTestCaseWithStepsUsingRunId.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the comments value for this ExecuteTestCaseWithStepsUsingRunId.
     * 
     * @return comments
     */
    public java.lang.String getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this ExecuteTestCaseWithStepsUsingRunId.
     * 
     * @param comments
     */
    public void setComments(java.lang.String comments) {
        this.comments = comments;
    }


    /**
     * Gets the stepRuns value for this ExecuteTestCaseWithStepsUsingRunId.
     * 
     * @return stepRuns
     */
    public org.wso2.www.php.xsd.TestCaseStepRunDetails[] getStepRuns() {
        return stepRuns;
    }


    /**
     * Sets the stepRuns value for this ExecuteTestCaseWithStepsUsingRunId.
     * 
     * @param stepRuns
     */
    public void setStepRuns(org.wso2.www.php.xsd.TestCaseStepRunDetails[] stepRuns) {
        this.stepRuns = stepRuns;
    }

    public org.wso2.www.php.xsd.TestCaseStepRunDetails getStepRuns(int i) {
        return this.stepRuns[i];
    }

    public void setStepRuns(int i, org.wso2.www.php.xsd.TestCaseStepRunDetails _value) {
        this.stepRuns[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExecuteTestCaseWithStepsUsingRunId)) return false;
        ExecuteTestCaseWithStepsUsingRunId other = (ExecuteTestCaseWithStepsUsingRunId) obj;
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
            this.testcaseRunId == other.getTestcaseRunId() &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              this.comments.equals(other.getComments()))) &&
            ((this.stepRuns==null && other.getStepRuns()==null) || 
             (this.stepRuns!=null &&
              java.util.Arrays.equals(this.stepRuns, other.getStepRuns())));
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
        _hashCode += getTestcaseRunId();
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getComments() != null) {
            _hashCode += getComments().hashCode();
        }
        if (getStepRuns() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStepRuns());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStepRuns(), i);
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
        new org.apache.axis.description.TypeDesc(ExecuteTestCaseWithStepsUsingRunId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">executeTestCaseWithStepsUsingRunId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcaseRunId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcaseRunId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "comments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepRuns");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepRuns"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseStepRunDetails"));
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
