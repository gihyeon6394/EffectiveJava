package com.effectiveJava.chapter2.item2;

public class Idol {

    private final String memberName;
    private final int age;
    private final int isMarried;
    private final String home;
    private final int isLeader;
    private final int isDebut;
    private final int height;

    public static class Builder {
        private final String memberName;
        private final int age;

        private String home = "-";
        private int isMarried = 0;
        private int isLeader = 0;
        private int isDebut = 1;
        private int height = -1;

        // 필수값을 초기화하는 Builder 생성자
        public Builder(String memberName, int age) {
            this.memberName = memberName;
            this.age = age;
        }

        public Builder home(String home) {
            this.home = home;
            return this;
        }

        public Builder isMarried(int isMarried) {
            this.isMarried = isMarried;
            return this;
        }

        public Builder isLeader(int isLeader) {
            this.isLeader = isLeader;
            return this;
        }

        public Builder isDebut(int isDebut) {
            this.isMarried = isDebut;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Idol build(){
            return new Idol(this);
        }
    }


    public Idol(Builder builder) {
        this.memberName = builder.memberName;
        this.age = builder.age;
        this.home = builder.home;
        this.isMarried = builder.isMarried;
        this.isLeader = builder.isLeader;
        this.isDebut = builder.isDebut;
        this.height = builder.height;
    }

    @Override
    public String toString() {
        return "GirlMember{" +
                "memberName='" + memberName + '\'' +
                ", age=" + age +
                ", home='" + home + '\'' +
                ", isMarried=" + isMarried +
                ", isLeader=" + isLeader +
                ", isDebut=" + isDebut +
                ", height=" + height +
                '}';
    }
}
