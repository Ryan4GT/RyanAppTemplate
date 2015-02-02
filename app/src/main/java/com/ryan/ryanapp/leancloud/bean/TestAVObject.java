package com.ryan.ryanapp.leancloud.bean;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.ryan.ryanapp.Utils.LogUtils;
import com.ryan.ryanapp.Utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 作者: Ryan
 * 创建时间:15/1/30 16:47
 * 类描述:
 */
@AVClassName("TestAVObject")
public class TestAVObject extends AVObject {


    public static final String TAG = "TestAVObject";

    public void setListData(List<String> listData){
        put("listData", listData);
    }


    public List<String> getListData(){
        return getList("listData");
    }


    public void setMapData(Map<String, String> mapData){
        put("mapData", mapData);
    }

    public Map<String, String> getMapData(){
        return getMap("mapData");
    }



    /**保存List数据*/
    public static void testSaveListData(){
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


    /**保存Map数据*/
    public static void testSaveMapData(){
        final TestAVObject testAVObject = new TestAVObject();
        final Map<String, String> mapData = new HashMap<String, String>();
        for(int i= 0; i< 4; i++){
           mapData.put("" + i , "item " + i);
        }
        testAVObject.setMapData(mapData);
        testAVObject.saveInBackground(new SaveCallback() {
            @Override public void done(AVException e) {
                if(e == null){
                    LogUtils.i(TAG, "Map集合保存成功 ");
                    final AVQuery<TestAVObject> avQuery = AVObject.getQuery(TestAVObject.class);
                    avQuery.whereEqualTo("objectid", testAVObject.getObjectId());
                    avQuery.findInBackground(new FindCallback<TestAVObject>() {
                        @Override public void done(List<TestAVObject> testAVObjects, AVException e) {
                            if(e == null){
                                LogUtils.i(TAG, "查询成功： " );
                            }else{
                                LogUtils.e(TAG, "查询失败！！！");
                            }
                        }
                    });

                }else{
                    LogUtils.e(TAG, "Map集合保存失败");
                }
            }
        });
    }


}
