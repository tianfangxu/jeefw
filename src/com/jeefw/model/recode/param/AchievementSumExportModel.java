package com.jeefw.model.recode.param;

import java.io.Serializable;

public class AchievementSumExportModel implements Serializable{

	private String year;// 年度
	private String month;// 月度
	private String build;// 楼宇
	private String name;
	
	private String property;// 物业管理费收入
	private String fixedparking;// 固定停车费收入
	private String tempparking;// 临时停车费收入
	private String advertising;// 广告费收入
	private String electricin;// 电费收入
	private String waterin;// 水费收入						(新)	
	private String water;// 水费支出
	private String electricout;// 电费支出
	private String gas;// 燃气费支出
	private String stationery;// 办公用品支出
	private String communication;// 电话、通信费等支出
	private String drinkwater;// 饮用水等支出
	private String doorplate;// 门牌制作等支出
	private String decorate;// 节假日布置支出
	private String cleanser;// 保洁用品支出
	private String afforestation;// 绿化费支出
	private String ppe;// 劳防用品支出
	private String trashcleaning;// 垃圾清运费支出
	private String emergencymaterial;// 应急物资支出
	private String wallwashing;// 外墙、水箱清洗支出
	private String alarmservice;// 报警服务费支出
	private String pestcontrol;// 灭虫害服务费支出
	private String sewerage;// 污水处理（疏通）费支出
	private String maintenance;// 地坪保养费支出
	private String office;// 办公总务其他支出
	private String other;// 其他支出
	private String rent;// 租金收入							(新)	
	private String rest;// 其他经营收入（机房租赁等）							(新)
	private String servicing;// 维修材料收入							(新)	
	
	private String sumproperty;// 物业管理费收入
	private String sumfixedparking;// 固定停车费收入
	private String sumtempparking;// 临时停车费收入
	private String sumadvertising;// 广告费收入
	private String sumelectricin;// 电费收入
	private String sumwaterin;// 水费收入						(新)	
	private String sumwater;// 水费支出
	private String sumelectricout;// 电费支出
	private String sumgas;// 燃气费支出
	private String sumstationery;// 办公用品支出
	private String sumcommunication;// 电话、通信费等支出
	private String sumdrinkwater;// 饮用水等支出
	private String sumdoorplate;// 门牌制作等支出
	private String sumdecorate;// 节假日布置支出
	private String sumcleanser;// 保洁用品支出
	private String sumafforestation;// 绿化费支出
	private String sumppe;// 劳防用品支出
	private String sumtrashcleaning;// 垃圾清运费支出
	private String sumemergencymaterial;// 应急物资支出
	private String sumwallwashing;// 外墙、水箱清洗支出
	private String sumalarmservice;// 报警服务费支出
	private String sumpestcontrol;// 灭虫害服务费支出
	private String sumsewerage;// 污水处理（疏通）费支出
	private String summaintenance;// 地坪保养费支出
	private String sumoffice;// 办公总务其他支出
	private String sumother;// 其他支出
	private String sumrent;// 租金收入							(新)	
	private String sumrest;// 其他经营收入（机房租赁等）							(新)
	private String sumservicing;// 维修材料收入							(新)	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getFixedparking() {
		return fixedparking;
	}
	public void setFixedparking(String fixedparking) {
		this.fixedparking = fixedparking;
	}
	public String getTempparking() {
		return tempparking;
	}
	public void setTempparking(String tempparking) {
		this.tempparking = tempparking;
	}
	public String getAdvertising() {
		return advertising;
	}
	public void setAdvertising(String advertising) {
		this.advertising = advertising;
	}
	public String getElectricin() {
		return electricin;
	}
	public void setElectricin(String electricin) {
		this.electricin = electricin;
	}
	public String getWaterin() {
		return waterin;
	}
	public void setWaterin(String waterin) {
		this.waterin = waterin;
	}
	public String getWater() {
		return water;
	}
	public void setWater(String water) {
		this.water = water;
	}
	public String getElectricout() {
		return electricout;
	}
	public void setElectricout(String electricout) {
		this.electricout = electricout;
	}
	public String getGas() {
		return gas;
	}
	public void setGas(String gas) {
		this.gas = gas;
	}
	public String getStationery() {
		return stationery;
	}
	public void setStationery(String stationery) {
		this.stationery = stationery;
	}
	public String getCommunication() {
		return communication;
	}
	public void setCommunication(String communication) {
		this.communication = communication;
	}
	public String getDrinkwater() {
		return drinkwater;
	}
	public void setDrinkwater(String drinkwater) {
		this.drinkwater = drinkwater;
	}
	public String getDoorplate() {
		return doorplate;
	}
	public void setDoorplate(String doorplate) {
		this.doorplate = doorplate;
	}
	public String getDecorate() {
		return decorate;
	}
	public void setDecorate(String decorate) {
		this.decorate = decorate;
	}
	public String getCleanser() {
		return cleanser;
	}
	public void setCleanser(String cleanser) {
		this.cleanser = cleanser;
	}
	public String getAfforestation() {
		return afforestation;
	}
	public void setAfforestation(String afforestation) {
		this.afforestation = afforestation;
	}
	public String getPpe() {
		return ppe;
	}
	public void setPpe(String ppe) {
		this.ppe = ppe;
	}
	public String getTrashcleaning() {
		return trashcleaning;
	}
	public void setTrashcleaning(String trashcleaning) {
		this.trashcleaning = trashcleaning;
	}
	public String getEmergencymaterial() {
		return emergencymaterial;
	}
	public void setEmergencymaterial(String emergencymaterial) {
		this.emergencymaterial = emergencymaterial;
	}
	public String getWallwashing() {
		return wallwashing;
	}
	public void setWallwashing(String wallwashing) {
		this.wallwashing = wallwashing;
	}
	public String getAlarmservice() {
		return alarmservice;
	}
	public void setAlarmservice(String alarmservice) {
		this.alarmservice = alarmservice;
	}
	public String getPestcontrol() {
		return pestcontrol;
	}
	public void setPestcontrol(String pestcontrol) {
		this.pestcontrol = pestcontrol;
	}
	public String getSewerage() {
		return sewerage;
	}
	public void setSewerage(String sewerage) {
		this.sewerage = sewerage;
	}
	public String getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getRest() {
		return rest;
	}
	public void setRest(String rest) {
		this.rest = rest;
	}
	public String getServicing() {
		return servicing;
	}
	public void setServicing(String servicing) {
		this.servicing = servicing;
	}
	public String getSumproperty() {
		return sumproperty;
	}
	public void setSumproperty(String sumproperty) {
		this.sumproperty = sumproperty;
	}
	public String getSumfixedparking() {
		return sumfixedparking;
	}
	public void setSumfixedparking(String sumfixedparking) {
		this.sumfixedparking = sumfixedparking;
	}
	public String getSumtempparking() {
		return sumtempparking;
	}
	public void setSumtempparking(String sumtempparking) {
		this.sumtempparking = sumtempparking;
	}
	public String getSumadvertising() {
		return sumadvertising;
	}
	public void setSumadvertising(String sumadvertising) {
		this.sumadvertising = sumadvertising;
	}
	public String getSumelectricin() {
		return sumelectricin;
	}
	public void setSumelectricin(String sumelectricin) {
		this.sumelectricin = sumelectricin;
	}
	public String getSumwaterin() {
		return sumwaterin;
	}
	public void setSumwaterin(String sumwaterin) {
		this.sumwaterin = sumwaterin;
	}
	public String getSumwater() {
		return sumwater;
	}
	public void setSumwater(String sumwater) {
		this.sumwater = sumwater;
	}
	public String getSumelectricout() {
		return sumelectricout;
	}
	public void setSumelectricout(String sumelectricout) {
		this.sumelectricout = sumelectricout;
	}
	public String getSumgas() {
		return sumgas;
	}
	public void setSumgas(String sumgas) {
		this.sumgas = sumgas;
	}
	public String getSumstationery() {
		return sumstationery;
	}
	public void setSumstationery(String sumstationery) {
		this.sumstationery = sumstationery;
	}
	public String getSumcommunication() {
		return sumcommunication;
	}
	public void setSumcommunication(String sumcommunication) {
		this.sumcommunication = sumcommunication;
	}
	public String getSumdrinkwater() {
		return sumdrinkwater;
	}
	public void setSumdrinkwater(String sumdrinkwater) {
		this.sumdrinkwater = sumdrinkwater;
	}
	public String getSumdoorplate() {
		return sumdoorplate;
	}
	public void setSumdoorplate(String sumdoorplate) {
		this.sumdoorplate = sumdoorplate;
	}
	public String getSumdecorate() {
		return sumdecorate;
	}
	public void setSumdecorate(String sumdecorate) {
		this.sumdecorate = sumdecorate;
	}
	public String getSumcleanser() {
		return sumcleanser;
	}
	public void setSumcleanser(String sumcleanser) {
		this.sumcleanser = sumcleanser;
	}
	public String getSumafforestation() {
		return sumafforestation;
	}
	public void setSumafforestation(String sumafforestation) {
		this.sumafforestation = sumafforestation;
	}
	public String getSumppe() {
		return sumppe;
	}
	public void setSumppe(String sumppe) {
		this.sumppe = sumppe;
	}
	public String getSumtrashcleaning() {
		return sumtrashcleaning;
	}
	public void setSumtrashcleaning(String sumtrashcleaning) {
		this.sumtrashcleaning = sumtrashcleaning;
	}
	public String getSumemergencymaterial() {
		return sumemergencymaterial;
	}
	public void setSumemergencymaterial(String sumemergencymaterial) {
		this.sumemergencymaterial = sumemergencymaterial;
	}
	public String getSumwallwashing() {
		return sumwallwashing;
	}
	public void setSumwallwashing(String sumwallwashing) {
		this.sumwallwashing = sumwallwashing;
	}
	public String getSumalarmservice() {
		return sumalarmservice;
	}
	public void setSumalarmservice(String sumalarmservice) {
		this.sumalarmservice = sumalarmservice;
	}
	public String getSumpestcontrol() {
		return sumpestcontrol;
	}
	public void setSumpestcontrol(String sumpestcontrol) {
		this.sumpestcontrol = sumpestcontrol;
	}
	public String getSumsewerage() {
		return sumsewerage;
	}
	public void setSumsewerage(String sumsewerage) {
		this.sumsewerage = sumsewerage;
	}
	public String getSummaintenance() {
		return summaintenance;
	}
	public void setSummaintenance(String summaintenance) {
		this.summaintenance = summaintenance;
	}
	public String getSumoffice() {
		return sumoffice;
	}
	public void setSumoffice(String sumoffice) {
		this.sumoffice = sumoffice;
	}
	public String getSumother() {
		return sumother;
	}
	public void setSumother(String sumother) {
		this.sumother = sumother;
	}
	public String getSumrent() {
		return sumrent;
	}
	public void setSumrent(String sumrent) {
		this.sumrent = sumrent;
	}
	public String getSumrest() {
		return sumrest;
	}
	public void setSumrest(String sumrest) {
		this.sumrest = sumrest;
	}
	public String getSumservicing() {
		return sumservicing;
	}
	public void setSumservicing(String sumservicing) {
		this.sumservicing = sumservicing;
	}
	
	
}
