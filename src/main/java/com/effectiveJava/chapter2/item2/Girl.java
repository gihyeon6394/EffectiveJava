package com.effectiveJava.chapter2.item2;

public class Girl {
    private String memberName;
    private int age;
    private String home;
    private int isMarried;
    private int isLeader;
    private int isDebut;
    private int height;


    /**
     * telescoping constructor pattern (점층적 생성자 패턴) (begin)
     */

    public Girl() {
    }

    public Girl(String memberName, int age) {
        this.memberName = memberName;
        this.age = age;
    }

    public Girl(String memberName, int age, String home, int isMarried, int isLeader) {
        this.memberName = memberName;
        this.age = age;
        this.home = home;
        this.isMarried = isMarried;
        this.isLeader = isLeader;
    }

    public Girl(String memberName, int age, String home, int isMarried, int isLeader, int isDebut, int height) {
        this.memberName = memberName;
        this.age = age;
        this.home = home;
        this.isMarried = isMarried;
        this.isLeader = isLeader;
        this.isDebut = isDebut;
        this.height = height;
    }

    /**
     * telescoping constructor pattern (점층적 생성자 패턴) (end)
     * */

    /**
     * JavaBeans pattern (자바 빈즈 패턴) (begin)
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setIsMarried(int isMarried) {
        this.isMarried = isMarried;
    }

    public void setIsLeader(int isLeader) {
        this.isLeader = isLeader;
    }

    public void setIsDebut(int isDebut) {
        this.isDebut = isDebut;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * JavaBeans pattern (자바 빈즈 패턴) (end)
     * */
}
