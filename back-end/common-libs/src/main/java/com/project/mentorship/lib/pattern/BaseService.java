package com.project.mentorship.lib.pattern;

import com.project.mentorship.lib.exception.NotImplementedException;
import java.util.List;
import java.util.Optional;

/**
 * Generic base service interface providing common CRUD operations.
 * <p>
 * Each method has a default implementation that throws
 * {@link NotImplementedException}. Concrete services should override these
 * methods as needed.
 * 
 * @param <T>
 *            the type of entity handled by the service
 */

public interface BaseService<T> {

	/**
	 * Creates a new entity.
	 * 
	 * @param entity
	 *            the entity to create
	 * @return the created entity
	 * @throws NotImplementedException
	 *             if the method is not implemented
	 */
	default T create(T entity) {
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
	 * Deletes an entity by its ID.
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
	 * Finds an entity by its ID.
	 * 
	 * @param id
	 *            the unique identifier of the entity
	 * @return an {@link Optional} containing the entity if found, or empty
	 *         otherwise
	 * @throws NotImplementedException
	 *             if the method is not implemented
	 */
	default Optional<T> findById(String id) {
		throw new NotImplementedException();
	}

	/**
	 * Returns all entities.
	 * 
	 * @return a list of all entities
	 * @throws NotImplementedException
	 *             if the method is not implemented
	 */
	default List<T> findAll() {
		throw new NotImplementedException();
	}
}
