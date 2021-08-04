package top.chaser.framework.common.web.flux.decorator;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;

public class DefaultServerWebExchangeDecorator extends ServerWebExchangeDecorator {

    private DefaultServerHttpRequestDecorator requestDecorator;

    private DefaultServerHttpResponseDecorator responseDecorator;

    public DefaultServerWebExchangeDecorator(ServerWebExchange delegate) {
        super(delegate);
        requestDecorator = new DefaultServerHttpRequestDecorator(delegate.getRequest());
        responseDecorator = new DefaultServerHttpResponseDecorator(delegate.getResponse());
    }

    @Override
    public ServerHttpRequest getRequest() {
        return requestDecorator;
    }

    @Override
    public ServerHttpResponse getResponse() {
        return responseDecorator;
    }
}
