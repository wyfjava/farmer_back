package com.tencent.wxcloudrun.constant;

/**
 * key前缀
 *
 * @author xiaojunling*/
public class RedisKeyConstant {

    /**
     * 用户信息分布式锁前缀
     */
    public static final String USER_LOCK = "lock:user:";
    /**
     *积分分布式锁前缀
     */
    public static final String USER_INTEGRAL_LOCK = "lock:integral:";
    /**
     * 课程收藏点赞
     * */
    public static final String COURSE_COLLECT_LOCK = "lock:course:collect:";

    /**
     * 课程投票
     * */
    public static final String COURSE_VOTE_LOCK = "lock:course:vote:";

    /**
     * 订阅状态
     * */
    public static final String SUBSCRIBE_POPUP_LOCK = "lock:subscribe:popup:";

    /**
     * 直播课程推送消息标记
     */
    public static final String LIVE_COURSE_MESSAGE ="msg:live:course:";
    /**
     * 点击埋点，并发控制锁
     */
    public static final String BURY_POINT_CLICK ="bury:point:click:";
    /**
     * 页面埋点，并发控制锁
     */
    public static final String BURY_POINT_PAGE ="bury:point:page:";
    /**
     * 曝光埋点，并发控制锁
     */
    public static final String BURY_POINT_EXPOSURE ="bury:point:exposure:";

    /**
     * 评论置顶
     * */
    public static final String COMMENT_TOP_LOCK = "lock:comment:top";

    /**
     * 试卷提交
     */
    public static final String PAPER_EXAM_LOCK = "lock:paper:exam:";

    /**
     * 培训班开班通知
     */
    public static final String TRAIN_CLASS_LOCK = "lock:train:class:";

    /**
     * 兑换码创建锁
     */
    public static final String REDEEM_CODE_LOCK = "lock:redeem:code";

    /**
     * 兑换码set
     */
    public static final String REDEEM_CODE_SET = "set:redeem:code";

    /**
     * 学习力 每日用户观看课程满10分标识
     */
    public static final String LEARN_WATCH_COURSE_FLAG ="learn:watch:course:";


    /**
     * 用户观看课程满10分送学习力提升次数
     */
    public static final String LEARN_WATCH_COURSE_LAYER_COUNT ="learn:watch:course:layer:count";

    /**
     * 用户增加学习力时分布式锁
     */
    public static final String LEARN_POWER_LOCK = "lock:learn:power:";
    /**
     * 学习力清除时读写锁
     */
    public static final String LEARN_POWER_WRITE_READ_LOCK="lock:write_read:learn:power";
    /**
     * 线下活动提交报名
     */
    public static final String ACT_OFFLINE_SIGN_LOCK = "lock:act:offline:sign";

    /**
     * 用户昵称专属码
     */
    public static final String USER_EXCLUSIVE_CODE = "lock:user:exclusive:code";

    /**
     * 万能钥匙
     */
    public static final String MASTER_KEY = "admin:masterKey:%s";

    /**
     * 直播间抽奖锁
     */
    public static final String LIVE_LOTTERY_LOCK="lock:live:lottery:room";

    /**
     * 直播间抽奖详情
     */
    public static final String LIVE_LOTTERY_INFO = "live:lottery:room:info:";

    /**
     * 直播间口令
     */
    public static final String LIVE_LOTTERY_USER= "live:lottery:room:user:";

    /**
     * 直播间pk投奖锁
     */
    public static final String LIVE_PK_LOCK="lock:live:lottery:room";

    /**
     * 直播间pk投票详情
     */
    public static final String LIVE_PK_INFO = "live:pk:room:info:";

    /**
     * 直播间投票
     */
    public static final String LIVE_PK_VOTE = "live:pk:room:vote:";

    /**
     * 投票人数
     */
    public static final String LIVE_PK_VOTE_USER = "live:pk:room:vote:user:";

    /**
     * 底部运营
     */
    public static final String QX_BOTTOM_OPERATE = "qx:bottom:operate";

    /**
     * 用户关闭底部运营位记录
     */
    public static final String QX_BOTTOM_OPERATE_CLOSE = "qx:bottom:operate:close:";

    /**
     * 首页弹层key
     */
    public static final String QX_USER_LAYER_PUT = "qx:user:layer:put:";

    /**
     * 省市区
     */
    public static final String QX_CITY_INFO="qx:city:info";
}
