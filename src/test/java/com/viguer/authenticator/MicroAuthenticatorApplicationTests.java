package com.viguer.authenticator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MicroAuthenticatorApplicationTests {

	@Test
	void contextLoads() {
        String[] args = new String[0];
        MicroAuthenticatorApplication application = new MicroAuthenticatorApplication();
        application.main(args);
	}

}
