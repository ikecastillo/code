/**
 * ServerInfoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.objects.xsd;

public class ServerInfoResponse  implements java.io.Serializable {
    private java.lang.String customerId;

    private java.lang.String customerName;

    private java.lang.String login;

    private java.lang.String primaryServerUrl;

    private java.lang.String secondaryServerUrl;

    private java.lang.String userId;

    public ServerInfoResponse() {
    }

    public ServerInfoResponse(
           java.lang.String customerId,
           java.lang.String customerName,
           java.lang.String login,
           java.lang.String primaryServerUrl,
           java.lang.String secondaryServerUrl,
           java.lang.String userId) {
           this.customerId = customerId;
           this.customerName = customerName;
           this.login = login;
           this.primaryServerUrl = primaryServerUrl;
           this.secondaryServerUrl = secondaryServerUrl;
           this.userId = userId;
    }


    /**
     * Gets the customerId value for this ServerInfoResponse.
     * 
     * @return customerId
     */
    public java.lang.String getCustomerId() {
        return customerId;
    }


    /**
     * Sets the customerId value for this ServerInfoResponse.
     * 
     * @param customerId
     */
    public void setCustomerId(java.lang.String customerId) {
        this.customerId = customerId;
    }


    /**
     * Gets the customerName value for this ServerInfoResponse.
     * 
     * @return customerName
     */
    public java.lang.String getCustomerName() {
        return customerName;
    }


    /**
     * Sets the customerName value for this ServerInfoResponse.
     * 
     * @param customerName
     */
    public void setCustomerName(java.lang.String customerName) {
        this.customerName = customerName;
    }


    /**
     * Gets the login value for this ServerInfoResponse.
     * 
     * @return login
     */
    public java.lang.String getLogin() {
        return login;
    }


    /**
     * Sets the login value for this ServerInfoResponse.
     * 
     * @param login
     */
    public void setLogin(java.lang.String login) {
        this.login = login;
    }


    /**
     * Gets the primaryServerUrl value for this ServerInfoResponse.
     * 
     * @return primaryServerUrl
     */
    public java.lang.String getPrimaryServerUrl() {
        return primaryServerUrl;
    }


    /**
     * Sets the primaryServerUrl value for this ServerInfoResponse.
     * 
     * @param primaryServerUrl
     */
    public void setPrimaryServerUrl(java.lang.String primaryServerUrl) {
        this.primaryServerUrl = primaryServerUrl;
    }


    /**
     * Gets the secondaryServerUrl value for this ServerInfoResponse.
     * 
     * @return secondaryServerUrl
     */
    public java.lang.String getSecondaryServerUrl() {
        return secondaryServerUrl;
    }


    /**
     * Sets the secondaryServerUrl value for this ServerInfoResponse.
     * 
     * @param secondaryServerUrl
     */
    public void setSecondaryServerUrl(java.lang.String secondaryServerUrl) {
        this.secondaryServerUrl = secondaryServerUrl;
    }


    /**
     * Gets the userId value for this ServerInfoResponse.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this ServerInfoResponse.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServerInfoResponse)) return false;
        ServerInfoResponse other = (ServerInfoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.customerId==null && other.getCustomerId()==null) || 
             (this.customerId!=null &&
              this.customerId.equals(other.getCustomerId()))) &&
            ((this.customerName==null && other.getCustomerName()==null) || 
             (this.customerName!=null &&
              this.customerName.equals(other.getCustomerName()))) &&
            ((this.login==null && other.getLogin()==null) || 
             (this.login!=null &&
              this.login.equals(other.getLogin()))) &&
            ((this.primaryServerUrl==null && other.getPrimaryServerUrl()==null) || 
             (this.primaryServerUrl!=null &&
              this.primaryServerUrl.equals(other.getPrimaryServerUrl()))) &&
            ((this.secondaryServerUrl==null && other.getSecondaryServerUrl()==null) || 
             (this.secondaryServerUrl!=null &&
              this.secondaryServerUrl.equals(other.getSecondaryServerUrl()))) &&
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
        if (getCustomerId() != null) {
            _hashCode += getCustomerId().hashCode();
        }
        if (getCustomerName() != null) {
            _hashCode += getCustomerName().hashCode();
        }
        if (getLogin() != null) {
            _hashCode += getLogin().hashCode();
        }
        if (getPrimaryServerUrl() != null) {
            _hashCode += getPrimaryServerUrl().hashCode();
        }
        if (getSecondaryServerUrl() != null) {
            _hashCode += getSecondaryServerUrl().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServerInfoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://objects.services/xsd", "ServerInfoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "customerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "customerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("login");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "login"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryServerUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "primaryServerUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondaryServerUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "secondaryServerUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://objects.services/xsd", "userId"));
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
