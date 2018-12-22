package com.surpass.framework.scheduling;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;

import com.surpass.utils.SpringContextUtil;

public class MyTest {
	public static void test() {
		try {
			SchedulerFactory gSchedulerFactory = (SchedulerFactory) SpringContextUtil.getBean("stdSchedulerFactory");
			Scheduler sche = gSchedulerFactory.getScheduler();
			String job_name = "动态任务调度";
			System.out.println("【系统启动】开始(每1秒输出一次)...");
			QuartzManager.addJob(sche, job_name, QuartzJobExample.class, "0/1 * * * * ?");

			Thread.sleep(3000);
			System.out.println("【修改时间】开始(每2秒输出一次)...");
			QuartzManager.modifyJobTime(sche, job_name, "10/2 * * * * ?");
			Thread.sleep(4000);
			System.out.println("【移除定时】开始...");
			QuartzManager.removeJob(sche, job_name);
			System.out.println("【移除定时】成功");

			System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");
			QuartzManager.addJob(sche, job_name, QuartzJobExample.class, "*/10 * * * * ?");
			Thread.sleep(30000);
			System.out.println("【移除定时】开始...");
			QuartzManager.removeJob(sche, job_name);
			System.out.println("【移除定时】成功");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void test11() {
		try {
			SchedulerFactory gSchedulerFactory = (SchedulerFactory) SpringContextUtil.getBean("stdSchedulerFactory");
			Scheduler sche = gSchedulerFactory.getScheduler();
			String job_name = "动态任务调度";
			System.out.println("【系统启动】开始(每1秒输出一次)...");
			QuartzManager.addJob(sche, job_name, QuartzJobExample.class, "0 24 12 30 3 ? 2017");
			// QuartzManager.removeJob(sche, job_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		test();
	}
}
