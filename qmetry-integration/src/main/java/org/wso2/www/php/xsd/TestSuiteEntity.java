/**
 * TestSuiteEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class TestSuiteEntity  implements java.io.Serializable {
    private int id;

    private java.lang.String name;

    private java.lang.String objective;

    private java.lang.String status;

    private java.lang.String assignedTo;

    private int folderId;

    private java.lang.String folderPath;

    private org.wso2.www.php.xsd.UserDefinedFieldArray[] testSuiteUdfs;

    public TestSuiteEntity() {
    }

    public TestSuiteEntity(
           int id,
           java.lang.String name,
           java.lang.String objective,
           java.lang.String status,
           java.lang.String assignedTo,
           int folderId,
           java.lang.String folderPath,
           org.wso2.www.php.xsd.UserDefinedFieldArray[] testSuiteUdfs) {
           this.id = id;
           this.name = name;
           this.objective = objective;
           this.status = status;
           this.assignedTo = assignedTo;
           this.folderId = folderId;
           this.folderPath = folderPath;
           this.testSuiteUdfs = testSuiteUdfs;
    }


    /**
     * Gets the id value for this TestSuiteEntity.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this TestSuiteEntity.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the name value for this TestSuiteEntity.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this TestSuiteEntity.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the objective value for this TestSuiteEntity.
     * 
     * @return objective
     */
    public java.lang.String getObjective() {
        return objective;
    }


    /**
     * Sets the objective value for this TestSuiteEntity.
     * 
     * @param objective
     */
    public void setObjective(java.lang.String objective) {
        this.objective = objective;
    }


    /**
     * Gets the status value for this TestSuiteEntity.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this TestSuiteEntity.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the assignedTo value for this TestSuiteEntity.
     * 
     * @return assignedTo
     */
    public java.lang.String getAssignedTo() {
        return assignedTo;
    }


    /**
     * Sets the assignedTo value for this TestSuiteEntity.
     * 
     * @param assignedTo
     */
    public void setAssignedTo(java.lang.String assignedTo) {
        this.assignedTo = assignedTo;
    }


    /**
     * Gets the folderId value for this TestSuiteEntity.
     * 
     * @return folderId
     */
    public int getFolderId() {
        return folderId;
    }


    /**
     * Sets the folderId value for this TestSuiteEntity.
     * 
     * @param folderId
     */
    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }


    /**
     * Gets the folderPath value for this TestSuiteEntity.
     * 
     * @return folderPath
     */
    public java.lang.String getFolderPath() {
        return folderPath;
    }


    /**
     * Sets the folderPath value for this TestSuiteEntity.
     * 
     * @param folderPath
     */
    public void setFolderPath(java.lang.String folderPath) {
        this.folderPath = folderPath;
    }


    /**
     * Gets the testSuiteUdfs value for this TestSuiteEntity.
     * 
     * @return testSuiteUdfs
     */
    public org.wso2.www.php.xsd.UserDefinedFieldArray[] getTestSuiteUdfs() {
        return testSuiteUdfs;
    }


    /**
     * Sets the testSuiteUdfs value for this TestSuiteEntity.
     * 
     * @param testSuiteUdfs
     */
    public void setTestSuiteUdfs(org.wso2.www.php.xsd.UserDefinedFieldArray[] testSuiteUdfs) {
        this.testSuiteUdfs = testSuiteUdfs;
    }

    public org.wso2.www.php.xsd.UserDefinedFieldArray getTestSuiteUdfs(int i) {
        return this.testSuiteUdfs[i];
    }

    public void setTestSuiteUdfs(int i, org.wso2.www.php.xsd.UserDefinedFieldArray _value) {
        this.testSuiteUdfs[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestSuiteEntity)) return false;
        TestSuiteEntity other = (TestSuiteEntity) obj;
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
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.assignedTo==null && other.getAssignedTo()==null) || 
             (this.assignedTo!=null &&
              this.assignedTo.equals(other.getAssignedTo()))) &&
            this.folderId == other.getFolderId() &&
            ((this.folderPath==null && other.getFolderPath()==null) || 
             (this.folderPath!=null &&
              this.folderPath.equals(other.getFolderPath()))) &&
            ((this.testSuiteUdfs==null && other.getTestSuiteUdfs()==null) || 
             (this.testSuiteUdfs!=null &&
              java.util.Arrays.equals(this.testSuiteUdfs, other.getTestSuiteUdfs())));
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
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getAssignedTo() != null) {
            _hashCode += getAssignedTo().hashCode();
        }
        _hashCode += getFolderId();
        if (getFolderPath() != null) {
            _hashCode += getFolderPath().hashCode();
        }
        if (getTestSuiteUdfs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestSuiteUdfs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestSuiteUdfs(), i);
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
        new org.apache.axis.description.TypeDesc(TestSuiteEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "TestSuiteEntity"));
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
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "status"));
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
        elemField.setFieldName("testSuiteUdfs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "testSuiteUdfs"));
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
