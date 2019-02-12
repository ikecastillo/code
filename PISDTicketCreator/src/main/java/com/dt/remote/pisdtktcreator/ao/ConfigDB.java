package com.dt.remote.pisdtktcreator.ao;

import net.java.ao.Entity;

/**
 * Created by yagnesh.bhat on 4/18/2016.
 */
public interface ConfigDB extends Entity {

    String getsrvcDskId();
    void setsrvcDskId(String srvcDskId);

    String getsrvcDskName();
    void setsrvcDskName(String srvcDskName);

    String getreqTypeId();
    void setreqTypeId(String reqTypeId);

    String getreqTypeName();
    void setreqTypeName(String reqTypeName);

    String getcustFields();
    void setcustFields(String custFields);
}
