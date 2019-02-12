package com.dt.jira.plugin.rest;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.bc.JiraServiceContextImpl;
import com.atlassian.jira.bc.filter.SearchRequestService;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.search.SearchRequest;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.util.SimpleErrorCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model object that gets the filter name and its filterid
 * Created by yagnesh.bhat on 8/25/2015.
 */
@XmlRootElement(name = "message4")
@XmlAccessorType(XmlAccessType.FIELD)
public class FilterModel {

    @XmlElement(name = "filterId")
    private String filterId;

    @XmlElement(name = "filterName")
    private String filterName;

    private SearchRequestService searchRequestService;

    Logger log = LoggerFactory.getLogger(FilterModel.class);

    public String getFilterId() {
        return filterId;
    }
    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }

    public String getFilterName() {
        return filterName;
    }
    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }
    public FilterModel(String filterId) {
        super();
        this.filterId = filterId;
        determineFilterName(filterId);
    }

    public String determineFilterName (String filterId) {
        SearchRequest sr = null;
        SearchRequestService searchRequestService = ComponentAccessor.getComponent(SearchRequestService.class);
        //String filterName = "Change Management Filter";


        JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
        final ApplicationUser user = authenticationContext.getLoggedInUser();
        log.debug(" Trying to Extract Filter name");
        if (filterId.startsWith("filter-"))
        {
            Long filterIdLong = new Long(filterId.substring(7));
            sr = searchRequestService.getFilter(
                    new JiraServiceContextImpl(user, new SimpleErrorCollection()), filterIdLong);
            log.debug(" IS  SR NOT NULL?");
            if (sr != null)
            {
                log.debug("SR IS NOT NULL");
                //params.put("searchRequest", sr);
                filterName = sr.getName();
            }
        }

        log.debug("Filter Name retrieved is " + filterName);
        return filterName;
    }
}
