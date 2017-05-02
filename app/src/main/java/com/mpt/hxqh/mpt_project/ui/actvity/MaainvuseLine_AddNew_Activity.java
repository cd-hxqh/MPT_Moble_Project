package com.mpt.hxqh.mpt_project.ui.actvity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.mpt.hxqh.mpt_project.webserviceclient.AndroidClientService;

/**
 * 物料转移新增行
 **/
public class MaainvuseLine_AddNew_Activity extends BaseActivity {

    private static final String TAG = "TransferLine_AddNew_Activity";

    private ImageView backImageView; //返回按钮

    private TextView titleTextView; //标题

    private Button submit;

//    private TextView orderTextView; //Order
    private TextView usetypeTextView; //usetype
    private TextView linetypeTextView; //linetype
    private TextView itemnumTextView; //itemnum
    private TextView formbinTextView;//formbin
    private TextView tositeTextView;//tosite
    private TextView tostorelocTextView; //tostoreloc
    private TextView rotassetnumTextView; //rotassetnum
    private TextView issuetoTextView; //issueto
    private EditText quantityTextView; //quantity
    private TextView tobinTextView;//tobin
    private TextView conversionTextView;//conversion

    private String invusenum;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maainvuseline_addnew);
        initData();
        findViewById();
        initView();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }

    private void initData() {
        invusenum = getIntent().getStringExtra("invusenum");
    }

    @Override
    protected void findViewById() {

        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        submit = (Button) findViewById(R.id.sbmit_id);
//        orderTextView = (TextView) findViewById(R.id.order_text_id);
        usetypeTextView = (TextView) findViewById(R.id.usetype_text_id);
        linetypeTextView = (TextView) findViewById(R.id.linetype_text_id);
        itemnumTextView = (TextView) findViewById(R.id.itemnum_text_id);
        formbinTextView = (TextView) findViewById(R.id.frombin_text_id);
        tositeTextView = (TextView) findViewById(R.id.tositeid_text_id);
        tostorelocTextView = (TextView) findViewById(R.id.tostoreloc_text_id);
        rotassetnumTextView = (TextView) findViewById(R.id.rotassetnum_text_id);
        issuetoTextView = (TextView) findViewById(R.id.issueto_text_id);
        quantityTextView = (EditText) findViewById(R.id.quantity_text_id);
        tobinTextView = (TextView) findViewById(R.id.tobin_text_id);
        conversionTextView = (TextView) findViewById(R.id.conversion_text_id);
//        statusTextView = (TextView) findViewById(R.id.status_text_id);

//        recyclerView = (RecyclerView) findViewById(R.id.dqgz10_recyclerView_id);
//        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
//        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.asset_transferline);
        submit.setText("save");
        submit.setVisibility(View.VISIBLE);

        usetypeTextView.setText("Transfer");

//        from_storeroomTextView.setOnClickListener(locationTextViewOnClickListener);
//        inventory_ownerTextView.setOnClickListener(ownerOnClickListener);
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
     * 位置
     **/
    private View.OnClickListener locationTextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MaainvuseLine_AddNew_Activity.this, LocationChooseActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    /**
     * 仓管员
     **/
    private View.OnClickListener ownerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MaainvuseLine_AddNew_Activity.this, LocationChooseActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(MaainvuseLine_AddNew_Activity.this);
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
                WebResult reviseresult = AndroidClientService.AddMatoutbLin(MaainvuseLine_AddNew_Activity.this, invusenum,itemnumTextView.getText().toString(),
                        formbinTextView.getText().toString(), usetypeTextView.getText().toString(), linetypeTextView.getText().toString()
                        , tositeTextView.getText().toString(), rotassetnumTextView.getText().toString(), quantityTextView.getText().toString()
                        , tostorelocTextView.getText().toString(), tobinTextView.getText().toString(), issuetoTextView.getText().toString()
                        , AccountUtils.getpersonId(MaainvuseLine_AddNew_Activity.this),conversionTextView.getText().toString(), Constants.TRANSFER_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult == null) {
                    Toast.makeText(MaainvuseLine_AddNew_Activity.this, "false", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MaainvuseLine_AddNew_Activity.this, workResult.returnStr, Toast.LENGTH_SHORT).show();
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
            case LocationChooseActivity.LOCATION_CODE:
                String location = data.getExtras().getString("Location");
//                from_storeroomTextView.setText(location);
                break;
//            case RESULT_OK:
//                String result = data.getExtras().getString("result");
//                snTextView.setText(result);
//                break;
        }
    }

}
