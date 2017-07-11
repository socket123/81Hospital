package com.chusu.platform.hdfs;

import java.io.InputStream;

public interface HDFSService {
	/**
	 * @param listFile
	 * @param path
	 * @return 返回自动文件存储的全路径
	 */
	
	public byte[] download(String source) ;
	
	public String upload(String path,String filename,InputStream inputStream) ;
	
	public void delete(String SourceFileName) ;
}