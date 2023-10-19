package com.mvcmasters.ems.base;

/**
 * BaseQuery represents the basic structure for querying with pagination.
 */
public class BaseQuery {

    /** Default page number if not provided. */
    private static final int DEFAULT_PAGE = 1;

    /** Default limit for page size if not provided. */
    private static final int DEFAULT_LIMIT = 10;

    /** The current page number for the query. */
    private Integer page = DEFAULT_PAGE;

    /** The limit or maximum number of results per page for the query. */
    private Integer limit = DEFAULT_LIMIT;

    /**
     * Retrieves the page number.
     *
     * @return the page number
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Sets the page number.
     *
     * @param newPage the new page number to set
     */
    public void setPage(final Integer newPage) {
        this.page = newPage;
    }

    /**
     * Retrieves the limit of results per page.
     *
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets the limit of results per page.
     *
     * @param newLimit the new limit to set
     */
    public void setLimit(final Integer newLimit) {
        this.limit = newLimit;
    }
}
