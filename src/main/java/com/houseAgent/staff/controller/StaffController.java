package com.houseAgent.staff.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.domain.StaffDTO;
import com.houseAgent.staff.service.IStaffService;


@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private IStaffService staffService;
	
	@PostMapping
    public ExtAjaxResponse save(Staff staff, HttpSession session) {
		
    	try {
    		Staff manager = SessionUtil.getStaff(session);
    		staffService.save(staff, manager);
    		return new ExtAjaxResponse(true,"添加员工成功！");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"添加员工失败！！！");
	    }
    }
	
	@GetMapping
    public Page<StaffDTO> findAll(StaffDTO staffDTO, ExtjsPageRequest pageRequest, HttpSession session) {
		Staff staff = SessionUtil.getStaff(session);
//		Staff staff = staffService.findById(3L);
		if(staff.getPosition().equals("经理")) {
			System.out.println(staff.getStore());
			staffDTO.setStoreId(staff.getStore().getId());
		}
		System.out.println("staffdto: "+staffDTO.getStoreId());
		return staffService.findAll(StaffDTO.getWhereClause(staffDTO), pageRequest.getPageable());
	}
	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse  delete(@PathVariable("id") String id) {
		try {
			staffService.deleteById(id);
			return new ExtAjaxResponse (true,"成功删除！");
		} catch (Exception e) {
			return new ExtAjaxResponse (true,"删除失败！");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") String[] ids) 
	{
		try {
			if(ids!=null) {
				staffService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！！！");
		}
	}
	
	@GetMapping(value="{id}")
	public StaffDTO getOne(HttpSession session) 
	{
//		SessionUtil.setStaffId(session,5L);
		String id = SessionUtil.getStaffId(session);
		StaffDTO dto = staffService.findOne(id);
		return dto;
	}
	
	//管理员修改员工信息
	@PutMapping(value="{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse UpdateStaff(@PathVariable("id") String id,@RequestBody StaffDTO dto) {
		try {
			staffService.update(id, dto);
			return new ExtAjaxResponse(true,"修改成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"修改失败！！！");
		}
	}
	
	//员工修改个人信息
	@PostMapping(value="/updateInfo")
	public ExtAjaxResponse updateStaff1(StaffDTO dto) {
		try {
			staffService.update(dto.getId(), dto);
			return new ExtAjaxResponse(true,"修改成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"修改失败！！！");
		}
	}
	
	@PostMapping(value = "/upload")
	public ExtAjaxResponse upload(@RequestParam("faceImg") MultipartFile file,HttpSession session,HttpServletRequest request) throws IOException { 
		String fileName = ""; 
		File filedir; 
		System.out.println("上传文件controller");
		try {
			if(!("").equals(file.getOriginalFilename())&&file !=null) {
	    	   //获得文件名
	           fileName = file.getOriginalFilename();
	           System.out.println(fileName);
	           String filePath = request.getSession().getServletContext()
                       .getRealPath("/")
                       + "Customer/upload/staff/";
	           filedir = new File(filePath);
	           if(!filedir.exists()) {
	        	   filedir.mkdirs();
	           }
	           //将附件保存
	           file.transferTo(new File(filePath+fileName));
			}
			
//			SessionUtil.setStaffId(session,5L);//得删除
			String id = SessionUtil.getStaffId(session);
			StaffDTO dto = staffService.findOne(id);
			dto.setFaceImg(fileName);
			Staff staff = new Staff();
			StaffDTO.dtoToEntity(dto, staff);
			staffService.saveAndUpdate(staff);
			return new ExtAjaxResponse(true,"上传成功！");
			
		} catch (Exception e) {
			return new ExtAjaxResponse(false,"上传失败！");
		}
	}
	@GetMapping(value="/findMyInfo")
	public StaffDTO getOne2(HttpSession session){
		String id = SessionUtil.getStaffId(session);
		StaffDTO dto = staffService.findOne(id);
		return dto;
	}
}