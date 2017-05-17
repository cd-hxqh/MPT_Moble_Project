package com.mpt.hxqh.mpt_project.webserviceclient;

import android.content.Context;
import android.util.Log;

import com.mpt.hxqh.mpt_project.api.JsonUtils;
import com.mpt.hxqh.mpt_project.config.Constants;
import com.mpt.hxqh.mpt_project.model.WebResult;
import com.mpt.hxqh.mpt_project.unit.AccountUtils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by think on 2015/8/11.
 */
public class AndroidClientService {
    private static final String TAG = "AndroidClientService";
    public static String NAMESPACE = "http://www.ibm.com/maximo";//http://www.ibm.com/maximo
    public static String url = "";
    public static int timeOut = 60000;

    public AndroidClientService() {
    }

    public AndroidClientService(String url) {
        this.url = url;
    }

    public void setTimeOut(int seconds) {
        this.timeOut = seconds * 1000;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 开始工作流
     * context 上下文
     * processname 过程名
     * keyValue 对应的
     *
     * @return
     */
    public static WebResult startwf(Context context, String processname, String mbo, String keyValue, String key, String loginid) {

        String url = AccountUtils.getIpAddress(context) + Constants.WORK_FLOW_URL;
        Log.e("发送工作流",url);
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "wfservicestartWF2");
        soapReq.addProperty("processname", processname);//流程名称
        soapReq.addProperty("mbo", mbo);//如工单WORKORDER
        soapReq.addProperty("keyValue", keyValue);//对应的表ID的值，如工单需要传送WONUM的值，采购申请prnum的值
        soapReq.addProperty("key", key);//对应的表ID，如工单：wonum，采购申请，prnum
        soapReq.addProperty("loginid",loginid);//用户id
        Log.e("发送工作流",soapReq.toString());
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
            Log.e("发送工作流",soapEnvelope.toString());
        } catch (IOException | XmlPullParserException e) {
            return null;
        }
        String obj = null;
        WebResult result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
//            result = JsonUtils.parsingStartWF(obj, key);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return result;
    }

    /**
     * 审批工作流
     *
     * @return
     */
    public static WebResult approve(Context context, String processname, String mbo, String keyValue, String key, String zx, String desc,String loginid) {
        String url = AccountUtils.getIpAddress(context) + Constants.WORK_FLOW_URL;
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "wfservicewfGoOn");
        soapReq.addProperty("processname", processname);//流程名称
        soapReq.addProperty("mboName", mbo);//表名
        soapReq.addProperty("keyValue", keyValue);//对应的表ID的值，如工单需要传送WONUM的值
        soapReq.addProperty("key", key);//如工单：wonum
        soapReq.addProperty("zx", zx);//审批的结果，1为审批通过，0为审批不通过
        if (!desc.equals("")) {
            soapReq.addProperty("desc", desc);//审批意见
        }
        soapReq.addProperty("loginid",loginid);//用户id
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            return null;
        } catch (XmlPullParserException e) {
            return null;
        }
        String obj = null;
        WebResult result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
//            result = JsonUtils.parsingGoOn(obj, key);
        } catch (SoapFault soapFault) {
            Log.i(TAG, "ssssss");
            return null;
        }
        return result;
    }

    /**
     * 资产转移新增方法
     * @return
     */
    public static WebResult AddAssetTrs(Context context, String description, String fromstoreloc, String invowner, String createby, String url) {

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mptmobileserviceAddAssetTrs");
        soapReq.addProperty("DESCRIPTION", description);//描述
        soapReq.addProperty("FROMSTORELOC", fromstoreloc);//库存
        soapReq.addProperty("INVOWNER", invowner);//仓管员
        soapReq.addProperty("CREATEBY", createby);//创建人
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
//            e.printStackTrace();
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();

            Log.i(TAG, "obj=" + obj);
            webResult = JsonUtils.parsingWebResult(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 资产转移行新增方法
     * @return
     */
    public static WebResult AddAssetTrsLin(Context context, String invusenum, String usetype, String linetype, String itemnum
            , String tostoreloc, String rotassetnum, String issueto, String quantity, String url) {

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mptmobileserviceAddAssetTrsLin");
        soapReq.addProperty("INVUSENUM", invusenum);//
        soapReq.addProperty("USETYPE", usetype);//
        soapReq.addProperty("LINETYPE", linetype);//
        soapReq.addProperty("ITEMNUM", itemnum);//
        soapReq.addProperty("TOSTORELOC", tostoreloc);//
        soapReq.addProperty("ROTASSETNUM", rotassetnum);//
        soapReq.addProperty("ISSUETO", issueto);//
        soapReq.addProperty("QUANTITY", quantity);//
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
//            e.printStackTrace();
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();

            Log.i(TAG, "obj=" + obj);
            webResult = JsonUtils.parsingWebResult(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 物料转移新增方法
     * @return
     */
    public static WebResult AddMatoutb(Context context, String description, String fromstoreloc, String invowner, String usetype, String url) {

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mptmobileserviceAddMatoutb");
        soapReq.addProperty("DESCRIPTION", description);//描述
        soapReq.addProperty("FROMSTORELOC", fromstoreloc);//库存
        soapReq.addProperty("INVOWNER", invowner);//仓管员
        soapReq.addProperty("USETYPE", usetype);//交易类型
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
//            e.printStackTrace();
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();

            Log.i(TAG, "obj=" + obj);
            webResult = JsonUtils.parsingWebResult(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 物料转移行新增行方法
     * @return
     */
    public static WebResult AddMatoutbLin(Context context, String invusenum, String itemnum, String frombin, String usetype
            , String linetype, String tositeid, String rotassetnum, String quantity, String tostoreloc, String tobin
            , String issueto, String enterby, String conversion, String url) {

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mptmobileserviceAddMatoutbLin");
        soapReq.addProperty("INVUSENUM", invusenum);//
        soapReq.addProperty("ITEMNUM", itemnum);//
        soapReq.addProperty("FROMBIN", frombin);//
        soapReq.addProperty("USETYPE", usetype);//
        soapReq.addProperty("LINETYPE", linetype);//
        soapReq.addProperty("TOSITEID", tositeid);//
        soapReq.addProperty("ROTASSETNUM", rotassetnum);//
        soapReq.addProperty("QUANTITY", quantity);//
        soapReq.addProperty("TOSTORELOC", tostoreloc);//
        soapReq.addProperty("TOBIN", tobin);//
        soapReq.addProperty("ISSUETO", issueto);//
        soapReq.addProperty("ENTERBY", enterby);//
        soapReq.addProperty("CONVERSION", conversion);//
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
//            e.printStackTrace();
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();

            Log.i(TAG, "obj=" + obj);
            webResult = JsonUtils.parsingWebResult(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 物料退库新增方法
     * @return
     */
    public static WebResult AddMatRf(Context context, String description, String fromstoreloc, String invowner, String createby, String url) {

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mptmobileserviceAddMatoutb");
        soapReq.addProperty("DESCRIPTION", description);//描述
        soapReq.addProperty("FROMSTORELOC", fromstoreloc);//库存
        soapReq.addProperty("INVOWNER", invowner);//仓管员
        soapReq.addProperty("CREATEBY", createby);//
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
//            e.printStackTrace();
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();

            Log.i(TAG, "obj=" + obj);
            webResult = JsonUtils.parsingWebResult(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 物料退库行新增行方法
     * @return
     */
    public static WebResult AddMatRfLin(Context context, String invusenum, String itemnum, String rotassetnum, String enterby
            , String quantity, String linetype, String newphyscnt, String remark,String url) {

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mptmobileserviceAddMatRfLin");
        soapReq.addProperty("INVUSENUM", invusenum);//
        soapReq.addProperty("ITEMNUM", itemnum);//
        soapReq.addProperty("ROTASSETNUM", rotassetnum);//
        soapReq.addProperty("ENTERBY", enterby);//
        soapReq.addProperty("QUANTITY", quantity);//
        soapReq.addProperty("LINETYPE", linetype);//
        soapReq.addProperty("NEWPHYSCNT", newphyscnt);//
        soapReq.addProperty("REMARK", remark);//
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
//            e.printStackTrace();
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();

            Log.i(TAG, "obj=" + obj);
            webResult = JsonUtils.parsingWebResult(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 物料盘点新增方法
     * @return
     */
    public static WebResult AddMatSto(Context context, String description, String location, String vendor, String createby,String visitd, String url) {

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mptmobileserviceAddMatSto");
        soapReq.addProperty("DESCRIPTION", description);//描述
        soapReq.addProperty("LOCATION", location);//位置
        soapReq.addProperty("VENDOR", vendor);//供应商
        soapReq.addProperty("CREATEBY", createby);//创建人
        soapReq.addProperty("VISITD", visitd);//更新时间
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
//            e.printStackTrace();
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();

            Log.i(TAG, "obj=" + obj);
            webResult = JsonUtils.parsingWebResult(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 物料退库行新增行方法
     * @return
     */
    public static WebResult AddMatStoLine(Context context, String stocktnum, String assetnum, String checkserial, String remark
            , String ischeck,String url) {

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mptmobileserviceAddMatRfLin");
        soapReq.addProperty("STOCKTNUM", stocktnum);//
        soapReq.addProperty("ASSETNUM", assetnum);//
        soapReq.addProperty("CHECKSERIAL", checkserial);//
        soapReq.addProperty("REMARK", remark);//
        soapReq.addProperty("ISCHECK", ischeck);//
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
//            e.printStackTrace();
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();

            Log.i(TAG, "obj=" + obj);
            webResult = JsonUtils.parsingWebResult(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 采购接收新增方法
     * @return
     */
    public static WebResult AddPo(Context context, String description,String userid, String url) {

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mptmobileserviceAddPo");
        soapReq.addProperty("DESCRIPTION", description);//描述
        soapReq.addProperty("USERID", userid);//位置
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
//            e.printStackTrace();
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();

            Log.i(TAG, "obj=" + obj);
            webResult = JsonUtils.parsingWebResult(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 采购接收新增行方法
     * @return
     */
    public static WebResult AddPoLine(Context context, String userid, String ponum, String itemnum, String conversion
            , String orderqty,String url) {

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mptmobileserviceAddPoLine");
        soapReq.addProperty("USERID", userid);//
        soapReq.addProperty("PONUM", ponum);//
        soapReq.addProperty("ITEMNUM", itemnum);//
        soapReq.addProperty("CONVERSION", conversion);//
        soapReq.addProperty("ORDERQTY", orderqty);//
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
//            e.printStackTrace();
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();

            Log.i(TAG, "obj=" + obj);
            webResult = JsonUtils.parsingWebResult(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 通用修改
     */
    public static WebResult UpdateWO(Context context, String json, String mboObjectName, String mboKey, String mboKeyValue, String url) {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mobileserviceUpdateMbo");
        soapReq.addProperty("json", json);//参数
        soapReq.addProperty("mboObjectName", mboObjectName);//表名
        soapReq.addProperty("mboKey", mboKey);//表主键 如：WONUM
        soapReq.addProperty("mboKeyValue", mboKeyValue);//表主键值
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context)+ url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();
//            webResult = JsonUtils.parsingInsertWO(obj, mboKey);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 通过webservice实现图片上传
     *
     * @param imageBuffer
     */
    /**
     * 通用修改
     */
    public static String connectWebService(Context context, String filename, String image, String ownertable, String ownerid, String url) {

        Log.i(TAG, "filename=" + filename + ",ownertable=" + ownertable + ",ownerid=" + ownerid);
        Log.i(TAG, "url=" + url);
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mobileserviceuploadImage");
        soapReq.addProperty("filename", filename);//文件名
        soapReq.addProperty("image", image);//图片Json
        soapReq.addProperty("ownertable", ownertable);//表名
        soapReq.addProperty("ownerid", ownerid);//表主键值
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
            return null;
        }
        String obj = null;
        String webResult = null;
        try {
            webResult = soapEnvelope.getResponse().toString();
            Log.i(TAG, "webResult=" + webResult);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 通用修改
     */
    public static String permissionWebService(Context context,String name) {

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mobileservicegetLocaPermission");
        soapReq.addProperty("user", AccountUtils.getuserName(context));//新增信息json
        //soapReq.addProperty("user", "A01934");//新增信息json
        soapEnvelope.setOutputSoapObject(soapReq);

        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context)+"/meaweb/services/MOBILESERVICE", timeOut);

        try {

            httpTransport.call("urn:action", soapEnvelope);

        } catch (IOException | XmlPullParserException e) {

            return null;
        }

        String obj = null;

        try {

            obj = soapEnvelope.getResponse().toString();

            Log.i("库存查询", "obj=" + obj);

        } catch (SoapFault soapFault) {

            soapFault.printStackTrace();

        }
        return obj;
    }

}