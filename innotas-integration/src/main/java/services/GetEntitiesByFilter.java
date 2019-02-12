/**
 * GetEntitiesByFilter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class GetEntitiesByFilter  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long entityTypeId;

    private java.lang.String filter;

    private long[] fieldsRequest;

    public GetEntitiesByFilter() {
    }

    public GetEntitiesByFilter(
           java.lang.String sessionId,
           java.lang.Long entityTypeId,
           java.lang.String filter,
           long[] fieldsRequest) {
           this.sessionId = sessionId;
           this.entityTypeId = entityTypeId;
           this.filter = filter;
           this.fieldsRequest = fieldsRequest;
    }


    /**
     * Gets the sessionId value for this GetEntitiesByFilter.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this GetEntitiesByFilter.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the entityTypeId value for this GetEntitiesByFilter.
     * 
     * @return entityTypeId
     */
    public java.lang.Long getEntityTypeId() {
        return entityTypeId;
    }


    /**
     * Sets the entityTypeId value for this GetEntitiesByFilter.
     * 
     * @param entityTypeId
     */
    public void setEntityTypeId(java.lang.Long entityTypeId) {
        this.entityTypeId = entityTypeId;
    }


    /**
     * Gets the filter value for this GetEntitiesByFilter.
     * 
     * @return filter
     */
    public java.lang.String getFilter() {
        return filter;
    }


    /**
     * Sets the filter value for this GetEntitiesByFilter.
     * 
     * @param filter
     */
    public void setFilter(java.lang.String filter) {
        this.filter = filter;
    }


    /**
     * Gets the fieldsRequest value for this GetEntitiesByFilter.
     * 
     * @return fieldsRequest
     */
    public long[] getFieldsRequest() {
        return fieldsRequest;
    }


    /**
     * Sets the fieldsRequest value for this GetEntitiesByFilter.
     * 
     * @param fieldsRequest
     */
    public void setFieldsRequest(long[] fieldsRequest) {
        this.fieldsRequest = fieldsRequest;
    }

    public long getFieldsRequest(int i) {
        return this.fieldsRequest[i];
    }

    public void setFieldsRequest(int i, long _value) {
        this.fieldsRequest[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetEntitiesByFilter)) return false;
        GetEntitiesByFilter other = (GetEntitiesByFilter) obj;
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
            ((this.entityTypeId==null && other.getEntityTypeId()==null) || 
             (this.entityTypeId!=null &&
              this.entityTypeId.equals(other.getEntityTypeId()))) &&
            ((this.filter==null && other.getFilter()==null) || 
             (this.filter!=null &&
              this.filter.equals(other.getFilter()))) &&
            ((this.fieldsRequest==null && other.getFieldsRequest()==null) || 
             (this.fieldsRequest!=null &&
              java.util.Arrays.equals(this.fieldsRequest, other.getFieldsRequest())));
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
        if (getEntityTypeId() != null) {
            _hashCode += getEntityTypeId().hashCode();
        }
        if (getFilter() != null) {
            _hashCode += getFilter().hashCode();
        }
        if (getFieldsRequest() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFieldsRequest());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFieldsRequest(), i);
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
        new org.apache.axis.description.TypeDesc(GetEntitiesByFilter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">getEntitiesByFilter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "entityTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "filter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldsRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "fieldsRequest"));
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
