package com.dt.autoassign.ao;

import net.java.ao.Entity;

/**
 * Created by yagnesh.bhat on 5/5/2016.
 */
public interface ConfigDB  extends Entity {
    String getsolutionGroup();
    void setsolutionGroup(String solutionGroup);

    String getissueType();
    void setissueType(String issueType);

    String getowner();
    void setowner(String owner);

    String getimplementor();
    void setimplementor(String implementor);

}
