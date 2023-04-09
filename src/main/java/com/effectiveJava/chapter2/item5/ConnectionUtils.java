package com.effectiveJava.chapter2.item5;

import com.effectiveJava.chapter2.item3.Minzi;

/**
 * 유틸리티 클래스
 * 
 * */
public class ConnectionUtils {

    private static  final ConnectionUtils INASTANCE = new ConnectionUtils();


    /**
     * question : 만일 Connector를 용도에 따라 바꿔 사용해야한다면?
     * -> changeConnector() 같은 메서드가 최선?
     * */
    private static Connector connector = new ConnectorKakao();

    // 객체 생성 방지
    private ConnectionUtils() {
    }

    public static ConnectionUtils getInstance(){
        return INASTANCE;
    }
    public static void changeConnector(Connector connector){
        ConnectionUtils.connector = connector;
    }

    public void runConnector(){
        connector.runIt();
    }

}
