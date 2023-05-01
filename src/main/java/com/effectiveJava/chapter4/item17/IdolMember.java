package com.effectiveJava.chapter4.item17;


/**
 * 불변클래스 만들어보기 1
 * 빌더 패턴 적용
 */
public class IdolMember {

    // 자주 사용하는 객체들은 상수 필드로 제공
    public static final IdolMember MINZI = new Builder("민지").age(2004).isLeader(1).isDebut(1).build();
    public static final IdolMember KARINA = new Builder("카리나").age(2000).isLeader(1).isDebut(1).build();

    private final String name;
    private final int birthYear;
    private final int isLeader;
    private final int isDebut;

    public static class Builder {

        /**
         * required value
         */
        private final String name;

        /**
         * default value
         */

        private int age;

        private int isLeader = 0;
        private int isDebut = 1;

        public Builder(String name) {
            this.name = name;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder isLeader(int isLeader) {
            this.isLeader = isLeader;
            return this;
        }

        public Builder isDebut(int isDebut) {
            this.isDebut = isDebut;
            return this;
        }

        public IdolMember build() {
            return new IdolMember(this);
        }
    }


    /**
     * private 생성자
     */
    private IdolMember(String name, int birthYear, int isLeader, int isDebut) {
        this.name = name;
        this.birthYear = birthYear;
        this.isLeader = isLeader;
        this.isDebut = isDebut;
    }

    public IdolMember(Builder builder) {
        this.name = builder.name;
        this.birthYear = builder.age;
        this.isLeader = builder.isLeader;
        this.isDebut = builder.isDebut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdolMember idolMember = (IdolMember) o;

        if (birthYear != idolMember.birthYear) return false;
        if (isLeader != idolMember.isLeader) return false;
        if (isDebut != idolMember.isDebut) return false;
        return name.equals(idolMember.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + birthYear;
        result = 31 * result + isLeader;
        result = 31 * result + isDebut;
        return result;
    }

    @Override
    public String toString() {
        return "Idol{" +
                "name='" + name + '\'' +
                ", age=" + birthYear +
                ", isLeader=" + isLeader +
                ", isDebut=" + isDebut +
                '}';
    }
}
