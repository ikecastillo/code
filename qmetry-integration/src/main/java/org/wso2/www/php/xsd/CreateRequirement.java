/**
 * CreateRequirement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class CreateRequirement  implements java.io.Serializable {
    private java.lang.String token;

    private java.lang.String parentType;

    private int parentId;

    private java.lang.String requirementName;

    private java.lang.String objective;

    private int component;

    private int status;

    private int assignedTo;

    private org.wso2.www.php.xsd.KeyValuePair[] udfInfo;

    public CreateRequirement() {
    }

    public CreateRequirement(
           java.lang.String token,
           java.lang.String parentType,
           int parentId,
           java.lang.String requirementName,
           java.lang.String objective,
           int component,
           int status,
           int assignedTo,
           org.wso2.www.php.xsd.KeyValuePair[] udfInfo) {
           this.token = token;
           this.parentType = parentType;
           this.parentId = parentId;
           this.requirementName = requirementName;
           this.objective = objective;
           this.component = component;
           this.status = status;
           this.assignedTo = assignedTo;
           this.udfInfo = udfInfo;
    }


    /**
     * Gets the token value for this CreateRequirement.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this CreateRequirement.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the parentType value for this CreateRequirement.
     * 
     * @return parentType
     */
    public java.lang.String getParentType() {
        return parentType;
    }


    /**
     * Sets the parentType value for this CreateRequirement.
     * 
     * @param parentType
     */
    public void setParentType(java.lang.String parentType) {
        this.parentType = parentType;
    }


    /**
     * Gets the parentId value for this CreateRequirement.
     * 
     * @return parentId
     */
    public int getParentId() {
        return parentId;
    }


    /**
     * Sets the parentId value for this CreateRequirement.
     * 
     * @param parentId
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }


    /**
     * Gets the requirementName value for this CreateRequirement.
     * 
     * @return requirementName
     */
    public java.lang.String getRequirementName() {
        return requirementName;
    }


    /**
     * Sets the requirementName value for this CreateRequirement.
     * 
     * @param requirementName
     */
    public void setRequirementName(java.lang.String requirementName) {
        this.requirementName = requirementName;
    }


    /**
     * Gets the objective value for this CreateRequirement.
     * 
     * @return objective
     */
    public java.lang.String getObjective() {
        return objective;
    }


    /**
     * Sets the objective value for this CreateRequirement.
     * 
     * @param objective
     */
    public void setObjective(java.lang.String objective) {
        this.objective = objective;
    }


    /**
     * Gets the component value for this CreateRequirement.
     * 
     * @return component
     */
    public int getComponent() {
        return component;
    }


    /**
     * Sets the component value for this CreateRequirement.
     * 
     * @param component
     */
    public void setComponent(int component) {
        this.component = component;
    }


    /**
     * Gets the status value for this CreateRequirement.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this CreateRequirement.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the assignedTo value for this CreateRequirement.
     * 
     * @return assignedTo
     */
    public int getAssignedTo() {
        return assignedTo;
    }


    /**
     * Sets the assignedTo value for this CreateRequirement.
     * 
     * @param assignedTo
     */
    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }


    /**
     * Gets the udfInfo value for this CreateRequirement.
     * 
     * @return udfInfo
     */
    public org.wso2.www.php.xsd.KeyValuePair[] getUdfInfo() {
        return udfInfo;
    }


    /**
     * Sets the udfInfo value for this CreateRequirement.
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
        if (!(obj instanceof CreateRequirement)) return false;
        CreateRequirement other = (CreateRequirement) obj;
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
            ((this.parentType==null && other.getParentType()==null) || 
             (this.parentType!=null &&
              this.parentType.equals(other.getParentType()))) &&
            this.parentId == other.getParentId() &&
            ((this.requirementName==null && other.getRequirementName()==null) || 
             (this.requirementName!=null &&
              this.requirementName.equals(other.getRequirementName()))) &&
            ((this.objective==null && other.getObjective()==null) || 
             (this.objective!=null &&
              this.objective.equals(other.getObjective()))) &&
            this.component == other.getComponent() &&
            this.status == other.getStatus() &&
            this.assignedTo == other.getAssignedTo() &&
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
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        if (getParentType() != null) {
            _hashCode += getParentType().hashCode();
        }
        _hashCode += getParentId();
        if (getRequirementName() != null) {
            _hashCode += getRequirementName().hashCode();
        }
        if (getObjective() != null) {
            _hashCode += getObjective().hashCode();
        }
        _hashCode += getComponent();
        _hashCode += getStatus();
        _hashCode += getAssignedTo();
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
        new org.apache.axis.description.TypeDesc(CreateRequirement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">createRequirement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "parentType"));
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
        elemField.setFieldName("requirementName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "requirementName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objective");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "objective"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("component");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "component"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assignedTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "assignedTo"));
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
