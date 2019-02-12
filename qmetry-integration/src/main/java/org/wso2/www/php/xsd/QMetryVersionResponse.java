/**
 * QMetryVersionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class QMetryVersionResponse  implements java.io.Serializable {
    private java.lang.String QMetryVersion;

    public QMetryVersionResponse() {
    }

    public QMetryVersionResponse(
           java.lang.String QMetryVersion) {
           this.QMetryVersion = QMetryVersion;
    }


    /**
     * Gets the QMetryVersion value for this QMetryVersionResponse.
     * 
     * @return QMetryVersion
     */
    public java.lang.String getQMetryVersion() {
        return QMetryVersion;
    }


    /**
     * Sets the QMetryVersion value for this QMetryVersionResponse.
     * 
     * @param QMetryVersion
     */
    public void setQMetryVersion(java.lang.String QMetryVersion) {
        this.QMetryVersion = QMetryVersion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QMetryVersionResponse)) return false;
        QMetryVersionResponse other = (QMetryVersionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.QMetryVersion==null && other.getQMetryVersion()==null) || 
             (this.QMetryVersion!=null &&
              this.QMetryVersion.equals(other.getQMetryVersion())));
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
        if (getQMetryVersion() != null) {
            _hashCode += getQMetryVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QMetryVersionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">QMetryVersionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("QMetryVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "QMetryVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
