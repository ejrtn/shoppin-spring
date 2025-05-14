package com.example.shopping;

import com.example.shopping.delivery.DeliveryDto;
import com.example.shopping.delivery.DeliveryMapper;
import com.example.shopping.delivery.DeliveryService;
import com.example.shopping.product.ProductDto;
import com.example.shopping.product.ProductService;
import com.example.shopping.user.UserDto;
import com.example.shopping.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
//@Transactional
class ShoppingApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	DeliveryService deliveryService;


	@Test
	void contextLoads() {
		deliveryService.courierCompanyList();
		deliveryService.searchWaybill("2025-03-29 ~ 2025-03-29","1234567890","01");

	}

}
