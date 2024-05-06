package com._6core.platform.warehouse.domain.dto.product;

import java.math.BigInteger;

public record ProductResponse(
    String name, String description, String image, BigInteger price, String category) {}
