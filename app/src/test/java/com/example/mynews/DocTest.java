package com.example.mynews;

import org.junit.Test;

import models.search.Doc;

import static junit.framework.Assert.assertEquals;

public class DocTest {

    @Test
    public void GetDocTest() throws Exception {
        Doc doc = new Doc("testWebURL",null,"testPubDate","testSectionName","testSubSectionName");

        assertEquals("testWebURL", doc.getWebUrl());
        assertEquals(null, doc.getHeadline());
        assertEquals("testPubDate", doc.getPubDate());
        assertEquals("testSectionName", doc.getSectionName());
        assertEquals("testSubSectionName", doc.getSubsectionName());

    }
}
