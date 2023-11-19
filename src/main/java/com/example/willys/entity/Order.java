package com.example.willys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "orders", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"customer_id", "product_id"})
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    private LocalDate date;

    private int quantity;

    private double totalAmount;

    //Columns
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


    public Order() {
        this.date = LocalDate.now();
    }

    @PrePersist
    public void calculateAmount(){
        this.totalAmount = quantity * product.getPrice();
    }
}
