package com.dt.jira.xmatters.intgt.plugin.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.dt.jira.xmatters.intgt.plugin.ao.EventDB;
import com.dt.jira.xmatters.intgt.plugin.rest.EventBean;
import net.java.ao.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Yagnesh.Bhat on 10/19/2015.
 */
public class EventConfigServiceImpl implements  EventConfigService {

    private final Logger log = LoggerFactory.getLogger(EventConfigServiceImpl.class);
    private final ActiveObjects ao;

    /**
     * Constructor
     * @param ao the ActiveObjects to be injected
     */
    public EventConfigServiceImpl(ActiveObjects ao) {
        this.ao = checkNotNull(ao);
    }


    /**
     * Save Event config to DB
     * @param eventBean
     * @return event bean that is saved
     */
    @Override
    public EventBean addEvent(final EventBean eventBean) {

        EventBean alreadyExistEventBean = getEvent(eventBean);

        if (alreadyExistEventBean==null) {
            ao.executeInTransaction(new TransactionCallback<EventBean>() {
                @Override
                public EventBean doInTransaction() {
                    final EventDB xMEventDB1 = ao.create(EventDB.class);
                    xMEventDB1.setFormAPIID(eventBean.getFormAPIID());
                    xMEventDB1.setxMattersTemplateName(eventBean.getxMattersTemplateName());
                    xMEventDB1.setxMattersFormWSURL(eventBean.getxMattersFormWSURL());
                    xMEventDB1.setxMattersResponseAvl(eventBean.getxMattersFormResponseCodeAvailableUUID());
                    xMEventDB1.setxMattersResponseNotAvl(eventBean.getxMattersFormResponseCodeNotAvailableUUID());
                    xMEventDB1.save();
                    return eventBean;
                }
            });
        }
        return null;
    }

    /**
     * Gets the event by the form API Id
     * @param eventBean
     * @return event bean if exists, null otherwise
     */
    @Override
    public EventBean getEvent(EventBean eventBean) {

        EventBean alreadyExistEventBean = null;
        EventDB[] xMEventDB = ao.find(EventDB.class, " FORM_APIID = ? ", eventBean.getFormAPIID());

        log.debug("Number of events found (should be 1) : " + xMEventDB.length);

        /* There will only be one event returned at the max, if any, since form API Id would be unique */
        for (EventDB xMEventDB1 : xMEventDB) {
            alreadyExistEventBean = new EventBean();
            alreadyExistEventBean.setFormAPIID(xMEventDB1.getFormAPIID());
            alreadyExistEventBean.setxMattersTemplateName(xMEventDB1.getxMattersTemplateName());
            alreadyExistEventBean.setxMattersFormWSURL(xMEventDB1.getxMattersFormWSURL());
            alreadyExistEventBean.setxMattersFormResponseCodeAvailableUUID(xMEventDB1.getxMattersResponseAvl());
            alreadyExistEventBean.setxMattersFormResponseCodeNotAvailableUUID(xMEventDB1.getxMattersResponseNotAvl());
        }
        return alreadyExistEventBean;
    }

    /**
     * Deletes an event having a form api Id
     * @param eventBean
     * @return the event bean if deleted successfully, null otherwise
     */
    @Override
    public EventBean deleteEvent(final EventBean eventBean) {
        ao.executeInTransaction(new TransactionCallback<EventBean>() {
            @Override
            public EventBean doInTransaction() {
                EventBean eb = new EventBean();
                EventDB[] xMEventDb = ao.find(EventDB.class, "FORM_APIID = ?", eventBean.getFormAPIID());
                if (xMEventDb.length > 0 ) {
                    ao.delete(xMEventDb);
                }
                return eb;
            }
        });
        return null;
    }

    /**
     * Get all the xMatters Events configured in DB
     *
     * @return list of all events
     */
    @Override
    public List<EventBean> getAllEventsFromDB() {

        List<EventBean> eventsList = new ArrayList<EventBean>();
        EventBean eventBean;
        EventDB[] xMEventDB = ao.find(EventDB.class, Query.select().order("FORM_APIID ASC"));
        for (EventDB xMEventDB1 : xMEventDB) {
            eventBean = new EventBean();
            eventBean.setFormAPIID(xMEventDB1.getFormAPIID());
            eventBean.setxMattersTemplateName(xMEventDB1.getxMattersTemplateName());
            eventBean.setxMattersFormWSURL(xMEventDB1.getxMattersFormWSURL());
            eventBean.setxMattersFormResponseCodeAvailableUUID(xMEventDB1.getxMattersResponseAvl());
            eventBean.setxMattersFormResponseCodeNotAvailableUUID(xMEventDB1.getxMattersResponseNotAvl());
            eventsList.add(eventBean);
        }
        return eventsList;
    }
}
