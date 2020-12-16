package com.cpz;

import com.cpz.exception.ResourceException;
import com.cpz.io.LoadDataConfigResource;
import com.cpz.io.Resources;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName ResourcesTest.java
 * @createTime 2020年12月15日 23:26:00
 */
public class ResourcesTest {


    @Test
    public void testGetResourceInputStream() throws ResourceException {
        String path = "src/main/java/com/cpz/io/LoadDataConfigResource.java";
        Resources resources = new LoadDataConfigResource();
        InputStream inputStream = resources.getResourceInputStream(path);


    }
}
