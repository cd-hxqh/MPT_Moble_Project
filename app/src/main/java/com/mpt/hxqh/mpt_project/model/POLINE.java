package com.mpt.hxqh.mpt_project.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22.
 */

public class POLINE implements Serializable {
    public String LINETYPE;//LineType
    public String SERIALNUM;//SN
    public String LOCATION;//Location
    public String STORELOC;//Storeroom
    public String SCANNER;//Scanner
    public String POLINENUM;//Line
    public String EXIST;//Exist？

    public String getLINETYPE() {
        return LINETYPE;
    }

    public void setLINETYPE(String LINETYPE) {
        this.LINETYPE = LINETYPE;
    }

    public String getSERIALNUM() {
        return SERIALNUM;
    }

    public void setSERIALNUM(String SERIALNUM) {
        this.SERIALNUM = SERIALNUM;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getSTORELOC() {
        return STORELOC;
    }

    public void setSTORELOC(String STORELOC) {
        this.STORELOC = STORELOC;
    }

    public String getSCANNER() {
        return SCANNER;
    }

    public void setSCANNER(String SCANNER) {
        this.SCANNER = SCANNER;
    }

    public String getPOLINENUM() {
        return POLINENUM;
    }

    public void setPOLINENUM(String POLINENUM) {
        this.POLINENUM = POLINENUM;
    }

    public String getEXIST() {
        return EXIST;
    }

    public void setEXIST(String EXIST) {
        this.EXIST = EXIST;
    }
}
