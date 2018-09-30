package com.houseAgent.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.common.beans.BeanUtils;
import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.domain.StaffDTO;
import com.houseAgent.staff.service.IStaffService;


@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private IStaffService staffService;
	
	@PostMapping
    public ExtAjaxResponse save(Staff staff) {
		
    	try {
    		staffService.saveAndUpdate(staff);
    		return new ExtAjaxResponse(true,"添加员工成功！");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"添加员工失败！！！");
	    }
    }
	
	@GetMapping
    public Page<Staff> findAll(StaffDTO staffDTO,ExtjsPageRequest pageRequest) {
		return staffService.findAll(StaffDTO.getWhereClause(staffDTO), pageRequest.getPageable());
	}
	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse  delete(@PathVariable("id") long id) {
		try {
			staffService.deleteById(id);
			return new ExtAjaxResponse (true,"成功删除！");
		} catch (Exception e) {
			return new ExtAjaxResponse (true,"删除失败！");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) 
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
	public Staff getOne(@PathVariable("id") Long id) 
	{
		return staffService.findOne(id);
	}
	
	@PutMapping(value="{id}")
	public ExtAjaxResponse UpdateOrder(@PathVariable("id") Long Id,@RequestBody Staff dto) {
		try {
			Staff entity = staffService.findOne(Id);
			BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
			/*System.out.println(dto);*/
			staffService.saveAndUpdate(entity);
			return new ExtAjaxResponse(true,"修改成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"修改失败！！！");
		}
	}
}