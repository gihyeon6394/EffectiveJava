package com.effectiveJava.chapter4.item20.idol;

public class Ive implements Idol{
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
        System.out.println("play music AFTER LIKE");
    }

    @Override
    public void viewMyCompany() {
        System.out.println("my company is STARSHIP");
    }
}
