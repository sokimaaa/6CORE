package com._6core.platform.shopping.cart.infra.config;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.platform.shopping.cart.domain.validator.ValidationChainBuilder;
import com._6core.platform.shopping.cart.domain.validator.Validator;
import com._6core.platform.shopping.cart.domain.validator.impl.CartNotEmpty;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {
  @Bean
  Validator<ShoppingCartV01> cartNotEmpty() {
    return new CartNotEmpty();
  }

  @Bean
  Validator<ShoppingCartV01> createChain(List<Validator<ShoppingCartV01>> validators) {
    return new ValidationChainBuilder().build(validators);
  }
}
