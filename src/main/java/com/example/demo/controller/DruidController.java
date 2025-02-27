package com.example.demo.controller;


import com.example.demo.controller.dto.DruidRequest;
import com.example.demo.service.DruidService;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("count")
@RequiredArgsConstructor
public class DruidController {

    private final DruidService druidService;

    @PostMapping("")
    @Operation(summary = "test", description = "test")
    public ResponseEntity<String> test(@RequestBody DruidRequest dto) {
        return ResponseEntity.ok("string");
    }

    @PostMapping("/ingestion")
    @Operation(summary = "내장된 csv 파일 ingestion")
    public ResponseEntity<String> ingestion() {
        druidService.ingestion();
        return null;
    }

    @GetMapping("/topN")
    @Operation(summary = "해당하는 column의 top N 정보를 출력합니다.")
    public ResponseEntity<JsonNode> topN(@QueryParam("column") String column) {
        JsonNode response = druidService.topN(column);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }



}
