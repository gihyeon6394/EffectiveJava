package com.effectiveJava.chapter3.item12;

import java.util.Objects;

public class Item12 {

    public static void main(String[] args) {
        PhoneNumber1 phoneNumber1 = new PhoneNumber1("SKT", "010-5678-1234");
        System.out.println(phoneNumber1.toString());
        System.out.println(phoneNumber1);
        System.out.println(phoneNumber1 + "에 연결할 수 없습니다.");

        PhoneNumber2 phoneNumber2 = new PhoneNumber2("SKT", "010-5678-1234");
        System.out.println(phoneNumber2.toString());
        System.out.println(phoneNumber2);
        System.out.println(phoneNumber2 + "에 연결할 수 없습니다.");


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

        public String getTelecom() {
            return telecom;
        }

        public void setTelecom(String telecom) {
            this.telecom = telecom;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PhoneNumber1 that = (PhoneNumber1) o;

            if (!Objects.equals(telecom, that.telecom)) return false;
            return Objects.equals(phoneNumber, that.phoneNumber);
        }

        @Override
        public int hashCode() {
            int result = telecom != null ? telecom.hashCode() : 0;
            result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
            return result;
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

        public String getTelecom() {
            return telecom;
        }

        public void setTelecom(String telecom) {
            this.telecom = telecom;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PhoneNumber1 that = (PhoneNumber1) o;

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
         * 객체의 통신사 (telecom)와 전화번호 (phoneNumber)를 문자열로 제공한다
         */
        @Override
        public String toString() {
            return "PhoneNumber2{" +
                    "telecom='" + telecom + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }
    }

}
