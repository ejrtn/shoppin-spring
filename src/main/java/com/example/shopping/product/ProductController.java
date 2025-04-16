package com.example.shopping.product;

import com.example.shopping.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CommonService commonService;

    @PostMapping("productSave")
    public int productSave(ProductDto productDto, MultipartFile img){
        return productService.productSave(productDto,img);
    }

    @PostMapping("productList")
    public List<ProductDto> productList(String saleYn){
        return productService.productList(saleYn);
    }

    @PostMapping("productUpdate")
    public int productUpdate(ProductDto productDto){
        return productService.productUpdate(productDto);
    }

    @PostMapping("getProduct")
    @ResponseBody
    public ProductDto getProduct(String productId){
        return productService.getProduct(productId);
    }

    @GetMapping("imgLoad")
    @ResponseBody
    public byte[] imgLoad(String img){
        return commonService.imgLoad(img);
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("top10",productService.top10());
        return "index";
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
