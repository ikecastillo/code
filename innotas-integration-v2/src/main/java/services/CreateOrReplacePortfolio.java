/**
 * CreateOrReplacePortfolio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class CreateOrReplacePortfolio  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long portfolioTypeId;

    private services.objects.xsd.ValuePair[] valuePairs;

    public CreateOrReplacePortfolio() {
    }

    public CreateOrReplacePortfolio(
           java.lang.String sessionId,
           java.lang.Long portfolioTypeId,
           services.objects.xsd.ValuePair[] valuePairs) {
           this.sessionId = sessionId;
           this.portfolioTypeId = portfolioTypeId;
           this.valuePairs = valuePairs;
    }


    /**
     * Gets the sessionId value for this CreateOrReplacePortfolio.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this CreateOrReplacePortfolio.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the portfolioTypeId value for this CreateOrReplacePortfolio.
     * 
     * @return portfolioTypeId
     */
    public java.lang.Long getPortfolioTypeId() {
        return portfolioTypeId;
    }


    /**
     * Sets the portfolioTypeId value for this CreateOrReplacePortfolio.
     * 
     * @param portfolioTypeId
     */
    public void setPortfolioTypeId(java.lang.Long portfolioTypeId) {
        this.portfolioTypeId = portfolioTypeId;
    }


    /**
     * Gets the valuePairs value for this CreateOrReplacePortfolio.
     * 
     * @return valuePairs
     */
    public services.objects.xsd.ValuePair[] getValuePairs() {
        return valuePairs;
    }


    /**
     * Sets the valuePairs value for this CreateOrReplacePortfolio.
     * 
     * @param valuePairs
     */
    public void setValuePairs(services.objects.xsd.ValuePair[] valuePairs) {
        this.valuePairs = valuePairs;
    }

    public services.objects.xsd.ValuePair getValuePairs(int i) {
        return this.valuePairs[i];
    }

    public void setValuePairs(int i, services.objects.xsd.ValuePair _value) {
        this.valuePairs[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateOrReplacePortfolio)) return false;
        CreateOrReplacePortfolio other = (CreateOrReplacePortfolio) obj;
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
            ((this.portfolioTypeId==null && other.getPortfolioTypeId()==null) || 
             (this.portfolioTypeId!=null &&
              this.portfolioTypeId.equals(other.getPortfolioTypeId()))) &&
            ((this.valuePairs==null && other.getValuePairs()==null) || 
             (this.valuePairs!=null &&
              java.util.Arrays.equals(this.valuePairs, other.getValuePairs())));
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
        if (getPortfolioTypeId() != null) {
            _hashCode += getPortfolioTypeId().hashCode();
        }
        if (getValuePairs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValuePairs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValuePairs(), i);
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
        new org.apache.axis.description.TypeDesc(CreateOrReplacePortfolio.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">createOrReplacePortfolio"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portfolioTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "portfolioTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valuePairs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "valuePairs"));
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
