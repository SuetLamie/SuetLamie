package com.surpass.framework.scheduling;

import java.net.InetAddress;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class InstantiationSchedulerListener implements ApplicationListener<ContextRefreshedEvent> {
	// 可以把dao注入进来
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			// 把定时任务初始化的代码写到这里
			String ip = null;
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
				System.err.println(ip);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
