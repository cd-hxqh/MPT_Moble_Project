package com.mpt.hxqh.mpt_project.adpter;

import android.animation.Animator;
import android.content.Context;
import android.util.Log;

import com.mpt.hxqh.mpt_project.R;
import com.mpt.hxqh.mpt_project.model.UDSTOCKTLINE;
import com.mpt.hxqh.mpt_project.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 * 物料盘点行适配器
 */
public class UdstockineScanAdapter extends BaseQuickAdapter<UDSTOCKTLINE> {
    private static final String TAG = "UdstockineScanAdapter";
    private int position;

    public UdstockineScanAdapter(Context context, int layoutResId, List data) {
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
    protected void convert(BaseViewHolder helper, UDSTOCKTLINE item) {
        Log.i(TAG, "item=" + item.getUDSTOCKTLINEID());

        if (item.getISSCAN() == 1) {
            helper.setBackgroundRes(R.id.card_container, R.color.blue);
        } else {
            helper.setBackgroundRes(R.id.card_container, R.color.white);
        }
        helper.setText(R.id.item_text_id, item.getASSETNUM());
        helper.setText(R.id.sn_text_id, item.getSERIALNUM());
        helper.setText(R.id.new_sn_text_id, item.getCHECKSERIAL());

    }


}
