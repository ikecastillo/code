/**
 * ListFoldersResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php.xsd;

public class ListFoldersResponse  implements java.io.Serializable {
    private org.wso2.www.php.xsd.FolderDetails[] folderDetails;

    public ListFoldersResponse() {
    }

    public ListFoldersResponse(
           org.wso2.www.php.xsd.FolderDetails[] folderDetails) {
           this.folderDetails = folderDetails;
    }


    /**
     * Gets the folderDetails value for this ListFoldersResponse.
     * 
     * @return folderDetails
     */
    public org.wso2.www.php.xsd.FolderDetails[] getFolderDetails() {
        return folderDetails;
    }


    /**
     * Sets the folderDetails value for this ListFoldersResponse.
     * 
     * @param folderDetails
     */
    public void setFolderDetails(org.wso2.www.php.xsd.FolderDetails[] folderDetails) {
        this.folderDetails = folderDetails;
    }

    public org.wso2.www.php.xsd.FolderDetails getFolderDetails(int i) {
        return this.folderDetails[i];
    }

    public void setFolderDetails(int i, org.wso2.www.php.xsd.FolderDetails _value) {
        this.folderDetails[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListFoldersResponse)) return false;
        ListFoldersResponse other = (ListFoldersResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.folderDetails==null && other.getFolderDetails()==null) || 
             (this.folderDetails!=null &&
              java.util.Arrays.equals(this.folderDetails, other.getFolderDetails())));
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
        if (getFolderDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFolderDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFolderDetails(), i);
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
        new org.apache.axis.description.TypeDesc(ListFoldersResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", ">listFoldersResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "folderDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.wso2.org/php/xsd", "FolderDetails"));
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
