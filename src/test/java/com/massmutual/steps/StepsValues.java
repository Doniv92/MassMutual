package com.massmutual.steps;

import com.massmutual.pages.PageValues;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsValues {
	
	PageValues page = new PageValues();
	
	@When("values are displayed on screen")
	public void values_are_displayed_on_screen() {
	    page.verifyValues();
	}

	@Then("count of values and amounts should be {int}")
	public void count_of_values_and_amounts_should_be(int count) {
	    page.verifyCountOfValues(count);
	}
	
	@Then("values should be greater than {int}")
	public void values_should_be_greater_than(int minimumValue) {
	    page.verifyValuesAreGreaterThan(minimumValue);
	}
	
	@Then("total balance should be correct")
	public void total_balance_should_be_correct() {
	    page.verifyTotalBalance();
	}
	
	@Then("values should be formatted as currencies {string}")
	public void values_should_be_formatted_as_currencies(String expectedCurrency) {
	    page.verifyValuesAreFormattedAs(expectedCurrency);
	}
	
	@Then("total balance should match the sum of the values")
	public void total_balance_should_match_the_sum_of_the_values() {
	    page.verifyTotalBalance();
	}
	
}
