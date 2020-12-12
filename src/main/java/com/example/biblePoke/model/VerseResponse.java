package com.example.biblePoke.model;

import lombok.Getter;

@Getter
public class VerseResponse {
    private String id;
    private String orgId;
    private String bookId;
    private String chapterId;
    private String bibleId;
    private String reference;
    private String content;
    private String verseCount;
    private String copyright;
    private NextVerse nextVerse;
    private PreviousVerse previousVerse;
    private MetaVerse metaVerse;
}
