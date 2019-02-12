/**
 * Timesheet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class Timesheet  implements java.io.Serializable {
    private java.lang.String endDate;

    private services.objects.xsd.TimesheetEntry[] entriesAccount;

    private services.objects.xsd.TimesheetEntry[] entriesOther;

    private services.objects.xsd.TimesheetEntry[] entriesPortfolio;

    private services.objects.xsd.TimesheetEntry[] entriesProject;

    private java.lang.Long satisfactionId;

    private java.lang.String startDate;

    private java.lang.String summaryNotes;

    private java.lang.Long timesheetId;

    private java.lang.Long timesheetPeriodId;

    private java.lang.Long userId;

    public Timesheet() {
    }

    public Timesheet(
           java.lang.String endDate,
           services.objects.xsd.TimesheetEntry[] entriesAccount,
           services.objects.xsd.TimesheetEntry[] entriesOther,
           services.objects.xsd.TimesheetEntry[] entriesPortfolio,
           services.objects.xsd.TimesheetEntry[] entriesProject,
           java.lang.Long satisfactionId,
           java.lang.String startDate,
           java.lang.String summaryNotes,
           java.lang.Long timesheetId,
           java.lang.Long timesheetPeriodId,
           java.lang.Long userId) {
           this.endDate = endDate;
           this.entriesAccount = entriesAccount;
           this.entriesOther = entriesOther;
           this.entriesPortfolio = entriesPortfolio;
           this.entriesProject = entriesProject;
           this.satisfactionId = satisfactionId;
           this.startDate = startDate;
           this.summaryNotes = summaryNotes;
           this.timesheetId = timesheetId;
           this.timesheetPeriodId = timesheetPeriodId;
           this.userId = userId;
    }


    /**
     * Gets the endDate value for this Timesheet.
     * 
     * @return endDate
     */
    public java.lang.String getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this Timesheet.
     * 
     * @param endDate
     */
    public void setEndDate(java.lang.String endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the entriesAccount value for this Timesheet.
     * 
     * @return entriesAccount
     */
    public services.objects.xsd.TimesheetEntry[] getEntriesAccount() {
        return entriesAccount;
    }


    /**
     * Sets the entriesAccount value for this Timesheet.
     * 
     * @param entriesAccount
     */
    public void setEntriesAccount(services.objects.xsd.TimesheetEntry[] entriesAccount) {
        this.entriesAccount = entriesAccount;
    }

    public services.objects.xsd.TimesheetEntry getEntriesAccount(int i) {
        return this.entriesAccount[i];
    }

    public void setEntriesAccount(int i, services.objects.xsd.TimesheetEntry _value) {
        this.entriesAccount[i] = _value;
    }


    /**
     * Gets the entriesOther value for this Timesheet.
     * 
     * @return entriesOther
     */
    public services.objects.xsd.TimesheetEntry[] getEntriesOther() {
        return entriesOther;
    }


    /**
     * Sets the entriesOther value for this Timesheet.
     * 
     * @param entriesOther
     */
    public void setEntriesOther(services.objects.xsd.TimesheetEntry[] entriesOther) {
        this.entriesOther = entriesOther;
    }

    public services.objects.xsd.TimesheetEntry getEntriesOther(int i) {
        return this.entriesOther[i];
    }

    public void setEntriesOther(int i, services.objects.xsd.TimesheetEntry _value) {
        this.entriesOther[i] = _value;
    }


    /**
     * Gets the entriesPortfolio value for this Timesheet.
     * 
     * @return entriesPortfolio
     */
    public services.objects.xsd.TimesheetEntry[] getEntriesPortfolio() {
        return entriesPortfolio;
    }


    /**
     * Sets the entriesPortfolio value for this Timesheet.
     * 
     * @param entriesPortfolio
     */
    public void setEntriesPortfolio(services.objects.xsd.TimesheetEntry[] entriesPortfolio) {
        this.entriesPortfolio = entriesPortfolio;
    }

    public services.objects.xsd.TimesheetEntry getEntriesPortfolio(int i) {
        return this.entriesPortfolio[i];
    }

    public void setEntriesPortfolio(int i, services.objects.xsd.TimesheetEntry _value) {
        this.entriesPortfolio[i] = _value;
    }


    /**
     * Gets the entriesProject value for this Timesheet.
     * 
     * @return entriesProject
     */
    public services.objects.xsd.TimesheetEntry[] getEntriesProject() {
        return entriesProject;
    }


    /**
     * Sets the entriesProject value for this Timesheet.
     * 
     * @param entriesProject
     */
    public void setEntriesProject(services.objects.xsd.TimesheetEntry[] entriesProject) {
        this.entriesProject = entriesProject;
    }

    public services.objects.xsd.TimesheetEntry getEntriesProject(int i) {
        return this.entriesProject[i];
    }

    public void setEntriesProject(int i, services.objects.xsd.TimesheetEntry _value) {
        this.entriesProject[i] = _value;
    }


    /**
     * Gets the satisfactionId value for this Timesheet.
     * 
     * @return satisfactionId
     */
    public java.lang.Long getSatisfactionId() {
        return satisfactionId;
    }


    /**
     * Sets the satisfactionId value for this Timesheet.
     * 
     * @param satisfactionId
     */
    public void setSatisfactionId(java.lang.Long satisfactionId) {
        this.satisfactionId = satisfactionId;
    }


    /**
     * Gets the startDate value for this Timesheet.
     * 
     * @return startDate
     */
    public java.lang.String getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this Timesheet.
     * 
     * @param startDate
     */
    public void setStartDate(java.lang.String startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the summaryNotes value for this Timesheet.
     * 
     * @return summaryNotes
     */
    public java.lang.String getSummaryNotes() {
        return summaryNotes;
    }


    /**
     * Sets the summaryNotes value for this Timesheet.
     * 
     * @param summaryNotes
     */
    public void setSummaryNotes(java.lang.String summaryNotes) {
        this.summaryNotes = summaryNotes;
    }


    /**
     * Gets the timesheetId value for this Timesheet.
     * 
     * @return timesheetId
     */
    public java.lang.Long getTimesheetId() {
        return timesheetId;
    }


    /**
     * Sets the timesheetId value for this Timesheet.
     * 
     * @param timesheetId
     */
    public void setTimesheetId(java.lang.Long timesheetId) {
        this.timesheetId = timesheetId;
    }


    /**
     * Gets the timesheetPeriodId value for this Timesheet.
     * 
     * @return timesheetPeriodId
     */
    public java.lang.Long getTimesheetPeriodId() {
        return timesheetPeriodId;
    }


    /**
     * Sets the timesheetPeriodId value for this Timesheet.
     * 
     * @param timesheetPeriodId
     */
    public void setTimesheetPeriodId(java.lang.Long timesheetPeriodId) {
        this.timesheetPeriodId = timesheetPeriodId;
    }


    /**
     * Gets the userId value for this Timesheet.
     * 
     * @return userId
     */
    public java.lang.Long getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this Timesheet.
     * 
     * @param userId
     */
    public void setUserId(java.lang.Long userId) {
        this.userId = userId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Timesheet)) return false;
        Timesheet other = (Timesheet) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.entriesAccount==null && other.getEntriesAccount()==null) || 
             (this.entriesAccount!=null &&
              java.util.Arrays.equals(this.entriesAccount, other.getEntriesAccount()))) &&
            ((this.entriesOther==null && other.getEntriesOther()==null) || 
             (this.entriesOther!=null &&
              java.util.Arrays.equals(this.entriesOther, other.getEntriesOther()))) &&
            ((this.entriesPortfolio==null && other.getEntriesPortfolio()==null) || 
             (this.entriesPortfolio!=null &&
              java.util.Arrays.equals(this.entriesPortfolio, other.getEntriesPortfolio()))) &&
            ((this.entriesProject==null && other.getEntriesProject()==null) || 
             (this.entriesProject!=null &&
              java.util.Arrays.equals(this.entriesProject, other.getEntriesProject()))) &&
            ((this.satisfactionId==null && other.getSatisfactionId()==null) || 
             (this.satisfactionId!=null &&
              this.satisfactionId.equals(other.getSatisfactionId()))) &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.summaryNotes==null && other.getSummaryNotes()==null) || 
             (this.summaryNotes!=null &&
              this.summaryNotes.equals(other.getSummaryNotes()))) &&
            ((this.timesheetId==null && other.getTimesheetId()==null) || 
             (this.timesheetId!=null &&
              this.timesheetId.equals(other.getTimesheetId()))) &&
            ((this.timesheetPeriodId==null && other.getTimesheetPeriodId()==null) || 
             (this.timesheetPeriodId!=null &&
              this.timesheetPeriodId.equals(other.getTimesheetPeriodId()))) &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId())));
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
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getEntriesAccount() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEntriesAccount());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEntriesAccount(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEntriesOther() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEntriesOther());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEntriesOther(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEntriesPortfolio() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEntriesPortfolio());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEntriesPortfolio(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEntriesProject() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEntriesProject());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEntriesProject(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSatisfactionId() != null) {
            _hashCode += getSatisfactionId().hashCode();
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getSummaryNotes() != null) {
            _hashCode += getSummaryNotes().hashCode();
        }
        if (getTimesheetId() != null) {
            _hashCode += getTimesheetId().hashCode();
        }
        if (getTimesheetPeriodId() != null) {
            _hashCode += getTimesheetPeriodId().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Timesheet.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "Timesheet"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entriesAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "entriesAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "TimesheetEntry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entriesOther");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "entriesOther"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "TimesheetEntry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entriesPortfolio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "entriesPortfolio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "TimesheetEntry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entriesProject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "entriesProject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "TimesheetEntry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("satisfactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "satisfactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summaryNotes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "summaryNotes"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timesheetPeriodId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "timesheetPeriodId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "userId"));
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
