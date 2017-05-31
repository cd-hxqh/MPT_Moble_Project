package com.mpt.hxqh.mpt_project.ui.actvity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.mpt.hxqh.mpt_project.R;
import com.mpt.hxqh.mpt_project.config.Constants;
import com.mpt.hxqh.mpt_project.model.WebResult;
import com.mpt.hxqh.mpt_project.unit.AccountUtils;
import com.mpt.hxqh.mpt_project.unit.DateTimeSelect;
import com.mpt.hxqh.mpt_project.webserviceclient.AndroidClientService;

/**
 * 资产移动新增行
 **/
public class UdassettransfLine_AddNew_Activity extends BaseActivity {

    private static final String TAG = "UdassettransfLine_AddNew_Activity";

    private ImageView backImageView; //返回按钮

    private TextView titleTextView; //标题

    private Button submit;

//    private TextView orderTextView; //Order
    private TextView assetnumTextView; //assetnum
    private TextView fromsiteTextView; //fromsite
    private TextView tositeTextView; //tosite

    private String assettrannum;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udassettransfline_addnew);
        initData();
        findViewById();
        initView();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }

    private void initData() {
        assettrannum = getIntent().getStringExtra("assettrannum");
    }

    @Override
    protected void findViewById() {

        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        submit = (Button) findViewById(R.id.sbmit_id);
//        orderTextView = (TextView) findViewById(R.id.order_text_id);
        assetnumTextView = (TextView) findViewById(R.id.assetnum_text_id);
        fromsiteTextView = (TextView) findViewById(R.id.fromsite_text_id);
        tositeTextView = (TextView) findViewById(R.id.tosite_text_id);

//        recyclerView = (RecyclerView) findViewById(R.id.dqgz10_recyclerView_id);
//        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
//        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.asset_management_text);
        submit.setText("save");
        submit.setVisibility(View.VISIBLE);

        assetnumTextView.setOnClickListener(assetnumOnClickListener);
        fromsiteTextView.setOnClickListener(new locationTextViewOnClickListener(fromsiteTextView));
        tositeTextView.setOnClickListener(new locationTextViewOnClickListener(tositeTextView));
        submit.setOnClickListener(submitOnClickListener);
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

    private View.OnClickListener submitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            submitDataInfo();
        }
    };

    /**
     * rotassetnum
     **/
    private View.OnClickListener assetnumOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(UdassettransfLine_AddNew_Activity.this, AssetChooseActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private class locationTextViewOnClickListener implements View.OnClickListener{
        private TextView textView;
        private locationTextViewOnClickListener(TextView textView){
            this.textView = textView;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UdassettransfLine_AddNew_Activity.this, LocationChooseActivity.class);
            if (textView == fromsiteTextView){
                startActivityForResult(intent, 0);
            }else {
                startActivityForResult(intent, 1);
            }
        }
    }

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(UdassettransfLine_AddNew_Activity.this);
        dialog.content("Sure to save?")//
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
                        showProgressDialog("Waiting...");
                        startAsyncTask();
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.AddMoveLine(UdassettransfLine_AddNew_Activity.this, assettrannum,
                        assetnumTextView.getText().toString(), fromsiteTextView.getText().toString(),tositeTextView.getText().toString()
                        , AccountUtils.getpersonId(UdassettransfLine_AddNew_Activity.this),Constants.TRANSFER_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult == null) {
                    Toast.makeText(UdassettransfLine_AddNew_Activity.this, "false", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UdassettransfLine_AddNew_Activity.this, workResult.returnStr, Toast.LENGTH_SHORT).show();
//                    setResult(100);
                    finish();
                }
                closeProgressDialog();
            }
        }.execute();
        //}else {
        closeProgressDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case AssetChooseActivity.ASSET_CODE:
                String asset = data.getExtras().getString("Assetnum");
                assetnumTextView.setText(asset);
                break;
            case LocationChooseActivity.LOCATION_CODE:
                String location = data.getExtras().getString("Location");
                if (requestCode == 0) {
                    fromsiteTextView.setText(location);
                }else {
                    tositeTextView.setText(location);
                }
                break;
//            case RESULT_OK:
//                String result = data.getExtras().getString("result");
//                snTextView.setText(result);
//                break;
        }
    }

}
