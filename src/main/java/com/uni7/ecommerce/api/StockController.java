package com.uni7.ecommerce.api;

import com.uni7.ecommerce.api.model.request.StockItemRequest;
import com.uni7.ecommerce.api.model.response.StockItemResponse;
import com.uni7.ecommerce.product.ProductService;
import com.uni7.ecommerce.stock.StockItem;
import com.uni7.ecommerce.stock.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/stockItems")
public class StockController {

    private final StockService stockService;
    private final ProductService productService;

    public StockController(StockService stockService, ProductService productService) {
        this.stockService = stockService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody StockItemRequest stockItemRequest) {
        var product = productService.findById(stockItemRequest.getProductId());
        if(!product.isPresent()) {
            return ResponseEntity.badRequest().body("inválid productId: " + stockItemRequest.getProductId());
        }

        var stockItem = new StockItem(product.get(), stockItemRequest.getAmount(), stockItemRequest.getPrice());

        var inserted = stockService.save(stockItem);
        return ResponseEntity.ok(new StockItemResponse(inserted));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody StockItemRequest stockItemRequest) {
        var product = productService.findById(stockItemRequest.getProductId());
        if(!product.isPresent()) {
            return ResponseEntity.badRequest().body("inválid productId: " + stockItemRequest.getProductId());
        }

        var stockItem = stockService.findByProductId(product.get().getId()).get();

        stockItem.setAmount(stockItemRequest.getAmount());
        stockItem.setPrice(stockItemRequest.getPrice());
        stockItem.setProduct(product.get());

        var updated = stockService.save(stockItem);
        return ResponseEntity.ok(new StockItemResponse(updated));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        var stockItems = stockService.findAll().stream()
                .map(StockItemResponse::new);

        return ResponseEntity.ok(stockItems);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        var stockItem = stockService.findById(id);
        if(stockItem.isPresent()) {
            return ResponseEntity.ok(new StockItemResponse(stockItem.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
        stockService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
