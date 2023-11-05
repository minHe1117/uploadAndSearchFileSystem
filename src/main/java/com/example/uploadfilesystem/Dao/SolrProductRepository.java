package com.example.uploadfilesystem.Dao;

import com.example.uploadfilesystem.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface SolrProductRepository extends SolrCrudRepository<Product, Long> {
    Product findBySkuId(Long skuId);

    @Query("prod_description:*?0*")
    public Page<Product> findByProdDescription(String searchTerm, Pageable pageable);

    @Query("prod_description:*?0* OR size:*?0* OR color:*?0*")
    public Page<Product> findByCustomQuery(String searchTerm, Pageable pageable);

}
