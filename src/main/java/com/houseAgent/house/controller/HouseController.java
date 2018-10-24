package com.houseAgent.house.controller;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.houseAgent.common.beans.BeanUtils;
import com.houseAgent.common.util.DynamicUtil;
import com.houseAgent.common.util.ExcelUtil;
import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.house.domain.HouseQueryDTO;
import com.houseAgent.house.service.HouseService;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.service.StaffService;


@RestController
@RequestMapping("/house")
public class HouseController {
	@Autowired
	private HouseService houseService;
	@Autowired
	private StaffService staffService;
	@GetMapping
	public Page<HouseDTO> getPage(HouseQueryDTO houseQueryDTO ,ExtjsPageRequest pageRequest) 
	{
		return houseService.findAll(HouseQueryDTO.getWhereClause(houseQueryDTO),pageRequest.getPageable());
	}
	@GetMapping("/management")
	public Page<HouseDTO> showHouse(ExtjsPageRequest pageRequest) 
	{
		return houseService.showAll(pageRequest.getPageable());
	}
	@RequestMapping("/excelupload")
	@ResponseBody
	public ExtAjaxResponse excelUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) {
		//System.out.println(file);
		if(!file.isEmpty()){
			//MultipartFile 转 file
			File excelfile = null;
			try {
				excelfile=File.createTempFile("tmp", null);
			    file.transferTo(excelfile);
			    excelfile.deleteOnExit();        
			} catch (IOException e) {
			    e.printStackTrace();
			}
			System.out.println(excelfile);
			List<List> datas = new ArrayList<>();
			if (file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$")) {
	            datas = ExcelUtil.getData(excelfile, false);
	        } else {
	        	datas = ExcelUtil.getData(excelfile, true);
	        }
			List<Object> propertyName = datas.get(0);
			datas.remove(0);
			//System.out.println(datas);
			for (List data : datas) {
				System.out.println(data);
				House house = new House();
				DynamicUtil.dynamicSet(house, propertyName, data);
				//Staff staff = SessionUtil.getStaff(session);
				//house.setStaff(staff);
				house.setStaff(null);
				house.setStore(null);
				houseService.addOneHouse(house);
			}
			return new ExtAjaxResponse(true, "数据导入成功！");
		}else{
			return new ExtAjaxResponse(false, "数据导入失败！");
		}
	}
	@RequestMapping("/rarupload")
	@ResponseBody
	public ExtAjaxResponse rarUpload(@RequestParam(value = "file", required = true) MultipartFile file,HttpServletRequest request) {
		//System.out.println(file);
		String fileName = file.getOriginalFilename();
        System.out.println("fileName:"+fileName);
        try {
            // 得到输入流（字节流）对象
            InputStream fileInputStream = file.getInputStream();
            // 文件的扩展名
            String extension = FilenameUtils.getExtension(fileName);
            if (extension.equals("zip") || extension.equals("rar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                System.out.println("1");
                BufferedInputStream Bin = new BufferedInputStream(zip);
                String Parent=request.getSession().getServletContext()
                        .getRealPath("/")+ "Customer/upload/house"; //输出路径（文件夹目录
                File Fout=null; 
                ZipEntry entry; 
                System.out.println("2");
                while((entry = zip.getNextEntry())!=null && !entry.isDirectory()){ 
                 Fout=new File(Parent,entry.getName()); 
                 System.out.println("3");
                 if(!Fout.exists()){ 
                  (new File(Fout.getParent())).mkdirs(); 
                 } 
                 FileOutputStream out=new FileOutputStream(Fout); 
                 BufferedOutputStream Bout=new BufferedOutputStream(out); 
                 int b; 
                 while((b=Bin.read())!=-1){ 
                  Bout.write(b); 
                 } 
                 Bout.close(); 
                 out.close(); 
                 System.out.println(Fout+"解压成功");  
                } 
                Bin.close(); 
                zip.close(); 

            } else {
            }
            return new ExtAjaxResponse(true,"部署成功!");
        } catch (Exception e) {
            
            return new ExtAjaxResponse(false,"部署失败!");
        }
	}
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) 
	{
		try {
			if(id!=null) {
				houseService.deleteById(id);
			}
			return new ExtAjaxResponse(true,"删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"删除失败！");
		}
	}
	@PutMapping(value="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update(@PathVariable("id") Long myId,@RequestBody HouseDTO dto) 
	{
		System.out.println(dto.getStaffId());
		try {
			House entity = houseService.findById(myId);
			if(entity!=null) {
				BeanUtils.copyProperties(dto,entity);//使用自定义的BeanUtils
				entity.setStaff(staffService.findById(dto.getStaffId()));
				entity.setStore(staffService.findById(dto.getStaffId()).getStore());
				houseService.updata(entity);
				//System.out.println(entity);
			}
			return new ExtAjaxResponse(true,"更新成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"更新失败！");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) 
	{
		try {
			if(ids!=null) {
				houseService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	@RequestMapping("/imagesUpload")
    public String imagesUpload(@RequestParam(value = "fileList", required = true) MultipartFile[] files,
            HttpServletRequest request) {
		System.out.println(request.getSession().getMaxInactiveInterval());
        List<String> list = new ArrayList<String>();
        System.out.println(files.length);
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                list = saveFile(request, file, list);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
            return list.get(i);
        }
        return null;
    }
	@RequestMapping("/vedioUpload")
	@ResponseBody
    public ExtAjaxResponse vedioUpload(@RequestParam(value = "fileList", required = true) MultipartFile[] files,
            HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        System.out.println(files.length);
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                list = saveFile2(request, file, list);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
		return null;
    }
    private List<String> saveFile(HttpServletRequest request,
            MultipartFile file, List<String> list) {
        if (!file.isEmpty()) {
            try {
                String filePath = request.getSession().getServletContext()
                        .getRealPath("/")
                        + "Customer/upload/house/" + file.getOriginalFilename();
                System.out.println(filePath);
                list.add(file.getOriginalFilename());
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    private List<String> saveFile2(HttpServletRequest request,
            MultipartFile file, List<String> list) {
        if (!file.isEmpty()) {
            try {
                String filePath = request.getSession().getServletContext()
                        .getRealPath("/")
                        + "Customer/upload/house/" + file.getOriginalFilename();
                System.out.println(filePath);
                list.add(file.getOriginalFilename());
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
