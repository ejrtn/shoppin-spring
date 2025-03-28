package com.example.shopping.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public boolean productSave(ProductDto productDto){
        return productMapper.productSave(productDto);
    }

    public List<ProductDto> productList(String saleYn){
        return productMapper.productList(saleYn);
    }

    public boolean productUpdate(ProductDto productDto){
        return productMapper.productUpdate(productDto);
    }

    public int productMinus(String productId, int cnt){
        return productMapper.productMinus(productId, cnt);
    }

    public int productPlus(String productId, int cnt){
        return productMapper.productPlus(productId, cnt);
    }
}
