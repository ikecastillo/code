/**
 * QMetryWSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wso2.www.php;

public class QMetryWSLocator extends org.apache.axis.client.Service implements org.wso2.www.php.QMetryWS {

    public QMetryWSLocator() {
    }


    public QMetryWSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QMetryWSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QMetryWSSOAPPort_Http
    private java.lang.String QMetryWSSOAPPort_Http_address = "http://10.134.8.17/qmetry/WEB-INF/ws/service.php";

    public java.lang.String getQMetryWSSOAPPort_HttpAddress() {
        return QMetryWSSOAPPort_Http_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QMetryWSSOAPPort_HttpWSDDServiceName = "QMetryWSSOAPPort_Http";

    public java.lang.String getQMetryWSSOAPPort_HttpWSDDServiceName() {
        return QMetryWSSOAPPort_HttpWSDDServiceName;
    }

    public void setQMetryWSSOAPPort_HttpWSDDServiceName(java.lang.String name) {
        QMetryWSSOAPPort_HttpWSDDServiceName = name;
    }

    public org.wso2.www.php.QMetryWSPortType getQMetryWSSOAPPort_Http() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QMetryWSSOAPPort_Http_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQMetryWSSOAPPort_Http(endpoint);
    }

    public org.wso2.www.php.QMetryWSPortType getQMetryWSSOAPPort_Http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.wso2.www.php.QMetryWSSOAPBindingStub _stub = new org.wso2.www.php.QMetryWSSOAPBindingStub(portAddress, this);
            _stub.setPortName(getQMetryWSSOAPPort_HttpWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQMetryWSSOAPPort_HttpEndpointAddress(java.lang.String address) {
        QMetryWSSOAPPort_Http_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.wso2.www.php.QMetryWSPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                org.wso2.www.php.QMetryWSSOAPBindingStub _stub = new org.wso2.www.php.QMetryWSSOAPBindingStub(new java.net.URL(QMetryWSSOAPPort_Http_address), this);
                _stub.setPortName(getQMetryWSSOAPPort_HttpWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("QMetryWSSOAPPort_Http".equals(inputPortName)) {
            return getQMetryWSSOAPPort_Http();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.wso2.org/php", "QMetryWS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.wso2.org/php", "QMetryWSSOAPPort_Http"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QMetryWSSOAPPort_Http".equals(portName)) {
            setQMetryWSSOAPPort_HttpEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
