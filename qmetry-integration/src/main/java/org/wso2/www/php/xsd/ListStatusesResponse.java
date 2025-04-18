/**
 * ListStatusesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListStatusesResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.StatusEntity[] statusEntity;

    public ListStatusesResponse() {
    }

    public ListStatusesResponse(
           org.wso2.www.php.xsd.StatusEntity[] statusEntity) {
           this.statusEntity = statusEntity;
    }


    /**
     * Gets the statusEntity value for this ListStatusesResponse.
     * 
     * @return statusEntity
     */
    public org.wso2.www.php.xsd.StatusEntity[] getStatusEntity() {
        return statusEntity;
    }


    /**
     * Sets the statusEntity value for this ListStatusesResponse.
     * 
     * @param statusEntity
     */
    public void setStatusEntity(org.wso2.www.php.xsd.StatusEntity[] statusEntity) {
        this.statusEntity = statusEntity;
    }

    public org.wso2.www.php.xsd.StatusEntity getStatusEntity(int i) {
        return this.statusEntity[i];
    }

    public void setStatusEntity(int i, org.wso2.www.php.xsd.StatusEntity _value) {
        this.statusEntity[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListStatusesResponse)) return false;
        ListStatusesResponse other = (ListStatusesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.statusEntity==null && other.getStatusEntity()==null) || 
             (this.statusEntity!=null &&
              java.util.Arrays.equals(this.statusEntity, other.getStatusEntity())));
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
        if (getStatusEntity() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStatusEntity());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStatusEntity(), i);
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
        new org.apache.axis.description.TypeDesc(ListStatusesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listStatusesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusEntity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "statusEntity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "StatusEntity"));
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
