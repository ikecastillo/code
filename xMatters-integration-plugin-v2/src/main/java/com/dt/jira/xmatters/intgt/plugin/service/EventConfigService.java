package com.dt.jira.xmatters.intgt.plugin.service;

import com.dt.jira.xmatters.intgt.plugin.rest.EventBean;

import java.util.List;

/**
 * Created by Yagnesh.Bhat on 10/19/2015.
 */
public interface EventConfigService {

    EventBean addEvent(final EventBean eventBean);

    EventBean getEvent(final EventBean eventBean);

    EventBean deleteEvent(final EventBean eventBean);

    List<EventBean> getAllEventsFromDB();
}
