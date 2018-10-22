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
    private String supporting;  //配套设施
    private String introduce;	// 详细介绍
    private Date applyTime;		//申请日期
    private Date publishTime;	//发布日期
    private RentApplyStates state;		//状态(正在审核，审核不通过，未售，已售，取消)
//    private Long customerId;	//客户外键
    private User user;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getRegion() {
		return region;
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
	public Boolean getIsEntireRent() {
		return isEntireRent;
	}
	public String getSupporting() {
		return supporting;
	}
	public String getIntroduce() {
		return introduce;
	}
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "apply_time")
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	public Date getApplyTime() {
		return applyTime;
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
	public void setRegion(String region) {
		this.region = region;
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
	public void setIsEntireRent(Boolean isEntireRent) {
		this.isEntireRent = isEntireRent;
	}
	public void setSupporting(String supporting) {
		this.supporting = supporting;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public void setState(RentApplyStates state) {
		this.state = state;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "HouseRent [id=" + id + ", region=" + region + ", rent=" + rent + ", area=" + area + ", room=" + room
				+ ", hall=" + hall + ", floor=" + floor + ", title=" + title + ", address=" + address + ", images="
				+ images + ", video=" + video + ", isEntireRent=" + isEntireRent + ", supporting=" + supporting
				+ ", introduce=" + introduce + ", applyTime=" + applyTime + ", publishTime=" + publishTime + ", state="
				+ state + ", user=" + user + "]";
	}
	
}
