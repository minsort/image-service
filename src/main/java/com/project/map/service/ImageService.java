package com.project.map.service;

import com.project.map.dto.ProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class ImageService {

    private final CreateProfile createProfile;

    public ImageService(CreateProfile createProfile) {
        this.createProfile = createProfile;
    }

    public String createFinalImage(ProfileDto profileDto) throws Exception {
        BufferedImage mapSnapshotImage = CreateSnapshot.decodeBase64ToImage(profileDto.getSnapshot());
        BufferedImage profileImage = createProfile.createProfile(profileDto);
        BufferedImage logoImage = CreateImage.createImage("/static/logo.png");
        BufferedImage emojiImage = CreateImage.createImage("/static/emoji.png");
        BufferedImage footerImage = CreateImage.createImage("/static/footer.png");
        BufferedImage bgImage = CreateImage.createImage("/static/bg.png");

        BufferedImage bgFinalImage = CreateImage.addTextToImage(35, 12, "#"+String.valueOf(profileDto.getId()), 11);

        assert mapSnapshotImage != null;
        BufferedImage finalImage = overlayImages(mapSnapshotImage, profileImage, -30, -25);
        BufferedImage finalImageWithLogo = overlayImages(finalImage, logoImage, 6, 10);
        BufferedImage finalImageWithEmoji = overlayImages(finalImageWithLogo, emojiImage, 26, 410);
        BufferedImage finalImageWithFooter = overlayImages(finalImageWithEmoji, footerImage, 26, 980);
        BufferedImage finalImageWithBg = overlayImages(finalImageWithFooter, bgImage, 142, 980);
        BufferedImage finalAll = overlayImages(finalImageWithBg, bgFinalImage, 142, 980);

        return CreateDirectoryAtRuntime.createDirectoryForResult(
                ConvertImageToByte.bufferedImageToByteArray(finalAll));
    }

    private static BufferedImage overlayImages(BufferedImage background, BufferedImage overlay, int x, int y) {
        int width = Math.max(background.getWidth(), overlay.getWidth());
        int height = Math.max(background.getHeight(), overlay.getHeight());

        BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = combinedImage.createGraphics();

        // Наложение снепшота карты
        g2d.drawImage(background, 0, 0, null);

        // Наложение изображения профайла
        g2d.drawImage(overlay, x, y, null);

        g2d.dispose();

        return combinedImage;
    }

}
