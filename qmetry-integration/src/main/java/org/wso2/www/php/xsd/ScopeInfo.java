/**
 * ScopeInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ScopeInfo  implements java.io.Serializable {
    private int projectId;

    private java.lang.String projectName;

    private int releaseId;

    private java.lang.String releaseName;

    private int buildId;

    private java.lang.String buildName;

    public ScopeInfo() {
    }

    public ScopeInfo(
           int projectId,
           java.lang.String projectName,
           int releaseId,
           java.lang.String releaseName,
           int buildId,
           java.lang.String buildName) {
           this.projectId = projectId;
           this.projectName = projectName;
           this.releaseId = releaseId;
           this.releaseName = releaseName;
           this.buildId = buildId;
           this.buildName = buildName;
    }


    /**
     * Gets the projectId value for this ScopeInfo.
     * 
     * @return projectId
     */
    public int getProjectId() {
        return projectId;
    }


    /**
     * Sets the projectId value for this ScopeInfo.
     * 
     * @param projectId
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


    /**
     * Gets the projectName value for this ScopeInfo.
     * 
     * @return projectName
     */
    public java.lang.String getProjectName() {
        return projectName;
    }


    /**
     * Sets the projectName value for this ScopeInfo.
     * 
     * @param projectName
     */
    public void setProjectName(java.lang.String projectName) {
        this.projectName = projectName;
    }


    /**
     * Gets the releaseId value for this ScopeInfo.
     * 
     * @return releaseId
     */
    public int getReleaseId() {
        return releaseId;
    }


    /**
     * Sets the releaseId value for this ScopeInfo.
     * 
     * @param releaseId
     */
    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
    }


    /**
     * Gets the releaseName value for this ScopeInfo.
     * 
     * @return releaseName
     */
    public java.lang.String getReleaseName() {
        return releaseName;
    }


    /**
     * Sets the releaseName value for this ScopeInfo.
     * 
     * @param releaseName
     */
    public void setReleaseName(java.lang.String releaseName) {
        this.releaseName = releaseName;
    }


    /**
     * Gets the buildId value for this ScopeInfo.
     * 
     * @return buildId
     */
    public int getBuildId() {
        return buildId;
    }


    /**
     * Sets the buildId value for this ScopeInfo.
     * 
     * @param buildId
     */
    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }


    /**
     * Gets the buildName value for this ScopeInfo.
     * 
     * @return buildName
     */
    public java.lang.String getBuildName() {
        return buildName;
    }


    /**
     * Sets the buildName value for this ScopeInfo.
     * 
     * @param buildName
     */
    public void setBuildName(java.lang.String buildName) {
        this.buildName = buildName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ScopeInfo)) return false;
        ScopeInfo other = (ScopeInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.projectId == other.getProjectId() &&
            ((this.projectName==null && other.getProjectName()==null) || 
             (this.projectName!=null &&
              this.projectName.equals(other.getProjectName()))) &&
            this.releaseId == other.getReleaseId() &&
            ((this.releaseName==null && other.getReleaseName()==null) || 
             (this.releaseName!=null &&
              this.releaseName.equals(other.getReleaseName()))) &&
            this.buildId == other.getBuildId() &&
            ((this.buildName==null && other.getBuildName()==null) || 
             (this.buildName!=null &&
              this.buildName.equals(other.getBuildName())));
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
        _hashCode += getProjectId();
        if (getProjectName() != null) {
            _hashCode += getProjectName().hashCode();
        }
        _hashCode += getReleaseId();
        if (getReleaseName() != null) {
            _hashCode += getReleaseName().hashCode();
        }
        _hashCode += getBuildId();
        if (getBuildName() != null) {
            _hashCode += getBuildName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ScopeInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "ScopeInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "projectId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "projectName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("releaseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "releaseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("releaseName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "releaseName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "buildId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "buildName"));
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
