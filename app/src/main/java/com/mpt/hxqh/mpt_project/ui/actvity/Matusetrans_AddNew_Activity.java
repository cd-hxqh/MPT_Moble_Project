package com.mpt.hxqh.mpt_project.ui.actvity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.mpt.hxqh.mpt_project.manager.AppManager;
import com.mpt.hxqh.mpt_project.model.WebResult;
import com.mpt.hxqh.mpt_project.unit.AccountUtils;
import com.mpt.hxqh.mpt_project.webserviceclient.AndroidClientService;

/**
 * 实际物料新增行
 **/
public class Matusetrans_AddNew_Activity extends BaseActivity {

    private static final String TAG = "Matusetrans_AddNew_Activity";

    private ImageView backImageView; //返回按钮

    private TextView titleTextView; //标题

    private Button submit;

//    private TextView orderTextView; //Order
    private TextView itemnumTextView; //itemnum
    private EditText descriptionTextView; //description
    private TextView linetypeTextView; //linetype
    private TextView siteidTextView; //siteid
    private EditText quantityTextView; //quantity
    private EditText unitcostTextView; //unitcost
    private TextView locationTextView; //location
    private TextView trantypeTextView; //trantype

    private String wonum;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    private LinearLayout buttonLayout;
    private Button quit;
    private Button option;
    private String[] optionList = new String[]{"Back","Save"};

    private String[] linetypeList = new String[]{"Item", "Material"};
    private String[] trantypeList = new String[]{"ISSUE", "RETURN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matusetrans_addnew);
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
        itemnumTextView = (TextView) findViewById(R.id.item_text_id);
        descriptionTextView = (EditText) findViewById(R.id.description_text_id);
        linetypeTextView = (TextView) findViewById(R.id.linetype_text_id);
        siteidTextView = (TextView) findViewById(R.id.siteid_text_id);
        quantityTextView = (EditText) findViewById(R.id.quantity_text_id);
        unitcostTextView = (EditText) findViewById(R.id.unitcost_text_id);
        locationTextView = (TextView) findViewById(R.id.location_text_id);
        trantypeTextView = (TextView) findViewById(R.id.trantype_text_id);


        buttonLayout = (LinearLayout) findViewById(R.id.button_layout);
        quit = (Button) findViewById(R.id.quit);
        option = (Button) findViewById(R.id.option);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.add_actural_text);
        backImageView.setVisibility(View.GONE);
        buttonLayout.setVisibility(View.VISIBLE);
        submit.setText("save");
//        submit.setVisibility(View.VISIBLE);

        itemnumTextView.setOnClickListener(itemnumOnClickListener);
        linetypeTextView.setOnClickListener(linetypeOnClickListener);
        siteidTextView.setOnClickListener(siteidOnClickListener);
        locationTextView.setOnClickListener(new locationTextViewOnClickListener(locationTextView));
        trantypeTextView.setOnClickListener(trantypeOnClickListener);
        submit.setOnClickListener(submitOnClickListener);

        quit.setOnClickListener(quitOnClickListener);
        option.setOnClickListener(optionOnClickListener);
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

    private View.OnClickListener quitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final NormalDialog dialog = new NormalDialog(Matusetrans_AddNew_Activity.this);
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
                            AppManager.AppExit(Matusetrans_AddNew_Activity.this);
                        }
                    });

        }
    };

    private View.OnClickListener optionOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final NormalListDialog normalListDialog = new NormalListDialog(Matusetrans_AddNew_Activity.this, optionList);
            normalListDialog.title("Option")
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut)//
                    .show();
            normalListDialog.setOnOperItemClickL(new OnOperItemClickL() {
                @Override
                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    linetypeTextView.setText(linetypeList[position]);
                    switch (position){
                        case 0://Back
                            normalListDialog.superDismiss();
                            finish();
                            break;
                        case 1://Save
                            normalListDialog.superDismiss();
                            submitDataInfo();
                            break;
                    }
//                    normalListDialog.dismiss();
                }
            });
        }
    };

    /**
     * rotassetnum
     **/
    private View.OnClickListener itemnumOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Matusetrans_AddNew_Activity.this, ItemChooseActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener siteidOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Matusetrans_AddNew_Activity.this, SiteChooseActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener linetypeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final NormalListDialog normalListDialog = new NormalListDialog(Matusetrans_AddNew_Activity.this, linetypeList);
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
                            descriptionTextView.setText("");
                            descriptionTextView.setClickable(false);
                            itemnumTextView.setClickable(true);
                            normalListDialog.dismiss();
                            break;
                        case 1://Material
                            itemnumTextView.setText("");
                            descriptionTextView.setClickable(true);
                            itemnumTextView.setClickable(false);
                            normalListDialog.dismiss();
                            break;
                    }
                    normalListDialog.dismiss();
                }
            });
        }
    };

    private View.OnClickListener trantypeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final NormalListDialog normalListDialog = new NormalListDialog(Matusetrans_AddNew_Activity.this, trantypeList);
            normalListDialog.title("Option")
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut)//
                    .show();
            normalListDialog.setOnOperItemClickL(new OnOperItemClickL() {
                @Override
                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                    trantypeTextView.setText(trantypeList[position]);
                    normalListDialog.dismiss();
                }
            });
        }
    };

    private class locationTextViewOnClickListener implements View.OnClickListener{
        private TextView textView;
        private locationTextViewOnClickListener(TextView textView){
            this.textView = textView;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Matusetrans_AddNew_Activity.this, LocationChooseActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(Matusetrans_AddNew_Activity.this);
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
                WebResult reviseresult = AndroidClientService.AddOutActuralLine(Matusetrans_AddNew_Activity.this, wonum,
                        itemnumTextView.getText().toString(), descriptionTextView.getText().toString(),linetypeTextView.getText().toString()
                        ,siteidTextView.getText().toString(),quantityTextView.getText().toString(),unitcostTextView.getText().toString()
                        ,locationTextView.getText().toString(),trantypeTextView.getText().toString()
                        , AccountUtils.getpersonId(Matusetrans_AddNew_Activity.this),Constants.TRANSFER_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult == null) {
                    Toast.makeText(Matusetrans_AddNew_Activity.this, "false", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Matusetrans_AddNew_Activity.this, workResult.returnStr, Toast.LENGTH_SHORT).show();
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
                String description = data.getExtras().getString("description");
                itemnumTextView.setText(itemnum);
                descriptionTextView.setText(description);
                break;
            case LocationChooseActivity.LOCATION_CODE:
                String location = data.getExtras().getString("Location");
                locationTextView.setText(location);
                break;
            case SiteChooseActivity.SITE_CODE:
                String siteid = data.getExtras().getString("siteid");
                siteidTextView.setText(siteid);
                break;
//            case RESULT_OK:
//                String result = data.getExtras().getString("result");
//                snTextView.setText(result);
//                break;
        }
    }

}
