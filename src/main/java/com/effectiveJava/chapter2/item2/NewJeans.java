package com.effectiveJava.chapter2.item2;


import java.util.Objects;

public class NewJeans extends GirlGroup {

    public enum NowStatus {ING, FINISHED, READY}

    private final NowStatus nowStatus;

    public static class Builder extends GirlGroup.Builder<Builder> {

        private final NowStatus nowStatus;

        public Builder(NowStatus nowStatus) {
            this.nowStatus = Objects.requireNonNull(nowStatus);
        }

        @Override
        public NewJeans build() {
            return new NewJeans(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }


    private NewJeans(Builder builder) {
        super(builder);
        this.nowStatus = builder.nowStatus;
    }

    @Override
    public String toString() {
        return "NewJeans{" +
                "nowStatus=" + nowStatus +
                ", memberFavoriteSet=" + memberFavoriteSet +
                '}';
    }
}
