Feature: Verify Values and Total Balance

  Scenario: Verify the right count of values appear on screen
  	Given URL is launched
  	When values are displayed on screen
  	Then count of values and amounts should be 5
  
  Scenario: Verify the values on screen are greater than 0
  	Given URL is launched
  	When values are displayed on screen
  	Then values should be greater than 0
    
  Scenario: Verify the total balance is correct based on the values listed on the screen
  	Given URL is launched
  	When values are displayed on screen
  	Then total balance should be correct
  	
  Scenario Outline: Verify the values are formatted as currencies
  	Given URL is launched
  	When values are displayed on screen
  	Then values should be formatted as currencies "<currency>"
  	
  	Examples:
  		| currency |
			| $        |
  
  Scenario: Verify the total balance matches the sum of the values
  	Given URL is launched
  	When values are displayed on screen
  	Then total balance should match the sum of the values
  	