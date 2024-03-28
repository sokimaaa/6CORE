package com._6core.platform.orderspec.rest.v1;

import com._6core.platform.orderspec.rest.v1.dto.OrderDetailsResponse;
import com._6core.platform.orderspec.rest.v1.dto.OrderItemResponse;
import com._6core.platform.orderspec.rest.v1.dto.OrderShortResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigInteger;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

@Tag(name = "Order Receive Specification", description = "REST reactive specification for interaction with Order service")
public interface OrderGetApi {

    /**
     * Searching orders history by single user GET /orders
     * 
     * @return OK (status code 200)
     */
    @GetMapping(value = "/orders", produces = "application/json")
    @Operation(summary = "Get orders history", description = "Get all orders with all statuses by a single user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderShortResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid"),
            @ApiResponse(responseCode = "500", description = "Unexpected error") })
    default Flux<ResponseEntity<OrderShortResponse>> getOrdersHistory() {
        BigInteger mockPrice = BigInteger.valueOf(-1L);
        OrderShortResponse mockResponse = new OrderShortResponse("-1", "-1", mockPrice, "-1");
        return Flux.just(ResponseEntity.ok(mockResponse));
    }

    /**
     * Exploring exact order GET /orders/{orderId}/items
     * 
     * @param orderId - (required)
     * @return OK (status code 200)
     */
    @GetMapping(value = "/orders/{orderId}/items", produces = "application/json")
    @Operation(summary = "Get order details", description = "Get details of a specific order by order ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetailsResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request. Requested parameter doesn't exist"),
            @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Unexpected error") })
    default Flux<ResponseEntity<OrderDetailsResponse>> getOrder(
            @Parameter(description = "ID of the order", required = true) @PathVariable String orderId) {
        BigInteger mockPrice = BigInteger.valueOf(-1L);
        OrderItemResponse mockItem = new OrderItemResponse("-1", "-1", 0, mockPrice);
        OrderDetailsResponse mockResponse = new OrderDetailsResponse("-1", "-1", mockPrice, Set.of(mockItem), "-1");
        return Flux.just(ResponseEntity.ok(mockResponse));
    }
}
