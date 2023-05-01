package com.effectiveJava.chapter4.item15;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * item 15. 클래스와 멤버의 접근 권한을 최소화하라
 */
public class Item15 {

    public static void main(String[] args) {


        System.out.println(Arrays.toString(foo.USERS1));
        ArrayUtils.remove(foo.USERS1, 0);
        foo.USERS1[0] = null;
        System.out.println(Arrays.toString(foo.USERS1));

        System.out.println(foo.USER2_LIST.remove(0)); //UnsupportedOperationException
    }

    public static class foo {
        public static final User[] USERS1 = {new User("카리나", 19), new User("강해린", 21)};
        private static final User[] USERS2 = {new User("카리나", 19), new User("강해린", 21)};

        // unmodifiableList 이용
        public static final List<User> USER2_LIST = Collections.unmodifiableList(Arrays.asList(USERS2));

        // clone 이용
        public static final User[] values() {
            return USERS2.clone();
        }
    }


}
