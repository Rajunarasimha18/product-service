package com.raju.product.products;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProductDTO {

	private Long productId;	
   
    private String productName;

    private String description;

    private String brand;

    private Double price;

    private Double discountPrice;

    private Integer stockQuantity;

    private Boolean inStock = true;

    private Category category;

    private List<String> imageUrls;

    private List<String> tags;

    private Double rating = 0.0;

    private Integer totalReviews = 0;

    private Integer totalSold = 0;

    private Boolean isFeatured = false;
    
	private Boolean isActive = true;

	private String createdBy;

	private Date createdDate;

	private Boolean isDeletedValue = false;

	private String modifiedBy;

	private Date modifiedDate;
	
}
