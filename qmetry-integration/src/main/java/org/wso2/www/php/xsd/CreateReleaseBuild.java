/**
 * CreateReleaseBuild.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class CreateReleaseBuild  implements java.io.Serializable {
    private java.lang.String token;

    private java.lang.String releaseName;

    private java.lang.String releaseDesc;

    private java.lang.String targetReleaseDate;

    private java.lang.String buildName;

    private java.lang.String buildDesc;

    private java.lang.String targetBuildDate;

    public CreateReleaseBuild() {
    }

    public CreateReleaseBuild(
           java.lang.String token,
           java.lang.String releaseName,
           java.lang.String releaseDesc,
           java.lang.String targetReleaseDate,
           java.lang.String buildName,
           java.lang.String buildDesc,
           java.lang.String targetBuildDate) {
           this.token = token;
           this.releaseName = releaseName;
           this.releaseDesc = releaseDesc;
           this.targetReleaseDate = targetReleaseDate;
           this.buildName = buildName;
           this.buildDesc = buildDesc;
           this.targetBuildDate = targetBuildDate;
    }


    /**
     * Gets the token value for this CreateReleaseBuild.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this CreateReleaseBuild.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the releaseName value for this CreateReleaseBuild.
     * 
     * @return releaseName
     */
    public java.lang.String getReleaseName() {
        return releaseName;
    }


    /**
     * Sets the releaseName value for this CreateReleaseBuild.
     * 
     * @param releaseName
     */
    public void setReleaseName(java.lang.String releaseName) {
        this.releaseName = releaseName;
    }


    /**
     * Gets the releaseDesc value for this CreateReleaseBuild.
     * 
     * @return releaseDesc
     */
    public java.lang.String getReleaseDesc() {
        return releaseDesc;
    }


    /**
     * Sets the releaseDesc value for this CreateReleaseBuild.
     * 
     * @param releaseDesc
     */
    public void setReleaseDesc(java.lang.String releaseDesc) {
        this.releaseDesc = releaseDesc;
    }


    /**
     * Gets the targetReleaseDate value for this CreateReleaseBuild.
     * 
     * @return targetReleaseDate
     */
    public java.lang.String getTargetReleaseDate() {
        return targetReleaseDate;
    }


    /**
     * Sets the targetReleaseDate value for this CreateReleaseBuild.
     * 
     * @param targetReleaseDate
     */
    public void setTargetReleaseDate(java.lang.String targetReleaseDate) {
        this.targetReleaseDate = targetReleaseDate;
    }


    /**
     * Gets the buildName value for this CreateReleaseBuild.
     * 
     * @return buildName
     */
    public java.lang.String getBuildName() {
        return buildName;
    }


    /**
     * Sets the buildName value for this CreateReleaseBuild.
     * 
     * @param buildName
     */
    public void setBuildName(java.lang.String buildName) {
        this.buildName = buildName;
    }


    /**
     * Gets the buildDesc value for this CreateReleaseBuild.
     * 
     * @return buildDesc
     */
    public java.lang.String getBuildDesc() {
        return buildDesc;
    }


    /**
     * Sets the buildDesc value for this CreateReleaseBuild.
     * 
     * @param buildDesc
     */
    public void setBuildDesc(java.lang.String buildDesc) {
        this.buildDesc = buildDesc;
    }


    /**
     * Gets the targetBuildDate value for this CreateReleaseBuild.
     * 
     * @return targetBuildDate
     */
    public java.lang.String getTargetBuildDate() {
        return targetBuildDate;
    }


    /**
     * Sets the targetBuildDate value for this CreateReleaseBuild.
     * 
     * @param targetBuildDate
     */
    public void setTargetBuildDate(java.lang.String targetBuildDate) {
        this.targetBuildDate = targetBuildDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateReleaseBuild)) return false;
        CreateReleaseBuild other = (CreateReleaseBuild) obj;
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
              this.targetBuildDate.equals(other.getTargetBuildDate())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateReleaseBuild.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createReleaseBuild"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
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
