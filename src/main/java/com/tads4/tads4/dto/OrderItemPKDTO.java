package com.tads4.tads4.dto;

import com.tads4.tads4.entities.Order;
import com.tads4.tads4.entities.OrderItemPK;
import com.tads4.tads4.entities.Product;

public class OrderItemPKDTO {
    private  Long id;
    private Order order;
    private Product product;

    public OrderItemPKDTO() {
    }

    public OrderItemPKDTO(Long id, Order order, Product product) {
        this.id = id;
        this.order = order;
        this.product = product;
    }
    public OrderItemPKDTO(OrderItemPK entity) {
        this.id = id;
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

