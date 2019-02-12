package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the backlog elaboration bar chart.
 * @author kiran.muthoju
 * (1-5, 6-10,11-15,16-20,21-50)
 *
 */
@XmlRootElement(name = "backlogelaboration")
@XmlAccessorType(XmlAccessType.FIELD)
public class BacklogElaborationModel {

	

	
	public BacklogElaborationModel(String label, String story,
			String technicalStory, String defect, String epic, String theme, String activity,
			String url, String ticks) {
		super();
		this.label = label;
		this.story = story;
		this.technicalStory = technicalStory;
		this.defect = defect;
		this.epic = epic;
		this.theme = theme;
		this.activity = activity;
		this.url = url;
		this.ticks = ticks;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTicks() {
		return ticks;
	}
	public void setTicks(String ticks) {
		this.ticks = ticks;
	}
	
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getTechnicalStory() {
		return technicalStory;
	}
	public void setTechnicalStory(String technicalStory) {
		this.technicalStory = technicalStory;
	}
	public String getDefect() {
		return defect;
	}
	public void setDefect(String defect) {
		this.defect = defect;
	}
	public String getEpic() {
		return epic;
	}
	public void setEpic(String epic) {
		this.epic = epic;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}

	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}


	@XmlElement(name = "label")
	private String label;
	@XmlElement(name = "story")
	private String story;	
	@XmlElement(name = "technicalStory")
	private String technicalStory;	
	@XmlElement(name = "defect")
	private String defect;		
	@XmlElement(name = "epic")
	private String epic;		
	@XmlElement(name = "activity")
	private String activity;
	@XmlElement(name = "theme")
	private String theme;		
	@XmlElement(name = "url")
	private String url;
	@XmlElement(name = "ticks")
	private String ticks;
	
	
	
	
	
	
	
}