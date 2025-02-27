package com.example.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "Druid Query DTO with dynamic additional properties")
public class DruidRequest {

    @Schema(description = "Query type for Druid", example = "groupBy")
    private final String queryType;

    @Schema(description = "Data source name", example = "csv-to-datasource-per-day")
    private final String dataSource;

    @Schema(description = "Granularity setting", example = "all")
    private final String granularity;

    @Schema(description = "List of dimensions for grouping")
    private final List<String> dimensions;

    @Schema(description = "Additional dynamic properties")
    private final Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}