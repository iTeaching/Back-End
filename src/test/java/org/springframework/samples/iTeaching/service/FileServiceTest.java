package org.springframework.samples.iTeaching.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.multipart.MultipartFile;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FileServiceTest {

	@Autowired
	private FileServiceImpl fileService;
	
		
	@Test
	public void testLoadAll() throws Exception {
		Stream<Path> p = this.fileService.loadAll();

		assertEquals(2, p.count());
	}
//	@Test
//	public void testSave() throws Exception {
//		
//		MultipartFile multipartFile = new MockMultipartFile("file3.pdf", new FileInputStream(new File("")));
//
//        this.fileService.save(multipartFile);
//
//        assertEquals(3, fileService.loadAll().count());
//	}
//	
	@Test
    public void testLoad() throws Exception {
        Resource p = this.fileService.load("file1.pdf");
        assertEquals(4833471L, p.contentLength());
    }


}
