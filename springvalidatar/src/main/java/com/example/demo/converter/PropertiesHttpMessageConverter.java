package com.example.demo.converter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {

    public PropertiesHttpMessageConverter() {
        super(new MediaType("text", "plain"));
    }


    /**反序列化过程*/
    @Override
    protected Properties readInternal(Class<? extends Properties> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Properties properties = new Properties();
        HttpHeaders headers = httpInputMessage.getHeaders();
        MediaType contentType = headers.getContentType();
        Charset charset = null;
        charset = contentType.getCharset();
        charset = charset == null ? Charset.forName("UTF-8"):charset;
        InputStream inputBody = httpInputMessage.getBody();
        InputStreamReader reader =  new InputStreamReader(inputBody,charset);
        properties.load(reader);
        return properties;
    }


    /**序列化过程*/
    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }

    @Override
    public Properties read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return  readInternal(null, httpInputMessage);
    }


}
