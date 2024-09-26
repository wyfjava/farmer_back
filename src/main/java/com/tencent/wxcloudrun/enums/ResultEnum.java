package com.tencent.wxcloudrun.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    //成功code 200
    SUCCESS(200, "OK"),
    AUTHENTICATION_FAILED(300, "身份认证失败"),
    AUTHORIZATION_FAILED(301, "授权失败"),
    FAILED(500, "操作失败,请联系管理员处理"),

    //TODO 1000开始添加业务相关code 500为一段增加,

    REPEATED(3000, "处理中,请勿重复操作"),
    NOT_LOGIN(3001, "请登录!"),


    VERIFICATION_ERROR(1001, "验证码错误"),
    PARAMETER_ERROR(1002, "参数错误"),
    NOT_FOUND(1004, "密码有误"),
    USER_IP_LIMIT(1006, "网络地址不正确"),
    USER_LOCK(1007, "账号被锁定"),
    USER_EXPIRETIME(1008, "账号逾期"),
    USER_NOT_FOUND(1009, "用户不存在"),
    USERNAME_NOT_FOUND(1010, "用户名错误"),
    NO_PERMISSION(1011, "权限不足"),
    PHONE_IS_REGISTER(1012, "手机号已被注册"),
    PHONE_NOT_REGISTER(1013, "手机号未注册"),
    NOT_FOUND_ROLE(1014, "没有获取到相关角色"),
    NOT_ALLOW_LOGIN(1015, "无权登录访问"),
    PERMISSION_FLAG_ALREADY_EXISTED(1016, "权限标识已经存在"),
    HAVE_EMPLOYEE_CAN_NOT_DELETE(1017, "该角色下已经绑定员工，不允许删除"),
    ADD_HOSPITAL_NAME(1018, "机构名字已存在。"),
    EMPLOYEE_EXIST(1019,"员工已经存在"),


    FIRST_OR_NOT(1019, "必须有第一职业地点"),
    PRODUCT_ONE_DELETE_ERROR(1020,"已关联二级产品，不能删除。"),
    PRODUCT_ONE_USER_DELETE_ERROR(1021,"已有认证人员，不能删除。"),
    ACCOUNT_LOCK(1022,"密码错误10次，账号被锁定，如需解锁请联系@管理员解锁"),
    //数据操作code 1051-1100
    FILE_NOT_FOUND(1051, "未找到数据"),
    PARAM_ERROR(1052, "数据获取失败"),
    DATA_EXISTED(1053, "数据已存在"),
    DATA_EXISTED_GROUP(1054, "创建失败，已有该集团"),
    DATA_EXISTED_ENTERPRISE(1055, "创建失败，已有该企业"),
    DATA_EXISTED_ORG(1056, "创建失败，已有该机构"),
    DATA_EXISTED_ACCOUNT(1057, "您已完成本次答题，请勿重复答题"),
    FILE_NOT_FOUND_GROUP_DATA(1058, "未找集团数据"),
    FILE_NOT_FOUND_ORG_DATA(1059, "未找机构数据"),
    DICTIONARY_SING_EXISTENCE(1060, "标识不能重复"),
    APPLY_STATUS_ERROR(1061,"已审核或待审核中"),
    HOSPITAL_CODE_STATUS_ERROR(1062,"机构数据重复"),
    COURSE_OCCUPY_ERROR(1063,"医生已在课程中展示，不可删除"),
    PROCESS_CODE_STATUS_ERROR(1064,"流程配置创建失败"),
    PROCESS_PRODUCT_CODE_STATUS_ERROR(1065,"专栏以被关联"),
    PRODUCT_PROCESS_CODE_STATUS_ERROR(1066,"产品未配置认证流程"),
    CERTIFICATE_PRODUCT_CODE_STATUS_ERROR(1067,"已有认证产品或正在认证中"),
    CERTIFICATE_AUID_CODE_STATUS_ERROR(1068,"已审核过"),
    MATERIAL_OCCUPY_ERROR(1069,"素材已在课程中展示，不可删除"),



    PROCESS_PAPER_CODE_STATUS_ERROR(1070,"试卷已被关联"),
    OPERATE_OCCUPY_ERROR(1071,"金刚区运营栏位已存在，请走修改操作！"),
    PRODUCT_PROCESS_TOTAL_SCORE_ERROR(1072,"配置分数不能大于试卷分数!"),
    USER_BUY_COURSE_ERROR(1073,"您已购买过该课程!"),
    USER_BUY_COURSE_BALANCE_ERROR(1074,"用户积分不足!"),
    USER_BUY_COURSE_ZERO_ERROR(1075,"当前课程免费不需要购买!"),
    COLUMN_PROCESS_CINFIG_ERROR(1076,"当前专栏已被流程配置关联，不可删除!"),
    CANCEL_APPLY_ERROR(1077,"取消驳回失败，用户已被删除"),
    CHECK_USER_HOSPITAL(1078,"机构未建账"),
    COURSE_OPERATE_WARNING(1079,"与其它运营位冲突"),
    USER_APPLY_INPUT_WARNING(1080,"当前有手动输入的机构, 请进行修改"),
    USER_NOT_EXIST(1081,"医生信息不存在"),
    // 认证相关 1100- 1130
    CERTIFICATE_RULE_ERROR(1100,"不符符合考试规则"),
    CERTIFICATE_PARAM_ERROR(1101,"考试数据有问题"),

    // 问卷相关 1130 - 1150
    NOT_YET_ERROR(1130, "问卷还未开始"),
    EXCEEDING_NUMBER_ANWSERS_ERROR(1131, "超过作答次数"),
    SAME_DAY_EXCEEDING_NUMBER_ANWSERS_ERROR(1132, "超过作答次数！"),
    END_YET_ERROR(1133, "问卷已过期"),

    //触达计划
    REACH_PLAN_PHONE_ERROR(1200, "计划发送人员手机号格式不正确"),
    REACH_PLAN_EXCEL_EMPTY_ERROR(5010, "上传的excel表格信息为空！"),

    //文件上传下载code  2001-2050
    UPLOAD_FAILED(2001, "文件上传失败"),
    DOWNLOAD_FAILED(2002, "文件下载失败"),
    DELETED_FAILED(2003, "文件删除失败"),
    REPEAT_FAILED(2004, "文件重复"),
    //线下活动
    OFFLINE_ACTIVITY_SAVE_FAILED(3001, "活动保存失败！"),
    OFFLINE_ACTIVITY_UPLOAD_ERROR_01(3002, "上传的excel表格信息为空！"),
    OFFLINE_ACTIVITY_WHITE_LIST_02(3003, "白名单人员手机号格式不正确！"),
    OFFLINE_ACTIVITY_COLLECT_WHITE_LIST_02(3026, "V1用户信息收集白名单人员手机号格式不正确！"),
    OFFLINE_ACTIVITY_WHITE_DELETE_ERROR(3004, "白名单人员逻辑删失败！"),
    OFFLINE_ACTIVITY_WHITE_ERROR_02(3005, "保存白名单信息异常！"),
    OFFLINE_ACTIVITY_NO_EXIST(3006, "线下活动不存在！"),
    OFFLINE_ACTIVITY_COLLECT_NOT_ALLOW_DELETE(3007, "课程合集下有课程不允许删除！"),
    OFFLINE_ACTIVITY_COURSE_ADD_ERROR(3008, "请至少关联一个课程！"),
    OFFLINE_ACTIVITY_EXAM_ADD_ERROR(3009, "请至少关联一个试卷！"),
    OFFLINE_ACTIVITY_EXAMList_ADD_ERROR(30131, "请至少关联一个试卷合集！"),
    OFFLINE_ACT_EXAM_COLLECT_NOT_ALLOW_DELETE(3010, "考试合集下有考试不允许删除！"),
    OFFLINE_ACT_CLASS_TYPE_NO_EXIST(3011, "线下活动班型不存在！"),
    OFFLINE_ACT_CLASS_RANK_NO_EXIST(3012, "线下活动班次不存在！"),
    OFFLINE_ACT_COLLECT_DELETE_ERROR(3013, "线下活动合集下关联有活动，不允许删除！"),
    OFFLINE_ACT_APPROVAL_ERROR(3014, "线下活动-报名审核信息不存在！"),
    OFFLINE_ACT_CLASSRANK_CROSS(3015, "同个班型报名了多个班次！"),
    OFFLINE_ACT_EXAM_NO_PASS(3016, "考试未全部通过"),
    OFFLINE_ACT_SIGN_WHITE_LIST(3017, "不在报名白名单内"),
    OFFLINE_ACT_CLASSTYPE_CROSS(3018, "不符合跨班型报名条件！"),
    OFFLINE_ACT_EXAM_USER_LEVEL_LIMIT(3019, "不符合考试资格限制！"),
    OFFLINE_ACT_EXAM_WHITE_LIMIT(3020, "不在考试白名单内！"),
    OFFLINE_ACTIVITY_REQ_ADD_ERROR(3021, "已存在此类型的报名要求！"),
    OFFLINE_ACT_WHITE_ORG_NAME_ERROR(3022, "白名单内机构名称格式错误！"),
    OFFLINE_ACT_WHITE_ORG_CODE_ERROR(3023, "白名单内机构编码格式错误！"),
    OFFLINE_ACT_COLLECT_SIGN_ERROR(3024, "您已报名活动下其他班次！"),
    OFFLINE_ACT_SIGN_LIMIT_ERROR(3025, "该活动报名人数已达上线！"),
    OFFLINE_ACT_CATE_NUM_LIMIT_ERROR(3026, "活动板块数量不能超过5个！"),
    OFFLINE_ACT_REQUIRE_VIDEO_ERROR(3027, "请先完成所有报名要求！"),
    //http API相关
    API_REQUEST_ERROR(5001,"接口调用失败"),
    API_MOOR_AUTH_ERROR(5002, "授权失败,未找到机构账号"),
    API_NO_ACCOUNT_ERROR(5003, "授权失败,请联系管理员在账号管理中设置座席号"),
    API_NO_BUS_ACCOUNT_ERROR(5004, "授权失败,请联系管理员在机构管理中关联通讯号"),
    //添加积分
    API_PERSON_CENTER_NO_USER_INFO(5005, "您的登录已超时，请先登录！"),
    API_USER_INFO_ERROR(5006, "没有该用户信息！"),
    API_INTEGRAL_INFO_ERROR(5007, "没有获取到相应得积分规则！"),
    API_USER_INTEGRAL_ERROR(5008, "用户积分修改失败！"),
    API_INTEGRAL_RECORD_ERROR(5009, "用户积分修改明细添加失败！"),
    MEET_EXCEL_UPLOAD_ERROR_01(5010, "上传的excel表格信息为空！"),

    //会议
    MEET_EXCEL_UPLOAD_ERROR_02(5011, "保存参会人员信息异常！"),
    MEET_EXCEL_UPLOAD_ERROR_03(5012, "保存会议与参会人员关联信息异常！"),
    MEET_CHECK_ERROR(5013, "会议不存在！"),
    MEET_PARTICIPANTS_DELETE_ERROR(5014, "参会人员逻辑删失败！"),
    MEET_SUMMARY_ERROR(5015, "会议总结不存在！"),
    MEET_ATTACHMENT_NULL(5017, "没会议附件！"),
    COLUMN_ERROR_01(5016, "专栏不存在！"),

    MEET_PARTI_01(5018, "参会人员名称必填！"),
    MEET_PARTI_02(5019, "参会人员手机号格式不正确！"),
    MEET_PARTI_03(5019, "参会人员机构名称必填！"),
    MEET_PARTI_04(5020, "参会人员角色必填！"),
    MEET_PARTI_05(5021, "参会人员科室必填！"),
    MEET_PARTI_06(5022, "参会人员职务必填！"),
    MEET_PARTI_07(5023, "解析文件异常！"),
    APPROVAL_PERSON_ERROR(5024, "审批人错误！"),
    DEAL_OPINION_NULL(5025, "处理意见不能为空！"),
    MEET_PARTI_08(5026, "参会人员机构编码必填！"),
    WHITE_LIST_01(5027, "手机号必填！"),
    COLUMN_ERROR_02(5028, "专栏下没有课程关联！"),

    COLUMN_CATE_ERROR_01(5029, "该专栏分类不存在！"),
    //直播活动
    ACTIVITY_NULL(5050, "活动不存在！"),
    ACTIVITY_CATA_DEL_FORBID(5051, "活动下有目录存在，不允许删除！"),
    ACTIVITY_COURSE_NULL(5052, "直播不存在！"),
    ACTIVITY_COURSE_PAPER_NULL(5053, "直播没关联试卷！"),
    ACTIVITY_ONLINE_COURSE_NULL(5054, "直播没关联线上课程！"),
    ACTIVITY_ONLINE_COURSE_ERROR(5055, "直播关联线上课程不存在！"),
    ACTIVITY_REGIST_ERROR(5056, "您已报名！"),
    ACTIVITY_REGIST_NO_PART(5057, "无人报名！"),
    ACTIVITY_COURSE_COMMENT_ERROR1(5058, "获取直播评论接口参数异常！"),
    ACTIVITY_COURSE_COMMENT_ERROR2(5059, "相应的直播不存在！"),
    ACTIVITY_COURSE_EXAM_ERROR1(5060, "接口参数异常！"),
    WHITE_LIST_ERROR1(5061, "保存白名单失败！"),
    COLLECT_WHITE_LIST_ERROR1(5065, "保存V1用户收集白名单失败！"),
    ACTIVITY_COURSE_DEL_FORBID(5062, "目录下有直播存在，不允许删除！"),

    ACTIVITY_ACT_COURSE_ERROR(5063, "直播课程不存在！"),

    ACTIVITY_COURSE_NOT_ALLOW_DELETE(5064, "直播没结束，不允许删除！"),

    //触达计划
    QUESTIONNAIRE_PHONE_ERROR(5200, "问卷过滤手机号格式不正确"),
    QUESTIONNAIRE_EXCEL_EMPTY_ERROR(5201, "问卷上传的excel表格信息为空！"),



    PHONE_ERROR(5300, "手机号格式不正确"),

    EXCEL_EMPTY_ERROR(5301, "excel表格信息为空！"),


    PROJECT_EXIST(5500,"该项目已被创建，无需重复创建"),
    PROJECT_HAVE_EXPERT(5501,"该课题有关联专家，请先解除关联之后再删除！"),
    EXPERT_EXIST(5502,"该单位的专家已被创建，无需重复创建！"),
    FORUM_EXIST(5503,"该论坛已被创建，无需重复创建！"),
    EXPERT_HAVE_FORUM(5504,"该专家有关联论坛，请先解除关联之后再删除！"),
    EXPERT_HAVE_PROJECT(5505,"该专家有关联课题项目，请先解除关联之后再删除！"),
    FORUM_HAVE_EXPERT(5506,"该论坛有关联专家，请先解除关联之后再删除！"),
    SCIENTIFIC_FIELD_CHECK_NAME(5507,"字段名称重复！"),
    SCIENTIFIC_FIELD_UPDATE_CHECK_AUTH(5508,"没有权限修改字段"),
    SCIENTIFIC_FIELD_OPTION_CHECK_NAME(5509,"字段选项名称重复"),
    SCIENTIFIC_EXPERT_CHECK_EDIT_AUTH(5510,"无权限修改专家信息！"),
    SCIENTIFIC_EXPERT_CHECK_DELETED_AUTH(5511,"无权限删除专家信息！"),



    //微信支付相关  6001-6050
    GET_OPENID_ERROR(6001, "获取openid失败！"),
    CALL_WX_MAKE_ORDER_ERROR(6002, "调用微信生成预支付订单失败！"),
    WX_PAY_NOTIFY_ORDER_ERROR(6003, "微信支付结果回调处理失败！"),

    // 直播间
    LIVE_NOT_STARTED(6052, "直播未开始或已结束"),
    LIVE_CLOSE(6053, "直播已关闭"),
    LIVE_NON_EXISTENT(6054, "直播不存在"),
    LIVE_ROLE_ERROR(6055, "当前角色不正确"),

    //忘记密码
    SEND_SMS_VARCODE_ERROR(7001,"调用阿里云发送验证码失败！"),
    VARCODE_FAILURE(7002,"验证码错误或已过期！"),
    VARCODE_ONCE_PER_MINUTE(7003,"您的操作过于频繁，请稍后再试！"),
    ACCOUNT_DOES_NOT_EXIST(7004,"账号不存在"),
    ACCOUNT_DOES_NOT_EXISTS(7005,"账号不存在."),


    EXCEL_ANALYSIS_ERROR(8000,"excel数据解析不符合规则"),
    //课程数据清洗
    COURSE_CODE_ERROR(8001,"课程编码不能为空"),
    SOURCE_RESOURCE_ERROR(8002,"资源来源不能为空"),
    SOURCE_TYPE_ERROR(8003,"资源类型不能为空"),
    CONTENT_THEME_ERROR(8004,"内容题材不能为空"),
    COURSE_CATE_ERROR(8005,"课程类型不能为空"),


    //企业微信异常
    WECOM_LOGIN_ERROR(9000, "企业微信授权登录失败"),

    //会议
    MEETING_CHECK_TYPE_NAME(9001,"会议类型名字重复"),
    MEETING_CHECK_TYPE_NUM(9002,"会议大类代码重复"),
    MEETING_CHECK_STATUS_OPEN(9003,"会议大类有启动类型"),

    LEARN_POWER_CODE_INVALID(10001, "兑换码无效"),
    LEARN_POWER_CODE_USED(10002, "兑换码已使用"),
    LEARN_POWER_CODE_EXPIRE(10003, "兑换码过期"),
    INJECTION_USER_FLOW_ERROR(11001,"注射医生提交失败"),
    INJECTION_DOCTOR_USER_CHECK_PHONE(11002,"医生已申请成为V2，正在审核中或审核驳回"),
    INJECTION_DOCTOR_USER_CHECK_LEVEL(11003,"该用户已是注册医生，请直接选择"),
    INJECTION_LABEL_CHECK_NAME(11003,"标签名重复"),
    INJECTION_USER_CHECK_PHONE(11004,"手机号已注册注射医生"),


    INJECTION_LABEL_ASSIGN_DEPARTMENT_EMPTY(11005,"标签指定使用部门为空"),
    INJECTOR_DOCTOR_NOT_EXIST(11006,"注射医生不存在"),

    INJECTOR_DOCTOR_NOT_APPROVE_PASS(11007,"注射医生未审批通过，不可执行修改操作"),
    COMMENT_EXPERT_REPEAT(12000,"已评论,不能重复评论"),
    NOT_EXPERT_RDOCTOR(12001,"您不是该活动医生"),
    NON_FEEDBACK(12002,"活动执行完成后,可反馈"),
    NON_DONE(12003,"审批未通过,不能执行该操作"),
    COMMENT_CLOSE(12004,"执行完成30个自然日之后不再收集用户评价"),
    COMMENT_DETAIL(12005,"活动已失效"),
    FEES_NAME_DUPL(13000,"重复的活动名称"),
    CONFIRMED_ERROR(13004,"积分已确认,请勿重复操作"),
    CONFIRMED_ERROR2(13005,"只能该活动医生可确认"),
    DOCTORDETAIL_ERROR(13006,"医生二维码与登录人不一致"),

    FEES_NO_STUDENT(13003,"不是该班型学员"),

    FEES_CLASS_UPDATE(13001,"活动执行完成,班型信息不允许编辑"),
    FEES_CLASS_NAME(13002,"班型名称不能重复"),

    FEES_CLASS_SIGNUP_NULL(13101,"报名班型不存在或已取消!"),
    FEES_CLASS_SIGNUP_NOT_TIME(13102,"该班型不在报名时间!"),
    FEES_CLASS_SIGNUP_NOT_QUOTA(13103,"报名失败名额已满!"),
    FEES_CLASS_SIGNUP_NOT_INTEGRAL(13104,"用户报名班型积分不足!"),
    FEES_CLASS_SIGNUP_UPDATE_INTEGRAL_FAIL(13105,"用户报名班型积分扣除失败!"),
    FEES_CLASS_UPDATE_REFUND(13106,"取消报名状态才能退款"),
    FEES_ACTIVITY_SIGNUP_ERROR_STATUS(13107,"该班型已过期!"),
    //专栏勋章
    COLUMN_MEDAL_CHECK_NAME(14001,"专栏勋章名字重复"),

    //直播抽奖
    LIVE_LOTTERY_SAVE_ERROR(16001,"直播抽奖创建失败"),
    LIVE_LOTTERY_SAVE_REPEAT(16002,"当前时间已有直播抽奖"),
    LIVE_PK_SAVE_REPEAT(16003,"当前时间已有pk投票"),
    LIVE_PK_SAVE_ERROR(16004,"直播投票创建失败"),
    LIVE_LOTTERY_CHECK_EXCEL_ERROR(16005,"上传中奖用户格式错误"),


    ;


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;


    }


}
