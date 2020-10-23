package com.algaworks.pedidovenda.querydsl;

import java.util.List;
import java.util.Optional;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.pedidovenda.util.QuerydslUtils;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class AbstractRepository<T, ID> {

	@PersistenceContext
	protected EntityManager em;
	private final Class<T> clazz;
	private final EntityPath<T> entityPath;

	public AbstractRepository(Class<T> clazz) {
		this.clazz = clazz;
		this.entityPath = QuerydslUtils.getEntityPath(clazz);
	}
	
	public T save(T entity) {
		em.merge(entity);
		em.flush();
		return entity;
	}
	
	public void delete(T entity) {
		em.remove(entity);
		em.flush();
	}

	public T findById(ID id) {
		return em.find(clazz, id);
	}
	
	public Optional<T> findOne(ID id) {
		return Optional.ofNullable(findById(id));
	}

	public List<T> findAll() {
		return queryFromEntity().fetch();
	}

	public List<T> findAll(int first, int pageSize) {
		return queryFromEntity().offset(first).limit(pageSize).fetch();
	}

	public List<T> findAll(Predicate... predicates) {
		return queryFromEntity().where(predicates).fetch();
	}

	public List<T> findAll(int first, int pageSize, Predicate... predicates) {
		return queryFromEntity().where(predicates).offset(first).limit(pageSize).fetch();
	}

	public Long count() {
		return queryFromEntity().fetchCount();
	}

	public Long count(Predicate... predicates) {
		return queryFromEntity().where(predicates).fetchCount();
	}
	
	public boolean exists(Predicate... predicates) {
		return queryFromEntity().where(predicates).fetchCount() > 0;
	}

	protected JPAQuery<T> queryFromEntity() {
		return new JPAQuery<T>(em).from(entityPath);
	}
}
