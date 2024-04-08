package com._6core.lib.java.domain.immutable.cart;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class ShoppingCartV01Impl implements ShoppingCartV01 {

    @Override
    public abstract String cartId();

    @Override
    public abstract List<String> productIds();

}
