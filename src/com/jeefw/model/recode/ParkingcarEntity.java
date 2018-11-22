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
 * 楼宇停车位信息
 * @author Administrator
 *
 */
@Entity
@Table(name = "m_parkingcar")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ParkingcarEntity extends BaseEntity{

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "id", length = 32, nullable = false, unique = true)
	private String id;// 主键
	@Column(name = "pcnumber")
	private String pcnumber;// 车位编号
	@Column(name = "pcunit")
	private String pcunit;// 名称
	@Column(name = "pctype")
	private String pctype;// 类型
	@Column(name = "pcarea")
	private String pcarea;// 面积
	@Column(name = "pcfree")
	private String pcfree;// 空闲
	@Column(name = "buildid")
	private String buildid;// 楼宇id
	@Column(name = "buildname")
	private String buildname	;//	楼宇名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPcnumber() {
		return pcnumber;
	}
	public void setPcnumber(String pcnumber) {
		this.pcnumber = pcnumber;
	}
	public String getPcunit() {
		return pcunit;
	}
	public void setPcunit(String pcunit) {
		this.pcunit = pcunit;
	}
	public String getPctype() {
		return pctype;
	}
	public void setPctype(String pctype) {
		this.pctype = pctype;
	}
	public String getPcarea() {
		return pcarea;
	}
	public void setPcarea(String pcarea) {
		this.pcarea = pcarea;
	}
	public String getPcfree() {
		return pcfree;
	}
	public void setPcfree(String pcfree) {
		this.pcfree = pcfree;
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
	
	

}
