import cn.hutool.http.HttpUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.LongStream;

public class AlphaTest {
    int[] arr = new int[10];
    @Before
    public void generateArray(){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));
    }
    //@Test
    public void testFile(){
        String result = HttpUtil.get("https://wenku.baidu.com/view/cfc3adfbf705cc1755270913.html");
        System.out.println(result);
    }

    //@Test
    public void testCalculate() {
        Instant start = Instant.now();
        LongStream.rangeClosed(0, 1000_0000_0000L).parallel().reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("耗费时间为"+ Duration.between(start,end).toMillis());
    }

    @Test
    public void testSort(){
    }

    @After
    public void testPrintArray(){
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));
    }
}
