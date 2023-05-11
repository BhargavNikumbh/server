package com.serenecandles.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="image_data")
@Getter
@Setter
@Builder
@AllArgsConstructor

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;
    private String filename;
    private String type;
    @Lob
    @Column(name="image", length = 1000)
    private byte[] image;

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "image")
//    @JsonIgnore
//    private Set<ProductImage> productImages = new HashSet<>();
    public Image(){}

    public Image(MultipartFile img) throws IOException {
        this.image = img.getBytes();
    }

    public Image(String filename, String type, byte[] image) {
        this.filename = filename;
        this.type = type;
        this.image = image;
    }
}
