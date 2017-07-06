package ru.pravvich.jdbc.properties;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class PropertiesLoaderTest {

    @Test
    public void whenKeyExistThenReturnValue() {

        final String result = new PropertiesLoader("login_db.properties")
                .get("password");

        Assert.assertThat(result, is("1"));
    }

    @Test
    public void whenKeyNotExistReturnNull() {
        final String result = new PropertiesLoader("login_db.properties")
                .get("not_exist_key");

        Assert.assertNull(result);
    }
}