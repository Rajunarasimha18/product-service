package com.raju.product.products;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.raju.product.commoniservices.IMasterCommonServices;
import com.raju.product.constants.MessageConstants;
import com.raju.product.exception.CommonException;

@Service
public class ProductServiceImpl implements IMasterCommonServices<ProductDTO,Long>{

	@Autowired
	private IProductDao iProductDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ProductDTO save(ProductDTO dto) {	
		return modelMapper.map(iProductDao.save(modelMapper.map(dto, Product.class)),ProductDTO.class);
	}

	@Override
	public ProductDTO findById(Long id) {
		return modelMapper.map(iProductDao.findById(id)
				.orElseThrow(() -> new CommonException(
						String.format(MessageConstants.NOT_FOUND, MessageConstants.PRODUCTMASTER, id),
						HttpStatus.NOT_FOUND)),
				ProductDTO.class);
	}

	@Override
	public ProductDTO update(Long id, ProductDTO dto) {
		Product vc = iProductDao.findById(id)
				.orElseThrow(() -> new CommonException(
						String.format(MessageConstants.NOT_FOUND, MessageConstants.PRODUCTMASTER, id),
						HttpStatus.NOT_FOUND));

		if (Objects.nonNull(dto.getProductName())) {
			vc.setProductName(dto.getProductName());
		}
		if (Objects.nonNull(dto.getDescription())) {
			vc.setDescription(dto.getDescription());
		}
		if (Objects.nonNull(dto.getBrand())) {
			vc.setBrand(dto.getBrand());
		}
		if (Objects.nonNull(dto.getPrice())) {
			vc.setPrice(dto.getPrice());
		}
		if (Objects.nonNull(dto.getDiscountPrice())) {
			vc.setDiscountPrice(dto.getDiscountPrice());
		}
		if (Objects.nonNull(dto.getStockQuantity())) {
			vc.setStockQuantity(dto.getStockQuantity());
		}
		if (Objects.nonNull(dto.getInStock())) {
			vc.setInStock(dto.getInStock());
		}
		if (Objects.nonNull(dto.getCategory())) {
			vc.setCategory(dto.getCategory());
		}
		if (Objects.nonNull(dto.getInStock())) {
			vc.setInStock(dto.getInStock());
		}
		if (Objects.nonNull(dto.getInStock())) {
			vc.setInStock(dto.getInStock());
		}
		if (Objects.nonNull(dto.getIsFeatured())) {
			vc.setIsFeatured(dto.getIsFeatured());
		}	
		
		
		return modelMapper.map(iProductDao.save(vc), ProductDTO.class);
	}

	@Override
	public ProductDTO deleteById(Long id) {
		Product vc = iProductDao.findById(id)
				.orElseThrow(() -> new CommonException(
						String.format(MessageConstants.NOT_FOUND, MessageConstants.PRODUCTMASTER, id),
						HttpStatus.NOT_FOUND));
		vc.setIsDeletedValue(true);		
		return modelMapper.map(iProductDao.save(vc), ProductDTO.class);
	}

	@Override
	public Page<ProductDTO> findAllByCompany(Long id, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Page<ProductDTO> findAll(String search, Pageable pageable) {
		Page<Product> page;
		if (search == null || search.trim().isEmpty()) {
			page = iProductDao.findAllNonDeleted(pageable);
		} else {
			page = iProductDao.findAllNonDeletedBySearch(search, pageable);
		}
		return page.map(p -> {
			ProductDTO dto = new ProductDTO();
			BeanUtils.copyProperties(p, dto);
			return dto;
		});
	}

	
	public List<ProductDTO> findAllListNonDeleted() {
		List<Product> categories = iProductDao.findAllListNonDeleted();
		return categories.stream().map(cat -> modelMapper.map(cat, ProductDTO.class))
				.collect(Collectors.toList());
	}
	
	public Page<ProductDTO> findAllNonDeletedActive(String search, Pageable pageable) {
		Page<Product> page;
		if (search == null || search.trim().isEmpty()) {
			page = iProductDao.findAllNonDeletedActive(pageable);
		} else {
			page = iProductDao.findAllNonDeletedActiveBySearch(search, pageable);
		}
		return page.map(p -> {
			ProductDTO dto = new ProductDTO();
			BeanUtils.copyProperties(p, dto);
			return dto;
		});
	}
	
	public List<ProductDTO> findAllListNonDeletedActive() {
		List<Product> categories = iProductDao.findAllListNonDeletedActive();
		return categories.stream().map(cat -> modelMapper.map(cat, ProductDTO.class))
				.collect(Collectors.toList());
	}
	
	public ProductDTO toggleProductActiveStatus(Long id) {
		Product vc = iProductDao.findById(id)
				.orElseThrow(() -> new CommonException(
						String.format(MessageConstants.NOT_FOUND, MessageConstants.PRODUCTMASTER, id),
						HttpStatus.NOT_FOUND));
		vc.setIsActive(!vc.getIsActive());
				
		return modelMapper.map(iProductDao.save(vc), ProductDTO.class);
	}

	public Object findByCategory(Category category, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findByBrand(String brand, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findByPriceRange(Double minPrice, Double maxPrice, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findFeaturedProducts(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findLatestProducts(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findTopSellingProducts(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findLowStockProducts(Integer stock) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object updateStock(Long id, Integer quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object reduceStock(Long id, Integer quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object updateRating(Long id, Double rating) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findByTags(List<String> tags, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object checkAvailability(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
