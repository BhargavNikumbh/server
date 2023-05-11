//package com.serenecandles.server.model;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class ProductCategory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long productCategoryId;
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Product product;
//
//    @ManyToOne
//    private Category category;
//}
