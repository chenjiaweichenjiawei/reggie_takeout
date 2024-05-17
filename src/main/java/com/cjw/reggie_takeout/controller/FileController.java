package com.cjw.reggie_takeout.controller;

import com.cjw.reggie_takeout.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传和下载
 *
 * @author CJW
 * @date 2023/4/11
 */
@RestController
@RequestMapping("/common")
public class FileController {
    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //将原始文件名称的后缀拿出来加入生成的文件名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用UUID重新生成文件名称，防止文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;
        //如果该目录不存在，先创建目录在再保存文件
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //把文件保存到我们设置的路径和文件名中
        file.transferTo(new File(basePath + fileName));
        return Result.success(fileName);
    }

    /**
     * 文件下载
     *
     * @param name
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try {
            //通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            //通过输出流将文件写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            //关闭资源
            fileInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}






















