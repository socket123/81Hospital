package com.chusu.platform.hdfs;

import java.io.IOException;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.RemoteIterator;

/**
 * HDFS工具类 Author: 菩提树下的杨过(http://yjmyzz.cnblogs.com) Since: 2015-05-21
 */
public interface HDFSUtil {

	/**
	 * 判断路径是否存在
	 *
	 * @param conf
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public boolean exists(String configname, String path);

	public boolean exists(String path);

	/**
	 * 创建文件
	 *
	 * @param conf
	 * @param filePath
	 * @param contents
	 * @throws IOException
	 */
	public void createFile(String filePath, byte[] contents);

	public void createFile(String configname, String filePath, byte[] contents);

	/**
	 * 创建文件
	 *
	 * @param conf
	 * @param filePath
	 * @param fileContent
	 * @throws IOException
	 */
	public void createFile(String configname, String filePath, String fileContent);

	public void createFile(String filePath, String fileContent);

	/**
	 * @param conf
	 * @param localFilePath
	 * @param remoteFilePath
	 * @throws IOException
	 */
	public void copyFromLocalFile(String configname, String localFilePath, String remoteFilePath);

	public void copyFromLocalFile(String localFilePath, String remoteFilePath);

	/**
	 * 删除目录或文件
	 *
	 * @param conf
	 * @param remoteFilePath
	 * @param recursive
	 * @return
	 * @throws IOException
	 */
	public boolean deleteFile(String configname, String remoteFilePath, boolean recursive);

	public boolean deleteFile(String remoteFilePath, boolean recursive);

	/**
	 * 删除目录或文件(如果有子目录,则级联删除)
	 *
	 * @param conf
	 * @param remoteFilePath
	 * @return
	 * @throws IOException
	 */
	public boolean deleteFile(String configname, String remoteFilePath);

	public boolean deleteFile(String remoteFilePath);

	/**
	 * 文件重命名
	 *
	 * @param conf
	 * @param oldFileName
	 * @param newFileName
	 * @return
	 * @throws IOException
	 */
	public boolean renameFile(String configname, String oldFileName, String newFileName);

	public boolean renameFile(String oldFileName, String newFileName);

	/**
	 * 创建目录
	 *
	 * @param conf
	 * @param dirName
	 * @return
	 * @throws IOException
	 */
	public boolean createDirectory(String configname, String dirName);

	public boolean createDirectory(String dirName);

	/**
	 * 列出指定路径下的所有文件(不包含目录)
	 *
	 * @param conf
	 * @param basePath
	 * @param recursive
	 */
	public RemoteIterator<LocatedFileStatus> listFiles(String configname, String basePath, boolean recursive);

	public RemoteIterator<LocatedFileStatus> listFiles(String basePath, boolean recursive);

	/**
	 * 列出指定路径下的文件（非递归）
	 *
	 * @param conf
	 * @param basePath
	 * @return
	 * @throws IOException
	 */
	public RemoteIterator<LocatedFileStatus> listFiles(String configname, String basePath);

	public RemoteIterator<LocatedFileStatus> listFiles(String basePath);

	/**
	 * 列出指定目录下的文件\子目录信息（非递归）
	 *
	 * @param conf
	 * @param dirPath
	 * @return
	 * @throws IOException
	 */
	public FileStatus[] listStatus(String configname, String dirPath);

	public FileStatus[] listStatus(String dirPath);

	/**
	 * 读取文件内容
	 *
	 * @param conf
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public String readFile(String configname, String filePath);

	public String readFile(String filePath);

	public FileSystem getHDFS();

	public FileSystem getHDFS(String configname);
}
