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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@SpringBootTest
@Transactional
class ShoppingApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	DeliveryService deliveryService;


	@Test
	void contextLoads() {
		System.out.println("======================= 사용자 저장 =======================");
		UserDto userDto = new UserDto();
		userDto.setName("유덕수");
		userDto.setUserId("ejrtn153");
		userDto.setPassword("ejrtn153");
		userDto.setAddress("서울");
		userDto.setGender("남성");
		userDto.setEmail("dlstn153@naver.com");
		userDto.setPhonNumber("010-0000-0000");
		userDto.setYyyymmdd("19930610");
		userService.userSave(userDto);


		Map<String,String> login = userService.login("ejrtn153","ejrtn153");
		System.out.println("로그인 완료 결과 : "+login);
		login = userService.login("ejrtn153","ejrtn");
		System.out.println("로그인 실패 결과 : "+login);


		userService.passwordUpdate("ejrtn153","ejrtn153","ejrtn");
		login = userService.login("ejrtn153","ejrtn");
		System.out.println("패스워드 변경 결과 : "+login);
		userService.userDelete("ejrtn153");
		login = userService.login("ejrtn153","ejrtn");
		System.out.println("아이디 삭제 결과 : "+login);

		int findId = userService.findId("유덕수","sd");
		System.out.println("아이디 찾기 입력(유덕수, sd) 결과 : "+(findId==1));
		findId = userService.findId("유덕수","dlstn153@naver.com");
		System.out.println("아이디 찾기 입력(유덕수, dlstn153@naver.com) 결과 : "+(findId==1));

		int findPassword = userService.findPassword("유덕수","sd","ejrtn153");
		System.out.println("패스워드 찾기 입력(유덕수, sd, ejrtn153) 결과 : "+(findPassword==1));
		findPassword = userService.findPassword("유덕수","dlstn153@naver.com","ejrtn153");
		System.out.println("패스워드 찾기 입력(유덕수, sd, ejrtn153) 결과 : "+(findPassword==1));


		System.out.println();
		System.out.println();
		System.out.println("======================= 제품 저장 =======================");
		ProductDto productDto = new ProductDto();
		productDto.setCategory("바지");
		String productId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		productDto.setProductId(productId);
		productDto.setCnt(10);
		productDto.setPrice(100000);
		productDto.setExplanation("슬림바지");
		productDto.setProductName("바지");
		productService.productSave(productDto);
		System.out.println(productService.productList("Y"));

		System.out.println("======================= 제품 판매여부(saleYn) 불가로 변경 =======================");
		productDto.setSaleYn("N");
		productService.productUpdate(productDto);
		System.out.println(productService.productList("N"));
		System.out.println("======================= 제품 개수(cnt) -10 =======================");
		productService.productMinus(productId,10);
		System.out.println(productService.productList("N"));
		System.out.println("======================= 제품 개수(cnt) +100 =======================");
		productService.productPlus(productId,100);
		System.out.println(productService.productList("N"));



		System.out.println();
		System.out.println();
		System.out.println("======================= 주문 저장 =======================");
		DeliveryDto deliveryDto = new DeliveryDto();
		deliveryDto.setAddress("받는 주소");
		String deliveryId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		deliveryDto.setDeliveryId(deliveryId);
		deliveryDto.setStatus("배송중");
		deliveryDto.setUserId("ejrtn153");
		deliveryDto.setDetail("{result:" +
									"{" +
										"바지:{수량:10,가격:200000}," +
										"셔츠:{수량:20,가격:400000}" +
									"}" +
								"총가격:600000" +
								"}");
		deliveryDto.setPriceTotal(600000);
		deliveryService.deliverySave(deliveryDto);
		System.out.println(deliveryService.deliveryList(deliveryDto));
		System.out.println("======================= status 배송중 >> 배송완료 수정 =======================");
		deliveryDto.setStatus("배송완료");
		deliveryService.deliveryUpdate(deliveryDto);
		System.out.println(deliveryService.deliveryList(deliveryDto));


	}

}
