package model;

public class FoodPojo {
    private String mName;
    private String mCategory;
    private boolean mIsAllowed;

    private FoodPojo() {}

    private FoodPojo(final String name, final String category, final boolean isAllowed) {
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
