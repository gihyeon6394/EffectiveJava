package com.effectiveJava.chapter3.item11;

import java.util.Map;
import java.util.Objects;

public class Item11 {

    public static void main(String[] args) {

        Map<PhoneNumber1, String> map = new java.util.HashMap<>();
        map.put(new PhoneNumber1("SK", "01012345678"), "Kim");
        System.out.println(map.get(new PhoneNumber1("SK", "01012345678"))); //null


        Map<PhoneNumber2, String> map2 = new java.util.HashMap<>();
        map2.put(new PhoneNumber2("SK", "01012345678"), "Kim");
        System.out.println(map2.get(new PhoneNumber2("SK", "01012345678"))); //kim
    }

    public static class PhoneNumber1 {

        private String telecom;
        private String phoneNumber;

        public PhoneNumber1() {
        }

        public PhoneNumber1(String telecom, String phoneNumber) {
            this.telecom = telecom;
            this.phoneNumber = phoneNumber;
        }
    }

    public static class PhoneNumber2 {

        private String telecom;
        private String phoneNumber;

        public PhoneNumber2() {
        }

        public PhoneNumber2(String telecom, String phoneNumber) {
            this.telecom = telecom;
            this.phoneNumber = phoneNumber;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PhoneNumber2 that = (PhoneNumber2) o;

            if (!Objects.equals(telecom, that.telecom)) return false;
            return Objects.equals(phoneNumber, that.phoneNumber);
        }

        @Override
        public int hashCode() {

            int result = telecom != null ? telecom.hashCode() : 0;
            result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
            return result;
        }


        /**
         * hashCode 2 : Object 클래스의 hash
         * 장점 : 소스가 간단함
         * 단점 : 속도가 느림
         * => hash 성능이 민감하지 않은 경우에만 사용한다.
         */

/*        @Override
        public int hashCode() {
            return Objects.hash(telecom, phoneNumber);
        }*/
    }

    public static class PhoneNumber3 {

        private String telecom;
        private String phoneNumber;

        private int hashCode;

        public PhoneNumber3() {
        }

        public PhoneNumber3(String telecom, String phoneNumber) {
            this.telecom = telecom;
            this.phoneNumber = phoneNumber;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PhoneNumber2 that = (PhoneNumber2) o;

            if (!Objects.equals(telecom, that.telecom)) return false;
            return Objects.equals(phoneNumber, that.phoneNumber);
        }

        /**
         * 불변 클래스의 지연초기화 방식
         */

        @Override
        public int hashCode() {
            int result = hashCode;
            // hashCode가 정의된적이 없을 때만 초기화
            if (result == 0) {
                result = telecom != null ? telecom.hashCode() : 0;
                result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
            }
            return result;
        }


    }
}
