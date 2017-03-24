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
            return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" + Constants.LOCATIONS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'STATUS':'!=INACTIVE'}}";
        } else {
            return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" + Constants.LOCATIONS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'STATUS':'!=INACTIVE'},'sinorsearch':{'LOCATION':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
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
