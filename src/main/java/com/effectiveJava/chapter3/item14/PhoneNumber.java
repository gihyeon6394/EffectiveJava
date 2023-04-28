package com.effectiveJava.chapter3.item14;

import java.util.Objects;

public class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {

    private String telecom;
    private String phoneNumber;

    public PhoneNumber() {
    }

    public PhoneNumber(String telecom, String phoneNumber) {
        this.telecom = telecom;
        this.phoneNumber = phoneNumber;
    }

    public void setTelecom(String telecom) {
        this.telecom = telecom;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTelecom() {
        return telecom;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        if (!Objects.equals(telecom, that.telecom)) return false;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {

        int result = telecom != null ? telecom.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "telecom='" + telecom + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public PhoneNumber clone() {
        try {
            return (PhoneNumber) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    /**
     * 핵심필드를 비교하는 compareTo() 메서드
     * 핵심필드에서 순서가 결정나면, 나머지 필드는 비교하지 않는다. (성능)
     */

    @Override
    public int compareTo(PhoneNumber o) {
        int result = this.telecom.compareTo(o.telecom);
        if (result == 0){
            result = this.phoneNumber.compareTo(o.phoneNumber);
        }
        return result;
    }
}
