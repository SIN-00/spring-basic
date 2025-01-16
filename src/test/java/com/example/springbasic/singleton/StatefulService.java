package com.example.springbasic.singleton;

public class StatefulService {

    //공유되는 지역변수 사용 -> 싱글톤 컨테이너 사용시 문제점
    //private int price;

    public int order(String name, int price) {

        System.out.println("name =" + name + ", price =" + price);
        //this.price = price;
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
