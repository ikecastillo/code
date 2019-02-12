/**
 * CreateSchedule.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class CreateSchedule  implements java.io.Serializable {
    private java.lang.String token;

    private int testsuiteId;

    private int platformId;

    private java.lang.String scheduleDate;

    private int agentId;

    public CreateSchedule() {
    }

    public CreateSchedule(
           java.lang.String token,
           int testsuiteId,
           int platformId,
           java.lang.String scheduleDate,
           int agentId) {
           this.token = token;
           this.testsuiteId = testsuiteId;
           this.platformId = platformId;
           this.scheduleDate = scheduleDate;
           this.agentId = agentId;
    }


    /**
     * Gets the token value for this CreateSchedule.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this CreateSchedule.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the testsuiteId value for this CreateSchedule.
     * 
     * @return testsuiteId
     */
    public int getTestsuiteId() {
        return testsuiteId;
    }


    /**
     * Sets the testsuiteId value for this CreateSchedule.
     * 
     * @param testsuiteId
     */
    public void setTestsuiteId(int testsuiteId) {
        this.testsuiteId = testsuiteId;
    }


    /**
     * Gets the platformId value for this CreateSchedule.
     * 
     * @return platformId
     */
    public int getPlatformId() {
        return platformId;
    }


    /**
     * Sets the platformId value for this CreateSchedule.
     * 
     * @param platformId
     */
    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }


    /**
     * Gets the scheduleDate value for this CreateSchedule.
     * 
     * @return scheduleDate
     */
    public java.lang.String getScheduleDate() {
        return scheduleDate;
    }


    /**
     * Sets the scheduleDate value for this CreateSchedule.
     * 
     * @param scheduleDate
     */
    public void setScheduleDate(java.lang.String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }


    /**
     * Gets the agentId value for this CreateSchedule.
     * 
     * @return agentId
     */
    public int getAgentId() {
        return agentId;
    }


    /**
     * Sets the agentId value for this CreateSchedule.
     * 
     * @param agentId
     */
    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateSchedule)) return false;
        CreateSchedule other = (CreateSchedule) obj;
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
            this.testsuiteId == other.getTestsuiteId() &&
            this.platformId == other.getPlatformId() &&
            ((this.scheduleDate==null && other.getScheduleDate()==null) || 
             (this.scheduleDate!=null &&
              this.scheduleDate.equals(other.getScheduleDate()))) &&
            this.agentId == other.getAgentId();
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
        _hashCode += getTestsuiteId();
        _hashCode += getPlatformId();
        if (getScheduleDate() != null) {
            _hashCode += getScheduleDate().hashCode();
        }
        _hashCode += getAgentId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateSchedule.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createSchedule"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testsuiteId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testsuiteId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("platformId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "platformId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduleDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "scheduleDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "agentId"));
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
