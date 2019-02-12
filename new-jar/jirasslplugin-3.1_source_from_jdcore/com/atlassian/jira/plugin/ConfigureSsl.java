package com.atlassian.jira.plugin;

import com.atlassian.jira.config.util.JiraHome;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.log4j.Logger;

@com.atlassian.sal.api.websudo.WebSudoRequired
public class ConfigureSsl extends com.atlassian.jira.web.action.JiraWebActionSupport
{
  private String rulesText;
  private LinkedList<String> existingRules;
  private final String SUCCESS = "success";
  private String message = new String();
  private StringBuilder hostsAdded = new StringBuilder();
  
  private String portNumber;
  
  public ConfigureSsl() {}
  
  public String doDefault()
    throws Exception
  {
    return "";
  }
  
  public String doExecute() throws Exception
  {
    if (!isAdministrator()) {
      return "denied";
    }
    buildExistingRules();
    


    if (rulesText != null)
    {
      java.util.List<String> newRules = new java.util.ArrayList();
      String returnString = "success";
      

      String[] rules = rulesText.split("\n");
      for (String rule : rules)
      {
        rule = rule.trim();
        if ((!rule.equals("")) && (!rule.isEmpty()))
        {
          newRules.add(rule);
          Pattern p = Pattern.compile("\\w*://");
          Matcher m = p.matcher(rule);
          if (m.find())
            rule = rule.substring(m.end(), rule.length());
          String[] splitHost = rule.split(":");
          if (splitHost.length > 1)
          {
            portNumber = splitHost[1];
            if (portNumber.contains("/")) {
              int contextIndex = portNumber.indexOf("/");
              portNumber = portNumber.substring(0, contextIndex);
              message = addSslCert(splitHost[0], Integer.parseInt(portNumber), "changeit");
            }
            message = addSslCert(splitHost[0], Integer.parseInt(portNumber), "changeit");

          }
          else if (splitHost[0].contains("/")) {
            int contextIndex = splitHost[0].indexOf("/");
            splitHost[0] = splitHost[0].substring(0, contextIndex);
            message = addSslCert(splitHost[0], 443, "changeit");
          } else {
            message = addSslCert(splitHost[0], 443, "changeit");
          }
        }
      }
      











      return returnString;
    }
    
    return "configure";
  }
  
  private void buildExistingRules() throws Exception
  {
    existingRules = new LinkedList();
    
    File file = new File("cacerts");
    if (!file.isFile()) {
      char SEP = File.separatorChar;
      File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
      
      file = new File(dir, "cacerts");
      if (!file.isFile()) {
        file = new File(dir, "cacerts");
      }
    }
    InputStream in = new FileInputStream(file);
    
    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
    ks.load(in, "changeit".toCharArray());
    in.close();
    Enumeration<String> existing = ks.aliases();
    while (existing.hasMoreElements())
    {
      java.security.cert.Certificate cert = ks.getCertificate((String)existing.nextElement());
      if ((cert instanceof X509Certificate))
      {

        existingRules.add(((X509Certificate)cert).getSubjectDN().getName());
      }
    }
  }
  

  public String addSslCert(String host, int port, String passphrase)
    throws Exception
  {
    File file = new File("cacerts");
    if (!file.isFile()) {
      char SEP = File.separatorChar;
      File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
      
      file = new File(dir, "cacerts");
      if (!file.isFile()) {
        file = new File(dir, "cacerts");
      }
    }
    InputStream in = new FileInputStream(file);
    
    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
    
    ks.load(in, passphrase.toCharArray());
    in.close();
    
    SSLContext context = SSLContext.getInstance("TLS");
    TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    
    tmf.init(ks);
    X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];
    
    SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
    context.init(null, new javax.net.ssl.TrustManager[] { tm }, null);
    javax.net.ssl.SSLSocketFactory factory = context.getSocketFactory();
    
    log.debug("Opening connection to " + host + ":" + port + "...");
    try {
      SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
      socket.setSoTimeout(10000);
      log.debug("Starting SSL handshake...");
      socket.startHandshake();
      socket.close();
      
      log.debug("No errors, certificate is already trusted");
    }
    catch (Exception ex) {
      addErrorMessage(ex.getClass() + " during connection to " + host + ":" + port + "...");
    }
    
    X509Certificate[] chain = chain;
    if (chain == null) {
      addErrorMessage("Could not obtain server certificate chain");
      return "error";
    }
    
    BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
    

    MessageDigest sha1 = MessageDigest.getInstance("SHA1");
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    for (int i = 0; i < chain.length; i++) {
      X509Certificate cert = chain[i];
      sha1.update(cert.getEncoded());
      md5.update(cert.getEncoded());
    }
    

    String jiraHomeDirectory = ((JiraHome)com.atlassian.jira.component.ComponentAccessor.getComponentOfType(JiraHome.class)).getHome().toString();
    for (int k = 0; k < chain.length; k++)
    {
      X509Certificate cert = chain[k];
      String alias = host + "-" + (k + 1);
      ks.setCertificateEntry(alias, cert);
      
      try
      {
        OutputStream out = new FileOutputStream(file);
        ks.store(out, passphrase.toCharArray());
        out.close();

      }
      catch (FileNotFoundException fnfe)
      {
        OutputStream out = new FileOutputStream(new File(jiraHomeDirectory + "/cacerts"));
        ks.store(out, passphrase.toCharArray());
        out.close();
      }
    }
    

    hostsAdded.append("Added ");
    hostsAdded.append(host);
    hostsAdded.append(" to temporary location.\n ");
    
    return "To complete the process, copy the file from " + jiraHomeDirectory + "/cacerts to " + file + ", then restart JIRA.";
  }
  
  private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();
  
  private static String toHexString(byte[] bytes) {
    StringBuilder sb = new StringBuilder(bytes.length * 3);
    for (int b : bytes) {
      b &= 0xFF;
      sb.append(HEXDIGITS[(b >> 4)]);
      sb.append(HEXDIGITS[(b & 0xF)]);
      sb.append(' ');
    }
    return sb.toString();
  }
  
  public boolean isAdministrator() {
    return hasGlobalPermission(com.atlassian.jira.permission.GlobalPermissionKey.ADMINISTER);
  }
  
  public String getRulesText()
  {
    return rulesText;
  }
  
  public void setRulesText(String rulesText)
  {
    this.rulesText = rulesText;
  }
  
  public LinkedList<String> getExistingRules()
  {
    return existingRules;
  }
  
  public void setExistingRules(LinkedList<String> existingRules)
  {
    this.existingRules = existingRules;
  }
  
  public String getMessage()
  {
    return message;
  }
  
  public String getHostsAdded()
  {
    return hostsAdded.toString();
  }
  
  private static class SavingTrustManager implements X509TrustManager
  {
    private final X509TrustManager tm;
    private X509Certificate[] chain;
    
    SavingTrustManager(X509TrustManager tm) {
      this.tm = tm;
    }
    
    public X509Certificate[] getAcceptedIssuers() {
      throw new UnsupportedOperationException();
    }
    
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
    {
      throw new UnsupportedOperationException();
    }
    
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
    {
      this.chain = chain;
      tm.checkServerTrusted(chain, authType);
    }
  }
}
