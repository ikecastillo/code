/**
 * CreateOrReplaceTimesheetEntries.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class CreateOrReplaceTimesheetEntries  implements java.io.Serializable {
    private java.lang.String sessionId;

    private services.objects.xsd.Timesheet timesheet;

    private java.lang.Boolean autoSubmit;

    public CreateOrReplaceTimesheetEntries() {
    }

    public CreateOrReplaceTimesheetEntries(
           java.lang.String sessionId,
           services.objects.xsd.Timesheet timesheet,
           java.lang.Boolean autoSubmit) {
           this.sessionId = sessionId;
           this.timesheet = timesheet;
           this.autoSubmit = autoSubmit;
    }


    /**
     * Gets the sessionId value for this CreateOrReplaceTimesheetEntries.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this CreateOrReplaceTimesheetEntries.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the timesheet value for this CreateOrReplaceTimesheetEntries.
     * 
     * @return timesheet
     */
    public services.objects.xsd.Timesheet getTimesheet() {
        return timesheet;
    }


    /**
     * Sets the timesheet value for this CreateOrReplaceTimesheetEntries.
     * 
     * @param timesheet
     */
    public void setTimesheet(services.objects.xsd.Timesheet timesheet) {
        this.timesheet = timesheet;
    }


    /**
     * Gets the autoSubmit value for this CreateOrReplaceTimesheetEntries.
     * 
     * @return autoSubmit
     */
    public java.lang.Boolean getAutoSubmit() {
        return autoSubmit;
    }


    /**
     * Sets the autoSubmit value for this CreateOrReplaceTimesheetEntries.
     * 
     * @param autoSubmit
     */
    public void setAutoSubmit(java.lang.Boolean autoSubmit) {
        this.autoSubmit = autoSubmit;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateOrReplaceTimesheetEntries)) return false;
        CreateOrReplaceTimesheetEntries other = (CreateOrReplaceTimesheetEntries) obj;
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
            ((this.timesheet==null && other.getTimesheet()==null) || 
             (this.timesheet!=null &&
              this.timesheet.equals(other.getTimesheet()))) &&
            ((this.autoSubmit==null && other.getAutoSubmit()==null) || 
             (this.autoSubmit!=null &&
              this.autoSubmit.equals(other.getAutoSubmit())));
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
        if (getTimesheet() != null) {
            _hashCode += getTimesheet().hashCode();
        }
        if (getAutoSubmit() != null) {
            _hashCode += getAutoSubmit().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateOrReplaceTimesheetEntries.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">createOrReplaceTimesheetEntries"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timesheet");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "timesheet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "Timesheet"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoSubmit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "autoSubmit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
