package com.example.demo.nativeQuery;

import in.zapr.druid.druidry.aggregator.DruidAggregator;
import in.zapr.druid.druidry.aggregator.LongSumAggregator;
import in.zapr.druid.druidry.dataSource.TableDataSource;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.dimension.SimpleDimension;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.granularity.PredefinedGranularity;
import in.zapr.druid.druidry.granularity.SimpleGranularity;
import in.zapr.druid.druidry.query.aggregation.DruidTopNQuery;
import in.zapr.druid.druidry.query.aggregation.DruidTopNQuery.DruidTopNQueryBuilder;
import in.zapr.druid.druidry.query.config.Interval;
import in.zapr.druid.druidry.topNMetric.SimpleMetric;
import in.zapr.druid.druidry.topNMetric.TopNMetric;
import java.util.Arrays;
import java.util.Collections;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;


public class DruidQueryGenerator {

    private static DruidTopNQueryBuilder topNTemplate() {
        Granularity granularity = new SimpleGranularity(PredefinedGranularity.ALL);
        DateTime startTime = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2024, 12, 31, 0, 0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);
        return DruidTopNQuery.builder()
                .dataSource(new TableDataSource("amazon-india-sales-report"))
                .granularity(granularity)
                .intervals(Collections.singletonList(interval));
    }

    public static DruidTopNQuery topNQuery(String dimension) {

        DruidAggregator aggregator1 = new LongSumAggregator("count", "count");
        DruidDimension dim = new SimpleDimension(dimension);
        TopNMetric metric = new SimpleMetric("count");
        return topNTemplate()
                .dimension(dim)
                .topNMetric(metric)
                .threshold(10)
                .aggregators(Arrays.asList(aggregator1))
                .build();
    }

}
