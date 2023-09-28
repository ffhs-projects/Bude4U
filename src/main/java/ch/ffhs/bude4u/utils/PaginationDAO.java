package ch.ffhs.bude4u.utils;

import java.util.List;

public interface PaginationDAO<T> {

    List<T> getIndex(int start, int offset);
}
