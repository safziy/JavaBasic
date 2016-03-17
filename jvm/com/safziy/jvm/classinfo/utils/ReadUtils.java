package com.safziy.jvm.classinfo.utils;

import java.io.IOException;
import java.io.InputStream;

public class ReadUtils {
	
	public static byte[] readBytes(InputStream is, int len) throws IOException {
		byte[] b = new byte[len];
		is.read(b);
		return b;
	}
	
	public static byte readByte(InputStream is, int len) throws IOException {
		return (byte)readLong(is,len);
	}
	
	public static short readShort(InputStream is, int len) throws IOException {
		return (short)readLong(is,len);
	}

	public static int readInt(InputStream is, int len) throws IOException {
		return (int)readLong(is,len);
	}

	public static long readLong(InputStream is, int len) throws IOException {
		byte[] bytes = readBytes(is, len);
		long value = 0;
		for (int i = 0; i < bytes.length; i++) {
			value = (value << 8) + (bytes[i] & 0xFF);
		}
		return value;
	}
	
	public static float readFloat(InputStream is, int len) throws IOException {
		return Float.intBitsToFloat(readInt(is, len));
	}

	public static double readDouble(InputStream is, int len) throws IOException {
		return Double.longBitsToDouble(readLong(is, len));
	}

	public static String readHex(InputStream is, int len) throws IOException {
		byte[] bytes = readBytes(is, len);
		return bytes2Hex(bytes);
	}
	
	private static String bytes2Hex(byte[] bytes){
		StringBuilder sb = new StringBuilder();
		if (bytes == null || bytes.length == 0) {
			return sb.toString();
		}
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				sb.append(0);
			}
			sb.append(hv);
		}
		return sb.toString();
	}
	
	public static String readString(InputStream is, int len) throws IOException {
		return new String(readBytes(is, len));
	}

}
