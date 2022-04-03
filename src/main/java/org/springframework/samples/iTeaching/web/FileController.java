package org.springframework.samples.iTeaching.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.iTeaching.model.FileiTeaching;
import org.springframework.samples.iTeaching.model.Response;
import org.springframework.samples.iTeaching.service.FileServiceAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileServiceAPI fileServiceAPI;
    
    
    private static final String EXTENSION = ".pdf";
	private static final String SERVER_LOCATION = "/downloads";
    

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws Exception{
            fileServiceAPI.save(files);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Los archivos fueron cargados correctamente"));
        }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception{
            Resource resource=fileServiceAPI.load(filename);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
        }

    //Listar los elementos que se encuentran en el servidor
    @GetMapping("/files")
    public ResponseEntity<List<FileiTeaching>> getAllFiles() throws Exception{
        List<FileiTeaching> files= fileServiceAPI.loadAll().map(path->{
            String filename=path.getFileName().toString();
            String url=MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileiTeaching(filename,url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    
    
    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@RequestParam("fileName") String fileName) throws IOException {
        File file = new File(SERVER_LOCATION + File.separator + fileName + EXTENSION);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=iTeaching.pdf");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    
    
    
    }
