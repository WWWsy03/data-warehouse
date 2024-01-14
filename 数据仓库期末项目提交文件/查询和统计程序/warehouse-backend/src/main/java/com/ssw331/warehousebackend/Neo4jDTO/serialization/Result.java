package com.ssw331.warehousebackend.Neo4jDTO.serialization;

import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
public class Result<T> implements Serializable {
    private UUID id;
    private boolean success;
    private T data;
    private final List<String> modelName = new ArrayList<>(Arrays.asList("关系型-mysql", "关系型-hive", "图数据库"));
    private List<Long> modelTime;
    private List<String> modelLog;

    public Result<T> setResult(boolean success, List<Long> modelTime, List<String> modelLog) {
        this.id = UUID.randomUUID();
        this.success = success;
        this.modelTime = modelTime;
        this.modelLog = modelLog;
        return this;
    }

    public Result<T> setResult(boolean success, List<Long> modelTime, List<String> modelLog, T data) {
        this.id = UUID.randomUUID();
        this.success = success;
        this.data = data;
        this.modelTime = modelTime;
        this.modelLog = modelLog;
        return this;
    }
}
