package com.example.parcer.demo.utils;

import com.example.parcer.demo.dto.SourceDto;
import lombok.experimental.UtilityClass;

import java.net.MalformedURLException;
import java.net.URL;

@UtilityClass
public class GitHubDataUtil {

    private static final String GITHUB_USERNAME_REPO_BRANCH_README = "https://raw.githubusercontent.com/%s/%s/%s/README.md";

    public static String convertToRawUrl(SourceDto source) throws MalformedURLException {
        URL url = new URL(source.getUrl());
        String path = url.getPath();

        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        String[] parts = path.split("/");

        String username = parts[0];
        String repository = parts[1];

        return String.format(GITHUB_USERNAME_REPO_BRANCH_README,
                username,
                repository,
                source.getGitHubBranch());
    }
}
