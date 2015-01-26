package com.ryan.ryanapp.leancloud;

import com.avos.avoscloud.AVUser;

public class User extends AVUser {

    public User() {
    }

    public String getHeadImage() {
        return getString("headImage");
    }

    /**
     * 设置用户图像
     */
    public void setHeadImage(String headImage) {
        put("headImage", headImage);
    }

    public String getDisplayName() {
        return getString("displayName");
    }

    /**
     * 设置显示的名称
     */
    public void setDisplayName(String displayName) {
        put("displayName", displayName);
    }


}
