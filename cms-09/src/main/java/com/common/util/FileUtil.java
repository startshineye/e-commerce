package com.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author gzz_gzz@163.com
 * @date 2015年1月26日下午2:01:13
 * @功能描述: 文件读写辅助类
 */
public class FileUtil {
	private static Log logger = LogFactory.getLog(FileUtil.class);
	// 递归删除文件夹
	public static void deleteFile(File file) {
		if (file.exists()) {// 判断文件是否存在
			if (file.isFile()) {// 判断是否是文件
				file.delete();// 删除文件
			} else if (file.isDirectory()) {// 否则如果它是一个目录
				File[] files = file.listFiles();// 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) {// 遍历目录下所有的文件
					deleteFile(files[i]);// 把每个文件用这个方法进行迭代
				}
				file.delete();// 删除文件夹
			}
		} else {
			logger.debug("所删除的文件不存在");
		}
	}

	/**
	 * @功能描述: 以工作空间编码写文本文件
	 */
	public static void writeFile(String path, String context) throws IOException {
		createDir(path);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
		writer.write(context);
		writer.close();
	}

	/**
	 * @功能描述:创建文件夹
	 */
	public static void createDir(String path) throws IOException {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
	}

}
