package com.example.groceryapp.StoreProductHelper;

public class StoreProductHelperClass {

    private String itemName;
    private String itemId;
    private String itemStock;
    private String restockTime;
    private String itemPrice;
    private String itemCategory;
    private String itemImage;

    public StoreProductHelperClass(String itemName, String itemId, String itemStock, String restockTime, String itemPrice, String itemCategory, String itemImage) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.itemStock = itemStock;
        this.restockTime = restockTime;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemStock() {
        return itemStock;
    }

    public String getRestockTime() {
        return restockTime;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getItemImage() {
        return itemImage;
    }
}
