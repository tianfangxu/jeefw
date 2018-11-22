package com.jeefw.model.recode;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.jeefw.model.recode.param.firstpartyContractModel;

import core.support.ExtJSBaseParameter;

@MappedSuperclass
public class BaseEntity extends firstpartyContractModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "createtime")
	private String createtime;		//创建时间			
	@Column(name = "flag")
	private String flag = "0";			//删除标志		0:未删除；1已删除
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public static String gettime(){
        return new SimpleDateFormat("yyyyMMddHHmms").format(new Date());
	}
}
