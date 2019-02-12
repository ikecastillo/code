/**
 * CreateFolder.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class CreateFolder  implements java.io.Serializable {
    private java.lang.String token;

    private java.lang.String folderName;

    private java.lang.String folderDesc;

    private java.lang.String folderType;

    private int parentId;

    public CreateFolder() {
    }

    public CreateFolder(
           java.lang.String token,
           java.lang.String folderName,
           java.lang.String folderDesc,
           java.lang.String folderType,
           int parentId) {
           this.token = token;
           this.folderName = folderName;
           this.folderDesc = folderDesc;
           this.folderType = folderType;
           this.parentId = parentId;
    }


    /**
     * Gets the token value for this CreateFolder.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this CreateFolder.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the folderName value for this CreateFolder.
     * 
     * @return folderName
     */
    public java.lang.String getFolderName() {
        return folderName;
    }


    /**
     * Sets the folderName value for this CreateFolder.
     * 
     * @param folderName
     */
    public void setFolderName(java.lang.String folderName) {
        this.folderName = folderName;
    }


    /**
     * Gets the folderDesc value for this CreateFolder.
     * 
     * @return folderDesc
     */
    public java.lang.String getFolderDesc() {
        return folderDesc;
    }


    /**
     * Sets the folderDesc value for this CreateFolder.
     * 
     * @param folderDesc
     */
    public void setFolderDesc(java.lang.String folderDesc) {
        this.folderDesc = folderDesc;
    }


    /**
     * Gets the folderType value for this CreateFolder.
     * 
     * @return folderType
     */
    public java.lang.String getFolderType() {
        return folderType;
    }


    /**
     * Sets the folderType value for this CreateFolder.
     * 
     * @param folderType
     */
    public void setFolderType(java.lang.String folderType) {
        this.folderType = folderType;
    }


    /**
     * Gets the parentId value for this CreateFolder.
     * 
     * @return parentId
     */
    public int getParentId() {
        return parentId;
    }


    /**
     * Sets the parentId value for this CreateFolder.
     * 
     * @param parentId
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateFolder)) return false;
        CreateFolder other = (CreateFolder) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken()))) &&
            ((this.folderName==null && other.getFolderName()==null) || 
             (this.folderName!=null &&
              this.folderName.equals(other.getFolderName()))) &&
            ((this.folderDesc==null && other.getFolderDesc()==null) || 
             (this.folderDesc!=null &&
              this.folderDesc.equals(other.getFolderDesc()))) &&
            ((this.folderType==null && other.getFolderType()==null) || 
             (this.folderType!=null &&
              this.folderType.equals(other.getFolderType()))) &&
            this.parentId == other.getParentId();
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
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        if (getFolderName() != null) {
            _hashCode += getFolderName().hashCode();
        }
        if (getFolderDesc() != null) {
            _hashCode += getFolderDesc().hashCode();
        }
        if (getFolderType() != null) {
            _hashCode += getFolderType().hashCode();
        }
        _hashCode += getParentId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateFolder.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createFolder"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "folderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "folderDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "folderType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "parentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
