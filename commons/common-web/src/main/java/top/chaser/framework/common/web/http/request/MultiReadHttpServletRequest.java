package top.chaser.framework.common.web.http.request;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import top.chaser.framework.common.base.util.JSONUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.TreeMap;

/**
 * @description:
 * @create: 2019-08-26 15:05
 **/
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
    private byte [] requestData;

    @SneakyThrows
    public MultiReadHttpServletRequest(HttpServletRequest request){
        super(request);
        int length = this.getContentLength();
        if (length > 0) {
            requestData = new byte[length];
            IOUtils.read(request.getInputStream(), requestData, 0, length);
        }
    }

    public String getBody(){
        String body = null;
        if(requestData!=null){
            body = new String(requestData);
        }
        return body;
    }

    public String getSortBody(){
        return JSONUtil.toJSONString(this.getBody(TreeMap.class));
    }

    public <T> T getBody(Class<T> type){
        return JSONUtil.parseObject(this.getBody(),type,false);
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (this.getContentLength() < 1) {
            return null;
        }
        return new ResettableServletInputStream(new ByteArrayInputStream(requestData));
    }


    private class ResettableServletInputStream extends ServletInputStream {
        protected ResettableServletInputStream(ByteArrayInputStream inputStream) {
            this.inputStream = inputStream;
        }

        private ByteArrayInputStream inputStream;

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public void close() throws IOException {
            inputStream.close();
        }

        @Override
        public void reset() {
            this.inputStream.reset();
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }
    public static boolean isMultiReadHttpServletRequest(HttpServletRequest request) {
        return request instanceof MultiReadHttpServletRequest;
    }

    public static MultiReadHttpServletRequest newMultiReadHttpServletRequest(HttpServletRequest request) {
        return isMultiReadHttpServletRequest(request)?(MultiReadHttpServletRequest)request:new MultiReadHttpServletRequest(request);
    }
}
