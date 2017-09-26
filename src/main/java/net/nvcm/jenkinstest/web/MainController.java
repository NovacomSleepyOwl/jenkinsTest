package net.nvcm.jenkinstest.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(value = "/{dividend}/{divider}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> division (@PathVariable("dividend") int a, @PathVariable("divider") int b){
        String body = Integer.toString(a/b);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
