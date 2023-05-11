package com.serenecandles.server.repo;

import com.serenecandles.server.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByFilename(String fileName);
    @Modifying
    @Query(value = "DELETE FROM image_data where image=null", nativeQuery = true)
    void deleteNullImages();
}
