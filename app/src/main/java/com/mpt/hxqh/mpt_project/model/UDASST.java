package com.mpt.hxqh.mpt_project.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22.
 * 资产维修
 */

public class UDASST implements Serializable {
    public String DESCRIPTION;//DESCRIPTION
    public String LOCATION;//LOCATION
    public String REPAIRNUM;//REPAIRNUM
    public String UDASSTNUM;//UDASSTNUM
    public String UDINSTALLDATE;//UDINSTALLDATE
    public String UDSTATUS;//UDSTATUS

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getUDASSTNUM() {
        return UDASSTNUM;
    }

    public void setUDASSTNUM(String UDASSTNUM) {
        this.UDASSTNUM = UDASSTNUM;
    }

    public String getUDINSTALLDATE() {
        return UDINSTALLDATE;
    }

    public void setUDINSTALLDATE(String UDINSTALLDATE) {
        this.UDINSTALLDATE = UDINSTALLDATE;
    }

    public String getUDSTATUS() {
        return UDSTATUS;
    }

    public void setUDSTATUS(String UDSTATUS) {
        this.UDSTATUS = UDSTATUS;
    }

    public String getREPAIRNUM() {
        return REPAIRNUM;
    }

    public void setREPAIRNUM(String REPAIRNUM) {
        this.REPAIRNUM = REPAIRNUM;
    }
}
