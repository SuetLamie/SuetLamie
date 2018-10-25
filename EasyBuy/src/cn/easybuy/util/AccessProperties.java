package cn.easybuy.util;
/**
 * 获取配置文件数据
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AccessProperties {
	static String Driver; //驱动
	static String Url; //地址
	static String User; //账号
	static String password; //密码
	static{
		Properties per=new Properties();
		try {
			InputStream in=AccessProperties.class.getResourceAsStream("/jdbc.properties");
			per.load(in);
			Driver=per.getProperty("Driver");
			Url=per.getProperty("Url");
			User=per.getProperty("Username");
			password=per.getProperty("Password");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
