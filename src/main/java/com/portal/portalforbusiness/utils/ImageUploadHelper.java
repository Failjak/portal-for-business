package com.portal.portalforbusiness.utils;

import jakarta.servlet.http.Part;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.UUID;

@UtilityClass
public class ImageUploadHelper {
    private static final String IMAGE_UPLOAD_PATH = "/static/files/%s.%s";

    public static String getPath(String fileName, String type) {
        return String.format(IMAGE_UPLOAD_PATH, fileName, type);
    }

    public static String generateImageName() {
        return UUID.randomUUID().toString();
    }

    public static String generateImageType(Part image) {
        return image.getContentType().split("/")[1];
    }

    public static String uploadImage(Part image, String absolutePath) {
        try {
            String imagePath = getPath(generateImageName(), generateImageType(image));
            image.write(absolutePath + imagePath);
            return imagePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}