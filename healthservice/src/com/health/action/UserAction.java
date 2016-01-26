package com.health.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import javax.print.attribute.standard.Copies;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import javax.xml.ws.Action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.catalina.startup.UserConfig;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import cn.eavic.framework.web.BaseAction;
import com.health.common.Commonparam;
import com.health.common.MatrixToImageWriter;
import com.health.common.PingYin;
import com.health.common.PushThread;
import com.health.entity.Aboutus;
import com.health.entity.Bootpage;
import com.health.entity.Firstpage;
import com.health.entity.Knowledge;
import com.health.entity.Message;
import com.health.entity.Onlinefirst;
import com.health.entity.Onlinetest;
import com.health.entity.Recommend;
import com.health.entity.Supports;
import com.health.entity.User;
import com.health.entity.Version;
import com.health.service.UserManager;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import com.json.BaseBean;
import com.json.JsonAuthority;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@Results({ @Result(name = BaseAction.NONE, location = "userAction.action", type = "redirect") })
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserManager userManager;
	private String rows, page;
	private String ids;
	private String account, password;
	private String passWord;
	private Long userId;
	private Long id;
	private String aboutusId;
	private String supportsId;
	private String versionId;
	private String table;
	private String title;
	private String content;
	private String creatTime;
	private String type;
	private String name;
	private String phone;
	private String email;//反馈邮箱
	private String address;
	private String keyword;
	private String homeId;
	private File homefile;
	private String homefileFileName;
	private String homeproduct;
	private String urlNum;// 版本内容-------活版本号
	private String urlpath;// 网址
	private String onlinetestId;// 在线测试id
	private String topic;// 题目
	private String testa;// A
	private String testb;// B
	private String testc;// C
	private String testd;// D
	private Integer correctAnswer;// 正确答案
	private String introduction;
	private String source;
	private String videoUrl;
	private File knowledgefile;
	private String knowledgefileFileName;
	private File knowledgefile1;
	private String knowledgefile1FileName;
	private File knowledgefile2;
	private String knowledgefile2FileName;
	private String knowledgeId;
	private File firstpagefile;
	private String firstpagefileFileName;
	private File firstpagefile1;
	private String firstpagefile1FileName;
	private File firstpagefile2;
	private String firstpagefile2FileName;
	private String firstpageId;

	private String onlinefirstId;
	private File  onlinefirstfile;
	private String onlinefirstfileFileName;
	
	private String  recommendId;
	private File    recommendfile;
	private String  recommendfileFileName;
	private File    recommendfile1;
	private String  recommendfile1FileName;
	
	
	
	
	
	
	/**
	 * 设置置顶
	 * @throws Exception
	 */
	
	private String firstpage;

	public void topFirstpageSetting() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			JSONArray array = JSONArray.fromObject(firstpage);
			int rt =0;
			for(int i=0;i < array.size();i++){
				JSONObject obj = array.getJSONObject(i);
				Long count = userManager.findFirstpageCount(obj.getLong("id"));
				if(count <3){
					userManager.updateFirstpage(obj.getLong("id"));
					rt++;
				}
			}		
			if(rt == array.size())
				bean.setStatus(200);
			else{
				bean.setMsg("已经超过最大设置数量!");
				bean.setStatus(400);
			}
			
		} catch (Exception e) {
			bean.setStatus(400);
			bean.setMsg(e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}
	
	
	
	
	

	/**
	 * 取消置顶
	 * @throws Exception
	 */
	public void topFirstpageCancel() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			
			int rt = userManager.topFirstpageCancel(ids);
			if(rt >0)
				bean.setStatus(200);
			else{
				bean.setStatus(400);
			    bean.setMsg("取消置顶失败!");
			}
			
		} catch (Exception e) {
			bean.setStatus(400);
		    bean.setMsg(e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}
	
	
	
	
	
	
	
	/**
	 * 引导页图片
	 */

	public void loadBootpageInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Bootpage> list = userManager.loadBootpageInfo();
			bean.setStatus(200);
			bean.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 关于我们
	 * 
	 */

	public void loadAboutusInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Aboutus> list = userManager.loadAboutusInfo();
			bean.setStatus(200);
			bean.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 技术支持
	 * 
	 */

	public void loadSupportsInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Supports> list = userManager.loadSupportsInfo();
			bean.setStatus(200);
			bean.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 版本更新
	 * 
	 */

	public void loadVersionInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Version> list = userManager.loadVersionInfo();
			bean.setStatus(200);
			bean.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 反馈意见
	 * 
	 */

	public void loadMessageInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Message> list = userManager.loadMessageInfo(Integer.valueOf(rows), Integer.valueOf(page));
			bean.setStatus(200);
			bean.setRows(list);
			bean.setTotal(userManager.loadMessageInfoCount());
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	

	/**
	 * 知识
	 * 
	 */

	public void loadKnowledgeInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Knowledge> list = userManager.loadKnowledgeInfo(
					Integer.valueOf(rows), Integer.valueOf(page));
			bean.setStatus(200);
			bean.setRows(list);
			bean.setTotal(userManager.loadKnowledgeInfoCount());
		} catch (Exception e) {
			bean.setStatus(400);
			bean.setMsg(e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 添加 知识 目标文件夹+最终文件名
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	public void addKnowledge() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		String result = "";

		try {

			String knowledgefile1 = Commonparam
					.makeNewFileName(this.knowledgefileFileName);
			Commonparam.makeFile(request, this.knowledgefile,
					Commonparam.productImage, knowledgefile1);

			String knowledgefile2 = Commonparam
					.makeNewFileName(this.knowledgefile1FileName);
			Commonparam.makeFile(request, this.knowledgefile1,
					Commonparam.productImage, knowledgefile2);
			
		
			
			String spath = "";

			if (this.knowledgefile2 != null) {

				String knowledgefile3 = Commonparam
						.makeNewFileName(this.knowledgefile2FileName);
				Commonparam.makeFile(request, this.knowledgefile2,
						Commonparam.productImage, knowledgefile3);
				spath = Commonparam.productImage + knowledgefile3;

			}	
		

			Knowledge com = new Knowledge();

			com.setTitle(title);
			com.setIntroduction(introduction);
			com.setContent(content);
			com.setSource(source);
			com.setVideoUrl(videoUrl);
			com.setImgInfo(Commonparam.productImage + knowledgefile1);
			com.setImgDetail(Commonparam.productImage + knowledgefile2);
			com.setImgVideo(spath);

			boolean rt = userManager.addKnowledge(com);
			if (rt) {
				result = "添加成功";
			} else {
				result = "添加失败";

			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getLocalizedMessage();
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/pages/result.jsp").forward(request,
				response);

	}
	
	
	
	
	/**
	 * 修改  知识
	 */
	public void updateKnowledge () throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean base = new BaseBean();

		String result = "";
		try {
			String spath = "";
			String spathtw = "";
			String spathth = "";
			
			if (this.knowledgefile != null) {

				String knowledgefile4 = Commonparam
						.makeNewFileName(this.knowledgefileFileName);
				Commonparam.makeFile(request, this.knowledgefile,
						Commonparam.productImage, knowledgefile4);
				spath = Commonparam.productImage + knowledgefile4;

			}
			

			if (this.knowledgefile1 != null) {

				String knowledgefile5 = Commonparam
						.makeNewFileName(this.knowledgefile1FileName);
				Commonparam.makeFile(request, this.knowledgefile1,
						Commonparam.productImage, knowledgefile5);
				spathtw = Commonparam.productImage + knowledgefile5;

			}
			

			if (this.knowledgefile2 != null) {

				String knowledgefile6 = Commonparam
						.makeNewFileName(this.knowledgefile2FileName);
				Commonparam.makeFile(request, this.knowledgefile2,
						Commonparam.productImage, knowledgefile6);
				spathth = Commonparam.productImage + knowledgefile6;

			}


			boolean rt = userManager.updateKnowledge(Long.valueOf(knowledgeId),title,introduction,content,videoUrl,source,spath,spathtw,spathth);

			if (rt) {
				result = "修改成功";
			} else {
				result = "修改失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getLocalizedMessage();
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/pages/result.jsp").forward(request,
				response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 首页
	 * 
	 */

	public void loadFirstpageInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Firstpage> list = userManager.loadFirstpageInfo(Integer.valueOf(rows), Integer.valueOf(page));
			bean.setStatus(200);
			bean.setRows(list);
			bean.setTotal(userManager.loadFirstpageInfoCount());
		} catch (Exception e) {
			bean.setStatus(400);
			bean.setMsg(e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	
	/**
	 * 添加 首页
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	public void addFirstpage() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		String result = "";

		try {

			String firstpagefile1 = Commonparam
					.makeNewFileName(this.firstpagefileFileName);
			Commonparam.makeFile(request, this.firstpagefile,
					Commonparam.productImage, firstpagefile1);

			String firstpagefile2 = Commonparam
					.makeNewFileName(this.firstpagefile1FileName);
			Commonparam.makeFile(request, this.firstpagefile1,
					Commonparam.productImage, firstpagefile2);
			

			String spath = "";

			if (this.firstpagefile2 != null) {

				String firstpagefile3 = Commonparam
						.makeNewFileName(this.firstpagefile2FileName);
				Commonparam.makeFile(request, this.firstpagefile2,
						Commonparam.productImage, firstpagefile3);
				spath = Commonparam.productImage + firstpagefile3;

			}	
				
		

			Firstpage com = new Firstpage();

			com.setTitle(title);
			com.setIntroduction(introduction);
			com.setContent(content);
			com.setSource(source);
			com.setVideoUrl(videoUrl);
			com.setImgInfo(Commonparam.productImage + firstpagefile1);
			com.setImgDetail(Commonparam.productImage + firstpagefile2);
			com.setImgVideo(spath);

			boolean rt = userManager.addFirstpage(com);
			if (rt) {
				result = "添加成功";
			} else {
				result = "添加失败";

			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getLocalizedMessage();
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/pages/result.jsp").forward(request,
				response);

	}
	
	
	
	
	/**
	 * 修改  首页
	 */
	public void updateFirstpage () throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean base = new BaseBean();

		String result = "";
		try {
			String spath = "";
			String spathtw = "";
			String spathth = "";
			
			if (this.firstpagefile != null) {

				String firstpagefile4 = Commonparam
						.makeNewFileName(this.firstpagefileFileName);
				Commonparam.makeFile(request, this.firstpagefile,
						Commonparam.productImage, firstpagefile4);
				spath = Commonparam.productImage + firstpagefile4;

			}
			

			if (this.firstpagefile1 != null) {

				String firstpagefile5 = Commonparam
						.makeNewFileName(this.firstpagefile1FileName);
				Commonparam.makeFile(request, this.firstpagefile1,
						Commonparam.productImage, firstpagefile5);
				spathtw = Commonparam.productImage + firstpagefile5;

			}
			

			if (this.firstpagefile2 != null) {

				String firstpagefile6 = Commonparam
						.makeNewFileName(this.firstpagefile2FileName);
				Commonparam.makeFile(request, this.firstpagefile2,
						Commonparam.productImage, firstpagefile6);
				spathth = Commonparam.productImage + firstpagefile6;

			}



			boolean rt = userManager.updateFirstpage(Long.valueOf(firstpageId),title,introduction,content,videoUrl,source,spath,spathtw,spathth);

			if (rt) {
				result = "修改成功";
			} else {
				result = "修改失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getLocalizedMessage();
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/pages/result.jsp").forward(request,
				response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 在线测试
	 * 
	 */

	public void loadOnlinetestInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Onlinetest> list = userManager.loadOnlinetestInfo();
			bean.setStatus(200);
			bean.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 添加 在线测试
	 * 
	 * @param com
	 * @return
	 */
	public void addOnlinetest() {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			BaseBean base = new BaseBean();
			Onlinetest com = new Onlinetest();
			com.setTopic(topic);
			com.setTesta(testa);
			com.setTestb(testb);
			com.setTestc(testc);
			com.setTestd(testd);
			com.setCorrectAnswer(correctAnswer);
			boolean rt = userManager.addOnlinetest(com);
			if (rt) {
				base.setMsg("添加成功");
				base.setStatus(200);
			} else {
				base.setMsg("添加失败");
				base.setStatus(400);
			}
			String json = JSONObject.fromObject(base).toString();
			response.getOutputStream().write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改 在线测试
	 * 
	 */
	public void updatpeOnlinetest() {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			BaseBean base = new BaseBean();
			boolean rt = userManager.updatpeOnlinetest(
					Long.valueOf(onlinetestId), topic, testa, testb, testc,
					testd, correctAnswer);
			if (rt) {
				base.setStatus(200);
				base.setMsg("修改成功");
			} else {
				base.setStatus(400);
				base.setMsg("修改失败");
			}
			String json = JSONObject.fromObject(base).toString();
			response.getOutputStream().write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * * 首页 关键字模糊搜索
	 */

	public void findFirstpageKeyword() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			if (keyword == null || rows == null || page == null) {
				bean.setMsg(Commonparam.error_param);
			} else {
				// id,name,img_list,introduction
				List<Object[]> list = userManager.findFirstpageKeyword(keyword,
						Integer.valueOf(rows), Integer.valueOf(page));
				List<Firstpage> rt = new ArrayList<Firstpage>();
				for (Object[] obj : list) {
					Firstpage firstp = new Firstpage();
					firstp.setId(Long.valueOf(obj[0].toString()));
					firstp.setTitle(obj[1].toString());
					firstp.setIntroduction(obj[2].toString());
					firstp.setCreatTime(obj[3].toString());
					firstp.setImgDetail(obj[4].toString());
					firstp.setSource(obj[5].toString());
					firstp.setContent(obj[6].toString());
					firstp.setImgInfo(obj[7].toString());
					firstp.setImgVideo(obj[8] == null ? "" : obj[8].toString());
					firstp.setVideoUrl(obj[9] == null ? "" : obj[9].toString());
					firstp.setImgTop(obj[10] == null ? "" : obj[10].toString());
					rt.add(firstp);
				}
				bean.setStatus(200);
				bean.setRows(rt);
				bean.setTotal(userManager.findFirstpageKeywordCount(keyword));
			}

		} catch (Exception e) {
			// TODO: handle exception
			bean.setMsg(e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 后台用户登陆
	 */
	public void loginTest() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		JSONObject jsonrt = new JSONObject();
		try {
			User user = userManager.loginTest(account, password);
			if (user != null) {
				if (user.getAccount().equals(account)
						&& user.getPassWord().equals(password)) {
					request.getSession().setAttribute("user", user);
					jsonrt.put("status", 200);
					jsonrt.put("msg", "登录成功");
				} else {
					jsonrt.put("status", 400);
					jsonrt.put("msg", "账号或密码错");
				}
			} else {
				jsonrt.put("status", 400);
				jsonrt.put("msg", "账号或密码错");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonrt.put("status", 400);
			jsonrt.put("msg", "账号或密码错");
		}

		response.getOutputStream().write(jsonrt.toString().getBytes("UTF-8"));
	}

	/**
	 * 后台用户 计算总数
	 */

	public void loadUserinfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<User> list = userManager.loadUserinfo(Integer.valueOf(rows),
					Integer.valueOf(page));
			bean.setStatus(200);
			bean.setRows(list);
			bean.setTotal(userManager.loadUserinfoCount());
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 新增后台用户
	 */
	private Short role;

	public void addUserinfo() throws ServletException, IOException {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		String result = "";
		try {
			BaseBean base = new BaseBean();

			User phoe = new User();

			phoe.setAccount(account);
			phoe.setPassWord(passWord);
			Integer count = userManager.testAccount(account);
			if (count == 0) {
				boolean rt = userManager.addUserinfo(phoe);

				if (rt) {
					result = "添加成功";
				} else {
					result = "添加失败";

				}
			} else {
				result = "用户已存在";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getLocalizedMessage();
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/pages/result.jsp").forward(request,
				response);

	}

	/**
	 * 修改信息
	 */
	public void updateuserinfo() {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			BaseBean base = new BaseBean();
			boolean rt = userManager.updateuserinfo(userId, account, passWord);
			if (rt) {
				base.setStatus(200);
				base.setMsg("修改成功");
			} else {
				base.setStatus(400);
				base.setMsg("修改失败");
			}
			String json = JSONObject.fromObject(base).toString();
			response.getOutputStream().write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除基础数据
	 */
	public void deleteBaseInfo() {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean base = new BaseBean();
		try {
			boolean rt = userManager.deleteBaseInfo(ids, table);
			if (rt) {
				base.setStatus(200);
				base.setMsg("删除成功");
			} else {
				base.setStatus(400);
				base.setMsg("删除失败");
			}
			String json = JSONObject.fromObject(base).toString();
			response.getOutputStream().write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加 引导页 目标文件夹+最终文件名
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	public void addBootpageImge() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		String result = "";

		try {
			String homefile1 = Commonparam
					.makeNewFileName(this.homefileFileName);
			Commonparam.makeFile(request, this.homefile,
					Commonparam.productImage, homefile1);

			Bootpage com = new Bootpage();

			com.setBootPage(Commonparam.productImage + homefile1);

			boolean rt = userManager.addBootpageImge(com);
			if (rt) {
				result = "添加成功";
			} else {
				result = "添加失败";

			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getLocalizedMessage();
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/pages/result.jsp").forward(request,
				response);

	}

	/**
	 * 修改 引导页
	 * 
	 */
	public void updateBootpageimage() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean base = new BaseBean();

		String result = "";
		try {
			String spath = "";

			if (this.homefile != null) {

				String homefile2 = Commonparam
						.makeNewFileName(this.homefileFileName);
				Commonparam.makeFile(request, this.homefile,
						Commonparam.productImage, homefile2);
				spath = Commonparam.productImage + homefile2;

			}

			boolean rt = userManager.updateBootpageimage(Long.valueOf(homeId),
					spath);

			if (rt) {
				result = "修改成功";
			} else {
				result = "修改失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getLocalizedMessage();
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/pages/result.jsp").forward(request,
				response);
	}

	/**
	 * 添加 关于我们
	 * 
	 * @param com
	 * @return
	 */
	public void addAboutus() {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			BaseBean base = new BaseBean();
			Aboutus com = new Aboutus();
			com.setTitle(title);
			com.setContent(content);
			boolean rt = userManager.addAboutus(com);
			if (rt) {
				base.setMsg("添加成功");
				base.setStatus(200);
			} else {
				base.setMsg("添加失败");
				base.setStatus(400);
			}
			String json = JSONObject.fromObject(base).toString();
			response.getOutputStream().write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改 关于我们
	 * 
	 */
	public void updatpeAboutus() {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			BaseBean base = new BaseBean();
			boolean rt = userManager.updatpeAboutus(Long.valueOf(aboutusId),
					title, content);
			if (rt) {
				base.setStatus(200);
				base.setMsg("修改成功");
			} else {
				base.setStatus(400);
				base.setMsg("修改失败");
			}
			String json = JSONObject.fromObject(base).toString();
			response.getOutputStream().write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加 技术支持
	 * 
	 * @param com
	 * @return
	 */
	public void addSupports() {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			BaseBean base = new BaseBean();
			Supports com = new Supports();
			com.setTitle(title);
			com.setContent(content);
			boolean rt = userManager.addSupports(com);
			if (rt) {
				base.setMsg("添加成功");
				base.setStatus(200);
			} else {
				base.setMsg("添加失败");
				base.setStatus(400);
			}
			String json = JSONObject.fromObject(base).toString();
			response.getOutputStream().write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改 技术支持
	 * 
	 */
	public void updatpeSupports() {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			BaseBean base = new BaseBean();
			boolean rt = userManager.updatpeSupports(Long.valueOf(supportsId),
					title, content);
			if (rt) {
				base.setStatus(200);
				base.setMsg("修改成功");
			} else {
				base.setStatus(400);
				base.setMsg("修改失败");
			}
			String json = JSONObject.fromObject(base).toString();
			response.getOutputStream().write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改 版本更新
	 * 
	 */
	public void updatpeVersion() {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			BaseBean base = new BaseBean();
			boolean rt = userManager.updatpeVersion(Long.valueOf(versionId),
					title, urlpath, urlNum);
			if (rt) {
				base.setStatus(200);
				base.setMsg("修改成功");
			} else {
				base.setStatus(400);
				base.setMsg("修改失败");
			}
			String json = JSONObject.fromObject(base).toString();
			response.getOutputStream().write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	
	/**
	 *   在线测试 首页
	 * 
	 */
	
	public void loadOnlinefirstInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Onlinefirst> list = userManager.loadOnlinefirstInfo();
			bean.setStatus(200);
			bean.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}
	
	
	
	/**
	 * 添加在线测试 首页
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	public void addOnlinefirst() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		String result = "";

		try {
			
			String onlinefirstfile1 = Commonparam
					.makeNewFileName(this.onlinefirstfileFileName);
			Commonparam.makeFile(request, this.onlinefirstfile,
					Commonparam.productImage, onlinefirstfile1);


			Onlinefirst com = new Onlinefirst();


			com.setContent(content);
			com.setIntroduction(introduction);
			com.setImgInfo(Commonparam.productImage + onlinefirstfile1);


			boolean rt = userManager.addOnlinefirst(com);
			if (rt) {
				result = "添加成功";
			} else {
				result = "添加失败";

			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getLocalizedMessage();
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/pages/result.jsp").forward(request,
				response);

	}
	

	/**
	 * 修改在线测试 首页
	 * 
	 */
	
	public void updateOnlinefirst () throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean base = new BaseBean();

		String result = "";
		try {
			String spath = "";
		
			
			if (this.onlinefirstfile != null) {

				String onlinefirstfile2 = Commonparam
						.makeNewFileName(this.onlinefirstfileFileName);
				Commonparam.makeFile(request, this.onlinefirstfile,
						Commonparam.productImage, onlinefirstfile2);
				spath = Commonparam.productImage + onlinefirstfile2;

			}
			
			boolean rt = userManager.updateOnlinefirst(Long.valueOf(onlinefirstId),content,introduction,spath);

			if (rt) {
				result = "修改成功";
			} else {
				result = "修改失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getLocalizedMessage();
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/pages/result.jsp").forward(request,
				response);
	}
	
	
	
	
	
	/**
	 *   实用推荐
	 * 
	 */
	
	public void loadRecommendInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Recommend> list = userManager.loadRecommendInfo(Integer.valueOf(rows), Integer.valueOf(page));
			bean.setStatus(200);
			bean.setRows(list);
			bean.setTotal(userManager.loadRecommendInfoCount());
		} catch (Exception e) {
			bean.setStatus(400);
			bean.setMsg(e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	
	
	
	/**
	 * 添加 实用推荐 目标文件夹+最终文件名
	 * 
	 * @throws IOException
	 * @throws ServletException
	 * 
	 */
	
	public void addRecommend() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		String result = "";

		try {

			String recommendfile1 = Commonparam
					.makeNewFileName(this.recommendfileFileName);
			Commonparam.makeFile(request, this.recommendfile,
					Commonparam.productImage, recommendfile1);

			String recommendfile2 = Commonparam
					.makeNewFileName(this.recommendfile1FileName);
			Commonparam.makeFile(request, this.recommendfile1,
					Commonparam.productImage, recommendfile2);
			
		

			Recommend com = new Recommend();

			com.setTitle(title);
			com.setIntroduction(introduction);
			com.setContent(content);
			com.setImgInfo(Commonparam.productImage + recommendfile1);
			com.setImgDetail(Commonparam.productImage + recommendfile2);

			boolean rt = userManager.addRecommend(com);
			if (rt) {
				result = "添加成功";
			} else {
				result = "添加失败";

			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getLocalizedMessage();
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/pages/result.jsp").forward(request,
				response);

	}
	
	
	
	/**
	 * 
	 * 实用推荐  修改
	 * 
	 */
	
	public void updateRecommend () throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean base = new BaseBean();

		String result = "";
		try {
			String spath = "";
			String spathtw = "";
			
			if (this.recommendfile != null) {

				String recommendfile3 = Commonparam
						.makeNewFileName(this.recommendfileFileName);
				Commonparam.makeFile(request, this.recommendfile,
						Commonparam.productImage, recommendfile3);
				spath = Commonparam.productImage + recommendfile3;

			}
			

			if (this.recommendfile1 != null) {

				String recommendfile4 = Commonparam
						.makeNewFileName(this.recommendfile1FileName);
				Commonparam.makeFile(request, this.recommendfile1,
						Commonparam.productImage, recommendfile4);
				spathtw = Commonparam.productImage + recommendfile4;

			}



			boolean rt = userManager.updateRecommend(Long.valueOf(recommendId),title,introduction,content,spath,spathtw);

			if (rt) {
				result = "修改成功";
			} else {
				result = "修改失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getLocalizedMessage();
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/pages/result.jsp").forward(request,
				response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	public String getFirstpage() {
		return firstpage;
	}

	public void setFirstpage(String firstpage) {
		this.firstpage = firstpage;
	}



	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public File getRecommendfile() {
		return recommendfile;
	}

	public void setRecommendfile(File recommendfile) {
		this.recommendfile = recommendfile;
	}

	public String getRecommendfileFileName() {
		return recommendfileFileName;
	}

	public void setRecommendfileFileName(String recommendfileFileName) {
		this.recommendfileFileName = recommendfileFileName;
	}

	public File getRecommendfile1() {
		return recommendfile1;
	}

	public void setRecommendfile1(File recommendfile1) {
		this.recommendfile1 = recommendfile1;
	}

	public String getRecommendfile1FileName() {
		return recommendfile1FileName;
	}

	public void setRecommendfile1FileName(String recommendfile1FileName) {
		this.recommendfile1FileName = recommendfile1FileName;
	}

	public String getOnlinefirstId() {
		return onlinefirstId;
	}

	public void setOnlinefirstId(String onlinefirstId) {
		this.onlinefirstId = onlinefirstId;
	}

	public File getOnlinefirstfile() {
		return onlinefirstfile;
	}

	public void setOnlinefirstfile(File onlinefirstfile) {
		this.onlinefirstfile = onlinefirstfile;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public File getFirstpagefile() {
		return firstpagefile;
	}

	public void setFirstpagefile(File firstpagefile) {
		this.firstpagefile = firstpagefile;
	}

	public String getFirstpagefileFileName() {
		return firstpagefileFileName;
	}

	public void setFirstpagefileFileName(String firstpagefileFileName) {
		this.firstpagefileFileName = firstpagefileFileName;
	}

	public File getFirstpagefile1() {
		return firstpagefile1;
	}

	public void setFirstpagefile1(File firstpagefile1) {
		this.firstpagefile1 = firstpagefile1;
	}

	public String getFirstpagefile1FileName() {
		return firstpagefile1FileName;
	}

	public void setFirstpagefile1FileName(String firstpagefile1FileName) {
		this.firstpagefile1FileName = firstpagefile1FileName;
	}

	public File getFirstpagefile2() {
		return firstpagefile2;
	}

	public void setFirstpagefile2(File firstpagefile2) {
		this.firstpagefile2 = firstpagefile2;
	}

	public String getFirstpagefile2FileName() {
		return firstpagefile2FileName;
	}

	public void setFirstpagefile2FileName(String firstpagefile2FileName) {
		this.firstpagefile2FileName = firstpagefile2FileName;
	}

	public String getFirstpageId() {
		return firstpageId;
	}

	public void setFirstpageId(String firstpageId) {
		this.firstpageId = firstpageId;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public File getKnowledgefile() {
		return knowledgefile;
	}

	public void setKnowledgefile(File knowledgefile) {
		this.knowledgefile = knowledgefile;
	}

	public String getKnowledgefileFileName() {
		return knowledgefileFileName;
	}

	public void setKnowledgefileFileName(String knowledgefileFileName) {
		this.knowledgefileFileName = knowledgefileFileName;
	}

	public File getKnowledgefile1() {
		return knowledgefile1;
	}

	public void setKnowledgefile1(File knowledgefile1) {
		this.knowledgefile1 = knowledgefile1;
	}

	public String getKnowledgefile1FileName() {
		return knowledgefile1FileName;
	}

	public void setKnowledgefile1FileName(String knowledgefile1FileName) {
		this.knowledgefile1FileName = knowledgefile1FileName;
	}

	public File getKnowledgefile2() {
		return knowledgefile2;
	}

	public void setKnowledgefile2(File knowledgefile2) {
		this.knowledgefile2 = knowledgefile2;
	}

	public String getKnowledgefile2FileName() {
		return knowledgefile2FileName;
	}

	public void setKnowledgefile2FileName(String knowledgefile2FileName) {
		this.knowledgefile2FileName = knowledgefile2FileName;
	}

	public String getOnlinetestId() {
		return onlinetestId;
	}

	public void setOnlinetestId(String onlinetestId) {
		this.onlinetestId = onlinetestId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTesta() {
		return testa;
	}

	public void setTesta(String testa) {
		this.testa = testa;
	}

	public String getTestb() {
		return testb;
	}

	public void setTestb(String testb) {
		this.testb = testb;
	}

	public String getTestc() {
		return testc;
	}

	public void setTestc(String testc) {
		this.testc = testc;
	}

	public String getTestd() {
		return testd;
	}

	public void setTestd(String testd) {
		this.testd = testd;
	}

	public Integer getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Integer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getUrlNum() {
		return urlNum;
	}

	public void setUrlNum(String urlNum) {
		this.urlNum = urlNum;
	}

	public String getUrlpath() {
		return urlpath;
	}

	public void setUrlpath(String urlpath) {
		this.urlpath = urlpath;
	}

	public String getHomeId() {
		return homeId;
	}

	public void setHomeId(String homeId) {
		this.homeId = homeId;
	}

	public File getHomefile() {
		return homefile;
	}

	public void setHomefile(File homefile) {
		this.homefile = homefile;
	}

	public String getHomefileFileName() {
		return homefileFileName;
	}

	public void setHomefileFileName(String homefileFileName) {
		this.homefileFileName = homefileFileName;
	}

	public String getHomeproduct() {
		return homeproduct;
	}

	public void setHomeproduct(String homeproduct) {
		this.homeproduct = homeproduct;
	}

	public Short getRole() {
		return role;
	}

	public void setRole(Short role) {
		this.role = role;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setAboutusId(String aboutusId) {
		this.aboutusId = aboutusId;
	}

	public String getAboutusId() {
		return aboutusId;
	}

	public void setSupportsId(String supportsId) {
		this.supportsId = supportsId;
	}

	public String getSupportsId() {
		return supportsId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setOnlinefirstfileFileName(String onlinefirstfileFileName) {
		this.onlinefirstfileFileName = onlinefirstfileFileName;
	}

	public String getOnlinefirstfileFileName() {
		return onlinefirstfileFileName;
	}

}
