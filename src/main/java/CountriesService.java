import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;
import helper.CountriesServiceData;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain(CountriesServiceData.DOMAIN)
public class CountriesService {
    @ContentType(JSON)
    @GET(CountriesServiceData.GET_ONE_COUNTRY_BY_CODE_URI)
    @Headers({
            @Header(name = CountriesServiceData.HEADER_NAME_1, value = CountriesServiceData.HEADER_VALUE_1),
            @Header(name = CountriesServiceData.HEADER_NAME_2, value = CountriesServiceData.HEADER_VALUE_2)
    })
    static
    RestMethod<Info> getOneCountry;

    @ContentType(JSON)
    @GET(CountriesServiceData.GET_TWO_COUNTRIES_BY_CODE_URI)
    @QueryParameter(name = "codes", value = "co;nor")
    @Headers({
            @Header(name = CountriesServiceData.HEADER_NAME_1, value = CountriesServiceData.HEADER_VALUE_1),
            @Header(name = CountriesServiceData.HEADER_NAME_2, value = CountriesServiceData.HEADER_VALUE_2)
    })
    static RestMethod<Info> getTwoCountries;

}
