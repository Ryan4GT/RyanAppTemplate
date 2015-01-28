package com.ryan.ryanapp.leancloud.bean;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.ryan.ryanapp.Utils.LogUtils;
import com.ryan.ryanapp.Utils.StringUtil;
/**
 * 作者: Ryan
 * 创建时间:15/1/27 11:18
 * 类描述:商品
 */
@AVClassName("Goods")
public class Goods extends AVObject {

    private static final String TAG = "Goods";
    public void setGoodsName(String goodsName) {
        put("goodsName", goodsName);
    }

    public String getGoodsName() {
        return getString("goodsName");
    }

    public void setDisplayImages(String displayImages) {
        put("displayImages", displayImages);
    }

    public String getDisplayImages() {
        return getString("displayImages");
    }

    public void setPrice(float price) {
        put("price", price + "");
    }

    public float getPrice() {

        try {
            float price = Float.valueOf(getString("price")); return price;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setGoodsOwner(User goodsOwner){
        put("goodsOwner", goodsOwner);
    }

    public User getGoodsOwner(){//商品拥有者
        try {
            return getAVObject("goodsOwner", User.class);
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage());
            return null;
        }
    }




}
