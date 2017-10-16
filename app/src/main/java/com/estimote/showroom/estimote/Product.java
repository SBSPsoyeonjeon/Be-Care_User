package com.estimote.showroom.estimote;

import java.util.Arrays;

public class Product {
    private String name;
    private String id;
    private int[] sum;
    private int rssi;
    private int counter;

    public Product(String name, String id) {
        this.name = name;
        this.id = id;
        this.sum = new int[5];
        this.counter=0;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getRssi() {
        return Integer.toString(this.rssi);
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
        if(this.counter<5){
            this.sum[this.counter++] = rssi;
        }
    }

    public String getSum(){
        int result=0;
        if(counter > 0){
            for(int i=0; i<counter; i++) {
                result += sum[i];
            }
            result /= counter;
        }
        return Integer.toString(result);
    }

    public void flush(){
        //Arrays.fill(sum, 0);
        rssi = 0;
        counter = 0;
    }
}