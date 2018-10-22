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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.houseAgent.common.util.DynamicUtil;
import com.houseAgent.common.util.ExcelUtil;
import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.house.domain.HouseQueryDTO;
import com.houseAgent.house.service.HouseService;


@RestController
@RequestMapping("/house")
public class HouseController {
	@Autowired
	private HouseService houseService;
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
		System.out.println("54we54r1w5r1");
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
	public ExtAjaxResponse rarUpload(@RequestParam(value = "file", required = true) MultipartFile file) {
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
                String Parent="E:\\test\\"; //输出路径（文件夹目录
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
	@RequestMapping("/imagesUpload")
    public String imagesUpload(@RequestParam(value = "fileList", required = true) MultipartFile[] files,
            HttpServletRequest request) {
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
                        + "Customer/images/" + file.getOriginalFilename();
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
                        + "Customer/video/" + file.getOriginalFilename();
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
