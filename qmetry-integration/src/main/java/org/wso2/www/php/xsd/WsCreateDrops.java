/**
 * WsCreateDrops.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class WsCreateDrops  implements java.io.Serializable {
    private java.lang.String token;

    private java.lang.String dropName;

    private java.lang.String description;

    private int projectId;

    private int releaseId;

    private java.lang.String cycleId;

    private java.lang.String roleId;

    public WsCreateDrops() {
    }

    public WsCreateDrops(
           java.lang.String token,
           java.lang.String dropName,
           java.lang.String description,
           int projectId,
           int releaseId,
           java.lang.String cycleId,
           java.lang.String roleId) {
           this.token = token;
           this.dropName = dropName;
           this.description = description;
           this.projectId = projectId;
           this.releaseId = releaseId;
           this.cycleId = cycleId;
           this.roleId = roleId;
    }


    /**
     * Gets the token value for this WsCreateDrops.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this WsCreateDrops.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the dropName value for this WsCreateDrops.
     * 
     * @return dropName
     */
    public java.lang.String getDropName() {
        return dropName;
    }


    /**
     * Sets the dropName value for this WsCreateDrops.
     * 
     * @param dropName
     */
    public void setDropName(java.lang.String dropName) {
        this.dropName = dropName;
    }


    /**
     * Gets the description value for this WsCreateDrops.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this WsCreateDrops.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the projectId value for this WsCreateDrops.
     * 
     * @return projectId
     */
    public int getProjectId() {
        return projectId;
    }


    /**
     * Sets the projectId value for this WsCreateDrops.
     * 
     * @param projectId
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


    /**
     * Gets the releaseId value for this WsCreateDrops.
     * 
     * @return releaseId
     */
    public int getReleaseId() {
        return releaseId;
    }


    /**
     * Sets the releaseId value for this WsCreateDrops.
     * 
     * @param releaseId
     */
    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
    }


    /**
     * Gets the cycleId value for this WsCreateDrops.
     * 
     * @return cycleId
     */
    public java.lang.String getCycleId() {
        return cycleId;
    }


    /**
     * Sets the cycleId value for this WsCreateDrops.
     * 
     * @param cycleId
     */
    public void setCycleId(java.lang.String cycleId) {
        this.cycleId = cycleId;
    }


    /**
     * Gets the roleId value for this WsCreateDrops.
     * 
     * @return roleId
     */
    public java.lang.String getRoleId() {
        return roleId;
    }


    /**
     * Sets the roleId value for this WsCreateDrops.
     * 
     * @param roleId
     */
    public void setRoleId(java.lang.String roleId) {
        this.roleId = roleId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsCreateDrops)) return false;
        WsCreateDrops other = (WsCreateDrops) obj;
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
            ((this.dropName==null && other.getDropName()==null) || 
             (this.dropName!=null &&
              this.dropName.equals(other.getDropName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this.projectId == other.getProjectId() &&
            this.releaseId == other.getReleaseId() &&
            ((this.cycleId==null && other.getCycleId()==null) || 
             (this.cycleId!=null &&
              this.cycleId.equals(other.getCycleId()))) &&
            ((this.roleId==null && other.getRoleId()==null) || 
             (this.roleId!=null &&
              this.roleId.equals(other.getRoleId())));
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
        if (getDropName() != null) {
            _hashCode += getDropName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += getProjectId();
        _hashCode += getReleaseId();
        if (getCycleId() != null) {
            _hashCode += getCycleId().hashCode();
        }
        if (getRoleId() != null) {
            _hashCode += getRoleId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsCreateDrops.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">wsCreateDrops"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dropName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "dropName"));
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
        elemField.setFieldName("projectId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "projectId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("releaseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "releaseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cycleId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "cycleId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("roleId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "roleId"));
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
