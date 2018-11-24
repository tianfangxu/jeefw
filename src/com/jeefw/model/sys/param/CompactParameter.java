package com.jeefw.model.sys.param;

import core.support.ExtJSBaseParameter;

/**
 * 合同管理的参数类
 * @JC
 */
public class CompactParameter extends ExtJSBaseParameter {

	private String htsj;   //合同时间 例子：      11/18/2018 - 11/18/2018

	public String getHtsj() {
		return htsj;
	}

	public void setHtsj(String htsj) {
		this.htsj = htsj;
	}
}
