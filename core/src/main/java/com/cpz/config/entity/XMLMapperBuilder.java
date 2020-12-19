package com.cpz.config.entity;

import com.cpz.model.Configuration;
import com.cpz.model.MappedStatement;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName XMLMapperBuilder.java
 * @createTime 2020年12月17日 21:59:00
 */
@Data
@Slf4j
@Builder
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder() {
    }

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 把mapper文件内容映射到 MappedStatement中
     * 然后把MappedStatement 添加到Configuration 的mapper中
     *
     * @param inputStream
     * @throws DocumentException
     */
    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        final String namespace = rootElement.attributeValue("namespace");
        List<Element> selectNodes = rootElement.selectNodes("//select");
        if (CollectionUtils.isNotEmpty(selectNodes)) {
            selectNodes.forEach(element -> {
                final String id = element.attributeValue("id");
                final String resultType = element.attributeValue("resultType");
                final String parameterType = element.attributeValue("parameterType");
                final String sql = element.getTextTrim();
                MappedStatement statement = MappedStatement.builder().id(id).sql(sql)
                        .parameterType(parameterType).resultType(resultType).build();
                String key = namespace + "." + id;
                configuration.getMappedStatementMap().put(key, statement);
            });
        }
    }
}
