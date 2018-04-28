package com.example.boot_webflux.functions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 * stream in java 8
 */
public class Streams {
    private enum Status {
        OPEN, CLOSED
    }

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task(Status status, Integer points) {
            this.status = status;
            this.points = points;
        }

        Status getStatus() {
            return status;
        }

        Integer getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }

    public static void main(String[] args) {
        final Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8)
        );

        final long totalPointsOfOpenTasks = tasks.stream()
                .filter(task -> task.getStatus() == Status.OPEN)
                .mapToInt(Task::getPoints)
                .sum();
        System.out.println("Total points: " + totalPointsOfOpenTasks);

        // stream 并行处理
        final double totalPoints = tasks.stream()
                .parallel()
                .map(Task::getPoints).reduce(0, Integer::sum);
        System.out.println("Total points (all tasks): " + totalPoints);
        // 元素分组
        final Map<Status, List<Task>> map = tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);
        // 计算每个 task 权重的平均值
        final Collection<String> result = tasks.stream()
                .mapToInt(Task::getPoints)
                .asLongStream()
                .mapToDouble(value -> value / totalPoints)
                .boxed()
                .mapToLong(value -> (long) (value * 100))
                .mapToObj(value -> value + "%")
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
