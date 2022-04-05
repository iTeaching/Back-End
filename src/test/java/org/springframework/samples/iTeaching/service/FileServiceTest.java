package org.springframework.samples.iTeaching.service;

import static org.assertj.core.api.Assertions.assertThat;



import java.nio.file.Path;
import java.util.stream.Stream;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FileServiceTest {

	@Autowired
	private FileServiceImpl fileService;
	
		
	@Test
	public void testLoadAll() throws Exception {
		Stream<Path> p = this.fileService.loadAll();

		assertThat(p.count()==2);
	}
//	@Test
//	public void testSave() throws Exception {
//		
//	}
	@Test
	public void testLoad() throws Exception {
		Resource p = this.fileService.load("file1");

		assertThat(p.equals("/Back-End/uploads/file1.pdf"));
	}


}
