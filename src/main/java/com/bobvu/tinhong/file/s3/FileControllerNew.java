package com.bobvu.tinhong.file.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user/getPresignedURL")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileControllerNew {

    private static final String FILE_NAME = "fileName";

    @Autowired
    FileServiceNew fileServiceNew;

    @GetMapping
    public ResponseEntity<Object> findByName(HttpServletRequest request, @RequestBody(required = false) Map<String, String> params) {
        final String path = request.getServletPath();
        if (params.containsKey(FILE_NAME))
            return new ResponseEntity<>(fileServiceNew.findByName(params.get(FILE_NAME)), HttpStatus.OK);
        return null;
    }

    @PostMapping
    public ResponseEntity<Object> saveFile(@RequestParam("extension") String extension) {
        return new ResponseEntity<>(fileServiceNew.save(extension), HttpStatus.OK);
    }

}