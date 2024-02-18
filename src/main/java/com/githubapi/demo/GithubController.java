package com.githubapi.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;


@RestController
public class GithubController {

    @Autowired
    public GithubService githubService;

    @GetMapping(value = "/not-forks-repositories/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username, @RequestHeader(HttpHeaders.ACCEPT) String acceptHeader) throws URISyntaxException, IOException, InterruptedException {
        if (!acceptHeader.equals("application/json"))
            return ResponseEntity.status(HttpStatusCode.valueOf(406)).build();
        return githubService.getUserRepositories(username);
    }
}
