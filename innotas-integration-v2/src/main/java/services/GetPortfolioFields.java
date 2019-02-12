/**
 * GetPortfolioFields.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class GetPortfolioFields  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long portfolioTypeId;

    public GetPortfolioFields() {
    }

    public GetPortfolioFields(
           java.lang.String sessionId,
           java.lang.Long portfolioTypeId) {
           this.sessionId = sessionId;
           this.portfolioTypeId = portfolioTypeId;
    }


    /**
     * Gets the sessionId value for this GetPortfolioFields.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this GetPortfolioFields.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the portfolioTypeId value for this GetPortfolioFields.
     * 
     * @return portfolioTypeId
     */
    public java.lang.Long getPortfolioTypeId() {
        return portfolioTypeId;
    }


    /**
     * Sets the portfolioTypeId value for this GetPortfolioFields.
     * 
     * @param portfolioTypeId
     */
    public void setPortfolioTypeId(java.lang.Long portfolioTypeId) {
        this.portfolioTypeId = portfolioTypeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetPortfolioFields)) return false;
        GetPortfolioFields other = (GetPortfolioFields) obj;
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
              this.portfolioTypeId.equals(other.getPortfolioTypeId())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPortfolioFields.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">getPortfolioFields"));
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
