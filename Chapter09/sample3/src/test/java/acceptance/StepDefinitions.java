package acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

/** Steps definitions for calculator.feature */
public class StepDefinitions {
    private String server = System.getProperty("calculator.url");

    private RestTemplate restTemplate = new RestTemplate();

    private String a;
    private String b;
    private String result;

    private String c;
    private String d;
    private String div_result;

    @Given("^I have two numbers: (.*) and (.*)$")
    public void i_have_two_numbers(String a, String b) throws Throwable {
        this.a = a;
        this.b = b;
    }

    @When("^the calculator sums them$")
    public void the_calculator_sums_them() throws Throwable {
        String url = String.format("%s/sum?a=%s&b=%s", server, a, b);
        result = restTemplate.getForObject(url, String.class);
    }

    @Then("^I receive (.*) as a result$")
    public void i_receive_as_a_result(String expectedResult) throws Throwable {
        assertEquals(expectedResult, result);
    }

    @Given("^I have dividend and divisor: (.*) and (.*)$")
    public void divide_two_numbers(String c, String d) throws Throwable {
        this.c = c;
        this.d = d;
    }

    @When("^the calculator divides them$")
    public void the_calculator_sums_them() throws Throwable {
        String url = String.format("%s/div?a=%s&b=%s", server, c, d);
        result = restTemplate.getForObject(url, String.class);
    }

    @Then("^I receive (.*) as the quotient$")
    public void quotient_result(String expectedResult) throws Throwable {
        assertEquals(expectedResult, div_result);
    }
}