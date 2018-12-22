package com.surpass.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 类名称： FTPUtil<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2016-6-16<br>
 * 修改人： 王鹏飞<br>
 * 版本: 2.0.1 修改时间：2018-05-22
 */
public class FTPUtil {
	private static Logger log = LoggerFactory.getLogger(FTPUtil.class);
	
	// 数据编码
	private final static String charsetName = "UTF-8";
	
	private static String username;
	private static String password;
	private static String ip = null;
	private static int port;
	private static String filedir = "";
	/* apache 反向代理访问前缀 */
	private static String savePath="";

	public static String getFiledir() {
		return filedir;
	}

	public static void setFiledir(String filedir) {
		FTPUtil.filedir = filedir;
	}

	public static String getSavePath() {
		return savePath;
	}

	public static void setSavePath(String savePath) {
		FTPUtil.savePath = savePath;
	}

	public FTPUtil() {

	}

	private static void initCfg() {
		if (null == ip) {
			try {
				Properties property = ReadProperty.loadFile("/config/ftpConfig.properties");
				username = property.getProperty("username");
				password = property.getProperty("password");
				ip = property.getProperty("ip");
				port = Integer.parseInt(property.getProperty("port"));
				filedir = property.getProperty("filedir");
				savePath = property.getProperty("savePath");
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
			}
		}
	}

	@SuppressWarnings("unused")
	private FTPClientConfig getFtpConfig() {
		FTPClientConfig ftpConfig = new FTPClientConfig("UNIX");
		ftpConfig.setServerLanguageCode("ISO-8859-1");
		return ftpConfig;
	}

	/**
	 * 获取一个ftp连接
	 */
	public static FTPClient connectServer() {
		int reply = -1;
		try {
			initCfg();
			FTPClient ftpClient = new FTPClient();
			ftpClient.connect(ip);
			ftpClient.login(username, password);
			ftpClient.setDefaultPort(port);
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				log.info("FTP server refused connection.");
			} else {
				log.info("FTP server connection successful.");
			}
			return ftpClient;
		} catch (Exception e) {
			log.error("login ftp server [" + ip + "] failed.", e);
			e.printStackTrace();
		}
		return null;
	}

	public void changeWorkingDirectory(FTPClient ftpClient) {
		try {
			ftpClient.changeWorkingDirectory(filedir);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/** */
	/**
	 * 递归创建远程服务器目录
	 * 
	 * @param remote
	 *            远程服务器文件绝对路径
	 * 
	 * @return 目录创建是否成功
	 * @throws IOException
	 */
	public static boolean CreateDirecroty(FTPClient ftpClient, String remote) throws IOException {
		remote = new String(remote.getBytes(charsetName), "iso-8859-1");
		boolean success = true;
		String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
		// 如果远程目录不存在，则递归创建远程服务器目录
		if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(new String(directory))) {

			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			while (true) {
				String subDirectory = new String(remote.substring(start, end));
				if (!ftpClient.changeWorkingDirectory(subDirectory)) {
					if (ftpClient.makeDirectory(subDirectory)) {
						ftpClient.changeWorkingDirectory(subDirectory);
					} else {
						System.out.println("创建目录失败");
						success = false;
						return success;
					}
				}
				start = end + 1;
				end = directory.indexOf("/", start);
				// 检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		}
		return success;
	}

	/**
	 * 上传文件
	 * 
	 * @param filePath
	 *            path 后缀必须带有 “/”
	 * @param inputStream
	 * @param newFileName
	 */
	public static void uploadFile(FTPClient ftpClient, String filePath, InputStream inputStream, String newFileName) {
		// 设置PassiveMode传输
		ftpClient.enterLocalPassiveMode();
		// 设置以二进制流的方式传输
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// 对远程目录的处理
		@SuppressWarnings("unused")
		String remoteFileName = filePath;
		if (filePath.contains("/")) {
			remoteFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
			// 创建服务器远程目录结构，创建失败直接返回
			try {
				filePath = new String(filePath.getBytes(charsetName), "iso-8859-1");
				if (!CreateDirecroty(ftpClient, filePath)) {
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		BufferedInputStream buffIn = null;
		try {
			buffIn = new BufferedInputStream(inputStream);
			newFileName = new String(newFileName.getBytes(charsetName), "iso-8859-1");
			ftpClient.storeFile(newFileName, buffIn);
		} catch (Exception e) {
			log.error("Upload file occur exception.", e);
			e.printStackTrace();
			try {
				if (buffIn != null)
					buffIn.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				closeConnect(ftpClient);// 关闭ftp
				if (buffIn != null)
					buffIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void uploadFile(FTPClient ftpClient, InputStream inputStream, String newFileName) {
		changeWorkingDirectory(ftpClient);

		BufferedInputStream buffIn = null;
		try {
			buffIn = new BufferedInputStream(inputStream);
			newFileName = new String(newFileName.getBytes(charsetName), "iso-8859-1");
			ftpClient.storeFile(newFileName, buffIn);
		} catch (Exception e) {
			log.error("Upload file occur exception.", e);
			e.printStackTrace();
			try {
				if (buffIn != null)
					buffIn.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				closeConnect(ftpClient);// 关闭ftp
				if (buffIn != null)
					buffIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 方法名称：loadFile<br>
	 * 方法说明: 下载ftp文件 写到本地<br>
	 * @param ftpClient
	 * @param remoteFileName
	 * @param localFileName
	 */
	public void loadFile(FTPClient ftpClient, String remoteFileName, String localFileName) {
		BufferedOutputStream buffOut = null;
		try {
			buffOut = new BufferedOutputStream(new FileOutputStream(localFileName));
			remoteFileName = new String(remoteFileName.getBytes(charsetName), "iso-8859-1");
			ftpClient.retrieveFile(remoteFileName, buffOut);
		} catch (Exception e) {
			log.error("Download file occur exception.", e);
			e.printStackTrace();
			try {
				if (buffOut != null)
					buffOut.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				closeConnect(ftpClient);// 关闭ftp
				if (buffOut != null)
					buffOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 
	 * 方法名称：retrieveFile<br>
	 * 方法说明: 获取ftp文件数据流<br>
	 * 创建人: pfwang<br>
	 * 创建日期: 2017-11-9<br>
	 * @param ftpClient
	 * @param path
	 * @param os
	 * @return
	 */
	public static boolean retrieveFile(FTPClient ftpClient, String path, OutputStream os) {
		boolean b = false;
		try {
			path = new String(path.getBytes(charsetName), "iso-8859-1");
			b = ftpClient.retrieveFile(path, os);
		} catch (Exception e) {
			log.error("Download file occur exception.", e);
			e.printStackTrace();
		} finally {
			closeConnect(ftpClient);
		}
		return b;
	}

	/**
	 * 
	 * 方法名称：deleteFile<br>
	 * 方法说明: 删除 ftp 服务器文件<br>
	 * 创建人: pfwang<br>
	 * 创建日期: 2017-11-9<br>
	 * @param ftpClient
	 * @param filename
	 */
	public static void deleteFile(FTPClient ftpClient, String filename) {
		try {
			filename = new String(filename.getBytes(charsetName), "iso-8859-1");
			ftpClient.deleteFile(filename);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnect(ftpClient);// 关闭ftp
		}
	}

	/**
	 * 关闭 ftp 链接
	 */
	private static void closeConnect(FTPClient ftpClient) {
		try {
			if (ftpClient != null) {
				ftpClient.logout();
				ftpClient.disconnect();
				log.info("Ftp have closed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// FTPUtil handle = new FTPUtil();
		// handle.connectServer();
		//
		// String pathString =
		// "XCKSCL/027017/2016-05-23/1463975565716sub-search.png";

		// byte[] b = handle.loadFileByte(pathString);
		// System.out.println(b.toString());
		// System.out.println("11111111111");
		// handle.closeConnect();
	}
}
