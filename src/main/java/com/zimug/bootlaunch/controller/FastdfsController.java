package com.zimug.bootlaunch.controller;

import com.zimug.bootlaunch.config.exception.AjaxResponse;
import com.zimug.bootlaunch.config.exception.CustomException;
import com.zimug.bootlaunch.config.exception.CustomExceptionType;
import com.zimug.spring.fastdfs.FastDFSClientUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

@Controller
@RequestMapping("fastdfs")
public class FastdfsController {

    @Resource
    private FastDFSClientUtil fastDFSClientUtil;

    @PostMapping("/upload")
    @ResponseBody
    public AjaxResponse upload(@RequestParam("file") MultipartFile file) {

        try {
            String originalfileName = file.getOriginalFilename();
            String ext = originalfileName.substring(originalfileName.lastIndexOf("."));
            String fileId = fastDFSClientUtil.uploadFile(file.getBytes(), ext);

            Map<String, Object> map = new HashMap<>();
            map.put("fileId", fileId);
            map.put("url", fastDFSClientUtil.getSourceUrl(fileId));
            return AjaxResponse.success(map);
        } catch (Exception e) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "文件上传图片服务器失败");
        }
    }


    @DeleteMapping("/delete")
    @ResponseBody
    public AjaxResponse delete(@RequestParam String fileId) {
        try {
            byte[] imgfile = fastDFSClientUtil.downloadFile(fileId);
            System.out.println("文件大小：" + (imgfile != null ? imgfile.length : null));

            fastDFSClientUtil.delete(fileId);
        } catch (Exception e) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "文件删除失败");
        }
        return AjaxResponse.success();
    }


}
