package com.example.productservice.service;


import com.example.productservice.client.InventoryClient;
import com.example.productservice.dto.InventoryResponse;
import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final InventoryClient inventoryClient;


    public void createProduct(ProductRequest productRequest) {

       Product product = productMapper.mapToProduct(productRequest);

       log.info("Creating product {}", product);


        productRepository.save(product);
        log.info("Product {} created", product.getName());
    }


    public List<ProductResponse> getAllProducts() {
       List<Product> products =productRepository.findAll();


       List<String> skuCodes = products.stream()
               .map(Product::getSkuCode)
               .toList();

       String skuCodesJoined = String.join(",", skuCodes);


        log.info("SkuCodes  is : " + skuCodesJoined);

       List<InventoryResponse> inventoryResponses=inventoryClient.isInStock(skuCodesJoined);
        log.info("Inventory Responses is "+inventoryResponses);


         List<ProductResponse> productResponseList=     products.stream()
                      .map(productMapper::mapToResposne )
                      .toList();




       for (ProductResponse productResponse : productResponseList){
           for  (InventoryResponse inventoryResponse : inventoryResponses){
               if(productResponse.getSkuCode().equals(inventoryResponse.getSkuCode())){
                   productResponse.setInStock(inventoryResponse.isInStock());
               }
               break;
           }
       }


         return  productResponseList;

    }
}
