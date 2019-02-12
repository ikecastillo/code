/**
 * ListRequirementsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListRequirementsResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.RequirementEntity[] requirements;

    public ListRequirementsResponse() {
    }

    public ListRequirementsResponse(
           org.wso2.www.php.xsd.RequirementEntity[] requirements) {
           this.requirements = requirements;
    }


    /**
     * Gets the requirements value for this ListRequirementsResponse.
     * 
     * @return requirements
     */
    public org.wso2.www.php.xsd.RequirementEntity[] getRequirements() {
        return requirements;
    }


    /**
     * Sets the requirements value for this ListRequirementsResponse.
     * 
     * @param requirements
     */
    public void setRequirements(org.wso2.www.php.xsd.RequirementEntity[] requirements) {
        this.requirements = requirements;
    }

    public org.wso2.www.php.xsd.RequirementEntity getRequirements(int i) {
        return this.requirements[i];
    }

    public void setRequirements(int i, org.wso2.www.php.xsd.RequirementEntity _value) {
        this.requirements[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListRequirementsResponse)) return false;
        ListRequirementsResponse other = (ListRequirementsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requirements==null && other.getRequirements()==null) || 
             (this.requirements!=null &&
              java.util.Arrays.equals(this.requirements, other.getRequirements())));
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
        if (getRequirements() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRequirements());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRequirements(), i);
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
        new org.apache.axis.description.TypeDesc(ListRequirementsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listRequirementsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requirements");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "requirements"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "RequirementEntity"));
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
