/**
 * SubmitTimesheets.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class SubmitTimesheets  implements java.io.Serializable {
    private java.lang.String sessionId;

    private long[] timesheetIds;

    public SubmitTimesheets() {
    }

    public SubmitTimesheets(
           java.lang.String sessionId,
           long[] timesheetIds) {
           this.sessionId = sessionId;
           this.timesheetIds = timesheetIds;
    }


    /**
     * Gets the sessionId value for this SubmitTimesheets.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this SubmitTimesheets.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the timesheetIds value for this SubmitTimesheets.
     * 
     * @return timesheetIds
     */
    public long[] getTimesheetIds() {
        return timesheetIds;
    }


    /**
     * Sets the timesheetIds value for this SubmitTimesheets.
     * 
     * @param timesheetIds
     */
    public void setTimesheetIds(long[] timesheetIds) {
        this.timesheetIds = timesheetIds;
    }

    public long getTimesheetIds(int i) {
        return this.timesheetIds[i];
    }

    public void setTimesheetIds(int i, long _value) {
        this.timesheetIds[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubmitTimesheets)) return false;
        SubmitTimesheets other = (SubmitTimesheets) obj;
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
            ((this.timesheetIds==null && other.getTimesheetIds()==null) || 
             (this.timesheetIds!=null &&
              java.util.Arrays.equals(this.timesheetIds, other.getTimesheetIds())));
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
        if (getTimesheetIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTimesheetIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTimesheetIds(), i);
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
        new org.apache.axis.description.TypeDesc(SubmitTimesheets.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">submitTimesheets"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timesheetIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "timesheetIds"));
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
