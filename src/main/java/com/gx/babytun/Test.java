package com.gx.babytun;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 谷鑫 G x
 * @Classname Test
 * @Describe:
 * @date 2018/11/14 15:34
 */
public class Test {

    public static void main(String[] args) {
        HashMap<Integer,String> map  = new HashMap<Integer,String>();
        map.put(1,"guxin");
        System.out.println(map.get(1).hashCode());
    }
}
