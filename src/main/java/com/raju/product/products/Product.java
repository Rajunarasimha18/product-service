package com.raju.product.products;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.raju.product.common.SequenceListener;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
@EntityListeners(SequenceListener.class)
@ToString
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SEQUENCE_NAME = "product_sequence";

	@Id
	private Long productId;	

    @Indexed
    @Field("product_name")
    private String productName;

    @Field("description")
    private String description;

    @Field("brand")
    private String brand;

    @Field("price")
    private Double price;

    @Field("discount_price")
    private Double discountPrice;

    @Field("stock_quantity")
    private Integer stockQuantity;

    @Field("in_stock")
    private Boolean inStock = true;

    @Field("category")
    private Category category;

    @Field("image_Urls")
    private List<String> imageUrls;

    @Field("tags")
    private List<String> tags;

    @Field("rating")
    private Double rating = 0.0;

    @Field("total_reviews")
    private Integer totalReviews = 0;

    @Field("total_sold")
    private Integer totalSold = 0;

    @Field("is_featured")
    private Boolean isFeatured = false;
    
    @Field("is_active")
	private Boolean isActive = true;

	@Field("created_by")
	private String createdBy;

	@Field("created_date")
	private Date createdDate;

	@Field("created_system_ip")
	private String createdSystemIp;

	@Field("is_deleted_value")
	private Boolean isDeletedValue = false;

	@Field("modified_by")
	private String modifiedBy;

	@Field("modified_date")
	private Date modifiedDate;

	@Field("modified_system_ip")
	private String modifiedSystemIp;
}
