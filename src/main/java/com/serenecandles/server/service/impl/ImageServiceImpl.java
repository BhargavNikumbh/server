package com.serenecandles.server.service.impl;

import com.serenecandles.server.model.Image;
import com.serenecandles.server.repo.ImageRepository;
import com.serenecandles.server.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl {
    @Autowired
    private ImageRepository imageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        Image imageData = imageRepository.save(Image.builder()
                .filename(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtils.compressImage(file.getBytes())).build());

        if(imageData !=null){
            return "File Uploaded Successfully "+file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<Image> dbImageData = imageRepository.findByFilename(fileName);
        return  ImageUtils.decompressImage(dbImageData.get().getImage());
    }
}
