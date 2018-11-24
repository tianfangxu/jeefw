package com.jeefw.model.recode.param;

import com.jeefw.model.sys.SysUser;

import core.support.ExtJSBaseParameter;

public class BaseModel<T,G> extends ExtJSBaseParameter{
	
	private T eqparam;
	
	private G likeparam;
	
	private String page;

	private String rows;
	
	private SysUser loginuser;

	public T getEqparam() {
		return eqparam;
	}

	public void setEqparam(T eqparam) {
		this.eqparam = eqparam;
	}

	public G getLikeparam() {
		return likeparam;
	}

	public void setLikeparam(G likeparam) {
		this.likeparam = likeparam;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public SysUser getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(SysUser loginuser) {
		this.loginuser = loginuser;
	}
	
	

}
