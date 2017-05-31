package com.mpt.hxqh.mpt_project.ui.actvity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.mpt.hxqh.mpt_project.R;
import com.mpt.hxqh.mpt_project.config.Constants;
import com.mpt.hxqh.mpt_project.model.WebResult;
import com.mpt.hxqh.mpt_project.unit.AccountUtils;
import com.mpt.hxqh.mpt_project.webserviceclient.AndroidClientService;

/**
 * 计划物料新增行
 **/
public class Wpmaterial_AddNew_Activity extends BaseActivity {

    private static final String TAG = "Wpmaterial_AddNew_Activity";

    private ImageView backImageView; //返回按钮

    private TextView titleTextView; //标题

    private Button submit;

//    private TextView orderTextView; //Order
    private TextView itemTextView; //item
    private EditText descriptionTextView; //description
    private TextView locationTextView; //location
    private TextView issuetoTextView; //issueto
    private TextView linetypeTextView; //linetype
    private TextView restypeTextView; //restype
    private EditText quantityTextView; //quantity
    private TextView storelocsiteTextView; //storelocsite
    private EditText unitcostTextView; //unitcost
    private TextView orderunitTextView; //orderunit

    private String wonum;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    private String[] linetypeList = new String[]{"Item", "Material"};
    private String[] restypeList = new String[]{"AUTOMATIC", "HARD","SOFT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpmaterial_addnew);
        initData();
        findViewById();
        initView();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }

    private void initData() {
        wonum = getIntent().getStringExtra("wonum");
    }

    @Override
    protected void findViewById() {

        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        submit = (Button) findViewById(R.id.sbmit_id);
//        orderTextView = (TextView) findViewById(R.id.order_text_id);
        itemTextView = (TextView) findViewById(R.id.item_text_id);
        descriptionTextView = (EditText) findViewById(R.id.description_text_id);
        locationTextView = (TextView) findViewById(R.id.location_text_id);
        issuetoTextView = (TextView) findViewById(R.id.issueto_text_id);
        linetypeTextView = (TextView) findViewById(R.id.linetype_text_id);
        restypeTextView = (TextView) findViewById(R.id.restype_text_id);
        quantityTextView = (EditText) findViewById(R.id.quantity_text_id);
        storelocsiteTextView = (TextView) findViewById(R.id.storelocsite_text_id);
        unitcostTextView = (EditText) findViewById(R.id.unitcost_text_id);
        orderunitTextView = (TextView) findViewById(R.id.orderunit_text_id);

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

        itemTextView.setOnClickListener(itemOnClickListener);
        linetypeTextView.setOnClickListener(linetypeOnClickListener);
        locationTextView.setOnClickListener(new locationOnClickListener(locationTextView));
        issuetoTextView.setOnClickListener(issuetoOnClickListener);
        storelocsiteTextView.setOnClickListener(new locationOnClickListener(storelocsiteTextView));
        restypeTextView.setOnClickListener(restypeOnClickListener);
        orderunitTextView.setOnClickListener(orderunitOnClickListener);
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
     *
     **/
    private View.OnClickListener itemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Wpmaterial_AddNew_Activity.this, ItemChooseActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener linetypeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final NormalListDialog normalListDialog = new NormalListDialog(Wpmaterial_AddNew_Activity.this, linetypeList);
            normalListDialog.title("Option")
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut)//
                    .show();
            normalListDialog.setOnOperItemClickL(new OnOperItemClickL() {
                @Override
                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                    linetypeTextView.setText(linetypeList[position]);
                    switch (position){
                        case 0://Item
                            orderunitTextView.setText("");
                            orderunitTextView.setClickable(false);
                            locationTextView.setClickable(true);
                            restypeTextView.setClickable(true);
                            storelocsiteTextView.setClickable(true);
                            normalListDialog.dismiss();
                            break;
                        case 1://Material
                            locationTextView.setText("");
                            restypeTextView.setText("");
                            storelocsiteTextView.setText("");
                            orderunitTextView.setClickable(true);
                            locationTextView.setClickable(false);
                            restypeTextView.setClickable(false);
                            storelocsiteTextView.setClickable(false);
                            normalListDialog.dismiss();
                            break;
                    }
                    normalListDialog.dismiss();
                }
            });
        }
    };

    private View.OnClickListener restypeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final NormalListDialog normalListDialog = new NormalListDialog(Wpmaterial_AddNew_Activity.this, restypeList);
            normalListDialog.title("Option")
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut)//
                    .show();
            normalListDialog.setOnOperItemClickL(new OnOperItemClickL() {
                @Override
                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                    restypeTextView.setText(restypeList[position]);
                    normalListDialog.dismiss();
                }
            });
        }
    };

    private View.OnClickListener orderunitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Wpmaterial_AddNew_Activity.this, MeasureunitChooseActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener locationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Wpmaterial_AddNew_Activity.this, LocationChooseActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private class locationOnClickListener implements View.OnClickListener{
        private TextView textView;
        private locationOnClickListener(TextView textView){
            this.textView = textView;
        }
        @Override
        public void onClick(View v) {
            if (textView == locationTextView){
                Intent intent = new Intent(Wpmaterial_AddNew_Activity.this, LocationChooseActivity.class);
                startActivityForResult(intent, 0);
            }else {
                Intent intent = new Intent(Wpmaterial_AddNew_Activity.this, LocationChooseActivity.class);
                startActivityForResult(intent, 1);
            }
        }
    }

    private View.OnClickListener issuetoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Wpmaterial_AddNew_Activity.this, PersonChooseActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(Wpmaterial_AddNew_Activity.this);
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
                WebResult reviseresult = AndroidClientService.AddOutPlanLine(Wpmaterial_AddNew_Activity.this, wonum,
                        itemTextView.getText().toString(), descriptionTextView.getText().toString(),locationTextView.getText().toString()
                        ,issuetoTextView.getText().toString(),linetypeTextView.getText().toString(),
                        restypeTextView.getText().toString(),quantityTextView.getText().toString(),
                        storelocsiteTextView.getText().toString(),unitcostTextView.getText().toString(),
                        orderunitTextView.getText().toString(),AccountUtils.getpersonId(Wpmaterial_AddNew_Activity.this),Constants.TRANSFER_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult == null) {
                    Toast.makeText(Wpmaterial_AddNew_Activity.this, "false", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Wpmaterial_AddNew_Activity.this, workResult.returnStr, Toast.LENGTH_SHORT).show();
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
            case ItemChooseActivity.ITEM_CODE:
                String itemnum = data.getExtras().getString("Itemnum");
                itemTextView.setText(itemnum);
                break;
            case LocationChooseActivity.LOCATION_CODE:
                String location = data.getExtras().getString("Location");
                if (requestCode == 0) {
                    locationTextView.setText(location);
                }else {
                    storelocsiteTextView.setText(location);
                }
                break;
            case MeasureunitChooseActivity.MEASUREUNIT_CODE:
                String measureunitid = data.getExtras().getString("measureunitid");
                orderunitTextView.setText(measureunitid);
                break;
            case PersonChooseActivity.PERSON_CODE:
                String personid = data.getExtras().getString("personid");
                issuetoTextView.setText(personid);
                break;
//            case RESULT_OK:
//                String result = data.getExtras().getString("result");
//                snTextView.setText(result);
//                break;
        }
    }

}
