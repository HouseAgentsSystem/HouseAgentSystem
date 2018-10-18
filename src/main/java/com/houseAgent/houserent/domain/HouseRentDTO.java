package com.houseAgent.houserent.domain;

public class HouseRentDTO extends HouseRentBaseDTO {
	
	private String rentType;
    private String userFaceImage;
    private String userRealname;
    private String userPhoneNumber;
    
    public static void entityToDto(HouseRent entity , HouseRentDTO dto) {
    	HouseRentBaseDTO.entityToDto(entity, dto);
    	dto.rentType = entity.getIsEntireRent() == true ? "整租" : "合租";
    	dto.userFaceImage = entity.getUser().getFaceImage();
    	dto.userRealname = entity.getUser().getRealname();
    	dto.userPhoneNumber = entity.getUser().getPhoneNumber();
    }
    
    public String getRentType() {
		return rentType;
	}
	public void setRentType(String rentType) {
		this.rentType = rentType;
	}
	public String getUserFaceImage() {
		return userFaceImage;
	}
	public void setUserFaceImage(String userFaceImage) {
		this.userFaceImage = userFaceImage;
	}
	public String getUserRealname() {
		return userRealname;
	}
	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	@Override
	public String toString() {
		return "HouseRentDTO [rentType=" + rentType + ", userFaceImage=" + userFaceImage + ", userRealname="
				+ userRealname + ", userPhoneNumber=" + userPhoneNumber + "]";
	}
}
