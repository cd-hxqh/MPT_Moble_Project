package com.mpt.hxqh.mpt_project.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22.
 * 资产转移
 */

public class INVUSELINE implements Serializable {
    public String INVUSELINENUM;//INVUSELINENUM
    public String DESCRIPTION;//描述
    public String INVUSENUM;//INVUSENUM
    public String ISSUETO;//ISSUETO
    public String ITEMNUM;//ITEMNUM
    public String LINETYPE;//LINETYPE
    public String LOCATION;//LOCATION
    public String QUANTITY;//QUANTITY
    public String ROTASSETNUM;//ROTASSETNUM
    public String TOLOCATION;//TOLOCATION
    public String TOSITEID;//TOSITEID
    public String TOSTORELOC;//TOSTORELOC


    public String getINVUSELINENUM() {
        return INVUSELINENUM;
    }

    public void setINVUSELINENUM(String INVUSELINENUM) {
        this.INVUSELINENUM = INVUSELINENUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getINVUSENUM() {
        return INVUSENUM;
    }

    public void setINVUSENUM(String INVUSENUM) {
        this.INVUSENUM = INVUSENUM;
    }

    public String getISSUETO() {
        return ISSUETO;
    }

    public void setISSUETO(String ISSUETO) {
        this.ISSUETO = ISSUETO;
    }

    public String getITEMNUM() {
        return ITEMNUM;
    }

    public void setITEMNUM(String ITEMNUM) {
        this.ITEMNUM = ITEMNUM;
    }

    public String getLINETYPE() {
        return LINETYPE;
    }

    public void setLINETYPE(String LINETYPE) {
        this.LINETYPE = LINETYPE;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(String QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public String getROTASSETNUM() {
        return ROTASSETNUM;
    }

    public void setROTASSETNUM(String ROTASSETNUM) {
        this.ROTASSETNUM = ROTASSETNUM;
    }

    public String getTOLOCATION() {
        return TOLOCATION;
    }

    public void setTOLOCATION(String TOLOCATION) {
        this.TOLOCATION = TOLOCATION;
    }

    public String getTOSITEID() {
        return TOSITEID;
    }

    public void setTOSITEID(String TOSITEID) {
        this.TOSITEID = TOSITEID;
    }

    public String getTOSTORELOC() {
        return TOSTORELOC;
    }

    public void setTOSTORELOC(String TOSTORELOC) {
        this.TOSTORELOC = TOSTORELOC;
    }
}
