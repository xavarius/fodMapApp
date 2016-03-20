package com.maciejmalak.whatcanieat.foodList;

public class FoodPojo {
    private String mName;
    private String mCategory;
    private boolean mIsAllowed;

    private FoodPojo() {}

    public FoodPojo(final String name, final String category, final boolean isAllowed) {
        mName = name;
        mCategory = category;
        mIsAllowed = isAllowed;
    }

    public String getName() {
        return mName;
    }

    public String getCategory() {
        return mCategory;
    }

    public boolean isAllowed() {
        return mIsAllowed;
    }
}
