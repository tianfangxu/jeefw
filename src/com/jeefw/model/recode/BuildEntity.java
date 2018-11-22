package com.jeefw.model.recode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/***
 * 楼宇信息
 * @author Administrator
 *
 */
@Entity
@Table(name = "m_build")
@DynamicInsert(true)
@DynamicUpdate(true)
public class BuildEntity extends BaseEntity{
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "id", length = 32, nullable = false, unique = true)
	private String id;// 主键
	@Column(name = "buildnumber")
	private String buildnumber;// 编号
	@Column(name = "buildname")
	private String buildname;// 名称
	@Column(name = "buildaddress")
	private String buildaddress;// 地址
	@Column(name = "buildarea")
	private String buildarea;// 地址区
	@Column(name = "buildroad")
	private String buildroad;// 地址路
	@Column(name = "buildhouse")
	private String buildhouse;// 地址号
	@Column(name = "buildmanager")
	private String buildmanager;// 楼宇经理
	@Column(name = "buildphone")
	private String buildphone;// 电话号码
	@Column(name = "buildcondition")
	private String buildcondition;// 物业概况
	@Column(name = "userid")
	private String userid	;//	楼宇经理id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuildnumber() {
		return buildnumber;
	}
	public void setBuildnumber(String buildnumber) {
		this.buildnumber = buildnumber;
	}
	public String getBuildname() {
		return buildname;
	}
	public void setBuildname(String buildname) {
		this.buildname = buildname;
	}
	public String getBuildaddress() {
		return buildaddress;
	}
	public void setBuildaddress(String buildaddress) {
		this.buildaddress = buildaddress;
	}
	public String getBuildarea() {
		return buildarea;
	}
	public void setBuildarea(String buildarea) {
		this.buildarea = buildarea;
	}
	public String getBuildroad() {
		return buildroad;
	}
	public void setBuildroad(String buildroad) {
		this.buildroad = buildroad;
	}
	public String getBuildhouse() {
		return buildhouse;
	}
	public void setBuildhouse(String buildhouse) {
		this.buildhouse = buildhouse;
	}
	public String getBuildmanager() {
		return buildmanager;
	}
	public void setBuildmanager(String buildmanager) {
		this.buildmanager = buildmanager;
	}
	public String getBuildphone() {
		return buildphone;
	}
	public void setBuildphone(String buildphone) {
		this.buildphone = buildphone;
	}
	public String getBuildcondition() {
		return buildcondition;
	}
	public void setBuildcondition(String buildcondition) {
		this.buildcondition = buildcondition;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	

}
