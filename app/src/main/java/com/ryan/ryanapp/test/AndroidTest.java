package com.ryan.ryanapp.test;

import com.ryan.ryanapp.Utils.StringUtil;

import junit.framework.TestCase;

/**
 * Created by Ryan
 * 类描述 ：
 * 创建时间 ：  2015/1/23 17:20.
 */
public class AndroidTest extends TestCase{

    public void testStringUtil(){
       boolean isEmpty =  StringUtil.isEmpty("Hello!");
        System.out.println(isEmpty);
        assertEquals(true, isEmpty);
    }

}
