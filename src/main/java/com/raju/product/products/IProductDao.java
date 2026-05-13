package com.raju.product.products;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductDao extends MongoRepository<Product,Long>{

	@Query(value="{ '$and': [ " + "{ 'is_deleted_value': false }, " + "{ 'is_active': true }, " + "{ '$or': [ "
			+ "{ 'product_name': { '$regex': ?0, '$options': 'i' } }, "
			+ "{ 'description': { '$regex': ?0, '$options': 'i' } }, "
			+ "{ 'brand': { '$regex': ?0, '$options': 'i' } }, "
			+ "{ 'price': { '$regex': ?0, '$options': 'i' } }, "
			+ "{ 'discount_price': { '$regex': ?0, '$options': 'i' } }, "
			+ "{ 'stock_quantity': { '$regex': ?0, '$options': 'i' } } " + "] } " + "] }", sort = "{ 'created_date': -1 }")
	Page<Product> findAllNonDeletedActiveBySearch(String search, Pageable pageable);

	@Query(value="{ 'is_deleted_value': false, 'is_active': true }", sort = "{ 'created_date': -1 }")
	Page<Product> findAllNonDeletedActive(Pageable pageable);
	
	@Query(value="{ 'is_deleted_value': false }", sort = "{ 'created_date': -1 }")
	Page<Product> findAllNonDeleted(Pageable pageable);
	
	@Query(value="{ '$and': [ " + "{ 'is_deleted_value': false }, " + "{ '$or': [ "
			+ "{ 'product_name': { '$regex': ?0, '$options': 'i' } }, "
			+ "{ 'description': { '$regex': ?0, '$options': 'i' } }, "
			+ "{ 'brand': { '$regex': ?0, '$options': 'i' } }, "
			+ "{ 'price': { '$regex': ?0, '$options': 'i' } }, "
			+ "{ 'discount_price': { '$regex': ?0, '$options': 'i' } }, "
			+ "{ 'stock_quantity': { '$regex': ?0, '$options': 'i' } } " + "] } " + "] }", sort = "{ 'created_date': -1 }")
	Page<Product> findAllNonDeletedBySearch(String search, Pageable pageable);

	@Query(value="{ 'is_deleted_value': false, 'is_active': true }", sort = "{ 'created_date': -1 }")
	List<Product> findAllListNonDeletedActive();
	
	@Query(value="{ 'is_deleted_value': false }", sort = "{ 'created_date': -1 }")
	List<Product> findAllListNonDeleted();
	

}
