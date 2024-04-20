package com._6core.platform.shopping.cart.spec.rest.v1;

import com._6core.platform.shopping.cart.spec.rest.v1.request.PutShoppingCartItemRequest;
import com._6core.platform.shopping.cart.spec.rest.v1.response.PutShoppingCartItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Validated
@Tag(name = "manageCart", description = "the reactive manageCart API")
public interface ManageShoppingCartApi {

  /**
   * PUT /carts/{cartId} : Put a new item to cart
   *
   * @param cartId - (required)
   * @param putRequest - (required)
   * @param serverWebExchange - (optional)
   * @return OK (status code 201)
   */
  @Operation(
      operationId = "putCartItem",
      summary = "Put the item to cart",
      tags = {"putCartItem"},
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Created",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PutShoppingCartItemResponse.class))),
        @ApiResponse(responseCode = "400", description = "Incorrect put request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Cart or item not found")
      })
  @PutMapping(
      value = "/carts/{cartId}",
      produces = "application/json",
      consumes = "application/json")
  default Mono<ResponseEntity<PutShoppingCartItemResponse>> put(
      @Parameter(name = "cartId", required = true) @PathVariable("cartId") final String cartId,
      @Parameter(name = "cartPuttingRequest", required = true) @Valid @RequestBody
          PutShoppingCartItemRequest putRequest,
      @Parameter(hidden = true) final ServerWebExchange serverWebExchange) {
    final var putResponse = new PutShoppingCartItemResponse("-1", "-1", Boolean.FALSE);
    return Mono.just(ResponseEntity.ok(putResponse));
  }
}
