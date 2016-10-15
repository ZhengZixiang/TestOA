package me.zzx.oa.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
	
	@SuppressWarnings("resource")
	/**
	 * 获得文件的二进制数组形式
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] getByteArray(File file) throws IOException {
		long length = file.length(); 
		FileInputStream fis = new FileInputStream(file);
		if(length > Integer.MAX_VALUE) {
			throw new SystemException("error.file.tobig");
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream((int) length);
		int bufferSize = 1024;
		byte [] b = new byte[bufferSize];
		int i = 0;
		while((i = fis.read(b)) != -1) {
			baos.write(b, 0, i);
		}
		return baos.toByteArray();
	}
}
