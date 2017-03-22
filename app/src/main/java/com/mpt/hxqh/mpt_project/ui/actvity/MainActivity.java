package com.mpt.hxqh.mpt_project.ui.actvity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mpt.hxqh.mpt_project.R;
import com.mpt.hxqh.mpt_project.manager.AppManager;
import com.mpt.hxqh.mpt_project.unit.MessageUtils;

/**
 * 主菜单
 **/
public class MainActivity extends BaseActivity {

    private ImageView backImageView; //返回按钮

    private TextView titleTextView; //标题

    private ImageView assetImagView;//资产查询

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        assetImagView = (ImageView) findViewById(R.id.asset_image_id);
    }

    @Override
    protected void initView() {
        backImageView.setVisibility(View.GONE);
        titleTextView.setText(R.string.main_text);
        assetImagView.setOnClickListener(assetImagViewOnClickListener);
    }

    /**
     * 资产查询
     **/
    private View.OnClickListener assetImagViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Asset_Search_Activity.class);
            startActivityForResult(intent, 0);

        }
    };

    private long exitTime = 0;

    @Override
    public void onBackPressed() {


        if ((System.currentTimeMillis() - exitTime) > 2000) {
            MessageUtils.showMiddleToast(this, getResources().getString(R.string.exit_text));
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(MainActivity.this);
        }
    }
}
