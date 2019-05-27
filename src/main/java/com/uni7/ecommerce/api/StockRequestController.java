package com.uni7.ecommerce.api;

import com.uni7.ecommerce.api.model.request.StockItemRequest;
import com.uni7.ecommerce.api.model.request.StockRequestItem;
import com.uni7.ecommerce.api.model.request.StockRequestRequest;
import com.uni7.ecommerce.api.model.response.StockItemResponse;
import com.uni7.ecommerce.product.ProductService;
import com.uni7.ecommerce.stock.StockItem;
import com.uni7.ecommerce.stock.StockRequest;
import com.uni7.ecommerce.stock.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/stockRequest")
public class StockRequestController {

    private final StockService stockService;
    private final ProductService productService;

    public StockRequestController(StockService stockService, ProductService productService) {
        this.stockService = stockService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody StockRequestRequest stockRequestRequest) {
        for (StockRequestItem stockRequestItem: stockRequestRequest.getItems()) {

            var stockItem = stockService.findByProductId(stockRequestItem.getProductId());

            if (!stockItem.isPresent()) {
                return ResponseEntity.badRequest().body("the store does not work with this type of product. id:" + stockRequestItem.getProductId());
            } else {
                stockItem.get().setAmount(stockItem.get().getAmount() + stockRequestItem.getAmount());
                stockService.save(stockItem.get());
            }
        }
        return ResponseEntity.ok("stock has been updated");
    }
}
