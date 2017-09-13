package api.entities;

import api.ConciseAPI;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class ApiObject extends ConciseAPI {
    private Response response;

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    private RequestSpecification requestHeader() {
        return given().headers(getHeaders());
    }

    public List<String> getListsOf(String parseField) {

        if (getResponseAsString() != null) {
            List<String> dataList = parseValues(getResponseAsString(), parseField);
            return regexName(dataList);
        }

        return null;
    }

    protected String getResponseAsString() {
        return this.response.asString();
    }

    private static List<String> parseValues(String responce, String fieldName) {
        List<String> parse = new ArrayList<>();

        try {
            parse = from(responce).get(fieldName);

        } catch (ClassCastException e) {
            String oneValueParse = from(responce).get(fieldName);
            parse.add(oneValueParse);
        }

        return parse;
    }

    @Step("Do POST to \"{0}\"")
    public void post(String path, String body) {
        if (body != null && path != null) {
            try {
                this.response = requestHeader().body(body).post(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected String generateStringFromResource(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Step("Assert structure of response")
    public void assertResponseStructure(String pathToSchema) {
        assertThat(getResponseAsString(), matchesJsonSchemaInClasspath(pathToSchema));
    }

    @Step("Send GET to \"{0}\" ")
    public void get(String path) {
        try {
            this.response = requestHeader().get(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
