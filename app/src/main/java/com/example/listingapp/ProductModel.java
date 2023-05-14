package com.example.listingapp;

public class ProductModel {

    private String productImage;
    private int productPrice;
    private String productName;
    private String productType;
    private int productTax;

    public ProductModel(String productImage, int productPrice, String productName, String productType, int productTax) {
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productType = productType;
        this.productTax = productTax;
    }

    public String getProductImage() {
        return productImage;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public int getProductTax() {
        return productTax;
    }
}
