package com.serenecandles.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String categoryname;
    private String categorydescription;
    private String categorycreatedOn;

    public Category(){}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @JsonIgnore
    private List<Product> product1;

    public List<Product> getProduct1() {
        return product1;
    }

    public void setProduct1(List<Product> product1) {
        this.product1 = product1;
    }

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="category_images",
            joinColumns = {
                @JoinColumn(name="category_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name="image_id")
            }
    )
    private Set<Image> categoryImages;


    public Set<Image> getCategoryImages() {
        return categoryImages;
    }

    public void setCategoryImages(Set<Image> categoryImages) {
        this.categoryImages = categoryImages;
    }

//    public void addCategoryImage(Image img){
//        categoryImages.add(img);
//        img.setCategory(this);
//    }
//
//    public void removeCategoryImage(Image img){
//        categoryImages.remove(img);
//        img.setCategory(null);
//    }


}
