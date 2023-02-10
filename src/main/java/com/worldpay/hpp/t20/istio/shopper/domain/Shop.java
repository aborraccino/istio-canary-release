package com.worldpay.hpp.t20.istio.shopper.domain;

public class Shop {

    private String id;
    private String email;
    private String tel;
    private String address;
    private String category;

    public Shop(String id, String email, String telephone, String address) {

        this.id = id;
        this.email = email;
        this.tel = telephone;
        this.address = address;
    }

    public Shop(String id, String email, String telephone, String address, String category) {

        this.id = id;
        this.email = email;
        this.tel = telephone;
        this.address = address;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
