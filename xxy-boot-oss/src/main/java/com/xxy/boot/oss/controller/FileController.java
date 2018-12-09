package com.xxy.boot.oss.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Value("${oss.root.path}")
	private String ossRootPath;
	
	@Value("${oss.domain.prefix}")
	private String ossDomainPrefix;
	
	
	
    //文件上传相关代码
    @RequestMapping(value = "upload/{type}")
    public String upload(@PathVariable("type") String type, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = ossRootPath+File.separator+type+File.separator;
        // 解决中文问题，liunx下中文路径，图片显示问题
        String newfileName = UUID.randomUUID().toString().replace("-", "").toLowerCase() + suffixName;
        File dest = new File(filePath + newfileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            String url=ossDomainPrefix+"dd/"+type+"/"+newfileName;
            return String.format("{\"code\":0, \"msg\":\"上传成功\",\"url\":\"%s\"}",url);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"code\":-1, \"msg\":\"上传失败\"}";
    }

    //文件下载相关代码
    @RequestMapping("/dd/{folder}/{filename:.+}")
    public void downloadFile(@PathVariable("folder") String folder, @PathVariable("filename") String filename, HttpServletResponse response){
    	//String filename="abc.png";
    	if(StringUtils.isEmpty(filename)){
    		return;
    	}
    	
    	File file = new File(ossRootPath, folder+'/'+filename);
        if (!file.exists()) {
            return;
        }
        
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" +  filename);// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

}
