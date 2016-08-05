package org.chzz.map.base.utils;

/**
 * ============================================================
 * 版权 ： 版权所有 (c)   2015/7/2
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2015/7/2--16:39
 * 描述 ：
 * 修订历史 ：自定义常量集
 * <p>
 * ============================================================
 */
public class ConstantValues {
    public static final int LOADING_DURATION = 1000;
    public static final int LOADING_MORE = 500;
    public static final int HTTP_OK = 200;
    public static final String FAILURE = "";
    public static final String FAILUREMSG = "连接服务器失败,请检查网络连接.";
    public static final String NODATA = "没有更多数据";
    public static final String COMEFROM = "Android";
    public static String[] Title = {"排程", "日常", "设置"};
    //手机屏的宽度
    public static int SCREEN_WIDTH;
    //手机屏高度
    public static int SCREEN_HEIGHT;
    //用户是否登录
    public static boolean IS_LOGIN;

    // 按下状态
    public static final int POINT_STATE_SELECTED = 1;
    // */ 手势密码点的状态
    public static final int POINT_STATE_NORMAL = 0; // 正常状态

    // 错误状态
    public static final int POINT_STATE_WRONG = 2;

    //---------------------------------------通用------------------------
    // 服务器的base地址
    // public static final String BASE_URL = "http://192.168.1.90:8080/";
    //public static final String BASE_URL = "http://113.106.162.250:8011/";
    public static String BASE_URL = "http://restapi.amap.com/";
    // public static String BASE_URL = "http://192.168.1.56:8080/";
    public static String PHOTO_URL = (BASE_URL.substring(0, BASE_URL.length() - 1));

    public final static String a = "v3/assistant/coordinate/convert?parameters";
}
