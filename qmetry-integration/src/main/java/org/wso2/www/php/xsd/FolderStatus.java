/**
 * FolderStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class FolderStatus  implements java.io.Serializable {
    private int id;

    private java.lang.String name;

    private java.lang.String description;

    private int parentId;

    private int childCount;

    private java.lang.String folderStatus;

    private java.lang.String folderPath;

    public FolderStatus() {
    }

    public FolderStatus(
           int id,
           java.lang.String name,
           java.lang.String description,
           int parentId,
           int childCount,
           java.lang.String folderStatus,
           java.lang.String folderPath) {
           this.id = id;
           this.name = name;
           this.description = description;
           this.parentId = parentId;
           this.childCount = childCount;
           this.folderStatus = folderStatus;
           this.folderPath = folderPath;
    }


    /**
     * Gets the id value for this FolderStatus.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this FolderStatus.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the name value for this FolderStatus.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this FolderStatus.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this FolderStatus.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this FolderStatus.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the parentId value for this FolderStatus.
     * 
     * @return parentId
     */
    public int getParentId() {
        return parentId;
    }


    /**
     * Sets the parentId value for this FolderStatus.
     * 
     * @param parentId
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }


    /**
     * Gets the childCount value for this FolderStatus.
     * 
     * @return childCount
     */
    public int getChildCount() {
        return childCount;
    }


    /**
     * Sets the childCount value for this FolderStatus.
     * 
     * @param childCount
     */
    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }


    /**
     * Gets the folderStatus value for this FolderStatus.
     * 
     * @return folderStatus
     */
    public java.lang.String getFolderStatus() {
        return folderStatus;
    }


    /**
     * Sets the folderStatus value for this FolderStatus.
     * 
     * @param folderStatus
     */
    public void setFolderStatus(java.lang.String folderStatus) {
        this.folderStatus = folderStatus;
    }


    /**
     * Gets the folderPath value for this FolderStatus.
     * 
     * @return folderPath
     */
    public java.lang.String getFolderPath() {
        return folderPath;
    }


    /**
     * Sets the folderPath value for this FolderStatus.
     * 
     * @param folderPath
     */
    public void setFolderPath(java.lang.String folderPath) {
        this.folderPath = folderPath;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FolderStatus)) return false;
        FolderStatus other = (FolderStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this.parentId == other.getParentId() &&
            this.childCount == other.getChildCount() &&
            ((this.folderStatus==null && other.getFolderStatus()==null) || 
             (this.folderStatus!=null &&
              this.folderStatus.equals(other.getFolderStatus()))) &&
            ((this.folderPath==null && other.getFolderPath()==null) || 
             (this.folderPath!=null &&
              this.folderPath.equals(other.getFolderPath())));
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
        _hashCode += getId();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += getParentId();
        _hashCode += getChildCount();
        if (getFolderStatus() != null) {
            _hashCode += getFolderStatus().hashCode();
        }
        if (getFolderPath() != null) {
            _hashCode += getFolderPath().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FolderStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "FolderStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "parentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("childCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "childCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "folderStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "folderPath"));
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
