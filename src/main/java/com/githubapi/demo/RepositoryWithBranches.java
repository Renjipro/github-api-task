package com.githubapi.demo;

import java.util.List;

public record RepositoryWithBranches(String repositoryName, String login, List<Branch> branches) {
    public RepositoryWithBranches(Repository repository, String username, List<Branch> branches){
        this(repository.name(), username, branches);
    }
}
