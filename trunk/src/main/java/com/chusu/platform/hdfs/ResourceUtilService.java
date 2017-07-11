package com.chusu.platform.hdfs;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public interface ResourceUtilService {
	
	void upload(final InputStream fis, final String storepath);
	
	void upload(final String fstr, final String storepath);
	
	void delete(String storepath);
	
	void multidelete(String[] storepaths);
	
	byte[] resize(InputStream is, int w, int h) throws IOException;
	
	String getStorePath(String storename);
	
	String getFileType(String filename);

	InputStream getFileStream(String storepath);
	
	byte[] createJpgByFontAndAlign(String s, String align, int x, int y, int width, int height, Color bgcolor, Color fontcolor, Font font);
	
	public byte[] downloadFile(String SourceFileName) throws NoSuchElementException, IllegalStateException, Exception;
	
	public void deleteFile(String targetFileName);
}
