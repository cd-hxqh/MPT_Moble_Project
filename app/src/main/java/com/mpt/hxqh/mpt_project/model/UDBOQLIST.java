package com.mpt.hxqh.mpt_project.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22.
 * 采购接收
 */

public class UDBOQLIST implements Serializable {
    public String EXIST;//EXIST
    public String UDBOQLISTID;//UDBOQLISTID
    public String DESCRIPTION;//DESCRIPTION
    public String ITEMNUM;//ITEMNUM
    public String MATRECTRANSID;//MATRECTRANSID
    public String PONUM;//PONUM
    public String REVISIONNUM;//REVISIONNUM
    public String SERIALNUM;//SERIALNUM
    public String UNITCOST;//UNITCOST
    public String UPDATEBY;//UPDATEBY
    public String UPDATEDATE;//UPDATEDATE

    public String getEXIST() {
        return EXIST;
    }

    public void setEXIST(String EXIST) {
        this.EXIST = EXIST;
    }

    public String getUDBOQLISTID() {
        return UDBOQLISTID;
    }

    public void setUDBOQLISTID(String UDBOQLISTID) {
        this.UDBOQLISTID = UDBOQLISTID;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getITEMNUM() {
        return ITEMNUM;
    }

    public void setITEMNUM(String ITEMNUM) {
        this.ITEMNUM = ITEMNUM;
    }

    public String getMATRECTRANSID() {
        return MATRECTRANSID;
    }

    public void setMATRECTRANSID(String MATRECTRANSID) {
        this.MATRECTRANSID = MATRECTRANSID;
    }

    public String getPONUM() {
        return PONUM;
    }

    public void setPONUM(String PONUM) {
        this.PONUM = PONUM;
    }

    public String getREVISIONNUM() {
        return REVISIONNUM;
    }

    public void setREVISIONNUM(String REVISIONNUM) {
        this.REVISIONNUM = REVISIONNUM;
    }

    public String getSERIALNUM() {
        return SERIALNUM;
    }

    public void setSERIALNUM(String SERIALNUM) {
        this.SERIALNUM = SERIALNUM;
    }

    public String getUNITCOST() {
        return UNITCOST;
    }

    public void setUNITCOST(String UNITCOST) {
        this.UNITCOST = UNITCOST;
    }

    public String getUPDATEBY() {
        return UPDATEBY;
    }

    public void setUPDATEBY(String UPDATEBY) {
        this.UPDATEBY = UPDATEBY;
    }

    public String getUPDATEDATE() {
        return UPDATEDATE;
    }

    public void setUPDATEDATE(String UPDATEDATE) {
        this.UPDATEDATE = UPDATEDATE;
    }
}
