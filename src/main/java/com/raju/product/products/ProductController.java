package com.raju.product.products;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raju.product.common.Response;
import com.raju.product.commoniservices.IMasterCommonServices;
import com.raju.product.constants.MessageConstants;
import com.raju.product.constants.QualifierConstants;
import com.raju.product.constants.UrlConstants;

@RestController
@RequestMapping(UrlConstants.PRODUCT)
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@Autowired
	@Qualifier(QualifierConstants.PRODUCT_QUALIFIER)
	private IMasterCommonServices<ProductDTO, ?> iMasterCommonServices;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@PostMapping
	public ResponseEntity<Response<Object>> save(@RequestBody ProductDTO dto) {
		return ResponseEntity
				.ok(Response.returnResponse(
						String.format(MessageConstants.SUCCESS_SAVE, MessageConstants.PRODUCTMASTER,
								iMasterCommonServices.save(dto).getProductId()),
						HttpStatus.CREATED, MessageConstants.SUCCESS));
	}

	@GetMapping(value = UrlConstants.FIND_BY_ID)
	public ResponseEntity<Response<Object>> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(Response.returnResponse(iMasterCommonServices.findById(id),
				String.format(MessageConstants.SUCCESS_ID, MessageConstants.PRODUCTMASTER), HttpStatus.OK,
				MessageConstants.SUCCESS));
	}

	@PutMapping(value = UrlConstants.UPDATE)
	public ResponseEntity<Response<Object>> update(@PathVariable("id") Long id, @RequestBody ProductDTO dto) {
		return ResponseEntity.ok(Response.returnResponse(
				String.format(MessageConstants.SUCCESS_UPDATE, MessageConstants.PRODUCTMASTER,
						iMasterCommonServices.update(id, dto).getProductId()),
				HttpStatus.CREATED, MessageConstants.SUCCESS));
	}

	@DeleteMapping(value = UrlConstants.DELETE)
	public ResponseEntity<Response<Object>> delete(@PathVariable("id") Long id) {
		return ResponseEntity
				.ok(Response.returnResponse(
						String.format(MessageConstants.SUCCESS_DELETE, MessageConstants.PRODUCTMASTER,
								iMasterCommonServices.deleteById(id).getProductId()),
						HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@GetMapping(value = UrlConstants.LIST)
	public ResponseEntity<Response<Object>> findAll(@RequestParam(name = "search", required = false) String search,
			Pageable pageable) {
		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.findAll(search, pageable),
				String.format(MessageConstants.SUCCESS_LIST, MessageConstants.PRODUCTMASTER), HttpStatus.OK,
				MessageConstants.SUCCESS));
	}

	@GetMapping(value = UrlConstants.LIST_ONLY)
	public ResponseEntity<Response<Object>> findList() {
		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.findAllListNonDeleted(),
				String.format(MessageConstants.SUCCESS_LIST, MessageConstants.PRODUCTMASTER), HttpStatus.OK,
				MessageConstants.SUCCESS));
	}

	@GetMapping(value = UrlConstants.ACTIVE_LIST)
	public ResponseEntity<Response<Object>> findAllNonDeletedActive(
			@RequestParam(name = "search", required = false) String search, Pageable pageable) {
		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.findAllNonDeletedActive(search, pageable),
				String.format(MessageConstants.SUCCESS_LIST, MessageConstants.PRODUCTMASTER), HttpStatus.OK,
				MessageConstants.SUCCESS));
	}

	@GetMapping(value = UrlConstants.ACTIVE_LIST_ONLY)
	public ResponseEntity<Response<Object>> findActiveList() {
		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.findAllListNonDeletedActive(),
				String.format(MessageConstants.SUCCESS_LIST, MessageConstants.PRODUCTMASTER), HttpStatus.OK,
				MessageConstants.SUCCESS));
	}

	@GetMapping(value = UrlConstants.TOGGLE_ACTIVE_STATUS_BY_ID)
	public ResponseEntity<Response<Object>> toggleProductActiveStatus(@PathVariable("id") Long id) {
		return ResponseEntity.ok(Response.returnResponse(
				String.format(MessageConstants.SUCCESS_DELETE, MessageConstants.PRODUCTMASTER,
						productServiceImpl.toggleProductActiveStatus(id).getProductId()),
				HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<Response<Object>> findByCategory(@PathVariable Category category, Pageable pageable) {

		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.findByCategory(category, pageable),
				"Products fetched successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@GetMapping("/brand/{brand}")
	public ResponseEntity<Response<Object>> findByBrand(@PathVariable String brand, Pageable pageable) {

		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.findByBrand(brand, pageable),
				"Products fetched successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@GetMapping("/price-range")
	public ResponseEntity<Response<Object>> findByPriceRange(@RequestParam Double minPrice,
			@RequestParam Double maxPrice, Pageable pageable) {

		return ResponseEntity
				.ok(Response.returnResponse(productServiceImpl.findByPriceRange(minPrice, maxPrice, pageable),
						"Products fetched successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@GetMapping("/featured")
	public ResponseEntity<Response<Object>> featuredProducts(Pageable pageable) {

		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.findFeaturedProducts(pageable),
				"Featured products fetched successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@GetMapping("/latest")
	public ResponseEntity<Response<Object>> latestProducts(Pageable pageable) {

		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.findLatestProducts(pageable),
				"Latest products fetched successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@GetMapping("/top-selling")
	public ResponseEntity<Response<Object>> topSellingProducts(Pageable pageable) {

		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.findTopSellingProducts(pageable),
				"Top selling products fetched successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@GetMapping("/low-stock")
	public ResponseEntity<Response<Object>> lowStockProducts(@RequestParam(defaultValue = "10") Integer stock) {

		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.findLowStockProducts(stock),
				"Low stock products fetched successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@PutMapping("/update-stock/{id}")
	public ResponseEntity<Response<Object>> updateStock(@PathVariable Long id, @RequestParam Integer quantity) {

		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.updateStock(id, quantity),
				"Stock updated successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@PutMapping("/reduce-stock/{id}")
	public ResponseEntity<Response<Object>> reduceStock(@PathVariable Long id, @RequestParam Integer quantity) {

		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.reduceStock(id, quantity),
				"Stock reduced successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@PutMapping("/update-rating/{id}")
	public ResponseEntity<Response<Object>> updateRating(@PathVariable Long id, @RequestParam Double rating) {

		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.updateRating(id, rating),
				"Rating updated successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@GetMapping("/tags")
	public ResponseEntity<Response<Object>> findByTags(@RequestParam List<String> tags, Pageable pageable) {

		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.findByTags(tags, pageable),
				"Products fetched successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

	@GetMapping("/availability/{id}")
	public ResponseEntity<Response<Object>> checkAvailability(@PathVariable Long id) {

		return ResponseEntity.ok(Response.returnResponse(productServiceImpl.checkAvailability(id),
				"Availability fetched successfully", HttpStatus.OK, MessageConstants.SUCCESS));
	}

}
