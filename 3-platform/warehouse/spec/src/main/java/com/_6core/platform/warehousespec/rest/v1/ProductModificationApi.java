package com._6core.platform.warehousespec.rest.v1;

import com._6core.platform.warehousespec.rest.v1.dto.product.ProductDescriptionRequest;
import com._6core.platform.warehousespec.rest.v1.dto.product.ProductDescriptionResponse;
import com._6core.platform.warehousespec.rest.v1.dto.product.ProductNameRequest;
import com._6core.platform.warehousespec.rest.v1.dto.product.ProductNameResponse;
import com._6core.platform.warehousespec.rest.v1.dto.product.ProductPriceRequest;
import com._6core.platform.warehousespec.rest.v1.dto.product.ProductPriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

/** Interface for modifying product information in the Warehouse Service. */
@Tag(
    name = "Product Modification",
    description = "REST specification for modifying product information")
public interface ProductModificationApi {
  /** Update product's description for a given product identified by its ID. */
  @Operation(
      summary = "Update product description",
      description = "Modify the description of a product")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ProductDescriptionResponse.class))
            }),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @PatchMapping("/products/{productId}/description")
  default Mono<ResponseEntity<ProductDescriptionResponse>> updateProductDescription(
      @Parameter(name = "productId", required = true) @PathVariable("productId") String productId,
      ProductDescriptionRequest request) {

    ProductDescriptionResponse response = new ProductDescriptionResponse("-1", "updated");
    return Mono.just(ResponseEntity.ok(response));
  }

  /** Update product's name for a given product identified by its ID. */
  @Operation(
      summary = "Update product description",
      description = "Modify the description of a product")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ProductNameResponse.class))
            }),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @PatchMapping("/products/{productId}/name")
  default Mono<ResponseEntity<ProductNameResponse>> updateProductName(
      @Parameter(name = "productId", required = true) @PathVariable("productId") String productId,
      ProductNameRequest request) {
    ProductNameResponse response = new ProductNameResponse("-1", "updated");
    return Mono.just(ResponseEntity.ok(response));
  }

  /** Update product's price for a given product identified by its ID */
  @Operation(
      summary = "Update product description",
      description = "Modify the description of a product")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ProductPriceResponse.class))
            }),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @PatchMapping("/products/{productId}/price")
  default Mono<ResponseEntity<ProductPriceResponse>> updateProductPrice(
      @Parameter(name = "productId", required = true) @PathVariable("productId") String productId,
      ProductPriceRequest request) {
    ProductPriceResponse response = new ProductPriceResponse("-1", new BigDecimal("123.45"));
    return Mono.just(ResponseEntity.ok(response));
  }
}
