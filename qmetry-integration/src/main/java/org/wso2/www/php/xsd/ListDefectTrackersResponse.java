/**
 * ListDefectTrackersResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListDefectTrackersResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.DefectTracker[] defectTracker;

    public ListDefectTrackersResponse() {
    }

    public ListDefectTrackersResponse(
           org.wso2.www.php.xsd.DefectTracker[] defectTracker) {
           this.defectTracker = defectTracker;
    }


    /**
     * Gets the defectTracker value for this ListDefectTrackersResponse.
     * 
     * @return defectTracker
     */
    public org.wso2.www.php.xsd.DefectTracker[] getDefectTracker() {
        return defectTracker;
    }


    /**
     * Sets the defectTracker value for this ListDefectTrackersResponse.
     * 
     * @param defectTracker
     */
    public void setDefectTracker(org.wso2.www.php.xsd.DefectTracker[] defectTracker) {
        this.defectTracker = defectTracker;
    }

    public org.wso2.www.php.xsd.DefectTracker getDefectTracker(int i) {
        return this.defectTracker[i];
    }

    public void setDefectTracker(int i, org.wso2.www.php.xsd.DefectTracker _value) {
        this.defectTracker[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListDefectTrackersResponse)) return false;
        ListDefectTrackersResponse other = (ListDefectTrackersResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.defectTracker==null && other.getDefectTracker()==null) || 
             (this.defectTracker!=null &&
              java.util.Arrays.equals(this.defectTracker, other.getDefectTracker())));
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
        if (getDefectTracker() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDefectTracker());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDefectTracker(), i);
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
        new org.apache.axis.description.TypeDesc(ListDefectTrackersResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listDefectTrackersResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defectTracker");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "defectTracker"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "DefectTracker"));
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
