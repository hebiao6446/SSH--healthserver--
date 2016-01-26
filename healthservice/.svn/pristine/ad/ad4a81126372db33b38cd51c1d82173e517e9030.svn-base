package com.health.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import org.slf4j.LoggerFactory;

public class PushUtils {

	/************************************************
	 * 测试推�?服务器地�?��gateway.sandbox.push.apple.com /2195
	 * 产品推�?服务器地�?��gateway.push.apple.com / 2195
	 ***************************************************/
	private static String host = "17.149.34.66";
	private static int port = 2195;
	private static String p12FilePassword = "minzh2010";
	public static void main(String[] args) throws Exception {

		List<String> deviceTokens = new ArrayList<String>();
		deviceTokens.add("38a187d97985a9a3be364efb5cc88e8aae8e8bec187343a3b145576583be01bb");
		

		String content = "正式推�?测试";// push的内�?
		String p12File = "d:/cert.p12";// 这里是一�?p12格式的文件路径，�?��去apple官网申请�?��
		push2More(p12File, deviceTokens,content);// 群组推�?
		//push2One(p12File, deviceToken2,content);// 单个推�?

	}

	/**
	 * 向单个iphone手机推�?消息.
	 * 
	 * @param deviceToken
	 *            iphone手机获取的token
	 * @param p12File
	 *            .p12格式的文件路�?
	 * @param p12Pass
	 *            .p12格式的文件密�?
	 * @param customDictionarys
	 *            CustomDictionary字典�?
	 * @param content
	 *            推�?内容
	 */
	public static void push2One(String p12File,
			String deviceToken, String content) {
		try {
			PayLoad payLoad = new PayLoad();
			payLoad.addAlert(content);// push的内�?
			payLoad.addBadge(1);// 应用图标上小红圈上的数�?
			payLoad.addSound("default");// 铃音

			// 添加字典
			payLoad.addCustomDictionary("url", "www.baidu.com");
			PushNotificationManager pushManager = PushNotificationManager
					.getInstance();
			pushManager.addDevice("iphone", deviceToken);

			// 链接到APNs
			pushManager.initializeConnection(host, port, p12File, p12FilePassword,
					SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);

			// �?��推�?
			Device client = pushManager.getDevice("iphone");
			pushManager.sendNotification(client, payLoad);
			// 断开链接
			pushManager.stopConnection();
			pushManager.removeDevice("iphone");

		} catch (Exception e) {

		}
	}

	/**
	 * 向iphone群组推�?消息.
	 * 
	 * @param deviceTokens
	 *            iphone手机获取的token
	 * @param p12File
	 *            .p12格式的文件路�?
	 * @param p12Pass
	 *            .p12格式的文件密�?
	 * @param customDictionarys
	 *            CustomDictionary字典
	 * @param content
	 *            推�?内容
	 */
	public static void push2More(String p12File, 
			List<String> deviceTokens, String content) {
		PushNotificationManager pushManager=null;
		try {
			PayLoad payLoad = new PayLoad();
			payLoad.addAlert(content);// push的内�?
			payLoad.addBadge(1);// 应用图标上小红圈上的数�?
			payLoad.addSound("default");// 铃音

			// 添加字典
			payLoad.addCustomDictionary("url", "www.baidu.com");
			 pushManager = PushNotificationManager
					.getInstance();

			// 链接到APNs
			pushManager.initializeConnection(host, port, p12File, p12FilePassword,
					SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);

			// �?��循环推�?
			for (int i = 0; i < deviceTokens.size(); i++) {
				pushManager.addDevice("iphone" + i, deviceTokens.get(i));
				Device client = pushManager.getDevice("iphone" + i);
				pushManager.sendNotification(client, payLoad);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		} finally {
			if (pushManager != null) {
				// 断开链接
				try {
					pushManager.stopConnection();
					for (int i = 0; i < deviceTokens.size(); i++) {
						pushManager.removeDevice("iphone" + i);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
