package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.api.CommonResult;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/api/common")
public class CommonController extends BaseController {

    @PostMapping("upload")
    public CommonResult<String> upload(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        InputStream inputStream = file.getInputStream();
        String contentType = file.getContentType();
        Resource resource = file.getResource();
        String filename = resource.getFilename();
        long size = file.getSize();
        System.out.println(contentType);
        System.out.println(resource);
        System.out.println(size);
        System.out.println(filename);
        File tempFile = File.createTempFile("123",".txt");
        System.out.println(tempFile.getName());

        return CommonResult.success("上传成功");
    }
}
