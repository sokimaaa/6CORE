package com._6core.platform.orderinfra.adapter.driven.persistence.repository;

import com._6core.platform.orderinfra.adapter.driven.persistence.entity.OrderEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryAdapter extends ReactiveCrudRepository<OrderEntity, String> {
}
