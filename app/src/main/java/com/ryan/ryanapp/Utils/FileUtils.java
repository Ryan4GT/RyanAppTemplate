package com.ryan.ryanapp.Utils;

import java.io.*;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.ryan.ryanapp.Constants;

/**
 * 
 * @author yt
 *	
 *为了方便应用文件统一管理，以及图片不对外开放，文件的创建做以下规范：
 * 
 * 一、 从服务器获取的非文本文件和应用生成的文本文件：
 * 
 * 1.用户在本地缓存的图片都必须放在缓存目录中，缓存目录可能在SD卡中，也可能在应用程序缓存区域中；
 *   具体的位置由ImageLoader的DiskCache决定；
 * 2.所有的图片都应该缓存在  缓存目录/img目录下；
 * 	  所有的视频都应该缓存在  缓存目录/audio目录下；
 *   所有的图片都应该缓存在  缓存目录/video目录下；
 *   对应的目录调用FileUtils类中对应的方法即可
 * 3.在本地缓存目录中的文件不要再创建子目录，文件名要用 Md5FileNameGenerator类处理 
 * 	 
 * 二、 本应用生成的非文本文件要存放在临时文件夹中			
 * 
 *
 * 创建日期 ： 2014年12月12日 下午5:15:09
 */
public final class FileUtils {

	private static final String					TAG						= "FileUtils";
	private static final Md5FileNameGenerator	MD5_FILE_NAME_GENERATOR	= new Md5FileNameGenerator();
	private static final String					VIDEO_DIR_NAME			= "3ww44pc1hvx5zmpbzqii0fd2e";				//video     
	private static final String					AUDIO_DIR_NAME			= "5c9jhqz75q8o182tkxejn6huq";				//audio     
	private static final String					TEXT_DIR_NAME			= "1p5s5mf7k95eagl44epn0uz0x";				//text
	private static final String					TEMP_DIR_NAME			= MD5_FILE_NAME_GENERATOR.generate("temp"); //temp

	/**获取应用图片缓存目录*/
	public static final String getImageCacheDir() {

		return ImageLoader.getInstance().getDiskCache().getDirectory().getAbsolutePath();
	}

	/**获取应用音频缓存目录*/
	public static final String getAudioCacheDir() {

		File cacheDir = ImageLoader.getInstance().getDiskCache().getDirectory();
		File f = null;
		if (Constants.DEBUG) {
			f = new File(cacheDir + "/audio");
		} else {
			f = new File(cacheDir + "/" + AUDIO_DIR_NAME);
		}
		if (!f.exists()) {
			f.mkdirs();
		}
		return f.getAbsolutePath();
	}

	/**获取应用视频缓存目录*/
	public static final String getVideoCacheDir() {

        File cacheDir = ImageLoader.getInstance().getDiskCache().getDirectory();
		File f = null;
		if (Constants.DEBUG) {
			f = new File(cacheDir + "/video");
		} else {
			f = new File(cacheDir + "/" + VIDEO_DIR_NAME);
		}
		if (!f.exists()) {
			f.mkdirs();
		}
		return f.getAbsolutePath();
	}

	/**获取应用文本文件缓存目录*/
	public static final String getTextCacheDir() {

        File cacheDir = ImageLoader.getInstance().getDiskCache().getDirectory();
		File f = null;
		if (Constants.DEBUG) {
			f = new File(cacheDir + "/text");
		} else {
			f = new File(cacheDir + "/" + TEXT_DIR_NAME);
		}
		if (!f.exists()) {
			f.mkdirs();
		}
		return f.getAbsolutePath();
	}

	/**临时缓存文件夹，自己拍的照片和视频*/
	public static final String getTempCacheDir() {

        File cacheDir = ImageLoader.getInstance().getDiskCache().getDirectory();
		File f = null;
		if (Constants.DEBUG) {
			f = new File(cacheDir + "/temp");
		} else {
			f = new File(cacheDir + "/" + TEMP_DIR_NAME);
		}
		if (!f.exists()) {
			f.mkdirs();
		}
		return f.getAbsolutePath();
	}

	/**在本地缓存文件的时候生成文件名*/
	public static final String generateLocalFilePath(Constants.FileType fileType, String fileName) {

		switch (fileType) {
			case AUDIO:
				return getAudioCacheDir() + File.separator + MD5_FILE_NAME_GENERATOR.generate(fileName);
			case IMAGE:
				return getImageCacheDir() + File.separator + MD5_FILE_NAME_GENERATOR.generate(fileName);
			case VIDEO:
				return getVideoCacheDir() + File.separator + MD5_FILE_NAME_GENERATOR.generate(fileName);
			case TEXT:
				return getTextCacheDir() + File.separator + MD5_FILE_NAME_GENERATOR.generate(fileName);
			default:
				return null;
		}
	}

	/**
	 * 本地是否已经存在此文件
	 * @param fileType	文件类型
	 * @param fileName	文件名（userID + "/" + 文件名）
	 * @return 如果文件存在返回此文件对象，如果文件不存在返回null
	 */
	public static final File getLocalFile(Constants.FileType fileType, String fileName) {

		String filePath = "";
		switch (fileType) {
			case AUDIO:
				filePath = getAudioCacheDir() + File.separator + MD5_FILE_NAME_GENERATOR.generate(fileName);
				break;
			case IMAGE:
				filePath = getAudioCacheDir() + File.separator + MD5_FILE_NAME_GENERATOR.generate(fileName);
				break;
			case VIDEO:
				filePath = getAudioCacheDir() + File.separator + MD5_FILE_NAME_GENERATOR.generate(fileName);
				break;
			case TEXT:
				filePath = getAudioCacheDir() + File.separator + MD5_FILE_NAME_GENERATOR.generate(fileName);
				break;
		}
		File f = new File(filePath);
		if (f.exists()) {
			return f;
		}
		return null;
	}

	/**
	 * 创建临时文件的文件名； 文件名由时间戳组成 + 文件后缀组成
	 * @param fileType	文件类型
	 * @return	
	 */
	public static final String generateTempFilePath(Constants.FileType fileType) {

		String filePath = "";
		switch (fileType) {
			case AUDIO:
				filePath = getTempCacheDir() + File.separator + System.currentTimeMillis() + Constants.AUDIO_FILE_SUFFIX;
				break;
			case IMAGE:
				filePath = getTempCacheDir() + File.separator + System.currentTimeMillis() + Constants.IMAGE_FILE_SUFFIX;
				break;
			case VIDEO:
				filePath = getTempCacheDir() + File.separator + System.currentTimeMillis() + Constants.VIDEO_FILE_SUFFIX;
				break;
			case TEXT:
				filePath = getTempCacheDir() + File.separator + System.currentTimeMillis() + Constants.TEXT_FILE_SUFFIX;
				break;
		}
		return filePath;
	}

	/**
	 * 根据文件完整路径获取文件名
	 * @param tempFilePath 临时文件的完整文件名
	 * @return	如果获取到文件名，返回文件名；否则返回null
	 */
	public static final String getTempFileName(String tempFilePath) {

		String fileName = tempFilePath.substring(tempFilePath.lastIndexOf(File.separator) + 1);
		return fileName;
	}

	/**
	 * 根据文件获取文件名
	 * @param tempFile 临时文件对象
	 * @return	如果获取到文件名，返回文件名；否则返回null
	 */
	public static final String getTempFileName(File tempFile) {

		if (tempFile != null)
			return getTempFileName(tempFile.getAbsolutePath());
		else
			return null;
	}

	/**
	 * 复制文件
	 * @param srcPath 源文件路径
	 * @param destPath	目标文件路径
	 * @param deleteSrc	是否删除源文件
	 * @return	文件拷贝成功返回true，否则返回false
	 */
	public static boolean copyFile(String srcPath, String destPath, boolean deleteSrc) {

		File srcFile = new File(srcPath);
		File destFile = new File(destPath);
		return copyFile(srcFile, destFile, deleteSrc);
	}

	/**
	 * 复制文件
	 * @param srcFile	源文件
	 * @param destFile	目标文件
	 * @param deleteSrc	是否删除源文件
	 * @return	文件拷贝成功返回true，否则返回false
	 */
	public static boolean copyFile(File srcFile, File destFile, boolean deleteSrc) {

		if (!srcFile.exists() || !srcFile.isFile()) {
			return false;
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = in.read(buffer)) > 0) {
				out.write(buffer, 0, i);
				out.flush();
			}
			if (deleteSrc) {
				srcFile.delete();
			}
		} catch (Exception e) {
			LogUtils.e(e);
			return false;
		} finally {
			close(out);
			close(in);
		}
		return true;
	}

	/***
	 * 用字符串生成文件
	 * @return	true生成文件成功， false生成文件失败
	 */
	public static final boolean string2File(String content, String targetFile) {

		return bytesArray2File(content.getBytes(), targetFile);
	}

	/**
	 * 字节数组转文件
	 * @param content	字节内容
	 * @param filePath	目标文件路径
	 * @return
	 */
	public static final boolean bytesArray2File(byte[] content, String filePath) {

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filePath);
			fos.write(content);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			LogUtils.e(TAG, e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 文件转字符串
	 * @param sourceFilePath
	 * @return
	 */
	public static final String file2String(String sourceFilePath) {

		byte[] fileData = null;
		FileInputStream fileInputStream = null;
		File file = new File(sourceFilePath);
		try {
			fileInputStream = new FileInputStream(sourceFilePath);
			int length = (int) file.length();
			fileData = new byte[length];
			int readCount = 0; // 已经成功读取的字节的个数
			while (readCount < length) {
				readCount += fileInputStream.read(fileData, readCount, length - readCount);
			}
			fileInputStream.close();
			return new String(fileData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 文件转字符串
	 * @param sourceFilePath
	 * @return
	 */
	public static final String stream2String(InputStream is) {

		byte[] buffer = new byte[1024];
		int len = -1;
		StringBuffer sb = new StringBuffer();
		try {
			while ((len = is.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, len));
			}
			is.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取MD5命名类
	 * @return
	 */
	public static final Md5FileNameGenerator getMD5FileNameGenerator() {

		return MD5_FILE_NAME_GENERATOR;
	}


	/** 关闭流 */
	public static boolean close(Closeable io) {

		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				LogUtils.e(e);
			}
		}
		return true;
	}

}
