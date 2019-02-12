/**
 * RequirementEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class RequirementEntity  implements java.io.Serializable {
    private int id;

    private java.lang.String name;

    private java.lang.String objective;

    private java.lang.String coverageStatus;

    private java.lang.String component;

    private java.lang.String componentValue;

    private java.lang.String creationDate;

    private java.lang.String modifiedDate;

    private java.lang.String status;

    private java.lang.String statusValue;

    private java.lang.String assignedTo;

    private java.lang.String assignedToValue;

    private int versionNumber;

    private int folderId;

    private java.lang.String folderPath;

    private org.wso2.www.php.xsd.UserDefinedFieldArray[] requirementUdfs;

    public RequirementEntity() {
    }

    public RequirementEntity(
           int id,
           java.lang.String name,
           java.lang.String objective,
           java.lang.String coverageStatus,
           java.lang.String component,
           java.lang.String componentValue,
           java.lang.String creationDate,
           java.lang.String modifiedDate,
           java.lang.String status,
           java.lang.String statusValue,
           java.lang.String assignedTo,
           java.lang.String assignedToValue,
           int versionNumber,
           int folderId,
           java.lang.String folderPath,
           org.wso2.www.php.xsd.UserDefinedFieldArray[] requirementUdfs) {
           this.id = id;
           this.name = name;
           this.objective = objective;
           this.coverageStatus = coverageStatus;
           this.component = component;
           this.componentValue = componentValue;
           this.creationDate = creationDate;
           this.modifiedDate = modifiedDate;
           this.status = status;
           this.statusValue = statusValue;
           this.assignedTo = assignedTo;
           this.assignedToValue = assignedToValue;
           this.versionNumber = versionNumber;
           this.folderId = folderId;
           this.folderPath = folderPath;
           this.requirementUdfs = requirementUdfs;
    }


    /**
     * Gets the id value for this RequirementEntity.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this RequirementEntity.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the name value for this RequirementEntity.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this RequirementEntity.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the objective value for this RequirementEntity.
     * 
     * @return objective
     */
    public java.lang.String getObjective() {
        return objective;
    }


    /**
     * Sets the objective value for this RequirementEntity.
     * 
     * @param objective
     */
    public void setObjective(java.lang.String objective) {
        this.objective = objective;
    }


    /**
     * Gets the coverageStatus value for this RequirementEntity.
     * 
     * @return coverageStatus
     */
    public java.lang.String getCoverageStatus() {
        return coverageStatus;
    }


    /**
     * Sets the coverageStatus value for this RequirementEntity.
     * 
     * @param coverageStatus
     */
    public void setCoverageStatus(java.lang.String coverageStatus) {
        this.coverageStatus = coverageStatus;
    }


    /**
     * Gets the component value for this RequirementEntity.
     * 
     * @return component
     */
    public java.lang.String getComponent() {
        return component;
    }


    /**
     * Sets the component value for this RequirementEntity.
     * 
     * @param component
     */
    public void setComponent(java.lang.String component) {
        this.component = component;
    }


    /**
     * Gets the componentValue value for this RequirementEntity.
     * 
     * @return componentValue
     */
    public java.lang.String getComponentValue() {
        return componentValue;
    }


    /**
     * Sets the componentValue value for this RequirementEntity.
     * 
     * @param componentValue
     */
    public void setComponentValue(java.lang.String componentValue) {
        this.componentValue = componentValue;
    }


    /**
     * Gets the creationDate value for this RequirementEntity.
     * 
     * @return creationDate
     */
    public java.lang.String getCreationDate() {
        return creationDate;
    }


    /**
     * Sets the creationDate value for this RequirementEntity.
     * 
     * @param creationDate
     */
    public void setCreationDate(java.lang.String creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Gets the modifiedDate value for this RequirementEntity.
     * 
     * @return modifiedDate
     */
    public java.lang.String getModifiedDate() {
        return modifiedDate;
    }


    /**
     * Sets the modifiedDate value for this RequirementEntity.
     * 
     * @param modifiedDate
     */
    public void setModifiedDate(java.lang.String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    /**
     * Gets the status value for this RequirementEntity.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this RequirementEntity.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the statusValue value for this RequirementEntity.
     * 
     * @return statusValue
     */
    public java.lang.String getStatusValue() {
        return statusValue;
    }


    /**
     * Sets the statusValue value for this RequirementEntity.
     * 
     * @param statusValue
     */
    public void setStatusValue(java.lang.String statusValue) {
        this.statusValue = statusValue;
    }


    /**
     * Gets the assignedTo value for this RequirementEntity.
     * 
     * @return assignedTo
     */
    public java.lang.String getAssignedTo() {
        return assignedTo;
    }


    /**
     * Sets the assignedTo value for this RequirementEntity.
     * 
     * @param assignedTo
     */
    public void setAssignedTo(java.lang.String assignedTo) {
        this.assignedTo = assignedTo;
    }


    /**
     * Gets the assignedToValue value for this RequirementEntity.
     * 
     * @return assignedToValue
     */
    public java.lang.String getAssignedToValue() {
        return assignedToValue;
    }


    /**
     * Sets the assignedToValue value for this RequirementEntity.
     * 
     * @param assignedToValue
     */
    public void setAssignedToValue(java.lang.String assignedToValue) {
        this.assignedToValue = assignedToValue;
    }


    /**
     * Gets the versionNumber value for this RequirementEntity.
     * 
     * @return versionNumber
     */
    public int getVersionNumber() {
        return versionNumber;
    }


    /**
     * Sets the versionNumber value for this RequirementEntity.
     * 
     * @param versionNumber
     */
    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }


    /**
     * Gets the folderId value for this RequirementEntity.
     * 
     * @return folderId
     */
    public int getFolderId() {
        return folderId;
    }


    /**
     * Sets the folderId value for this RequirementEntity.
     * 
     * @param folderId
     */
    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }


    /**
     * Gets the folderPath value for this RequirementEntity.
     * 
     * @return folderPath
     */
    public java.lang.String getFolderPath() {
        return folderPath;
    }


    /**
     * Sets the folderPath value for this RequirementEntity.
     * 
     * @param folderPath
     */
    public void setFolderPath(java.lang.String folderPath) {
        this.folderPath = folderPath;
    }


    /**
     * Gets the requirementUdfs value for this RequirementEntity.
     * 
     * @return requirementUdfs
     */
    public org.wso2.www.php.xsd.UserDefinedFieldArray[] getRequirementUdfs() {
        return requirementUdfs;
    }


    /**
     * Sets the requirementUdfs value for this RequirementEntity.
     * 
     * @param requirementUdfs
     */
    public void setRequirementUdfs(org.wso2.www.php.xsd.UserDefinedFieldArray[] requirementUdfs) {
        this.requirementUdfs = requirementUdfs;
    }

    public org.wso2.www.php.xsd.UserDefinedFieldArray getRequirementUdfs(int i) {
        return this.requirementUdfs[i];
    }

    public void setRequirementUdfs(int i, org.wso2.www.php.xsd.UserDefinedFieldArray _value) {
        this.requirementUdfs[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RequirementEntity)) return false;
        RequirementEntity other = (RequirementEntity) obj;
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
            ((this.objective==null && other.getObjective()==null) || 
             (this.objective!=null &&
              this.objective.equals(other.getObjective()))) &&
            ((this.coverageStatus==null && other.getCoverageStatus()==null) || 
             (this.coverageStatus!=null &&
              this.coverageStatus.equals(other.getCoverageStatus()))) &&
            ((this.component==null && other.getComponent()==null) || 
             (this.component!=null &&
              this.component.equals(other.getComponent()))) &&
            ((this.componentValue==null && other.getComponentValue()==null) || 
             (this.componentValue!=null &&
              this.componentValue.equals(other.getComponentValue()))) &&
            ((this.creationDate==null && other.getCreationDate()==null) || 
             (this.creationDate!=null &&
              this.creationDate.equals(other.getCreationDate()))) &&
            ((this.modifiedDate==null && other.getModifiedDate()==null) || 
             (this.modifiedDate!=null &&
              this.modifiedDate.equals(other.getModifiedDate()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.statusValue==null && other.getStatusValue()==null) || 
             (this.statusValue!=null &&
              this.statusValue.equals(other.getStatusValue()))) &&
            ((this.assignedTo==null && other.getAssignedTo()==null) || 
             (this.assignedTo!=null &&
              this.assignedTo.equals(other.getAssignedTo()))) &&
            ((this.assignedToValue==null && other.getAssignedToValue()==null) || 
             (this.assignedToValue!=null &&
              this.assignedToValue.equals(other.getAssignedToValue()))) &&
            this.versionNumber == other.getVersionNumber() &&
            this.folderId == other.getFolderId() &&
            ((this.folderPath==null && other.getFolderPath()==null) || 
             (this.folderPath!=null &&
              this.folderPath.equals(other.getFolderPath()))) &&
            ((this.requirementUdfs==null && other.getRequirementUdfs()==null) || 
             (this.requirementUdfs!=null &&
              java.util.Arrays.equals(this.requirementUdfs, other.getRequirementUdfs())));
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
        if (getObjective() != null) {
            _hashCode += getObjective().hashCode();
        }
        if (getCoverageStatus() != null) {
            _hashCode += getCoverageStatus().hashCode();
        }
        if (getComponent() != null) {
            _hashCode += getComponent().hashCode();
        }
        if (getComponentValue() != null) {
            _hashCode += getComponentValue().hashCode();
        }
        if (getCreationDate() != null) {
            _hashCode += getCreationDate().hashCode();
        }
        if (getModifiedDate() != null) {
            _hashCode += getModifiedDate().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStatusValue() != null) {
            _hashCode += getStatusValue().hashCode();
        }
        if (getAssignedTo() != null) {
            _hashCode += getAssignedTo().hashCode();
        }
        if (getAssignedToValue() != null) {
            _hashCode += getAssignedToValue().hashCode();
        }
        _hashCode += getVersionNumber();
        _hashCode += getFolderId();
        if (getFolderPath() != null) {
            _hashCode += getFolderPath().hashCode();
        }
        if (getRequirementUdfs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRequirementUdfs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRequirementUdfs(), i);
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
        new org.apache.axis.description.TypeDesc(RequirementEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "RequirementEntity"));
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
        elemField.setFieldName("objective");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "objective"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coverageStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "coverageStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("component");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "component"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("componentValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "componentValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "creationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "modifiedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "statusValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assignedTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "assignedTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assignedToValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "assignedToValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "versionNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "folderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "folderPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requirementUdfs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "requirementUdfs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "UserDefinedFieldArray"));
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
