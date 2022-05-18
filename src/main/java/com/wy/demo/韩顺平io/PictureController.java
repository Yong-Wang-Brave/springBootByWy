package com.wy.demo.韩顺平io;


import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.wy.demo.Exception.ServiceException;
import com.wy.demo.lightspot.UnitedReturn.Result2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RequestMapping()
@RestController
public class PictureController {

    @PostMapping("/upload")
    public Result2<String> upload(@RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile == null || imageFile.isEmpty()) {
            log.info("上传图片为空");
        }
        uploadFile(imageFile);
        return Result2.sucess();
    }

    private void uploadFile(MultipartFile imageFile) {
        InputStream inputStream = null;
        try {
            inputStream = imageFile.getInputStream();
            //流只能被读取一次，需要用到两次（获取名字,上传一次），所以需要克隆一个流
            ByteArrayOutputStream byteArrayOutputStream = cloneInputStream(inputStream);
            ByteArrayInputStream type = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ByteArrayInputStream upload = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
           //获取后缀名
            String type1 = FileTypeUtil.getType(type);
            if (!StrUtil.equalsAnyIgnoreCase(type1, "jpg", "png", "jpeg")) {
                throw new ServiceException("上传图片格式不正确！");
            }

            String fileName = "wangyong"+ RandomUtil.randomString(8) + "." + type1;
            log.info("文件名称：{}", fileName);
            //文件上传
           // uoload(upload)



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ByteArrayOutputStream cloneInputStream(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
            return outputStream;
        } catch (IOException e) {
            throw new ServiceException("复制inputStream发生异常！", e);
        }
    }
}