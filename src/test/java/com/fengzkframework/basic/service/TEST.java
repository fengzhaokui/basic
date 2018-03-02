package com.fengzkframework.basic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class TEST {

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        String[] strings = {"a", "ah", "b", "ba", "ab", "ac", "sd", "fd", "ar", "te", "se", "te",
                "sdr", "gdf", "df", "fg", "gh", "oa", "ah", "qwe", "re", "ty", "ui"};
        List<String> stringList = new ArrayList<>(Arrays.asList(strings));

        long start=System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        forkjointest forkJoinService = new forkjointest(stringList, 20);
        // 提交可分解的ForkJoinTask任务
        ForkJoinTask<List<String>> future = pool.submit(forkJoinService);
        System.out.println(future.get());
        System.out.print(System.currentTimeMillis()-start);
        // 关闭线程池
        pool.shutdown();


    }
}
