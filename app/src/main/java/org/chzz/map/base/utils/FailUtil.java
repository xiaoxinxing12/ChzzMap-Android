package org.chzz.map.base.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;


/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/2/26
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2016/2/26--13:56
 * 描述 ： 失败时的操作
 * 修订历史 ：
 * ============================================================
 **/
public class FailUtil {

    public static void checkResult(Context context, int code, String msg, ImageView imageView) {
        imageView.setVisibility(View.VISIBLE);
        switch (code) {
            case 200:
                ToastUtil.show(msg + ",跳到登陆页面!");
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 2000);
                break;
            case -1:
                error(msg);
                break;
            default:
                ToastUtil.show("错误码:" + code + "," + msg);
                ToastUtil.show(msg);
                break;
        }
    }

    public static void checkResult(Context context, int code, String msg) {
        switch (code) {
            case 1004:
                ToastUtil.show(msg + ",跳到登陆页面!");
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 2000);
                break;
            case -1:
                error(msg);
                break;
            default:
              //  ToastUtil.show("错误码:" + code + "," + msg);
                ToastUtil.show( msg);
                break;
        }
    }

    private static void error(String msg) {
        if (null == msg) {
            ToastUtil.show("未知错误!");
            return;
        }
        if (msg.contains("failed to connect to")) {
            ToastUtil.show("连接服务器失败!");
        } else if (msg.contains("FormatException")) {
            ToastUtil.show("数据格式转换错!" + msg);
        } else if (msg.contains("404")) {
            ToastUtil.show("服务器异常!" + msg);
        } else if (msg.contains("405")) {
            ToastUtil.show("服务器异常!" + msg);
        } else {
            ToastUtil.show(msg);
        }
    }


    public static boolean checkResult(int size, ImageView imageView) {
        if (size == 0) {
            imageView.setVisibility(View.VISIBLE);
            return true;
        } else {
            imageView.setVisibility(View.GONE);
            return false;
        }

    }

}
