package org.chzz.map.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import org.chzz.adapter.CHZZRecyclerViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.map.base.BaseEntity;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/3/21
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2016/3/21--16:45
 * 描述 ：通用的RecyclerViewAdapter
 * 修订历史 ：
 * ============================================================
 **/
public class NormalRecyclerViewAdapter<T extends BaseEntity.BaseBean> extends CHZZRecyclerViewAdapter<T> {
    private Context context;
    private IFillDataListener iFillDataListener;
    private ItemChildListener mItemChildListener;

    public NormalRecyclerViewAdapter(RecyclerView recyclerView, int itemLayoutId, IFillDataListener fillDataListener, ItemChildListener itemChildListener) {
        super(recyclerView, itemLayoutId);
        this.iFillDataListener = fillDataListener;
        this.mItemChildListener = itemChildListener;
    }

    @Override
    protected void setItemChildListener(CHZZViewHolderHelper viewHolderHelper) {
        super.setItemChildListener(viewHolderHelper);
        if (null != mItemChildListener)
            mItemChildListener.setItemChildListener(viewHolderHelper);
    }

    @Override
    protected void fillData(CHZZViewHolderHelper chzzViewHolderHelper, int i, T t) {
        iFillDataListener.setFillDataListener(chzzViewHolderHelper, i, t);
    }

    public interface IFillDataListener {
        /**
         * 给item设置数据
         *
         * @param chzzViewHolderHelper
         * @param i                    第几项
         * @param
         */
        public void setFillDataListener(CHZZViewHolderHelper chzzViewHolderHelper, int i, BaseEntity.BaseBean t);

    }

    public interface ItemChildListener {
        /**
         * 给itemOnClick
         *
         * @param chzzViewHolderHelper
         * @param
         * @param
         */
        public void setItemChildListener(CHZZViewHolderHelper chzzViewHolderHelper);

    }
}
