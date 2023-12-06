package com.tads4.tads4.dto;

import com.tads4.tads4.entities.Order;
import com.tads4.tads4.entities.OrderStatus;
import com.tads4.tads4.entities.Payment;
import com.tads4.tads4.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.Instant;

public class OrderDTO {

    //@NotNull
    //@NotEmpty
    private Long id;
    private Instant moment;
    private OrderStatus status;
    private Payment payment;
    private User client;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Instant moment, OrderStatus status, Payment payment, User client) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.payment = payment;
        this.client = client;
    }

    public OrderDTO(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getSatus();
        payment = entity.getPayment();
        client = entity.getClient();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
