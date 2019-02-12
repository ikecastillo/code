/**
 * GetTimesheetVariables.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class GetTimesheetVariables  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long userId;

    private java.lang.Long periodId;

    public GetTimesheetVariables() {
    }

    public GetTimesheetVariables(
           java.lang.String sessionId,
           java.lang.Long userId,
           java.lang.Long periodId) {
           this.sessionId = sessionId;
           this.userId = userId;
           this.periodId = periodId;
    }


    /**
     * Gets the sessionId value for this GetTimesheetVariables.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this GetTimesheetVariables.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the userId value for this GetTimesheetVariables.
     * 
     * @return userId
     */
    public java.lang.Long getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this GetTimesheetVariables.
     * 
     * @param userId
     */
    public void setUserId(java.lang.Long userId) {
        this.userId = userId;
    }


    /**
     * Gets the periodId value for this GetTimesheetVariables.
     * 
     * @return periodId
     */
    public java.lang.Long getPeriodId() {
        return periodId;
    }


    /**
     * Sets the periodId value for this GetTimesheetVariables.
     * 
     * @param periodId
     */
    public void setPeriodId(java.lang.Long periodId) {
        this.periodId = periodId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTimesheetVariables)) return false;
        GetTimesheetVariables other = (GetTimesheetVariables) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sessionId==null && other.getSessionId()==null) || 
             (this.sessionId!=null &&
              this.sessionId.equals(other.getSessionId()))) &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.periodId==null && other.getPeriodId()==null) || 
             (this.periodId!=null &&
              this.periodId.equals(other.getPeriodId())));
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
        if (getSessionId() != null) {
            _hashCode += getSessionId().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getPeriodId() != null) {
            _hashCode += getPeriodId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetTimesheetVariables.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">getTimesheetVariables"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "periodId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
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
