package com.example.parcer.demo.utils;

import lombok.experimental.UtilityClass;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.Jsoup;

@UtilityClass
public class MarkdownUtil {

    public static String convertMarkdownToPlainText(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);

        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String htmlContent = renderer.render(document);

        return Jsoup
                .parse(htmlContent)
                .text();
    }
}
