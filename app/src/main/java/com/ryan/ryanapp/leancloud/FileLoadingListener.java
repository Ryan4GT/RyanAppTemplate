package com.ryan.ryanapp.leancloud;

/**
 * Created by Ryan
 * 类描述 ：
 * 创建时间 ：  2015/1/23 13:13.
 */
public interface FileLoadingListener {

    void onFileLoadingProgress(Integer progress);

    /**
     * 文件上传完毕的回调函数
     * @param succeed true上传成功， false上传失败
     * @param fileUrl 文件上传成功后在LeanCloud服务器上的url地址
     * @param result  上传结果描述
     */
    void onFileLoadingDone(boolean succeed, String fileUrl, String result);

}
