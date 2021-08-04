package top.chaser.framework.common.web.http.response;


import top.chaser.framework.common.base.util.HttpServletUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


/**
 * 返回值输出代理类
 *
 * @author kokJuis
 * @Title: ResponseWrapper
 * @Description:
 * @date 上午9:52:11
 */
public class ResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer = null;
    private ServletOutputStream out = null;
    private PrintWriter writer = null;

    public ResponseWrapper(HttpServletResponse resp) throws IOException {
        super(resp);
        buffer = new ByteArrayOutputStream();//真正存储数据的流
        out = new WapperedOutputStream(buffer);
        writer = new PrintWriter(out);
    }

    //重载父类获取outputstream的方法
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    //重载父类获取writer的方法
    @Override
    public PrintWriter getWriter() throws UnsupportedEncodingException {
        return writer;
    }

    //重载父类获取flushBuffer的方法
    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    public String getContent() throws IOException {
        String result = null;
        if (HttpServletUtil.isTextContentType(getContentType())) {
            flushBuffer();//将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据
            result = new String(buffer.toByteArray());
        }
        return result;
    }

    //内部类，对ServletOutputStream进行包装
    private class WapperedOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos = null;

        public WapperedOutputStream(ByteArrayOutputStream stream) throws IOException {
            bos = stream;
        }

        @Override
        public void write(int b) throws IOException {
            if(bos.size()<=1024*1024*10){
                bos.write(b);
            }
            getResponse().getOutputStream().write(b);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }
    }
}
