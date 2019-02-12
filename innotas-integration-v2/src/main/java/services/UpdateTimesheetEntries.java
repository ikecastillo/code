/**
 * UpdateTimesheetEntries.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class UpdateTimesheetEntries  implements java.io.Serializable {
    private java.lang.String sessionId;

    private services.objects.xsd.TimesheetEntry[] timesheetEntries;

    public UpdateTimesheetEntries() {
    }

    public UpdateTimesheetEntries(
           java.lang.String sessionId,
           services.objects.xsd.TimesheetEntry[] timesheetEntries) {
           this.sessionId = sessionId;
           this.timesheetEntries = timesheetEntries;
    }


    /**
     * Gets the sessionId value for this UpdateTimesheetEntries.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this UpdateTimesheetEntries.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the timesheetEntries value for this UpdateTimesheetEntries.
     * 
     * @return timesheetEntries
     */
    public services.objects.xsd.TimesheetEntry[] getTimesheetEntries() {
        return timesheetEntries;
    }


    /**
     * Sets the timesheetEntries value for this UpdateTimesheetEntries.
     * 
     * @param timesheetEntries
     */
    public void setTimesheetEntries(services.objects.xsd.TimesheetEntry[] timesheetEntries) {
        this.timesheetEntries = timesheetEntries;
    }

    public services.objects.xsd.TimesheetEntry getTimesheetEntries(int i) {
        return this.timesheetEntries[i];
    }

    public void setTimesheetEntries(int i, services.objects.xsd.TimesheetEntry _value) {
        this.timesheetEntries[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateTimesheetEntries)) return false;
        UpdateTimesheetEntries other = (UpdateTimesheetEntries) obj;
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
            ((this.timesheetEntries==null && other.getTimesheetEntries()==null) || 
             (this.timesheetEntries!=null &&
              java.util.Arrays.equals(this.timesheetEntries, other.getTimesheetEntries())));
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
        if (getTimesheetEntries() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTimesheetEntries());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTimesheetEntries(), i);
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
        new org.apache.axis.description.TypeDesc(UpdateTimesheetEntries.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">updateTimesheetEntries"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timesheetEntries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "timesheetEntries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "TimesheetEntry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
