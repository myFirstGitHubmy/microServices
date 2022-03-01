package com.shared.dto;

import com.domain.Role;

import java.util.Date;

public class UserDto {
    public Long id;
    public String name;
    public String login;
    public String password;
    public String email;
    public Date createDate;
    public String uuid;
    public Role roles;

    public UserDto(Long id, String name, String login, String password, String email, Date createDate,String uuid, Role roles){
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.uuid = uuid;
        this.roles = roles;
    }
}
