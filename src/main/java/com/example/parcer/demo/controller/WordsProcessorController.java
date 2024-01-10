package com.example.parcer.demo.controller;

import com.example.parcer.demo.dto.SourceDto;
import com.example.parcer.demo.service.WordsProcessorService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class WordsProcessorController {

    private final WordsProcessorService wordsProcessorService;

    @GetMapping("/count")
    public List<String> countPopularWordsFromSource(@NotBlank @RequestParam String url,
                                                    @NotBlank @RequestParam String gitHubBranch) {

        log.debug("Request to count the popular words from URL: {}", url);
        var source = SourceDto.builder()
                .url(url)
                .gitHubBranch(gitHubBranch)
                .build();

        return wordsProcessorService.countPopularWordsFromSource(source);
    }

}
