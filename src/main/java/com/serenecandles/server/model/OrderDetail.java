package com.serenecandles.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;
import com.serenecandles.server.model.Product;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private String orderFullName;
    private String orderFullAddress;
    private String orderEmailAddress;

    public OrderDetail(String orderFullName, String orderFullAddress, String orderEmailAddress, Product product, User user, String orderContactNumber, String orderAlternateContactNumber, String orderStatus, Double orderAmount, Date date) {
        this.orderFullName = orderFullName;
        this.orderFullAddress = orderFullAddress;
        this.orderEmailAddress = orderEmailAddress;
        this.product = product;
        this.user = user;
        this.orderContactNumber = orderContactNumber;
        this.orderAlternateContactNumber = orderAlternateContactNumber;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.orderDate = date;
    }

    @OneToOne
    @JoinColumn(name = "product_product_id")
    private Product product;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String orderContactNumber;
    private String orderAlternateContactNumber;
    private String orderStatus;
    private Double orderAmount;
    private java.sql.Date orderDate;

}
