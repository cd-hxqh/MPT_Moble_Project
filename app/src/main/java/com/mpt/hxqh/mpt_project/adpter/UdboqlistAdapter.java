package com.mpt.hxqh.mpt_project.adpter;

import android.animation.Animator;
import android.content.Context;

import com.mpt.hxqh.mpt_project.R;
import com.mpt.hxqh.mpt_project.model.UDBOQLIST;
import com.mpt.hxqh.mpt_project.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 * 采购接收
 */
public class UdboqlistAdapter extends BaseQuickAdapter<UDBOQLIST> {
    private static final String TAG = "UdstockineScanAdapter";
    private int position;

    public UdboqlistAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        position = index;
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, UDBOQLIST item) {

        helper.setText(R.id.item_text_id, item.getUDBOQLISTID());
        helper.setText(R.id.sn_text_id, item.getITEMNUM());
        if (item.getEXIST().equals("Y")) {
            helper.setChecked(R.id.exist_check_id, true);
        } else {
            helper.setChecked(R.id.exist_check_id, false);
        }

    }


}
