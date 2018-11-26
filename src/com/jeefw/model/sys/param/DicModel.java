package com.jeefw.model.sys.param;

import com.jeefw.model.recode.param.BaseModel;

import java.io.Serializable;

/***
 * 字典信息
 *
 * @author Administrator
 *
 */

public class DicModel<T, G> extends BaseModel<T, G> implements Serializable {

	private Long id;
	private String dictKey;
	private String dictValue;
	private Long sequence;
	private String parentDictkey;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDictKey() {
		return dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getParentDictkey() {
		return parentDictkey;
	}

	public void setParentDictkey(String parentDictkey) {
		this.parentDictkey = parentDictkey;
	}
}
