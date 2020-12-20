package com.cpz.config;

import com.cpz.config.entity.SqlConfigEntity;
import com.cpz.config.entity.XMLMapperBuilder;
import com.cpz.exception.ParseConfigException;
import com.cpz.exception.ResourceException;
import com.cpz.io.LoadDataConfigResource;
import com.cpz.io.Resources;
import com.cpz.model.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName XMConfigFactoryBuilder.java
 * @createTime 2020年12月16日 22:48:00
 */
@Slf4j
public class XMLConfigFactoryBuilder implements XMLConfigFactory<Configuration> {

    @Override
    public Configuration parseConfig(InputStream inputStream) throws ParseConfigException {
        log.debug("parseConfig start");
        Configuration parseConfig = new Configuration();
        try {
            Document document = new SAXReader().read(inputStream);
            Element rootElement = document.getRootElement();
            //查询所有 property 属性
            List<Element> elementList = rootElement.selectNodes("//property");
            if (CollectionUtils.isNotEmpty(elementList)) {
                Properties properties = new Properties();
                elementList.forEach(element -> {
                    String name = element.attributeValue("name");
                    String value = element.attributeValue("value");
                    properties.setProperty(name, value);
                });
                log.debug("parseConfig properties {} ", properties);

             /*   HikariConfig config = new HikariConfig();
                config.setUsername((String) properties.get("username"));
                config.setPassword((String) properties.get("passWord"));
                config.setJdbcUrl((String) properties.get("jdbcUrl"));
                config.setDriverClassName((String) properties.get("driverClass"));
                HikariPool hikariPool = new HikariPool(config);*/
                ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
                comboPooledDataSource.setDriverClass((String) properties.get("driverClass"));
                comboPooledDataSource.setJdbcUrl((String) properties.get("jdbcUrl"));
                comboPooledDataSource.setUser((String) properties.get("username"));
                comboPooledDataSource.setPassword((String) properties.get("passWord"));
                parseConfig.setDataSource(comboPooledDataSource);
            /*    parseConfig.setHikariPool(hikariPool);*/
                parseMapperXml(rootElement, parseConfig);
            }
        } catch (Exception e) {
            log.error("parse Config error", e);
            throw new ParseConfigException("parseConfig error");
        }
        return parseConfig;
    }

    /**
     * 解析mapperXml
     * 因为 mapper是存放在 config里的 所以可以直接拿到
     * 一个mapper 是对应的一个标签值
     *
     * @param rootElement
     */
    public void parseMapperXml(Element rootElement, Configuration configuration) {
        log.debug("parseMapperXml rootElement {}", rootElement);
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        List<String> mapperPathList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(mapperList)) {
            mapperList.forEach(element -> {
                mapperPathList.add(element.attributeValue("resource"));
            });
        }
        if (CollectionUtils.isNotEmpty(mapperPathList)) {
            mapperPathList.forEach(mapperPath -> {
                XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(configuration);
                Resources resources = new LoadDataConfigResource();
                try {
                    mapperBuilder.parse(resources.getResourceInputStream(mapperPath));
                } catch (Exception e) {
                    log.error("getResourceInputStream fail {}", mapperPath);
                }
            });
        }
    }
}
