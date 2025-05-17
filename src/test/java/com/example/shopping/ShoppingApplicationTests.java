package com.example.shopping;

import com.example.shopping.delivery.CourierCompanyService;
import com.example.shopping.delivery.DeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Transactional
class ShoppingApplicationTests {

	@Autowired
	DeliveryService deliveryService;

	@Autowired
	CourierCompanyService courierCompanyService;

	@Test
	void contextLoads() {
		String s = courierCompanyService.searchWaybill("91198159824","로젠택배");
	}

}
