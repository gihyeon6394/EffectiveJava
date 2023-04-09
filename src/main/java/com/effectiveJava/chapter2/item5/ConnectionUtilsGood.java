package com.effectiveJava.chapter2.item5;

import com.effectiveJava.chapter2.item2.Idol;

import java.rmi.ConnectIOException;

/**
 * 유틸리티 클래스
 * 
 * */
public class ConnectionUtilsGood {

    private Connector connector ;

    public ConnectionUtilsGood() {
    }

    //1. 생성자 주입
    public ConnectionUtilsGood(Connector connector) {
        this.connector = connector;
    }

    public ConnectionUtilsGood(Builder builder) {
        this.connector = builder.connector;
    }


    public static ConnectionUtilsGood valueOf(Connector connector){
        ConnectionUtilsGood connectionUtilsGood = new ConnectionUtilsGood();
        connectionUtilsGood.connector = connector;
        return connectionUtilsGood;
    }

    public void runConnector(){
        connector.runIt();
    }

    public static class Builder {
        private final Connector connector;


        // 필수값을 초기화하는 Builder 생성자
        public Builder(Connector connector) {
           this.connector = connector;
        }


        public ConnectionUtilsGood build(){
            return new ConnectionUtilsGood(this);
        }
    }

}
