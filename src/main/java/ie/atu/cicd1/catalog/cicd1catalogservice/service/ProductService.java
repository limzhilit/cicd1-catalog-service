package ie.atu.cicd1.catalog.cicd1catalogservice.service;

import ie.atu.cicd1.catalog.cicd1catalogservice.dto.CreateProductRequest;
import ie.atu.cicd1.catalog.cicd1catalogservice.model.Category;
import ie.atu.cicd1.catalog.cicd1catalogservice.model.Product;
import ie.atu.cicd1.catalog.cicd1catalogservice.repository.CategoryRepository;
import ie.atu.cicd1.catalog.cicd1catalogservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
  private final ProductRepository repository;
  private final CategoryRepository categoryRepository;

  public List<Product> getAll() {
    return repository.findAll();
  }

  public Product createProduct(CreateProductRequest request) {
    if (request.categoryId() == null) {
      throw new ResponseStatusException(
              HttpStatus.BAD_REQUEST, "categoryId is required");
    }

    Category category = categoryRepository
            .findById(request.categoryId())
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Category does not exist"));

    Product product = new Product();
    product.setName(request.name());
    product.setPrice(request.price());
    product.setCategory(category);

    return repository.save(product);
  }

  public Product getById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Product not found"
        ));
  }

  public List<Product> getByMinimumPrice(BigDecimal minPrice) {
    return repository.findByMinimumPrice(minPrice);
  }

  public List<Product> getByMaximumPrice(BigDecimal maxPrice) {
    return repository.findByMaximumPrice(maxPrice);
  }

  public List<Product> getByCategoryName(String categoryName) {
    return repository.findByCategoryName(categoryName);
  }
}