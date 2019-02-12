package com.dt.jira.plugin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
	
			private String title;
			private String pubDate;
			private String author;
			private String description;
			
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}
			public String getPubDate() {
				return pubDate;
			}
			public void setPubDate(String pubDate) {
				this.pubDate = pubDate;
			}
			public String getAuthor() {
				return author;
			}
			public void setAuthor(String author) {
				this.author = author;
			}
			public String getDescription() {
				return description;
			}
			public void setDescription(String description) {
				this.description = description;
			}
			
			
}
