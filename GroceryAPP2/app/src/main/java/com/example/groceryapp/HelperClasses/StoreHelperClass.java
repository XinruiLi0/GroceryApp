package com.example.groceryapp.HelperClasses;

public class StoreHelperClass {

    private String storeName;
    private String storeId;

    public StoreHelperClass(String storeName, String storeId) {
        this.storeName = storeName;
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreId() {
        return storeId;
    }
}
