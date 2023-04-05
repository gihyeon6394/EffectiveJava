package com.effectiveJava.chapter2.item2;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class GirlGroup {

    public enum MemberFavorite {MINZI, HANI, HAERIN, SAKURA, KARINA}

    ;
    final Set<MemberFavorite> memberFavoriteSet;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<MemberFavorite> members = EnumSet.noneOf(MemberFavorite.class);

        public T addMembers(MemberFavorite memberFavorite) {
            members.add(Objects.requireNonNull(memberFavorite));
            return self();
        }


        abstract GirlGroup build();

        protected abstract T self();
    }

    GirlGroup(Builder<?> builder) {
        memberFavoriteSet = builder.members.clone();
    }

}
