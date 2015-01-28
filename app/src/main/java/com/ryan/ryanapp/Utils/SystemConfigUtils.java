package com.ryan.ryanapp.Utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.avos.avoscloud.AVUser;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.ryan.ryanapp.RyanApp;
import com.ryan.ryanapp.leancloud.bean.User;
/**
 * 作者: Ryan
 * 创建时间:15/1/27 16:07
 * 类描述:
 */
public class SystemConfigUtils {

    //    public static SystemConfigUtils instance;
    //    private SystemConfigUtils(){};
    //    public static final SystemConfigUtils getInstance(){
    //        if(instance == null){
    //            synchronized (SystemConfigUtils.class){
    //                instance = new SystemConfigUtils();
    //            }
    //        }
    //        return instance;
    //    }

    public static void saveUser(User user) {

        if(user != null) {
            String username = user.getUsername();
            String password = user.getString("password");
            if(!StringUtil.isEmpty(username) && !StringUtil.isEmpty(password)) {
                SharedPreferences sp = RyanApp.instance.getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.commit();
            }
        }

    }

    public static User getUserSaved() {
        SharedPreferences sp = RyanApp.instance.getSharedPreferences("user", Context.MODE_PRIVATE);
        String username = sp.getString("username", "");
        String password = sp.getString("password", "");
        User user = new User();
        user.setUsername(username);
        user.put("password", password);
        return user;
    }



}
