package com.example.chetnatanmay;

public class Users {
    String name,email,password,phone,reg_on;

    public Users(){

    }

    public Users(String name, String email, String password, String phone, String reg_on) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.reg_on = reg_on;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReg_on() {
        return reg_on;
    }

    public void setReg_on(String reg_on) {
        this.reg_on = reg_on;
    }
}
