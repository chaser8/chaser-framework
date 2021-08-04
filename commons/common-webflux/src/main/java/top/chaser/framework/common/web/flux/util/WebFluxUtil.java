package top.chaser.framework.common.web.flux.util;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Flux;
import top.chaser.framework.common.base.exception.SystemException;
import top.chaser.framework.common.web.exception.WebErrorType;

import java.io.IOException;
import java.io.InputStream;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@Slf4j
public class WebFluxUtil {
    public static <T> T body(ServerHttpRequest request, Class<T> clazz) {
        Flux<DataBuffer> body = request.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(dataBuffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
            DataBufferUtils.release(dataBuffer);
            bodyRef.set(charBuffer.toString());
        });//读取request body到缓存
        String bodyStr = bodyRef.get();//获取request body
        return JSONUtil.toBean(bodyStr, clazz);
    }

    public static <T> Function<ServerHttpRequest, T> body(Class<T> clazz) {
        return (request) -> {
            Flux<DataBuffer> body = request.getBody();
            AtomicReference<String> result = new AtomicReference<>();
            body.subscribe(dataBuffer -> {
                CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
                DataBufferUtils.release(dataBuffer);
                result.set(charBuffer.toString());
            });
            return JSONUtil.toBean(result.get(), clazz);
        };
    }

    public static DataBuffer toDataBuffer(byte[] bytes) {
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
        return nettyDataBufferFactory.wrap(bytes);
    }

    public static final List<MediaType> MEDIA_TYPES = Lists.newArrayList(MediaType.TEXT_XML,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_JSON_UTF8,
            MediaType.TEXT_PLAIN,
            MediaType.TEXT_XML);

    public static byte[] resolveBody(String inOrOut, DataBuffer buffer) {
        try {
            InputStream dataBuffer = buffer.asInputStream();
            byte[] bytes = IOUtils.toByteArray(dataBuffer);
            if (log.isDebugEnabled()) {
                log.debug("\n{}Payload    : {}", inOrOut, new String(bytes));
            }
            DataBufferUtils.release(buffer);
            return bytes;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new SystemException(WebErrorType.REQUEST_BODY_READ_ERROR, e);
        }
    }

    public static byte[] resolveRequest(DataBuffer buffer) {
        return resolveBody(">>>>>>>>>>", buffer);
    }

    public static byte[] resolveResponse(DataBuffer buffer) {
        return resolveBody("<<<<<<<<<<", buffer);
    }
}
