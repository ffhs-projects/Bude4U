package ch.ffhs.bude4u.utils;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {

    /**
     * get entity with matching identifier
     * @param id the id to filter for
     * @return matching entity
     */
    public Optional<T> get(String id);

    /**
     * fetches list of all entities
     * @return list of Type <T>
     * @TODO: Maybe add pagination?!
     */
    public List<T> getAll();

    /**
     * creates new entity in database
     * @param t entity to create
     */
    public void create(T t);

    /**
     * update provided entity in database
     * @param t entity to update
     */
    public void update(T t);

    /**
     * delete provided entity in database
     * @param t entity to delete
     */
    public void delete(T t);

}
