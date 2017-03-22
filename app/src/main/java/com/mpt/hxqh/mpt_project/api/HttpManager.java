package com.mpt.hxqh.mpt_project.api;


import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.mpt.hxqh.mpt_project.R;
import com.mpt.hxqh.mpt_project.application.BaseApplication;
import com.mpt.hxqh.mpt_project.bean.LoginResults;
import com.mpt.hxqh.mpt_project.bean.Results;
import com.mpt.hxqh.mpt_project.config.Constants;
import com.mpt.hxqh.mpt_project.unit.AccountUtils;

import org.apache.http.Header;


/**
 * Created by apple on 15/5/27.
 */
public class HttpManager {

    private static BaseApplication mApp = BaseApplication.getInstance();
    private static AsyncHttpClient sClient = null;
    private static final String TAG = "HttpManager";
    private static String appid = null;
    private static String objectname = null;

    /**
     * 设置待办事项接口*
     */
    public static String getwfassignmentUrl(String persionid, String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.WFASSIGNMENT_APPID + "','objectname':'" + Constants.WFASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WFASSIGNMENTID DESC','condition':{'ASSIGNCODE':'" + persionid + "','ASSIGNSTATUS':'=INACTIVE'}}";
        } else {
            return "{'appid':'" + Constants.WFASSIGNMENT_APPID + "','objectname':'" + Constants.WFASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WFASSIGNMENTID DESC','condition':{'ASSIGNCODE':'" + persionid + "','ASSIGNSTATUS':'=INACTIVE'}" + ",'sinorsearch':{'WFASSIGNMENTID':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }


    /**
     * 设置工单接口*
     */
    public static String getworkorderUrl(String type,String status, String search, int curpage, int showcount) {
        switch (type) {
            case Constants.FR://故障工单
                appid = "UDREPORTWO";
                objectname = "WORKORDER";
                break;
            case Constants.AA://终验收工单
                appid = "UDZYSWO";
                objectname = "WORKORDER";
                break;
            case Constants.DC://调试工单
                appid = "DEBUGORDER";
                objectname = "DEBUGWORKORDER";
                break;
            case Constants.SP://排查工单
                appid = "UDPCWO";
                objectname = "WORKORDER";
                break;
            case Constants.TP://技改工单
                appid = "UDJGWO";
                objectname = "WORKORDER";
                break;
            case Constants.WS://定检工单
                appid = "UDDJWO";
                objectname = "WORKORDER";
                break;
        }
        if (search.equals("")) {
            if (!type.equals(Constants.DC)) {
                if (status.equals("全部")) {

                    return "{'appid':'" + appid + "','objectname':'" + objectname + "'," +
                            "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WORKORDERID desc','condition':{'WORKTYPE':'" + type + "'}}";
                }else {

                    return "{'appid':'" + appid + "','objectname':'" + objectname + "'," +
                            "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WORKORDERID desc','condition':{'WORKTYPE':'" + type + "','UDSTATUS':'" + status + "'}}";
                }
            } else {
                if (status.equals("全部")) {

                    return "{'appid':'" + appid + "','objectname':'" + objectname + "'," +
                            "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'DEBUGWORKORDERNUM desc'}";
                }else {

                    return "{'appid':'" + appid + "','objectname':'" + objectname + "'," +
                            "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'DEBUGWORKORDERNUM desc','condition':{'STATUS':'" + status + "'}}";
                }

            }
        } else {
            if (!type.equals(Constants.DC)) {
                if (status.equals("全部")) {

                    return "{'appid':'" + appid + "','objectname':'" + objectname + "'," +
                            "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WORKORDERID desc'" +
                            ",'condition':{'WORKTYPE':'" + type + "'},'sinorsearch':{'WONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";
                }else {

                    return "{'appid':'" + appid + "','objectname':'" + objectname + "'," +
                            "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WORKORDERID desc'" +
                            ",'condition':{'WORKTYPE':'" + type + "','UDSTATUS':'" + status + "'},'sinorsearch':{'WONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";
                }
            } else {

                if (status.equals("全部")) {

                    return "{'appid':'" + appid + "','objectname':'" + objectname + "'," +
                            "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'DEBUGWORKORDERNUM desc'" +
                            ",'sinorsearch':{'DEBUGWORKORDERNUM':'" + search + "','DESCRIPTION':'" + search + "'}}";
                }else{

                    return "{'appid':'" + appid + "','objectname':'" + objectname + "'," +
                            "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'DEBUGWORKORDERNUM desc'" +
                            ",'sinorsearch':{'DEBUGWORKORDERNUM':'" + search + "','DESCRIPTION':'" + search + "'},'condition':{'STATUS':'" + status + "'}}";
                }
            }
        }
    }

    public static String getworkorder(String type,String wonum){
        switch (type) {
            case Constants.FR://故障工单
                appid = "UDREPORTWO";
                objectname = "WORKORDER";
                break;
            case Constants.AA://终验收工单
                appid = "UDZYSWO";
                objectname = "WORKORDER";
                break;
            case Constants.SP://排查工单
                appid = "UDPCWO";
                objectname = "WORKORDER";
                break;
            case Constants.TP://技改工单
                appid = "UDJGWO";
                objectname = "WORKORDER";
                break;
            case Constants.WS://定检工单
                appid = "UDDJWO";
                objectname = "WORKORDER";
                break;
        }
        return "{'appid':'" + appid + "','objectname':'" + objectname + "'," +
                "'curpage':1,'showcount':20,'option':'read','condition':{'WONUM':'=" + wonum + "'}}";
    }

    public static String getworkorderByid(String type,String workorderid){
        switch (type) {
            case Constants.FR://故障工单
                appid = "UDREPORTWO";
                objectname = "WORKORDER";
                break;
            case Constants.AA://终验收工单
                appid = "UDZYSWO";
                objectname = "WORKORDER";
                break;
            case Constants.SP://排查工单
                appid = "UDPCWO";
                objectname = "WORKORDER";
                break;
            case Constants.TP://技改工单
                appid = "UDJGWO";
                objectname = "WORKORDER";
                break;
            case Constants.WS://定检工单
                appid = "UDDJWO";
                objectname = "WORKORDER";
                break;
        }
        return "{'appid':'" + appid + "','objectname':'" + objectname + "'," +
                "'curpage':1,'showcount':20,'option':'read','condition':{'WORKORDERID':'=" + workorderid + "'}}";
    }

    public static String getdebugworkorderByid(String debugworkorderid){
        return "{'appid':'DEBUGORDER','objectname':'DEBUGWORKORDER'," +
                "'curpage':1,'showcount':20,'option':'read','condition':{'DEBUGWORKORDERID':'=" + debugworkorderid + "'}}";
    }

    /**
     * 设置选择工单接口*
     */
    public static String getChooseWorkOrderUrl(String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.WOTRACK_APPID + "','objectname':'" + Constants.WORKORDER_APPID + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WORKTYPE':'!=OSPR'}}";
        } else {
            return "{'appid':'" + Constants.WOTRACK_APPID + "','objectname':'" + Constants.WORKORDER_APPID + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'%" + search + "%','WORKTYPE':'!=OSPR'}}";
        }
    }

    /**
     * 设置资产查询*
     */
    public static String getAssetUrl(String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.ASSET_APPID + "','objectname':'" + Constants.ASSET_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'ASSETNUM ASC'}";
        } else {
            return "{'appid':'" + Constants.ASSET_APPID + "','objectname':'" + Constants.ASSET_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'ASSETNUM ASC','sinorsearch':{'ASSETNUM':' "+ search + "','DESCRIPTION':'" + search + "'}}";
        }
    }

    /**
     * 设置采购接收查询*
     */
    public static String getPoUrl(String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.ASSET_APPID + "','objectname':'" + Constants.ASSET_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'ASSETNUM ASC'}";
        } else {
            return "{'appid':'" + Constants.ASSET_APPID + "','objectname':'" + Constants.ASSET_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'ASSETNUM ASC','sinorsearch':{'ASSETNUM':' "+ search + "','DESCRIPTION':'" + search + "'}}";
        }
    }

    /**
     * 设置部件查询*
     */
    public static String getBujianUrl(String assetnum,int curpage, int showcount) {
            return "{'appid':'" + Constants.ASSET_APPID + "','objectname':'" + Constants.ASSET_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PARENT':'" + assetnum + "'}}";
    }

    /**
     * 设置备件查询*
     */
    public static String getBeijianUrl(String assetnum,int curpage, int showcount) {
        return "{'appid':'" + Constants.ASSET_APPID + "','objectname':'" + Constants.SPAREPART_NAME + "'," +
                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSETNUM':'" + assetnum + "'}}";
    }

    /**
     * 设置工单历史查询*
     */
    public static String getWorkorderUrl(String assetnum,int curpage, int showcount) {
        return "{'appid':'" + Constants.ASSET_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSETNNUM':'" + assetnum + "'}}";
    }

    /**
     * 设置工单查询*
     */
    public static String getWorkOrderUrl(String search,String type, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.UDWOTRACK_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WONUM desc','condition':{'WORKTYPE':'" + type + "'}}";
        } else {
            return "{'appid':'" + Constants.UDWOTRACK_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WONUM desc','condition':{'WORKTYPE':'" + type + "'},'sinorsearch':{'WONUM':' "+ search + "','DESCRIPTION':'" + search + "'}}";
        }
    }

    /**
     * 设置工单任务查询*
     */
    public static String getWoactivityUrl(String wonum,String istask,int curpage, int showcount) {
//        return "{'appid':'" + Constants.WOACTIVITY_APPID + "','objectname':'" + Constants.WOACTIVITY_NAME + "'," +
//                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PARENT':'" + wonum + "','istask':'" + istask + "'}}";
        return "{'appid':'" + Constants.WOACTIVITY_APPID + "','objectname':'" + Constants.WOACTIVITY_NAME + "'," +
                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置工单计划物料查询*
     */
    public static String getWplaborLUrl(String wonum,String istask,int curpage, int showcount) {
//        return "{'appid':'" + Constants.UDWOTRACK_APPID + "','objectname':'" + Constants.WPLABOR_NAME + "'," +
//                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PARENT':'" + wonum + "','istask':'" + istask + "'}}";
        return "{'appid':'" + Constants.UDWOTRACK_APPID + "','objectname':'" + Constants.WPLABOR_NAME + "'," +
                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置工单计划物料查询*
     */
    public static String getWpmateriaLUrl(String wonum,String istask,int curpage, int showcount) {
//        return "{'appid':'" + Constants.WPMATERIAL_NAME + "','objectname':'" + Constants.WPMATERIAL_NAME + "'," +
//                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PARENT':'" + wonum + "','istask':'" + istask + "'}}";
        return "{'appid':'" + Constants.WPMATERIAL_NAME + "','objectname':'" + Constants.WPMATERIAL_NAME + "'," +
                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置工单实际员工查询*
     */
    public static String getLabtransUrl(String wonum,String istask,int curpage, int showcount) {
//        return "{'appid':'" + Constants.UDWOTRACK_APPID + "','objectname':'" + Constants.LABTRANS_NAME + "'," +
//                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PARENT':'" + wonum + "','istask':'" + istask + "'}}";
        return "{'appid':'" + Constants.UDWOTRACK_APPID + "','objectname':'" + Constants.LABTRANS_NAME + "'," +
                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置工单实际物料查询*
     */
    public static String getMatusetransUrl(String wonum,String istask,int curpage, int showcount) {
//        return "{'appid':'" + Constants.MATUSETRANS_NAME + "','objectname':'" + Constants.MATUSETRANS_NAME + "'," +
//                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PARENT':'" + wonum + "','istask':'" + istask + "'}}";
        return "{'appid':'" + Constants.MATUSETRANS_NAME + "','objectname':'" + Constants.MATUSETRANS_NAME + "'," +
                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置库存查询*
     */
    public static String getInventoryUrl(String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.INVENTORY_APPID + "','objectname':'" + Constants.INVENTORY_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'ITEMNUM desc'}";
        } else {
            return "{'appid':'" + Constants.INVENTORY_APPID + "','objectname':'" + Constants.INVENTORY_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'ITEMNUM desc','sinorsearch':{'ITEMNUM':' "+ search + "','ITEMNUM_DEC':'" + search + "'}}";
        }
    }

    /**
     * 设置库存余量查询*
     */
    public static String getInvbalancesUrl(String itemnum,String location,int curpage, int showcount) {
        return "{'appid':'" + Constants.INVENTORY_APPID + "','objectname':'" + Constants.INVBALANCES_NAME + "'," +
                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'itemnum':'" + itemnum + "','location':'" + location + "'}}";
    }

    /**
     * 设置出差总结报告*
     */
    public static String getTripReportUrl(String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.UDTRIPREPORT_APPID + "','objectname':'" + Constants.UDTRIPREPORT_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.UDTRIPREPORT_APPID + "','objectname':'" + Constants.UDTRIPREPORT_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'"+"'sinorsearch':{'SERIALNUMBER':' + value + ','PROJECT':' + value + '}}";
        }
    }

    /**
     * 设置计划任务接口*
     */
    public static String getwoactivityUrl(String wonum, int curpage, int showcount) {
        return "{'appid':'" + Constants.WOACTIVITY_APPID + "','objectname':'" + Constants.WOACTIVITY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" +
                ",'condition':{'parent':'" + wonum + "'}" +
                "}";
    }

    /**
     * 设置jobtask计划任务接口*
     */
    public static String getJobtaskUrl(String jpnum) {
        return "{'appid':'" + Constants.JOBTASK_APPID + "','objectname':'" + Constants.JOBTASK_NAME + "','option':'read'" +
                ",'condition':{'JPNUM':'" + jpnum + "'}" +
                "}";
    }

    /**
     * 设置调试工单子表接口*
     */
    public static String getuddebugworkorderlineUrl(String wonum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDDEBUGWORKORDERLINE_APPID + "','objectname':'" + Constants.UDDEBUGWORKORDERLINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" +
                ",'condition':{'DEBUGWORKORDERNUM':'" + wonum + "'}" +
                "}";
    }

    /**
     * 设置设备位置的接口
     */
    public static String getLoactionUrl(String value, String pronum, String locnum, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" + Constants.LOCATION_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'UDPRONUM':'" + pronum + "','UDLOCNUM':'" + locnum + "'}}";
        }
        return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" + Constants.LOCATION_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'LOCATION':'" + value + "','DESCRIPTION':'" + value + "'},'condition':{'UDPRONUM':'" + pronum + "','UDLOCNUM':'" + locnum + "'}}";
    }

    /**
     * 设置库房的接口
     */
    public static String getLoactionUrl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" + Constants.LOCATION_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'TYPE':'库房','TESTSITE':'1'}}";
        }
        return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" + Constants.LOCATION_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'LOCATION':'" + value + "','DESCRIPTION':'" + value + "'},'condition':{'TYPE':'库房','TESTSITE':'1'}}";
    }

    /**
     * 设置计划员工接口*
     */
    public static String getwplaborUrl(String type, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WPLABOR_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置故障工单物料接口*
     */
    public static String getWpmaterialUrl(String wonum, int curpage, int showcount) {
        return "{'appid':'" + Constants.WPMATERIAL_NAME + "','objectname':'" + Constants.WPMATERIAL_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" +
                ",'condition':{'WONUM':'" + wonum + "'}" +
                "}";
    }

    /**
     * 设置任务分配接口*
     */
    public static String getassignmentUrl(String type, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.ASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置实际员工接口
     */
    public static String getlabtransUrl(String type, String wonum, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.LABTRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'REFWO':'" + wonum + "'}}";
    }


    /**
     * 设置故障汇报接口
     */
    public static String getfailurereportUrl(String type, String wonum) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.FAILUREREPORT_NAME + "','option':'read','condition':{'WONUM':'" + wonum + "'}}";
    }


    /**
     * 设置工程台账的接口
     */
    public static String getUdprourl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDPRO_APPID + "','objectname':'" + Constants.UDPRO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'PRONUM desc'}";
        }
        return "{'appid':'" + Constants.UDPRO_APPID + "','objectname':'" + Constants.UDPRO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'PRONUM desc','sinorsearch':{'PRONUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
    }

    /**
     * 设置工程台账的试点项目接口
     */
    public static String getUdprourl2(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDPRO_APPID + "','objectname':'" + Constants.UDPRO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'TESTPRO':'Y'}}";
        }
        return "{'appid':'" + Constants.UDPRO_APPID + "','objectname':'" + Constants.UDPRO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'PRONUM':'" + value + "','DESCRIPTION':'" + value + "'},'condition':{'TESTPRO':'Y'}}";
    }

    /**
     * 设置风机型号的接口
     */
    public static String getUdfandetailsurl(String value, String pronum, String siteid, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDFANDETAILS_APPID + "','objectname':'" + Constants.UDFANDETAILS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'LOCNUM','condition':{'PRONUM':'=" + pronum + "','siteid':'=" + siteid + "'}}";
        }
        return "{'appid':'" + Constants.UDFANDETAILS_APPID + "','objectname':'" + Constants.UDFANDETAILS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'LOCNUM','condition':{'PRONUM':'" + value + "','PRONUM':'=" + pronum + "','siteid':'=" + siteid + "'}}";
    }

    /**
     * 设置风机型号的接口
     */
    public static String getUdfandetailsurl(String value, String pronum, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDFANDETAILS_APPID + "','objectname':'" + Constants.UDFANDETAILS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'LOCNUM','condition':{'PRONUM':'=" + pronum + "'}}";
        }
        return "{'appid':'" + Constants.UDFANDETAILS_APPID + "','objectname':'" + Constants.UDFANDETAILS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'LOCNUM','sinorsearch':{'LOCNUM':'" + value + "','MODELTYPE':'" + value + "'},'condition':{'PRONUM':'=" + pronum + "'}}";
    }

    /**
     * 设置风机型号的接口
     */
    public static String getUdfandetailsurl(String value, String pronum) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDFANDETAILS_APPID + "','objectname':'" + Constants.UDFANDETAILS_NAME + "','option':'read','condition':{'PRONUM':'=" + pronum + "'}}";
        }
        return "{'appid':'" + Constants.UDFANDETAILS_APPID + "','objectname':'" + Constants.UDFANDETAILS_NAME + "','option':'read','sinorsearch':{'LOCNUM':'" + value + "','MODELTYPE':'" + value + "'},'condition':{'PRONUM':'=" + pronum + "'}}";
    }

    /**
     * 设置问题联络单的接口
     */
    public static String getUdfeedbacksurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDFEEDBACK_APPID + "','objectname':'" + Constants.UDFEEDBACK_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'FEEDBACKNUM desc'}";
        }
        return "{'appid':'" + Constants.UDFEEDBACK_APPID + "','objectname':'" + Constants.UDFEEDBACK_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" + ",'orderby':'FEEDBACKNUM desc'    ,'sinorsearch':{'FEEDBACKNUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
    }

    /**
     * 根据id查询问题联络单的接口
     */
    public static String getUdfeedbacksurlByid(String feedbackid) {
        return "{'appid':'" + Constants.UDFEEDBACK_APPID + "','objectname':'" + Constants.UDFEEDBACK_NAME + "','curpage':1,'showcount':20,'option':'read','condition':{'UDFEEDBACKID':'=" + feedbackid + "'}}";
    }

    /**
     * 设置项目人员的接口
     */
    public static String getUdpersonurl(String value, String pronum, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDPERSON_APPID + "','objectname':'" + Constants.UDPERSON_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'UDPRONUM':'=" + pronum + "'}}";
        }
        return "{'appid':'" + Constants.UDPERSON_APPID + "','objectname':'" + Constants.UDPERSON_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'DISPLAYNAME':'" + value + "','UDPRONUM':'=" + pronum + "'}}";
    }

    /**
     * 设置项目车辆的接口
     */
    public static String getudvehicleurl(String value, String pronum, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDVEHICLE_APPID + "','objectname':'" + Constants.UDVEHICLE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PRONUM':'=" + pronum + "'}}";
        }
        return "{'appid':'" + Constants.UDVEHICLE_APPID + "','objectname':'" + Constants.UDVEHICLE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'LICENSENUM':'" + value + "','PRONUM':'=" + pronum + "'}}";
    }


    /**
     * 设置项目日报的接口
     */
    public static String getudprorunlogurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDPRORUNLOG_APPID + "','objectname':'" + Constants.UDPRORUNLOG_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'PRORUNLOGNUM desc'}";
        }
        return "{'appid':'" + Constants.UDPRORUNLOG_APPID + "','objectname':'" + Constants.UDPRORUNLOG_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" + ",'orderby':'PRORUNLOGNUM desc','sinorsearch':{'PRORUNLOGNUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
    }

    /**
     * 设置土建阶段日报接口*
     */
    public static String getUdprorunlogLine1Url(String prorunlognum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDPRORUNLOGLINE1_APPID + "','objectname':'" + Constants.UDPRORUNLOGLINE1_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" +
                ",'orderby':'CREATEDATE desc','condition':{'PRORUNLOGNUM':'" + prorunlognum + "'}" +
                "}";
    }

    /**
     * 设置吊装调试日报接口*
     */
    public static String getUdprorunlogLine2Url(String prorunlognum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDPRORUNLOGLINE2_APPID + "','objectname':'" + Constants.UDPRORUNLOGLINE2_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" +
                ",'orderby':'CREATEDATE desc','condition':{'PRORUNLOGNUM':'" + prorunlognum + "'}" +
                "}";
    }

    /**
     * 设置工作日报接口*
     */
    public static String getUdprorunlogLine3Url(String prorunlognum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDPRORUNLOGLINE3_APPID + "','objectname':'" + Constants.UDPRORUNLOGLINE3_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" +
                ",'orderby':'RUNLOGDATE desc','condition':{'PRORUNLOGNUM':'" + prorunlognum + "'}" +
                "}";
    }

    /**
     * 设置工装管理接口*
     */
    public static String getUdprorunlogLine4Url(String prorunlognum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDPRORUNLOGLINE4_APPID + "','objectname':'" + Constants.UDPRORUNLOGLINE4_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" +
                ",'orderby':'RUNLOGDATE desc','condition':{'PRORUNLOGNUM':'" + prorunlognum + "'}" +
                "}";
    }

    /**
     * 设置库存盘点的接口
     */
    public static String getudstockurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDSTOCK_APPID + "','objectname':'" + Constants.UDSTOCK_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'STOCKNUM DESC'}";
        }
        return "{'appid':'" + Constants.UDSTOCK_APPID + "','objectname':'" + Constants.UDSTOCK_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" + ",'orderby':'STOCKNUM DESC','sinorsearch':{'STOCKNUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
    }

    /**
     * 根据库存单id查询库存盘点信息
     */
    public static String getudstockurlByid(String udstockid) {
        return "{'appid':'" + Constants.UDSTOCK_APPID + "','objectname':'" + Constants.UDSTOCK_NAME + "','curpage':1,'showcount':20,'option':'read','condition':{'UDSTOCKID':'" + udstockid + "'}}";
    }

    /**
     * 设置库存盘点行的接口
     */
    public static String getudstocklineurl(String lgort, String stocknum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDSTOCKLINE_APPID + "','objectname':'" + Constants.UDSTOCKLINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'LGORT':'" + lgort + "','STOCKNUM':'" + stocknum + "'}}";
    }


    /**
     * 设置故障提报单的接口
     */
    public static String getudfaultreporturl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDFAULTREP_APPID + "','objectname':'" + Constants.UDFAULTREPORT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'UDFAULTREPORTNUM DESC'}";
        }
        return "{'appid':'" + Constants.UDFAULTREP_APPID + "','objectname':'" + Constants.UDFAULTREPORT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" + ",'orderby':'UDFAULTREPORTNUM DESC','sinorsearch':{'UDFAULTREPORTNUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
    }
    /**
     * 设置工作申请的接口
     */
    public static String getudworkapplyurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDFEDBKCON_APPID + "','objectname':'" + Constants.UDWORKAPPLY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WOAPPLYNUM ASC'}";
        }
        return "{'appid':'" + Constants.UDFEDBKCON_APPID + "','objectname':'" + Constants.UDWORKAPPLY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" + ",'orderby':'WOAPPLYNUM ASC','sinorsearch':{'WOAPPLYNUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
    }

    /**
     * 根据id查找故障提报单的接口
     */
    public static String getudreporturlByid(String udreportid) {
        return "{'appid':'" + Constants.UDFAULTREP_APPID + "','objectname':'" + Constants.UDFAULTREPORT_NAME + "','curpage':1,'showcount':20,'option':'read','condition':{'UDREPORTID':'" + udreportid + "'}}";
    }

    /**
     * 设置运行记录的接口
     */
    public static String getudrunlogrurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDRUNLOGR_APPID + "','objectname':'" + Constants.UDRUNLOGR_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'LOGNUM DESC'}";
        }
        return "{'appid':'" + Constants.UDRUNLOGR_APPID + "','objectname':'" + Constants.UDRUNLOGR_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" + ",'orderby':'LOGNUM DESC','sinorsearch':{'LOGNUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
    }

    /**
     * 设置运行记录子表接口*
     */
    public static String getUdrunlinerUrl(String lognum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDRUNLINER_APPID + "','objectname':'" + Constants.UDRUNLINER_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" +
                ",'condition':{'LOGNUM':'" + lognum + "'}" +
                "}";
    }


    /**
     * 设置巡检单的接口
     */
    public static String getudinspourl(String value,String status, int curpage, int showcount) {
        if (value.equals("")) {
            if (status.equals("全部")){
                return "{'appid':'" + Constants.UDINSPOAPP_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'INSPONUM DESC'}";
            }else {
                return "{'appid':'" + Constants.UDINSPOAPP_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'INSPONUM DESC','condition':{'STATUS':'=" + status + "'}}";
            }
        }else {
            if (status.equals("全部")){
                return "{'appid':'" + Constants.UDINSPOAPP_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'INSPONUM DESC'," + ",'sinorsearch':{'INSPONUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
            }else {
                return "{'appid':'" + Constants.UDINSPOAPP_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'INSPONUM DESC'," + ",'sinorsearch':{'INSPONUM':'" + value + "','DESCRIPTION':'" + value + "'},'condition':{'STATUS':'=" + status + "'}}";
            }
        }
    }

    /**
     * 设置巡检单的接口
     */
    public static String getudinspourlByid(String udinspoid) {
        return "{'appid':'" + Constants.UDINSPOAPP_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':1,'showcount':20,'option':'read','condition':{'UDINSPOID':'" + udinspoid + "'}}";
    }

    /**
     * 设置巡检项目的接口
     */
    public static String getudinsprojecturl(String insponum, int curpage, int showcount) {
//        if (value.equals("")) {
        return "{'appid':'" + Constants.UDINSPROJECT_APPID + "','objectname':'" + Constants.UDINSPROJECT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'JPTASK','condition':{'INSPONUM':'" + insponum + "'}}";
//        }
//        return "{'appid':'" + Constants.UDINSPROJECT_APPID + "','objectname':'" + Constants.UDINSPROJECT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" + ",'orderby':'JPTASK','condition':{'INSPONUM':'" + insponum + "'}" + ",'sinorsearch':{'JPTASK':'" + value + "','DESCRIPTION':'" + value + "'}}";
    }


    /**
     * 设置车辆行驶记录的接口
     */
    public static String getudcardrivelogurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDCARDRIVE_APPID + "','objectname':'" + Constants.UDCARDRIVE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'CARDRIVELOGNUM DESC'}";
        }
        return "{'appid':'" + Constants.UDCARDRIVE_APPID + "','objectname':'" + Constants.UDCARDRIVE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'CARDRIVELOGNUM DESC'," + ",'sinorsearch':{'CARDRIVELOGNUM':'" + value + "','GOREASON':'" + value + "'}}";
    }

    /**
     * 设置车辆加油记录的接口
     */
    public static String getudcarfuelchargeurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDCARFUELCHARGE_APPID + "','objectname':'" + Constants.UDCARFUELCHARGE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'CARFUELCHARGENUM DESC'}";
        }
        return "{'appid':'" + Constants.UDCARFUELCHARGE_APPID + "','objectname':'" + Constants.UDCARFUELCHARGE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'CARFUELCHARGENUM DESC'," + ",'sinorsearch':{'CARFUELCHARGENUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
    }

    /**
     * 设置车辆维修记录的接口
     */
    public static String getudcarmainlogurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDCARMAINLOG_APPID + "','objectname':'" + Constants.UDCARMAINLOG_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'MAINLOGNUM DESC'}";
        }
        return "{'appid':'" + Constants.UDCARMAINLOG_APPID + "','objectname':'" + Constants.UDCARMAINLOG_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'MAINLOGNUM DESC'," + ",'sinorsearch':{'MAINLOGNUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
    }
    /**
     * 设置选择车辆接口
     */
    public static String getudvehicleurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDVEHICLE_APPID + "','objectname':'" + Constants.UDVEHICLE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        }
        return "{'appid':'" + Constants.UDVEHICLE_APPID + "','objectname':'" + Constants.UDVEHICLE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'LICENSENUM':'" + value + "','DRIVER':'" + value + "'}}";
    }

    /**
     * 设置加油卡台账接口
     */
    public static String getGreaseCard(String value, int curpage, int showcount){

        if (value.equals("")) {
            return "{'appid':'" + Constants.GREASECARD_APPID + "','objectname':'" + Constants.GREASECARD_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        }
        return "{'appid':'" + Constants.GREASECARD_APPID + "','objectname':'" + Constants.GREASECARD_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'PRONUM':'" + value + "','LICENSENUM':'" + value + "'}}";
    }
    /**
     * 设置选择车辆接口
     */
    public static String getUddepturl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDDEPT_APPID + "','objectname':'" + Constants.UDDEPT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        }
        return "{'appid':'" + Constants.UDDEPT_APPID + "','objectname':'" + Constants.UDDEPT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'LICENSENUM':'" + value + "','DRIVER':'" + value + "'}}";
    }


    /**
     * 设置person人员表的接口
     */
    public static String getPersonUrl(String serch, int curpage, int showcount) {
        if (serch.equals("")) {
            return "{'appid':'" + Constants.PERSON_APPID + "','objectname':'" + Constants.PERSON_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'STATUS':'=活动'}}";
        }
        return "{'appid':'" + Constants.PERSON_APPID + "','objectname':'" + Constants.PERSON_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'PERSONID':'" + serch + "','DISPLAYNAME':'" + serch + "'},'condition':{'STATUS':'=活动'}}";
    }

    /**
     * 设置工作计划的接口
     */
    public static String getJobplanUrl(String serch, int curpage, int showcount, String type) {
        if (serch.equals("")) {
            return "{'appid':'" + Constants.JOBPLAN_APPID + "','objectname':'" + Constants.JOBPLAN_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'UDSTNDTYPE':'" + type + "','STATUS':'活动'}}";
        }
        return "{'appid':'" + Constants.JOBPLAN_APPID + "','objectname':'" + Constants.JOBPLAN_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'JPNUM':'" + serch + "','DESCRIPTION':'" + serch + "'},'condition':{'UDSTNDTYPE':'" + type + "','STATUS':'活动'}}";
    }

    /**
     * 设置计划的接口
     */
    public static String getUdinvestpUrl(String serch, int curpage, int showcount, String type) {
        if (serch.equals("")) {
            return "{'appid':'" + Constants.UDINVESTP_APPID + "','objectname':'" + Constants.UDINVESTP_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'APPTYPE':'" + type + "'}}";
        }
        return "{'appid':'" + Constants.UDINVESTP_APPID + "','objectname':'" + Constants.UDINVESTP_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'PLANNUM':'" + serch + "','PROJECTNUM':'" + serch + "'},'condition':{'APPTYPE':'" + type + "'}}";
    }

    /**
     * 设置故障类的接口
     */
    public static String getFailurelistUrl(String serch, int curpage, int showcount) {
        if (serch.equals("")) {
            return "{'appid':'" + Constants.FAILURELIST_APPID + "','objectname':'" + Constants.FAILURELIST_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'TYPE2':'*'}}";
        }
        return "{'appid':'" + Constants.FAILURELIST_APPID + "','objectname':'" + Constants.FAILURELIST_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'FAILURECODE':'" + serch + "','CODEDESC':'" + serch + "'},'condition':{'TYPE2':'*'}}";
    }

    /**
     * 设置问题代码的接口
     */
    public static String getFailurelist2Url(String serch, int curpage, int showcount, String parent) {
        if (serch.equals("")) {
            return "{'appid':'" + Constants.FAILURELIST_APPID + "','objectname':'" + Constants.FAILURELIST_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PARENT':'" + parent + "'}}";
        }
        return "{'appid':'" + Constants.FAILURELIST_APPID + "','objectname':'" + Constants.FAILURELIST_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'FAILURECODE':'" + serch + "','CODEDESC':'" + serch + "'},'condition':{'PARENT':'" + parent + "'}}";
    }

    /**
     * 设置问题代码的接口
     */
    public static String getFailurelist3Url(int curpage, int showcount, String failurecode) {
        return "{'appid':'" + Constants.FAILURELIST_APPID + "','objectname':'" + Constants.FAILURELIST_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'FAILURECODE':'=" + failurecode + "'}}";
    }

    /**
     * 设置物资编码的接口
     */
    public static String getItemUrl(String serch, int curpage, int showcount) {
        if (serch.equals("")) {
            return "{'appid':'" + Constants.ITEM_APPID + "','objectname':'" + Constants.ITEM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        }
        return "{'appid':'" + Constants.ITEM_APPID + "','objectname':'" + Constants.ITEM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'ITEMNUM':'" + serch + "','DESCRIPTION':'" + serch + "'}}";
    }

    /**
     * 查询所有工单的接口
     */
    public static String getWorkorderAll(int curpage, int showcount){
        return "{'appid':'','objectname':'WORKORDER','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }


    /**
     * 查询附件的接口
     */
    public static String getDoclinks(String ownertable, String ownerid){
        return "{'appid':'" + Constants.DOCLINKS_APPID + "','objectname':'" + Constants.DOCLINKS_NAME  + "','option':'read','condition':{'OWNERTABLE':'=" + ownertable  + "','OWNERID':'=" + ownerid+"'}}";
    }









    /**
     * 使用用户名密码登录
     *
     * @param cxt
     * @param username 用户名
     * @param password 密码
     * @param imei     密码
     * @param handler  返回结果处理
     */
    public static void loginWithUsername(final Context cxt, final String username, final String password, String imei,
                                         final HttpRequestHandler<String> handler) {

        String ip_adress = Constants.HTTP_API_IP + Constants.SIGN_IN_URL;

        Log.i(TAG,"ip_adress="+ip_adress);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("loginid", username);
        params.put("password", password);
        params.put("imei", imei);
        client.setTimeout(20000);
        client.post(ip_adress, params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                SafeHandler.onFailure(handler, IMErrorType.errorMessage(cxt, IMErrorType.ErrorLoginFailure));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "SstatusCode=" + statusCode + "responseString=" + responseString);
                if (statusCode == 200) {
                    LoginResults loginResults = JsonUtils.parsingAuthStr(cxt, responseString);
                    if (loginResults != null) {
                        if (loginResults.getErrcode().equals(Constants.LOGINSUCCESS) || loginResults.getErrcode().equals(Constants.CHANGEIMEI)) {
                            SafeHandler.onSuccess(handler, loginResults.getResult());
                        } else if (loginResults.getErrcode().equals(Constants.USERNAMEERROR)) {
                            SafeHandler.onFailure(handler, loginResults.getErrmsg());
                        }
                    }

                }
            }
        });


    }


    /**
     * 不分页获取信息方法*
     */
    public static void getData(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
        String url = AccountUtils.getIpAddress(cxt) + Constants.BASE_URL;
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setTimeout(20000);
        client.get(url, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Results result = JsonUtils.parsingResults1(cxt, responseString);

                SafeHandler.onSuccess(handler, result);

            }
        });
    }


    /**
     * 解析返回的结果--分页*
     */
    public static void getDataPagingInfo(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
        Log.i(TAG, "data=" + data);
        String url = Constants.HTTP_API_IP + Constants.BASE_URL;
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setTimeout(20000);
        client.get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Results result = JsonUtils.parsingResults(cxt, responseString);
                if (null == result) {

                    SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
                }else{
                    assert result != null;

                    SafeHandler.onSuccess(handler, result, result.getCurpage(), result.getShowcount());

                }
            }
        });
    }


}
