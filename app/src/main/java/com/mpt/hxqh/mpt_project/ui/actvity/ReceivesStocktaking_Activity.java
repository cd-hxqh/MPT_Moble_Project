package com.mpt.hxqh.mpt_project.ui.actvity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.mpt.hxqh.mpt_project.R;
import com.mpt.hxqh.mpt_project.adpter.UdboqlistAdapter;
import com.mpt.hxqh.mpt_project.api.HttpManager;
import com.mpt.hxqh.mpt_project.api.HttpRequestHandler;
import com.mpt.hxqh.mpt_project.api.JsonUtils;
import com.mpt.hxqh.mpt_project.bean.Results;
import com.mpt.hxqh.mpt_project.config.Constants;
import com.mpt.hxqh.mpt_project.manager.AppManager;
import com.mpt.hxqh.mpt_project.model.UDBOQLIST;
import com.mpt.hxqh.mpt_project.model.UDSTOCKTLINE;
import com.mpt.hxqh.mpt_project.model.WebResult;
import com.mpt.hxqh.mpt_project.ui.widget.SwipeRefreshLayout;
import com.mpt.hxqh.mpt_project.unit.AccountUtils;
import com.mpt.hxqh.mpt_project.unit.MessageUtils;
import com.mpt.hxqh.mpt_project.webserviceclient.AndroidClientService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 采购接收
 **/
public class ReceivesStocktaking_Activity extends BaseActivity {

    private static String TAG = "ReceivesStocktaking_Activity";

    private static final int STOCKTAKING_CODE = 1090;
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private TextView ponumText;

    /**
     * sn
     **/
    private TextView scanButton;

    /**
     * 检查按钮
     **/
    private Button checkButton;


    /**提交更新**/


    /**
     * 行表
     **/
    LinearLayoutManager layoutManager;
    /**
     * RecyclerView*
     */
    public RecyclerView recyclerView;
    /**
     * 暂无数据*
     */
    private LinearLayout nodatalayout;
    /**
     * 界面刷新*
     */
    private SwipeRefreshLayout refresh_layout = null;
    /**
     * 适配器*
     */
    private UdboqlistAdapter udboqlistadapter;

    private int page = 1;

    ArrayList<UDSTOCKTLINE> items = new ArrayList<UDSTOCKTLINE>();

    private String ponum; //ponum编号

    private LinearLayout buttonLayout;
    private Button quit;
    private Button option;
    private String[] optionList = new String[]{"Back", "Confirm"};
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    private List<UDBOQLIST> udboqlist = new ArrayList<UDBOQLIST>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_receive);
        initData();
        findViewById();
        initView();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

    }

    private void initData() {
        ponum = getIntent().getExtras().getString("ponum");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        ponumText = (TextView) findViewById(R.id.order_text_id);

        scanButton = (TextView) findViewById(R.id.serialnum_text_id);
        checkButton = (Button) findViewById(R.id.snscan_button_id);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);

        buttonLayout = (LinearLayout) findViewById(R.id.button_layout);
        quit = (Button) findViewById(R.id.quit);
        option = (Button) findViewById(R.id.option);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        backImageView.setVisibility(View.GONE);
        titleTextView.setText(R.string.receive_scanning_text);
        ponumText.setText(ponum);
        scanButton.setOnClickListener(scanButtonOnClickListener);
        checkButton.setOnClickListener(checkButtonOnClickListener);

        buttonLayout.setVisibility(View.VISIBLE);

        layoutManager = new LinearLayoutManager(ReceivesStocktaking_Activity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyItemDecoration());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        refresh_layout.setOnRefreshListener(refreshOnRefreshListener);
        refresh_layout.setOnLoadListener(refreshOnLoadListener);

        refresh_layout.setRefreshing(true);
        initAdapter(new ArrayList<UDBOQLIST>());
        getData();

        quit.setOnClickListener(quitOnClickListener);
        option.setOnClickListener(optionOnClickListener);


    }


    /**
     * 二维码扫描
     **/
    private View.OnClickListener scanButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ReceivesStocktaking_Activity.this, MipcaActivityCapture.class);
            intent.putExtra("mark", 1); //扫码标识
            startActivityForResult(intent, STOCKTAKING_CODE);
        }
    };


    /**
     * 检查sn码是否存在
     **/
    private View.OnClickListener checkButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isExist(scanButton.getText().toString());
        }
    };


    /**
     * 检查SN码是否存在
     **/
    private void isExist(String sn) {
        if (sn.equals("")) {
            MessageUtils.showMiddleToast(ReceivesStocktaking_Activity.this, "Please scan the sn code");
        } else {
            SearchSn(sn);
        }
    }

    private SwipeRefreshLayout.OnRefreshListener refreshOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            page = 1;
            getData();
        }
    };

    private SwipeRefreshLayout.OnLoadListener refreshOnLoadListener = new SwipeRefreshLayout.OnLoadListener() {
        @Override
        public void onLoad() {
            page++;
            getData();
        }
    };

    /**
     * 获取数据*
     */
    private void initAdapter(final List<UDBOQLIST> list) {
        nodatalayout.setVisibility(View.GONE);
        udboqlistadapter = new UdboqlistAdapter(ReceivesStocktaking_Activity.this, R.layout.list_udboqlist_item, list);
        recyclerView.setAdapter(udboqlistadapter);

    }


    /**
     * 获取数据*
     */
    private void getData() {
        HttpManager.getDataPagingInfo(ReceivesStocktaking_Activity.this, HttpManager.getUDBOQLISTURL(ponum, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<UDBOQLIST> item = JsonUtils.parsingUDBOQLIST(results.getResultlist());
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                if (item == null || item.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {

                    if (item != null || item.size() != 0) {
                        if (page == 1) {
                            initAdapter(new ArrayList<UDBOQLIST>());
                        }
                        if (page > totalPages) {
                            MessageUtils.showMiddleToast(ReceivesStocktaking_Activity.this, getString(R.string.have_load_out_all_the_data));
                        } else {
                            addData(item);
                        }
                    }
                    nodatalayout.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });

    }


    /**
     * 查询SN码是否存在
     */
    private void SearchSn(final String sn) {
        HttpManager.getDataPagingInfo(ReceivesStocktaking_Activity.this, HttpManager.getSERIALNUMURL(sn, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<UDBOQLIST> item = JsonUtils.parsingUDBOQLIST(results.getResultlist());
                if (item == null || item.isEmpty()) {
                    MessageUtils.showMiddleToast(ReceivesStocktaking_Activity.this, "Serialnum does not exist");
                } else {
                    update(sn);
                }
            }

            @Override
            public void onFailure(String error) {
            }
        });

    }


    /**
     * 判断SN码是否存在，如果存在，则将对应的SN码填写成已存在
     **/

    private void update(String serialnum) {
        List<UDBOQLIST> ulist = udboqlistadapter.getData();
        for (int i = 0; i < ulist.size(); i++) {
            UDBOQLIST udboq = ulist.get(i);
            String sn = udboq.getSERIALNUM();
            if (sn.equals(serialnum)) {
                udboq.setEXIST("Y");
                udboq.setUPDATEBY(AccountUtils.getpersonId(ReceivesStocktaking_Activity.this));
                udboq.setUPDATEDATE(currentDate());
                udboqlistadapter.remove(i);
                udboqlistadapter.add(i, udboq);
                udboqlistadapter.notifyDataSetChanged();
                udboqlist.add(udboq);
            }
        }
    }


    /**
     * 获取当前日期
     **/
    private String currentDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }


    /**
     * 添加数据*
     */
    private void addData(final List<UDBOQLIST> list) {
        udboqlistadapter.addData(list);
    }


    //定义RecyclerView分割线
    class MyItemDecoration extends RecyclerView.ItemDecoration {
        /**
         * @param outRect 边界
         * @param view    recyclerView ItemView
         * @param parent  recyclerView
         * @param state   recycler 内部数据管理
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //设定底部边距为1px
            outRect.set(0, 0, 0, 1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case STOCKTAKING_CODE:
                if (resultCode == RESULT_OK) {
                    String results = data.getExtras().getString("result");
                    scanButton.setText(results);
                }

                break;
        }

    }


//    /**
//     * 根据SN号查询资产表是否存在
//     **/
//    private void isExistSN(String serialnum) {
//
//        HttpManager.getDataPagingInfo(ReceivesStocktaking_Activity.this, HttpManager.getAssetUrl(serialnum), new HttpRequestHandler<Results>() {
//            @Override
//            public void onSuccess(Results results) {
//            }
//
//            @Override
//            public void onSuccess(Results results, int totalPages, int currentPage) {
//                ArrayList<ASSET> item = JsonUtils.parsingASSET(results.getResultlist());
//                if (item == null || item.isEmpty()) {
//                } else {
//                    update(item.get(0).getSERIALNUM());
//                }
//            }
//
//            @Override
//            public void onFailure(String error) {
//            }
//        });
//
//
//    }


    /**
     * 退出按钮
     **/
    private View.OnClickListener quitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final NormalDialog dialog = new NormalDialog(ReceivesStocktaking_Activity.this);
            dialog.content("Sure to exit?")//
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut)//
                    .show();
            dialog.setOnBtnClickL(
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                        }
                    },
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            AppManager.AppExit(ReceivesStocktaking_Activity.this);
                        }
                    });

        }
    };


    /**
     * 操作按钮
     **/
    private View.OnClickListener optionOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final NormalListDialog normalListDialog = new NormalListDialog(ReceivesStocktaking_Activity.this, optionList);
            normalListDialog.title("Option")
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut)//
                    .show();
            normalListDialog.setOnOperItemClickL(new OnOperItemClickL() {
                @Override
                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0://Back
                            normalListDialog.superDismiss();
                            finish();
                            break;
                        case 1://Confirm
                            normalListDialog.superDismiss();
                            if (null == udboqlist || udboqlist.size() == 0) {
                                MessageUtils.showMiddleToast(ReceivesStocktaking_Activity.this, "Please count data...");
                            } else {
                                showProgressDialog("Waiting...");
                                submitData();
                            }
                            break;

                    }
                }
            });
        }
    };


    /**
     * 提交盘点的数据
     **/
    private void submitData() {
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.UdBOQList(ReceivesStocktaking_Activity.this, ponum, JsonUtils.encapsUDBOQLISTList(udboqlist), Constants.TRANSFER_URL);

                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                closeProgressDialog();
                if (workResult == null) {
                    MessageUtils.showMiddleToast(ReceivesStocktaking_Activity.this, "fail");
                } else {
                    MessageUtils.showMiddleToast(ReceivesStocktaking_Activity.this, workResult.returnStr);
                    finish();
                }

            }
        }.execute();
    }


}
