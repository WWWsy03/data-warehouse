package com.ssw331.warehousebackend.controller;

import com.ssw331.warehousebackend.Neo4jDTO.serialization.Result;
import com.ssw331.warehousebackend.Neo4jDTO.serialization.ResultResponse;
import com.ssw331.warehousebackend.service.DataQualityService;
import com.ssw331.warehousebackend.service.DirectorActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Tag(name = "数据质量")
@RestController
@RequestMapping("/quality")
public class DataQualityController {
    @Autowired
    DataQualityService dataQualityService;
    @Autowired
    private void setDataQualityService(DataQualityService dataQualityService) {
        this.dataQualityService=dataQualityService;
    }

    @Operation(summary = "数据预处理阶段空值统计")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<Object> actorByDirector() {
        Map<String,Object> data=dataQualityService.getDataStatistics();
        List<Long> modelTimes = Arrays.asList(0L, 0L, 0L);
        List<String> modelLogs = Arrays.asList("无", "无", "无");
        return ResultResponse.success(data, modelTimes, modelLogs);
    }
}
