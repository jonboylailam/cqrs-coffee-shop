package com.sprinthive.coffeshop.orders.dto;

public class OrderInfo {

    private String type;
    private String beanOrigin;

    public OrderInfo() {
        this.type = null;
        this.beanOrigin = null;
    }

    public OrderInfo(String type, String beanOrigin) {
        this.type = type;
        this.beanOrigin = beanOrigin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBeanOrigin() {
        return beanOrigin;
    }

    public void setBeanOrigin(String beanOrigin) {
        this.beanOrigin = beanOrigin;
    }
}
