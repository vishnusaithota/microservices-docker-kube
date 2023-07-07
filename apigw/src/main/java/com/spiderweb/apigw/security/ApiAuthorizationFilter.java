package com.spiderweb.apigw.security;

import com.spiderweb.apigw.ApiGWApplication;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class ApiAuthorizationFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("Intercepted the request and checking for Key");

        Route attribute =  exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
       String application = attribute.getId();
        List<String> apiKey = exchange.getRequest().getHeaders().get("ApiKey");
        if (application == null || (apiKey == null || apiKey.isEmpty())) {
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Sorry You are not authorized");
       }
        System.out.println(apiKey.get(0));
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
