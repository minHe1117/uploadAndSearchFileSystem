package com.example.uploadfilesystem.Controller;

import com.example.uploadfilesystem.Dao.SolrProductRepository;
import com.example.uploadfilesystem.Entity.Product;
import com.example.uploadfilesystem.Service.SolrSearchService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    final SolrSearchService solrProductService;

    public ProductController(SolrSearchService solrProductService) {
        this.solrProductService = solrProductService;
    }

    @PostMapping()
    public String createProduct(@RequestBody Product product) {
        String description = "Product Created";
        Product saved = solrProductService.save(product);
        return description;
    }

    @GetMapping("/{productId}")
    public Product readProduct(@PathVariable Long productId) {
        return solrProductService.findBySkuId(productId);
    }

    @PutMapping()
    public String updateProduct(@RequestBody Product product) {
        String description = "Order Updated";
        solrProductService.save(product);
        return description;
    }

    @DeleteMapping("/{productId}")
    public String deleteOrder(@PathVariable Long productId) {
        String description = "Order Deleted";
        solrProductService.delete(solrProductService.findBySkuId(productId));
        return description;
    }

    @GetMapping("/desc/{prodDesc}/{page}/{pageSize}")
    public List<Product> findOrder(@PathVariable String prodDesc, @PathVariable int page, @PathVariable int pageSize) {
        return solrProductService.findByProdDescription(prodDesc, PageRequest.of(page, pageSize)).getContent();
    }

    @GetMapping("/search/{searchTerm}/{page}/{pageSize}")
    public List<Product> findOrderBySearchTerm(@PathVariable String searchTerm, @PathVariable int page,@PathVariable int pageSize) {
        return solrProductService.findByCustomQuery(searchTerm, PageRequest.of(page, pageSize)).getContent();
    }
}
