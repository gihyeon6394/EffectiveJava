package com.effectiveJava.chapter2.item7;

import java.lang.ref.WeakReference;

/**
 * 다 쓴 객체 참조를 해제하라
 * : memory leak, 다 썼으면 GC target으로 만들어라
 * <p>
 * memory leak 주범
 * - 클래스가 자기 메모리를 직접 관리
 * ex. 인스턴스 변수로 object type array, 즉 객체 참조 변수
 * null로 참조 지워서 GC 대상에 포함시키자. {@link Stack}
 * 주의점 : 다쓴 객체를 null 처리하는 건 예외적인 경우다. 변수의 범위 (scope)을 최소로 잡았다면 GC는 당연히 잘 이루어짐 (TODO.57)
 * - 캐시
 * {@link java.util.WeakHashMap} 사용 (TODO.WeakHashMap : https://www.baeldung.com/java-weakhashmap)
 * 단, WeakHashMap은 이런 목적에서만 유용함
 * <p>
 * - 리스너, 콜백 => weak reference (약한 참조) 사용해서 GC 대상자에 포함시키기
 * <p>
 * memory leak 위험한 이유 : {@link OutOfMemoryError}, disk paging을 일으킴 (TODO. what is disk paging?)
 */
public class Item7 {

    public static void main(String[] args) throws InterruptedException {

        // 객체가 GC 대상인지 확인하는 코드
        Object object = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(object);

        object = null; // object에 null 할당 (GC target)
        System.gc(); // 가비지 컬렉션 실행
        Thread.sleep(1000); // 잠시 대기

        if (weakReference.get() == null) {
            System.out.println("객체가 GC 대상이어서 지워졌습니다.");
        } else {
            System.out.println("객체가 GC 대상이 아니라 여전히 살아있습니다.");
        }

    }


}
