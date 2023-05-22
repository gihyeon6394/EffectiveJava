package com.effectiveJava.chapter4.item20.idolSk;

import com.effectiveJava.chapter4.item20.idol.AbstractIdol;
import com.effectiveJava.chapter4.item20.idol.Idol;

public class NewJeans extends AbstractIdol implements Idol {
    @Override
    public void sing() {
        System.out.println("play music ATTENTION");
    }

    @Override
    public void viewMyCompany() {
        System.out.println("my company is HYBE");
    }
}
