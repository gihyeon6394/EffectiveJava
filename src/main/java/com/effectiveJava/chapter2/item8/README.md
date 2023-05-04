<h1>item 8. finalizer와 cleaner 사용을 피하라</h1>

> cleaner는 중요치 않은 native 자원에 사용하자, 그러나 실행여부와 성능 보장은 안된다

자바의 객체 소멸자
finalizer (~ java 8)
cleaner (java 8~)

finalizer
예측이 힘들고 위험함
작업 중 발생한 예외는 무시되고, 작업이 남아도 그 즉시 종료됨

cleaner
finalizer deprecated 에 따라 등장
예측불가, 느림, 불필요


구린 이유

즉시 수행된다는 보장 없다
실행 시점을 예측할 수 없다
수행 여부 보장 안됨

따라서 상태를 영구적으로 저장하는 작업에는 절대 사용하면 안됨
성능 문제 동반
AutoCloseable 구현 대비 약 50배 느림
보안 문제





