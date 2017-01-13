package com.github.yf_library.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

public class MD5Util
{

	public static String toMD5(String s)
	{
		if (StringUtils.isEmpty(s) || StringUtils.isBlank(s))
		{
			return null;
		}

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try
		{
			byte[] btInput = s.getBytes("UTF-8");
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = 16;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			String result = new String(str);
			return result;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public static String getHashCode(Object object) throws IOException
	{
		if (object == null)
			return "";

		String ss = null;
		ObjectOutputStream s = null;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		try
		{
			s = new ObjectOutputStream(bo);
			s.writeObject(object);
			s.flush();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (s != null)
			{
				s.close();
				s = null;
			}
		}
		ss = toMD5(bo.toString());
		return ss;
	}

	public final static String sha1(String s)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try
		{
			byte[] btInput = s.getBytes("UTF-8");
			// 鑾峰緱MD5鎽樿绠楁硶鐨� MessageDigest 瀵硅�?
			MessageDigest mdInst = MessageDigest.getInstance("sha-1");
			// 浣跨敤鎸囧畾鐨勫瓧鑺傛洿鏂版憳瑕�?
			mdInst.update(btInput);
			// 鑾峰緱�?�嗘�?
			byte[] md = mdInst.digest();
			// 鎶婂瘑鏂囪浆鎹㈡垚鍗佸叚杩涘埗鐨勫瓧绗︿覆褰㈠紡
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args)
	{
		String str = "appid=wxd930ea5d5a258f4f&auth_code=123456&body=test&device_info=123&mch_id=1900000109&nonce_str=960f228109051b9969f76c82bde183ac&out_trade_no=1400755861&spbill_create_ip=127.0.0.1&total_fee=1&key=8934e7d15453e97507ef794cf7b0519d";
		System.out.println(MD5Util.toMD5(str));
	}
}
