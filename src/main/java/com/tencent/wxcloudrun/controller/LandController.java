package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.constant.Constant;
import com.tencent.wxcloudrun.dto.LandDTO;
import com.tencent.wxcloudrun.service.LandService;
import com.tencent.wxcloudrun.utils.PageUtils;
import com.tencent.wxcloudrun.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.tencent.wxcloudrun.constant.Constant.SUCCESS_MSG;


/**
 * 字段管理
 *
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 18:20:43
 */
@RestController
@RequestMapping("/api/land")
public class LandController {

    @Autowired
    private LandService landService;

    /**
     * 列表
     */
    @ApiOperation("列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "name", value = "耕地名称", paramType = "query", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "status", value = "耕地状态", paramType = "query", required = true, dataType = "int", dataTypeClass = Integer.class),
    })
    public Result<PageUtils<LandDTO>> list(@RequestParam Map<String, Object> params) {
        PageUtils<LandDTO> page = landService.queryPage(params);
        return Result.OK(SUCCESS_MSG, page);
    }

    @ApiOperation("详情")
    @GetMapping("/get/{id}")
    public Result<LandDTO> get(@ApiParam(value = "id", example = "1", required = true) @PathVariable("id") Long id) {
        LandDTO info = landService.getInfo(id);
        return Result.OK(SUCCESS_MSG, info);
    }


}
