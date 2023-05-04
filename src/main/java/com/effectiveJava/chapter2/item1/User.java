package com.effectiveJava.chapter2.item1;

public class User {


    // 싱글턴 객체
    private static User userSingleTone = User.valueOf("팜하니 (싱글턴)", "부산광역시 싱글턴동");

    protected String userName;
    protected String home;


    /**
     * static factory method
     */
    public static User valueOf(String userName, String home) {
        User user = new User();
        user.userName = userName;
        user.home = home;
        return user;
    }



    // 시그니처가 같은 여러 생성자가 필요할경우 유용
    public static User valueOfNotAge(String userName) {
        User user = new User();
        user.userName = userName;
        return user;
    }

    public static User getInstance() {
        return userSingleTone;
    }

    // 하위 타입의 객체 반환
    public static User valueOfSub(String userName) {
        User userSub = new UserSub();
        userSub.userName = userName;
        return userSub;
    }

    // 매개변수에 따라 다른 클래스 반환하기
    public static User valueOfByName(String userName) {
        User user = new User();
        if ("비속어가 있는 이름".equals(userName)) {
            user.userName = "이런 짓 하지 마세요";
        } else {
            user.userName = userName;
        }
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}
