/**
 * InsertNote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class InsertNote  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.Long parentEntityTypeId;

    private java.lang.Long parentId;

    private java.lang.String title;

    private java.lang.String content;

    public InsertNote() {
    }

    public InsertNote(
           java.lang.String sessionId,
           java.lang.Long parentEntityTypeId,
           java.lang.Long parentId,
           java.lang.String title,
           java.lang.String content) {
           this.sessionId = sessionId;
           this.parentEntityTypeId = parentEntityTypeId;
           this.parentId = parentId;
           this.title = title;
           this.content = content;
    }


    /**
     * Gets the sessionId value for this InsertNote.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this InsertNote.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the parentEntityTypeId value for this InsertNote.
     * 
     * @return parentEntityTypeId
     */
    public java.lang.Long getParentEntityTypeId() {
        return parentEntityTypeId;
    }


    /**
     * Sets the parentEntityTypeId value for this InsertNote.
     * 
     * @param parentEntityTypeId
     */
    public void setParentEntityTypeId(java.lang.Long parentEntityTypeId) {
        this.parentEntityTypeId = parentEntityTypeId;
    }


    /**
     * Gets the parentId value for this InsertNote.
     * 
     * @return parentId
     */
    public java.lang.Long getParentId() {
        return parentId;
    }


    /**
     * Sets the parentId value for this InsertNote.
     * 
     * @param parentId
     */
    public void setParentId(java.lang.Long parentId) {
        this.parentId = parentId;
    }


    /**
     * Gets the title value for this InsertNote.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this InsertNote.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the content value for this InsertNote.
     * 
     * @return content
     */
    public java.lang.String getContent() {
        return content;
    }


    /**
     * Sets the content value for this InsertNote.
     * 
     * @param content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsertNote)) return false;
        InsertNote other = (InsertNote) obj;
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
            ((this.parentEntityTypeId==null && other.getParentEntityTypeId()==null) || 
             (this.parentEntityTypeId!=null &&
              this.parentEntityTypeId.equals(other.getParentEntityTypeId()))) &&
            ((this.parentId==null && other.getParentId()==null) || 
             (this.parentId!=null &&
              this.parentId.equals(other.getParentId()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent())));
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
        if (getParentEntityTypeId() != null) {
            _hashCode += getParentEntityTypeId().hashCode();
        }
        if (getParentId() != null) {
            _hashCode += getParentId().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsertNote.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services", ">insertNote"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentEntityTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "parentEntityTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "parentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://services", "content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
