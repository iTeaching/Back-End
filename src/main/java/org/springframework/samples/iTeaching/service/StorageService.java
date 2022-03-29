package org.springframework.samples.iTeaching.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	public String store(MultipartFile file, String folder, HttpSession http) {
		String fileName;
		try {
		String path = http.getServletContext().getRealPath("/").replace("webapp", "resources\\static\\resources\\images\\" + folder);
		fileName = file.getName().trim().concat(String.valueOf(new Date().getTime())).concat(".png");
		file.transferTo(new File(path + fileName));
		TimeUnit.SECONDS.sleep(3);
		} catch (Exception exception) {
			fileName = "error";
		}
		return fileName;
	}
	
	
	public static void saveFile(String uploadDir, String fileName,
            MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
	
	
	
	

