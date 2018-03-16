package com.fengzkframework.basic.service;

import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.utils.JsonUtils;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TEST {

    public static void main(String args[]) throws ExecutionException, InterruptedException {

//        String[] strings = {"a", "ah", "b", "ba", "ab", "ac", "sd", "fd", "ar", "te", "se", "te",
//                "sdr", "gdf", "df", "fg", "gh", "oa", "ah", "qwe", "re", "ty", "ui"};
//        List<String> stringList = new ArrayList<>(Arrays.asList(strings));
//
//        long start=System.currentTimeMillis();
//        ForkJoinPool pool = new ForkJoinPool();
//        forkjointest forkJoinService = new forkjointest(stringList, 20);
//        // 提交可分解的ForkJoinTask任务
//        ForkJoinTask<List<String>> future = pool.submit(forkJoinService);
//        System.out.println(future.get());
//        System.out.print(System.currentTimeMillis()-start);
//        // 关闭线程池
//        pool.shutdown();

//        CountDownLatch doneSignal = new CountDownLatch(100);
//        for(int i=0;i<100;i++)
//        {
//            new runc(doneSignal).run();
//          // doneSignal.await();
//        }
       // getshhylogin();
       // concurrenceTest();
//        String ss="512s";
//        System.out.println(ss.substring(0,3));
       // HashSet
        List<String> data=Collections.synchronizedList(new ArrayList<String>());
    }


    public static void concurrenceTest() {
        /**
         * 模拟高并发情况代码 同时请求
         */
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final CountDownLatch countDownLatch = new CountDownLatch(100); // 相当于计数器，当所有都准备好了，再一起执行，模仿多并发，保证并发量
        final CountDownLatch countDownLatch2 = new CountDownLatch(100); // 保证所有线程执行完了再打印atomicInteger的值
        ExecutorService executorService = Executors.newFixedThreadPool(100);//线程池数量
        List<String> list=new ArrayList<String>();
        try {
            for (int i = 0; i < 100; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            countDownLatch.await(); //一直阻塞当前线程，直到计时器的值为0,保证同时并发
                            //Thread.sleep(3000);
                            //业务代码
                         list.add( getshhylogin());


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        //每个线程增加1000次，每次加1
//                        for (int j = 0; j < 100; j++) {
//                            atomicInteger.incrementAndGet();
//                        }

//                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        System.out.println("当前时间"+df.format(System.currentTimeMillis()));
                       // System.out.println("当前时间"+ Calendar.getInstance().toString());
                       countDownLatch2.countDown();
                    }
                });
                countDownLatch.countDown();
            }

            countDownLatch2.await();// 保证所有线程执行完
            System.out.println("结果数量"+list.size());
            executorService.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static  String getshhylogin()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println("当前时间"+df.format(System.currentTimeMillis()));
        Gson gson=new Gson();
        String url="http://192.168.28.239:8080/xbjhapi/weixin/shhylogin";
        Map<String, String> map=new HashMap<String,String>();
        map.put("data","CMCaJm1nr9A9YCuP7kKzQb3jX5aOdUSMETSQqA+tm9km6iG2jCubHLMRaIkqfCkgXY5ObpdtnRivGIjZMaVcV8+s6vlOtGyoFHBAg18qAaY\\u003d");
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String jsonstr= JsonUtils.hashMap2Json(map);
        System.out.println(df.format(System.currentTimeMillis())+"请求："+url+"[boby:]"+jsonstr);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonstr, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResultData aa=  restTemplate.postForObject(url,formEntity, ResultData.class);
        System.out.println(df.format(System.currentTimeMillis())+"返回："+gson.toJson(aa));
        return gson.toJson(aa);
    }
}

class  runc implements Runnable
{
    CountDownLatch countDownLatch;
public runc(CountDownLatch countDownLatch)
{
    this.countDownLatch=countDownLatch;
}
    @Override
    public void run() {
        //countDownLatch.countDown();
        countDownLatch.countDown();
        try {
                 // Thread.sleep(3000);
            countDownLatch.await();
                   } catch (InterruptedException e) {
                           e.printStackTrace();
                   }

        System.out.print("当前时间"+System.currentTimeMillis());
              // System.out.println("[" + mThreadName + "] end!");
    }
}
