package com.ryan.ryanapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ryan.ryanapp.Constants;
import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.LogUtils;
import com.ryan.ryanapp.Utils.StringUtil;
import com.ryan.ryanapp.leancloud.FileLoadingListener;
import com.ryan.ryanapp.leancloud.LeanCloudUtils;
import com.ryan.ryanapp.leancloud.UniversualImageLoaderUtils;
import com.ryan.ryanapp.leancloud.bean.User;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMe extends FragmentBase {

    private ImageView headImageView;
    private TextView nicknameView;
    private String headImageLocalPath;
    private TextView exposeGoodsView;
    private TextView exposedGoodsView;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentBase.
     */
    public static FragmentMe newInstance(Map<String, String> params) {
        FragmentMe fragmentMe = new FragmentMe();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentMe.setArguments(args);
        return fragmentMe;
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        toolbar.setTitle(R.string.fragment_me_title);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRootView = inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        headImageView = (ImageView) fragmentRootView.findViewById(R.id.headImageView);
        nicknameView = (TextView) fragmentRootView.findViewById(R.id.nicknameView);
        exposedGoodsView = (TextView) fragmentRootView.findViewById(R.id.exposedGoodsView);
        exposeGoodsView = (TextView) fragmentRootView.findViewById(R.id.exposeGoodsView);
        headImageView.setOnClickListener(this);
        exposeGoodsView.setOnClickListener(this);
        exposedGoodsView.setOnClickListener(this);
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case FragmentImageLibraryBrowser.ACTIVITY_REQUEST_CODE_IMAGE_LIBRAY_BROWSER:
                    String imageUri = data.getStringExtra(FragmentImageLibraryBrowser.ACTIVITY_RESULT_EXTRA_KEY);
                    headImageLocalPath = FragmentImageLibraryBrowser.cropImage(this, imageUri);
                    if(StringUtil.isEmpty(headImageLocalPath)) {
                        Toast.makeText(getActivity(), "您的手机暂不支裁切操作", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case FragmentImageLibraryBrowser.ACTIVITY_REQUEST_CODE_IMAGE_CROP:
                    File headImageFile = new File(headImageLocalPath);
                    if(headImageFile.exists() && headImageFile.length() > 0) {
                        LogUtils.e(TAG, " 裁切的图片………… " + headImageLocalPath);
                        ImageLoader.getInstance().displayImage(Uri.fromFile(new File(headImageLocalPath)).toString(), headImageView, UniversualImageLoaderUtils.getDisplayImageOptions());
                        LeanCloudUtils.uploadFile(headImageLocalPath, Constants.FileType.IMAGE, new FileLoadingListener() {
                            @Override public void onFileLoadingProgress(Integer progress) {

                            }
                            @Override public void onFileLoadingDone(boolean succeed, String fileUrl, String result) {
                                if(succeed) {
                                    final String headImage = AVUser.getCurrentUser(User.class).getHeadImage();
                                    AVUser.getCurrentUser(User.class).setHeadImage(fileUrl);
                                    AVUser.getCurrentUser(User.class).saveInBackground(new SaveCallback() {
                                        @Override public void done(AVException e) {
                                            if(e == null) {
                                                Toast.makeText(getActivity(), "图像设置成功", Toast.LENGTH_SHORT).show();
                                            } else {
                                                AVUser.getCurrentUser(User.class).setHeadImage(headImage);
                                                Toast.makeText(getActivity(), "图像设置失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(getActivity(), "图像上传失败", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                    break;
            }
        } else {
            switch (requestCode) {
                case FragmentImageLibraryBrowser.ACTIVITY_REQUEST_CODE_IMAGE_LIBRAY_BROWSER:
                case FragmentImageLibraryBrowser.ACTIVITY_REQUEST_CODE_IMAGE_CROP:
                    Toast.makeText(getActivity(), "图像设置失败", Toast.LENGTH_SHORT).show();
                    headImageLocalPath = null;
                    break;
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        String headImage = AVUser.getCurrentUser(User.class).getHeadImage();
        if(!StringUtil.isEmpty(headImage)) {
            ImageLoader.getInstance().displayImage(headImage, headImageView, UniversualImageLoaderUtils.getDisplayImageOptions());
        }
        nicknameView.setText(AVUser.getCurrentUser(User.class).getUsername());
    }

    @Override public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.headImageView:
                FragmentImageLibraryBrowser.chooseImageFromLibrary(this);
                break;
            case R.id.exposedGoodsView:
                ActivityMeHome.setBeingLoadedFragment(FragmentExposedGoods.newInstance(new HashMap<String, String>()));
                startActivity(new Intent(getActivity(), ActivityMeHome.class));
                break;
            case R.id.exposeGoodsView:
                ActivityMeHome.setBeingLoadedFragment(FragmentExposeGoods.newInstance(new HashMap<String, String>()));
                startActivity(new Intent(getActivity(), ActivityMeHome.class));
                break;

        }
    }
}
