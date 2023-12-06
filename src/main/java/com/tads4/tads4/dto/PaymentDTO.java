package com.tads4.tads4.dto;

import com.tads4.tads4.entities.Order;
import com.tads4.tads4.entities.Payment;

import java.time.Instant;

public class PaymentDTO {
    private Long id;
    private Order order;
    private Instant moment;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Order order, Instant moment) {
        this.id = id;
        this.order = order;
        this.moment = moment;
    }
    public PaymentDTO(Payment entity) {
        id = entity.getId();
        order = entity.getOrder();
        moment = entity.getMoment();
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

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }
}
