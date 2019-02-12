/**
 * SubmitRequests.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class SubmitRequests  implements java.io.Serializable {
    private java.lang.String sessionId;

    private long[] requestIds;

    public SubmitRequests() {
    }

    public SubmitRequests(
           java.lang.String sessionId,
           long[] requestIds) {
           this.sessionId = sessionId;
           this.requestIds = requestIds;
    }


    /**
     * Gets the sessionId value for this SubmitRequests.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this SubmitRequests.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the requestIds value for this SubmitRequests.
     * 
     * @return requestIds
     */
    public long[] getRequestIds() {
        return requestIds;
    }


    /**
     * Sets the requestIds value for this SubmitRequests.
     * 
     * @param requestIds
     */
    public void setRequestIds(long[] requestIds) {
        this.requestIds = requestIds;
    }

    public long getRequestIds(int i) {
        return this.requestIds[i];
    }

    public void setRequestIds(int i, long _value) {
        this.requestIds[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubmitRequests)) return false;
        SubmitRequests other = (SubmitRequests) obj;
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
            ((this.requestIds==null && other.getRequestIds()==null) || 
             (this.requestIds!=null &&
              java.util.Arrays.equals(this.requestIds, other.getRequestIds())));
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
        if (getRequestIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRequestIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRequestIds(), i);
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
        new org.apache.axis.description.TypeDesc(SubmitRequests.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">submitRequests"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "requestIds"));
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
