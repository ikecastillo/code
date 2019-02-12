/**
 * SetScope.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class SetScope  implements java.io.Serializable {
    private java.lang.String token;

    private java.lang.String projectName;

    private java.lang.String releaseName;

    private java.lang.String buildName;

    public SetScope() {
    }

    public SetScope(
           java.lang.String token,
           java.lang.String projectName,
           java.lang.String releaseName,
           java.lang.String buildName) {
           this.token = token;
           this.projectName = projectName;
           this.releaseName = releaseName;
           this.buildName = buildName;
    }


    /**
     * Gets the token value for this SetScope.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this SetScope.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the projectName value for this SetScope.
     * 
     * @return projectName
     */
    public java.lang.String getProjectName() {
        return projectName;
    }


    /**
     * Sets the projectName value for this SetScope.
     * 
     * @param projectName
     */
    public void setProjectName(java.lang.String projectName) {
        this.projectName = projectName;
    }


    /**
     * Gets the releaseName value for this SetScope.
     * 
     * @return releaseName
     */
    public java.lang.String getReleaseName() {
        return releaseName;
    }


    /**
     * Sets the releaseName value for this SetScope.
     * 
     * @param releaseName
     */
    public void setReleaseName(java.lang.String releaseName) {
        this.releaseName = releaseName;
    }


    /**
     * Gets the buildName value for this SetScope.
     * 
     * @return buildName
     */
    public java.lang.String getBuildName() {
        return buildName;
    }


    /**
     * Sets the buildName value for this SetScope.
     * 
     * @param buildName
     */
    public void setBuildName(java.lang.String buildName) {
        this.buildName = buildName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetScope)) return false;
        SetScope other = (SetScope) obj;
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
            ((this.releaseName==null && other.getReleaseName()==null) || 
             (this.releaseName!=null &&
              this.releaseName.equals(other.getReleaseName()))) &&
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
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        if (getProjectName() != null) {
            _hashCode += getProjectName().hashCode();
        }
        if (getReleaseName() != null) {
            _hashCode += getReleaseName().hashCode();
        }
        if (getBuildName() != null) {
            _hashCode += getBuildName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetScope.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">setScope"));
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
        elemField.setFieldName("releaseName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "releaseName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
