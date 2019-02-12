/**
 * TestCaseRunDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestCaseRunDetails  implements java.io.Serializable {
    private int testcaseid;

    private int testcaserunid;

    private java.lang.String testcasename;

    private java.lang.String status;

    private java.lang.String commentTestCase;

    public TestCaseRunDetails() {
    }

    public TestCaseRunDetails(
           int testcaseid,
           int testcaserunid,
           java.lang.String testcasename,
           java.lang.String status,
           java.lang.String commentTestCase) {
           this.testcaseid = testcaseid;
           this.testcaserunid = testcaserunid;
           this.testcasename = testcasename;
           this.status = status;
           this.commentTestCase = commentTestCase;
    }


    /**
     * Gets the testcaseid value for this TestCaseRunDetails.
     * 
     * @return testcaseid
     */
    public int getTestcaseid() {
        return testcaseid;
    }


    /**
     * Sets the testcaseid value for this TestCaseRunDetails.
     * 
     * @param testcaseid
     */
    public void setTestcaseid(int testcaseid) {
        this.testcaseid = testcaseid;
    }


    /**
     * Gets the testcaserunid value for this TestCaseRunDetails.
     * 
     * @return testcaserunid
     */
    public int getTestcaserunid() {
        return testcaserunid;
    }


    /**
     * Sets the testcaserunid value for this TestCaseRunDetails.
     * 
     * @param testcaserunid
     */
    public void setTestcaserunid(int testcaserunid) {
        this.testcaserunid = testcaserunid;
    }


    /**
     * Gets the testcasename value for this TestCaseRunDetails.
     * 
     * @return testcasename
     */
    public java.lang.String getTestcasename() {
        return testcasename;
    }


    /**
     * Sets the testcasename value for this TestCaseRunDetails.
     * 
     * @param testcasename
     */
    public void setTestcasename(java.lang.String testcasename) {
        this.testcasename = testcasename;
    }


    /**
     * Gets the status value for this TestCaseRunDetails.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this TestCaseRunDetails.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the commentTestCase value for this TestCaseRunDetails.
     * 
     * @return commentTestCase
     */
    public java.lang.String getCommentTestCase() {
        return commentTestCase;
    }


    /**
     * Sets the commentTestCase value for this TestCaseRunDetails.
     * 
     * @param commentTestCase
     */
    public void setCommentTestCase(java.lang.String commentTestCase) {
        this.commentTestCase = commentTestCase;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestCaseRunDetails)) return false;
        TestCaseRunDetails other = (TestCaseRunDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.testcaseid == other.getTestcaseid() &&
            this.testcaserunid == other.getTestcaserunid() &&
            ((this.testcasename==null && other.getTestcasename()==null) || 
             (this.testcasename!=null &&
              this.testcasename.equals(other.getTestcasename()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.commentTestCase==null && other.getCommentTestCase()==null) || 
             (this.commentTestCase!=null &&
              this.commentTestCase.equals(other.getCommentTestCase())));
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
        _hashCode += getTestcaseid();
        _hashCode += getTestcaserunid();
        if (getTestcasename() != null) {
            _hashCode += getTestcasename().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getCommentTestCase() != null) {
            _hashCode += getCommentTestCase().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TestCaseRunDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseRunDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcaseid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcaseid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcaserunid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcaserunid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testcasename");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testcasename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commentTestCase");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "commentTestCase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
