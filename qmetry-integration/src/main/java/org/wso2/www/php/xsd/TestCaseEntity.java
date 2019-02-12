/**
 * TestCaseEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestCaseEntity  implements java.io.Serializable {
    private int id;

    private java.lang.String name;

    private java.lang.String objective;

    private java.lang.String component;

    private java.lang.String componentValue;

    private java.lang.String creationDate;

    private java.lang.String modifiedDate;

    private java.lang.String status;

    private java.lang.String statusValue;

    private java.lang.String assignedTo;

    private java.lang.String assignedToValue;

    private java.lang.String preCondition;

    private java.lang.String postCondition;

    private java.lang.String designer;

    private java.lang.String designerValue;

    private java.lang.String priority;

    private java.lang.String priorityValue;

    private java.lang.String executionTime;

    private java.lang.String type;

    private java.lang.String typeValue;

    private java.lang.String modifiedReason;

    private java.lang.String modifiedReasonValue;

    private java.lang.String testingType;

    private java.lang.String testingTypeValue;

    private java.lang.String version;

    private java.lang.String testScriptName;

    private int folderId;

    private java.lang.String folderPath;

    private org.wso2.www.php.xsd.UserDefinedFieldArray[] testCaseUdfs;

    public TestCaseEntity() {
    }

    public TestCaseEntity(
           int id,
           java.lang.String name,
           java.lang.String objective,
           java.lang.String component,
           java.lang.String componentValue,
           java.lang.String creationDate,
           java.lang.String modifiedDate,
           java.lang.String status,
           java.lang.String statusValue,
           java.lang.String assignedTo,
           java.lang.String assignedToValue,
           java.lang.String preCondition,
           java.lang.String postCondition,
           java.lang.String designer,
           java.lang.String designerValue,
           java.lang.String priority,
           java.lang.String priorityValue,
           java.lang.String executionTime,
           java.lang.String type,
           java.lang.String typeValue,
           java.lang.String modifiedReason,
           java.lang.String modifiedReasonValue,
           java.lang.String testingType,
           java.lang.String testingTypeValue,
           java.lang.String version,
           java.lang.String testScriptName,
           int folderId,
           java.lang.String folderPath,
           org.wso2.www.php.xsd.UserDefinedFieldArray[] testCaseUdfs) {
           this.id = id;
           this.name = name;
           this.objective = objective;
           this.component = component;
           this.componentValue = componentValue;
           this.creationDate = creationDate;
           this.modifiedDate = modifiedDate;
           this.status = status;
           this.statusValue = statusValue;
           this.assignedTo = assignedTo;
           this.assignedToValue = assignedToValue;
           this.preCondition = preCondition;
           this.postCondition = postCondition;
           this.designer = designer;
           this.designerValue = designerValue;
           this.priority = priority;
           this.priorityValue = priorityValue;
           this.executionTime = executionTime;
           this.type = type;
           this.typeValue = typeValue;
           this.modifiedReason = modifiedReason;
           this.modifiedReasonValue = modifiedReasonValue;
           this.testingType = testingType;
           this.testingTypeValue = testingTypeValue;
           this.version = version;
           this.testScriptName = testScriptName;
           this.folderId = folderId;
           this.folderPath = folderPath;
           this.testCaseUdfs = testCaseUdfs;
    }


    /**
     * Gets the id value for this TestCaseEntity.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this TestCaseEntity.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the name value for this TestCaseEntity.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this TestCaseEntity.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the objective value for this TestCaseEntity.
     * 
     * @return objective
     */
    public java.lang.String getObjective() {
        return objective;
    }


    /**
     * Sets the objective value for this TestCaseEntity.
     * 
     * @param objective
     */
    public void setObjective(java.lang.String objective) {
        this.objective = objective;
    }


    /**
     * Gets the component value for this TestCaseEntity.
     * 
     * @return component
     */
    public java.lang.String getComponent() {
        return component;
    }


    /**
     * Sets the component value for this TestCaseEntity.
     * 
     * @param component
     */
    public void setComponent(java.lang.String component) {
        this.component = component;
    }


    /**
     * Gets the componentValue value for this TestCaseEntity.
     * 
     * @return componentValue
     */
    public java.lang.String getComponentValue() {
        return componentValue;
    }


    /**
     * Sets the componentValue value for this TestCaseEntity.
     * 
     * @param componentValue
     */
    public void setComponentValue(java.lang.String componentValue) {
        this.componentValue = componentValue;
    }


    /**
     * Gets the creationDate value for this TestCaseEntity.
     * 
     * @return creationDate
     */
    public java.lang.String getCreationDate() {
        return creationDate;
    }


    /**
     * Sets the creationDate value for this TestCaseEntity.
     * 
     * @param creationDate
     */
    public void setCreationDate(java.lang.String creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Gets the modifiedDate value for this TestCaseEntity.
     * 
     * @return modifiedDate
     */
    public java.lang.String getModifiedDate() {
        return modifiedDate;
    }


    /**
     * Sets the modifiedDate value for this TestCaseEntity.
     * 
     * @param modifiedDate
     */
    public void setModifiedDate(java.lang.String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    /**
     * Gets the status value for this TestCaseEntity.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this TestCaseEntity.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the statusValue value for this TestCaseEntity.
     * 
     * @return statusValue
     */
    public java.lang.String getStatusValue() {
        return statusValue;
    }


    /**
     * Sets the statusValue value for this TestCaseEntity.
     * 
     * @param statusValue
     */
    public void setStatusValue(java.lang.String statusValue) {
        this.statusValue = statusValue;
    }


    /**
     * Gets the assignedTo value for this TestCaseEntity.
     * 
     * @return assignedTo
     */
    public java.lang.String getAssignedTo() {
        return assignedTo;
    }


    /**
     * Sets the assignedTo value for this TestCaseEntity.
     * 
     * @param assignedTo
     */
    public void setAssignedTo(java.lang.String assignedTo) {
        this.assignedTo = assignedTo;
    }


    /**
     * Gets the assignedToValue value for this TestCaseEntity.
     * 
     * @return assignedToValue
     */
    public java.lang.String getAssignedToValue() {
        return assignedToValue;
    }


    /**
     * Sets the assignedToValue value for this TestCaseEntity.
     * 
     * @param assignedToValue
     */
    public void setAssignedToValue(java.lang.String assignedToValue) {
        this.assignedToValue = assignedToValue;
    }


    /**
     * Gets the preCondition value for this TestCaseEntity.
     * 
     * @return preCondition
     */
    public java.lang.String getPreCondition() {
        return preCondition;
    }


    /**
     * Sets the preCondition value for this TestCaseEntity.
     * 
     * @param preCondition
     */
    public void setPreCondition(java.lang.String preCondition) {
        this.preCondition = preCondition;
    }


    /**
     * Gets the postCondition value for this TestCaseEntity.
     * 
     * @return postCondition
     */
    public java.lang.String getPostCondition() {
        return postCondition;
    }


    /**
     * Sets the postCondition value for this TestCaseEntity.
     * 
     * @param postCondition
     */
    public void setPostCondition(java.lang.String postCondition) {
        this.postCondition = postCondition;
    }


    /**
     * Gets the designer value for this TestCaseEntity.
     * 
     * @return designer
     */
    public java.lang.String getDesigner() {
        return designer;
    }


    /**
     * Sets the designer value for this TestCaseEntity.
     * 
     * @param designer
     */
    public void setDesigner(java.lang.String designer) {
        this.designer = designer;
    }


    /**
     * Gets the designerValue value for this TestCaseEntity.
     * 
     * @return designerValue
     */
    public java.lang.String getDesignerValue() {
        return designerValue;
    }


    /**
     * Sets the designerValue value for this TestCaseEntity.
     * 
     * @param designerValue
     */
    public void setDesignerValue(java.lang.String designerValue) {
        this.designerValue = designerValue;
    }


    /**
     * Gets the priority value for this TestCaseEntity.
     * 
     * @return priority
     */
    public java.lang.String getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this TestCaseEntity.
     * 
     * @param priority
     */
    public void setPriority(java.lang.String priority) {
        this.priority = priority;
    }


    /**
     * Gets the priorityValue value for this TestCaseEntity.
     * 
     * @return priorityValue
     */
    public java.lang.String getPriorityValue() {
        return priorityValue;
    }


    /**
     * Sets the priorityValue value for this TestCaseEntity.
     * 
     * @param priorityValue
     */
    public void setPriorityValue(java.lang.String priorityValue) {
        this.priorityValue = priorityValue;
    }


    /**
     * Gets the executionTime value for this TestCaseEntity.
     * 
     * @return executionTime
     */
    public java.lang.String getExecutionTime() {
        return executionTime;
    }


    /**
     * Sets the executionTime value for this TestCaseEntity.
     * 
     * @param executionTime
     */
    public void setExecutionTime(java.lang.String executionTime) {
        this.executionTime = executionTime;
    }


    /**
     * Gets the type value for this TestCaseEntity.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this TestCaseEntity.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the typeValue value for this TestCaseEntity.
     * 
     * @return typeValue
     */
    public java.lang.String getTypeValue() {
        return typeValue;
    }


    /**
     * Sets the typeValue value for this TestCaseEntity.
     * 
     * @param typeValue
     */
    public void setTypeValue(java.lang.String typeValue) {
        this.typeValue = typeValue;
    }


    /**
     * Gets the modifiedReason value for this TestCaseEntity.
     * 
     * @return modifiedReason
     */
    public java.lang.String getModifiedReason() {
        return modifiedReason;
    }


    /**
     * Sets the modifiedReason value for this TestCaseEntity.
     * 
     * @param modifiedReason
     */
    public void setModifiedReason(java.lang.String modifiedReason) {
        this.modifiedReason = modifiedReason;
    }


    /**
     * Gets the modifiedReasonValue value for this TestCaseEntity.
     * 
     * @return modifiedReasonValue
     */
    public java.lang.String getModifiedReasonValue() {
        return modifiedReasonValue;
    }


    /**
     * Sets the modifiedReasonValue value for this TestCaseEntity.
     * 
     * @param modifiedReasonValue
     */
    public void setModifiedReasonValue(java.lang.String modifiedReasonValue) {
        this.modifiedReasonValue = modifiedReasonValue;
    }


    /**
     * Gets the testingType value for this TestCaseEntity.
     * 
     * @return testingType
     */
    public java.lang.String getTestingType() {
        return testingType;
    }


    /**
     * Sets the testingType value for this TestCaseEntity.
     * 
     * @param testingType
     */
    public void setTestingType(java.lang.String testingType) {
        this.testingType = testingType;
    }


    /**
     * Gets the testingTypeValue value for this TestCaseEntity.
     * 
     * @return testingTypeValue
     */
    public java.lang.String getTestingTypeValue() {
        return testingTypeValue;
    }


    /**
     * Sets the testingTypeValue value for this TestCaseEntity.
     * 
     * @param testingTypeValue
     */
    public void setTestingTypeValue(java.lang.String testingTypeValue) {
        this.testingTypeValue = testingTypeValue;
    }


    /**
     * Gets the version value for this TestCaseEntity.
     * 
     * @return version
     */
    public java.lang.String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this TestCaseEntity.
     * 
     * @param version
     */
    public void setVersion(java.lang.String version) {
        this.version = version;
    }


    /**
     * Gets the testScriptName value for this TestCaseEntity.
     * 
     * @return testScriptName
     */
    public java.lang.String getTestScriptName() {
        return testScriptName;
    }


    /**
     * Sets the testScriptName value for this TestCaseEntity.
     * 
     * @param testScriptName
     */
    public void setTestScriptName(java.lang.String testScriptName) {
        this.testScriptName = testScriptName;
    }


    /**
     * Gets the folderId value for this TestCaseEntity.
     * 
     * @return folderId
     */
    public int getFolderId() {
        return folderId;
    }


    /**
     * Sets the folderId value for this TestCaseEntity.
     * 
     * @param folderId
     */
    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }


    /**
     * Gets the folderPath value for this TestCaseEntity.
     * 
     * @return folderPath
     */
    public java.lang.String getFolderPath() {
        return folderPath;
    }


    /**
     * Sets the folderPath value for this TestCaseEntity.
     * 
     * @param folderPath
     */
    public void setFolderPath(java.lang.String folderPath) {
        this.folderPath = folderPath;
    }


    /**
     * Gets the testCaseUdfs value for this TestCaseEntity.
     * 
     * @return testCaseUdfs
     */
    public org.wso2.www.php.xsd.UserDefinedFieldArray[] getTestCaseUdfs() {
        return testCaseUdfs;
    }


    /**
     * Sets the testCaseUdfs value for this TestCaseEntity.
     * 
     * @param testCaseUdfs
     */
    public void setTestCaseUdfs(org.wso2.www.php.xsd.UserDefinedFieldArray[] testCaseUdfs) {
        this.testCaseUdfs = testCaseUdfs;
    }

    public org.wso2.www.php.xsd.UserDefinedFieldArray getTestCaseUdfs(int i) {
        return this.testCaseUdfs[i];
    }

    public void setTestCaseUdfs(int i, org.wso2.www.php.xsd.UserDefinedFieldArray _value) {
        this.testCaseUdfs[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestCaseEntity)) return false;
        TestCaseEntity other = (TestCaseEntity) obj;
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
            ((this.preCondition==null && other.getPreCondition()==null) || 
             (this.preCondition!=null &&
              this.preCondition.equals(other.getPreCondition()))) &&
            ((this.postCondition==null && other.getPostCondition()==null) || 
             (this.postCondition!=null &&
              this.postCondition.equals(other.getPostCondition()))) &&
            ((this.designer==null && other.getDesigner()==null) || 
             (this.designer!=null &&
              this.designer.equals(other.getDesigner()))) &&
            ((this.designerValue==null && other.getDesignerValue()==null) || 
             (this.designerValue!=null &&
              this.designerValue.equals(other.getDesignerValue()))) &&
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            ((this.priorityValue==null && other.getPriorityValue()==null) || 
             (this.priorityValue!=null &&
              this.priorityValue.equals(other.getPriorityValue()))) &&
            ((this.executionTime==null && other.getExecutionTime()==null) || 
             (this.executionTime!=null &&
              this.executionTime.equals(other.getExecutionTime()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.typeValue==null && other.getTypeValue()==null) || 
             (this.typeValue!=null &&
              this.typeValue.equals(other.getTypeValue()))) &&
            ((this.modifiedReason==null && other.getModifiedReason()==null) || 
             (this.modifiedReason!=null &&
              this.modifiedReason.equals(other.getModifiedReason()))) &&
            ((this.modifiedReasonValue==null && other.getModifiedReasonValue()==null) || 
             (this.modifiedReasonValue!=null &&
              this.modifiedReasonValue.equals(other.getModifiedReasonValue()))) &&
            ((this.testingType==null && other.getTestingType()==null) || 
             (this.testingType!=null &&
              this.testingType.equals(other.getTestingType()))) &&
            ((this.testingTypeValue==null && other.getTestingTypeValue()==null) || 
             (this.testingTypeValue!=null &&
              this.testingTypeValue.equals(other.getTestingTypeValue()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion()))) &&
            ((this.testScriptName==null && other.getTestScriptName()==null) || 
             (this.testScriptName!=null &&
              this.testScriptName.equals(other.getTestScriptName()))) &&
            this.folderId == other.getFolderId() &&
            ((this.folderPath==null && other.getFolderPath()==null) || 
             (this.folderPath!=null &&
              this.folderPath.equals(other.getFolderPath()))) &&
            ((this.testCaseUdfs==null && other.getTestCaseUdfs()==null) || 
             (this.testCaseUdfs!=null &&
              java.util.Arrays.equals(this.testCaseUdfs, other.getTestCaseUdfs())));
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
        if (getPreCondition() != null) {
            _hashCode += getPreCondition().hashCode();
        }
        if (getPostCondition() != null) {
            _hashCode += getPostCondition().hashCode();
        }
        if (getDesigner() != null) {
            _hashCode += getDesigner().hashCode();
        }
        if (getDesignerValue() != null) {
            _hashCode += getDesignerValue().hashCode();
        }
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        if (getPriorityValue() != null) {
            _hashCode += getPriorityValue().hashCode();
        }
        if (getExecutionTime() != null) {
            _hashCode += getExecutionTime().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getTypeValue() != null) {
            _hashCode += getTypeValue().hashCode();
        }
        if (getModifiedReason() != null) {
            _hashCode += getModifiedReason().hashCode();
        }
        if (getModifiedReasonValue() != null) {
            _hashCode += getModifiedReasonValue().hashCode();
        }
        if (getTestingType() != null) {
            _hashCode += getTestingType().hashCode();
        }
        if (getTestingTypeValue() != null) {
            _hashCode += getTestingTypeValue().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        if (getTestScriptName() != null) {
            _hashCode += getTestScriptName().hashCode();
        }
        _hashCode += getFolderId();
        if (getFolderPath() != null) {
            _hashCode += getFolderPath().hashCode();
        }
        if (getTestCaseUdfs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestCaseUdfs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestCaseUdfs(), i);
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
        new org.apache.axis.description.TypeDesc(TestCaseEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestCaseEntity"));
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
        elemField.setFieldName("preCondition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "preCondition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postCondition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "postCondition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "designer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designerValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "designerValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priorityValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "priorityValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executionTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "executionTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "typeValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "modifiedReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedReasonValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "modifiedReasonValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testingType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testingType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testingTypeValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testingTypeValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testScriptName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testScriptName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("testCaseUdfs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testCaseUdfs"));
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
