package com.example.uploadfilesystem.Service;

import com.example.uploadfilesystem.Dao.SolrProductRepository;
import com.example.uploadfilesystem.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SolarSearchServiceImpl implements SolrSearchService{

    SolrProductRepository solrProductRepository;

    public SolarSearchServiceImpl(SolrProductRepository solrProductRepository) {
        this.solrProductRepository = solrProductRepository;
    }

    @Override
    public Product save(Product product) {
        return this.solrProductRepository.save(product);
    }

    @Override
    public Iterable<Product> saveAll(List<Product> products) {
        return this.solrProductRepository.saveAll(products);
    }

    @Override
    public void delete(Product product) {
        this.solrProductRepository.delete(product);
    }

    @Override
    public Product findBySkuId(Long skuId) {
        return solrProductRepository.findBySkuId(skuId);
    }

    @Override
    public Page<Product> findByProdDescription(String searchTerm, Pageable pageable) {
        return solrProductRepository.findByProdDescription(searchTerm,pageable);
    }

    @Override
    public Page<Product> findByCustomQuery(String searchTerm, Pageable pageable) {
        return solrProductRepository.findByCustomQuery(searchTerm,pageable);
    }
}
