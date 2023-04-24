package com.effectiveJava.chapter3.item13;

import java.util.Objects;

public class PhoneNumber implements Cloneable{

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

}
