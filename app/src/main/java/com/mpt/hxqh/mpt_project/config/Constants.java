package com.mpt.hxqh.mpt_project.config;

/**
 * Created by think on 2015/10/19.
 */
public class Constants {

    /**
     * 基础接口*
     */
    /**
     * 默认*
     */
    public static final String HTTP_API_IP = "http://45.112.178.119:9080";



    /**
     * 登陆URL*
     */

    public static final String SIGN_IN_URL = "/maximo/mobile/system/login";


    /**
     * 通用接口查询*
     */
    public static final String BASE_URL = "/maximo/mobile/common/api";

    /**
     * 通用修改接口*
     */
//    public static final String WORK_URL = "http://192.168.100.17:7001/meaweb/services/MOBILESERVICE";
    public static final String WORK_URL = "/meaweb/services/MOBILESERVICE";

    /**
     * 领料单接口*
     */
    public static final String INVUSE_URL = "/meaweb/services/INVUSESERVICE";
    /**
     * 工作流*
     */
    public static final String WORK_FLOW_URL = "/meaweb/services/WFSERVICE";

    /**
     * ------------------数据库表名配置－－开始*
     */
    //待办事项的appid
    public static final String WFASSIGNMENT_APPID = "WFDESIGN";

    //待办事项的表名
    public static final String WFASSIGNMENT_NAME = "WFASSIGNMENT";
    /**
     * 工单管理*
     */
    //故障工单查询的appid
    public static final String UDWOTRACK_APPID = "UDWOTRACK";
    //故障工单表名
    public static final String WORKORDER_NAME = "WORKORDER";
    //工单计划任务appid
    public static final String WOACTIVITY_APPID = "WOACTIVITY";
    //工单计划任务表名
    public static final String WOACTIVITY_NAME = "WOACTIVITY";
    //计划任务appid
    public static final String JOBTASK_APPID = "JOBTASK";
    //计划任务表名
    public static final String JOBTASK_NAME = "JOBTASK";
    //调试工单子表appid
    public static final String UDDEBUGWORKORDERLINE_APPID = "UDDEBUGWORKORDERLINE";
    //调试工单子表表名
    public static final String UDDEBUGWORKORDERLINE_NAME = "UDDEBUGWORKORDERLINE";
    //工单计划员工表名
    public static final String WPLABOR_NAME = "WPLABOR";
    //工单物料表名
    public static final String WPMATERIAL_NAME = "WPMATERIAL";
    //工单任务分配表名
    public static final String ASSIGNMENT_NAME = "ASSIGNMENT";
    //工单实际员工表名
    public static final String LABTRANS_NAME = "LABTRANS";
    //工单实际物料表名
    public static final String MATUSETRANS_NAME = "MATUSETRANS";
    //故障汇报表名
    public static final String FAILUREREPORT_NAME = "FAILUREREPORT";
    //设备位置appid
    public static final String LOCATION_APPID = "LOCATION";
    //设备位置表名
    public static final String LOCATION_NAME = "LOCATIONS";
    //计划表appid
    public static final String UDINVESTP_APPID = "UDZYSPLAN";
    //计划表表名
    public static final String UDINVESTP_NAME = "UDINVESTP";
    //故障代码appid
    public static final String FAILURELIST_APPID = "FAILURELIST";
    //故障代码表名
    public static final String FAILURELIST_NAME = "FAILURELIST";
    //物资编码appid
    public static final String ITEM_APPID = "UDITEM";
    //物资编码表名
    public static final String ITEM_NAME = "ITEM";

    //库存appid
    public static final String INVENTORY_APPID = "INVENTORY";
    //库存表名
    public static final String INVENTORY_NAME = "INVENTORY";
    //库存余量表名
    public static final String INVBALANCES_NAME = "INVBALANCES";


    /**
     * 工程台账*
     */
    //工程台账appid
    public static final String UDPRO_APPID = "UDPROJECT";
    //工程台账表名**/
    public static final String UDPRO_NAME = "UDPRO";
    /**
     * 风机型号appid*
     */
    public static final String UDFANDETAILS_APPID = "UDFANDETAILS";
    /**
     * 风机型号表名*
     */
    public static final String UDFANDETAILS_NAME = "UDFANDETAILS";

    /**
     * 项目人员appid*
     */
    public static final String UDPERSON_APPID = "UDPERSON";
    /**
     * 项目人员表名*
     */
    public static final String UDPERSON_NAME = "PERSON";

    /**
     * 项目车辆appid*
     */
    public static final String UDVEHICLE_APPID = "UDVEHICLE";
    /**
     * 项目车辆表名*
     */

    public static final String UDVEHICLE_NAME = "UDVEHICLE";
    /**
     * 项目车辆appid*
     */
    public static final String GREASECARD_APPID = "GREASECARD";
    /**
     * 项目车辆表名*
     */

    public static final String GREASECARD_NAME = "GREASECARD";
    /**
     * 部门appid*
     */
    public static final String UDDEPT_APPID = "UDDEPT";
    /**
     * 部门表名*
     */
    public static final String UDDEPT_NAME = "UDDEPT";

    /**
     * 项目日报appid*
     */
    public static final String UDPRORUNLOG_APPID = "UDPRORUN";
    /**
     * 项目日报表名*
     */
    public static final String UDPRORUNLOG_NAME = "UDPRORUNLOG";
    //土建阶段日报appid
    public static final String UDPRORUNLOGLINE1_APPID = "UDPRORUNLOGLINE1";
    //土建阶段日报表名
    public static final String UDPRORUNLOGLINE1_NAME = "UDPRORUNLOGLINE1";
    //吊装调试日报appid
    public static final String UDPRORUNLOGLINE2_APPID = "UDPRORUNLOGLINE2";
    //吊装调试日报表名
    public static final String UDPRORUNLOGLINE2_NAME = "UDPRORUNLOGLINE2";
    //工作日报appid
    public static final String UDPRORUNLOGLINE3_APPID = "UDPRORUNLOGC";
    //工作日报表名
    public static final String UDPRORUNLOGLINE3_NAME = "UDPRORUNLOGC";
    //工装管理appid
    public static final String UDPRORUNLOGLINE4_APPID = "UDPRORUNLOGLINE4";
    //工装管理表名
    public static final String UDPRORUNLOGLINE4_NAME = "UDPRORUNLOGLINE4";

    /**
     * 问题联络单appid*
     */
    public static final String UDFEEDBACK_APPID = "UDFEDBKCON";
    /**
     * 问题联络单表名*
     */
    public static final String UDFEEDBACK_NAME = "UDFEEDBACK";


    /**
     * 库存盘点appid*
     */
    public static final String UDSTOCK_APPID = "UDSTOCK";
    /**
     * 库存盘点表名*
     */
    public static final String UDSTOCK_NAME = "UDSTOCK";
    /**
     * 库存盘点行appid*
     */
    public static final String UDSTOCKLINE_APPID = "UDSTOCKLINE";
    /**
     * 库存盘点行表名*
     */
    public static final String UDSTOCKLINE_NAME = "UDSTOCKLINE";

    /**故障提报单appid**/
    public static final String UDFAULTREP_APPID = "UDFAULTREP";
    /**故障提报单表名**/
    public static final String UDFAULTREPORT_NAME = "UDFAULTREPORT";

    /**工作申请appid**/
    public static final String UDFEDBKCON_APPID = "UDWOAPPLY";
    /**工作申请表名**/
    public static final String UDWORKAPPLY_NAME = "UDWORKAPPLY";




    /**巡检单appid**/
    public static final String UDINSPOAPP_APPID = "UDINSPOAPP";
    /**巡检单表名**/
    public static final String UDINSPO_NAME = "UDINSPO";
    /**巡检项目appid**/
    public static final String UDINSPROJECT_APPID = "UDINSPROJECT";
    /**巡检项目表名**/
    public static final String UDINSPROJECT_NAME = "UDINSPROJECT";

    /**运行日志appid**/
    public static final String UDRUNLOGR_APPID = "UDRUNLOG";
    /**运行日志表名**/
    public static final String UDRUNLOGR_NAME = "UDRUNLOGR";
    /**运行日志子表appid**/
    public static final String UDRUNLINER_APPID = "UDRUNLINER";
    /**运行日志表名**/
    public static final String UDRUNLINER_NAME = "UDRUNLINER";

    /**行驶记录appid**/
    public static final String UDCARDRIVE_APPID = "UDCARDRIVE";
    /**行驶记录表名**/
    public static final String UDCARDRIVE_NAME = "UDCARDRIVELOG";

    /**加油记录appid**/
    public static final String UDCARFUELCHARGE_APPID = "UDCARFUELC";
    /**加油记录表名**/
    public static final String UDCARFUELCHARGE_NAME = "UDCARFUELCHARGE";

    /**维修记录appid**/
    public static final String UDCARMAINLOG_APPID = "UDCARMAIN";
    /**维修记录表名**/
    public static final String UDCARMAINLOG_NAME = "UDCARMAINLOG";


    /**附件appid**/
    public static final String DOCLINKS_APPID = "DOCLINKS";
    /**附件表名**/
    public static final String DOCLINKS_NAME = "DOCLINKS";


    //设备appid
    public static final String ASSET_APPID = "ASSET";
    //设备表名
    public static final String ASSET_NAME = "ASSET";
    //备件表名
    public static final String SPAREPART_NAME = "SPAREPART";
    //作业计划appid
    public static final String JOBPLAN_APPID = "UDPLANSTND";
    //作业计划表名
    public static final String JOBPLAN_NAME = "JOBPLAN";
    //人员appid
    public static final String PERSON_APPID = "UDPERSON";
    //人员表名
    public static final String PERSON_NAME = "PERSON";


    /**
     *出差总结报告
     */

    public static final String UDTRIPREPORT_APPID="TRIPREPORT";

    public static final String UDTRIPREPORT_NAME="UDTRIPREPORT";

    /**
     * 位置表*
     */
    public static final String UDSTORELOC_APPID = "UDSTORELOC";
    /**
     * 位置表名*
     */
    public static final String LOCATIONS_NAME = "LOCATIONS";

    /**
     * 选择工单*
     */
    public static final String WOTRACK_APPID = "WOTRACK";
    /**
     * 选择工单表名*
     */
    public static final String WORKORDER_APPID = "WORKORDER";

    public static final String USER_INFO = "userinfo";
    public static final String NAME_KEY = "name_key";
    public static final String PASS_KEY = "pass_key";
    public static final String ISREMENBER = "isRemenber";


    /**
     * 用户登录表识--开始*
     */
    public static final String LOGINSUCCESS = "USER-S-101"; //登录成功

    public static final String CHANGEIMEI = "USER-S-104"; //登录成功,检测到用户更换手机登录

    public static final String USERNAMEERROR = "USER-E-100";//用户名密码错误

    public static final String GETDATASUCCESS = "GLOBAL-S-0";//获取数据成功

    /**
     * 工单跳转类型标识
     */
    public static final String FR = "FR";//故障工单
    public static final String AA = "AA";//终验收工单
    public static final String DC = "DC";//调试工单
    public static final String SP = "SP";//排查工单
    public static final String TP = "TP";//技改工单
    public static final String WS = "WS";//定检工单

    /**
     * 工作流名称
     */
    public static final String UDDJWO = "UDDJWO";//定检工单
    public static final String UDGZWO = "UDGZWO";//故障工单
    public static final String UDJGWO = "UDJGWO";//技改工单
    public static final String UDPCWO = "UDPCWO";//排查工单
    public static final String UDZYSWO = "UDZYSWO";//终验收工单
    public static final String UDDEBUGWOR="UDDEBUGWOR";//调试工单
    //工单状态
    public static final String STATUS1 = "新建";
    public static final String STATUS2 = "进行中";





    /**设置数据库参数-开始**/
    /**
     * 数据库路径
     */
    public static final String PATH_DB = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/Android/data/";
    /**
     * 无SD卡的数据库路径
     */
    public static final String NOT_SDCARD_PATH_DB = "/data/data/";
    /**
     * 数据库名称 *
     */
    public static final String TB_NAME = "sqlite-mingyang.db";

    /**
     * 选项跳转请求值
     */
    public static final int PERSONCODE = 100; //人员

    public static final int REGULARINSCODE = 110;//定检计划编号

    public static final int WS_JOBPLANCODE = 120;//定检作业计划

    public static final int UDPROCODE = 130;//项目编号

    public static final int UDLOCNUMCODE = 140;//风机号

    public static final int SP_JOBPLANCODE = 150;//排查作业计划

    public static final int WTCODE = 160;//风机型号

    public static final int TP_JOBPLANCODE = 170;//技改作业计划

    public static final int LOCATIONCODE = 180;//设备位置

    public static final int ZYS_UDPLANNUMCODE = 190;//终验收计划

    public static final int FAILURECODE = 200;//故障类

    public static final int PROBLEMCODE = 210;//问题原因

    public static final int ITEMCODE = 220;//物资编码

    public static final int LOCATIONCODE2 = 230;//库房

    public static final int UDVEHICLE = 240;//车辆

    public static final int WORKORDERCODE = 250;//相关故障工单

    public static final int UDDEPTCODE = 260;//部门

    public static final int WONUMCODE = 270;//业务单号

}
