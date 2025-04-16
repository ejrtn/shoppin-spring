package com.example.shopping.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public int productSave(ProductDto productDto, MultipartFile img){
        String path = System.getProperty("user.dir")+"\\..\\img\\"+img.getOriginalFilename();
        productDto.setImg(img.getOriginalFilename());
        try {
            img.transferTo(new File(path));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return productMapper.productSave(productDto);
    }

    public List<ProductDto> productList(String saleYn){
        return productMapper.productList(saleYn);
    }

    public int productUpdate(ProductDto productDto){
        return productMapper.productUpdate(productDto);
    }

    public List<ProductDto> top10(){
        return productMapper.top10();
    };

    public List<ProductDto> listAll(String category, String smallCategory, int startNum){
        return productMapper.listAll(category,smallCategory,startNum);
    }

    public ProductDto getProduct(String productId) {
        return productMapper.getProduct(productId);
    }
}
