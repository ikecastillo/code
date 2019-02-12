/**
 * GetRequirementByIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class GetRequirementByIdResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.RequirementEntity requirment;

    public GetRequirementByIdResponse() {
    }

    public GetRequirementByIdResponse(
           org.wso2.www.php.xsd.RequirementEntity requirment) {
           this.requirment = requirment;
    }


    /**
     * Gets the requirment value for this GetRequirementByIdResponse.
     * 
     * @return requirment
     */
    public org.wso2.www.php.xsd.RequirementEntity getRequirment() {
        return requirment;
    }


    /**
     * Sets the requirment value for this GetRequirementByIdResponse.
     * 
     * @param requirment
     */
    public void setRequirment(org.wso2.www.php.xsd.RequirementEntity requirment) {
        this.requirment = requirment;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetRequirementByIdResponse)) return false;
        GetRequirementByIdResponse other = (GetRequirementByIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requirment==null && other.getRequirment()==null) || 
             (this.requirment!=null &&
              this.requirment.equals(other.getRequirment())));
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
        if (getRequirment() != null) {
            _hashCode += getRequirment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetRequirementByIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">getRequirementByIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requirment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "requirment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "RequirementEntity"));
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
