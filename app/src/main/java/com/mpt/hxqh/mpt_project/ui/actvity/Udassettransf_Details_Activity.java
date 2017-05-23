package com.mpt.hxqh.mpt_project.ui.actvity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mpt.hxqh.mpt_project.R;
import com.mpt.hxqh.mpt_project.adpter.BaseQuickAdapter;
import com.mpt.hxqh.mpt_project.adpter.UdtransflineAdapter;
import com.mpt.hxqh.mpt_project.api.HttpManager;
import com.mpt.hxqh.mpt_project.api.HttpRequestHandler;
import com.mpt.hxqh.mpt_project.api.JsonUtils;
import com.mpt.hxqh.mpt_project.bean.Results;
import com.mpt.hxqh.mpt_project.model.UDASSETTRANSF;
import com.mpt.hxqh.mpt_project.model.UDTRANSFLINE;
import com.mpt.hxqh.mpt_project.ui.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 移动详情
 **/
public class Udassettransf_Details_Activity extends BaseActivity {

    private static final String TAG = "Udassettransf_Details_Activity";

    private ImageView backImageView; //返回按钮

    private TextView titleTextView; //标题

    private TextView orderTextView; //order
    private TextView descriptionTextView; //description
    private TextView depTextView; //From Department
    private TextView fromlocTextView;//
    private TextView tositeTextView;//
    private TextView statusTextView; //status

    private UDASSETTRANSF udassettransf;

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
    private UdtransflineAdapter udtransflineAdapter;

    private int page = 1;

    ArrayList<UDTRANSFLINE> items = new ArrayList<UDTRANSFLINE>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udassettransf_details);
        initData();
        findViewById();
        initView();
    }

    private void initData() {
        udassettransf = (UDASSETTRANSF) getIntent().getSerializableExtra("udassettransf");
    }

    @Override
    protected void findViewById() {

        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        orderTextView = (TextView) findViewById(R.id.order_text_id);
        descriptionTextView = (TextView) findViewById(R.id.description_text_id);
        depTextView = (TextView) findViewById(R.id.from_department_text_id);
        fromlocTextView = (TextView) findViewById(R.id.fromloc_text_id);
        tositeTextView = (TextView) findViewById(R.id.tosite_text_id);
        statusTextView = (TextView) findViewById(R.id.status_text_id);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.asset_management_text);

        if (udassettransf != null) {
            orderTextView.setText(udassettransf.getASSETTRANNUM());
            descriptionTextView.setText(udassettransf.getDESCRIPTION());
            depTextView.setText(udassettransf.getFROMDEPT());
            fromlocTextView.setText(udassettransf.getFROMLOC());
            tositeTextView.setText(udassettransf.getTOSITE());
            statusTextView.setText(udassettransf.getSTATUS());
        }


        layoutManager = new LinearLayoutManager(Udassettransf_Details_Activity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        refresh_layout.setOnRefreshListener(refreshOnRefreshListener);
        refresh_layout.setOnLoadListener(refreshOnLoadListener);

        refresh_layout.setRefreshing(true);
        initAdapter(new ArrayList<UDTRANSFLINE>());
        getData();

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
    private void initAdapter(final List<UDTRANSFLINE> list) {
        nodatalayout.setVisibility(View.GONE);
        udtransflineAdapter = new UdtransflineAdapter(Udassettransf_Details_Activity.this, R.layout.list_udasstrep_item, list);
        recyclerView.setAdapter(udtransflineAdapter);
        udtransflineAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }


    /**
     * 获取数据*
     */
    private void getData() {
        HttpManager.getDataPagingInfo(Udassettransf_Details_Activity.this, HttpManager.getUDTRANSFLINEURL(udassettransf.getASSETTRANNUM(), page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<UDTRANSFLINE> item = JsonUtils.parsingUDTRANSFLINE(results.getResultlist());
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                if (item == null || item.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {

                    if (item != null || item.size() != 0) {
                        if (page == 1) {
                            items = new ArrayList<UDTRANSFLINE>();
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
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });

    }


    /**
     * 添加数据*
     */
    private void addData(final List<UDTRANSFLINE> list) {
        udtransflineAdapter.addData(list);
    }

}
