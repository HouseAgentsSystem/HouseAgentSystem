package com.houseAgent.houserent.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.house.domain.HouseQueryDTO;
import com.houseAgent.houserent.domain.HouseRentBaseDTO;
import com.houseAgent.houserent.service.HouseRentService;

@RestController
@RequestMapping("/houseRent")
public class HouseRentController {
	@Autowired
	private HouseRentService houseRentService;
	@GetMapping
	public Page<HouseRentBaseDTO> getPage(ExtjsPageRequest pageRequest) 
	{
		return houseRentService.findAll(pageRequest.getPageable());
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
                        + "Customer/upload/houseRent/" + file.getOriginalFilename();
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
                        + "Customer/upload/houseRent/" + file.getOriginalFilename();
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
