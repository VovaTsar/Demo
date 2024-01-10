package com.example.parcer.demo.service.impl;

import com.example.parcer.demo.dto.SourceDto;
import com.example.parcer.demo.service.DataRetrieverService;
import com.example.parcer.demo.service.WordsProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WordsProcessorServiceImpl implements WordsProcessorService {

    private final DataRetrieverService dataRetrieverService;


    public List<String> countPopularWordsFromSource(SourceDto source) {
        log.info("Request to retrieve content from URL : {}", source.getUrl());
        String content = dataRetrieverService.retrieveContent(source);

        return findMostPopularWords(content);
    }


    /**
     * Finds the most popular words in the given text based on word frequency.
     * This method takes a text as input, processes it, and identifies the most popular words
     * that are longer than 4 characters. The popularity is determined by the frequency of each word
     * in the text. The result is a list of the top 3 most popular words.
     *
     * @param text The input text to analyze and extract popular words from.
     * @return A list containing the top 3 most popular words longer than 4 characters.
     * The list is sorted in descending order of popularity.
     */
    private List<String> findMostPopularWords(String text) {
        log.info("Find most popular words for text : {}", text);

        String[] words = text.split("\\s+");

        List<String> filteredWords = Arrays.stream(words)
                .filter(word -> word.length() > 4)
                .toList();

        Map<String, Long> wordCountMap = filteredWords.stream()
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        return wordCountMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
