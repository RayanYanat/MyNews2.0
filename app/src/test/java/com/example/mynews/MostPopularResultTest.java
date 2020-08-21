package com.example.mynews;

import org.junit.Test;

import java.util.List;

import models.mostPopular.Medium;
import models.mostPopular.MostPopulaArticles;
import models.topStories.TopStoriesArticles;

import static junit.framework.Assert.assertEquals;

public class MostPopularResultTest {

    List<Medium> urlImageMedia;

    @Test
    public void GetMostPopularTest() throws Exception {
        MostPopulaArticles result = new MostPopulaArticles ("testSection","testSubSection","testTitle","testUrl","testPublishedDate",urlImageMedia);

        assertEquals("testSection", result.getSection());
        assertEquals("testSubSection", result.getSubsection());
        assertEquals("testTitle", result.getTitle());
        assertEquals("testUrl", result.getUrl());
        assertEquals("testPublishedDate", result.getPublishedDate());
        assertEquals(urlImageMedia, result.getUrlImageMedia());
    }
}
