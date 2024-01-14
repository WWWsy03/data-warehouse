package com.ssw331.warehousebackend.Neo4jDTO.serialization;

import java.util.List;

public class ResultResponse {
    public static Result<Object> success(Object data, List<Long> modelTime, List<String> modelLog) {
        return new Result<>().setResult(true, modelTime, modelLog, data);
    }

    public static Result<Object> failure(List<Long> modelTime, List<String> modelLog) {
        return new Result<>().setResult(false, modelTime, modelLog);
    }
}
