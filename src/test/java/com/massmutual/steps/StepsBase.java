package com.massmutual.steps;

import com.massmutual.pages.PageBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class StepsBase {
	
	PageBase page = new PageBase();
	
	@Before
	public void setUp() {
		page.lauchURL();
	}
	
	@Given("URL is launched")
	public void URL_is_launched() {
		page.verifyCurrentURL();
	}
	
	@After
	public void tearDown() {
		page.closeBrowser();
	}
	
}
