/**
 * CreateProject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class CreateProject  implements java.io.Serializable {
    private java.lang.String token;

    private java.lang.String projectName;

    private java.lang.String projectDesc;

    private java.lang.String releaseName;

    private java.lang.String releaseDesc;

    private java.lang.String targetReleaseDate;

    private java.lang.String buildName;

    private java.lang.String buildDesc;

    private java.lang.String targetBuildDate;

    private int[] roleIds;

    private int[] userIds;

    private int copySampleData;

    public CreateProject() {
    }

    public CreateProject(
           java.lang.String token,
           java.lang.String projectName,
           java.lang.String projectDesc,
           java.lang.String releaseName,
           java.lang.String releaseDesc,
           java.lang.String targetReleaseDate,
           java.lang.String buildName,
           java.lang.String buildDesc,
           java.lang.String targetBuildDate,
           int[] roleIds,
           int[] userIds,
           int copySampleData) {
           this.token = token;
           this.projectName = projectName;
           this.projectDesc = projectDesc;
           this.releaseName = releaseName;
           this.releaseDesc = releaseDesc;
           this.targetReleaseDate = targetReleaseDate;
           this.buildName = buildName;
           this.buildDesc = buildDesc;
           this.targetBuildDate = targetBuildDate;
           this.roleIds = roleIds;
           this.userIds = userIds;
           this.copySampleData = copySampleData;
    }


    /**
     * Gets the token value for this CreateProject.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this CreateProject.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the projectName value for this CreateProject.
     * 
     * @return projectName
     */
    public java.lang.String getProjectName() {
        return projectName;
    }


    /**
     * Sets the projectName value for this CreateProject.
     * 
     * @param projectName
     */
    public void setProjectName(java.lang.String projectName) {
        this.projectName = projectName;
    }


    /**
     * Gets the projectDesc value for this CreateProject.
     * 
     * @return projectDesc
     */
    public java.lang.String getProjectDesc() {
        return projectDesc;
    }


    /**
     * Sets the projectDesc value for this CreateProject.
     * 
     * @param projectDesc
     */
    public void setProjectDesc(java.lang.String projectDesc) {
        this.projectDesc = projectDesc;
    }


    /**
     * Gets the releaseName value for this CreateProject.
     * 
     * @return releaseName
     */
    public java.lang.String getReleaseName() {
        return releaseName;
    }


    /**
     * Sets the releaseName value for this CreateProject.
     * 
     * @param releaseName
     */
    public void setReleaseName(java.lang.String releaseName) {
        this.releaseName = releaseName;
    }


    /**
     * Gets the releaseDesc value for this CreateProject.
     * 
     * @return releaseDesc
     */
    public java.lang.String getReleaseDesc() {
        return releaseDesc;
    }


    /**
     * Sets the releaseDesc value for this CreateProject.
     * 
     * @param releaseDesc
     */
    public void setReleaseDesc(java.lang.String releaseDesc) {
        this.releaseDesc = releaseDesc;
    }


    /**
     * Gets the targetReleaseDate value for this CreateProject.
     * 
     * @return targetReleaseDate
     */
    public java.lang.String getTargetReleaseDate() {
        return targetReleaseDate;
    }


    /**
     * Sets the targetReleaseDate value for this CreateProject.
     * 
     * @param targetReleaseDate
     */
    public void setTargetReleaseDate(java.lang.String targetReleaseDate) {
        this.targetReleaseDate = targetReleaseDate;
    }


    /**
     * Gets the buildName value for this CreateProject.
     * 
     * @return buildName
     */
    public java.lang.String getBuildName() {
        return buildName;
    }


    /**
     * Sets the buildName value for this CreateProject.
     * 
     * @param buildName
     */
    public void setBuildName(java.lang.String buildName) {
        this.buildName = buildName;
    }


    /**
     * Gets the buildDesc value for this CreateProject.
     * 
     * @return buildDesc
     */
    public java.lang.String getBuildDesc() {
        return buildDesc;
    }


    /**
     * Sets the buildDesc value for this CreateProject.
     * 
     * @param buildDesc
     */
    public void setBuildDesc(java.lang.String buildDesc) {
        this.buildDesc = buildDesc;
    }


    /**
     * Gets the targetBuildDate value for this CreateProject.
     * 
     * @return targetBuildDate
     */
    public java.lang.String getTargetBuildDate() {
        return targetBuildDate;
    }


    /**
     * Sets the targetBuildDate value for this CreateProject.
     * 
     * @param targetBuildDate
     */
    public void setTargetBuildDate(java.lang.String targetBuildDate) {
        this.targetBuildDate = targetBuildDate;
    }


    /**
     * Gets the roleIds value for this CreateProject.
     * 
     * @return roleIds
     */
    public int[] getRoleIds() {
        return roleIds;
    }


    /**
     * Sets the roleIds value for this CreateProject.
     * 
     * @param roleIds
     */
    public void setRoleIds(int[] roleIds) {
        this.roleIds = roleIds;
    }

    public int getRoleIds(int i) {
        return this.roleIds[i];
    }

    public void setRoleIds(int i, int _value) {
        this.roleIds[i] = _value;
    }


    /**
     * Gets the userIds value for this CreateProject.
     * 
     * @return userIds
     */
    public int[] getUserIds() {
        return userIds;
    }


    /**
     * Sets the userIds value for this CreateProject.
     * 
     * @param userIds
     */
    public void setUserIds(int[] userIds) {
        this.userIds = userIds;
    }

    public int getUserIds(int i) {
        return this.userIds[i];
    }

    public void setUserIds(int i, int _value) {
        this.userIds[i] = _value;
    }


    /**
     * Gets the copySampleData value for this CreateProject.
     * 
     * @return copySampleData
     */
    public int getCopySampleData() {
        return copySampleData;
    }


    /**
     * Sets the copySampleData value for this CreateProject.
     * 
     * @param copySampleData
     */
    public void setCopySampleData(int copySampleData) {
        this.copySampleData = copySampleData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateProject)) return false;
        CreateProject other = (CreateProject) obj;
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
            ((this.projectName==null && other.getProjectName()==null) || 
             (this.projectName!=null &&
              this.projectName.equals(other.getProjectName()))) &&
            ((this.projectDesc==null && other.getProjectDesc()==null) || 
             (this.projectDesc!=null &&
              this.projectDesc.equals(other.getProjectDesc()))) &&
            ((this.releaseName==null && other.getReleaseName()==null) || 
             (this.releaseName!=null &&
              this.releaseName.equals(other.getReleaseName()))) &&
            ((this.releaseDesc==null && other.getReleaseDesc()==null) || 
             (this.releaseDesc!=null &&
              this.releaseDesc.equals(other.getReleaseDesc()))) &&
            ((this.targetReleaseDate==null && other.getTargetReleaseDate()==null) || 
             (this.targetReleaseDate!=null &&
              this.targetReleaseDate.equals(other.getTargetReleaseDate()))) &&
            ((this.buildName==null && other.getBuildName()==null) || 
             (this.buildName!=null &&
              this.buildName.equals(other.getBuildName()))) &&
            ((this.buildDesc==null && other.getBuildDesc()==null) || 
             (this.buildDesc!=null &&
              this.buildDesc.equals(other.getBuildDesc()))) &&
            ((this.targetBuildDate==null && other.getTargetBuildDate()==null) || 
             (this.targetBuildDate!=null &&
              this.targetBuildDate.equals(other.getTargetBuildDate()))) &&
            ((this.roleIds==null && other.getRoleIds()==null) || 
             (this.roleIds!=null &&
              java.util.Arrays.equals(this.roleIds, other.getRoleIds()))) &&
            ((this.userIds==null && other.getUserIds()==null) || 
             (this.userIds!=null &&
              java.util.Arrays.equals(this.userIds, other.getUserIds()))) &&
            this.copySampleData == other.getCopySampleData();
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
        if (getProjectName() != null) {
            _hashCode += getProjectName().hashCode();
        }
        if (getProjectDesc() != null) {
            _hashCode += getProjectDesc().hashCode();
        }
        if (getReleaseName() != null) {
            _hashCode += getReleaseName().hashCode();
        }
        if (getReleaseDesc() != null) {
            _hashCode += getReleaseDesc().hashCode();
        }
        if (getTargetReleaseDate() != null) {
            _hashCode += getTargetReleaseDate().hashCode();
        }
        if (getBuildName() != null) {
            _hashCode += getBuildName().hashCode();
        }
        if (getBuildDesc() != null) {
            _hashCode += getBuildDesc().hashCode();
        }
        if (getTargetBuildDate() != null) {
            _hashCode += getTargetBuildDate().hashCode();
        }
        if (getRoleIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRoleIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRoleIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUserIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUserIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUserIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getCopySampleData();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateProject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createProject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "projectName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "projectDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("releaseName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "releaseName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("releaseDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "releaseDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("targetReleaseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "targetReleaseDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "buildName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "buildDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("targetBuildDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "targetBuildDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("roleIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "roleIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "userIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("copySampleData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "copySampleData"));
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
