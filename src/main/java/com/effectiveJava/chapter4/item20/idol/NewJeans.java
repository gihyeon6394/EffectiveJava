package com.effectiveJava.chapter4.item20.idol;

public class NewJeans implements Idol{
    @Override
    public void practice() {
        System.out.println("강남 연습실로 갑니다.");
    }

    @Override
    public void goAirport() {
        System.out.println("인천공항으로 갑니다.");
    }

    @Override
    public void sing() {
        System.out.println("play music ATTENTION");
    }

    @Override
    public void viewMyCompany() {
        System.out.println("my company is HYBE");
    }
}
