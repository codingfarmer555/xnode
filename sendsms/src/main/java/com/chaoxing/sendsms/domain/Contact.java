package com.chaoxing.sendsms.domain;

public class Contact {

    public String name;
    public String phone;

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
