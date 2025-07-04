package data;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.User;


public class GeneratorCreds {


    public User randomUser() {
        String email = randomEmail();
        String name = RandomStringUtils.randomAlphabetic(6, 10);
        String password = RandomStringUtils.randomAlphabetic(3, 10);

        return new User(email, name, password);
    }

    private String randomEmail() {
        String username = RandomStringUtils.randomAlphabetic(5, 10).toLowerCase();
        String domain = RandomStringUtils.randomAlphabetic(3, 8).toLowerCase();
        String tld = RandomStringUtils.randomAlphabetic(2, 4).toLowerCase();

        return username + "@" + domain + "." + tld;
    }
}
