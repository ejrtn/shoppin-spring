package com.example.shopping.delivery;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class CourierCompanyService {

    @Autowired
    CourierCompanyMapper courierCompanyMapper;

    // 스마트택배 API
    // https://tracking.sweettracker.co.kr:8443/templates/app.html#/
    public String courierCompanyList() {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            //카카오에게 보낼 url
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.exchange(
                    "https://info.sweettracker.co.kr/api/v1/companylist?t_key=vwJzrg9wK1IFaVl53qmJPA",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );
            JSONObject rjson = new JSONObject(response.getBody());
            JSONArray company = rjson.getJSONArray("Company");
            for(int i=0;i<company.length();i++) {
                if(i == 0){
                    courierCompanyMapper.courierCompanyCodeTruncate();
                }
                CourierCompanyDto courierCompanyDto = new CourierCompanyDto();
                JSONObject companyJson = company.getJSONObject(i);
                courierCompanyDto.setCompanyName(companyJson.getString("Name"));
                courierCompanyDto.setCompanyCode(companyJson.getString("Code"));
                courierCompanyDto.setInternational(companyJson.getString("International"));
                int result = courierCompanyMapper.courierCompanyCodeSave(courierCompanyDto);
                if(result == 0){
                    return "0";
                }
            }
            return "1";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String searchWaybill(String trackNumber,String companyName){
        try {

            String companyCode = courierCompanyMapper.courierCode(companyName);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            //카카오에게 보낼 url
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://info.sweettracker.co.kr/api/v1/trackingInfo?t_key=vwJzrg9wK1IFaVl53qmJPA&t_invoice="+trackNumber+"&t_code="+companyCode,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );

            JSONObject rjson = new JSONObject(response.getBody());

            return rjson.toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
