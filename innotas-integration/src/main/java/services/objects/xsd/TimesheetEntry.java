/**
 * TimesheetEntry.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class TimesheetEntry  implements java.io.Serializable {
    private java.lang.Double billableRate;

    private java.lang.Long companyId;

    private java.lang.String entryDate;

    private java.lang.Double entryHours;

    private java.lang.Long entryId;

    private java.lang.Long entryTypeId;

    private java.lang.Double internalRate;

    private java.lang.Boolean isBillable;

    private java.lang.Boolean isProductive;

    private java.lang.Long level1Id;

    private java.lang.Long level2Id;

    private java.lang.Long level3Id;

    private java.lang.Long locationId;

    private java.lang.String notes;

    private java.lang.String state;

    private java.lang.Long timesheetId;

    public TimesheetEntry() {
    }

    public TimesheetEntry(
           java.lang.Double billableRate,
           java.lang.Long companyId,
           java.lang.String entryDate,
           java.lang.Double entryHours,
           java.lang.Long entryId,
           java.lang.Long entryTypeId,
           java.lang.Double internalRate,
           java.lang.Boolean isBillable,
           java.lang.Boolean isProductive,
           java.lang.Long level1Id,
           java.lang.Long level2Id,
           java.lang.Long level3Id,
           java.lang.Long locationId,
           java.lang.String notes,
           java.lang.String state,
           java.lang.Long timesheetId) {
           this.billableRate = billableRate;
           this.companyId = companyId;
           this.entryDate = entryDate;
           this.entryHours = entryHours;
           this.entryId = entryId;
           this.entryTypeId = entryTypeId;
           this.internalRate = internalRate;
           this.isBillable = isBillable;
           this.isProductive = isProductive;
           this.level1Id = level1Id;
           this.level2Id = level2Id;
           this.level3Id = level3Id;
           this.locationId = locationId;
           this.notes = notes;
           this.state = state;
           this.timesheetId = timesheetId;
    }


    /**
     * Gets the billableRate value for this TimesheetEntry.
     * 
     * @return billableRate
     */
    public java.lang.Double getBillableRate() {
        return billableRate;
    }


    /**
     * Sets the billableRate value for this TimesheetEntry.
     * 
     * @param billableRate
     */
    public void setBillableRate(java.lang.Double billableRate) {
        this.billableRate = billableRate;
    }


    /**
     * Gets the companyId value for this TimesheetEntry.
     * 
     * @return companyId
     */
    public java.lang.Long getCompanyId() {
        return companyId;
    }


    /**
     * Sets the companyId value for this TimesheetEntry.
     * 
     * @param companyId
     */
    public void setCompanyId(java.lang.Long companyId) {
        this.companyId = companyId;
    }


    /**
     * Gets the entryDate value for this TimesheetEntry.
     * 
     * @return entryDate
     */
    public java.lang.String getEntryDate() {
        return entryDate;
    }


    /**
     * Sets the entryDate value for this TimesheetEntry.
     * 
     * @param entryDate
     */
    public void setEntryDate(java.lang.String entryDate) {
        this.entryDate = entryDate;
    }


    /**
     * Gets the entryHours value for this TimesheetEntry.
     * 
     * @return entryHours
     */
    public java.lang.Double getEntryHours() {
        return entryHours;
    }


    /**
     * Sets the entryHours value for this TimesheetEntry.
     * 
     * @param entryHours
     */
    public void setEntryHours(java.lang.Double entryHours) {
        this.entryHours = entryHours;
    }


    /**
     * Gets the entryId value for this TimesheetEntry.
     * 
     * @return entryId
     */
    public java.lang.Long getEntryId() {
        return entryId;
    }


    /**
     * Sets the entryId value for this TimesheetEntry.
     * 
     * @param entryId
     */
    public void setEntryId(java.lang.Long entryId) {
        this.entryId = entryId;
    }


    /**
     * Gets the entryTypeId value for this TimesheetEntry.
     * 
     * @return entryTypeId
     */
    public java.lang.Long getEntryTypeId() {
        return entryTypeId;
    }


    /**
     * Sets the entryTypeId value for this TimesheetEntry.
     * 
     * @param entryTypeId
     */
    public void setEntryTypeId(java.lang.Long entryTypeId) {
        this.entryTypeId = entryTypeId;
    }


    /**
     * Gets the internalRate value for this TimesheetEntry.
     * 
     * @return internalRate
     */
    public java.lang.Double getInternalRate() {
        return internalRate;
    }


    /**
     * Sets the internalRate value for this TimesheetEntry.
     * 
     * @param internalRate
     */
    public void setInternalRate(java.lang.Double internalRate) {
        this.internalRate = internalRate;
    }


    /**
     * Gets the isBillable value for this TimesheetEntry.
     * 
     * @return isBillable
     */
    public java.lang.Boolean getIsBillable() {
        return isBillable;
    }


    /**
     * Sets the isBillable value for this TimesheetEntry.
     * 
     * @param isBillable
     */
    public void setIsBillable(java.lang.Boolean isBillable) {
        this.isBillable = isBillable;
    }


    /**
     * Gets the isProductive value for this TimesheetEntry.
     * 
     * @return isProductive
     */
    public java.lang.Boolean getIsProductive() {
        return isProductive;
    }


    /**
     * Sets the isProductive value for this TimesheetEntry.
     * 
     * @param isProductive
     */
    public void setIsProductive(java.lang.Boolean isProductive) {
        this.isProductive = isProductive;
    }


    /**
     * Gets the level1Id value for this TimesheetEntry.
     * 
     * @return level1Id
     */
    public java.lang.Long getLevel1Id() {
        return level1Id;
    }


    /**
     * Sets the level1Id value for this TimesheetEntry.
     * 
     * @param level1Id
     */
    public void setLevel1Id(java.lang.Long level1Id) {
        this.level1Id = level1Id;
    }


    /**
     * Gets the level2Id value for this TimesheetEntry.
     * 
     * @return level2Id
     */
    public java.lang.Long getLevel2Id() {
        return level2Id;
    }


    /**
     * Sets the level2Id value for this TimesheetEntry.
     * 
     * @param level2Id
     */
    public void setLevel2Id(java.lang.Long level2Id) {
        this.level2Id = level2Id;
    }


    /**
     * Gets the level3Id value for this TimesheetEntry.
     * 
     * @return level3Id
     */
    public java.lang.Long getLevel3Id() {
        return level3Id;
    }


    /**
     * Sets the level3Id value for this TimesheetEntry.
     * 
     * @param level3Id
     */
    public void setLevel3Id(java.lang.Long level3Id) {
        this.level3Id = level3Id;
    }


    /**
     * Gets the locationId value for this TimesheetEntry.
     * 
     * @return locationId
     */
    public java.lang.Long getLocationId() {
        return locationId;
    }


    /**
     * Sets the locationId value for this TimesheetEntry.
     * 
     * @param locationId
     */
    public void setLocationId(java.lang.Long locationId) {
        this.locationId = locationId;
    }


    /**
     * Gets the notes value for this TimesheetEntry.
     * 
     * @return notes
     */
    public java.lang.String getNotes() {
        return notes;
    }


    /**
     * Sets the notes value for this TimesheetEntry.
     * 
     * @param notes
     */
    public void setNotes(java.lang.String notes) {
        this.notes = notes;
    }


    /**
     * Gets the state value for this TimesheetEntry.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this TimesheetEntry.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the timesheetId value for this TimesheetEntry.
     * 
     * @return timesheetId
     */
    public java.lang.Long getTimesheetId() {
        return timesheetId;
    }


    /**
     * Sets the timesheetId value for this TimesheetEntry.
     * 
     * @param timesheetId
     */
    public void setTimesheetId(java.lang.Long timesheetId) {
        this.timesheetId = timesheetId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TimesheetEntry)) return false;
        TimesheetEntry other = (TimesheetEntry) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.billableRate==null && other.getBillableRate()==null) || 
             (this.billableRate!=null &&
              this.billableRate.equals(other.getBillableRate()))) &&
            ((this.companyId==null && other.getCompanyId()==null) || 
             (this.companyId!=null &&
              this.companyId.equals(other.getCompanyId()))) &&
            ((this.entryDate==null && other.getEntryDate()==null) || 
             (this.entryDate!=null &&
              this.entryDate.equals(other.getEntryDate()))) &&
            ((this.entryHours==null && other.getEntryHours()==null) || 
             (this.entryHours!=null &&
              this.entryHours.equals(other.getEntryHours()))) &&
            ((this.entryId==null && other.getEntryId()==null) || 
             (this.entryId!=null &&
              this.entryId.equals(other.getEntryId()))) &&
            ((this.entryTypeId==null && other.getEntryTypeId()==null) || 
             (this.entryTypeId!=null &&
              this.entryTypeId.equals(other.getEntryTypeId()))) &&
            ((this.internalRate==null && other.getInternalRate()==null) || 
             (this.internalRate!=null &&
              this.internalRate.equals(other.getInternalRate()))) &&
            ((this.isBillable==null && other.getIsBillable()==null) || 
             (this.isBillable!=null &&
              this.isBillable.equals(other.getIsBillable()))) &&
            ((this.isProductive==null && other.getIsProductive()==null) || 
             (this.isProductive!=null &&
              this.isProductive.equals(other.getIsProductive()))) &&
            ((this.level1Id==null && other.getLevel1Id()==null) || 
             (this.level1Id!=null &&
              this.level1Id.equals(other.getLevel1Id()))) &&
            ((this.level2Id==null && other.getLevel2Id()==null) || 
             (this.level2Id!=null &&
              this.level2Id.equals(other.getLevel2Id()))) &&
            ((this.level3Id==null && other.getLevel3Id()==null) || 
             (this.level3Id!=null &&
              this.level3Id.equals(other.getLevel3Id()))) &&
            ((this.locationId==null && other.getLocationId()==null) || 
             (this.locationId!=null &&
              this.locationId.equals(other.getLocationId()))) &&
            ((this.notes==null && other.getNotes()==null) || 
             (this.notes!=null &&
              this.notes.equals(other.getNotes()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.timesheetId==null && other.getTimesheetId()==null) || 
             (this.timesheetId!=null &&
              this.timesheetId.equals(other.getTimesheetId())));
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
        if (getBillableRate() != null) {
            _hashCode += getBillableRate().hashCode();
        }
        if (getCompanyId() != null) {
            _hashCode += getCompanyId().hashCode();
        }
        if (getEntryDate() != null) {
            _hashCode += getEntryDate().hashCode();
        }
        if (getEntryHours() != null) {
            _hashCode += getEntryHours().hashCode();
        }
        if (getEntryId() != null) {
            _hashCode += getEntryId().hashCode();
        }
        if (getEntryTypeId() != null) {
            _hashCode += getEntryTypeId().hashCode();
        }
        if (getInternalRate() != null) {
            _hashCode += getInternalRate().hashCode();
        }
        if (getIsBillable() != null) {
            _hashCode += getIsBillable().hashCode();
        }
        if (getIsProductive() != null) {
            _hashCode += getIsProductive().hashCode();
        }
        if (getLevel1Id() != null) {
            _hashCode += getLevel1Id().hashCode();
        }
        if (getLevel2Id() != null) {
            _hashCode += getLevel2Id().hashCode();
        }
        if (getLevel3Id() != null) {
            _hashCode += getLevel3Id().hashCode();
        }
        if (getLocationId() != null) {
            _hashCode += getLocationId().hashCode();
        }
        if (getNotes() != null) {
            _hashCode += getNotes().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getTimesheetId() != null) {
            _hashCode += getTimesheetId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TimesheetEntry.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "TimesheetEntry"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billableRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "billableRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "companyId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entryDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "entryDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entryHours");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "entryHours"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entryId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "entryId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entryTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "entryTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internalRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "internalRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isBillable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "isBillable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isProductive");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "isProductive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level1Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "level1Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level2Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "level2Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level3Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "level3Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "locationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "notes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timesheetId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "timesheetId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
