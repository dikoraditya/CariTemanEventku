package com.example.utility;

import com.example.model.Event;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinnkurniawan on 10/14/17.
 */
public class ImageUploader {

    public static void uploadEventImage(MultipartFile backgroundImage, MultipartFile hostImage, Event event) throws Exception {
        if (backgroundImage != null && hostImage != null) {
            validateImage(backgroundImage.getBytes(), backgroundImage.getOriginalFilename());
            validateImage(hostImage.getBytes(), hostImage.getOriginalFilename());

            byte[] backgroundBytes = backgroundImage.getBytes();
            byte[] hostBytes = hostImage.getBytes();
            String backgroundImageName = event.getEventName().replace(" ", "-") + "." +
                    FilenameUtils.getExtension(backgroundImage.getOriginalFilename());
            String hostImageName = event.getHostedBy().replace(" ", "-") + "." +
                    FilenameUtils.getExtension(hostImage.getOriginalFilename());

            // Creating the directory to store file
            String rootPath = System.getProperty("user.dir") + "/resources/image/";
            File backgroundDir = new File(rootPath + File.separator + "event" + File.separator + event.getEventId() + File.separator + "background");
            if (!backgroundDir.exists())
                backgroundDir.mkdirs();

            File hostDir = new File(rootPath + File.separator + "event" + File.separator + event.getEventId() + File.separator + "host");
            if (!hostDir.exists())
                hostDir.mkdirs();

            // Create the file on server
            String backgroundFullPath = backgroundDir.getAbsolutePath() + File.separator + backgroundImageName;
            String hostFullPath = hostDir.getAbsolutePath() + File.separator + hostImageName;
            File backgroundServerFile = new File(backgroundFullPath);
            BufferedOutputStream backgroundStream = new BufferedOutputStream(new FileOutputStream(backgroundServerFile));
            backgroundStream.write(backgroundBytes);
            backgroundStream.close();

            File hostServerFile = new File(hostFullPath);
            BufferedOutputStream hostStream = new BufferedOutputStream(new FileOutputStream(hostServerFile));
            hostStream.write(hostBytes);
            hostStream.close();

            event.setBackgroundImg(backgroundDir.getPath() + File.separator + "background" + File.separator + backgroundImageName);
            event.setHostImg(hostDir.getPath() + File.separator + "host" + File.separator + hostImageName);
        }
    }

    public static void validateImage(byte[] bytes, String fileName) throws Exception {
        List<String> imageTypes = new ArrayList<String>();
        imageTypes.add(FileValidator.JPEG);
        imageTypes.add(FileValidator.PNG);

        boolean isImage = false;
        for (String imageType : imageTypes) {
            if (FileValidator.checkFileByType(bytes, imageType)) {
                isImage = true;
                break;
            }
        }
        if (!isImage) {
            throw new IllegalStateException("Invalid Format" +
                    fileName + " file type must be jpg/png/jpeg");
        }
    }
}
