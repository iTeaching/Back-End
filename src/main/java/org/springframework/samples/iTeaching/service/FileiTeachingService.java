package org.springframework.samples.iTeaching.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.FileiTeaching;
import org.springframework.samples.iTeaching.repository.FileiTeachingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileiTeachingService {

	@Autowired
	private FileiTeachingRepository fileiTeachingRepository;

	public FileiTeaching saveFile(MultipartFile file) {
		String docname = file.getOriginalFilename();
		try {
			FileiTeaching doc = new FileiTeaching(docname, file.getContentType(), file.getBytes());
			return fileiTeachingRepository.save(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Optional<FileiTeaching> getFile(Integer fileId) {
		return fileiTeachingRepository.findById(fileId);
	}

	public List<FileiTeaching> getFiles() {
		return fileiTeachingRepository.findAll();
	}
}
