/**
 * RequirementElements.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class RequirementElements  implements java.io.Serializable {
    private int requirementId;

    private java.lang.String requirementName;

    private java.lang.String requirementDescription;

    private int component;

    private int requirementStatus;

    private int assignedUser;

    private org.wso2.www.php.xsd.KeyValuePair[] udfInfo;

    public RequirementElements() {
    }

    public RequirementElements(
           int requirementId,
           java.lang.String requirementName,
           java.lang.String requirementDescription,
           int component,
           int requirementStatus,
           int assignedUser,
           org.wso2.www.php.xsd.KeyValuePair[] udfInfo) {
           this.requirementId = requirementId;
           this.requirementName = requirementName;
           this.requirementDescription = requirementDescription;
           this.component = component;
           this.requirementStatus = requirementStatus;
           this.assignedUser = assignedUser;
           this.udfInfo = udfInfo;
    }


    /**
     * Gets the requirementId value for this RequirementElements.
     * 
     * @return requirementId
     */
    public int getRequirementId() {
        return requirementId;
    }


    /**
     * Sets the requirementId value for this RequirementElements.
     * 
     * @param requirementId
     */
    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }


    /**
     * Gets the requirementName value for this RequirementElements.
     * 
     * @return requirementName
     */
    public java.lang.String getRequirementName() {
        return requirementName;
    }


    /**
     * Sets the requirementName value for this RequirementElements.
     * 
     * @param requirementName
     */
    public void setRequirementName(java.lang.String requirementName) {
        this.requirementName = requirementName;
    }


    /**
     * Gets the requirementDescription value for this RequirementElements.
     * 
     * @return requirementDescription
     */
    public java.lang.String getRequirementDescription() {
        return requirementDescription;
    }


    /**
     * Sets the requirementDescription value for this RequirementElements.
     * 
     * @param requirementDescription
     */
    public void setRequirementDescription(java.lang.String requirementDescription) {
        this.requirementDescription = requirementDescription;
    }


    /**
     * Gets the component value for this RequirementElements.
     * 
     * @return component
     */
    public int getComponent() {
        return component;
    }


    /**
     * Sets the component value for this RequirementElements.
     * 
     * @param component
     */
    public void setComponent(int component) {
        this.component = component;
    }


    /**
     * Gets the requirementStatus value for this RequirementElements.
     * 
     * @return requirementStatus
     */
    public int getRequirementStatus() {
        return requirementStatus;
    }


    /**
     * Sets the requirementStatus value for this RequirementElements.
     * 
     * @param requirementStatus
     */
    public void setRequirementStatus(int requirementStatus) {
        this.requirementStatus = requirementStatus;
    }


    /**
     * Gets the assignedUser value for this RequirementElements.
     * 
     * @return assignedUser
     */
    public int getAssignedUser() {
        return assignedUser;
    }


    /**
     * Sets the assignedUser value for this RequirementElements.
     * 
     * @param assignedUser
     */
    public void setAssignedUser(int assignedUser) {
        this.assignedUser = assignedUser;
    }


    /**
     * Gets the udfInfo value for this RequirementElements.
     * 
     * @return udfInfo
     */
    public org.wso2.www.php.xsd.KeyValuePair[] getUdfInfo() {
        return udfInfo;
    }


    /**
     * Sets the udfInfo value for this RequirementElements.
     * 
     * @param udfInfo
     */
    public void setUdfInfo(org.wso2.www.php.xsd.KeyValuePair[] udfInfo) {
        this.udfInfo = udfInfo;
    }

    public org.wso2.www.php.xsd.KeyValuePair getUdfInfo(int i) {
        return this.udfInfo[i];
    }

    public void setUdfInfo(int i, org.wso2.www.php.xsd.KeyValuePair _value) {
        this.udfInfo[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RequirementElements)) return false;
        RequirementElements other = (RequirementElements) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.requirementId == other.getRequirementId() &&
            ((this.requirementName==null && other.getRequirementName()==null) || 
             (this.requirementName!=null &&
              this.requirementName.equals(other.getRequirementName()))) &&
            ((this.requirementDescription==null && other.getRequirementDescription()==null) || 
             (this.requirementDescription!=null &&
              this.requirementDescription.equals(other.getRequirementDescription()))) &&
            this.component == other.getComponent() &&
            this.requirementStatus == other.getRequirementStatus() &&
            this.assignedUser == other.getAssignedUser() &&
            ((this.udfInfo==null && other.getUdfInfo()==null) || 
             (this.udfInfo!=null &&
              java.util.Arrays.equals(this.udfInfo, other.getUdfInfo())));
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
        _hashCode += getRequirementId();
        if (getRequirementName() != null) {
            _hashCode += getRequirementName().hashCode();
        }
        if (getRequirementDescription() != null) {
            _hashCode += getRequirementDescription().hashCode();
        }
        _hashCode += getComponent();
        _hashCode += getRequirementStatus();
        _hashCode += getAssignedUser();
        if (getUdfInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUdfInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUdfInfo(), i);
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
        new org.apache.axis.description.TypeDesc(RequirementElements.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "RequirementElements"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requirementId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "RequirementId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requirementName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "RequirementName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requirementDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "RequirementDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("component");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "Component"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requirementStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "RequirementStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assignedUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "AssignedUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "udfInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "KeyValuePair"));
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
