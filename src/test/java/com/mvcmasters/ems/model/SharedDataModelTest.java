package com.mvcmasters.ems.model;

import org.apache.ibatis.jdbc.Null;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SharedDataModelTest {

    @Test
    public void testIdGetterSetter() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setId(1);

        assertEquals(1, sharedData.getId());
    }

    @Test
    public void testUserIdGetterSetter() {
        // Arrange
        SharedDataModel model = new SharedDataModel();
        Integer expectedUserId = 456;

        // Act
        model.setUid(expectedUserId);
        Integer actualUserId = model.getUid();

        // Assert
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    public void testSubjectGetterSetter() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setSubject("abc");

        assertEquals("abc", sharedData.getSubject());
    }
    @Test
    public void whenSettingSubject_withWhitespace_thenTrimmed() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setSubject("  abc  ");

        assertEquals("abc", sharedData.getSubject());
    }

    @Test
    public void whenSettingSubject_toNull_thenSubjectIsNull() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setSubject(null);

        assertNull(sharedData.getSubject());
    }

    @Test
    public void whenSettingSubject_toEmptyString_thenSubjectIsEmpty() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setSubject("");

        assertEquals("", sharedData.getSubject());
    }

    @Test
    public void testContentGetterSetter() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setContent("abc");

        assertEquals("abc", sharedData.getContent());
    }
    @Test
    public void whenSettingContent_withWhitespace_thenTrimmed() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setContent("  abc  ");

        assertEquals("abc", sharedData.getContent());
    }

    @Test
    public void whenSettingContent_toNull_thenContentIsNull() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setContent(null);

        assertNull(sharedData.getContent());
    }

    @Test
    public void whenSettingContent_toEmptyString_thenContentIsEmpty() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setContent("");

        assertEquals("", sharedData.getContent());
    }

    @Test
    public void testLastModifiedUserIDGetterSetter() {
        SharedDataModel sharedData = new SharedDataModel();

        sharedData.setLastModifiedBy(1);

        assertEquals(1, sharedData.getLastModifiedBy());
    }

    @Test
    public void testCreatedTimeGetterSetter() {
        SharedDataModel sharedData = new SharedDataModel();

        LocalDateTime now = LocalDateTime.now();

        sharedData.setCreatedTime(now);

        assertEquals(now, sharedData.getCreatedTime());
    }

    @Test
    public void testLastModifiedTime() {
        SharedDataModel sharedData = new SharedDataModel();

        LocalDateTime now = LocalDateTime.now();

        sharedData.setModifiedTime(now);

        assertEquals(now, sharedData.getModifiedTime());
    }
}
