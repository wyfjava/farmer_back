package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.constant.Constant;
import com.tencent.wxcloudrun.dto.CityDTO;
import com.tencent.wxcloudrun.dto.CityInfoDTO;
import com.tencent.wxcloudrun.service.CityService;
import com.tencent.wxcloudrun.utils.PageUtils;
import com.tencent.wxcloudrun.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



/**
 * 省市县
 *
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 18:14:24
 */
@Api(tags = "省市县")
@RestController
@RequestMapping("/api/city")
public class CityController {
    @Autowired
    private CityService cityService;

    /**
     * 列表
     */
    @ApiOperation("列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int",dataTypeClass = Integer.class) ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int",dataTypeClass = Integer.class)
    })
    public Result<PageUtils<CityDTO>> list(@RequestParam Map<String, Object> params){
        PageUtils<CityDTO> page = cityService.queryPage(params);

        return Result.OK("page", page);
    }


//    /**
//     * 信息
//     */
//    @AccessLog("省市县信息")
//    @ApiOperation("信息")
//    @GetMapping("/get/{id}")
//    public Result get(@PathVariable("id") Long id){
//		CityEntity city = cityService.getById(id);
//
//        return Result.OK("city", city);
//    }

//    /**
//     * 保存
//     */
//    @AccessLog("省市县保存")
//    @ApiOperation("保存")
//    @PostMapping("/add")
//    public Result add(@ApiParam(value = "请求体", required = true) @RequestBody CityDTO city){
//        CityEntity entity = ConvertUtils.sourceToTarget(city, CityEntity.class);
//
//		cityService.save(entity);
//
//        return Result.OK();
//    }

//    /**
//     * 修改
//     */
//    @AccessLog("省市县修改")
//    @ApiOperation("修改")
//    @PostMapping("/edit")
//    public Result edit(@ApiParam(value = "请求体", required = true) @RequestBody CityDTO city){
//        CityEntity entity = ConvertUtils.sourceToTarget(city, CityEntity.class);
//
//        cityService.updateById(entity);
//
//        return Result.OK();
//    }

//    /**
//     * 删除
//     */
//    @AccessLog("省市县删除")
//    @ApiOperation("删除")
//    @PostMapping("/delete")
//    public Result delete(@RequestBody BaseDTO base){
//		cityService.removeById(base.getObjectCode());
//
//        return Result.OK();
//    }


//    @GetMapping("/export")
//    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
//        List<CityDTO> list = cityService.list(params);
//
//        ExcelUtils.exportExcelToTarget(response, null, list, CityExcel.class);
//    }


    /**
     * 查询所有的省
     */
    @ApiOperation("查询所有的省")
    @GetMapping("/findAllProvince")
    public Result<List<CityDTO>> findAllProvince(){
        List<CityDTO> list = cityService.findAllProvince();

        return Result.OK("cityList", list);
    }

    /**
     * 根据当前节点查询下级节点数据
     */
    @ApiOperation("根据当前节点查询下级节点数据")
    @PostMapping("/findAllNode")
    public Result<List<CityDTO>> findAllNode(@RequestBody CityDTO cityDTO){
        List<CityDTO> list = cityService.findAllNode(cityDTO.getParentCode());

        return Result.OK("cityList", list);
    }

    @ApiOperation("查询省市县树形结构(china)")
    @GetMapping("/tree")
    public Result<List<CityDTO>> findTree(){
        List<CityDTO> list = cityService.findTree();
        return Result.OK("cityList", list);
    }

    @ApiOperation("查询省市县树形结构(全部)")
    @GetMapping("/treeAll")
    public Result<List<CityDTO>> findTreeAll() {
        List<CityDTO> list = cityService.findTreeAll();
        return Result.OK("cityList", list);
    }

    /**
     * 列表
     */
    @ApiOperation("根据编码查名称")
    @PostMapping("/getCityInfo")
    public Result<List<CityDTO>> getCityInfo(@RequestBody CityDTO cityDTO){
        List<CityDTO> list = cityService.getCityInfo(cityDTO);
        return Result.OK("success", list);
    }

    @ApiOperation("标签专用-查询省市县树形结构信息(china)")
    @PostMapping("/queryCityTree")
    public Result<List<CityInfoDTO>> queryCityTree(){
        List<CityInfoDTO> list = cityService.findCityTree();
        return Result.OK("cityList", list);
    }

}
