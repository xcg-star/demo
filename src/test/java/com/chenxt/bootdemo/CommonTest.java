package com.chenxt.bootdemo;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

/**
 * demo
 *
 * @author chenxt
 * @date 2020/04/11
 */
public class CommonTest {
    @Test
    public void test() {

        System.out.println(Pattern.matches("^[1][3-9][0-9]{9}$","16812378424"));
    }
}
