package com.ryan.ryanapp;


public final class Constants {

    /**
     * 是否是测试环境
     */
    public static final boolean DEBUG = true;
    /**
     * 是否打印日志
     */
    public static final boolean PRINT_LOG = true;

    public static final String IMAGE_FILE_SUFFIX = ".jpg";
    public static final String VIDEO_FILE_SUFFIX = ".mp4";
    public static final String AUDIO_FILE_SUFFIX = ".amr";
    public static final String TEXT_FILE_SUFFIX = ".txt";

    /**
     * 文件类型
     */
    public static enum FileType {
        IMAGE, VIDEO, AUDIO, TEXT
    }

    public static class FRAGMENT_KEY {
        public static final int FRAGMENT_MAIN = 1;
        public static final int FRAGMENT_ORDER = 2;
        public static final int FRAGMENT_MESSAGE = 3;
        public static final int FRAGMENT_ME = 4;

    }


}
