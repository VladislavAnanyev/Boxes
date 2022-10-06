package com.company.work;

public class Products {
    private int[] productsQuantityInEachBox;
    private Integer totalProducts;

    public Integer getTotalProducts() {
        return totalProducts;
    }

    public int[] getProductsQuantityInEachBox() {
        return productsQuantityInEachBox;
    }

    public void setProductsQuantityInEachBox(int[] productsQuantityInEachBox) {
        this.productsQuantityInEachBox = productsQuantityInEachBox;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }
}
