package com.example.demo.service;

import com.example.demo.enums.Json;
import com.example.demo.enums.Url;
import com.example.demo.nativeQuery.DruidQueryGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DruidService {

    private final WebClientService webClientService;

    public void ingestion() {
        try {
            webClientService.post(
                    Url.TASK_URL.getUrl(),
                    header -> {},
                    Json.DATA_INGESTION_JSON.getJsonString()
            );
        } catch (RuntimeException e) {
            log.warn("ingestion 실패", e);
            throw e;
        }
    }

    public JsonNode topN(String column) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(
                    webClientService.post(
                            Url.JSON_QUERY_URL.getUrl(),
                            header -> {},
                            DruidQueryGenerator.topNQuery(column)
            ));
            return root.get(0).get("result");
        } catch (JsonProcessingException e) {
            log.warn("json 파싱 실패", e);
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            log.warn("topN 조회 실패", e);
            throw e;
        }
    }
}
