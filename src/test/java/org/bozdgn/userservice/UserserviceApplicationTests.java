package org.bozdgn.userservice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class UserserviceApplicationTests {

    @Test
    void itAdds1to2() {
        int result = 2 * 2;
        int expected = 4;

        assertThat(result).isEqualTo(expected);
    }

}
