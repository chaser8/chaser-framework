package top.chaser.framework.common.web.flux.decorator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import top.chaser.framework.common.web.flux.util.WebFluxUtil;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class DefaultServerHttpRequestDecorator extends ServerHttpRequestDecorator {
    private Flux<byte[]> fluxBody;


    public DefaultServerHttpRequestDecorator(ServerHttpRequest delegate) {
        super(delegate);
        final String path = delegate.getURI().getPath();
        final String query = delegate.getURI().getQuery();
        final String method = Optional.ofNullable(delegate.getMethod()).orElse(HttpMethod.GET).name();
        final String headers = delegate.getHeaders().entrySet()
                .stream()
                .map(entry -> "            " + entry.getKey() + ": [" + String.join(";", entry.getValue()) + "]")
                .collect(Collectors.joining("\n"));
        final MediaType contentType = delegate.getHeaders().getContentType();
        if (log.isDebugEnabled()) {
            log.debug("\n" +
                    "HttpMethod : {}\n" +
                    "Uri        : {}\n" +
                    "Headers    : \n" +
                    "{}", method, path + (StringUtils.isEmpty(query) ? "" : "?" + query), headers);
        }
        Flux<DataBuffer> flux = super.getBody();
        fluxBody = flux.map(dataBuffer ->WebFluxUtil.resolveRequest(dataBuffer));
    }

    @Override
    public Flux<DataBuffer> getBody() {
        return fluxBody.map(bytes ->WebFluxUtil.toDataBuffer(bytes));
    }
}
