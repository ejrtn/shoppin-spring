package com.example.shopping.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

@Service
public class CommonService {

    public byte[] imgLoad(String img){
        try {
            FileInputStream fis = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            String filePath = System.getProperty("user.dir")+"/../img/"+img;
            fis = new FileInputStream(filePath);

            int readCount = 0;
            byte[] buffer = new byte[1024];
            byte[] fileArray = null;

            while((readCount = fis.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();

            return fileArray;
        }catch (Exception e){
            throw new RuntimeException("File Error");
        }
    }
    public String fileSave(){
        return "";
    }
    public String getClientIP(HttpServletRequest request) {
        String clientIp = "";
        clientIp = request.getHeader("X-Forwarded-For");

        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("X-Real-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("X-RealIP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("REMOTE_ADDR");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }
}
