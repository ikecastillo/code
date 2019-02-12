/**
 * StatusEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class StatusEntity  implements java.io.Serializable {
    private java.lang.String statusname;

    private java.lang.String statusimage;

    public StatusEntity() {
    }

    public StatusEntity(
           java.lang.String statusname,
           java.lang.String statusimage) {
           this.statusname = statusname;
           this.statusimage = statusimage;
    }


    /**
     * Gets the statusname value for this StatusEntity.
     * 
     * @return statusname
     */
    public java.lang.String getStatusname() {
        return statusname;
    }


    /**
     * Sets the statusname value for this StatusEntity.
     * 
     * @param statusname
     */
    public void setStatusname(java.lang.String statusname) {
        this.statusname = statusname;
    }


    /**
     * Gets the statusimage value for this StatusEntity.
     * 
     * @return statusimage
     */
    public java.lang.String getStatusimage() {
        return statusimage;
    }


    /**
     * Sets the statusimage value for this StatusEntity.
     * 
     * @param statusimage
     */
    public void setStatusimage(java.lang.String statusimage) {
        this.statusimage = statusimage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatusEntity)) return false;
        StatusEntity other = (StatusEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.statusname==null && other.getStatusname()==null) || 
             (this.statusname!=null &&
              this.statusname.equals(other.getStatusname()))) &&
            ((this.statusimage==null && other.getStatusimage()==null) || 
             (this.statusimage!=null &&
              this.statusimage.equals(other.getStatusimage())));
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
        if (getStatusname() != null) {
            _hashCode += getStatusname().hashCode();
        }
        if (getStatusimage() != null) {
            _hashCode += getStatusimage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatusEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "StatusEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "statusname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusimage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "statusimage"));
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
