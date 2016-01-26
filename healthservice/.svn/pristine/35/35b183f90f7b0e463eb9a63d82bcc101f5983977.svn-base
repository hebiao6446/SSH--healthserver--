package com.health.common;

import java.util.Properties;
/**
 * 获取图片的网络地�?
 * @author Acer
 *
 */
public class PicPath {

	/**
	 * 获取图片路径
	 * @param key 登录密匙
	 * @return 返回路径
	 */
	public String getPicPath(String key) {
		Properties props = new Properties();
		String rt = "";
		try {
			props.load(getClass().getClassLoader().getResourceAsStream(
					"application.properties"));
			rt = props.getProperty(key);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return rt;
	}
}
