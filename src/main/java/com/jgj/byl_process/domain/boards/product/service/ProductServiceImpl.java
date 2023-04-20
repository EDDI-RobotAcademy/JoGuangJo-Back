package com.jgj.byl_process.domain.boards.product.service;

import com.jgj.byl_process.domain.boards.product.controller.dto.*;
import com.jgj.byl_process.domain.boards.product.entity.ImageResource;
import com.jgj.byl_process.domain.boards.product.entity.Product;
import com.jgj.byl_process.domain.boards.product.repository.ImageResourceRepository;
import com.jgj.byl_process.domain.boards.product.repository.ProductRepository;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardReadResponse;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;
    final private ImageResourceRepository imageResourceRepository;

    @Transactional
    @Override
    public void register(List<MultipartFile> imageFileList, RequestProductInfo productRequest) {

        log.info("글자 출력: " + productRequest);

        List<ImageResource> imageResourceList = new ArrayList<>();

        final String fixedStringPath = "/Users/majin-u/Desktop/JoGuangJo/frontend/JoGuangJo-Front/src/assets/productImgs";

        Product product = new Product();

        product.setProductName(productRequest.getProductName());
        product.setWriter(productRequest.getWriter());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());

        String content = product.getContent();

        content = content.replaceAll("<img[^>]*>", ""); // <img> 태그 제거
        content = content.replaceAll("<p>", ""); // <p> 태그 시작 부분 제거
        content = content.replaceAll("</p>", ""); // <p> 태그 종료 부분 제거

        // base64로 디코딩 하지 않고 단순히 <img> <p> 태그 replace 후 저장
        product.setContent(content);

        if (imageFileList != null && !imageFileList.isEmpty()) {
            for (MultipartFile file : imageFileList) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String filepath = fixedStringPath + "/" + filename;

                try {
                    file.transferTo(new File(filepath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                ImageResource imageResource = new ImageResource();
                imageResource.setProduct(product);
                imageResource.setFilePath(filepath);

                imageResourceList.add(imageResource);
            }
        }

        productRepository.save(product);

        if (!imageResourceList.isEmpty()) {
            imageResourceRepository.saveAll(imageResourceList);
        }
    }


    @Override
    public List<ProductListResponse> list() {
        List<Product> productList = productRepository.findAll();
        List<ProductListResponse> productResponseList = new ArrayList<>();

        for (Product product : productList) {
            List<String> imgPaths = imageResourceRepository.findImagePathByProductId(product.getProductId());

            // realThumbnail을 for문 안에서 초기화합니다.
            List<String> realThumbnail = new ArrayList<>();

            for (String thumbnail : imgPaths) {
                String fileName = thumbnail.substring(thumbnail.lastIndexOf("/") + 1);
                realThumbnail.add(fileName);

                System.out.println(fileName);
            }

            if (imgPaths == null || imgPaths.isEmpty()) {
                System.out.println("이미지 경로를 찾을 수 없습니다: " + product.getProductId());
            } else {
                System.out.println("product ID 의 이미지 경로 " + product.getProductId() + ": " + imgPaths);
            }

            productResponseList.add(new ProductListResponse(
                    product.getProductId(), product.getProductName(),
                    product.getWriter(), product.getPrice(), realThumbnail
            ));
        }

        return productResponseList;
    }




    @Override
    public ProductReadResponse read(Long productId) {
        Optional<Product> maybeProduct = productRepository.findById(productId);
        List<String> maybeProductImgList = imageResourceRepository.findImagePathByProductId(productId);


        Product product = maybeProduct.get();
        List<String> imgPaths = new ArrayList<>();

        for (String imgPath : maybeProductImgList) {
            String fileName = imgPath.substring(imgPath.lastIndexOf("/") + 1);
            imgPaths.add(fileName);
        }

        return new ProductReadResponse(
                product.getProductId(),
                product.getProductName(),
                product.getWriter(),
                product.getContent(),
                imgPaths,
                product.getPrice(),
                product.getRegDate()
        );
    }

    @Override
    public void remove(Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            log.error("Error deleting product with ID " + productId, e);
            throw new RuntimeException("Failed to delete product with ID " + productId, e);
        }
    }


    @Override
    public Product modify(Long productId, ProductRequest productRequest) {
        Optional<Product> maybeProduct = productRepository.findById(productId);

        if (maybeProduct.isEmpty()) {
            System.out.println("Product 정보를 찾지 못했습니다: " + productId);
            return null;
        }

        Product product = maybeProduct.get();
        product.setProductName(productRequest.getProductName());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);

        return product;
    }

//    @Override
//    public List<ImageResourceResponse> findProductImage(Long productId) {
//        List<ImageResource> imageResourceList = imageResourceRepository.findImagePathByProductId(productId);
//        List<ImageResourceResponse> imageResourceResponseList = new ArrayList<>();
//
//        for (ImageResource imageResource : imageResourceList) {
//            System.out.println("imageResource path: " + imageResource.getImageResourcePath());
//
//            imageResourceResponseList.add(new ImageResourceResponse(
//                    imageResource.getImageResourcePath()));
//        }
//
//        return imageResourceResponseList;
//    }

    @Override
    public List<AllProductResponse> all() {
        List<Product> productList = productRepository.findAll();
        List<AllProductResponse> allProductList = new ArrayList<>();

        return allProductList;
    }
}