package com.example.shopping.common;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

@Service
public class CommonService {

    public byte[] imgLoad(String img){
        try {
            FileInputStream fis = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            String filePath = System.getProperty("user.dir")+"\\..\\img\\"+img;
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
}
