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
    public static String BASE_URL = "http://192.168.1.243:8080/";
   // public static String BASE_URL = "http://192.168.1.56:8080/";
    public static String PHOTO_URL = (BASE_URL.substring(0, BASE_URL.length() - 1));
    //登录地址
    public static final String LOGIN_URL = "sys/login";
    //查询用户角色列表
    public static final String ROLE_URL = "user/getUserRole";
    //修改用户信息
    public static final String MODIFY_USERINFO_URL = "user/modifyUserInfo";
    //上传用户头像
    public static final String UPLOAD_USERHEAD_URL = "user/uploadUserHead";
    //修改用户密码
    public static final String MODIFY_PASSWORD_URL = "user/modifyUserPassword";
    //获取个人详细信息
    public static final String USERINFO_URL = "user/getUserInfo";
    //获取学生信息
    public static final String STUDENTINFO_URL = "sys/getUserDetaiById";
    //批次地址
    public static final String BATCH_URL = "util/getAllBatch";
    //老师排程
    public static final String SCHEDULE_URL = "AnnouncementByTeaSchedule/get";
    //查看评分列表
    public static final String OSCEEXAM_URL = "osceExam/getExamScheduleByTeacherId";


    //---------------------------------------学生相关------------------------
    //2.1 学生查询考试排程
    public static final String GET_SCHEDULE_URL = "schedule/student/getSchedule";

    //2.1 查询考前须知
    public static final String GET_NOTES_URL = "schedule/getExamNotes";
    //2.1 搜索公告

    public static final String SEARCH_ANNOUNCEMENT_URL = "announcement/searchAnnouncement";

    //2.1 搜索消息

    public static final String SEARCH_MESSAGE_URL = "message/searchMessage";
    //3.1 学生查询考试成绩

    public static final String GET_SCORE_URL = "schedule/student/getScore";
    //3.2 学生查询考试分站成绩
    public static final String GET_STATION_SCORE_URL = "schedule/student/getStationScore";

    //---------------------------------------教师相关------------------------
    //查看录像评分列表
    public static final String OSCEEXAM_N_URL = "osceExam/getNeedScoreByTeacherId";
    //获取题干
    public static final String SCOREDATE_URL = "osce/getExamScoreDate";
    //查看录像评分列表
    public static final String SCHEDULE_STANDARD_URL = "osce/getExamScoreAttachedDate";
    //获取实时视频url
    public static final String GET_VIDEO_URL = "device/getVideoUrlsByPort";
    //获取录像回放url
    // public static final String PLAYBACK_VIDEO_URL = "osce/getVideoReport";
    public static final String PLAYBACK_VIDEO_URL_NEW = "sys/getVideoReport";
    //评分
    public static final String POSTSCORE_URL = "osce/postScore";
    //查看录像评分列表
    public static final String NEEDSCORE_URL = "osceExam/getNeedScoreByTeacherId";
    //查看录像评分列表
    // public static final String EXAMINATION_URL = BASE_URL + "osceExam/ExaminationArrangement";
    // public static final String EXAMINATION_NEW_URL = BASE_URL + "schedule/notScoreList";
    public static final String EXAMINATION_NEW_URL = "osceExam/getUserScoreList";
    //云台控制
    public static final String DIRECTION_URL = "sys/direction";
    //云台控制
    public static final String GETBATCHBYID = "util/getAllBatch";

    //3.2 搜索房间
    public static final String searchRooms_url = "room/searchRooms";

    //3.3搜索摄像头
    public static final String searchCamera_url = "room/searchCamera";

    //3.4 搜索考试列表
    public static final String searchExamList_url = "exam/searchExamList";

    //3.5 查询轮次列表
    public static final String queryRound_url = "exam/queryRound";

    //3.6 查询考试技能题题库
    public static final String queryExamSkillItemBank_url = "exam/queryExamSkillItemBank";

    //3.7 是否已经评分
    public static final String haveScore_url = "score/haveScore";

    //3.8 是否已经评分
    public static final String startScore_url = "score/startScore";

    //3.9 查看评分
    public static final String viewScore_url = "score/viewScore";
    //3.9.1 查看评分
    public static final String queryScoreByScoreId_url = "score/queryScoreByScoreId";
    //3.10 提交评分
    public static final String commitScore_url = "score/commitScore";

    //3.11 查询历史评分
    public static final String historyScore_url = "score/historyScore";

    //3.12查询修改评分申请记录
    public static final String queryScoreApplication_url = "score/queryScoreApplication";

    //4.2 查询学生成绩
    public static final String examScoreByStudent_url = "statistics/examScoreByStudent";

    //4.3 查询学生能力评级
    public static final String queryStudentAbilityGrade_url = "statistics/queryStudentAbilityGrade";
}
