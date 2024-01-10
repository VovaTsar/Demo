package com.example.parcer.demo.service;

import com.example.parcer.demo.dto.SourceDto;

import java.util.List;

public interface WordsProcessorService {

    List<String> countPopularWordsFromSource(SourceDto source);
}
