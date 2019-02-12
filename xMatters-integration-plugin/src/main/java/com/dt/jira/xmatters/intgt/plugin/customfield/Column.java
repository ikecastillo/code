package com.dt.jira.xmatters.intgt.plugin.customfield;

import java.util.List;
import com.dt.jira.xmatters.intgt.plugin.customfield.TableCustomField;
public class Column
{
	
	 private String data;
	    private String type;
	    private String editor;
	    private List<String> selectOptions;
	    
	    public String toString()
	    {
	      return "" ;//TableCustomField.GSON.toJson(this);
	    }
	    
	    public void setColumnName(String data)
	    {
	      this.data = data;
	    }
	    
	    public void setType(String type)
	    {
	      this.type = type;
	    }
	    
	    public void setEditor(String editor)
	    {
	      this.editor = editor;
	    }
	    
	    public void setSelectOptions(List<String> selectOptions)
	    {
	      this.selectOptions = selectOptions;
	    }
	    
	    public String getColumnName()
	    {
	      return this.data;
	    }
	    
	    public String getType()
	    {
	      return this.type;
	    }
	    
	    public String getEditor()
	    {
	      return this.editor;
	    }
	    
	    public List<String> getSelectOptions()
	    {
	      return this.selectOptions;
	    }
	  }
	

