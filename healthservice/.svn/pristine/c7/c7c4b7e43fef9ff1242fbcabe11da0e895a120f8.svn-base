package com.health.common;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class HttpGetMsg {
	public static void main(String[] args) throws Exception {
		//用户已扫�?未扫描二维码列表
		//String data =HttpGetMsg.postUrl("http://127.0.0.1:8089/cubeinterface/user!findQrcodeList.action", "userId=1");
		
		//记录用户扫描二维码操�?
		//String data =HttpGetMsg.postUrl("http://www.aaarj.com:8080/cubeinterface/user!saveQrcodeScaned.action", "userId=1&qrcodeId=2");
		
		//二维码扫描排名前3
		//String data =HttpGetMsg.postUrl("http://127.0.0.1:8089/cubeinterface/user!findUserQrcode3Order.action", "");
		
		
		String data =HttpGetMsg.postUrl("http://127.0.0.1:8080/healthinterface/user!findFirstpageKeyword.action", "keyword=如&rows=10&page=1");
				
	
		System.out.println(data);
		
	/*	SimpleDateFormat fmt = new SimpleDateFormat("MM月dd日");
		System.out.println(fmt.format(new Date()));
		System.out.println((new Lunar(Calendar.getInstance())).toString());*/
		
	}

	public static String postUrl(String remoteUrl, String param) {
		URL url = null;
		HttpURLConnection httpurlconnection = null;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(remoteUrl);

			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setRequestMethod("POST");

			httpurlconnection.getOutputStream().write(param.getBytes());
			httpurlconnection.getOutputStream().close();
			InputStream in = httpurlconnection.getInputStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) != -1) {
				sb.append(new String(b, 0, i, "UTF-8"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			sb.append("0");

		} finally {
			if (httpurlconnection != null)
				httpurlconnection.disconnect();

		}
		return sb.toString();
	}

	public static String getUrl(String remoteUrl, String param) {
		URL url = null;
		HttpURLConnection httpurlconnection = null;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(remoteUrl);

			httpurlconnection = (HttpURLConnection) url.openConnection();
//			httpurlconnection.setDoOutput(true);
//			httpurlconnection.setRequestMethod("GET");
//
//			httpurlconnection.getOutputStream().write(param.getBytes());
//			httpurlconnection.getOutputStream().close();
			InputStream in = httpurlconnection.getInputStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) != -1) {
				sb.append(new String(b, 0, i, "UTF-8"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			sb.append("0");
			

		} finally {
			if (httpurlconnection != null)
				httpurlconnection.disconnect();

		}
		return sb.toString();
	}

}
