package com.tencent.wxcloudrun.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 常量
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;

    /**
     * 数字常量0
     */
    public static final String STR_ZERO = "0";

    /**
     * 数字常量1
     */
    public static final String STR_ONE = "1";
    /**
     * 数字常量2
     */
    public static final String STR_TWO = "2";
    /**
     * 数字常量3
     */
    public static final String STR_THIRD = "3";
    /**
     * 数字常量4
     */
    public static final String STR_FOUR = "4";

    public static Integer pageNum = 1;
    public static Integer pageSize = 10;
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    public static final String SUCCESS_MSG = "操作成功";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     * 升序
     */
    public static final String ASC = "asc";
    /**
     * 产品编号前缀
     */
    public static final String IMPD = "IMPD";
    /**
     * 课程编号前缀
     */
    public static final String IMCR = "IMCR";
    /**
     * 商学院课程编号前缀
     */
    public static final String IMCB = "IMCB";
    /**
     * 申请编号前缀
     */
    public static final String IMWF = "IMWF";
    /**
     * 产品编号初始值
     */
    public static final String F_ONE = "0001";
    /**
     * 课程编号初始值
     */
    public static final String F_E_ONE = "00000001";
    /**
     * 审核默起始值
     */
    public static final String F_N_ONE = "000000001";
    /**
     * 后台admin用户
     */
    public static final String AUDIENCE_ADMIN = "admin";
    /**
     * 前台API用户
     */
    public static final String AUDIENCE_API = "api";
    /**
     * 直播登陆
     */
    public static final String AUDIENCE_RTC = "rtc";

    /**
     * 直播登陆-主持人
     */
    public static final String AUDIENCE_RTC1 = "rtc-1";

    /**
     * 直播登陆-讲师
     */
    public static final String AUDIENCE_RTC2 = "rtc-2";

    /**
     * 前台API用户ticket
     */
    public static final String AUDIENCE_API_TIKECT = "api_ticket";
    public static final String COURSE_PARTITION = "coursePartition";
    public static final String RESOURCE_TYPE = "resourceType";
    public static final String CONTENT_THEME = "contentTheme";

    public static final String CATE_TYPE = "courseFirstCategory";

    /**
     * 直播缓存记录时长
     */
    public static final int LIVE_TIME = 60 * 60 * 24 * 2;
    /**
     * 皮肤科
     */
    public static  final String SKIN_DEPARTMENT = "皮肤科";
    /**
     * 5a目标科室
     */
    public final static String[] TARGET_DEPARTMENT = new String[]{"无创科"};
    public static final String IMEIK_COMPANY_NAME = "爱美客技术发展股份有限公司";

    /**
     * bean拷贝时忽略属性
     */
    public final static String[] IGNORE_ISOLATOR_PROPERTIES = new String[]{"id", "objectCode", "createBy", "createTime",
            "updateBy", "updateTime", "deleted"};

    public static final String SYSTEM_USER_CODE = "999999999";

    public static final String[] NUM_STR = {"一","二","三","四","五","六","七","八","九","十"};

    //====================邀请活动几个时间字段 start====================

    /**
     * 活动code 作用：后期可能复用活动，根据活动code来区分不同的活动
     */
    public static final String ACTIVE_CODE = "1000000000";

    /**
     * 活动分享开始时间
     */
    public static final String ACTIVE_SHARE_TIME_START = "2022-10-09 00:00:00";
    /**
     * 活动分享结束时间
     */
    public static final String ACTIVE_SHARE_TIME_END = "2023-03-06 23:59:59";

    /**
     * 被邀请人升级截止时间
     */
    public static final String UPGRADE_TIME_END = "2023-03-07 23:59:59";

    /**
     * 领取礼品截止时间
     */
    public static final String GET_GIFT_TIME_END = "2023-03-09 23:59:59";

    /**
     * 皮肤美容-objectCode
     */
    public static final String SKIN_BEAUTY_CODE = "1513887132250107";

    //====================邀请活动几个时间字段 end====================

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private final int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private final int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private final int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum ApplyStatusEnum {

        WAIT_EXAMINE("3", "待审核"),
        ADOPT("1", "审核通过"),
        REJECT("2", "拒绝"),
        REVOKE("4", "撤销操作"),
        DELETE("5", "用户已删除"),
        NO_DISPLAY("6", "子评论不显示"),

        ;

        private final String status;

        private final String describe;

        ApplyStatusEnum(String status, String describe) {
            this.status = status;
            this.describe = describe;
        }

        public String getStatus() {
            return status;
        }

        public String getDescribe() {
            return describe;
        }
    }


    @AllArgsConstructor
    @Getter
    public enum QualificationStatusEnum {
        /**
         * 认证状态
         */
        AUTHENTICATION("1", "已认证"),
        NO_AUTHENTICATION("2", "未认证");

        private String status;

        private String describe;


        public String getStatus() {
            return status;
        }

        public String getDescribe() {
            return describe;
        }
    }

    public enum DoctorLevelEnum {
        LV1("V1", "1"),
        LV2("V2", "2"),
        LV3("V3", "3"),
        LV4("V4", "4"),
        LV5("V5", "5");
        private final String level;
        private final String code;

        DoctorLevelEnum(String level, String code) {
            this.level = level;
            this.code = code;
        }

        public String getLevel() {
            return level;
        }

        public String getCode() {
            return code;
        }
    }

    public enum YesOrNoEnum {

        YES(1, "是"),
        NO(2, "否");

        private final int status;

        private final String describe;

        YesOrNoEnum(int status, String describe) {
            this.status = status;
            this.describe = describe;
        }

        public int getStatus() {
            return status;
        }

        public String getDescribe() {
            return describe;
        }
    }

    /**
     * 课程创建来源
     */
    public enum CreateSourceEnum {
        /**
         * 全轩后台
         */
        QX_BACK("1", "全轩后台"),
        /**
         *
         */
        FRONT("2", "小程序创建");

        private final String type;

        private final String describe;

        CreateSourceEnum(String type, String describe) {
            this.type = type;
            this.describe = describe;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }
    }

    /**
     * 上下架状态
     */
    public enum ShelfStatusEnum {
        ALL("0", "全部"),
        /**
         * 2下架
         */
        DOWN("2", "下架"),
        /**
         * 1上架
         */
        UP("1", "上架");

        private final String type;

        private final String describe;

        ShelfStatusEnum(String type, String describe) {
            this.type = type;
            this.describe = describe;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }
    }

    public enum CourseApplyStatusEnum {

        CG("1", "草稿"),
        SHZ("2", "审核中"),
        SHTG("3", "审核通过"),
        SHBH("4", "审核驳回");

        private final String status;

        private final String describe;

        CourseApplyStatusEnum(String status, String describe) {
            this.status = status;
            this.describe = describe;

        }

        public static String getDesByStatus(String status) {
            for (CourseApplyStatusEnum applyEnum : CourseApplyStatusEnum.values()) {
                if (status.equals(applyEnum.getStatus())) {
                    return applyEnum.getDescribe();
                }
            }
            return status;
        }

        public String getStatus() {
            return status;
        }

        public String getDescribe() {
            return describe;
        }
    }

    public enum PitTypeEnum {

        INDEX_CAR("1", "首页轮播区"),
        OPERATE_COL("2", "运营栏位"),
        JG_DIST("3", "金刚区"),
        LIST_CAR("4", "分类轮播图"),
        COURSE_HOT("5", "课程热搜"),
        COLUMN_HOT("6", "专栏热搜");

        private final String typeCode;

        private final String describe;

        PitTypeEnum(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }
    }


    public enum ProductPath {
        /**
         * 爱芙莱金装
         */
        AFLJZ("afljz", "爱芙莱金装"),
        /**
         * 爱芙莱
         */
        ALF("afl", "爱芙莱"),
        /**
         * 宝尼达
         */
        BND("bnd", "宝尼达"),
        /**
         * 嗨体熊猫
         */
        HTXM("htxm", "嗨体熊猫"),
        /**
         * 嗨体
         */
        HT("ht", "嗨体"),
        /**
         * 紧恋
         */
        JL("jl", "紧恋"),
        /**
         * 冭活泡泡
         */
        THPP("thpp", "冭活泡泡"),
        /**
         * 逸美一加一证书
         */
        YMYJY("ymyjy", "逸美一加一"),
        /**
         * 逸美
         */
        YM("ym", "逸美");

        private final String path;
        private final String productName;


        ProductPath(String path, String productName) {
            this.path = path;
            this.productName = productName;
        }

        public static String getPathByName(String productName) {
            for (ProductPath productPath : ProductPath.values()) {
                if (productPath.getProductName().equals(productName)) {
                    return productPath.getPath();
                }
            }
            return "";
        }

        public String getPath() {
            return path;
        }

        public String getProductName() {
            return productName;
        }

    }

    /**
     * 附件类型(1模特协议 2知青同意书 3模特术前术后照 4会场照片 5会场视频 6其他资料）
     */
    public enum AttachTypeEnum {

        MODEL_VAL("1", "模特协议"),
        INFORM_VAL("2", "知情同意书"),
        MODEL_PHOTO("3", "模特手术前后照"),
        VENUE_PHOTOS("4", "会场照片"),
        VIDEOS_VAL("5", "会场视频"),
        OTHER_INFORMS("6", "其他资料");

        private final String typeCode;

        private final String describe;

        AttachTypeEnum(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }

    }

    /**
     * 会议属性 1:公司战略品牌学术会 2:行业赞助型会议 3:集团客户会议 4:产品项目技术推广会 5:示教会 6:专家销售活动
     */
    public enum MeetingProperties {

        ZLPP_VAL("1", "公司战略品牌学术会"),
        HYZZ_VAL("2", "行业赞助型会议"),
        JTKH_VAL("3", "集团客户会议"),
        XMJSTG_VAL("4", "产品项目技术推广会"),
        SJH_VAL("5", "示教会"),
        ZJXS_VAL("6", "专家销售活动");

        private final String typeCode;

        private final String describe;

        MeetingProperties(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }

    }

    /**
     * 消息通知类型 1:医生审核通知 2:认证审核通知 3:会议审批通知 4:会议总结提醒
     */
    public enum MessageType {
        /**
         * 通知类型
         */
        DOCTOR_AUDIT("1", "医生审核通知"),
        AUTH_AUDIT("2", "认证审核通知"),
        MEETING_AUDIT("3", "会议审批通知"),
        MEETING_SUMMARY("4", "会议总结提醒"),
        MEETING_REJECTED("5", "会议驳回通知"),
        MEETING_PASS("6", "会议通过通知"),
        ORGANIZATION_USER_AUDIT("7", "机构用户审核通知"),
        BUSINESS_USER_AUDIT("8", "商业用户审核通知"),
        MSG_AUDIT_NOTICE("9", "评论审核通知"),

        ACTIVITY_CASE_AUDIT("10", "案例审核通知"),

        ACTIVITY_SIGN_IN_NOTICE("11", "报名通知"),

        EQUIPMENT_BOUND_FAIL_NOTICE("12", "设备绑定失败通知"),
        AFTER_SALE_APPLY_NOTICE("13", "售后申请通知"),
        AFTER_SALE_APPLY_TIMEOUT_NOTICE("14", "售后申请超时通知"),

        MEETING_CC("20", "会议审批抄送通知"),
        MEETING_PENDING("21", "会议待审批通知"),
        MEETING_URGE("22", "会议催办通知"),
        MEETING_UPDATE_FORM("23", "会议审批单修改通知"),
        MEETING_END("24", "会议审批完成通知"),
        CANCEL_NOTICE("25", "会议取消通知"),
        REJECT_NOTICE("26", "会议驳回通知"),
        REFUSE_NOTICE("27", "会议拒绝通知"),
        ORGANIZATION_BUSINESS_AUDIT("15", "商学苑机构用户审核通知"),
        POSTPONE_NOTICE("28", "会议延期通知"),

        INTENDED_COOPERATION_NOTICE("29", "意向合作通知"),
        CONTRACT_APPROVE_REJECT("30", "合同审批驳回通知"),
        CONTRACT_APPROVE_PASS("31", "合同审批通过通知"),
        CONTRACT_SIGN_FINISH("32", "合同签署完成通知"),
        CONTRACT_WILL_EXPIRE("33", "合同即将到期通知"),
        CONTRACT_SIGN("34", "合同盖章提醒"),
        REGISTER_INVITE_APPROVEL_NOTICE("35", "资质审核通知"),
        PAYMENT_RECEIVED_NOTICE("36", "商务确认提醒"),
        CONTRACT_SEAL_NOTICE("37", "合同盖章通知"),

        FLOW_NO_APPROVAL("38", "通用流程审批通知"),
        FLOW_WITHDRAW("39", "通用流程申请人撤回通知"),
        FLOW_END("40", "通用流程审批通过通知"),
        FLOW_REFUSE("41", "通用流程审批拒绝通知"),
        FLOW_REJECT("42", "通用流程审批退回通知"),
        FLOW_CC("43", "通用流程审批抄送通知"),
        FLOW_URGE("44", "通用流程审批催办通知"),

        CONTRACT_RETURN_NOTICE("45", "合同撤回/退回通知"),
        USER_ADD_COMPANY_APPLY_NOTICE("46", "用户申请审批通知"),
        ;

        private final String typeCode;

        private final String describe;

        MessageType(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }
    }

    /**
     * 消息状态 1:未读 2:已读
     */
    public enum MessageStatus {

        UNREAD("1", "未读"),
        READ("2", "已读");

        private final String typeCode;

        private final String describe;

        MessageStatus(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }
    }

    /**
     * 删除状态 0:未删除 1:已删除
     */
    public enum DeleteStatus {

        UNDELETE("0", "未删除"),
        DELETE("1", "已删除");

        private final String typeCode;

        private final String describe;

        DeleteStatus(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }
    }

    /**
     * 直播间角色
     */
    public enum LiveRole {

        HOST("1", "主持人"),
        AUTHOR("2", "讲师"),
        AUDIENCE("3", "观众");

        private final String typeCode;

        private final String describe;

        LiveRole(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }
    }

    /**
     * 编号生成参数
     */
    public enum NumberGenerator {

        ACTIVITY("IMAC", 15, "001", "活动编号"),
        COURSE("2", 12, "00000001", "课程编号");

        private final String param;

        private final int len;

        private final String initVal;

        private final String describe;

        NumberGenerator(String param, int len, String initVal, String describe) {
            this.param = param;
            this.len = len;
            this.initVal = initVal;
            this.describe = describe;

        }

        public String getParam() {
            return param;
        }

        public int getLen() {
            return len;
        }

        public String getInitVal() {
            return initVal;
        }

        public String getDescribe() {
            return describe;
        }
    }


    public enum SubscribeMessageId {
        /**
         * 全轩Live小程序订阅消息id
         */
        QX_LIVE_BROADCAST_START_NOTIFICATION("EPYgmcklNByEhAFz9u_vKhj_tXtIufxyGy0jqwQsHWg", "直播开始通知订阅消息id"),
        /**
         * 认证人:{{thing1.DATA}}
         * 认证结果:{{phrase2.DATA}}
         * 温馨提醒:{{thing3.DATA}}
         */
        CERTIFICATION_RESULTS_NOTIFICATION("Fu2dZEDbuB-mSErCTh8-aJLxiG5pYHwIE9t0hlx3Uf8", "认证结果通知"),

        /**
         * 邀请成功通知模板
         * <p>
         * 活动名称:{{thing4.DATA}}
         * 邀请时间:{{time5.DATA}}
         * 被邀请人:{{thing8.DATA}}
         * 邀请说明:{{thing2.DATA}}
         */
        INVITE_SUCCESS_NOTIFICATION("4jaEvTMGETqpA5MC-u8b4E6h2Ps6DYFR__ySVv9P6h0", "邀请成功通知"),

        /**
         * 5a活动报名案例审核结果通知
         */
        TRAIN_APPROVAL_RESULT_NOTIFICATION("1KFuNPSwOnfI9cDICO63SU8fN9BNsdpvcnm095aMaPA", "报名审核通知"),

        /**
         * 报名成功通知
         */
        SIGNIN_SUCCESS_NOTIFICATION("IFH1IPtwe5lmCGLrCxyYakeTWCqAUfWzfGPrKERB9IM", "报名成功通知"),


        /**
         * 5a培训开始通知
         */
        TRAIN_START_NOTIFICATION("LG4hDReDQ1ZNpVrseSuNY1UtXfg92EkMSkjM4aldQe0", "培训通知"),

        /**
         * 5a培训评估通知
         */
        TRAIN_RESULT_NOTIFICATION("XgtYxPckPVeDGUrQe9Cc6aZFEWh2KZHIhXDT3p30QTI", "评估结果通知"),
        /**
         * 机构用户认证结果通知
         */
        ORG_USER_REGIST_RESULT_NOTIFICATION("Lo961MeKGl9P1YQU7wp5Q3EqJfKsNP9C8HtwWtHxJyU", "用户认证结果通知"),
        /**
         * 商学院 机构用户认证结果通知
         */
        BUS_ORG_USER_REGIST_RESULT_NOTIFICATION("40d4FDvVFvBuEhRIAohenPVRl4AxmAaUZRSBi_k9Omg", "用户认证结果通知"),
        /**
         * 企业注册审批结果通知
         */
        COMPANY_REGIST_RESULT_NOTIFICATION("i1PCVyTplHxT_je9wfezAlEeg7YlUeojbZLn-HMrwXg", "审批结果通知"),
        /**
         * 课程订阅通知模板 全轩
         */
        COURSE_SUBSCRIBE_NOTIFICATION("735Agqf5XIL3Nb25opPG-wPxXtzqRJNHBZDBq6dtQ5g", "课程订阅通知"),
        /**
         * 课程订阅通知模板 全轩live
         */
        COURSE_SUBSCRIBE_NOTIFICATION_LIVE("uVzVLeqox2HekjozXXYE2BhWF4rRDp85BVQAJghfZwk", "课程订阅通知"),

        /**
         * 爱+发送作品审批结果通知
         */
        ICLUB_WORKS_APPROVE_RESULT_MSG("JoiB9QENo9REqiVcrb336zmDu7OvzrGkyCeX349KgEI", "作品审批结果通知"),

        /**
         * 全轩小程序订阅消息id
         */
        QX_BROADCAST_START_NOTIFICATION("S40puw9Jr3a4WbOgtRxJgVpda9bGeu6BFBILnRNZo2I", "直播开始通知订阅消息id"),

        /**
         * 直播提醒消息 -用的会议模版
         */
        QX_MEET_BROADCAST_START_NOTIFICATION("HAmZwgD1GHWOUqXLWKCXg_3W3iA73dc6RRaebYRoEZc", "直播开始通知订阅消息id"),

        /**
         * 全轩小程序积分入账通知id
         */
        QX_CREDIT_NOTICE_NOTIFICATION("R_dAbAv6esoDE7djU6M2xYyfYTQtes5piSNLwou9W90", "积分入账通知订阅消息id"),
        ;

        private String id;
        private String describe;

        SubscribeMessageId(String id, String describe) {
            this.id = id;
            this.describe = describe;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }
    }

    /**
     * 课程类型
     */
    public enum CourseType {

        ONLINE("1", "线上课程"),
        LIVE_COURSE("2", "直播课程");

        private final String typeCode;

        private final String describe;

        CourseType(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }
    }

    /**
     * 目录上线状态
     */
    public enum CatalogueType {

        ONLINE("1", "已上线"),
        OFFLINE("2", "待上线");

        private final String typeCode;

        private final String describe;

        CatalogueType(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }
    }

    /**
     * 直播课程考试状态
     */
    public enum CourseExamStatus {

        OFFLINE("1", "待上线"),
        NO_PART("2", "未参加"),
        REVIEW("3", "阅卷中"),
        PASS("4", "通过"),
        FAIL("5", "不通过");

        private final String typeCode;

        private final String describe;

        CourseExamStatus(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }
    }

    /**
     * 直播课程考试状态
     */
    public enum RigestType {

        ACTIVITY("1", "活动报名"),
        LIVE("2", "直播预约"),
        TRAIN("3", "线下培训报名");

        private final String typeCode;

        private final String describe;

        RigestType(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }
    }

    /**
     * 净回款达成率
     */
    public enum FinishRateType {
        LEVEL_1("1", "90%及其以上"),
        LEVEL_2("2", "80%（含）~90%"),
        LEVEL_3("3", "70%（含）~80%"),
        LEVEL_4("4", "70%以下");

        private final String typeCode;

        private final String describe;

        FinishRateType(String typeCode, String describe) {
            this.typeCode = typeCode;
            this.describe = describe;

        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getDescribe() {
            return describe;
        }
    }

    /**
     * 权限标识枚举
     */
    public enum PermissionFlagEnum {
        /**
         * 权限标识枚举
         */
        DOCTOR_AUDIT_FLAG("医生审批列表", "menu_audit_doctorlist"),
        AUTH_AUDIT_FLAG("认证列表", "menu_certificate_list"),
        INSTIT_AUDIT_FLAG("机构用户审批", "menu_audit_instit"),
        BUSINESS_AUDIT_FLAG("商业用户审批", "menu_audit_business");


        private final String name;
        private final String flag;

        PermissionFlagEnum(String name, String flag) {
            this.name = name;
            this.flag = flag;
        }

        public String getName() {
            return name;
        }

        public String getFlag() {
            return flag;
        }
    }


    /**
     * *注册来源
     **/
    @AllArgsConstructor
    @Getter
    public enum RegistrationSourceEnum {
        /**
         * 注册来源
         */
        APPLET("1", "全轩"),
        PC("2", "后台"),
        IMEIK_SERVICE("3", "爱美客服务"),
        SYSTEM("4", "系统"),
        BUS_SOHOOL("5","商学苑"),
        ICLUB("6","爱+club"),
        ;

        private final String code;
        private final String name;

        public static RegistrationSourceEnum getRegistrationSourceEnum(String code){
            for (RegistrationSourceEnum e : values()) {
                if (code.equals(e.getCode())) {
                    return e;
                }
            }
            return null;
        }
    }

    @AllArgsConstructor
    @Getter
    public enum UserIdentityEnum {
        /**
         * 用户身份类型 1-医生 3-机构 4-商业
         */
        DOCTOR_USER("1", "医生用户"),
        ORGANIZATION_USER("3", "机构用户"),
        BUSINESS_USER("4", "商业用户");


        private final String code;
        private final String name;
    }


    @AllArgsConstructor
    @Getter
    public enum SystemSourceEnum {
        /**
         * 用户来源 1全轩  2-官网 3-爱美客服务 4.销售系统IBT订单同步
         */
        QUAN_APPLET_PC("1", "全轩"),

        OFFICIAL_WEBSITE("2", "官网"),

        IMEIK_SERVICE("3", "爱美客服务"),

        SALES_IBT_ORDER_SYNC("4", "销售系统IBT订单同步"),

        BUSINESS_WECHAT("5", "商学院"),
        ICLUB("6", "爱+club");

        private final String code;
        private final String name;

        /**
         * 整形值转换为枚举类
         *
         * @param code 值
         * @return 枚举类
         */
        public static SystemSourceEnum getSystemSourceEnum(String code) {
            for (SystemSourceEnum e : values()) {
                if (code.equals(e.getCode()) ) {
                    return e;
                }
            }
            return null;
        }
    }

    @AllArgsConstructor
    @Getter
    public enum ActivityTypeEnum {
        /**
         * 活动类型
         */
        COMMON("1", "通用活动"),


        FIVE_A("2", "5A计划活动");


        private final String code;
        private final String name;
    }

    @AllArgsConstructor
    @Getter
    public enum ActivityTrainLevelEnum {
        /**
         * 培训班分级 1-微整注射实操初级班 2-微整注射实操高级班 3-线埋植技术实操培训班
         */
        PRIMARY("1", "微整注射实操初级班"),


        SENIOR("2", "微整注射实操高级班"),

        ACTUAL("3", "线埋植技术实操培训班");
        private final String code;
        private final String name;
    }

    @AllArgsConstructor
    @Getter
    public enum ActivityTrainCaseTypeEnum {
        /**
         * 案例分类 1-初级班报名条件案例 2-微整注射实操高级班报名条件(未参加过初级班) 3-微整高级班报名掌握初级案例(参加过初级班) 4-线埋报名条件(未参加过高级版) 5-线埋报名掌握高级班内容案例(参加过高级班)
         */
        PRIMARY("1", "初级班报名条件案例"),
        SENIOR_NO_SIGN_PRIMARY("2", "微整注射实操高级班报名条件(未参加过初级班) "),
        SENIOR_SIGN_PRIMARY("3", "微整高级班报名掌握初级案例(参加过初级班)"),
        ACTUAL_NO_SIGN_SENIOR("4", "线埋报名条件(未参加过高级班)"),
        ACTUAL_SIGN_SENIOR("5", "线埋报名掌握高级班内容案例(参加过高级班)");
        private final String code;
        private final String name;
    }


    @AllArgsConstructor
    @Getter
    public enum ActTrainApprovalEnum {
        /**
         * 培训级别 1-未审批  2-审批通过 3-审批驳回
         */
        NO_APPROVAL("1", "未审批"),
        PASS("2", "审批通过"),
        REJECTION("3","审批驳回");

        private final String code;
        private final String name;
    }

    @AllArgsConstructor
    @Getter
    public enum ActTrainSignStatusEnum {
        /**
         * 报名确认状态 1-未确认  2-已确认
         */
        NO_CONFIRM("1", "待确认"),
        CONFIRM("2", "已确认");

        private final String code;
        private final String name;
    }


    @AllArgsConstructor
    @Getter
    public enum ActTrainResultEnum {
        /**
         * 培训结果，1-培训未通过 2-培训通过 3-优秀
         */
        NO_PASS("1", "不通过"),
        PASS("2", "通过"),
        EXCELLENT("3", "优秀");

        private final String code;
        private final String name;

        public static ActTrainResultEnum getEnum(String code) {
            for (ActTrainResultEnum e : values()) {
                if (code.equals(e.getCode())) {
                    return e;
                }
            }
            return null;
        }
    }


    public enum ActCourseExportTypeEnum {
        /**
         * 权限标识枚举
         */
        ACT_COURSE("直播", "1"),
        ACT_COURSE_REPLAY("回看", "2"),
        COURSE_VIEW("课程学习", "3");

        private final String name;
        private final String code;

        ActCourseExportTypeEnum(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public static ActCourseExportTypeEnum getActCourseExportTypeEnum(String code) {
            for (ActCourseExportTypeEnum e : values()) {
                if (code.equals(e.getCode())) {
                    return e;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

    }


    @AllArgsConstructor
    @Getter
    public enum ReachPlanFilterEnum {
        /**
         * 触达计划过滤用户类型 1-按条件过滤 2-全部用户 3-按上传文件
         */
        CONDITION_FILTER("1", "条件过滤"),
        ALL_USER("2", "全部用户"),
        FILE_FILTER("3", "上传文件过滤");

        private final String code;

        private final String name;

    }


    @Getter
    @AllArgsConstructor
    public enum ReachPlanTypeEnum {
        /**
         * 触达计划发送类型
         */
        SCHEDULED_SEND("1", "定时发送"),
        NOW_SEND("2", "立即发送");

        private final String code;

        private final String name;
    }
    @Getter
    @AllArgsConstructor
    public enum TopSourceEnum{
        /**
         * 置顶来源
         */
        PC_ADMIN(1, "后台"),
        MINIAPP(2, "小程序");

        private final Integer code;

        private final String name;
    }

    @Getter
    @AllArgsConstructor
    public enum PlatformEnum {
        /**
         * 触达计划发送类型
         */
        APPLET("1", "小程序"),
        WECHAT("2", "公众号");

        private final String code;

        private final String name;
    }
    @Getter
    @AllArgsConstructor
    public enum SourceResourceEnum{
        /**
         * 触达计划发送类型
         */
        OWN_CREATE("1", "官方原创（自建）"),
        ACCREDIT("2", "机构授权（授权）"),
        UGC("3", "个人生产（UGC）");
        public static SourceResourceEnum getResourceEnum(String name){
            for (SourceResourceEnum e : values()) {
                if (name.equals(e.getName())) {
                    return e;
                }
            }
            return null;
        }

        private final String code;

        private final String name;
    }

    @Getter
    @AllArgsConstructor
    public enum ViewStatusEnum {
        /**
         * 视频观看状态
         */
        NOSTUDY(1, "未学习"),
        STUDYING(2, "进行中"),
        OVER(3, "已完成"),
        ;

        private final int code;

        private final String name;

    }


    @AllArgsConstructor
    @Getter
    public enum QuestionnaireFilterEnum {
        /**
         * 筛选用户标识 1-未筛选 2-按条件筛选 3-上传手机号列表
         */
        ALl_USER("1", "未过滤"),
        CONDITION_FILTER("2", "按条件过滤"),
        FILE_FILTER("3", "上传文件过滤");

        private final String code;

        private final String name;

    }
    @AllArgsConstructor
    @Getter
    public enum IbtOrgUserFlagEnum {
        /**
         * 注册用户：医生或者机构用户
         */
        REGISTER_USER("1", "注册用户"),
        NO_REGIST_USER("2", "非注册用户");

        private final String code;

        private final String name;

    }

    @AllArgsConstructor
    @Getter
    public enum SynchBtnShowFlagEnum {
        /**
         * 同步imeik服务-按钮是否展示
         */
        SHOW("1", "显示/已同步"),
        HIDE("2", "隐藏/未同步");

        private final String code;

        private final String name;

    }
    @AllArgsConstructor
    @Getter
    public enum WhiteListTypeEnum {
        /**
         * 1免审核白名单、2考试资格、3跨班型报名、4报名限制
         */
        AUDIT_FREE("1", "免审核限制"),
        EXAM_LIMIT("2", "考试资格限制"),
        ALLOW_CLASS_TYPE("3", "跨班型报名限制"),
        SIGN_LIMIT("4", "报名限制"),
        ACT_DETAIL_LIMIT("5", "活动详情查看白名单"),

        ;

        private final String code;

        private final String name;

    }
    @AllArgsConstructor
    @Getter
    public enum OfflineActApplyStatusEnum {

        NO_SIGN("1", "未报名"),
        SHZ("2", "审核中"),
        SHTG("3", "审核通过"),
        SHBH("4", "审核驳回"),
        QX("5", "已取消报名");

        private final String status;

        private final String describe;
    }

    /**
     * 搜索历史
     */
    @AllArgsConstructor
    @Getter
    public enum SearchHistoryEnum {

        COURSE("1", "课程"),

        COLUMN("2", "专栏");

        private final String type;

        private final String describe;
    }
    /**
     * 一级分类活动报名限制开关
     */
    @AllArgsConstructor
    @Getter
    public enum LimitFlagEnum {

        ON("1", "开启"),

        OFF("2", "关闭");

        private final String code;

        private final String describe;
    }
    @AllArgsConstructor
    @Getter
    public enum RequireTypeEnum {
        /**
         * 线下报名要求类型
         */
        EXAM("1", "考试要求"),
        WHITELIST("2", "报名白名单"),
        VIDEO("3", "视频要求");

        private final String code;

        private final String name;

    }

    /**
     * 大于
     */
    public static final String GREATER = "greater";

    /**
     * 小于
     */
    public static final String LESS = "less";

    public static final String BUSINESS_APP ="quanxuan";
    public static final String BUSINESS_SYS ="college";

    /** 特殊header前缀 */
    public static final String SPECIAL_HEADER_PREFIX = "x-imeik-";
    /** 刷新token */
    public static final String REFRESH_TOKEN = SPECIAL_HEADER_PREFIX + "refreshToken";

    /** 字典缓存key */
    public static final String DICTIONARY_TREE_CACHE_KEY = "DICTIONARY_TREE_CACHE_KEY";
}
