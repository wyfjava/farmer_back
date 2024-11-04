package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.constant.Constant;
import com.tencent.wxcloudrun.dto.SkuDTO;
import com.tencent.wxcloudrun.dto.SpuDTO;
import com.tencent.wxcloudrun.service.SkuService;
import com.tencent.wxcloudrun.service.SpuService;
import com.tencent.wxcloudrun.utils.PageUtils;
import com.tencent.wxcloudrun.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import static com.tencent.wxcloudrun.constant.Constant.SUCCESS_MSG;


/**
 * sku
 *
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 18:20:43
 */
@RestController
@RequestMapping("/api/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;
    @Autowired
    private SpuService spuService;

    /**
     * 列表
     */
    @ApiOperation("列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "spuName", value = "SPU名称", paramType = "query", required = true, dataType = "String", dataTypeClass = String.class),
    })
    public Result<PageUtils<SkuDTO>> list(@RequestParam Map<String, Object> params) {
        PageUtils<SkuDTO> page = skuService.queryPage(params);
        return Result.OK(SUCCESS_MSG, page);
    }

    /**
     * 列表
     */
    @ApiOperation("SPU列表")
    @GetMapping("/spuList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "title", value = "SPU名称", paramType = "query", required = true, dataType = "String", dataTypeClass = String.class),
    })
    public Result<PageUtils<SpuDTO>> spuList(@RequestParam Map<String, Object> params) {
        PageUtils<SpuDTO> page = spuService.queryPage(params);
        return Result.OK(SUCCESS_MSG, page);
    }

    @ApiOperation("SPU详情")
    @GetMapping("/spuDetail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "spuId", value = "spuId", paramType = "query", required = true, dataType = "String", dataTypeClass = String.class),
    })
    public Result<SpuDTO> spuDetail(@RequestParam Map<String, Object> params) {
        SpuDTO spuDTO = spuService.spuDetail(params);
        return Result.OK(SUCCESS_MSG, spuDTO);
    }


}
