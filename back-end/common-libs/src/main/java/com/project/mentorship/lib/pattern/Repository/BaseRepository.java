package com.project.mentorship.lib.pattern.Repository;

import com.project.mentorship.lib.exception.NotImplementedException;
import java.util.List;
import java.util.Optional;

/**
 * Generic base repository interface defining CRUD operations.
 * <p>
 * Each method throws {@link NotImplementedException} until overridden by a
 * concrete repository implementation.
 *
 * @param <T>
 *            the type of entity handled by the repository
 */

public interface BaseRepository<T> {

	/**
	 * Persists a new entity.
	 *
	 * @param entity
	 *            the entity to save
	 * @return the persisted entity
	 * @throws NotImplementedException
	 *             if the method is not implemented
	 */
	default T save(T entity) {
		throw new NotImplementedException();
	}

	/**
	 * Updates an existing entity.
	 *
	 * @param entity
	 *            the entity to update
	 * @return the updated entity
	 * @throws NotImplementedException
	 *             if the method is not implemented
	 */
	default T update(T entity) {
		throw new NotImplementedException();
	}

	/**
	 * Deletes an entity by ID.
	 *
	 * @param id
	 *            the unique identifier of the entity
	 * @throws NotImplementedException
	 *             if the method is not implemented
	 */
	default void delete(String id) {
		throw new NotImplementedException();
	}

	/**
	 * Finds an entity by ID.
	 *
	 * @param id
	 *            the unique identifier of the entity
	 * @return an {@link Optional} containing the entity if found
	 * @throws NotImplementedException
	 *             if the method is not implemented
	 */
	default Optional<T> findById(String id) {
		throw new NotImplementedException();
	}

	/**
	 * Retrieves all entities.
	 *
	 * @return a list of all entities
	 * @throws NotImplementedException
	 *             if the method is not implemented
	 */
	default List<T> findAll() {
		throw new NotImplementedException();
	}
}
