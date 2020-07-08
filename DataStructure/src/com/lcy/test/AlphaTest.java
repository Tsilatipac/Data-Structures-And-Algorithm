package com.lcy.test;

import com.lcy.sort.QuickSort;
import com.lcy.sort.RadixSort;
import com.lcy.tree.HeapSort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class AlphaTest {
    static int model = 0;
    int[] arr;
    static {
        model = 1; //1 为性能测试 0 为功能测试
    }
    Instant start;
    @Before
    public void generateArray(){
        if (model == 0) {
            arr = new int[10];
        }else{
            arr = new int[2000_0000];
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        if (model == 0) {
            System.out.println("排序前的数组");
            System.out.println(Arrays.toString(arr));
        }
        start = Instant.now();
    }

    @Test
    public void testVariables() {
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    public void testSort(){
        HeapSort.heapSort(arr);
//        QuickSort.quickSort2(arr,0,arr.length-1);
//        RadixSort.radixSort(arr);
    }

    @After
    public void testPrintArray(){
        if (model == 0) {
            System.out.println("排序后的数组");
            System.out.println(Arrays.toString(arr));
        }
        System.out.println(Duration.between(start, Instant.now()).toMillis());
    }
}
