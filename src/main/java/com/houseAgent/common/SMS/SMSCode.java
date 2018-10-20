package com.houseAgent.common.SMS;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SMSCode {
	/**
	 * 
	 * @param phoneNumber
	 * @param code
	 * @param type	(findPassword 忘记密码, register 注册)
	 * @return
	 * @throws Exception
	 */
	public static boolean sendCode(String phoneNumber,String code, String type)throws Exception {
		String code_Str = URLEncoder.encode("#code#="+code, "utf-8");
		if(type.equals("register")) {
			//注册模板
		}else if(type.equals("findPassword")) {
			//忘记密码模板
		}else {
			return false;
		}
		
		URL url=new URL("http://v.juhe.cn/sms/send?mobile=" + phoneNumber + "&tpl_id=102537"+"&tpl_value="+code_Str+"&key=d3e6ac8421de5709fa7855c6302724bd");
		URLConnection connection=url.openConnection();
		connection.connect();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuffer buffer = new StringBuffer();
		String lineData = null;
		while ((lineData=bufferedReader.readLine())!=null) {
			buffer.append(lineData);			
		}
		bufferedReader.close();
		if(buffer.toString().indexOf("\"error_code\":0")>0) {
			return true;
		}
		return false;
		
	}
}
