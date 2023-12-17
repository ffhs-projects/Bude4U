package ch.ffhs.bude4u.utils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericDAO<T> {

    /**
     * get entity with matching identifier
     *
     * @param id the id to filter for
     * @return matching entity
     */
    Optional<T> get(UUID id);

    /**
     * fetches list of all entities
     *
     * @return list of Type <T>
     */
    Optional<List<T>> getAll();

    /**
     * creates new entity in database
     *
     * @param t entity to create
     */
    void create(T t);

    /**
     * update provided entity in database
     *
     * @param t entity to update
     */
    void update(T t);

    /**
     * delete provided entity in database
     *
     * @param id entity to delete
     */
    void delete(UUID id);

    /**
     * Requests a list of Data for with pageNumber & offset parameter for paginated views.
     *
     * @param pageNumber pageNumber of first page (included)
     * @param pageSize   amount of values to return
     * @return returns 0 to pageSize entries.
     */
    List<T> getPaginatedItems(int pageNumber, int pageSize);

    /**
     * Gets a list of advertisements by user id
     *
     * @param advUserId User id
     * @return List of advertisements
     */
    Optional<List<T>> getByUserId(UUID advUserId);

    /**
     * Gets a list of advertisements by filter
     *
     * @param priceFrom Price from
     * @param priceTo   Price to
     * @param roomFrom  Room from
     * @param roomTo    Room to
     * @param category  Category
     * @param city      City
     * @return List of advertisements
     */
    Optional<List<T>> getByFilter(Long priceFrom, Long priceTo, Double roomFrom, Double roomTo, String category, String city);
}
