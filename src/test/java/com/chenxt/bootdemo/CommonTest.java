package com.chenxt.bootdemo;

import lombok.NonNull;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 通常测试
 *
 * @author chenxt
 * @date 2020/07/17
 */
public class CommonTest {
    @Test
    public void test() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Timestamp timestamp = Timestamp.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        System.out.println(localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        System.out.println(date.getTime());
        System.out.println(timestamp.getTime());
        System.out.println(localDateTime);
        System.out.println(date);
        System.out.println(timestamp);
//        Object res = supplierTest(()->null);sdfasdasdfasdfasdfasdfasdfasdasdff
//        Object res2 = supplierTest(()->new ArrayList<>());
//         consumerTest(a->{
//             System.out.println(1);
//         });
//        System.out.println(1);
    }

    private <T> List<T> supplierTest(Supplier<@NonNull List<T>> supplier) {
        return supplier.get();
    }

    private <T> void consumerTest(Consumer<@NonNull List<T>> supplier) {
        supplier.accept(null);
    }

    @Test
    public void test2() {
        boolean a = true;
        Assert.assertTrue(a);
        System.out.println(1);
    }
}
