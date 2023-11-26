package com.mvcmasters.ems.vo;

import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class ModuleTest {

    private Module module;

    @Before
    public void setUp() {
        module = new Module();
    }

    @Test
    public void testSetAndGetId() {
        Integer id = 1;
        module.setId(id);
        assertEquals(id, module.getId());
    }

    @Test
    public void testSetAndGetModuleName() {
        String moduleName = "Test Module";
        module.setModuleName(moduleName);
        assertEquals(moduleName, module.getModuleName());
        // Testing with null
        module.setModuleName(null);
        assertNull(module.getModuleName());
    }

    @Test
    public void testSetAndGetModuleStyle() {
        String moduleStyle = "Style1";
        module.setModuleStyle(moduleStyle);
        assertEquals(moduleStyle, module.getModuleStyle());
        // Testing with null
        module.setModuleStyle(null);
        assertNull(module.getModuleStyle());
    }

    @Test
    public void testSetAndGetUrl() {
        String url = "http://example.com";
        module.setUrl(url);
        assertEquals(url, module.getUrl());
        // Testing with null
        module.setUrl(null);
        assertNull(module.getUrl());
    }

    @Test
    public void testSetAndGetParentId() {
        Integer parentId = 2;
        module.setParentId(parentId);
        assertEquals(parentId, module.getParentId());
    }

    @Test
    public void testSetAndGetParentOptValue() {
        String parentOptValue = "Value1";
        module.setParentOptValue(parentOptValue);
        assertEquals(parentOptValue, module.getParentOptValue());
        // Testing with null
        module.setParentOptValue(null);
        assertNull(module.getParentOptValue());
    }

    @Test
    public void testSetAndGetGrade() {
        Integer grade = 3;
        module.setGrade(grade);
        assertEquals(grade, module.getGrade());
    }

    @Test
    public void testSetAndGetOptValue() {
        // Test with a non-null value
        String optValue = "Option1";
        module.setOptValue(optValue);
        assertEquals(optValue.trim(), module.getOptValue());

        // Test with a non-null value that includes leading/trailing whitespace
        String optValueWithSpace = " Option1 ";
        module.setOptValue(optValueWithSpace);
        assertEquals(optValueWithSpace.trim(), module.getOptValue());

        // Test with a null value
        module.setOptValue(null);
        assertNull(module.getOptValue());
    }

    @Test
    public void testSetAndGetOrders() {
        Integer orders = 4;
        module.setOrders(orders);
        assertEquals(orders, module.getOrders());
    }

    @Test
    public void testSetAndGetIsValid() {
        Byte isValid = 1;
        module.setIsValid(isValid);
        assertEquals(isValid, module.getIsValid());
    }

    @Test
    public void testSetAndGetCreateDate() {
        Date createDate = new Date();
        module.setCreateDate(createDate);
        assertEquals(createDate, module.getCreateDate());
    }

    @Test
    public void testSetAndGetUpdateDate() {
        Date updateDate = new Date();
        module.setUpdateDate(updateDate);
        assertEquals(updateDate, module.getUpdateDate());
    }
}
