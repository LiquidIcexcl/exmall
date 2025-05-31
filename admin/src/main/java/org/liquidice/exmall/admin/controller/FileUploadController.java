package org.liquidice.exmall.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/v1/file")
public class FileUploadController {

    // 从配置文件中读取上传目录，也可以直接写死
    @Value("${upload.path:uploads/}")
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 检查文件是否为空
            if (file.isEmpty()) {
                return new ResponseEntity<>("上传的文件为空", HttpStatus.BAD_REQUEST);
            }

            // 生成唯一文件名，避免重名冲突
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // 创建上传目录（如果不存在）
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // 构建完整的文件路径
            Path filePath = uploadDir.resolve(fileName);

            // 保存文件到本地
            Files.write(filePath, file.getBytes());

            // 返回成功响应，包含文件路径等信息
            return ResponseEntity.ok().body("文件上传成功：" + fileName);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("文件上传失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}