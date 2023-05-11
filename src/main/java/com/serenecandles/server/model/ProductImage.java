package com.serenecandles.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productImageId;
    private String filename;
    private String type;
    @Lob
    @Column(name="image", length = 1000)
    private byte[] image;

//    @ManyToOne
//    private Product product;

    public ProductImage(MultipartFile img) throws IOException {
        this.image = img.getBytes();
    }

    public ProductImage(String filename, String type, byte[] image) {
        this.filename = filename;
        this.type = type;
        this.image = image;
    }

}
