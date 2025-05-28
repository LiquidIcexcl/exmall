package org.liquidice.exmall.product.controller;

import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.framework.result.Result;
import org.liquidice.exmall.framework.result.Results;
import org.liquidice.exmall.product.dto.req.ProductReqDTO;
import org.liquidice.exmall.product.dto.resp.ProductRespDTO;
import org.liquidice.exmall.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

/**
 * 商品控制器
 * 负责处理商品相关的请求
 */
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

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
}
