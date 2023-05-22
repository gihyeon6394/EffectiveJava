package com.effectiveJava.chapter4.item20.idolSk;

import com.effectiveJava.chapter4.item20.idol.AbstractIdol;
import com.effectiveJava.chapter4.item20.idol.Idol;

public class Aespa extends SmEnt implements Idol {

    private AbstractIdolInner abstractIdolInner = new AbstractIdolInner();

    @Override
    public void practice() {abstractIdolInner.practice();}

    @Override
    public void goAirport() {abstractIdolInner.goAirport();}

    @Override
    public void sing() {abstractIdolInner.sing();}

    @Override
    public void viewMyCompany() {abstractIdolInner.viewMyCompany();}

    private class AbstractIdolInner extends AbstractIdol {
        @Override
        public void sing() {System.out.println("play music BLACK MAMBA");}

        @Override
        public void viewMyCompany() {System.out.println("my company is SM");}
    }
}
