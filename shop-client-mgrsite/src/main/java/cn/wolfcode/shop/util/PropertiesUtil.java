package cn.wolfcode.shop.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 属性工具
 */
@Component
public class PropertiesUtil {

    @Value("${uploadpath}")
    private String uploadpath;

    public String getUploadpath() {
        return uploadpath;
    }

    public void setUploadpath(String uploadpath) {
        this.uploadpath = uploadpath;
    }

}
