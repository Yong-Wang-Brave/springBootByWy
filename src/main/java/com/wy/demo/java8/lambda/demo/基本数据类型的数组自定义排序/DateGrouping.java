package com.wy.demo.java8.lambda.demo.基本数据类型的数组自定义排序;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DateGrouping {
    static class DateRange {
        private LocalDate start;
        private LocalDate end;

        public DateRange(LocalDate start, LocalDate end) {
            this.start = start;
            this.end = end;
        }

        public LocalDate getStart() {
            return start;
        }

        public LocalDate getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "DateRange{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<DateRange> dateList = Arrays.asList(
                new DateRange(LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 2)),
                new DateRange(LocalDate.of(2023, 7, 2), LocalDate.of(2023, 7, 3)),
                new DateRange(LocalDate.of(2023, 7, 3), LocalDate.of(2023, 7, 4)),
                new DateRange(LocalDate.of(2023, 7, 4), LocalDate.of(2023, 7, 5)),
                new DateRange(LocalDate.of(2023, 7, 5), LocalDate.of(2023, 7, 6)),
                new DateRange(LocalDate.of(2023, 7, 6), LocalDate.of(2023, 7, 7)),
                new DateRange(LocalDate.of(2023, 7, 6), LocalDate.of(2023, 7, 7)),
                new DateRange(LocalDate.of(2023, 7, 7), LocalDate.of(2023, 7, 8)),
                new DateRange(LocalDate.of(2023, 7, 8), LocalDate.of(2023, 7, 9)),
                new DateRange(LocalDate.of(2023, 7, 8), LocalDate.of(2023, 7, 9)),
                new DateRange(LocalDate.of(2023, 7, 9), LocalDate.of(2023, 7, 10))
        );

        Map<List<LocalDate>, List<DateRange>> dateMap = dateList.stream()
                .collect(Collectors.groupingBy(dateRange -> Arrays.asList(dateRange.getStart(), dateRange.getEnd())));

        System.out.println(dateMap);
    }
}