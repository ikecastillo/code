/**
 * FindEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class FindEntity  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long entityTypeId;

    private services.objects.xsd.ValuePair[] searchValuePairs;

    private long[] fieldsRequest;

    public FindEntity() {
    }

    public FindEntity(
           java.lang.String sessionId,
           java.lang.Long entityTypeId,
           services.objects.xsd.ValuePair[] searchValuePairs,
           long[] fieldsRequest) {
           this.sessionId = sessionId;
           this.entityTypeId = entityTypeId;
           this.searchValuePairs = searchValuePairs;
           this.fieldsRequest = fieldsRequest;
    }


    /**
     * Gets the sessionId value for this FindEntity.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this FindEntity.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the entityTypeId value for this FindEntity.
     * 
     * @return entityTypeId
     */
    public java.lang.Long getEntityTypeId() {
        return entityTypeId;
    }


    /**
     * Sets the entityTypeId value for this FindEntity.
     * 
     * @param entityTypeId
     */
    public void setEntityTypeId(java.lang.Long entityTypeId) {
        this.entityTypeId = entityTypeId;
    }


    /**
     * Gets the searchValuePairs value for this FindEntity.
     * 
     * @return searchValuePairs
     */
    public services.objects.xsd.ValuePair[] getSearchValuePairs() {
        return searchValuePairs;
    }


    /**
     * Sets the searchValuePairs value for this FindEntity.
     * 
     * @param searchValuePairs
     */
    public void setSearchValuePairs(services.objects.xsd.ValuePair[] searchValuePairs) {
        this.searchValuePairs = searchValuePairs;
    }

    public services.objects.xsd.ValuePair getSearchValuePairs(int i) {
        return this.searchValuePairs[i];
    }

    public void setSearchValuePairs(int i, services.objects.xsd.ValuePair _value) {
        this.searchValuePairs[i] = _value;
    }


    /**
     * Gets the fieldsRequest value for this FindEntity.
     * 
     * @return fieldsRequest
     */
    public long[] getFieldsRequest() {
        return fieldsRequest;
    }


    /**
     * Sets the fieldsRequest value for this FindEntity.
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
        if (!(obj instanceof FindEntity)) return false;
        FindEntity other = (FindEntity) obj;
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
            ((this.searchValuePairs==null && other.getSearchValuePairs()==null) || 
             (this.searchValuePairs!=null &&
              java.util.Arrays.equals(this.searchValuePairs, other.getSearchValuePairs()))) &&
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
        if (getSearchValuePairs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSearchValuePairs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSearchValuePairs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        new org.apache.axis.description.TypeDesc(FindEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">findEntity"));
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
        elemField.setFieldName("searchValuePairs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "searchValuePairs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ValuePair"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
