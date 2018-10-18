package com.houseAgent.houserent.domain;

import java.util.Date;

import com.houseAgent.common.beans.BeanUtils;
import com.houseAgent.houserent.util.RentApplyStates;

public class ShowHouseRentDTO {
	private Long id;
    private String region;	//地区
    private Double rent;		//月租金
    private Double area;		//面积
    private Integer room;		//室
    private Integer hall;		//厅
    private Integer floor;		//楼层
    private String title;		//标题=地区+几室+几厅+有无卫生间+是否整租
    private String address;		//详细地址
    private String image;		//图片
    private String rentType;  //是否整租
    private Date publishTime;	//发布日期
	
    public static void entityToDto(HouseRent entity, ShowHouseRentDTO dto) {
    	BeanUtils.copyProperties(entity, dto);
    	dto.image = entity.getImages().split(",")[0];
    	dto.rentType = entity.getIsEntireRent() == true ? "整租" : "合租";
    }
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Double getRent() {
		return rent;
	}
	public void setRent(Double rent) {
		this.rent = rent;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Integer getRoom() {
		return room;
	}
	public void setRoom(Integer room) {
		this.room = room;
	}
	public Integer getHall() {
		return hall;
	}
	public void setHall(Integer hall) {
		this.hall = hall;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRentType() {
		return rentType;
	}
	public void setRentType(String rentType) {
		this.rentType = rentType;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Override
	public String toString() {
		return "ShowHouseRentDTO [id=" + id + ", region=" + region + ", rent=" + rent + ", area=" + area + ", room="
				+ room + ", hall=" + hall + ", floor=" + floor + ", title=" + title + ", address=" + address
				+ ", image=" + image + ", rentType=" + rentType + ", publishTime=" + publishTime + "]";
	}
}
