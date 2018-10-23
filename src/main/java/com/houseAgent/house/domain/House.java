package com.houseAgent.house.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.domain.StaffDTO;
import com.houseAgent.store.domain.Store;
@Entity
@Table(name="t_house")
public class House {
	private Long id;
	private String region;//地区
	private Double price;//价格
	private Double area;//面积
	private String type;//房型
	private String buildDate;//建造时间
	private Integer room;//室
	private Integer hall;//厅
	private String orientation;//朝向
	private Integer floor;//楼层
	private String decorateLevel;//装修等级
	private String title;//标题
	private String address;//详细地址
	private String images;
	private String video;
	private Double longitude;//经度
	private Double latitude;//纬度
	private Integer isElevator;//是否有电梯
	private Integer isParking;//是否有停车位
	private String introduce;//详细介绍，核心卖点
	private Double agencyFees;//中介费
	private Integer state;//状态
	private Integer propertyRights;//产权年限
	private String ownerName;//房主姓名
	private String ownerPhoneNumber;//房主联系方式
	private Staff staff;//员工
	private Store store;//门店 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public String getRegion() {
		return region;
	}

	public Double getPrice() {
		return price;
	}
	public Double getArea() {
		return area;
	}
	public String getType() {
		return type;
	}
	public String getBuildDate() {
		return buildDate;
	}
	public Integer getRoom() {
		return room;
	}
	public Integer getHall() {
		return hall;
	}
	public String getOrientation() {
		return orientation;
	}
	public Integer getFloor() {
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
	public Double getLongitude() {
		return longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public Integer getIsElevator() {
		return isElevator;
	}
	public Integer getIsParking() {
		return isParking;
	}
	public String getIntroduce() {
		return introduce;
	}
	public Double getAgencyFees() {
		return agencyFees;
	}
	public Integer getState() {
		return state;
	}
	public Integer getPropertyRights() {
		return propertyRights;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public String getOwnerPhoneNumber() {
		return ownerPhoneNumber;
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
	
	public void setRegion(String region) {
		this.region = region;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	public void setRoom(Integer room) {
		this.room = room;
	}
	public void setHall(Integer hall) {
		this.hall = hall;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public void setFloor(Integer floor) {
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
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public void setIsElevator(Integer isElevator) {
		this.isElevator = isElevator;
	}
	public void setIsParking(Integer isParking) {
		this.isParking = isParking;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public void setAgencyFees(Double agencyFees) {
		this.agencyFees = agencyFees;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setPropertyRights(Integer propertyRights) {
		this.propertyRights = propertyRights;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public void setOwnerPhoneNumber(String ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", region=" + region + ", price=" + price + ", area=" + area + ", type=" + type
				+ ", buildDate=" + buildDate + ", room=" + room + ", hall=" + hall + ", orientation=" + orientation
				+ ", floor=" + floor + ", decorateLevel=" + decorateLevel + ", title=" + title + ", address=" + address
				+ ", images=" + images + ", video=" + video + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", isElevator=" + isElevator + ", isParking=" + isParking + ", introduce=" + introduce
				+ ", agencyFees=" + agencyFees + ", state=" + state + ", propertyRights=" + propertyRights
				+ ", ownerName=" + ownerName + ", ownerPhoneNumber=" + ownerPhoneNumber + ", staff=" + staff
				+ ", store=" + store + "]";
	}
}
