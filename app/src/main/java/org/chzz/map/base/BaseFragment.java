package org.chzz.map.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.chzz.map.App;
import org.chzz.map.R;
import org.chzz.map.adapter.NormalRecyclerViewAdapter;
import org.chzz.map.base.utils.TUtil;
import org.chzz.map.engine.Engine;

import org.chzz.dialog.CHZZAlertDialog;
import org.chzz.dialog.SweetAlertDialog;
import org.chzz.refresh.CHZZMoocStyleRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;

import butterknife.ButterKnife;


/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/7/3 上午1:28
 * 描述:
 */
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment implements CHZZRefreshLayout.RefreshLayoutDelegate, View.OnClickListener {
    protected String TAG;
    protected App mApp;
    protected View mContentView;
    protected Engine mEngine;
    protected BaseActivity mActivity;
    //布局
    protected CHZZRefreshLayout mRefreshLayout;
    protected RecyclerView mDataRv;
    protected NormalRecyclerViewAdapter mAdapter;
    protected boolean mIsNetworkEnabled = true;
    private CHZZAlertDialog mLoadingDialog;
    protected int iDisplayStart = 0;
    protected int iDisplayLength = 10;
    protected int recordsTotal;
    //0 默认 1 刷新  2下拉
    protected int mActionCode = 0;

    public T mPresenter;
    public E mModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        TAG = this.getClass().getSimpleName();
        mApp = App.getInstance();
        mActivity = (BaseActivity) activity;
        mEngine = mApp.getEngine();
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        initPresenter();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
           onUserVisible();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 避免多次从xml中加载布局文件
        if (mContentView == null) {
            initView(savedInstanceState);
            setListener();
           // onUserVisible();
            processLogic(savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    protected void setContentView(@LayoutRes int layoutResID) {
        mContentView = LayoutInflater.from(mApp).inflate(layoutResID, null);
    }

    /**
     * 初始化View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected void processLogic(Bundle savedInstanceState) {
    }

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    /**
     * 当fragment对用户可见时，会调用该方法，可在该方法中懒加载网络数据
     */
    protected void onUserVisible() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(mContentView);
    }
    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) mContentView.findViewById(id);
    }

    protected void showToast(String text) {

    }
    /**
     * 加载数据时show
     */
    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new CHZZAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
            //mLoadingDialog.setCustomImage(R.drawable.ic_launcher);
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setTitleText("数据加载中...");
        }
        mLoadingDialog.show();
    }

    /**
     * 加载完数据dismiss
     */
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }
    /**
     * 初始化RefreshLayout
     *
     * @param chzzRefreshLayout
     * @param mDataRv
     * @param mAdapter
     */
    protected void initRefresh(CHZZRefreshLayout chzzRefreshLayout, RecyclerView mDataRv, NormalRecyclerViewAdapter mAdapter, int count) {

        //列表数据
        CHZZMoocStyleRefreshViewHolder leftRefreshViewHolder = new CHZZMoocStyleRefreshViewHolder(mApp, true);
        leftRefreshViewHolder.setSpringDistanceScale(2);
        //刷新图标
        leftRefreshViewHolder.setOriginalImage(R.mipmap.ic_launcher);
        //刷新头背景色
        leftRefreshViewHolder.setUltimateColor(R.color.white);
        //设置刷新头
        chzzRefreshLayout.setRefreshViewHolder(leftRefreshViewHolder);
        //item分割线
        if (count == 1);
           // mDataRv.addItemDecoration(new Divider(mApp));
        //布局管理器
        mDataRv.setLayoutManager(new GridLayoutManager(mApp, count, GridLayoutManager.VERTICAL, false));
        mDataRv.setAdapter(mAdapter);

    }

    @Override
    public void onRefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {

    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}