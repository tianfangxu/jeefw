package com.jeefw.model.recode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 月度实绩
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_achievement")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AchievementEntity extends BaseEntity{

	@Column(name = "year")
	private String year;// 年度
	@Column(name = "month")
	private String month;// 月度
	@Column(name = "build")
	private String build;// 楼宇
	@Column(name = "type")
	private String type;// 费用类型
	@Column(name = "serialno")
	private String serialno;// 排序号
	
	@Column(name = "rent")
	private String rent;// 租金收入							(新)	
	@Column(name = "property")
	private String property;// 物业管理费收入
	@Column(name = "fixedparking")
	private String fixedparking;// 固定停车费收入
	@Column(name = "tempparking")
	private String tempparking;// 临时停车费收入
	@Column(name = "advertising")
	private String advertising;// 广告费收入
	@Column(name = "rest")
	private String rest;// 其他经营收入（机房租赁等）							(新)
	@Column(name = "servicing")
	private String servicing;// 维修材料收入							(新)	
	@Column(name = "electricin")
	private String electricin;// 电费收入
	@Column(name = "waterin")
	private String waterin;// 水费收入						(新)	
	@Column(name = "water")
	private String water;// 水费支出
	@Column(name = "electricout")
	private String electricout;// 电费支出
	@Column(name = "gas")
	private String gas;// 燃气费支出
	@Column(name = "stationery")
	private String stationery;// 办公用品支出
	@Column(name = "communication")
	private String communication;// 电话、通信费等支出
	@Column(name = "drinkwater")
	private String drinkwater;// 饮用水等支出
	@Column(name = "doorplate")
	private String doorplate;// 门牌制作等支出
	@Column(name = "decorate")
	private String decorate;// 节假日布置支出
	@Column(name = "cleanser")
	private String cleanser;// 保洁用品支出
	@Column(name = "afforestation")
	private String afforestation;// 绿化费支出
	@Column(name = "ppe")
	private String ppe;// 劳防用品支出
	@Column(name = "trashcleaning")
	private String trashcleaning;// 垃圾清运费支出
	@Column(name = "emergencymaterial")
	private String emergencymaterial;// 应急物资支出
	@Column(name = "wallwashing")
	private String wallwashing;// 外墙、水箱清洗支出
	@Column(name = "alarmservice")
	private String alarmservice;// 报警服务费支出
	@Column(name = "pestcontrol")
	private String pestcontrol;// 灭虫害服务费支出
	@Column(name = "sewerage")
	private String sewerage;// 污水处理（疏通）费支出
	@Column(name = "maintenance")
	private String maintenance;// 地坪保养费支出
	@Column(name = "office")
	private String office;// 办公总务其他支出
	@Column(name = "other")
	private String other;// 其他支出
	
	/**备注s**/
	@Column(name = "rentps")
	private String rentps;// 租金收入备注							(新)	
	@Column(name = "propertyps")
	private String propertyps;// 物业管理费收入备注
	@Column(name = "fixedparkingps")
	private String fixedparkingps;// 固定停车费收入备注	
	@Column(name = "tempparkingps")
	private String tempparkingps;// 临时停车费收入备注	
	@Column(name = "advertisingps")
	private String advertisingps;// 广告费收入备注	
	@Column(name = "restps")
	private String restps;// 其他经营收入（机房租赁等）备注								(新)
	@Column(name = "servicingps")
	private String servicingps;// 维修材料收入备注								(新)	
	@Column(name = "electricinps")
	private String electricinps;// 电费收入备注
	@Column(name = "waterinps")
	private String waterinps;// 水费收入备注						(新)
	@Column(name = "waterps")
	private String waterps;// 水费支出备注	
	@Column(name = "electricoutps")
	private String electricoutps;// 电费支出备注	
	@Column(name = "gasps")
	private String gasps;// 燃气费支出备注	
	@Column(name = "stationeryps")
	private String stationeryps;// 办公用品支出备注	
	@Column(name = "communicationps")
	private String communicationps;// 电话、通信费等支出备注	
	@Column(name = "drinkwaterps")
	private String drinkwaterps;// 饮用水等支出备注	
	@Column(name = "doorplateps")
	private String doorplateps;// 门牌制作等支出备注	
	@Column(name = "decorateps")
	private String decorateps;// 节假日布置支出备注	
	@Column(name = "cleanserps")
	private String cleanserps;// 保洁用品支出备注	
	@Column(name = "afforestationps")
	private String afforestationps;// 绿化费支出备注	
	@Column(name = "ppeps")
	private String ppeps;// 劳防用品支出备注	
	@Column(name = "trashcleaningps")
	private String trashcleaningps;// 垃圾清运费支出备注	
	@Column(name = "emergencymaterialps")
	private String emergencymaterialps;// 应急物资支出备注	
	@Column(name = "wallwashingps")
	private String wallwashingps;// 外墙、水箱清洗支出备注	
	@Column(name = "alarmserviceps")
	private String alarmserviceps;// 报警服务费支出备注	
	@Column(name = "pestcontrolps")
	private String pestcontrolps;// 灭虫害服务费支出备注	
	@Column(name = "sewerageps")
	private String sewerageps;// 污水处理（疏通）费支出备注	
	@Column(name = "maintenanceps")
	private String maintenanceps;// 地坪保养费支出备注	
	@Column(name = "officeps")
	private String officeps;// 办公总务其他支出备注	
	@Column(name = "otherps")
	private String otherps;// 其他支出备注
	
	@Column(name = "service")
	private String service;// 服务费收入(含基站)
	@Column(name = "warehouse")
	private String warehouse;// 仓储费/经营开发 收入
	@Column(name = "rental")
	private String rental;// 租线费收入
	@Column(name = "security")
	private String security;// 保安服务费支出
	@Column(name = "cleansing")
	private String cleansing;// 保洁服务费支出
	@Column(name = "projectout")
	private String projectout;// 工程外包支出
	@Column(name = "repair")
	private String repair;// 大修理费用支出
	@Column(name = "firefighting")
	private String firefighting;// 消防设备设施维护费支出
	@Column(name = "engineering")
	private String engineering;// 工程设备设施维护费支出
	@Column(name = "equipmenttesting")
	private String equipmenttesting;// 设备设施检测费支出
	@Column(name = "material")
	private String material;// 材料费支出
	@Column(name = "extinguisher")
	private String extinguisher;// 灭火机支出
	@Column(name = "upkeep")
	private String upkeep;// 日常维修费支出
	
	@Column(name = "serviceps")
	private String serviceps;// 服务费收入(含基站)
	@Column(name = "warehouseps")
	private String warehouseps;// 仓储费/经营开发 收入
	@Column(name = "rentalps")
	private String rentalps;// 租线费收入
	@Column(name = "securityps")
	private String securityps;// 保安服务费支出
	@Column(name = "cleansingps")
	private String cleansingps;// 保洁服务费支出
	@Column(name = "projectoutps")
	private String projectoutps;// 工程外包支出
	@Column(name = "repairps")
	private String repairps;// 大修理费用支出
	@Column(name = "firefightingps")
	private String firefightingps;// 消防设备设施维护费支出
	@Column(name = "engineeringps")
	private String engineeringps;// 工程设备设施维护费支出
	@Column(name = "equipmenttestingps")
	private String equipmenttestingps;// 设备设施检测费支出
	@Column(name = "materialps")
	private String materialps;// 材料费支出
	@Column(name = "extinguisherps")
	private String extinguisherps;// 灭火机支出
	@Column(name = "upkeepps")
	private String upkeepps;// 日常维修费支出
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
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
	public String getRentps() {
		return rentps;
	}
	public void setRentps(String rentps) {
		this.rentps = rentps;
	}
	public String getPropertyps() {
		return propertyps;
	}
	public void setPropertyps(String propertyps) {
		this.propertyps = propertyps;
	}
	public String getFixedparkingps() {
		return fixedparkingps;
	}
	public void setFixedparkingps(String fixedparkingps) {
		this.fixedparkingps = fixedparkingps;
	}
	public String getTempparkingps() {
		return tempparkingps;
	}
	public void setTempparkingps(String tempparkingps) {
		this.tempparkingps = tempparkingps;
	}
	public String getAdvertisingps() {
		return advertisingps;
	}
	public void setAdvertisingps(String advertisingps) {
		this.advertisingps = advertisingps;
	}
	public String getRestps() {
		return restps;
	}
	public void setRestps(String restps) {
		this.restps = restps;
	}
	public String getServicingps() {
		return servicingps;
	}
	public void setServicingps(String servicingps) {
		this.servicingps = servicingps;
	}
	public String getElectricinps() {
		return electricinps;
	}
	public void setElectricinps(String electricinps) {
		this.electricinps = electricinps;
	}
	public String getWaterinps() {
		return waterinps;
	}
	public void setWaterinps(String waterinps) {
		this.waterinps = waterinps;
	}
	public String getWaterps() {
		return waterps;
	}
	public void setWaterps(String waterps) {
		this.waterps = waterps;
	}
	public String getElectricoutps() {
		return electricoutps;
	}
	public void setElectricoutps(String electricoutps) {
		this.electricoutps = electricoutps;
	}
	public String getGasps() {
		return gasps;
	}
	public void setGasps(String gasps) {
		this.gasps = gasps;
	}
	public String getStationeryps() {
		return stationeryps;
	}
	public void setStationeryps(String stationeryps) {
		this.stationeryps = stationeryps;
	}
	public String getCommunicationps() {
		return communicationps;
	}
	public void setCommunicationps(String communicationps) {
		this.communicationps = communicationps;
	}
	public String getDrinkwaterps() {
		return drinkwaterps;
	}
	public void setDrinkwaterps(String drinkwaterps) {
		this.drinkwaterps = drinkwaterps;
	}
	public String getDoorplateps() {
		return doorplateps;
	}
	public void setDoorplateps(String doorplateps) {
		this.doorplateps = doorplateps;
	}
	public String getDecorateps() {
		return decorateps;
	}
	public void setDecorateps(String decorateps) {
		this.decorateps = decorateps;
	}
	public String getCleanserps() {
		return cleanserps;
	}
	public void setCleanserps(String cleanserps) {
		this.cleanserps = cleanserps;
	}
	public String getAfforestationps() {
		return afforestationps;
	}
	public void setAfforestationps(String afforestationps) {
		this.afforestationps = afforestationps;
	}
	public String getPpeps() {
		return ppeps;
	}
	public void setPpeps(String ppeps) {
		this.ppeps = ppeps;
	}
	public String getTrashcleaningps() {
		return trashcleaningps;
	}
	public void setTrashcleaningps(String trashcleaningps) {
		this.trashcleaningps = trashcleaningps;
	}
	public String getEmergencymaterialps() {
		return emergencymaterialps;
	}
	public void setEmergencymaterialps(String emergencymaterialps) {
		this.emergencymaterialps = emergencymaterialps;
	}
	public String getWallwashingps() {
		return wallwashingps;
	}
	public void setWallwashingps(String wallwashingps) {
		this.wallwashingps = wallwashingps;
	}
	public String getAlarmserviceps() {
		return alarmserviceps;
	}
	public void setAlarmserviceps(String alarmserviceps) {
		this.alarmserviceps = alarmserviceps;
	}
	public String getPestcontrolps() {
		return pestcontrolps;
	}
	public void setPestcontrolps(String pestcontrolps) {
		this.pestcontrolps = pestcontrolps;
	}
	public String getSewerageps() {
		return sewerageps;
	}
	public void setSewerageps(String sewerageps) {
		this.sewerageps = sewerageps;
	}
	public String getMaintenanceps() {
		return maintenanceps;
	}
	public void setMaintenanceps(String maintenanceps) {
		this.maintenanceps = maintenanceps;
	}
	public String getOfficeps() {
		return officeps;
	}
	public void setOfficeps(String officeps) {
		this.officeps = officeps;
	}
	public String getOtherps() {
		return otherps;
	}
	public void setOtherps(String otherps) {
		this.otherps = otherps;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getRental() {
		return rental;
	}
	public void setRental(String rental) {
		this.rental = rental;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getCleansing() {
		return cleansing;
	}
	public void setCleansing(String cleansing) {
		this.cleansing = cleansing;
	}
	public String getProjectout() {
		return projectout;
	}
	public void setProjectout(String projectout) {
		this.projectout = projectout;
	}
	public String getRepair() {
		return repair;
	}
	public void setRepair(String repair) {
		this.repair = repair;
	}
	public String getFirefighting() {
		return firefighting;
	}
	public void setFirefighting(String firefighting) {
		this.firefighting = firefighting;
	}
	public String getEngineering() {
		return engineering;
	}
	public void setEngineering(String engineering) {
		this.engineering = engineering;
	}
	public String getEquipmenttesting() {
		return equipmenttesting;
	}
	public void setEquipmenttesting(String equipmenttesting) {
		this.equipmenttesting = equipmenttesting;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getExtinguisher() {
		return extinguisher;
	}
	public void setExtinguisher(String extinguisher) {
		this.extinguisher = extinguisher;
	}
	public String getUpkeep() {
		return upkeep;
	}
	public void setUpkeep(String upkeep) {
		this.upkeep = upkeep;
	}
	public String getServiceps() {
		return serviceps;
	}
	public void setServiceps(String serviceps) {
		this.serviceps = serviceps;
	}
	public String getWarehouseps() {
		return warehouseps;
	}
	public void setWarehouseps(String warehouseps) {
		this.warehouseps = warehouseps;
	}
	public String getRentalps() {
		return rentalps;
	}
	public void setRentalps(String rentalps) {
		this.rentalps = rentalps;
	}
	public String getSecurityps() {
		return securityps;
	}
	public void setSecurityps(String securityps) {
		this.securityps = securityps;
	}
	public String getCleansingps() {
		return cleansingps;
	}
	public void setCleansingps(String cleansingps) {
		this.cleansingps = cleansingps;
	}
	public String getProjectoutps() {
		return projectoutps;
	}
	public void setProjectoutps(String projectoutps) {
		this.projectoutps = projectoutps;
	}
	public String getRepairps() {
		return repairps;
	}
	public void setRepairps(String repairps) {
		this.repairps = repairps;
	}
	public String getFirefightingps() {
		return firefightingps;
	}
	public void setFirefightingps(String firefightingps) {
		this.firefightingps = firefightingps;
	}
	public String getEngineeringps() {
		return engineeringps;
	}
	public void setEngineeringps(String engineeringps) {
		this.engineeringps = engineeringps;
	}
	public String getEquipmenttestingps() {
		return equipmenttestingps;
	}
	public void setEquipmenttestingps(String equipmenttestingps) {
		this.equipmenttestingps = equipmenttestingps;
	}
	public String getMaterialps() {
		return materialps;
	}
	public void setMaterialps(String materialps) {
		this.materialps = materialps;
	}
	public String getExtinguisherps() {
		return extinguisherps;
	}
	public void setExtinguisherps(String extinguisherps) {
		this.extinguisherps = extinguisherps;
	}
	public String getUpkeepps() {
		return upkeepps;
	}
	public void setUpkeepps(String upkeepps) {
		this.upkeepps = upkeepps;
	}
	
	
}
