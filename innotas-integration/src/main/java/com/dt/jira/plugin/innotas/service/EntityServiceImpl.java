/**
 * 
 */
package com.dt.jira.plugin.innotas.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import services.objects.xsd.EntityObj;
import services.objects.xsd.ValuePair;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.jira.plugin.rest.ConfigResource.Config;
import com.atlassian.jira.component.ComponentAccessor;

/**
 * @author sriram.rajaraman
 *
 */
public class EntityServiceImpl implements EntityService {	
	private static URL url = null;
	private static String uid = "nessapi_937524754";
	private static String pwd = "innotas0801";
	private String sessionId=null;
	private static final String PLUGIN_STORAGE_KEY = Config.class.getName();
	private static PluginSettingsFactory pluginSettingsFactory;
	
	public EntityServiceImpl(PluginSettingsFactory psf){
		this.pluginSettingsFactory = psf;
		init();
	}
	private void init() {
	System.out.println("PLUGIN_STORAGE_KEY : " + PLUGIN_STORAGE_KEY);
		//pluginSettingsFactory = ComponentAccessor.getComponent(PluginSettingsFactory.class);
		PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
		try {
				this.url = new URL((String)pluginSettings.get(PLUGIN_STORAGE_KEY + ".innotasUrl"));
			if (this.url == null)
				// set this as default incase a service url is not saved in config
				this.url = new URL("https://api1.innotas.com/services/MainService");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.uid = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".innotasUid");
		this.pwd = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".innotasPwd");
		//System.out.println("url: "+ this.url + "ID: " + this.uid + "pwd: " + this.pwd); 
	}
	@Override
	public EntityObj[] findEntity(Long entityTypeId, ValuePair[] vp, long[] fieldsRequest){
		System.out.println("url: "+ this.url + " ID: " + this.uid + " pwd: " + this.pwd); 
		services.MainService_SOAP11HTTPS_client2_newLocator svc = new services.MainService_SOAP11HTTPS_client2_newLocator();
		services.MainService_SOAP11HTTPS_client2_new_Interface p;
		EntityObj[] eob = null;
		try {
			p = svc.getMainService_SOAP11HTTPS_client2_new(this.url);
			// this.sessionId = this.sessionId != null?this.sessionId:p.login(this.uid, this.pwd);
			this.sessionId = p.login(this.uid, this.pwd);
			System.out.println("Session Id: " + sessionId);
			eob = p.findEntity(sessionId, entityTypeId, vp, fieldsRequest );
			for (int i=0; i<eob.length; i++) {
				//System.out.print("Entity Id: " + eob[i].getEntityId());
				ValuePair[] mv = eob[i].getMethodValues();
				//System.out.println("Entity Name: " + mv[0].getElementValue());
			}
			
			p.logout(this.sessionId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eob;
	}

}
