/**
 * DeleteTimesheetEntries.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class DeleteTimesheetEntries  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long timesheetId;

    private long[] timesheetEntryId;

    public DeleteTimesheetEntries() {
    }

    public DeleteTimesheetEntries(
           java.lang.String sessionId,
           java.lang.Long timesheetId,
           long[] timesheetEntryId) {
           this.sessionId = sessionId;
           this.timesheetId = timesheetId;
           this.timesheetEntryId = timesheetEntryId;
    }


    /**
     * Gets the sessionId value for this DeleteTimesheetEntries.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this DeleteTimesheetEntries.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the timesheetId value for this DeleteTimesheetEntries.
     * 
     * @return timesheetId
     */
    public java.lang.Long getTimesheetId() {
        return timesheetId;
    }


    /**
     * Sets the timesheetId value for this DeleteTimesheetEntries.
     * 
     * @param timesheetId
     */
    public void setTimesheetId(java.lang.Long timesheetId) {
        this.timesheetId = timesheetId;
    }


    /**
     * Gets the timesheetEntryId value for this DeleteTimesheetEntries.
     * 
     * @return timesheetEntryId
     */
    public long[] getTimesheetEntryId() {
        return timesheetEntryId;
    }


    /**
     * Sets the timesheetEntryId value for this DeleteTimesheetEntries.
     * 
     * @param timesheetEntryId
     */
    public void setTimesheetEntryId(long[] timesheetEntryId) {
        this.timesheetEntryId = timesheetEntryId;
    }

    public long getTimesheetEntryId(int i) {
        return this.timesheetEntryId[i];
    }

    public void setTimesheetEntryId(int i, long _value) {
        this.timesheetEntryId[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeleteTimesheetEntries)) return false;
        DeleteTimesheetEntries other = (DeleteTimesheetEntries) obj;
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
            ((this.timesheetId==null && other.getTimesheetId()==null) || 
             (this.timesheetId!=null &&
              this.timesheetId.equals(other.getTimesheetId()))) &&
            ((this.timesheetEntryId==null && other.getTimesheetEntryId()==null) || 
             (this.timesheetEntryId!=null &&
              java.util.Arrays.equals(this.timesheetEntryId, other.getTimesheetEntryId())));
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
        if (getTimesheetId() != null) {
            _hashCode += getTimesheetId().hashCode();
        }
        if (getTimesheetEntryId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTimesheetEntryId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTimesheetEntryId(), i);
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
        new org.apache.axis.description.TypeDesc(DeleteTimesheetEntries.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">deleteTimesheetEntries"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timesheetId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "timesheetId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timesheetEntryId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "timesheetEntryId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
