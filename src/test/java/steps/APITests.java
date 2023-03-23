package steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class APITests {

    private RequestSpecification request;
    private Response response;
    private String userId;

    @Given("I have a user with first name {string} and last name {string} and email {string}")
    public void i_have_a_user_with_first_name_and_last_name_and_email(String firstName, String lastName, String email) {
        request = RestAssured.given()
                .baseUri("https://63fe35bb571200b7b7c75edc.mockapi.io/awaed/users")
                .header("Content-Type", "application/json")
                .body("{\"firstName\": \"" + firstName + "\",\"lastName\": \"" + lastName + "\",\"email\": \"" + email + "\",\"active\": true}");
    }

    @When("I create a user")
    public void i_create_a_user() {
        response = request.post();
        userId = response.jsonPath().getString("id");
    }

    @Then("the status code should be 201")
    public void the_status_code_should_be_201() {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);
    }

    @Then("I retrieve the created user by id")
    public void i_retrieve_the_created_user_by_id() {
        request = RestAssured.given()
                .baseUri("https://63fe35bb571200b7b7c75edc.mockapi.io/awaed/users/" + userId);
        response = request.get();
    }

    @Then("the first name should be {string}")
    public void the_first_name_should_be(String firstName) {
        String retrievedFirstName = response.jsonPath().getString("firstName");
        Assert.assertEquals(firstName, retrievedFirstName);
    }
}
