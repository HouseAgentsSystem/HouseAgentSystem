package com.houseAgent.house.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.houseAgent.staff.domain.Staff;
import com.houseAgent.store.domain.Store;
@Entity
@Table(name="t_house")
public class House {
	private Long id;
	private String location;//地区
	private double price;//价格
	private double area;//面积
	private String type;//房型
	private String buildDate;//建造时间
	private int room;//室
	private int hall;//厅
	private String orientation;//朝向
	private int floor;//楼层
	private String decorateLevel;//装修等级
	private String title;//标题
	private String address;//详细地址
	private String images;
	private String video;
	private double longitude;//经度
	private double latitude;//纬度
	private int isElevator;//是否有电梯
	private int isParking;//是否有停车位
	private String introduce;//详细介绍，核心卖点
	private double agencyFees;//中介费
	private int state;//状态
	private int propertyRights;//产权年限
	private Staff staff;//员工
	private Store store;//门店 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getLocation() {
		return location;
	}
	public double getPrice() {
		return price;
	}
	public double getArea() {
		return area;
	}
	public String getType() {
		return type;
	}
	public String getBuildDate() {
		return buildDate;
	}
	public int getRoom() {
		return room;
	}
	public int getHall() {
		return hall;
	}
	public String getOrientation() {
		return orientation;
	}
	public int getFloor() {
		return floor;
	}
	public String getDecorateLevel() {
		return decorateLevel;
	}
	public String getTitle() {
		return title;
	}
	public String getAddress() {
		return address;
	}
	public String getImages() {
		return images;
	}
	public String getVideo() {
		return video;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public int getIsElevator() {
		return isElevator;
	}
	public int getIsParking() {
		return isParking;
	}
	public String getIntroduce() {
		return introduce;
	}
	public double getAgencyFees() {
		return agencyFees;
	}
	public int getState() {
		return state;
	}
	public int getPropertyRights() {
		return propertyRights;
	}
	@ManyToOne(cascade=CascadeType.REFRESH)
	public Staff getStaff() {
		return staff;
	}
	@ManyToOne(cascade=CascadeType.REFRESH)
	public Store getStore() {
		return store;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public void setHall(int hall) {
		this.hall = hall;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public void setDecorateLevel(String decorateLevel) {
		this.decorateLevel = decorateLevel;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setIsElevator(int isElevator) {
		this.isElevator = isElevator;
	}
	public void setIsParking(int isParking) {
		this.isParking = isParking;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public void setAgencyFees(double agencyFees) {
		this.agencyFees = agencyFees;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setPropertyRights(int propertyRights) {
		this.propertyRights = propertyRights;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
	
}
