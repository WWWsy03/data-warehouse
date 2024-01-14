package com.ssw331.warehousebackend.controller;

import com.ssw331.warehousebackend.Neo4jDTO.serialization.Result;
import com.ssw331.warehousebackend.Neo4jDTO.serialization.ResultResponse;
import com.ssw331.warehousebackend.service.DataQualityService;
import com.ssw331.warehousebackend.service.DataTraceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Tag(name = "溯源查询")
@RestController
@RequestMapping("/trace")
public class DataTraceController {
    @Autowired
    DataTraceService dataTraceService;
    @Autowired
    private void setDataQualityService(DataTraceService dataTraceService) {
        this.dataTraceService=dataTraceService;
    }

    @Operation(summary = "数据预处理阶段非电影数据")
    @RequestMapping(value = "/non-movie", method = RequestMethod.GET)
    public Result<Object> NullStatistic() {
        int data = dataTraceService.getNonMovieCounts();

        Map<String, Integer> dataMap = new HashMap<>();
        dataMap.put("nonMovieDataCount", data);

        List<Long> modelTimes = Arrays.asList(0L, 0L, 0L);
        List<String> modelLogs = Arrays.asList("无", "无", "无");

        return ResultResponse.success(dataMap, modelTimes, modelLogs);
    }


    @Operation(summary = "哈利波特相关统计统计")
    @RequestMapping(value = "/harry-potter", method = RequestMethod.GET)
    public Result<Object> HarryPotterStatistic() {

        Map<String, Integer> data = new HashMap<>();

        data.put("harryPotterMovieCount", dataTraceService.getNumberOfHarryMovies());
        data.put("formatCount", dataTraceService.getNumberOfHarryVersions());
        data.put("mergedWebPageCount", data.get("formatCount"));
        data.put("webPageCountForFirstEpisode", dataTraceService.getNumberOfMergedWebpages());

        List<Long> modelTimes = Arrays.asList(0L, 0L, 0L);
        List<String> modelLogs = Arrays.asList("无", "无", "无");
        return ResultResponse.success(data, modelTimes, modelLogs);
    }
}

