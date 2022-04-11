package com.example.groceryapp.CartHelper;

public class CartHelperClass {
    private String itemName;
    private String itemId;
    private String itemStock;
    private String restockTime;
    private String itemPrice;
    private String itemCategory;
    private String itemImage;
    private String itemAmount;

    public CartHelperClass(String itemName, String itemId, String itemStock, String restockTime, String itemPrice, String itemCategory, String itemImage, String itemAmount) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.itemStock = itemStock;
        this.restockTime = restockTime;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.itemImage = itemImage;
        this.itemAmount = itemAmount;
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

    public String getItemAmount() {
        return itemAmount;
    }
}
