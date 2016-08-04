package org.chzz.map.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.chzz.dialog.CHZZAlertDialog;
import org.chzz.dialog.SweetAlertDialog;
import org.chzz.map.App;
import org.chzz.map.R;
import org.chzz.map.adapter.NormalRecyclerViewAdapter;
import org.chzz.map.base.utils.ConstantValues;
import org.chzz.map.base.utils.SystemBarTintManager;
import org.chzz.map.base.utils.TUtil;
import org.chzz.map.base.utils.ThreadUtil;
import org.chzz.map.base.utils.ToastUtils;
import org.chzz.map.engine.Engine;
import org.chzz.refresh.CHZZMoocStyleRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;

import butterknife.ButterKnife;


/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/3/18
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2016/3/18--19:32
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity implements View.OnClickListener, CHZZRefreshLayout.RefreshLayoutDelegate {
    public T mPresenter;
    public E mModel;
    //布局
    protected CHZZRefreshLayout mRefreshLayout;
    protected RecyclerView mDataRv;
    protected NormalRecyclerViewAdapter mAdapter;
    protected boolean mIsNetworkEnabled = true;
    protected int iDisplayStart = 0;
    protected int iDisplayLength = 10;
    protected int recordsTotal;
    //分页页码
    protected int pageIndex = 0;
    //分页大小
    protected int pageSize = 10;
    //右标题
    protected Toolbar mToolbar;
    protected TextView mTitle;
    protected TextView mRight;
    //再按一次退出程序
    protected long exitTime;
    private CHZZAlertDialog mLoadingDialog;
    //一体化顶部
    protected LinearLayout mToolbarTop;
    protected App mApp;
    protected Engine mEngine;
    protected String TAG;
    protected boolean setTranslucentStatus,setShowRight;
    protected SystemBarTintManager mTintManager;
    protected Toolbar.OnMenuItemClickListener onMenuItemClick;
    protected MenuItem mSearch, mMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApp = App.getInstance();
//        mEngine = mApp.getEngine();
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);

        initView(savedInstanceState);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTop = (LinearLayout) findViewById(R.id.toolbar_top);
        mTitle = (TextView) findViewById(R.id.tv_title);
        //  mRight = (TextView) findViewById(R.id.tv_right);

        //设置可见
        setSupportActionBar(mToolbar);
        //显示返回键
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     //   getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.initPresenter();
        setListener();
        onUserVisible();
        processLogic(savedInstanceState);
        initTranslucentStatus();

    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    /**
     * 初始化view
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 当fragment对用户可见时，会调用该方法，可在该方法中懒加载网络数据
     */
    protected void onUserVisible() {
    }

    ;

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected void processLogic(Bundle savedInstanceState) {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 加载数据时show
     */
    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new CHZZAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
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

    private void initTranslucentStatus() {
        //判断手机版本是否大于19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&& null != mToolbarTop) {
            setTranslucentStatus(true);
            mToolbarTop.setVisibility(View.VISIBLE);
        }
        // 创建状态栏的管理实例
        mTintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        mTintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置，设置导航背景色
        mTintManager.setStatusBarTintResource(R.color.transparent);
        if (!setTranslucentStatus) ;
        //版本小于19隐藏一体化
    }

    /**
     * 公有的设置顶部状态栏一体化
     *
     * @param on 是否开启
     */
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            setTranslucentStatus = true;
            winParams.flags |= bits;
        } else {
            setTranslucentStatus = false;
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

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
        if (count == 1) ;
        //mDataRv.addItemDecoration(new Divider(mApp));
        //布局管理器
        mDataRv.setLayoutManager(new GridLayoutManager(mApp, count, GridLayoutManager.VERTICAL, false));
        mDataRv.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(setShowRight){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            mSearch = menu.findItem(R.id.action_search);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        if (mIsNetworkEnabled) {
            ThreadUtil.runInUIThread(new Runnable() {
                @Override
                public void run() {
                    iDisplayStart = 0;
                    onUserVisible();
                }
            }, ConstantValues.LOADING_DURATION);
        }
    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        if (iDisplayStart < recordsTotal) {
            ThreadUtil.runInUIThread(new Runnable() {
                @Override
                public void run() {
                    onUserVisible();
                }
            }, ConstantValues.LOADING_DURATION);
        } else {
            if (iDisplayStart > 10)
                ToastUtils.showInfo(this, "暂无更多数据");
            return false;
        }
        return true;
    }

}
