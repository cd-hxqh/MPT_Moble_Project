package com.mpt.hxqh.mpt_project.ui.actvity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mpt.hxqh.mpt_project.R;
import com.mpt.hxqh.mpt_project.adpter.AssetAdapter;
import com.mpt.hxqh.mpt_project.adpter.BaseQuickAdapter;
import com.mpt.hxqh.mpt_project.api.HttpManager;
import com.mpt.hxqh.mpt_project.api.HttpRequestHandler;
import com.mpt.hxqh.mpt_project.api.JsonUtils;
import com.mpt.hxqh.mpt_project.bean.Results;
import com.mpt.hxqh.mpt_project.dialog.FlippingLoadingDialog;
import com.mpt.hxqh.mpt_project.model.ASSET;
import com.mpt.hxqh.mpt_project.ui.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产查询接口
 **/
public class Asset_Search_Activity extends BaseActivity {

    public static final int MIPCA_CODE = 1003;

    private ImageView backImageView; //返回按钮

    private TextView titleTextView; //标题

    private TextView locationTextView; //位置
    private TextView snTextView; //SN

    private Button searchBtn;

    private FlippingLoadingDialog mLoadingDialog;


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
    private AssetAdapter assetAdapter;

    private int page = 1;

    ArrayList<ASSET> items = new ArrayList<ASSET>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_search);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {

        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        locationTextView = (TextView) findViewById(R.id.location_text_id);
        snTextView = (TextView) findViewById(R.id.sn_text_id);
        searchBtn = (Button) findViewById(R.id.search_btn_id);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.asset_text);
        locationTextView.setOnClickListener(locationTextViewOnClickListener);
        snTextView.setOnClickListener(snEditTextOnClickListener);
        searchBtn.setOnClickListener(searchBtnOnClickListener);

        layoutManager = new LinearLayoutManager(Asset_Search_Activity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(false);

        refresh_layout.setOnRefreshListener(onRefreshListener);
        refresh_layout.setOnLoadListener(onLoadListener);

        initAdapter(new ArrayList<ASSET>());
        items = new ArrayList<>();
    }

    /**
     * 返回按钮
     **/
    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    /**
     * 位置
     **/
    private View.OnClickListener locationTextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Asset_Search_Activity.this, LocationChooseActivity.class);
            startActivityForResult(intent, 0);

        }
    };
    /**
     * sn
     **/
    private View.OnClickListener snEditTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Asset_Search_Activity.this, MipcaActivityCapture.class);
            startActivityForResult(intent, 0);

        }
    };

    /**
     * 搜索
     **/
    private View.OnClickListener searchBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getLoadingDialog(getString(R.string.loading)).show();
            refresh_layout.setRefreshing(true);
            getData();
        }
    };

    /**
     * 下拉刷新
     **/
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            page = 1;
            getData();
        }
    };

    /**
     * 上拉加载
     **/
    private SwipeRefreshLayout.OnLoadListener onLoadListener = new SwipeRefreshLayout.OnLoadListener() {
        @Override
        public void onLoad() {
            page++;
            getData();

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case LocationChooseActivity.LOCATION_CODE:
                String location = data.getExtras().getString("Location");
                locationTextView.setText(location);
                break;
            case RESULT_OK:
                String result = data.getExtras().getString("result");
                snTextView.setText(result);
                break;
        }
    }


    private FlippingLoadingDialog getLoadingDialog(String msg) {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this, msg);
        return mLoadingDialog;
    }

    /**
     * 获取数据*
     */
    private void getData() {
        HttpManager.getDataPagingInfo(Asset_Search_Activity.this, HttpManager.getAssetUrl(locationTextView.getText().toString(), snTextView.getText().toString(), "", page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                mLoadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                mLoadingDialog.dismiss();
                ArrayList<ASSET> item = JsonUtils.parsingASSET(results.getResultlist());
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                if (item == null || item.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {

                    if (item != null || item.size() != 0) {
                        if (page == 1) {
                            items = new ArrayList<ASSET>();
                            initAdapter(items);
                        }
                        for (int i = 0; i < item.size(); i++) {
                            items.add(item.get(i));
                        }
                        addData(item);
                    }
                    nodatalayout.setVisibility(View.GONE);

                    initAdapter(items);
                }
            }

            @Override
            public void onFailure(String error) {
                mLoadingDialog.dismiss();
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });

    }


    /**
     * 获取数据*
     */
    private void initAdapter(final List<ASSET> list) {
        assetAdapter = new AssetAdapter(Asset_Search_Activity.this, R.layout.list_item_asset, list);
        recyclerView.setAdapter(assetAdapter);
        assetAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Asset_Search_Activity.this, Asset_Details_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("asset", items.get(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);

            }
        });
    }

    /**
     * 添加数据*
     */
    private void addData(final List<ASSET> list) {
        assetAdapter.addData(list);
    }

}
