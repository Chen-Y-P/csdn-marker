package com.chen;


import cn.hutool.http.HttpRequest;

public class TTT {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String rr = HttpRequest.get("https://blog.csdn.net/chen18677338530/article/details/93739247").execute().body();
            System.out.println(rr);
        }

    }
}
