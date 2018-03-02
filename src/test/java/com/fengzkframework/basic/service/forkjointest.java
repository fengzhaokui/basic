package com.fengzkframework.basic.service;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class forkjointest extends RecursiveTask<List<String>> {
    private static forkjointest forkJoinTest;
    private int threshold;  //阈值
    private List<String> list; //待拆分List

    public forkjointest(List<String> list, int threshold) {
        this.list = list;
        this.threshold = threshold;
    }
    @Override
        protected List<String> compute() {
            // 当end与start之间的差小于阈值时，开始进行实际筛选
            if (list.size() < threshold) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return list.parallelStream().filter(s -> s.contains("a")).collect(Collectors.toList());

            } else {
                // 如果当end与start之间的差大于阈值时,将大任务分解成两个小任务。
                int middle = list.size() / 2;
                List<String> leftList = list.subList(0, middle);
                List<String> rightList = list.subList(middle+1, list.size());
                forkjointest left = new forkjointest(leftList, threshold);
                forkjointest right = new forkjointest(rightList, threshold);
                // 并行执行两个“小任务”
                left.fork();
                right.fork();

                // 把两个“小任务”的结果合并起来
                List<String> join = left.join();
                join.addAll(right.join());
                return join;
            }
        }

//    public static forkjointest<List<String>> getInstance(List<String> list, int threshold) {
//        if (forkJoinTest == null) {
//            synchronized (forkjointest.class) {
//                if (forkJoinTest == null) {
//                    forkJoinTest = new forkjointest(list, threshold);
//                }
//            }
//        }
//        return forkJoinTest;
//    }
//    ForkJoinPool
}
