package com.ryan.ryanapp.test;
import com.avos.avoscloud.AVObject;

import junit.framework.TestCase;
/**
 * 作者: Ryan
 * 创建时间:15/1/27 11:29
 * 类描述:
 */
public class AVObjectTest extends TestCase {

    public void testAVObject(){
        AVObject avObject = new AVObject("Test");
        String objectId = avObject.getObjectId();
        System.out.print(objectId + "============================================================================");

    }

}
