package ch.ffhs.bude4u.utils;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {

    /**
     * get entity with matching identifier
     * @param id the id to filter for
     * @return matching entity
     */
    Optional<T> get(String id);

    /**
     * fetches list of all entities
     * @return list of Type <T>
     * {@code @TODO:} Maybe add pagination?!
     */
    List<T> getAll();

    /**
     * creates new entity in database
     * @param t entity to create
     */
    void create(T t);

    /**
     * update provided entity in database
     * @param t entity to update
     */
    void update(T t);

    /**
     * delete provided entity in database
     * @param t entity to delete
     */
    void delete(T t);

}
