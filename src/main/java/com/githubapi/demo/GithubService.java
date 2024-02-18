package com.githubapi.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GithubService {

    private final WebClient webClient;

    @Autowired
    public GithubService(WebClient webClient) {
        this.webClient = webClient;
    }


    public ResponseEntity<?> getUserRepositories(String username) {
        try {
            List<RepositoryWithBranches> result = fetchRepositoriesWithBranches(username).block();
            return ResponseEntity.ok(result);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("USER NOT FOUND");
        }

    }

    private Mono<List<RepositoryWithBranches>> fetchRepositoriesWithBranches(String username) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{username}/repos").build(username))
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response -> Mono.error(new RuntimeException()))
                .bodyToMono(new ParameterizedTypeReference<List<Repository>>() {})
                .flatMapMany(Flux::fromIterable)
                .filter(repository -> !repository.isFork())
                .flatMap(repository -> webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/repos/{owner}/{repo}/branches")
                                .build(username, repository.name()))
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<Branch>>() {})
                        .map(branches -> new RepositoryWithBranches(repository, username, branches))
                )
                .collectList();
    }
}
