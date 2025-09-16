package com.example.inventory.service;// ProductService.java
import com.example.inventory.entity.Product;
import com.example.inventory.entity.Supplier;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public Product createProduct(Product product) {
        Supplier supplier = supplierRepository.findById(product.getSupplier().getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        product.setSupplier(supplier);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setQuantity(productDetails.getQuantity());
        product.setPrice(productDetails.getPrice());

        Supplier supplier = supplierRepository.findById(productDetails.getSupplier().getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        product.setSupplier(supplier);

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsBySupplier(Long supplierId) {
        return productRepository.findBySupplierSupplierId(supplierId);
    }
}
