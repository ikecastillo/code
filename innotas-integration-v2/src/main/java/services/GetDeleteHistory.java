/**
 * GetDeleteHistory.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class GetDeleteHistory  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long entityTypeId;

    private java.lang.String startYYYYMMDD;

    private java.lang.String endYYYYMMDD;

    private java.lang.String simpleDateFormat;

    public GetDeleteHistory() {
    }

    public GetDeleteHistory(
           java.lang.String sessionId,
           java.lang.Long entityTypeId,
           java.lang.String startYYYYMMDD,
           java.lang.String endYYYYMMDD,
           java.lang.String simpleDateFormat) {
           this.sessionId = sessionId;
           this.entityTypeId = entityTypeId;
           this.startYYYYMMDD = startYYYYMMDD;
           this.endYYYYMMDD = endYYYYMMDD;
           this.simpleDateFormat = simpleDateFormat;
    }


    /**
     * Gets the sessionId value for this GetDeleteHistory.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this GetDeleteHistory.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the entityTypeId value for this GetDeleteHistory.
     * 
     * @return entityTypeId
     */
    public java.lang.Long getEntityTypeId() {
        return entityTypeId;
    }


    /**
     * Sets the entityTypeId value for this GetDeleteHistory.
     * 
     * @param entityTypeId
     */
    public void setEntityTypeId(java.lang.Long entityTypeId) {
        this.entityTypeId = entityTypeId;
    }


    /**
     * Gets the startYYYYMMDD value for this GetDeleteHistory.
     * 
     * @return startYYYYMMDD
     */
    public java.lang.String getStartYYYYMMDD() {
        return startYYYYMMDD;
    }


    /**
     * Sets the startYYYYMMDD value for this GetDeleteHistory.
     * 
     * @param startYYYYMMDD
     */
    public void setStartYYYYMMDD(java.lang.String startYYYYMMDD) {
        this.startYYYYMMDD = startYYYYMMDD;
    }


    /**
     * Gets the endYYYYMMDD value for this GetDeleteHistory.
     * 
     * @return endYYYYMMDD
     */
    public java.lang.String getEndYYYYMMDD() {
        return endYYYYMMDD;
    }


    /**
     * Sets the endYYYYMMDD value for this GetDeleteHistory.
     * 
     * @param endYYYYMMDD
     */
    public void setEndYYYYMMDD(java.lang.String endYYYYMMDD) {
        this.endYYYYMMDD = endYYYYMMDD;
    }


    /**
     * Gets the simpleDateFormat value for this GetDeleteHistory.
     * 
     * @return simpleDateFormat
     */
    public java.lang.String getSimpleDateFormat() {
        return simpleDateFormat;
    }


    /**
     * Sets the simpleDateFormat value for this GetDeleteHistory.
     * 
     * @param simpleDateFormat
     */
    public void setSimpleDateFormat(java.lang.String simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDeleteHistory)) return false;
        GetDeleteHistory other = (GetDeleteHistory) obj;
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
            ((this.startYYYYMMDD==null && other.getStartYYYYMMDD()==null) || 
             (this.startYYYYMMDD!=null &&
              this.startYYYYMMDD.equals(other.getStartYYYYMMDD()))) &&
            ((this.endYYYYMMDD==null && other.getEndYYYYMMDD()==null) || 
             (this.endYYYYMMDD!=null &&
              this.endYYYYMMDD.equals(other.getEndYYYYMMDD()))) &&
            ((this.simpleDateFormat==null && other.getSimpleDateFormat()==null) || 
             (this.simpleDateFormat!=null &&
              this.simpleDateFormat.equals(other.getSimpleDateFormat())));
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
        if (getStartYYYYMMDD() != null) {
            _hashCode += getStartYYYYMMDD().hashCode();
        }
        if (getEndYYYYMMDD() != null) {
            _hashCode += getEndYYYYMMDD().hashCode();
        }
        if (getSimpleDateFormat() != null) {
            _hashCode += getSimpleDateFormat().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDeleteHistory.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">getDeleteHistory"));
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
        elemField.setFieldName("startYYYYMMDD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "startYYYYMMDD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endYYYYMMDD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "endYYYYMMDD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("simpleDateFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "simpleDateFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
