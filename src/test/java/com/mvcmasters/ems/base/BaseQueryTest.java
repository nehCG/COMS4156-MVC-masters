package com.mvcmasters.ems.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseQueryTest {

    private BaseQuery baseQuery;

    @BeforeEach
    public void setUp() {
        baseQuery = new BaseQuery();
    }

    @Test
    public void testDefaultPageValue() {
        assertEquals(1, baseQuery.getPage());
    }

    @Test
    public void testDefaultLimitValue() {
        assertEquals(10, baseQuery.getLimit());
    }

    @Test
    public void testSetPage() {
        int testPage = 5;
        baseQuery.setPage(testPage);
        assertEquals(testPage, baseQuery.getPage());
    }

    @Test
    public void testSetLimit() {
        int testLimit = 20;
        baseQuery.setLimit(testLimit);
        assertEquals(testLimit, baseQuery.getLimit());
    }
}
