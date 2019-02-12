package com.dt.jira.create.incident.report.subtask.utils;

import java.util.ArrayList;
import java.util.List;

import com.atlassian.jira.issue.fields.Field;
import com.opensymphony.workflow.InvalidInputException;

/**
 * Container for storing and processing validator errors.
 *
 * @author Firoz Khan
 */
public class ValidatorErrorsBuilder {
    private List<Field> fields = new ArrayList<Field>();
    private List<String> messages = new ArrayList<String>();
    private boolean forScreen;

    /**
     * @param forScreen is we throwing exception for screen
     */
    public ValidatorErrorsBuilder(boolean forScreen) {
        this.forScreen = forScreen;
    }

    public void addError(Field field, String message) {
        this.fields.add(field);
        this.messages.add(message);
    }

    public void addError(String message) {
        this.addError(null, message);
    }

    public void process() throws InvalidInputException {
        if (this.fields.size() == 0) {
            return;
        }

        if(fields.size()==1) {
            Field f = this.fields.get(0);
            String m = this.messages.get(0);

            if(f == null || ! forScreen) {
                throw new InvalidInputException(m);
            } else {
                throw new InvalidInputException(f.getId(),m);
            }
        } else {
            InvalidInputException e = new InvalidInputException();

            for (int i = 0; i < fields.size(); i++) {
                Field f = this.fields.get(i);
                String m = this.messages.get(i);

                if (f == null || ! forScreen) {
                    e.addError(m);
                } else {
                    e.addError(f.getId(), m);
                }
            }

            throw e;
        }
    }
}