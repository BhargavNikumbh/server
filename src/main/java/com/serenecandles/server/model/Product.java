package com.serenecandles.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.serenecandles.server.util.StringPrefixedSequenceGenerator;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="demo_sql")
//    @GenericGenerator(name="demo_sql", strategy = "com.serenecandles.server.util.StringPrefixedSequenceGenerator", parameters = {
//            @Parameter(name= StringPrefixedSequenceGenerator.INCREMENT_PARAM, value = "1"),
//            @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "Product_"),
//            @Parameter(name= StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
//    })
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productname;
    private String productDescription;
    private int productQuantity;

    private Double productPrice;
    private String productCreatedOn;

//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="product_images",
            joinColumns = {
                @JoinColumn(name="product_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name="image_id")
            }
    )
    private Set<ProductImage> productImages;

    public Set<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ProductImage> productImages) {
        this.productImages = productImages;
    }


    @ManyToOne
    private Category category;

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
