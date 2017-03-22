package com.mpt.hxqh.mpt_project.adpter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.mpt.hxqh.mpt_project.R;
import com.mpt.hxqh.mpt_project.model.PO;
import com.mpt.hxqh.mpt_project.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 */
public class PoAdapter extends BaseQuickAdapter<PO> {
    private int position;
    public PoAdapter(Context context, int layoutResId, List data) {
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
    protected void convert(BaseViewHolder helper, PO item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.po_ponum_text, item.getPONUM());
        helper.setText(R.id.po_description_text, item.getDESCRIPTION());
    }


}
