package data;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.User;

public class GeneratorCreds {

    public static final String EMAIL_DOMAIN = "@yandex.ru";

    public User randomUser() {
        return new User(RandomStringUtils.randomAlphabetic(6)+EMAIL_DOMAIN,
                RandomStringUtils.randomAlphabetic(6), RandomStringUtils.randomAlphabetic(6));
    }
}