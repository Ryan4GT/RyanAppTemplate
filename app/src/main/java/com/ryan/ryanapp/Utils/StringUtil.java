package com.ryan.ryanapp.Utils;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class StringUtil {

    private static final String TAG = "StringUtil";

    public static final boolean isEmpty(String msg) {

        return msg == null || msg.trim().length() == 0;
    }

    /**
     * 使用字符串生成一个utf8字节数组
     *
     * @param pArray 字符串
     * @return utf8字节数组或null
     */
    public static byte[] getBytesUtf8(String pArray) {

        byte[] strByte = null;
        try {
            strByte = pArray.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strByte;
    }

    /**
     * 使用字节数组生成一个utf8字符串
     *
     * @param strByte 字节数组
     * @return utf8字符串或null
     */
    public static String newStringUtf8(byte[] strByte) {

        String result = null;
        try {
            result = new String(strByte, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入字符串
     *
     * @param content
     * @param file
     */
    public static void writeString(String content, File file) {
        Writer writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
