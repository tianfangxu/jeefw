package com.jeefw.model.recode.param;

import java.io.Serializable;

public class ParkingModel<T, G> extends BaseModel<T, G> implements Serializable {

	private String id;
	private String build;// 楼宇
	private String buildname;
	private String name;// 名称
	private String type;// 类型
	private String area;// 面积
	private String price;// 价格
	private String used;// 租赁状态
	private String createuser;// 创建人
	private String createtime;// 创建时间
	private String updateuser;// 修改人
	private String updatetime;// 修改时间
	private String deleteuser;// 删除人
	private String deletetime;// 删除时间
	private String deleteflg;// 删除标记

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getDeleteuser() {
		return deleteuser;
	}

	public void setDeleteuser(String deleteuser) {
		this.deleteuser = deleteuser;
	}

	public String getDeletetime() {
		return deletetime;
	}

	public void setDeletetime(String deletetime) {
		this.deletetime = deletetime;
	}

	public String getDeleteflg() {
		return deleteflg;
	}

	public void setDeleteflg(String deleteflg) {
		this.deleteflg = deleteflg;
	}

	public String getBuildname() {
		return buildname;
	}

	public void setBuildname(String buildname) {
		this.buildname = buildname;
	}

}
