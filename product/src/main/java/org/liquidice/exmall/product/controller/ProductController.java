package org.liquidice.exmall.product.controller;

import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.framework.result.Result;
import org.liquidice.exmall.framework.result.Results;
import org.liquidice.exmall.product.dto.req.*;
import org.liquidice.exmall.product.dto.resp.ProductDetailRespDTO;
import org.liquidice.exmall.product.dto.resp.ProductIconRespDTO;
import org.liquidice.exmall.product.dto.resp.ProductInfoDetailRespDTO;
import org.liquidice.exmall.product.dto.resp.ProductRespDTO;
import org.liquidice.exmall.product.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品控制器
 * 负责处理商品相关的请求
 */
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductInfoService productInfoService;
    private final ProductInfoDetailService productInfoDetailService;
    private final ProductIconService productIconService;
    private final ProductDetailService productDetailService;

    /**
     * 根据商品ID查询商品信息
     * @param productId 商品ID
     * @return 商品信息
     */
    @GetMapping("/api/exmall/product/v1/product/{productId}")
    public Result<ProductRespDTO> getProductById(@PathVariable("productId") Long productId) {
        ProductRespDTO product = productService.getProductById(productId);
        return Results.success(product);
    }

    /**
     * 新增商品参数
     * @param requestParam 商品请求参数
     * @return 新增结果
     */
    @PostMapping("/api/exmall/product/v1/product")
    public Result<Boolean> addProduct(@RequestBody ProductReqDTO requestParam) {
        Boolean result = productService.addProduct(requestParam);
        return Results.success(result);
    }

    /**
     * 根据商品ID更新商品参数
     * @param requestParam 商品请求参数
     * @return 更新结果
     */
    @PutMapping("/api/exmall/product/v1/product")
    public Result<Boolean> updateProduct(@RequestBody ProductReqDTO requestParam) {
        Boolean result = productService.updateProduct(requestParam);
        return Results.success(result);
    }

    /**
     * 根据商品ID删除商品参数
     *
     * @param productId 商品ID
     * @return 删除结果
     */
    @DeleteMapping("/api/exmall/product/v1/product")
    public Result<Boolean> deleteProduct(@RequestParam("productId") Long productId) {
        Boolean result = productService.deleteProduct(productId);
        return Results.success(result);
    }

    /*
      以下是商品相关(SKU)的接口
     */


    /**
     * 根据商品ID查询商品信息（属性）列表
     */
    @GetMapping("/api/exmall/product/v1/product/info/{productId}")
    public Result<List<ProductInfoReqDTO>> getProductInfoById(@PathVariable("productId") Long productId) {
        return Results.success(productInfoService.getProductInfoById(productId));
    }
    /**
     * 创建商品信息（属性）
     */
    @PostMapping("/api/exmall/product/v1/product/info")
    public Result<Void> createProductInfo(@RequestBody ProductInfoReqDTO requestParam) {
        productInfoService.createProductInfo(requestParam);
        return Results.success();
    }
    /**
     * 更新商品信息（属性）
     */
    @PutMapping("/api/exmall/product/v1/product/info")
    public Result<Void> updateProductInfo(@RequestBody ProductInfoReqDTO requestParam) {
        productInfoService.updateProductInfo(requestParam);
        return Results.success();
    }
    /**
     * 删除商品信息（属性）
     */
    @DeleteMapping("/api/exmall/product/v1/product/info")
    public Result<Void> deleteProductInfo(@RequestParam("productInfoId") Long productInfoId) {
        productInfoService.deleteProductInfo(productInfoId);
        return Results.success();
    }



    /**
     * 根据商品信息ID查询商品信息细节（属性细节）列表
     */
    @GetMapping("/api/exmall/product/v1/product/info/detail/{productInfoId}")
    public Result<List<ProductInfoDetailRespDTO>> getProductInfoDetailById(@PathVariable("productInfoId") Long productInfoId) {
        return Results.success(productInfoDetailService.getProductInfoDetailByProductInfoId(productInfoId));
    }
    /**
     * 创建商品信息细节（属性细节）
     */
    @PostMapping("/api/exmall/product/v1/product/info/detail")
    public Result<Void> createProductInfoDetail(@RequestBody ProductInfoDetailReqDTO requestParam) {
        productInfoDetailService.createProductInfoDetail(requestParam);
        return Results.success();
    }
    /**
     * 更新商品信息细节（属性细节）
     */
    @PutMapping("/api/exmall/product/v1/product/info/detail")
    public Result<Void> updateProductInfoDetail(@RequestBody ProductInfoDetailReqDTO requestParam) {
        productInfoDetailService.updateProductInfoDetail(requestParam);
        return Results.success();
    }
    /**
     * 删除商品信息细节（属性细节）
     */
    @DeleteMapping("/api/exmall/product/v1/product/info/detail")
    public Result<Void> deleteProductInfoDetail(@RequestParam("productInfoDetailId") Long productInfoDetailId) {
        productInfoDetailService.deleteProductInfoDetail(productInfoDetailId);
        return Results.success();
    }



    /**
     * 根据商品ID查询商品图标
     */
    @GetMapping("/api/exmall/product/v1/product/icon/{productId}")
    public Result<List<ProductIconRespDTO>> getProductIconById(@PathVariable("productId") Long productId) {
        return Results.success(productIconService.getProductIconById(productId));
    }
    /**
     * 创建商品图标
     */
    @PostMapping("/api/exmall/product/v1/product/icon")
    public Result<Void> createProductIcon(@RequestBody ProductIconReqDTO requestParam) {
        productIconService.createProductIcon(requestParam);
        return Results.success();
    }
    /**
     * 更新商品图标
     */
    @PutMapping("/api/exmall/product/v1/product/icon")
    public Result<Void> updateProductIcon(@RequestBody ProductIconReqDTO requestParam) {
        productIconService.updateProductIcon(requestParam);
        return Results.success();
    }
    /**
     * 删除商品图标
     */
    @DeleteMapping("/api/exmall/product/v1/product/icon")
    public Result<Void> deleteProductIcon(@RequestParam("productIconId") Long productIconId) {
        productIconService.deleteProductIcon(productIconId);
        return Results.success();
    }




    /**
     * 根据商品ID查询商品详情页图
     */
    @GetMapping("/api/exmall/product/v1/product/detail/{productId}")
    public Result<List<ProductDetailRespDTO>> getProductDetailById(@PathVariable("productId") Long productId) {
        return Results.success(productDetailService.getProductDetailById(productId));
    }
    /**
     * 创建商品详情页图
     */
    @PostMapping("/api/exmall/product/v1/product/detail")
    public Result<Void> createProductDetail(@RequestBody ProductDetailReqDTO requestParam) {
        productDetailService.createProductDetail(requestParam);
        return Results.success();
    }
    /**
     * 更新商品详情页图
     */
    @PutMapping("/api/exmall/product/v1/product/detail")
    public Result<Void> updateProductDetail(@RequestBody ProductDetailReqDTO requestParam) {
        productDetailService.updateProductDetail(requestParam);
        return Results.success();
    }
    /**
     * 删除商品详情页图
     */
    @DeleteMapping("/api/exmall/product/v1/product/detail")
    public Result<Void> deleteProductDetail(@RequestParam("productDetailId") Long productDetailId) {
        productDetailService.deleteProductDetail(productDetailId);
        return Results.success();
    }

}
