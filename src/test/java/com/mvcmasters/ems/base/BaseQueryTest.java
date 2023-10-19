package com.mvcmasters.ems.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseQueryTest {

    /**
     * Default value for the page parameter.
     */
    private static final int DEFAULT_PAGE_VALUE = 1;

    /**
     * Default value for the limit parameter.
     */
    private static final int DEFAULT_LIMIT_VALUE = 10;

    /**
     * Default test value for the page parameter.
     */
    private static final int DEFAULT_TEST_VALUE = 5;

    /**
     * Default test value for the limit parameter.
     */
    private static final int DEFAULT_TEST_LIMIT = 20;

    /**
     * The BaseQuery instance used for testing.
     */
    private BaseQuery baseQuery;

    /**
     * Set up the test by initializing the BaseQuery instance.
     */
    @BeforeEach
    public void setUp() {
        baseQuery = new BaseQuery();
    }

    /**
     * Test the default value of the page parameter.
     */
    @Test
    public void testDefaultPageValue() {
        assertEquals(DEFAULT_PAGE_VALUE, baseQuery.getPage());
    }

    /**
     * Test the default value of the limit parameter.
     */
    @Test
    public void testDefaultLimitValue() {
        assertEquals(DEFAULT_LIMIT_VALUE, baseQuery.getLimit());
    }

    /**
     * Test setting the page parameter to a specific value.
     */
    @Test
    public void testSetPage() {
        int testPage = DEFAULT_TEST_VALUE;
        baseQuery.setPage(testPage);
        assertEquals(testPage, baseQuery.getPage());
    }

    /**
     * Test setting the limit parameter to a specific value.
     */
    @Test
    public void testSetLimit() {
        int testLimit = DEFAULT_TEST_LIMIT;
        baseQuery.setLimit(testLimit);
        assertEquals(testLimit, baseQuery.getLimit());
    }
}
