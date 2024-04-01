package com._6core.platform.deliveryspec.rest.v1;

import com._6core.platform.deliveryspec.rest.v1.dto.DeliveryStatusResponse;
import com._6core.platform.deliveryspec.rest.v1.dto.UpdatedDeliveryStatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Tag(
    name = "Delivery Status Management Specification",
    description = "REST reactive specification for menegment delivery status")
public interface DeliveryStatusManagementApi {

  /**
   * Exploring delivery status GET /delivery/{deliveryId}
   *
   * @param deliveryIdId (required)
   * @return OK (status code 200)
   */
  @GetMapping(value = "/deliveries/{deliveryId}", produces = "application/json")
  @Operation(summary = "Get delivery status", description = "Get current delivery status")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DeliveryStatusResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description = "Authorization information is missing or invalid"),
        @ApiResponse(responseCode = "404", description = "Delivery not found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error")
      })
  default Mono<ResponseEntity<DeliveryStatusResponse>> getDeliveryStatus(
      @Parameter(description = "ID of the delivery", required = true) @PathVariable
          String deliveryId) {
    return Mono.just(ResponseEntity.ok(new DeliveryStatusResponse("-1", "-1")));
  }

  /**
   * Update delivery status to 'Delivering'. The delivery manager gets the order and goes to the
   * customer. PATCH /deliveries/{deliveryId}/delivering
   *
   * @param deliveryId (required)
   * @return OK (status code 200)
   */
  @PatchMapping(value = "/deliveries/{deliveryId}/delivering", produces = "application/json")
  @Operation(
      summary = "Update delivery status to 'delivering'",
      description = "Update delivery status to 'delivering'")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = UpdatedDeliveryStatusResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description = "Authorization information is missing or invalid"),
        @ApiResponse(responseCode = "404", description = "Delivery not found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error")
      })
  default Mono<ResponseEntity<UpdatedDeliveryStatusResponse>> updateDeliveryStatusDelivering(
      @Parameter(description = "ID of the delivery", required = true) @PathVariable
          String deliveryId) {
    return Mono.just(ResponseEntity.ok(new UpdatedDeliveryStatusResponse("-1", false)));
  }

  /**
   * Update delivery status to 'Delivered' PATCH /deliveries/{deliveryId}/delivered
   *
   * @param deliveryId (required)
   * @return OK (status code 200)
   */
  @PatchMapping(value = "/deliveries/{deliveryId}/delivered", produces = "application/json")
  @Operation(
      summary = "Update delivery status to 'delivered'",
      description = "Update delivery status to 'delivered'")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = UpdatedDeliveryStatusResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description = "Authorization information is missing or invalid"),
        @ApiResponse(responseCode = "404", description = "Delivery not found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error")
      })
  default Mono<ResponseEntity<UpdatedDeliveryStatusResponse>> updateDeliveryStatusDelivered(
      @Parameter(description = "ID of the delivery", required = true) @PathVariable
          String deliveryId) {
    return Mono.just(ResponseEntity.ok(new UpdatedDeliveryStatusResponse("-1", false)));
  }
}
