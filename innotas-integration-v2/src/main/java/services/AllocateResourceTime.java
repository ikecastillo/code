/**
 * AllocateResourceTime.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class AllocateResourceTime  implements java.io.Serializable {
    private java.lang.String sessionId;

    private long[] taskIds;

    private long[] resourceIds;

    private long[] projectRoleIds;

    private java.lang.Float estimateHours;

    private java.lang.Float scheduleHours;

    private java.lang.Float percentOfTime;

    private java.lang.Float hoursToComplete;

    public AllocateResourceTime() {
    }

    public AllocateResourceTime(
           java.lang.String sessionId,
           long[] taskIds,
           long[] resourceIds,
           long[] projectRoleIds,
           java.lang.Float estimateHours,
           java.lang.Float scheduleHours,
           java.lang.Float percentOfTime,
           java.lang.Float hoursToComplete) {
           this.sessionId = sessionId;
           this.taskIds = taskIds;
           this.resourceIds = resourceIds;
           this.projectRoleIds = projectRoleIds;
           this.estimateHours = estimateHours;
           this.scheduleHours = scheduleHours;
           this.percentOfTime = percentOfTime;
           this.hoursToComplete = hoursToComplete;
    }


    /**
     * Gets the sessionId value for this AllocateResourceTime.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this AllocateResourceTime.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the taskIds value for this AllocateResourceTime.
     * 
     * @return taskIds
     */
    public long[] getTaskIds() {
        return taskIds;
    }


    /**
     * Sets the taskIds value for this AllocateResourceTime.
     * 
     * @param taskIds
     */
    public void setTaskIds(long[] taskIds) {
        this.taskIds = taskIds;
    }

    public long getTaskIds(int i) {
        return this.taskIds[i];
    }

    public void setTaskIds(int i, long _value) {
        this.taskIds[i] = _value;
    }


    /**
     * Gets the resourceIds value for this AllocateResourceTime.
     * 
     * @return resourceIds
     */
    public long[] getResourceIds() {
        return resourceIds;
    }


    /**
     * Sets the resourceIds value for this AllocateResourceTime.
     * 
     * @param resourceIds
     */
    public void setResourceIds(long[] resourceIds) {
        this.resourceIds = resourceIds;
    }

    public long getResourceIds(int i) {
        return this.resourceIds[i];
    }

    public void setResourceIds(int i, long _value) {
        this.resourceIds[i] = _value;
    }


    /**
     * Gets the projectRoleIds value for this AllocateResourceTime.
     * 
     * @return projectRoleIds
     */
    public long[] getProjectRoleIds() {
        return projectRoleIds;
    }


    /**
     * Sets the projectRoleIds value for this AllocateResourceTime.
     * 
     * @param projectRoleIds
     */
    public void setProjectRoleIds(long[] projectRoleIds) {
        this.projectRoleIds = projectRoleIds;
    }

    public long getProjectRoleIds(int i) {
        return this.projectRoleIds[i];
    }

    public void setProjectRoleIds(int i, long _value) {
        this.projectRoleIds[i] = _value;
    }


    /**
     * Gets the estimateHours value for this AllocateResourceTime.
     * 
     * @return estimateHours
     */
    public java.lang.Float getEstimateHours() {
        return estimateHours;
    }


    /**
     * Sets the estimateHours value for this AllocateResourceTime.
     * 
     * @param estimateHours
     */
    public void setEstimateHours(java.lang.Float estimateHours) {
        this.estimateHours = estimateHours;
    }


    /**
     * Gets the scheduleHours value for this AllocateResourceTime.
     * 
     * @return scheduleHours
     */
    public java.lang.Float getScheduleHours() {
        return scheduleHours;
    }


    /**
     * Sets the scheduleHours value for this AllocateResourceTime.
     * 
     * @param scheduleHours
     */
    public void setScheduleHours(java.lang.Float scheduleHours) {
        this.scheduleHours = scheduleHours;
    }


    /**
     * Gets the percentOfTime value for this AllocateResourceTime.
     * 
     * @return percentOfTime
     */
    public java.lang.Float getPercentOfTime() {
        return percentOfTime;
    }


    /**
     * Sets the percentOfTime value for this AllocateResourceTime.
     * 
     * @param percentOfTime
     */
    public void setPercentOfTime(java.lang.Float percentOfTime) {
        this.percentOfTime = percentOfTime;
    }


    /**
     * Gets the hoursToComplete value for this AllocateResourceTime.
     * 
     * @return hoursToComplete
     */
    public java.lang.Float getHoursToComplete() {
        return hoursToComplete;
    }


    /**
     * Sets the hoursToComplete value for this AllocateResourceTime.
     * 
     * @param hoursToComplete
     */
    public void setHoursToComplete(java.lang.Float hoursToComplete) {
        this.hoursToComplete = hoursToComplete;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AllocateResourceTime)) return false;
        AllocateResourceTime other = (AllocateResourceTime) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sessionId==null && other.getSessionId()==null) || 
             (this.sessionId!=null &&
              this.sessionId.equals(other.getSessionId()))) &&
            ((this.taskIds==null && other.getTaskIds()==null) || 
             (this.taskIds!=null &&
              java.util.Arrays.equals(this.taskIds, other.getTaskIds()))) &&
            ((this.resourceIds==null && other.getResourceIds()==null) || 
             (this.resourceIds!=null &&
              java.util.Arrays.equals(this.resourceIds, other.getResourceIds()))) &&
            ((this.projectRoleIds==null && other.getProjectRoleIds()==null) || 
             (this.projectRoleIds!=null &&
              java.util.Arrays.equals(this.projectRoleIds, other.getProjectRoleIds()))) &&
            ((this.estimateHours==null && other.getEstimateHours()==null) || 
             (this.estimateHours!=null &&
              this.estimateHours.equals(other.getEstimateHours()))) &&
            ((this.scheduleHours==null && other.getScheduleHours()==null) || 
             (this.scheduleHours!=null &&
              this.scheduleHours.equals(other.getScheduleHours()))) &&
            ((this.percentOfTime==null && other.getPercentOfTime()==null) || 
             (this.percentOfTime!=null &&
              this.percentOfTime.equals(other.getPercentOfTime()))) &&
            ((this.hoursToComplete==null && other.getHoursToComplete()==null) || 
             (this.hoursToComplete!=null &&
              this.hoursToComplete.equals(other.getHoursToComplete())));
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
        if (getSessionId() != null) {
            _hashCode += getSessionId().hashCode();
        }
        if (getTaskIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTaskIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTaskIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResourceIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResourceIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResourceIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProjectRoleIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProjectRoleIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProjectRoleIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEstimateHours() != null) {
            _hashCode += getEstimateHours().hashCode();
        }
        if (getScheduleHours() != null) {
            _hashCode += getScheduleHours().hashCode();
        }
        if (getPercentOfTime() != null) {
            _hashCode += getPercentOfTime().hashCode();
        }
        if (getHoursToComplete() != null) {
            _hashCode += getHoursToComplete().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AllocateResourceTime.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">allocateResourceTime"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taskIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "taskIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resourceIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "resourceIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectRoleIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "projectRoleIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estimateHours");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "estimateHours"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduleHours");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "scheduleHours"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentOfTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "percentOfTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hoursToComplete");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "hoursToComplete"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
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
