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
 *楼宇内物业信息
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "m_property")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PropertyEntity extends BaseEntity{

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "id", length = 32, nullable = false, unique = true)
	private String id;// 主键
	@Column(name = "pnumber")
	private String pnumber;// 编号
	@Column(name = "punit")
	private String punit;// 管理单元
	@Column(name = "paddress")
	private String paddress;// 地址
	@Column(name = "proomname")
	private String proomname;// 室号
	@Column(name = "parea")
	private String parea;// 面积
	@Column(name = "pcondition")
	private String pcondition;// 物业概况
	@Column(name = "buildid")
	private String buildid;// 楼宇id
	@Column(name = "buildname")
	private String buildname;// 楼宇名称
	@Column(name = "userid")
	private String userid;// 楼宇经理id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPnumber() {
		return pnumber;
	}
	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}
	public String getPunit() {
		return punit;
	}
	public void setPunit(String punit) {
		this.punit = punit;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public String getProomname() {
		return proomname;
	}
	public void setProomname(String proomname) {
		this.proomname = proomname;
	}
	public String getParea() {
		return parea;
	}
	public void setParea(String parea) {
		this.parea = parea;
	}
	public String getPcondition() {
		return pcondition;
	}
	public void setPcondition(String pcondition) {
		this.pcondition = pcondition;
	}
	public String getBuildid() {
		return buildid;
	}
	public void setBuildid(String buildid) {
		this.buildid = buildid;
	}
	public String getBuildname() {
		return buildname;
	}
	public void setBuildname(String buildname) {
		this.buildname = buildname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
}
