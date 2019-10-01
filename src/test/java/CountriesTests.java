import com.epam.http.response.RestResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

public class CountriesTests {
    @BeforeTest
    public void before() {
        init(CountriesService.class);
    }

    @Test(description = "Get country by code")
    public void simpleRestTest() {
        RestResponse resp = CountriesService.getOneCountry.call();
        resp.isOk().
                body("borders[0]", equalTo("BRA")).
                body("name", equalTo("Colombia")).
                body("name", not("Russia"));
        resp.assertThat().header("Connection", "keep-alive");

//        assertEquals(resp.status.code, 200);
        assertEquals(resp.body.length(), 596);
    }

    @Test (description = "Get two countries by code")
    public void simpleRestTest2() {
        RestResponse resp = CountriesService.getTwoCountries.call();
        resp.isOk().
                body("name[0]", equalTo("Colombia")).
                body("name[1]", equalTo("Norway")).
                body("name", is(asList("Colombia", "Norway"))).

                body("borders[0][0]", equalTo("BRA")).
                body("borders[0]", hasItem("BRA")).
                body("borders[0]", is(asList("BRA","ECU","PAN","PER","VEN"))).
                body("borders[0]", hasSize(5)).

                body("borders[1][0]", equalTo("FIN")).
                body("borders[1]", hasSize(3));

        resp.assertThat().body("borders[0]", hasItem(startsWith("BR")));
        resp.assertThat().body("borders[0]", hasItem("BRA"));

        resp.assertThat().header("Connection", "keep-alive");
        assertEquals(resp.body.length(), 1210);
    }
}
