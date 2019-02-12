package com.dt.jira.plugin.genericbarchart.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Yagnesh.Bhat on 8/14/2015.
 */
@XmlRootElement(name = "genericsummary")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenericSummaryModel implements Comparable<GenericSummaryModel>{


    @XmlElement(name = "legend")
    public String legend;

    @XmlElement(name = "drillDown")
    private String drillDown;

    @XmlElement(name = "graphCoordinates")
    private List<GenericModel> graphCoordinates;


    public String getLegend() {
        return legend;
    }


    public void setLegend(String legend) {
        this.legend = legend;
    }


    public List<GenericModel> getGraphCoordinates() {
        return graphCoordinates;
    }


    public void setGraphCoordinates(List<GenericModel> graphCoordinates) {
        this.graphCoordinates = graphCoordinates;
    }


    public String getDrillDown() {
        return drillDown;
    }


    public void setDrillDown(String drillDown) {
        this.drillDown = drillDown;
    }


    public GenericSummaryModel() {
        super();
    }


    @Override
    public int compareTo(GenericSummaryModel o) {
        if(o.legend.equals("Major Issues")){
            return 1;
        }
        int legendValue = this.legend.compareTo(o.legend);
        return legendValue;
    }




}
