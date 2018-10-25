package cn.easybuy.util;
/**
 * ��ȡ�����ļ�����
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AccessProperties {
	static String Driver; //����
	static String Url; //��ַ
	static String User; //�˺�
	static String password; //����
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
