package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public enum Url {

    TASK_URL("http://router:8888/druid/indexer/v1/task"),
    JSON_QUERY_URL("http://router:8888/druid/v2");

    private final String url;
}
