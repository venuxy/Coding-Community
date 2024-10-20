package com.venux.oss.config;

import com.venux.oss.adapter.AliStorageAdapter;
import com.venux.oss.adapter.MinioStorageAdapter;
import com.venux.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件存储config
 */
@Configuration
public class StorageConfig {

    @Value("${storage.service.type}")
    private String storageType;

    @Bean
    
    public StorageAdapter storageService() {
        if ("minio".equals(storageType)) {
            return new MinioStorageAdapter();
        } else if ("aliyun".equals(storageType)) {
            return new AliStorageAdapter();
        } else {
            throw new IllegalArgumentException("未找到对应的文件存储处理器");
        }
    }

}