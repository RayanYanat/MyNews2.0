package com.example.mynews;

import org.junit.Test;

import java.util.List;

import models.topStories.Multimedia;

import models.topStories.TopStoriesArticles;

import static junit.framework.Assert.assertEquals;

public class TopStoriesResultTest {

    List<Multimedia> urlImageMedia;

    @Test
    public void GetTopStoriesTest() throws Exception {
        TopStoriesArticles result = new TopStoriesArticles ("testSection","testSubSection","testTitle","testUrl","testPublishedDate",urlImageMedia);

        assertEquals("testSection", result.getSection());
        assertEquals("testSubSection", result.getSubsection());
        assertEquals("testTitle", result.getTitle());
        assertEquals("testUrl", result.getUrl());
        assertEquals("testPublishedDate", result.getPublishedDate());
        assertEquals(urlImageMedia, result.getMultimedia());
    }
}
