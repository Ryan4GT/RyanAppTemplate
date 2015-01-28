package com.ryan.ryanapp.leancloud;

import android.app.Activity;
import android.app.Application;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.ryan.ryanapp.Constants;
import com.ryan.ryanapp.Utils.LogUtils;
import com.ryan.ryanapp.leancloud.bean.User;

import java.util.UUID;

/**
 * Created by Ryan
 * 类描述 ： 需要使用的LeanCloud的工具类
 * 创建时间 ：  2015/1/23 13:14.
 */
public class LeanCloudUtils {
    protected static final String TAG = "LeanCloudUtils";
    private static final String LEAN_CLOUD_APPID = "0vz1k3qsljkxrei2o7i5j8puzvvwi6azatroz5zckxfzutul";
    private static final String LEAN_CLOUD_APPKEY = "mr2tt50qqd2c0s1jwagnnvrmut344av41eoinq1fo1wxb01o";
    private static final String LEAN_CLOUD_APP_MASTER_KEY = "";

    /**
     * 在应用的Application的onCreate方法中初始化LeanCloud
     * 注：如果您需要使用美国站点，请下载 SSL 证书并拷贝到您的项目 res/raw/ 目录下
     */
    public static final void initLeanCloud(Application application, boolean useUSAServer, Class<? extends AVObject>[] classes) {
        for (int i = 0; i < classes.length; i++) {
            AVObject.registerSubclass(classes[i]);
        }
        AVOSCloud.initialize(application.getApplicationContext(), LEAN_CLOUD_APPID, LEAN_CLOUD_APPKEY);
    }

    /**
     * 在每个Activity的onCreate方法中调用这个方法，跟踪统计应用的打开情况
     */
    public static final void analyse(Activity activity) {
        AVAnalytics.trackAppOpened(activity.getIntent());
    }

    /**
     * 用户注册
     */
    public static void signup(String username, String password, final LoginOrSignupResultListener listener) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {

            @Override
            public void done(AVException avException) {
                if(avException == null) {
                    listener.onLoginOrSignupResult(true, "恭喜你，注册成功!");
                } else {
                    int code = avException.getCode();
                    listener.onLoginOrSignupResult(false, code + " : " + avException.getMessage());
                }
            }
        });
    }

    /**
     * 用户名登录
     */
    public static void login(String username, String password, final LoginOrSignupResultListener listener) {
        User.logInInBackground(username, password, new LogInCallback<AVUser>() {

            @Override
            public void done(AVUser avUser, AVException avException) {
                if(avException == null) {
                    listener.onLoginOrSignupResult(true, "恭喜你，登录成功!");
                } else {
                    int code = avException.getCode();
                    LogUtils.e(TAG,code + " : " + avException.getMessage());
                    listener.onLoginOrSignupResult(false, code + "");
                }
            }
        });
    }

    /**
     * 上传文件
     */
    public static void uploadFile(String sourceFilePath, Constants.FileType fileType, final FileLoadingListener listener) {
        String fileName = "";
        switch (fileType) {
            case AUDIO:
                fileName = UUID.randomUUID().toString().replaceAll("-", "") + Constants.AUDIO_FILE_SUFFIX;
                break;
            case VIDEO:
                fileName = UUID.randomUUID().toString().replaceAll("-", "") + Constants.VIDEO_FILE_SUFFIX;
                break;
            case IMAGE:
                fileName = UUID.randomUUID().toString().replaceAll("-", "") + Constants.IMAGE_FILE_SUFFIX;
                break;
            case TEXT:
                fileName = UUID.randomUUID().toString().replaceAll("-", "") + Constants.TEXT_FILE_SUFFIX;
                break;
        }
        try {
            final AVFile avFile = AVFile.withAbsoluteLocalPath(fileName, sourceFilePath);
            avFile.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException avException) {
                    if(listener != null) {
                        if(avException == null) {
                            String fileUrl = avFile.getUrl();
                            listener.onFileLoadingDone(true, fileUrl, "文件上传成功！");
                        } else {
                            listener.onFileLoadingDone(false, "", "文件上传失败！");
                            LogUtils.e(TAG, avException.getMessage());
                        }
                    }
                }
            }, new ProgressCallback() {
                @Override
                public void done(Integer integer) {
                    if(listener != null) {
                        listener.onFileLoadingProgress(integer);
                    }
                }
            });
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage());
            if(listener != null) {
                listener.onFileLoadingDone(false, "", "文件上传失败！");
                LogUtils.e(TAG, e.getMessage());
            }
        }
    }
}
