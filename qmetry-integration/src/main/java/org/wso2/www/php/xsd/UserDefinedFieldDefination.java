/**
 * UserDefinedFieldDefination.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class UserDefinedFieldDefination  implements java.io.Serializable {
    private int udfId;

    private java.lang.String udfEntityType;

    private int udfOrder;

    private java.lang.String udfIsMandatory;

    private java.lang.String udfType;

    private int udfLength;

    private int udfLookupList;

    private java.lang.String udfName;

    private java.lang.String udfFieldName;

    public UserDefinedFieldDefination() {
    }

    public UserDefinedFieldDefination(
           int udfId,
           java.lang.String udfEntityType,
           int udfOrder,
           java.lang.String udfIsMandatory,
           java.lang.String udfType,
           int udfLength,
           int udfLookupList,
           java.lang.String udfName,
           java.lang.String udfFieldName) {
           this.udfId = udfId;
           this.udfEntityType = udfEntityType;
           this.udfOrder = udfOrder;
           this.udfIsMandatory = udfIsMandatory;
           this.udfType = udfType;
           this.udfLength = udfLength;
           this.udfLookupList = udfLookupList;
           this.udfName = udfName;
           this.udfFieldName = udfFieldName;
    }


    /**
     * Gets the udfId value for this UserDefinedFieldDefination.
     * 
     * @return udfId
     */
    public int getUdfId() {
        return udfId;
    }


    /**
     * Sets the udfId value for this UserDefinedFieldDefination.
     * 
     * @param udfId
     */
    public void setUdfId(int udfId) {
        this.udfId = udfId;
    }


    /**
     * Gets the udfEntityType value for this UserDefinedFieldDefination.
     * 
     * @return udfEntityType
     */
    public java.lang.String getUdfEntityType() {
        return udfEntityType;
    }


    /**
     * Sets the udfEntityType value for this UserDefinedFieldDefination.
     * 
     * @param udfEntityType
     */
    public void setUdfEntityType(java.lang.String udfEntityType) {
        this.udfEntityType = udfEntityType;
    }


    /**
     * Gets the udfOrder value for this UserDefinedFieldDefination.
     * 
     * @return udfOrder
     */
    public int getUdfOrder() {
        return udfOrder;
    }


    /**
     * Sets the udfOrder value for this UserDefinedFieldDefination.
     * 
     * @param udfOrder
     */
    public void setUdfOrder(int udfOrder) {
        this.udfOrder = udfOrder;
    }


    /**
     * Gets the udfIsMandatory value for this UserDefinedFieldDefination.
     * 
     * @return udfIsMandatory
     */
    public java.lang.String getUdfIsMandatory() {
        return udfIsMandatory;
    }


    /**
     * Sets the udfIsMandatory value for this UserDefinedFieldDefination.
     * 
     * @param udfIsMandatory
     */
    public void setUdfIsMandatory(java.lang.String udfIsMandatory) {
        this.udfIsMandatory = udfIsMandatory;
    }


    /**
     * Gets the udfType value for this UserDefinedFieldDefination.
     * 
     * @return udfType
     */
    public java.lang.String getUdfType() {
        return udfType;
    }


    /**
     * Sets the udfType value for this UserDefinedFieldDefination.
     * 
     * @param udfType
     */
    public void setUdfType(java.lang.String udfType) {
        this.udfType = udfType;
    }


    /**
     * Gets the udfLength value for this UserDefinedFieldDefination.
     * 
     * @return udfLength
     */
    public int getUdfLength() {
        return udfLength;
    }


    /**
     * Sets the udfLength value for this UserDefinedFieldDefination.
     * 
     * @param udfLength
     */
    public void setUdfLength(int udfLength) {
        this.udfLength = udfLength;
    }


    /**
     * Gets the udfLookupList value for this UserDefinedFieldDefination.
     * 
     * @return udfLookupList
     */
    public int getUdfLookupList() {
        return udfLookupList;
    }


    /**
     * Sets the udfLookupList value for this UserDefinedFieldDefination.
     * 
     * @param udfLookupList
     */
    public void setUdfLookupList(int udfLookupList) {
        this.udfLookupList = udfLookupList;
    }


    /**
     * Gets the udfName value for this UserDefinedFieldDefination.
     * 
     * @return udfName
     */
    public java.lang.String getUdfName() {
        return udfName;
    }


    /**
     * Sets the udfName value for this UserDefinedFieldDefination.
     * 
     * @param udfName
     */
    public void setUdfName(java.lang.String udfName) {
        this.udfName = udfName;
    }


    /**
     * Gets the udfFieldName value for this UserDefinedFieldDefination.
     * 
     * @return udfFieldName
     */
    public java.lang.String getUdfFieldName() {
        return udfFieldName;
    }


    /**
     * Sets the udfFieldName value for this UserDefinedFieldDefination.
     * 
     * @param udfFieldName
     */
    public void setUdfFieldName(java.lang.String udfFieldName) {
        this.udfFieldName = udfFieldName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UserDefinedFieldDefination)) return false;
        UserDefinedFieldDefination other = (UserDefinedFieldDefination) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.udfId == other.getUdfId() &&
            ((this.udfEntityType==null && other.getUdfEntityType()==null) || 
             (this.udfEntityType!=null &&
              this.udfEntityType.equals(other.getUdfEntityType()))) &&
            this.udfOrder == other.getUdfOrder() &&
            ((this.udfIsMandatory==null && other.getUdfIsMandatory()==null) || 
             (this.udfIsMandatory!=null &&
              this.udfIsMandatory.equals(other.getUdfIsMandatory()))) &&
            ((this.udfType==null && other.getUdfType()==null) || 
             (this.udfType!=null &&
              this.udfType.equals(other.getUdfType()))) &&
            this.udfLength == other.getUdfLength() &&
            this.udfLookupList == other.getUdfLookupList() &&
            ((this.udfName==null && other.getUdfName()==null) || 
             (this.udfName!=null &&
              this.udfName.equals(other.getUdfName()))) &&
            ((this.udfFieldName==null && other.getUdfFieldName()==null) || 
             (this.udfFieldName!=null &&
              this.udfFieldName.equals(other.getUdfFieldName())));
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
        _hashCode += getUdfId();
        if (getUdfEntityType() != null) {
            _hashCode += getUdfEntityType().hashCode();
        }
        _hashCode += getUdfOrder();
        if (getUdfIsMandatory() != null) {
            _hashCode += getUdfIsMandatory().hashCode();
        }
        if (getUdfType() != null) {
            _hashCode += getUdfType().hashCode();
        }
        _hashCode += getUdfLength();
        _hashCode += getUdfLookupList();
        if (getUdfName() != null) {
            _hashCode += getUdfName().hashCode();
        }
        if (getUdfFieldName() != null) {
            _hashCode += getUdfFieldName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UserDefinedFieldDefination.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefinedFieldDefination"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfEntityType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfEntityType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfOrder");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfOrder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfIsMandatory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfIsMandatory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfLength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfLookupList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfLookupList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfFieldName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfFieldName"));
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
