/**
 * TestSuiteRunByPlatform.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestSuiteRunByPlatform  implements java.io.Serializable {
    private int id;

    private java.lang.String name;

    private java.lang.String status;

    private int testsuiteid;

    private java.lang.String testsuitename;

    private java.lang.String assignedTo;

    private int totalTC;

    private int passedTC;

    private int failedTC;

    private int NRTC;

    private int blockedTC;

    private int NATC;

    private java.lang.String executionStartTime;

    private java.lang.String executionEndTime;

    public TestSuiteRunByPlatform() {
    }

    public TestSuiteRunByPlatform(
           int id,
           java.lang.String name,
           java.lang.String status,
           int testsuiteid,
           java.lang.String testsuitename,
           java.lang.String assignedTo,
           int totalTC,
           int passedTC,
           int failedTC,
           int NRTC,
           int blockedTC,
           int NATC,
           java.lang.String executionStartTime,
           java.lang.String executionEndTime) {
           this.id = id;
           this.name = name;
           this.status = status;
           this.testsuiteid = testsuiteid;
           this.testsuitename = testsuitename;
           this.assignedTo = assignedTo;
           this.totalTC = totalTC;
           this.passedTC = passedTC;
           this.failedTC = failedTC;
           this.NRTC = NRTC;
           this.blockedTC = blockedTC;
           this.NATC = NATC;
           this.executionStartTime = executionStartTime;
           this.executionEndTime = executionEndTime;
    }


    /**
     * Gets the id value for this TestSuiteRunByPlatform.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this TestSuiteRunByPlatform.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the name value for this TestSuiteRunByPlatform.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this TestSuiteRunByPlatform.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the status value for this TestSuiteRunByPlatform.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this TestSuiteRunByPlatform.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the testsuiteid value for this TestSuiteRunByPlatform.
     * 
     * @return testsuiteid
     */
    public int getTestsuiteid() {
        return testsuiteid;
    }


    /**
     * Sets the testsuiteid value for this TestSuiteRunByPlatform.
     * 
     * @param testsuiteid
     */
    public void setTestsuiteid(int testsuiteid) {
        this.testsuiteid = testsuiteid;
    }


    /**
     * Gets the testsuitename value for this TestSuiteRunByPlatform.
     * 
     * @return testsuitename
     */
    public java.lang.String getTestsuitename() {
        return testsuitename;
    }


    /**
     * Sets the testsuitename value for this TestSuiteRunByPlatform.
     * 
     * @param testsuitename
     */
    public void setTestsuitename(java.lang.String testsuitename) {
        this.testsuitename = testsuitename;
    }


    /**
     * Gets the assignedTo value for this TestSuiteRunByPlatform.
     * 
     * @return assignedTo
     */
    public java.lang.String getAssignedTo() {
        return assignedTo;
    }


    /**
     * Sets the assignedTo value for this TestSuiteRunByPlatform.
     * 
     * @param assignedTo
     */
    public void setAssignedTo(java.lang.String assignedTo) {
        this.assignedTo = assignedTo;
    }


    /**
     * Gets the totalTC value for this TestSuiteRunByPlatform.
     * 
     * @return totalTC
     */
    public int getTotalTC() {
        return totalTC;
    }


    /**
     * Sets the totalTC value for this TestSuiteRunByPlatform.
     * 
     * @param totalTC
     */
    public void setTotalTC(int totalTC) {
        this.totalTC = totalTC;
    }


    /**
     * Gets the passedTC value for this TestSuiteRunByPlatform.
     * 
     * @return passedTC
     */
    public int getPassedTC() {
        return passedTC;
    }


    /**
     * Sets the passedTC value for this TestSuiteRunByPlatform.
     * 
     * @param passedTC
     */
    public void setPassedTC(int passedTC) {
        this.passedTC = passedTC;
    }


    /**
     * Gets the failedTC value for this TestSuiteRunByPlatform.
     * 
     * @return failedTC
     */
    public int getFailedTC() {
        return failedTC;
    }


    /**
     * Sets the failedTC value for this TestSuiteRunByPlatform.
     * 
     * @param failedTC
     */
    public void setFailedTC(int failedTC) {
        this.failedTC = failedTC;
    }


    /**
     * Gets the NRTC value for this TestSuiteRunByPlatform.
     * 
     * @return NRTC
     */
    public int getNRTC() {
        return NRTC;
    }


    /**
     * Sets the NRTC value for this TestSuiteRunByPlatform.
     * 
     * @param NRTC
     */
    public void setNRTC(int NRTC) {
        this.NRTC = NRTC;
    }


    /**
     * Gets the blockedTC value for this TestSuiteRunByPlatform.
     * 
     * @return blockedTC
     */
    public int getBlockedTC() {
        return blockedTC;
    }


    /**
     * Sets the blockedTC value for this TestSuiteRunByPlatform.
     * 
     * @param blockedTC
     */
    public void setBlockedTC(int blockedTC) {
        this.blockedTC = blockedTC;
    }


    /**
     * Gets the NATC value for this TestSuiteRunByPlatform.
     * 
     * @return NATC
     */
    public int getNATC() {
        return NATC;
    }


    /**
     * Sets the NATC value for this TestSuiteRunByPlatform.
     * 
     * @param NATC
     */
    public void setNATC(int NATC) {
        this.NATC = NATC;
    }


    /**
     * Gets the executionStartTime value for this TestSuiteRunByPlatform.
     * 
     * @return executionStartTime
     */
    public java.lang.String getExecutionStartTime() {
        return executionStartTime;
    }


    /**
     * Sets the executionStartTime value for this TestSuiteRunByPlatform.
     * 
     * @param executionStartTime
     */
    public void setExecutionStartTime(java.lang.String executionStartTime) {
        this.executionStartTime = executionStartTime;
    }


    /**
     * Gets the executionEndTime value for this TestSuiteRunByPlatform.
     * 
     * @return executionEndTime
     */
    public java.lang.String getExecutionEndTime() {
        return executionEndTime;
    }


    /**
     * Sets the executionEndTime value for this TestSuiteRunByPlatform.
     * 
     * @param executionEndTime
     */
    public void setExecutionEndTime(java.lang.String executionEndTime) {
        this.executionEndTime = executionEndTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestSuiteRunByPlatform)) return false;
        TestSuiteRunByPlatform other = (TestSuiteRunByPlatform) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            this.testsuiteid == other.getTestsuiteid() &&
            ((this.testsuitename==null && other.getTestsuitename()==null) || 
             (this.testsuitename!=null &&
              this.testsuitename.equals(other.getTestsuitename()))) &&
            ((this.assignedTo==null && other.getAssignedTo()==null) || 
             (this.assignedTo!=null &&
              this.assignedTo.equals(other.getAssignedTo()))) &&
            this.totalTC == other.getTotalTC() &&
            this.passedTC == other.getPassedTC() &&
            this.failedTC == other.getFailedTC() &&
            this.NRTC == other.getNRTC() &&
            this.blockedTC == other.getBlockedTC() &&
            this.NATC == other.getNATC() &&
            ((this.executionStartTime==null && other.getExecutionStartTime()==null) || 
             (this.executionStartTime!=null &&
              this.executionStartTime.equals(other.getExecutionStartTime()))) &&
            ((this.executionEndTime==null && other.getExecutionEndTime()==null) || 
             (this.executionEndTime!=null &&
              this.executionEndTime.equals(other.getExecutionEndTime())));
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
        _hashCode += getId();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        _hashCode += getTestsuiteid();
        if (getTestsuitename() != null) {
            _hashCode += getTestsuitename().hashCode();
        }
        if (getAssignedTo() != null) {
            _hashCode += getAssignedTo().hashCode();
        }
        _hashCode += getTotalTC();
        _hashCode += getPassedTC();
        _hashCode += getFailedTC();
        _hashCode += getNRTC();
        _hashCode += getBlockedTC();
        _hashCode += getNATC();
        if (getExecutionStartTime() != null) {
            _hashCode += getExecutionStartTime().hashCode();
        }
        if (getExecutionEndTime() != null) {
            _hashCode += getExecutionEndTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TestSuiteRunByPlatform.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteRunByPlatform"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "name"));
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
        elemField.setFieldName("testsuiteid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testsuiteid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testsuitename");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testsuitename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assignedTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "assignedTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalTC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "totalTC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passedTC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "PassedTC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("failedTC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "FailedTC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRTC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "NRTC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blockedTC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "BlockedTC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NATC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "NATC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executionStartTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executionStartTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executionEndTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executionEndTime"));
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
