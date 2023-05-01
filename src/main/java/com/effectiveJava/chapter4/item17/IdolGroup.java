package com.effectiveJava.chapter4.item17;

import java.util.Objects;

/**
 * 불변클래스 만들어보기 2
 * 정적 팩터리 메서드 + 함수형 프로그래밍
 * <p>
 * 빌더패턴 미적용 이유 : 필드가 4개 미만임
 * 함수형 프로그래밍 : 자료처리 (메서드)를 수학적 함수로 취급하고 상태와 가변 데이터를 멀리하는 프로그래밍 패러다임
 */
public class IdolGroup {

    public static final IdolGroup NEWJEANS = IdolGroup.of("NEWJEANS", "HYBE");
    public static final IdolGroup AESPA = IdolGroup.of("AESPA", "SM");

    private final String name;
    private final String agency;


    private IdolGroup(String name, String agency) {
        this.name = name;
        this.agency = agency;
    }

    public static IdolGroup of(String name, String agency) {
        return new IdolGroup(name, agency);
    }

    public IdolGroup changeAgency(String agency) {
        return new IdolGroup(this.name, agency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdolGroup idolGroup = (IdolGroup) o;

        if (!Objects.equals(name, idolGroup.name)) return false;
        return Objects.equals(agency, idolGroup.agency);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (agency != null ? agency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IdolGroup{" +
                "name='" + name + '\'' +
                ", agency='" + agency + '\'' +
                '}';
    }
}
