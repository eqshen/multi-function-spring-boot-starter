package com.eqshen.func.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 滑动窗口限流简单实现
 * @author eqshen
 * @description
 * @date 2021/4/22
 */
public class SlideWindowRateLimiter {
    /**
     * 窗口中时间片的个数
     */
    private int windowSize;

    /**
     * 整个窗口容纳请求的阈值
     */
    private int threshold;

    /**
     * 存放时间片的数组，循环塞值
     */
    private AtomicInteger[] timeSlices;

    /**
     * 每个时间片持续的时长
     */
    private int timeSliceDurationMs;

    private long lastAddTimestamp;

    private long beginTimestamp;

    /**
     *
     * @param windowSize 时间窗口长度（时间片数量）
     * @param timeSliceDurationMs 每个时间片的时间跨度
     * @param threshold 整个时间窗口中，可承载资源的总数量
     */
    public SlideWindowRateLimiter(int windowSize,int timeSliceDurationMs,int threshold){
        this.windowSize = windowSize;
        this.timeSliceDurationMs = timeSliceDurationMs;
        this.threshold = threshold;

        reset();
    }

    private void reset(){
        //
        beginTimestamp = System.currentTimeMillis();
        int timeSliceSize = windowSize * 2;
        AtomicInteger[] localTimeSlices = new AtomicInteger[timeSliceSize];
        for (int i = 0; i < timeSliceSize; i++) {
            localTimeSlices[i] = new AtomicInteger(0);
        }
        timeSlices = localTimeSlices;
    }

    private int locateIndex(){
        long now = System.currentTimeMillis();
        if (now - lastAddTimestamp > timeSliceDurationMs * windowSize) {
            reset();
        }
        return (int) (((now - beginTimestamp) / timeSliceDurationMs) % (windowSize*2));
    }

    public void showWindow(){
        for (int i = 0; i < timeSlices.length; i++) {
            this.printDebugLog("{},",false,timeSlices[i].get());
        }
        System.out.println();
    }

    public boolean access(){
        int index = locateIndex();
        //清除 index - windowSize之前的分片数据
        clearHistorySlice(index);

        int sum = 0;

        for (int i = 1; i <= windowSize; i++) {
            int id = index - i;
            if(id<0){
                id = windowSize*2 + id;
            }
            sum+= timeSlices[id].get();
        }
        if(sum >= threshold){

            return false;
        }

        sum += timeSlices[index].addAndGet(1);

        lastAddTimestamp = System.currentTimeMillis();
        //printDebugLog("access sum:{}",true,sum);
        return sum <= threshold;
    }

    private void clearHistorySlice(int currentIndex){
        //0 1 2  3  4 5 6 7 8 9
        int index = currentIndex - windowSize;
        for (int i = 0; i < windowSize; i++) {
            if(index < 0){
                index = windowSize*2 + index;
                //-1 ==>  8 - 1 = 7
            }
            timeSlices[index--].set(0);

        }

        while (currentIndex - windowSize > 0){
            timeSlices[currentIndex - windowSize].set(0);
            currentIndex--;
        }
    }

    private void printDebugLog(String msg,boolean changeLine,Object ...args){
        Pattern p = Pattern.compile("\\{\\}");
        Matcher m = p.matcher(msg);
        StringBuffer sb = new StringBuffer();
        int index = 0;
        while (m.find()){
            m.appendReplacement(sb, String.valueOf(args[index++]));
        }
        m.appendTail(sb);

        if(changeLine){
            System.out.println(sb.toString());
        }else{
            System.out.print(sb.toString());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SlideWindowRateLimiter slideWindowRateLimiter = new SlideWindowRateLimiter(5,1000,5);
        for (int i = 0; i < 100; i++) {
            TimeUnit.MILLISECONDS.sleep(800);
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +": " + slideWindowRateLimiter.access());
            slideWindowRateLimiter.showWindow();
            System.out.println("=======");
        }
    }
}
