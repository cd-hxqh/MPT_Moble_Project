package com.mpt.hxqh.mpt_project.api;


import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.mpt.hxqh.mpt_project.R;
import com.mpt.hxqh.mpt_project.bean.LoginResults;
import com.mpt.hxqh.mpt_project.bean.Results;
import com.mpt.hxqh.mpt_project.config.Constants;
import com.mpt.hxqh.mpt_project.unit.AccountUtils;

import org.apache.http.Header;


/**
 * Created by apple on 15/5/27.
 */
public class HttpManager {

    private static final String TAG = "HttpManager";

    /**
     * 设置位置*
     */
    public static String getLocationUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" + Constants.LOCATIONS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'STATUS':'!=INACTIVE','TYPE':'=STOREROOM'}}";
        } else {
            return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" + Constants.LOCATIONS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'STATUS':'!=INACTIVE','TYPE':'=STOREROOM'},'sinorsearch':{'LOCATION':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置人员*
     */
    public static String getPersonUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.PERSON_APPID + "','objectname':'" + Constants.PERSON_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.PERSON_APPID + "','objectname':'" + Constants.PERSON_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'PERSONID':'" + vlaue + "','DISPLAYNAME':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置INVBALANCES*
     */
    public static String getInvbalancesUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.INVBALANCES_APPID + "','objectname':'" + Constants.INVBALANCES_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.INVBALANCES_APPID + "','objectname':'" + Constants.INVBALANCES_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'BINNUM':'" + vlaue + "','LOTNUM':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置Site*
     */
    public static String getSiteUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.SITE_APPID + "','objectname':'" + Constants.SITE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.SITE_APPID + "','objectname':'" + Constants.SITE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'SITEID':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置ITEM*
     */
    public static String getItemUrl(String vlaue,String storeroom, int curpage, int showcount) {
        if (vlaue.equals("")) {
            if (storeroom==null||storeroom.equals("")) {
                return "{'appid':'" + Constants.ITEM_APPID + "','objectname':'" + Constants.ITEM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ROTATING':'=1'}}";
            }else {
                return "{'appid':'" + Constants.ITEM_APPID + "','objectname':'" + Constants.ITEM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ROTATING':'=1','STOREROOM':'" + storeroom + "'}}";
            }
        } else {
            if (storeroom==null||storeroom.equals("")) {
                return "{'appid':'" + Constants.ITEM_APPID + "','objectname':'" + Constants.ITEM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ROTATING':'=1'},'sinorsearch':{'ITEMNUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
            }else {
                return "{'appid':'" + Constants.ITEM_APPID + "','objectname':'" + Constants.ITEM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ROTATING':'=1','STOREROOM':'" + storeroom + "'}},'sinorsearch':{'ITEMNUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
            }
        }
    }

    /**
     * 设置资产查询*
     */
    public static String getAssetUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.UDMPTASSET_APPID + "','objectname':'" + Constants.ASSET_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.UDMPTASSET_APPID + "','objectname':'" + Constants.ASSET_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'STATUS':'!=INACTIVE','TYPE':'=STOREROOM'},'sinorsearch':{'LOCATION':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 资产
     **/
    public static String getAssetUrl(String location, String sn, String item, int curpage, int showcount) {
        if (!location.equals("")) {
            location = "=" + location;
        }
        if (!sn.equals("")) {
            sn = "=" + sn;
        }
        if (!item.equals("")) {
            item = "=" + item;
        }
        return "{'appid':'" + Constants.UDMPTASSET_APPID + "','objectname':'" + Constants.ASSET_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'ASSETNUM ASC','condition':{'LOCATION':'" + location + "','SERIALNUM':'" + sn + "','ITEMNUM':'" + item + "'}}";
    }


    /**
     * 设置资产转移*
     */
    public static String getINVUSEURL(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.ASOUTB_NAME + "','objectname':'" + Constants.INVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.ASOUTB_NAME + "','objectname':'" + Constants.INVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'INVUSENUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置资产转移行表*
     */
    public static String getINVUSELINEURL(String invusenum, int curpage, int showcount) {
            return "{'appid':'" + Constants.ASOUTB_NAME + "','objectname':'" + Constants.INVUSELINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INVUSENUM':'=" + invusenum + "'}}";

    }

    /**
     * 设置资产维修*
     */
    public static String getUDASSTURL(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.UDASREP_NAME + "','objectname':'" + Constants.UDASST_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.UDASREP_NAME + "','objectname':'" + Constants.UDASST_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'UDASSTNUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置资产维修行表*
     */
    public static String getUDASSTREPURL(String udasstnum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDASREP_NAME + "','objectname':'" + Constants.UDASSTREP_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'UDASSTNUM':'=" + udasstnum + "'}}";

    }

    /**
     * 设置资产报废*
     */
    public static String getUDRETIREURL(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.UDRETIRE_APPID + "','objectname':'" + Constants.UDRETIRE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.UDRETIRE_APPID + "','objectname':'" + Constants.UDRETIRE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'RETIRENUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置资产维修行表*
     */
    public static String getUDRETIRELINEURL(String retirenum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDRETIRE_APPID + "','objectname':'" + Constants.UDRETIRELINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'RETIRENUM':'=" + retirenum + "'}}";

    }
    /**
     * 设置资产移动*
     */
    public static String getUDASSETTRANSFURL(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.ASTRANSF_APPID + "','objectname':'" + Constants.UDASSETTRANSF_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.ASTRANSF_APPID + "','objectname':'" + Constants.UDASSETTRANSF_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'ASSETTRANNUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置资产移动行表*
     */
    public static String getUDTRANSFLINEURL(String assettrannum, int curpage, int showcount) {
        return "{'appid':'" + Constants.ASTRANSF_APPID + "','objectname':'" + Constants.UDTRANSFLINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSETTRANNUM':'=" + assettrannum + "'}}";

    }


    /**
     * 设置物料出库*
     */
    public static String getWORKORDERURL(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.WOFMOUT_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.WOFMOUT_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'WONUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置物料出库计划行表*
     */
    public static String getWPMATERIALURL(String wonum, int curpage, int showcount) {
        return "{'appid':'" + Constants.WOFMOUT_APPID + "','objectname':'" + Constants.WPMATERIAL_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'=" + wonum + "'}}";

    }

    /**
     * 设置物料出库计划行表*
     */
    public static String getMATUSETRANSURL(String wonum, int curpage, int showcount) {
        return "{'appid':'" + Constants.WOFMOUT_APPID + "','objectname':'" + Constants.WPMATERIAL_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'=" + wonum + "'}}";

    }


    /**
     * 设置物料退库*
     */
    public static String getMAINVUSE(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.MATREF_APPID + "','objectname':'" + Constants.MAINVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.MATREF_APPID + "','objectname':'" + Constants.MAINVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'INVUSENUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置物料出库计划行表*
     */
    public static String getMAINVUSELINEURL(String invusenum, int curpage, int showcount) {
        return "{'appid':'" + Constants.MATREF_APPID + "','objectname':'" + Constants.MAINVUSELINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INVUSENUM':'=" + invusenum + "'}}";

    }


    /**
     * 设置物料盘点*
     */
    public static String getUDSTOCKT(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.UDSTOCKT_APPID + "','objectname':'" + Constants.UDSTOCKT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.UDSTOCKT_APPID + "','objectname':'" + Constants.UDSTOCKT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'STOCKTNUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置物料盘点行*
     */
    public static String getUDSTOCKTLINEURL(String stocktnum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDSTOCKT_APPID + "','objectname':'" + Constants.UDSTOCKTLINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'STOCKTNUM':'=" + stocktnum + "'}}";

    }

    /**
     * 设置采购接收*
     */
    public static String getPOURL(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.WHIN_APPID + "','objectname':'" + Constants.PO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.WHIN_APPID + "','objectname':'" + Constants.PO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'PONUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置采购接收行*
     */
    public static String getPOLINEURL(String ponum, int curpage, int showcount) {
        return "{'appid':'" + Constants.WHIN_APPID + "','objectname':'" + Constants.POLINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PONUM':'=" + ponum + "'}}";

    }


    /**
     * 设置物料转移*
     */
    public static String getMAAINVUSE(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.MATOUTB_APPID + "','objectname':'" + Constants.MAINVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.MATOUTB_APPID + "','objectname':'" + Constants.MAINVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'INVUSENUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置物料转移行表*
     */
    public static String getMAAINVUSELINEURL(String invusenum, int curpage, int showcount) {
        return "{'appid':'" + Constants.MATOUTB_APPID + "','objectname':'" + Constants.MAINVUSELINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INVUSENUM':'=" + invusenum + "'}}";

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

        Log.i(TAG, "ip_adress=" + ip_adress);
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
                } else {
                    assert result != null;

                    SafeHandler.onSuccess(handler, result, result.getCurpage(), result.getShowcount());

                }
            }
        });
    }


}
