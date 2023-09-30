package ch.ffhs.bude4u.utils;

import java.util.List;

public interface PaginationDAO<T> {

    /**
     * Requests a list of Data for with start & offset parameter for paginated views.
     * @param start start of first entry (included)
     * @param length amount of values to return
     * @return returns 0 to length entries.
     */
    List<T> getPaginatedItems(int start, int length);
}
