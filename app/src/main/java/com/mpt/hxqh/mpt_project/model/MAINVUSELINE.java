package com.mpt.hxqh.mpt_project.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22.
 * 物料退库
 */

public class MAINVUSELINE implements Serializable {
    public String LINETYPE;//LINETYPE
    public String QUANTITY;//QUANTITY
    public String TOLOCATION;//TOLOCATION
    public String TOSTORELOC;//TOSTORELOC

    public String getLINETYPE() {
        return LINETYPE;
    }

    public void setLINETYPE(String LINETYPE) {
        this.LINETYPE = LINETYPE;
    }

    public String getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(String QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public String getTOLOCATION() {
        return TOLOCATION;
    }

    public void setTOLOCATION(String TOLOCATION) {
        this.TOLOCATION = TOLOCATION;
    }

    public String getTOSTORELOC() {
        return TOSTORELOC;
    }

    public void setTOSTORELOC(String TOSTORELOC) {
        this.TOSTORELOC = TOSTORELOC;
    }
}
