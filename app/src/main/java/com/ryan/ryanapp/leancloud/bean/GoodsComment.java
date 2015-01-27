package com.ryan.ryanapp.leancloud.bean;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.ryan.ryanapp.Utils.LogUtils;
/**
 * 作者: Ryan
 * 创建时间:15/1/27 12:55
 * 类描述:
 */
@AVClassName("GoodsComment")
public class GoodsComment extends AVObject {

    private static final String TAG = "GoodsComment";
    public void setCreateTime(long createTime) {
        put("createTime", createTime);
    }

    public long getCreateTime() {
        return getLong("createTime");
    }

    public void setCommentContent(String commentContent){
        put("commentContent", commentContent);
    }

    public String getCommentContent(){
        return getString("commentContent");
    }

    public void setGoodsID(String goodsID){
        put("goodsID", goodsID);
    }

    public String getGoodsID(){
        return getString("goodsID");
    }

    public void setReviewer(User reviewer){
        put("reviewer", reviewer);
    }

    public User getReviewer(){
        try {
            return  getAVObject("reviewer", User.class);
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage());
            return null;
        }
    }

}

