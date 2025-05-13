package com.example.shopping.user;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String userId;
    private String password;
    private String phonNumber;
    private String gender;
    private String yyyymmdd;
    private String email;
    private String postcode;
    private String address;
    private String detailAddress;
    private String extraAddress;
    private String deleteYn;
    private String partnerUserId;
}
