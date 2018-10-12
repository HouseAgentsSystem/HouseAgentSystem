package com.houseAgent.houserent.domain;

import java.util.Date;

import com.houseAgent.common.beans.BeanUtils;
import com.houseAgent.houserent.util.RentApplyStates;

public class HouseRentBaseDTO {
	private Long id;
    private String region;	//地区
    private Double rent;		//月租金
    private Double area;		//面积
    private Integer room;		//室
    private Integer hall;		//厅
    private Integer floor;		//楼层
    private String title;		//标题=地区+几室+几厅+有无卫生间+是否整租
    private String address;		//详细地址
    private String images;		//图片
    private String video;		//视频
    private Boolean isEntireRent;  //是否整租
    private Boolean isbed;	//is床
    private Boolean isWasher;	//is洗衣机
    private Boolean isAirConditioning;	//Is空调
    private Boolean isBalcony;  //is阳台
    private Boolean isRefrigerator;		//Is冰箱
    private Boolean isToilet;		//Is厕所
    private Boolean isKitchen;		//Is厨房
    private Boolean isCalorifier;	//is热水器
    private String introduce;	// 详细介绍
    private Date applyTime;		//申请日期
    private Date publishTime;	//发布日期
    private RentApplyStates state;		//状态(正在审核，审核不通过，未售，已售，取消)
    
    public static void entityToDto(HouseRent entity , HouseRentBaseDTO dto) {
    	BeanUtils.copyProperties(entity, dto);
    	String eSupportings = entity.getSupporting();
    	if(eSupportings!=null) {
    		String[] supportings = eSupportings.split(",");
        	dto.isbed = supportings[0].equals("1") ? true : false;
        	dto.isWasher = supportings[1].equals("1") ? true : false;
        	dto.isAirConditioning = supportings[2].equals("1") ? true : false;
        	dto.isBalcony = supportings[3].equals("1") ? true : false;
        	dto.isRefrigerator = supportings[4].equals("1") ? true : false;
        	dto.isToilet = supportings[5].equals("1") ? true : false;
        	dto.isKitchen = supportings[6].equals("1") ? true : false;
        	dto.isCalorifier = supportings[7].equals("1") ? true : false;
    	}
    }
    
    public static void dtoToEntity(HouseRentBaseDTO dto, HouseRent entity) {
    	BeanUtils.copyProperties(dto, entity);
    	String[] supportings = entity.getSupporting().split(",");
    	if(dto.isbed != null) {
    		supportings[0] = (dto.isbed == true) ? "1," : "0,";
    	}
    	if(dto.isWasher != null) {
    		supportings[1] = (dto.isWasher == true) ? "1," : "0,";
    	}
    	if(dto.isAirConditioning != null) {
    		supportings[2] = (dto.isAirConditioning == true) ? "1," : "0,";
    	}
    	if(dto.isBalcony != null) {
    		supportings[3] = (dto.isBalcony == true) ? "1," : "0,";
    	}
    	if(dto.isRefrigerator != null) {
    		supportings[4] = (dto.isRefrigerator == true) ? "1," : "0,";
    	}
    	if(dto.isToilet != null) {
    		supportings[5] = (dto.isToilet == true) ? "1," : "0,";
    	}
    	if(dto.isKitchen != null) {
    		supportings[6] = (dto.isKitchen == true) ? "1," : "0,";
    	}
    	if(dto.isCalorifier != null) {
    		supportings[7] = (dto.isCalorifier == true) ? "1," : "0,";
    	}
    	String supportingsNew = String.join(",", supportings);
    	entity.setSupporting(supportingsNew);
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
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public Boolean getIsEntireRent() {
		return isEntireRent;
	}
	public void setIsEntireRent(Boolean isEntireRent) {
		this.isEntireRent = isEntireRent;
	}
	public Boolean getIsbed() {
		return isbed;
	}
	public void setIsbed(Boolean isbed) {
		this.isbed = isbed;
	}
	public Boolean getIsWasher() {
		return isWasher;
	}
	public void setIsWasher(Boolean isWasher) {
		this.isWasher = isWasher;
	}
	public Boolean getIsAirConditioning() {
		return isAirConditioning;
	}
	public void setIsAirConditioning(Boolean isAirConditioning) {
		this.isAirConditioning = isAirConditioning;
	}
	public Boolean getIsBalcony() {
		return isBalcony;
	}
	public void setIsBalcony(Boolean isBalcony) {
		this.isBalcony = isBalcony;
	}
	public Boolean getIsRefrigerator() {
		return isRefrigerator;
	}
	public void setIsRefrigerator(Boolean isRefrigerator) {
		this.isRefrigerator = isRefrigerator;
	}
	public Boolean getIsToilet() {
		return isToilet;
	}
	public void setIsToilet(Boolean isToilet) {
		this.isToilet = isToilet;
	}
	public Boolean getIsKitchen() {
		return isKitchen;
	}
	public void setIsKitchen(Boolean isKitchen) {
		this.isKitchen = isKitchen;
	}
	public Boolean getIsCalorifier() {
		return isCalorifier;
	}
	public void setIsCalorifier(Boolean isCalorifier) {
		this.isCalorifier = isCalorifier;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public RentApplyStates getState() {
		return state;
	}
	public void setState(RentApplyStates state) {
		this.state = state;
	}
}
