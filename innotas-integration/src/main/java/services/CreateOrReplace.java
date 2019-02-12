/**
 * CreateOrReplace.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class CreateOrReplace  implements java.io.Serializable {
    private java.lang.String sessionId;

    private services.objects.xsd.EntityObj newObj;

    private services.objects.xsd.ValuePair[] searchValuePairs;

    public CreateOrReplace() {
    }

    public CreateOrReplace(
           java.lang.String sessionId,
           services.objects.xsd.EntityObj newObj,
           services.objects.xsd.ValuePair[] searchValuePairs) {
           this.sessionId = sessionId;
           this.newObj = newObj;
           this.searchValuePairs = searchValuePairs;
    }


    /**
     * Gets the sessionId value for this CreateOrReplace.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this CreateOrReplace.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the newObj value for this CreateOrReplace.
     * 
     * @return newObj
     */
    public services.objects.xsd.EntityObj getNewObj() {
        return newObj;
    }


    /**
     * Sets the newObj value for this CreateOrReplace.
     * 
     * @param newObj
     */
    public void setNewObj(services.objects.xsd.EntityObj newObj) {
        this.newObj = newObj;
    }


    /**
     * Gets the searchValuePairs value for this CreateOrReplace.
     * 
     * @return searchValuePairs
     */
    public services.objects.xsd.ValuePair[] getSearchValuePairs() {
        return searchValuePairs;
    }


    /**
     * Sets the searchValuePairs value for this CreateOrReplace.
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

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateOrReplace)) return false;
        CreateOrReplace other = (CreateOrReplace) obj;
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
            ((this.newObj==null && other.getNewObj()==null) || 
             (this.newObj!=null &&
              this.newObj.equals(other.getNewObj()))) &&
            ((this.searchValuePairs==null && other.getSearchValuePairs()==null) || 
             (this.searchValuePairs!=null &&
              java.util.Arrays.equals(this.searchValuePairs, other.getSearchValuePairs())));
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
        if (getNewObj() != null) {
            _hashCode += getNewObj().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateOrReplace.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">createOrReplace"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newObj");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "newObj"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "EntityObj"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchValuePairs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "searchValuePairs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ValuePair"));
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
