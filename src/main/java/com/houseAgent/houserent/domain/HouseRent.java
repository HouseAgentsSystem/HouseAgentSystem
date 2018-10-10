package com.houseAgent.houserent.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.houseAgent.houserent.util.RentApplyStates;
import com.houseAgent.user.domain.User;

@Entity
@Table(name="t_houserent")
public class HouseRent {

	private Long id;
    private String loaction;	//地区
    private Double rent;		//月租金
    private Double area;		//面积
    private Integer room;		//室
    private Integer hall;		//厅
    private Integer floor;		//楼层
    private String decorateLevel;//装修
    private String title;		//标题
    private String address;		//详细地址
    private String images;		//图片
    private String video;		//视频
    private Boolean isElevator;	//is电梯
    private Boolean isParking;	//is停车位
    private Boolean isAirConditioning;	//Is空调
    private Boolean isRefrigerator;		//Is冰箱
    private Boolean isSoft;		//Is沙发
    private Boolean isTV;		//Is电视
    private String introduce;	// 详细介绍
    private Date publishTime;	//发布日期
    private RentApplyStates state;		//状态(正在审核，审核不通过，未售，已售，取消)
//    private Long customerId;	//客户外键
    private User user;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getLoaction() {
		return loaction;
	}
	public Double getRent() {
		return rent;
	}
	public Double getArea() {
		return area;
	}
	public Integer getRoom() {
		return room;
	}
	public Integer getHall() {
		return hall;
	}
	public Integer getFloor() {
		return floor;
	}
	@Column(name = "decorate_level")
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
	@Column(name = "is_elevator")
	public Boolean getIsElevator() {
		return isElevator;
	}
	@Column(name = "is_parking")
	public Boolean getIsParking() {
		return isParking;
	}
	@Column(name = "is_airconditioning")
	public Boolean getIsAirConditioning() {
		return isAirConditioning;
	}
	@Column(name = "is_refrigerator")
	public Boolean getIsRefrigerator() {
		return isRefrigerator;
	}
	@Column(name = "is_soft")
	public Boolean getIsSoft() {
		return isSoft;
	}
	@Column(name = "is_tv")
	public Boolean getIsTV() {
		return isTV;
	}
	public String getIntroduce() {
		return introduce;
	}
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_time")
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	public Date getPublishTime() {
		return publishTime;
	}
	@Enumerated(EnumType.STRING)
	public RentApplyStates getState() {
		return state;
	}
//	public Long getCustomerId() {
//		return customerId;
//	}
	@ManyToOne(cascade=CascadeType.REFRESH)
	public User getUser() {
		return user;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setLoaction(String loaction) {
		this.loaction = loaction;
	}
	public void setRent(Double rent) {
		this.rent = rent;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public void setRoom(Integer room) {
		this.room = room;
	}
	public void setHall(Integer hall) {
		this.hall = hall;
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
	public void setIsElevator(Boolean isElevator) {
		this.isElevator = isElevator;
	}
	public void setIsParking(Boolean isParking) {
		this.isParking = isParking;
	}
	public void setIsAirConditioning(Boolean isAirConditioning) {
		this.isAirConditioning = isAirConditioning;
	}
	public void setIsRefrigerator(Boolean isRefrigerator) {
		this.isRefrigerator = isRefrigerator;
	}
	public void setIsSoft(Boolean isSoft) {
		this.isSoft = isSoft;
	}
	public void setIsTV(Boolean isTV) {
		this.isTV = isTV;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public void setState(RentApplyStates state) {
		this.state = state;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
//	public void setCustomerId(Long customerId) {
//		this.customerId = customerId;
//	}
	
}
