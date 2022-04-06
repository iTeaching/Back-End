package org.springframework.samples.iTeaching.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.FileiTeaching;
import org.springframework.samples.iTeaching.model.Valoracion;
import org.springframework.samples.iTeaching.repository.FileiTeachingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileiTeachingService {

	@Autowired
	private FileiTeachingRepository fileiTeachingRepository;

	public FileiTeaching saveFile(MultipartFile file, Asignatura asignatura) {
		String docname = file.getOriginalFilename();
		try {
			FileiTeaching doc = new FileiTeaching(docname, file.getContentType(), file.getBytes(), asignatura);
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
	
	@Transactional(readOnly = true)
	public Collection<FileiTeaching> findFileByAsignatura(Asignatura asignatura) throws DataAccessException {
		return fileiTeachingRepository.findByAsignatura(asignatura);
	}
}
