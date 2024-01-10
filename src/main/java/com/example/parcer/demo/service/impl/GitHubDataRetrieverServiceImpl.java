package com.example.parcer.demo.service.impl;

import com.example.parcer.demo.dto.SourceDto;
import com.example.parcer.demo.service.DataRetrieverService;
import com.example.parcer.demo.service.error.RetrievingContentRuntimeException;
import com.example.parcer.demo.utils.GitHubDataUtil;
import com.example.parcer.demo.utils.MarkdownUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class GitHubDataRetrieverServiceImpl implements DataRetrieverService {

    private final RestTemplate restTemplate;

    public String retrieveContent(SourceDto source) {
        try {
            String rawUrl = GitHubDataUtil.convertToRawUrl(source);
            String template = restTemplate.getForObject(rawUrl, String.class);

            return MarkdownUtil.convertMarkdownToPlainText(template);

        } catch (Exception ex) {
            log.error("Exception during retrieving content github, URL: {}", source.getUrl());
            throw new RetrievingContentRuntimeException("Exception during retrieving github content", ex);
        }
    }

}
