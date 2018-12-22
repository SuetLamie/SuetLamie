package com.surpass.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.surpass.mvc.wechat.base.result.WechatResult;
import com.surpass.utils.Constants.SystemConstant;
import com.surpass.utils.Constants.WechatConfig;

/**
 * Http连接工具类
 * 
 * @author phil
 * @date 2017年7月2日
 *
 */
public class HttpReqUtil {

	private static int DEFAULT_CONNTIME = 5000;
	private static int DEFAULT_READTIME = 5000;
	private static int DEFAULT_UPLOAD_READTIME = 10 * 1000;
	
	
	/**
	 *
	 * @param strUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	public static String net(String strUrl, Map params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", SystemConstant.userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(SystemConstant.DEF_CONN_TIMEOUT);
			conn.setReadTimeout(SystemConstant.DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, SystemConstant.DEFAULT_CHARACTER_ENCODING));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}
	
	// 将map型转为请求参数型
		public static String urlencode(Map<String, String> data) {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry i : data.entrySet()) {
				try {
					sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		}
	
	/**
	 * http请求
	 * 
	 * @param method
	 *            请求方法GET/POST
	 * @param path
	 *            请求路径
	 * @param timeout
	 *            连接超时时间 默认为5000
	 * @param readTimeout
	 *            读取超时时间 默认为5000
	 * @param data
	 *            数据
	 * @return
	 */
	private static String defaultConnection(String method, String path, int timeout, int readTimeout, String data, String encoding)
			throws Exception {
		String result = "";
		URL url = new URL(path);
		if (url != null) {
			HttpURLConnection conn = getConnection(method, url);
			conn.setConnectTimeout(timeout == 0 ? DEFAULT_CONNTIME : timeout);
			conn.setReadTimeout(readTimeout == 0 ? DEFAULT_READTIME : readTimeout);
			if (data != null && !"".equals(data)) {
				OutputStream output = conn.getOutputStream();
				output.write(data.getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING));
				output.flush();
			}
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream input = conn.getInputStream();
				result = IOUtil.inputStreamToString(input, encoding);
				conn.disconnect();
			}
		}
		return result;
	}

	/**
	 * 根据url的协议选择对应的请求方式
	 * 
	 * @param method
	 *            请求的方法
	 * @return
	 * @throws IOException
	 */
	private static HttpURLConnection getConnection(String method, URL url) throws IOException {
		HttpURLConnection conn = null;
		if ("https".equals(url.getProtocol())) {
			SSLContext context = null;
			try {
				context = SSLContext.getInstance("SSL", "SunJSSE");
				context.init(new KeyManager[0], new TrustManager[] { new MyX509TrustManager() },
						new java.security.SecureRandom());
			} catch (Exception e) {
				throw new IOException(e);
			}
			HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
			connHttps.setSSLSocketFactory(context.getSocketFactory());
			connHttps.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			});
			conn = connHttps;
		} else {
			conn = (HttpURLConnection) url.openConnection();
		}
		conn.setRequestMethod(method);
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		return conn;
	}

	/**
	 * 设置参数
	 * 
	 * @param map
	 *            参数map
	 * @param path
	 *            需要赋值的path
	 * @param charset
	 *            编码格式 默认编码为utf-8(取消默认)
	 * @return 已经赋值好的url 只需要访问即可
	 */
	public static String setParmas(Map<String, String> map, String path, String charset) throws Exception {
		String result = "";
		boolean hasParams = false;
		if (path != null && !"".equals(path)) {
			if (MapUtils.isNotEmpty(map)) {
				StringBuilder builder = new StringBuilder();
				Set<Entry<String, String>> params = map.entrySet();
				for (Entry<String, String> entry : params) {
					String key = entry.getKey().trim();
					String value = entry.getValue().trim();
					if (hasParams) {
						builder.append("&");
					} else {
						hasParams = true;
					}
					if (charset != null && !"".equals(charset)) {
						// builder.append(key).append("=").append(URLDecoder.(value,charset));
						builder.append(key).append("=").append(IOUtil.urlEncode(value, charset));
					} else {
						builder.append(key).append("=").append(value);
					}
				}
				result = builder.toString();
			}
		}
		return doUrlPath(path, result).toString();
	}

	/**
	 * 设置连接参数
	 * 
	 * @param path
	 *            路径
	 * @return
	 */
	private static URL doUrlPath(String path, String query) throws Exception {
		URL url = new URL(path);
		if (StringUtils.isEmpty(path)) {
			return url;
		}
		if (StringUtils.isEmpty(url.getQuery())) {
			if (path.endsWith("?")) {
				path += query;
			} else {
				path = path + "?" + query;
			}
		} else {
			if (path.endsWith("&")) {
				path += query;
			} else {
				path = path + "&" + query;
			}
		}
		return new URL(path);
	}

	/**
	 * 默认的http请求执行方法,返回
	 * 
	 * @param method 请求的方法 POST/GET
	 * @param path 请求path 路径
	 * @param map 请求参数集合
	 * @param data 输入的数据 允许为空
	 * @return
	 */
	public static String HttpDefaultExecute(String method, String path, Map<String, String> map, String data, String encoding) {
		String result = "";
		try {
			String url = setParmas((TreeMap<String, String>) map, path, "");
			result = defaultConnection(method, url, DEFAULT_CONNTIME, DEFAULT_READTIME, data, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 默认的https执行方法,返回
	 * 
	 * @param method
	 *            请求的方法 POST/GET
	 * @param path
	 *            请求path 路径
	 * @param map
	 *            请求参数集合
	 * @param data
	 *            输入的数据 允许为空
	 * @return
	 */
	public static String HttpsDefaultExecute(String method, String path, Map<String, String> map, String data, String encoding) {
		String result = "";
		try {
			String url = setParmas((TreeMap<String, String>) map, path, "");
			result = defaultConnection(method, url, DEFAULT_CONNTIME, DEFAULT_READTIME, data, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 文件路径
	 * 
	 * @param mediaUrl url 例如 http://su.bdimg.com/static/superplus/img/logo_white_ee663702.png
	 * @return logo_white_ee663702.png
	 */
	private static String getFileName(String mediaUrl) {
		String result = mediaUrl.substring(mediaUrl.lastIndexOf("/") + 1, mediaUrl.length());
		return result;
	}

	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param ContentType
	 *            内容类型
	 * @return
	 */
	private static String getFileExt(String contentType) {
		String fileExt = "";
		if (contentType == null) {
			return null;
		}
		if (contentType.contains("image/jpeg")) {
			fileExt = ".jpg";
		} else if (contentType.contains("audio/mpeg")) {
			fileExt = ".mp3";
		} else if (contentType.contains("audio/amr")) {
			fileExt = ".amr";
		} else if (contentType.contains("video/mp4")) {
			fileExt = ".mp4";
		} else if (contentType.contains("video/mpeg4")) {
			fileExt = ".mp4";
		} else if (contentType.contains("image/png")) {
			fileExt = ".png";
		}
		return fileExt;
	}

	/**
	 * 根据返回的头信息返回具体信息
	 * 
	 * @param contentType
	 *            contentType请求头信息
	 * @return Result.type==1 表示文本消息,
	 */
	private static WechatResult contentType(String contentType, HttpURLConnection conn, String savePath, String encoding) {
		WechatResult result = new WechatResult();
		try {
			if (conn != null) {
				InputStream input = conn.getInputStream();
				// 考虑使用switch
				// switch(contentType){
				// case "image/gif": result = inputStreamToMedia(input,
				// savePath, "gif");
				// }
				if (contentType.equals("image/gif")) { // gif图片
					result = inputStreamToMedia(input, savePath, "gif");
				} else if (contentType.equals("image/jpeg")) { // jpg图片
					result = inputStreamToMedia(input, savePath, "jpg");
				} else if (contentType.equals("image/jpg")) { // jpg图片
					result = inputStreamToMedia(input, savePath, "jpg");
				} else if (contentType.equals("image/png")) { // png图片
					result = inputStreamToMedia(input, savePath, "png");
				} else if (contentType.equals("image/bmp")) { // bmp图片
					result = inputStreamToMedia(input, savePath, "bmp");
				} else if (contentType.equals("audio/x-wav")) { // wav语音
					result = inputStreamToMedia(input, savePath, "wav");
				} else if (contentType.equals("audio/x-ms-wma")) { // wma语言
					result = inputStreamToMedia(input, savePath, "wma");
				} else if (contentType.equals("audio/mpeg")) { // mp3语言
					result = inputStreamToMedia(input, savePath, "mp3");
				} else if (contentType.equals("text/plain")) { // 文本信息
					String str = IOUtil.inputStreamToString(input, encoding);
					result.setObject(str);
				} else if (contentType.equals("application/json")) { // 返回json格式的数据
					String str = IOUtil.inputStreamToString(input, encoding);
					result.setObject(str);
				}
			} else {
				result.setObject("conn is null!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 默认的下载素材方法
	 * 
	 * @param method
	 *            http方法 POST/GET
	 * @param apiPath
	 *            api路径
	 * @param savePath
	 *            素材需要保存的路径
	 * @return 是否下载成功 Reuslt.success==true 表示下载成功
	 */
	public static WechatResult downMeaterMetod(TreeMap<String, String> params, String method, String apiPath,
			String savePath, String encoding) {
		WechatResult result = new WechatResult();
		try {
			apiPath = setParmas(params, apiPath, "");
			URL url = new URL(apiPath);
			HttpURLConnection conn = getConnection(method, url);
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				String contentType = conn.getContentType();
				result = contentType(contentType, conn, savePath, encoding);
			} else {
				result.setObject(conn.getResponseCode() + "," + conn.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将字符流转换为图片文件
	 * 
	 * @param input
	 *            字符流
	 * @param savePath
	 *            图片需要保存的路径
	 * @param 类型
	 *            jpg/png等
	 * @return
	 */
	private static WechatResult inputStreamToMedia(InputStream inputStream, String savePath, String type) {
		WechatResult result = new WechatResult();
		File file = null;
		file = new File(savePath);
		String paramPath = file.getParent(); // 路径
		String fileName = file.getName(); //
		String newName = fileName.substring(0, fileName.lastIndexOf(".")) + "." + type;// 根据实际返回的文件类型后缀
		savePath = paramPath + "\\" + newName;
		if (!file.exists()) {
			File dirFile = new File(paramPath);
			dirFile.mkdirs();
		}
		file = new File(savePath);
		try {
			IOUtils.copy(inputStream, new FileOutputStream(file));
			result.setSuccess(true);
			result.setObject("save success!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 改变图片大小、格式
	 * 
	 * @param is
	 * @param os
	 * @param size
	 * @param format
	 * @return
	 * @throws IOException
	 */
	public static OutputStream resizeImage(InputStream inputStream, OutputStream outputStream, int size, String format)
			throws IOException {
		BufferedImage prevImage = ImageIO.read(inputStream);
		double width = prevImage.getWidth();
		double height = prevImage.getHeight();
		double percent = size / width;
		int newWidth = (int) (width * percent);
		int newHeight = (int) (height * percent);
		BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
		Graphics graphics = image.createGraphics();
		graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
		ImageIO.write(image, format, outputStream);
		outputStream.flush();
		return outputStream;
	}

	/**
	 * 获取客户端ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    if(ip.indexOf(",")!=-1){
	    	String[] ips = ip.split(",");
	    	ip = ips[0].trim();
	    }
		return ip;
	}

	

	/**
	 * 上传永久素材(video)
	 * 
	 * @param accessToken
	 * @return
	 */
	public static String uploadForeverMediaFile(String accessToken, String title, String introduction, String path, String encoding) {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("access_token", accessToken);
		params.put("type", "video");
		String mediaId = null;
		try {
			String json = HttpsUploadVideoMediaFile(SystemConstant.POST_METHOD,
					WechatConfig.UPLOAD_FOREVER_MEDIA_TYPE_URL, params, path, title, introduction, encoding);
			mediaId = JsonUtil.fromJsonString(json, "media_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mediaId;
	}

	/**
	 * 上传永久素材(video,网络)
	 * 
	 * @param accessToken
	 * @return
	 */
	public static String uploadForeverMedia(String accessToken, String title, String introduction, String path, String encoding) {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("access_token", accessToken);
		params.put("type", "video");
		String mediaId = null;
		try {
			String json = HttpsUploadVideoMedia(SystemConstant.POST_METHOD, WechatConfig.UPLOAD_FOREVER_MEDIA_TYPE_URL,
					params, path, title, introduction, 0, 0, encoding);
			mediaId = JsonUtil.fromJsonString(json, "media_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mediaId;
	}


	/**
	 * 上传图文消息内的图片获取URL(本地)
	 * 
	 * @param accessToken
	 * @param path
	 * @return
	 */
	public static String uploadImgMediaFile(String accessToken, String path, String encoding) {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("access_token", accessToken);
		String url = null;
		try {
			String json = HttpsUploadMediaFile(SystemConstant.POST_METHOD, WechatConfig.UPLOAD_IMG_MEDIA_URL, params,
					path, encoding);
			url = JsonUtil.fromJsonString(json, "url");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 上传图文消息内的图片获取URL(网络)
	 * 
	 * @param accessToken
	 * @param path
	 * @return
	 */
	public static String uploadImgMedia(String accessToken, String path, String encoding) {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("access_token", accessToken);
		String url = null;
		try {
			String json = HttpsUploadMedia(SystemConstant.POST_METHOD, WechatConfig.UPLOAD_IMG_MEDIA_URL, params, path, 0,
					0, encoding);
			url = JsonUtil.fromJsonString(json, "url");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 上传媒体文件(本地)
	 * 
	 * @param method
	 *            请求方法 GET/POST
	 * @param path
	 *            api的路径
	 * @param param
	 *            api参数
	 * @param mediaPath
	 *            待上传的image/music 的path
	 * @return
	 * @throws Exception
	 */
	public static String HttpsUploadMediaFile(String method, String path, Map<String, String> param, String mediaPath, String encoding)
			throws Exception {
		String result = null;
		URL url = new URL(setParmas(param, path, ""));
		OutputStream output = null;
		DataInputStream inputStream = null;
		try {
			File file = new File(mediaPath);
			if (!file.isFile() || !file.exists()) {
				throw new IOException("file is not exist");
			}
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod(SystemConstant.POST_METHOD);
			// 设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", SystemConstant.DEFAULT_CHARACTER_ENCODING);
			// 设置边界
			String boundary = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			// 请求正文信息
			// 第一部分
			output = new DataOutputStream(con.getOutputStream());
			IOUtils.write(("--" + boundary + "\r\n").getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING), output);
			IOUtils.write(("Content-Disposition: form-data;name=\"media\"; filename=\"" + file.getName() + "\"\r\n")
					.getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING), output);
			IOUtils.write(
					"Content-Type:application/octet-stream\r\n\r\n".getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING),
					output);
			// IOUtils.write(("Content-Type: "+ fileExt + "\r\n\r\n").getBytes(), output);
			// 文件正文部分
			// 把文件已流文件的方式 推入到url中
			inputStream = new DataInputStream(new FileInputStream(file));
			IOUtils.copy(inputStream, output);
			// 结尾部分
			IOUtils.write(("\r\n--" + boundary + "--\r\n").getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING), output);
			output.flush();
			result = IOUtil.inputStreamToString(con.getInputStream(), encoding);
		} catch (IOException e) {
			throw new IOException("read data error");
		} 
		return result;
	}

	/**
	 * 上传媒体文件(不能本地)
	 * 
	 * @param method
	 *            请求方法 GET/POST
	 * @param path
	 *            api的路径
	 * @param param
	 *            api参数
	 * @param mediaPath
	 *            待上传的image/music 的path
	 * @param connTime
	 *            连接时间 默认为5000
	 * @param readTime
	 *            读取时间 默认为5000
	 * @return
	 * @throws Exception
	 */
	public static String HttpsUploadMedia(String method, String path, Map<String, String> param, String mediaPath,
			int connTime, int readTime, String encoding) throws Exception {
		String result = "";
		URL url = new URL(setParmas(param, path, ""));
		OutputStream output = null;
		BufferedInputStream inputStream = null;
		try {
			String boundary = "----";
			HttpURLConnection conn = getConnection(method, url);
			conn.setConnectTimeout(connTime == 0 ? DEFAULT_CONNTIME : connTime);
			conn.setReadTimeout(readTime == 0 ? DEFAULT_UPLOAD_READTIME : readTime);
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			output = conn.getOutputStream();
			URL mediaUrl = new URL(mediaPath);
			if (mediaUrl != null) {
				HttpURLConnection mediaConn = (HttpURLConnection) mediaUrl.openConnection();
				mediaConn.setDoOutput(true);
				mediaConn.setUseCaches(false);
				mediaConn.setRequestMethod(SystemConstant.GET_METHOD);
				mediaConn.setConnectTimeout(connTime == 0 ? DEFAULT_CONNTIME : connTime);
				mediaConn.setReadTimeout(readTime == 0 ? DEFAULT_UPLOAD_READTIME : readTime);
				String connType = mediaConn.getContentType();
				// 获得文件扩展
				String fileExt = getFileExt(connType);
				IOUtils.write(("--" + boundary + "\r\n").getBytes(), output);
				IOUtils.write(("Content-Disposition: form-data; name=\"media\"; filename=\"" + getFileName(mediaPath)
						+ "\"\r\n").getBytes(), output);
				IOUtils.write(("Content-Type: " + fileExt + "\r\n\r\n").getBytes(), output);
				inputStream = new BufferedInputStream(mediaConn.getInputStream());
				IOUtils.copy(inputStream, output);
				IOUtils.write(("\r\n----" + boundary + "--\r\n").getBytes(), output);
				mediaConn.disconnect();
				// 获取输入流
				result = IOUtil.inputStreamToString(conn.getInputStream(), encoding);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return result;
	}

	/**
	 * 上传Video媒体文件(本地)
	 * 
	 * @param method
	 *            请求方法 GET/POST
	 * @param path
	 *            api的路径
	 * @param param
	 *            api参数
	 * @param mediaPath
	 *            待上传的voide 的path
	 * @param title
	 *            视频标题
	 * @param introduction
	 *            视频描述
	 * @return
	 * @throws Exception
	 */
	public static String HttpsUploadVideoMediaFile(String method, String path, Map<String, String> param,
			String mediaPath, String title, String introduction, String encoding) throws Exception {
		String result = null;
		URL url = new URL(setParmas(param, path, ""));
		OutputStream output = null;
		DataInputStream inputStream = null;
		try {
			File file = new File(mediaPath);
			if (!file.isFile() || !file.exists()) {
				throw new IOException("file is not exist");
			}
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod(SystemConstant.POST_METHOD);
			// 设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", SystemConstant.DEFAULT_CHARACTER_ENCODING);
			// 设置边界
			String boundary = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			// 请求正文信息
			// 第一部分
			output = new DataOutputStream(con.getOutputStream());
			IOUtils.write(("--" + boundary + "\r\n").getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING), output);
			IOUtils.write(("Content-Disposition: form-data;name=\"media\"; filename=\"" + file.getName() + "\"\r\n")
					.getBytes(), output);
			IOUtils.write("Content-Type: video/mp4 \r\n\r\n".getBytes(), output);
			// 文件正文部分
			// 把文件已流文件的方式 推入到url中
			inputStream = new DataInputStream(new FileInputStream(file));
			IOUtils.copy(inputStream, output);
			// 结尾部分
			IOUtils.write(("--" + boundary + "\r\n").getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING), output);
			IOUtils.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n"
					.getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING), output);
			IOUtils.write(("{\"title\":\"" + title + "\",\"introduction\":\"" + introduction + "\"}")
					.getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING), output);
			IOUtils.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING),
					output);
			output.flush();
			result = IOUtil.inputStreamToString(con.getInputStream(), encoding);
		} catch (IOException e) {
			throw new IOException("read data error");
		} 
		return result;
	}

	/**
	 * 上传Video媒体文件(网络)
	 * 
	 * @param method
	 *            请求方法 GET/POST
	 * @param path
	 *            api的路径
	 * @param param
	 *            api参数
	 * @param mediaPath
	 *            待上传的voide 的path
	 * @param title
	 *            视频标题
	 * @param introduction
	 *            视频描述
	 * @param connTime
	 *            连接时间 默认为5000
	 * @param readTime
	 *            读取时间 默认为5000
	 * @return
	 * @throws Exception
	 */
	public static String HttpsUploadVideoMedia(String method, String path, Map<String, String> param, String mediaPath,
			String title, String introduction, int connTime, int readTime, String encoding) throws Exception {
		String result = null;
		URL url = new URL(setParmas(param, path, ""));
		OutputStream output = null;
		BufferedInputStream inputStream = null;
		try {
			String boundary = "----";
			HttpURLConnection conn = getConnection(method, url);
			conn.setConnectTimeout(connTime == 0 ? DEFAULT_CONNTIME : connTime);
			conn.setReadTimeout(readTime == 0 ? DEFAULT_UPLOAD_READTIME : readTime);
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			output = conn.getOutputStream();
			URL mediaUrl = new URL(mediaPath);
			if (mediaUrl != null) {
				HttpURLConnection mediaConn = (HttpURLConnection) mediaUrl.openConnection();
				mediaConn.setDoOutput(true);
				mediaConn.setUseCaches(false);
				mediaConn.setRequestMethod(SystemConstant.GET_METHOD);
				mediaConn.setConnectTimeout(connTime == 0 ? DEFAULT_CONNTIME : connTime);
				mediaConn.setReadTimeout(readTime == 0 ? DEFAULT_UPLOAD_READTIME : readTime);
				IOUtils.write(("--" + boundary + "\r\n").getBytes(), output);
				IOUtils.write(("Content-Disposition: form-data; name=\"media\"; filename=\"" + getFileName(mediaPath)
						+ "\"\r\n").getBytes(), output);
				IOUtils.write("Content-Type: video/mp4 \r\n\r\n".getBytes(), output);
				inputStream = new BufferedInputStream(mediaConn.getInputStream());
				IOUtils.copy(inputStream, output);
				// 结尾部分
				IOUtils.write(("--" + boundary + "\r\n").getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING), output);
				IOUtils.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n"
						.getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING), output);
				IOUtils.write(("{\"title\":\"" + title + "\",\"introduction\":\"" + introduction + "\"}")
						.getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING), output);
				IOUtils.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes(SystemConstant.DEFAULT_CHARACTER_ENCODING),
						output);
				mediaConn.disconnect();
				// 获取输入流
				result = IOUtil.inputStreamToString(conn.getInputStream(), encoding);
			}
		} catch (IOException e) {
			throw new IOException("read data error");
		}
		return result;
	}
}

/**
 * 自定义信任管理器
 * 
 * @author phil
 * @date 2017年7月2日
 */
class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
