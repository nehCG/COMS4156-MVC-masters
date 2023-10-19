package com.mvcmasters.ems.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SharedDataModelTest {

    /**
     * Test the getId and setId methods of SharedDataModel.
     */
    @Test
    public void testIdGetterSetter() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setId(1);

        assertEquals(1, sharedData.getId());
    }

    /**
     * Test the getUid and setUid methods of SharedDataModel.
     */
    @Test
    public void testUserIdGetterSetter() {
        // Arrange
        SharedDataModel model = new SharedDataModel();
        Integer expectedUserId = 1;

        // Act
        model.setUid(expectedUserId);
        Integer actualUserId = model.getUid();

        // Assert
        assertEquals(expectedUserId, actualUserId);
    }

    /**
     * Test the getSubject and setSubject methods of SharedDataModel.
     */
    @Test
    public void testSubjectGetterSetter() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setSubject("abc");

        assertEquals("abc", sharedData.getSubject());
    }

    /**
     * Test when setSubject with Whitespace.
     */
    @Test
    public void testSetSubjectWithWhitespace() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setSubject("  abc  ");

        assertEquals("abc", sharedData.getSubject());
    }

    /**
     * Test when setSubject with null.
     */
    @Test
    public void testSetSubjectWithNull() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setSubject(null);

        assertNull(sharedData.getSubject());
    }

    /**
     * Test when setSubject with empty string.
     */
    @Test
    public void testSetSubjectToEmptyString() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setSubject("");

        assertEquals("", sharedData.getSubject());
    }

    /**
     * Test the getContent and setContent methods of SharedDataModel.
     */
    @Test
    public void testContentGetterSetter() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setContent("abc");

        assertEquals("abc", sharedData.getContent());
    }

    /**
     * Test when setContent with whitespace .
     */
    @Test
    public void testSetContentWithWhitespace() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setContent("  abc  ");

        assertEquals("abc", sharedData.getContent());
    }

    /**
     * Test when setContent with null .
     */
    @Test
    public void testSetContentToNull() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setContent(null);

        assertNull(sharedData.getContent());
    }

    /**
     * Test when setContent with empty string .
     */
    @Test
    public void testSetContentToEmptyStringy() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setContent("");

        assertEquals("", sharedData.getContent());
    }

    /**
     * Test the getLastModifiedBy and
     * setLastModifiedBy methods of SharedDataModel.
     */
    @Test
    public void testLastModifiedUserIDGetterSetter() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setLastModifiedBy(1);

        assertEquals(1, sharedData.getLastModifiedBy());
    }

    /**
     * Test the getCreatedTime and
     * setCreatedTime methods of SharedDataModel.
     */
    @Test
    public void testCreatedTimeGetterSetter() {
        SharedDataModel sharedData = new SharedDataModel();

        LocalDateTime now = LocalDateTime.now();

        sharedData.setCreatedTime(now);

        assertEquals(now, sharedData.getCreatedTime());
    }

    /**
     * Test the getModifiedTime and
     * setModifiedTime methods of SharedDataModel.
     */
    @Test
    public void testLastModifiedTime() {
        SharedDataModel sharedData = new SharedDataModel();

        LocalDateTime now = LocalDateTime.now();

        sharedData.setModifiedTime(now);

        assertEquals(now, sharedData.getModifiedTime());
    }
}
