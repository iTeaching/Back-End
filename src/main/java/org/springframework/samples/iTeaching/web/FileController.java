package org.springframework.samples.iTeaching.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.iTeaching.model.FileiTeaching;
import org.springframework.samples.iTeaching.service.FileiTeachingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FileController {

	@Autowired 
	private FileiTeachingService fileiTeachingService;
	
	@GetMapping("/files")
	public String get(Model model) {
		List<FileiTeaching> docs = fileiTeachingService.getFiles();
		model.addAttribute("docs", docs);
		return "files/fileList";
	}
	
	@PostMapping("/files")
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, Model model) {
		if(files.length!=0) {
		for (MultipartFile file: files) {
			if(file.getContentType().equals("application/pdf") 
					|| file.getContentType().equals("image/jpeg") 
					|| file.getContentType().equals("image/png")){

				fileiTeachingService.saveFile(file);
			}else {
				model.addAttribute("errorMessage", "Solo se pueden subir archivos PDF, JPG o PNG");
				
			}
		}
		}
		List<FileiTeaching> docs = fileiTeachingService.getFiles();
		model.addAttribute("docs", docs);
		return "files/fileList";
	}
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		FileiTeaching doc = fileiTeachingService.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
}