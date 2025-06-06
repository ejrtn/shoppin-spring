package com.example.shopping.product;

import com.example.shopping.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CommonService commonService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("top10",productService.top10());
        return "index";
    }

    @PostMapping("/productSave")
    @ResponseBody
    public int productSave(@RequestPart("productDto") ProductDto productDto,@RequestPart("file") MultipartFile file){
        return productService.productSave(productDto,file);
    }

    @PostMapping("/productList")
    @ResponseBody
    public ProductList productList(@RequestParam int num){
        return productService.productList(num);
    }

    @PostMapping("/productDelete")
    @ResponseBody
    public int productDelete(@RequestParam List<String> products){
        return productService.productDelete(products);
    }

    @PostMapping("/productUpdate")
    public int productUpdate(ProductDto productDto){
        return productService.productUpdate(productDto);
    }

    @PostMapping("/getProduct")
    @ResponseBody
    public ProductDto getProduct(String productId){
        return productService.getProduct(productId);
    }

    @GetMapping("/imgLoad")
    @ResponseBody
    public byte[] imgLoad(String img){
        return commonService.imgLoad(img);
    }


    @GetMapping("/listAll/{category}")
    public String listAll(Model model, @PathVariable("category") String category){
        model.addAttribute("category",category);
        return "productList";
    }
    @GetMapping("/listAll/{category}/{subCategory}")
    public String listAll(Model model, @PathVariable("category") String category, @PathVariable("subCategory") String subCategory){
        model.addAttribute("category",category+"-"+subCategory);
        return "productList";
    }

    @PostMapping("/listAll")
    @ResponseBody
    public List<ProductDto> listAll(String category, String smallCategory, int startNum){
        return productService.listAll(category, smallCategory, startNum);
    }

    @GetMapping("/sell")
    public String sell(Model model,String productId){
        model.addAttribute("productId",productId);
        return "sell";
    }
}
