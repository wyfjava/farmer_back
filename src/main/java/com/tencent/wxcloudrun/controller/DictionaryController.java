package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.constant.Constant;
import com.tencent.wxcloudrun.dto.*;
import com.tencent.wxcloudrun.exception.MyForbiddenException;
import com.tencent.wxcloudrun.model.DictionaryEntity;
import com.tencent.wxcloudrun.service.DictionaryService;
import com.tencent.wxcloudrun.utils.ConvertUtils;
import com.tencent.wxcloudrun.utils.PageUtils;
import com.tencent.wxcloudrun.utils.RequestUtil;
import com.tencent.wxcloudrun.utils.Result;
import io.swagger.annotations.*;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



/**
 * 字段管理
 *
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 18:20:43
 */
@Api(tags = "a_字典管理")
@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 列表
     */
    @ApiOperation("列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int",dataTypeClass = Integer.class) ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "app", value = "系统标识", paramType = "query",  dataType="string") ,
    })
    public Result<PageUtils<DictionaryListDTO>> list(@RequestParam Map<String, Object> params){
        PageUtils<DictionaryListDTO> page = dictionaryService.queryPage(params);

        return Result.OK("page", page);
    }


    /**
     * 信息,包含子集
     */
    @ApiOperation("信息")
    @GetMapping("/get/{objectCode}")
    public Result<DictionaryWholeDTO> get(@PathVariable("objectCode") String objectCode){
        DictionaryWholeDTO dto = dictionaryService.getByObjectCodeByWhole(objectCode);

        Result<DictionaryWholeDTO> result = new Result<>();
        result.setData(dto);
        return result;
    }

    /**
     * 根据父级查询所有子集数据
     */
    @ApiOperation("根据父级查询所有子集数据")
    @GetMapping("/getSubset/{parent}")
    public Result<List<DictionaryListDTO>> getSubset(@PathVariable("parent") String parent){
        List<DictionaryListDTO> list = dictionaryService.getSubset(parent);

        return Result.OK("list", list);
    }



    /**
     * 一次性保存字典集
     */
    @ApiOperation("一次性保存字典集")
    @PostMapping("/wholeAdd")
    public Result wholeAdd(@ApiParam(value = "请求体", required = true) @RequestBody DictionaryWholeAddDTO dictionary){

        return dictionaryService.saveWholeAdd(dictionary);
    }
    /**
     * 根据父级Id,名字模糊查询子集
     */
    @ApiOperation("模糊查询子集数据")
    @PostMapping("/parentVague")
    public Result<List<DictionaryAddDTO>> parentVague(@ApiParam(value = "请求体", required = true) @RequestBody DictionaryParentVagueDTO dictionary){

        return dictionaryService.parentVague(dictionary);
    }



    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/add")
    public Result add(@ApiParam(value = "请求体", required = true) @RequestBody DictionaryAddDTO dictionary){
        String app = RequestUtil.getRequest().getHeader("app");
        if (StringUtil.isBlank(app)){
            throw new MyForbiddenException("没有获取到app值");
        }
        DictionaryEntity entity = ConvertUtils.sourceToTarget(dictionary, DictionaryEntity.class);
        entity.setApp(app);
		dictionaryService.save(entity);

        return Result.OK();
    }

    /**
     * 修改，包含子集
     */
    @ApiOperation("修改，包含子集")
    @PostMapping("/edit")
    public Result edit(@ApiParam(value = "请求体", required = true) @RequestBody DictionaryWholeDTO dictionary){

        dictionaryService.updateByObjectCodeWhole(dictionary);

        return Result.OK();
    }

    @ApiOperation("查询全部字典的树形结构")
    @GetMapping("/tree")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "app", value = "系统标识", paramType = "query",  dataType="string") ,
    })
    public Result<List<DictionaryDTO>> findTree(@RequestParam Map<String, Object> params){
        List<DictionaryDTO> list = dictionaryService.findTree2Params(params);
        return Result.OK("dicList", list);
    }
    /**
     * 根据标记字段sign查询整个数据结构
     */
    @ApiOperation("根据标记字段sign查询整个数据结构")
    @PostMapping("/getListBySign")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sign", value = "标识", paramType = "query", required = true, dataType="string") ,
            @ApiImplicitParam(name = "orderFlag", value = "排序", paramType = "query",  dataType="string") ,
    })
    public Result getListBySign(@RequestParam Map<String, Object> params) {
        List<DictionaryListDTO> dictionaryList=dictionaryService.getListBySign(params);
        return Result.OK("success", dictionaryList);
    }
    /**
     * 根据参数查询字典信息
     */
    @ApiOperation("根据参数查询字典信息")
    @PostMapping("/queryDictionary")
    public Result queryDictionary(@ApiParam(value = "请求体", required = true) @RequestBody DictionaryWholeDTO dictionary){
        return Result.OK("success",dictionaryService.queryDictionary(dictionary));
    }
    /**
     * 商学苑课程分类-根据标记字段sign查询整个数据结构
     */
    @ApiOperation("商学苑课程分类")
    @GetMapping("/getBusSchoolCategorys")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sign", value = "标识，固定值busSchoolCourseCategory", paramType = "query", required = true, dataType="string") ,
            @ApiImplicitParam(name = "orderFlag", value = "排序:1排序", paramType = "query",  dataType="string") ,
    })
    public Result getBusSchoolCategorys(@RequestParam Map<String, Object> params) {
        List<DictionaryListDTO> dictionaryList=dictionaryService.getListBySign(params);
        return Result.OK("success", dictionaryList);
    }

}
