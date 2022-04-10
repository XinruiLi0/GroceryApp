package com.example.groceryapp.StoreHelper;

public class StoreHelperClass {

    private String storeName;
    private String storeId;
    private String userName;
    private String userId;

    public StoreHelperClass(String storeName, String storeId, String userName, String userId) {
        this.storeName = storeName;
        this.storeId = storeId;
        this.userName = userName;
        this.userId = userId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

}
