/**
 * ContourEntry.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class ContourEntry  implements java.io.Serializable {
    private java.lang.String entryDate;

    private java.lang.Double entryHours;

    public ContourEntry() {
    }

    public ContourEntry(
           java.lang.String entryDate,
           java.lang.Double entryHours) {
           this.entryDate = entryDate;
           this.entryHours = entryHours;
    }


    /**
     * Gets the entryDate value for this ContourEntry.
     * 
     * @return entryDate
     */
    public java.lang.String getEntryDate() {
        return entryDate;
    }


    /**
     * Sets the entryDate value for this ContourEntry.
     * 
     * @param entryDate
     */
    public void setEntryDate(java.lang.String entryDate) {
        this.entryDate = entryDate;
    }


    /**
     * Gets the entryHours value for this ContourEntry.
     * 
     * @return entryHours
     */
    public java.lang.Double getEntryHours() {
        return entryHours;
    }


    /**
     * Sets the entryHours value for this ContourEntry.
     * 
     * @param entryHours
     */
    public void setEntryHours(java.lang.Double entryHours) {
        this.entryHours = entryHours;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContourEntry)) return false;
        ContourEntry other = (ContourEntry) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entryDate==null && other.getEntryDate()==null) || 
             (this.entryDate!=null &&
              this.entryDate.equals(other.getEntryDate()))) &&
            ((this.entryHours==null && other.getEntryHours()==null) || 
             (this.entryHours!=null &&
              this.entryHours.equals(other.getEntryHours())));
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
        if (getEntryDate() != null) {
            _hashCode += getEntryDate().hashCode();
        }
        if (getEntryHours() != null) {
            _hashCode += getEntryHours().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContourEntry.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ContourEntry"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
