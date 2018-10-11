package com.houseAgent.houserent.domain;

public class HouseRentDTO extends HouseRentBaseDTO {
	
    private String userFaceImage;
    private String userRealname;
    private String userPhoneNumber;
    
    public static void entityToDto(HouseRent entity , HouseRentDTO dto) {
    	HouseRentBaseDTO.entityToDto(entity, dto);
    	dto.userFaceImage = entity.getUser().getFaceImage();
    	dto.userRealname = entity.getUser().getRealname();
    	dto.userPhoneNumber = entity.getUser().getPhoneNumber();
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
}
