package com._6core.platform.orderspec.rest.v1.dto;

import java.math.BigInteger;

public record OrderShortResponse(String orderId, String status, BigInteger total, String userId) {}
