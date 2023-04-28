package com.effectiveJava.chapter3.item14;

import java.util.Comparator;
import java.util.Objects;

public class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {

    /**
     * 비교사 생성 메서드 방식
     */
    public static final Comparator<PhoneNumber> COMPARATOR = Comparator.comparing(PhoneNumber::getTelecom)
            .thenComparing(PhoneNumber::getPhoneNumber);


    /**
     * 값의 차를 이용하는 방식
     * 1. 무식하게 -연산을 사용하여 비교 (비추)
     * 2. 정적 compare 메서드를 활용 (권장)
     * 3. 비교자 생성 메서드를 활용 (권장)
     */
    static Comparator<Object> hashCodeOrder = new Comparator<Object>() {

        // 1.
        // @Override
        // public int compare(Object o1, Object o2) {
        //     return o1.hashCode() - o2.hashCode();
        // }

        // 2.
        @Override
        public int compare(Object o1, Object o2) {
            return Integer.compare(o1.hashCode(), o2.hashCode());
        }

    };

    // 3.
    static Comparator<Object> hashCodeOrder2 = Comparator.comparingInt(o -> o.hashCode());
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
        if (result == 0) {
            result = this.phoneNumber.compareTo(o.phoneNumber);
        }
        return result;
    }

    public int compareTo2(PhoneNumber o) {
        return COMPARATOR.compare(this, o);
    }
}
