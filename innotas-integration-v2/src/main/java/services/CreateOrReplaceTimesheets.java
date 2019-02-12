/**
 * CreateOrReplaceTimesheets.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class CreateOrReplaceTimesheets  implements java.io.Serializable {
    private java.lang.String sessionId;

    private services.objects.xsd.Timesheet[] timesheets;

    private java.lang.Boolean autoSubmitAll;

    public CreateOrReplaceTimesheets() {
    }

    public CreateOrReplaceTimesheets(
           java.lang.String sessionId,
           services.objects.xsd.Timesheet[] timesheets,
           java.lang.Boolean autoSubmitAll) {
           this.sessionId = sessionId;
           this.timesheets = timesheets;
           this.autoSubmitAll = autoSubmitAll;
    }


    /**
     * Gets the sessionId value for this CreateOrReplaceTimesheets.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this CreateOrReplaceTimesheets.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the timesheets value for this CreateOrReplaceTimesheets.
     * 
     * @return timesheets
     */
    public services.objects.xsd.Timesheet[] getTimesheets() {
        return timesheets;
    }


    /**
     * Sets the timesheets value for this CreateOrReplaceTimesheets.
     * 
     * @param timesheets
     */
    public void setTimesheets(services.objects.xsd.Timesheet[] timesheets) {
        this.timesheets = timesheets;
    }

    public services.objects.xsd.Timesheet getTimesheets(int i) {
        return this.timesheets[i];
    }

    public void setTimesheets(int i, services.objects.xsd.Timesheet _value) {
        this.timesheets[i] = _value;
    }


    /**
     * Gets the autoSubmitAll value for this CreateOrReplaceTimesheets.
     * 
     * @return autoSubmitAll
     */
    public java.lang.Boolean getAutoSubmitAll() {
        return autoSubmitAll;
    }


    /**
     * Sets the autoSubmitAll value for this CreateOrReplaceTimesheets.
     * 
     * @param autoSubmitAll
     */
    public void setAutoSubmitAll(java.lang.Boolean autoSubmitAll) {
        this.autoSubmitAll = autoSubmitAll;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateOrReplaceTimesheets)) return false;
        CreateOrReplaceTimesheets other = (CreateOrReplaceTimesheets) obj;
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
            ((this.timesheets==null && other.getTimesheets()==null) || 
             (this.timesheets!=null &&
              java.util.Arrays.equals(this.timesheets, other.getTimesheets()))) &&
            ((this.autoSubmitAll==null && other.getAutoSubmitAll()==null) || 
             (this.autoSubmitAll!=null &&
              this.autoSubmitAll.equals(other.getAutoSubmitAll())));
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
        if (getTimesheets() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTimesheets());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTimesheets(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAutoSubmitAll() != null) {
            _hashCode += getAutoSubmitAll().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateOrReplaceTimesheets.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">createOrReplaceTimesheets"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timesheets");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "timesheets"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "Timesheet"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoSubmitAll");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "autoSubmitAll"));
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
