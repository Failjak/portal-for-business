package com.portal.portalforbusiness.services;

import com.portal.portalforbusiness.dao.product.ProductDao;
import com.portal.portalforbusiness.dao.product.ProductDaoImpl;
import com.portal.portalforbusiness.dao.user.UserProductDaoImpl;
import com.portal.portalforbusiness.dto.ProductDto;
import com.portal.portalforbusiness.dto.SellerDto;
import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.dto.UserProductDto;
import com.portal.portalforbusiness.mapper.product.ProductDtoMapper;
import com.portal.portalforbusiness.mapper.product.ProductMapper;
import com.portal.portalforbusiness.mapper.seller.SellerDtoMapper;
import com.portal.portalforbusiness.mapper.user.UserProductDtoMapper;
import com.portal.portalforbusiness.models.Product;
import com.portal.portalforbusiness.models.Seller;


import java.util.*;

public class ProductService implements Service {
    private static final ProductService INSTANCE = new ProductService();

    private final ProductDao productDao = new ProductDaoImpl();
    private final UserProductDaoImpl userProductDao = new UserProductDaoImpl();
    private final ProductMapper productMapper = ProductMapper.getInstance();
    private final UserProductDtoMapper userProductDtoMapper = UserProductDtoMapper.getInstance();
    private final ProductDtoMapper productDtoMapper = ProductDtoMapper.getInstance();
    private final SellerDtoMapper sellerDtoMapper = SellerDtoMapper.getInstance();

    public void saveProduct(ProductDto productDto) {
        Product product = productDtoMapper.mapFrom(productDto);
        Seller seller = sellerDtoMapper.mapFrom(productDto.getSeller());
        product.setSeller(seller);
        productDao.save(product);
    }

    public Optional<ProductDto> delete(ProductDto productDto, SellerDto sellerDto) {
        return productDao.delete(productDto.getId(), sellerDtoMapper.mapFrom(sellerDto)).
                map(productMapper::mapFrom);
    }


    public Optional<List<ProductDto>> getProductsDtoBySeller(SellerDto sellerDto) {
        Optional<List<Product>> productsOptional = productDao.findProductsBySeller(sellerDtoMapper.mapFrom(sellerDto));
        if (productsOptional.isPresent()) {
            List<ProductDto> products = new ArrayList<>();
            for (Product product : productsOptional.get()) {
                products.add(productMapper.mapFrom(product));
            }
            return Optional.of(products);
        }
        return Optional.empty();
    }

    public Optional<List<Product>> getProductsBySeller(Seller seller) {
        return productDao.findProductsBySeller(seller);
    }

//    public Optional<List<ProductDto>> getAllProducts() {
//        return productDao.getAllProducts().map(products -> products.stream().map(productMapper::mapFrom).collect(Collectors.toList()));
//    }

    public Optional<List<ProductDto>> getAllProductsWithJson() {
        Map<Integer, Double> productsAvgMark = productDao.getProductsAvgMark();

        return productDao.getAllProducts().map(products ->
                products.stream().map(product -> productMapper.mapFrom(product, ProductDto.getJson(product), productsAvgMark.get(product.getId()))).toList()
        );
    }

    public Optional<List<ProductDto>> findProductsByName(String name) {
        Map<Integer, Double> productsAvgMark = productDao.getProductsAvgMark();

        return productDao.findAllProductsByName(name).map(products ->
                products.stream().map(product -> productMapper.mapFrom(product, ProductDto.getJson(product), productsAvgMark.get(product.getId()))).toList()
        );
    }

    public Optional<ProductDto> findProductsById(Integer id) {
        return productDao.findProductById(id).map(productMapper::mapFrom);
    }

    public void buyProduct(UserDto userDto, Integer productId) throws Exception {
        UserProductDto userProductDto = UserProductDto.builder().
                userId(userDto.getId()).
                productId(productId).
                build();

        Optional<ProductDto> productOptional = findProductsById(productId);
        if (productOptional.isEmpty()) {
            return;
        }
        ProductDto product = productOptional.get();

        Float productPrice;
        if (product.getCurrency() == Currency.getInstance("BYN"))
            productPrice = product.getPrice();
        else
            productPrice = product.getPrice() * 2.5F;

        if (productPrice > userDto.getCard().getBalance()) {
            throw new Exception("Недостаточный баланс");
        }

        userDto.getCard().setBalance(userDto.getCard().getBalance() - productPrice);
        userProductDao.save(userProductDtoMapper.mapFrom(userProductDto), userDto);
    }

    public List<Integer> getBoughtProductIdsByUser(UserDto userDto) {
        Optional<List<Integer>> productsOptional = userProductDao.getBoughtProductIdsByUser(userDto.getId());
        return productsOptional.orElseGet(Collections::emptyList);
    }

    public List<ProductDto> getBoughtProductsByUser(UserDto userDto) {
        Optional<List<Product>> boughtProducts = userProductDao.getBoughtProductsByUser(userDto.getId());
        if (boughtProducts.isEmpty())
            return null;

        return boughtProducts.get().stream().map(productMapper::mapFrom).toList();
    }
    public static ProductService getInstance() {
        return INSTANCE;
    }
}
