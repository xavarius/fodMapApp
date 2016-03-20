package com.maciejmalak.whatcanieat.foodList;

public class FoodPojo {
    private String mName;
    private String mCategory;
    private Short mIsAllowed;

    private FoodPojo() {}

    public FoodPojo(final String name, final String category,
                    final String isAllowed, final String desc) {
        mName = name;
        mCategory = category;
        if (isAllowed.equals("Forbidden")) {
            mIsAllowed = -1;
        } else if (isAllowed.equals("Restricted")) {
            mIsAllowed = 0;
        } else {
            mIsAllowed = 1;
        }
    }

    public String getName() {
        return mName;
    }

    public String getCategory() {
        return mCategory;
    }

    public Short isAllowed() {
        return mIsAllowed;
    }
}
