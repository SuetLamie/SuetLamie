package com.surpass.mvc.share.sys.bean;

import java.io.Serializable;

/**
 * 
 * 类名称： RunStatus<br>
 * 类描述：运行状态 <br>
 * 创建人： 方曙强<br>
 * 创建时间：2017-2-28<br>
 * 修改人： 方曙强<br>
 * 修改时间：2017-2-28
 */
public class RunStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private double freeMemory;
	private double maxMemory;
	private double useMemory;

	public RunStatus() {
		super();
	}

	public RunStatus(double freeMemory, double maxMemory, double useMemory) {
		super();
		this.freeMemory = freeMemory;
		this.maxMemory = maxMemory;
		this.useMemory = useMemory;
	}

	public double getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(double freeMemory) {
		this.freeMemory = freeMemory;
	}

	public double getMaxMemory() {
		return maxMemory;
	}

	public void setMaxMemory(double maxMemory) {
		this.maxMemory = maxMemory;
	}

	public double getUseMemory() {
		return useMemory;
	}

	public void setUseMemory(double useMemory) {
		this.useMemory = useMemory;
	}

}
