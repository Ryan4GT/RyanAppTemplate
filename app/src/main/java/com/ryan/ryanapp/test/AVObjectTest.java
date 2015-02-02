package com.ryan.ryanapp.test;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.ryan.ryanapp.Utils.LogUtils;
import com.ryan.ryanapp.leancloud.bean.TestAVObject;

import junit.framework.Test;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
/**
 * 作者: Ryan
 * 创建时间:15/1/27 11:29
 * 类描述:
 */
public class AVObjectTest extends TestCase {

    private static String TAG = "AVObjectTest";
    public void testAVObject(){
        AVObject avObject = new AVObject("Test");
        String objectId = avObject.getObjectId();
        System.out.print(objectId + "============================================================================");

    }

    public void testTestAVObject(){
        final TestAVObject testAVObject = new TestAVObject();
        List<String> listDatas = new ArrayList<String>();
        for(int i=0; i< 5; i++){
            listDatas.add("Item " + i);
        }
        testAVObject.setListData(listDatas);
        testAVObject.saveInBackground(new SaveCallback() {
            @Override public void done(AVException e) {
                if(e == null){
                    LogUtils.i(TAG, "List数据保存成功…………");
                    AVQuery<TestAVObject> avQuery = AVObject.getQuery(TestAVObject.class);
                    avQuery.whereEqualTo("objectid", testAVObject.getObjectId());
                    avQuery.findInBackground(new FindCallback<TestAVObject>() {
                        @Override public void done(List<TestAVObject> testAVObjects, AVException e) {
                            if(e == null){
                                LogUtils.i(TAG, "查询成功： " + testAVObject.getListData());
                            }else{
                                LogUtils.e(TAG, "查询失败！！！");
                            }
                        }
                    });
                }else{
                    LogUtils.i(TAG, "List数据保存失败…………");
                }
            }
        });
    }


}
