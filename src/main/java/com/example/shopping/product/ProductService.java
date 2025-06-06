package com.example.shopping.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public int productSave(ProductDto productDto, MultipartFile img){
        String fileName = ((int)(Math.random() * 899999) + 100000)+"-"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String path = System.getProperty("user.dir")+"/../img/"+fileName+"."+img.getContentType().replace("image/","");
        productDto.setProductId(fileName);
        productDto.setImg(fileName+"."+img.getContentType().replace("image/",""));

        try {
            img.transferTo(new File(path));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return productMapper.productSave(productDto);
    }

    public ProductList productList(int num){
        ProductList list = new ProductList();

        list.setProductDtos(productMapper.productList((num-1)*5));

        list.setProductTotal(productMapper.productTotal());

        if(num%10!=0)list.setStartNum(num-(num%10) + 1);
        else list.setStartNum(num-9);

        if(num%10!=0) {

            int endNum = num-(num%10)+10;
            int lastNum = list.getProductTotal()%5>0 ? list.getProductTotal()/5+1 : list.getProductTotal()/5;
            endNum = endNum < lastNum ? endNum : lastNum;
            list.setEndNum(endNum);
        }
        else list.setEndNum(num);

        return list;
    }

    public int productTotal(){
        return productMapper.productTotal();
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

    public int productDelete(List<String> products) {
        for(int i=0;i<products.size();i++){
            int ch = productMapper.productDelete(products.get(i));
            if(ch == 0) return ch;
        }
        return 0;
    }
}
