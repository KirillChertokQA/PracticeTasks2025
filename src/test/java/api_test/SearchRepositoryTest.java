package api_test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SearchRepositoryTest {

     //"Accept: application/vnd.github+json"
    //"X-GitHub-Api-Version: 2022-11-28" \
    //"https://api.github.com/search/code?q=Q"

    @Test
    public void searchRepoTest(){

        String repository;   //QA-45 - 200   PhoneBook - 89k   ios - 506k     git - 10.5M
        repository = System.getProperty("repo","A45-QA-Automation-Framework");
        if(repository.isBlank())
            Assert.fail("repository is blank");

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("X-GitHub-Api-Version", "2022-11-28")
                .addHeader("Accept", "application/vnd.github+json")
                .build();
       Response response = given()
                .spec(requestSpecification)
                .when()
               .param("q", repository)
                .get("https://api.github.com/search/repositories")
                .thenReturn();
        System.out.println("response.getStatusCode()-->" +response.getStatusCode());
        Assert.assertTrue(response.getBody().toString().contains(": false"));
    }
}
