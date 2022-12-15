package com.adm.rest;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * The CustomRequestBodyWrapper has the same behaviour as HttpServletRequestWrapper,
 * except that allows reading multiple times the body of the request on
 * {@link #getReader()} and {@link #getInputStream()}.
 *
 * <p>This functionality is essential in some parts of the application where the body
 * content is checked, but data loss is not desired.
 */
public class CustomRequestBodyWrapper extends HttpServletRequestWrapper {

    private byte[] cachedBody;

    public CustomRequestBodyWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream inputStream = request.getInputStream();
        this.cachedBody = StreamUtils.copyToByteArray(inputStream);
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new CachedBodyInputStream(this.cachedBody);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }
}
