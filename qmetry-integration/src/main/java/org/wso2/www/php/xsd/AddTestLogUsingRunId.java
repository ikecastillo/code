/**
 * AddTestLogUsingRunId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class AddTestLogUsingRunId  implements java.io.Serializable {
    private java.lang.String token;

    private int testCaseRunId;

    private int stepId;

    private java.lang.String fileName;

    private java.lang.String description;

    private java.lang.String content;

    private java.lang.String attachmentType;

    public AddTestLogUsingRunId() {
    }

    public AddTestLogUsingRunId(
           java.lang.String token,
           int testCaseRunId,
           int stepId,
           java.lang.String fileName,
           java.lang.String description,
           java.lang.String content,
           java.lang.String attachmentType) {
           this.token = token;
           this.testCaseRunId = testCaseRunId;
           this.stepId = stepId;
           this.fileName = fileName;
           this.description = description;
           this.content = content;
           this.attachmentType = attachmentType;
    }


    /**
     * Gets the token value for this AddTestLogUsingRunId.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this AddTestLogUsingRunId.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the testCaseRunId value for this AddTestLogUsingRunId.
     * 
     * @return testCaseRunId
     */
    public int getTestCaseRunId() {
        return testCaseRunId;
    }


    /**
     * Sets the testCaseRunId value for this AddTestLogUsingRunId.
     * 
     * @param testCaseRunId
     */
    public void setTestCaseRunId(int testCaseRunId) {
        this.testCaseRunId = testCaseRunId;
    }


    /**
     * Gets the stepId value for this AddTestLogUsingRunId.
     * 
     * @return stepId
     */
    public int getStepId() {
        return stepId;
    }


    /**
     * Sets the stepId value for this AddTestLogUsingRunId.
     * 
     * @param stepId
     */
    public void setStepId(int stepId) {
        this.stepId = stepId;
    }


    /**
     * Gets the fileName value for this AddTestLogUsingRunId.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this AddTestLogUsingRunId.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the description value for this AddTestLogUsingRunId.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this AddTestLogUsingRunId.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the content value for this AddTestLogUsingRunId.
     * 
     * @return content
     */
    public java.lang.String getContent() {
        return content;
    }


    /**
     * Sets the content value for this AddTestLogUsingRunId.
     * 
     * @param content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }


    /**
     * Gets the attachmentType value for this AddTestLogUsingRunId.
     * 
     * @return attachmentType
     */
    public java.lang.String getAttachmentType() {
        return attachmentType;
    }


    /**
     * Sets the attachmentType value for this AddTestLogUsingRunId.
     * 
     * @param attachmentType
     */
    public void setAttachmentType(java.lang.String attachmentType) {
        this.attachmentType = attachmentType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddTestLogUsingRunId)) return false;
        AddTestLogUsingRunId other = (AddTestLogUsingRunId) obj;
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
            this.testCaseRunId == other.getTestCaseRunId() &&
            this.stepId == other.getStepId() &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent()))) &&
            ((this.attachmentType==null && other.getAttachmentType()==null) || 
             (this.attachmentType!=null &&
              this.attachmentType.equals(other.getAttachmentType())));
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
        _hashCode += getTestCaseRunId();
        _hashCode += getStepId();
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        if (getAttachmentType() != null) {
            _hashCode += getAttachmentType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddTestLogUsingRunId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">addTestLogUsingRunId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testCaseRunId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseRunId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "stepId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "fileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "attachmentType"));
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
