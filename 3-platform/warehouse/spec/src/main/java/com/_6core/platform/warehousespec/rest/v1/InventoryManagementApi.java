package com._6core.platform.warehousespec.rest.v1;

import com._6core.platform.warehousespec.rest.v1.dto.inventory.InventoryAllocateRequest;
import com._6core.platform.warehousespec.rest.v1.dto.inventory.InventoryAllocateResponse;
import com._6core.platform.warehousespec.rest.v1.dto.inventory.InventorySupplyRequest;
import com._6core.platform.warehousespec.rest.v1.dto.inventory.InventorySupplyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

/** Interface for inventory management in the Warehouse Service. */
@Tag(
    name = "Inventory management",
    description =
        "REST specification for allocating the inventory and for supplying to the existing inventory")
public interface InventoryManagementApi {

  /**
   * This API endpoint allows you to allocate a new space for the inventory in the warehouse.
   *
   * <p>POST /warehouses/{warehouseId}/inventories
   *
   * @param warehouseId - required
   * @param request - required
   * @return OK (status code 200)
   */
  @Operation(
      summary = "Allocate inventory",
      description = "Allocate a new space for the inventory in our warehouse")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = InventoryAllocateResponse.class))
            }),
        @ApiResponse(responseCode = "400", description = "Field validation error"),
        @ApiResponse(responseCode = "401", description = "Authentication error"),
        @ApiResponse(responseCode = "404", description = "inventory not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @PostMapping(
      value = "/warehouses/{warehouseId}/inventories",
      produces = "application/json",
      consumes = "application/json")
  default Mono<ResponseEntity<InventoryAllocateResponse>> inventoryAllocate(
      @Parameter(name = "warehouseId", required = true) @PathVariable("warehouseId")
          String warehouseId,
      InventoryAllocateRequest request) {
    InventoryAllocateResponse response = new InventoryAllocateResponse("-1WH", "-1Inv");
    return Mono.just(ResponseEntity.ok(response));
  }

  /**
   * Supplies a product to the inventory identified by the given inventory ID.
   *
   * <p>Patch /warehouses/{warehouseId}/inventories/{inventoryId}
   *
   * @param warehouseId - required
   * @param inventoryId - required
   * @param request - required
   * @return OK (status code 200)
   */
  @Operation(
      summary = "Supply products to inventory",
      description = "Supplying new products to inventory")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = InventorySupplyResponse.class))
            }),
        @ApiResponse(responseCode = "400", description = "Field validation error"),
        @ApiResponse(responseCode = "401", description = "Authentication error"),
        @ApiResponse(responseCode = "404", description = "Inventory not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @PatchMapping(
      value = "/warehouses/{warehouseId}/inventories/{inventoryId}",
      produces = "application/json",
      consumes = "application/json")
  default Mono<ResponseEntity<InventorySupplyResponse>> supplyInventory(
      @Parameter(name = "inventoryId", required = true) @PathVariable("inventoryId")
          String inventoryId,
      @Parameter(name = "warehouseId", required = true) @PathVariable("warehouseId")
          String warehouseId,
      InventorySupplyRequest request) {

    InventorySupplyResponse response = new InventorySupplyResponse("-1", "-1", 3);
    return Mono.just(ResponseEntity.ok(response));
  }
}
