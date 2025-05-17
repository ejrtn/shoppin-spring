package com.example.shopping.delivery;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourierCompanyMapper {
    int courierCompanyCodeSave(CourierCompanyDto courierCompanyDto);

    int courierCompanyCodeTruncate();

    String courierCode(String companyName);
}
