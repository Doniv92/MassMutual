package com.massmutual.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.massmutual.utilities.Utilities;

public class PageValues extends PageBase{

	
	public PageValues() {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//*[starts-with(@id,'lbl_val')]")
	private List<WebElement> listLabels;
	
	@FindBy(xpath = "//*[starts-with(@id,'txt_val')]")
	private List<WebElement> listAmounts;
	
	@FindBy(id = "lbl_ttl_val")
	private WebElement labelTotalBalance;

	@FindBy(id = "txt_ttl_val")
	private WebElement amountTotalBalance;
	
	
	
	/**
	 * Below method will verify that the values are present and visible
	 * @param none
	 */
	public void verifyValues() {
		Utilities.waitForElements(listLabels);
		Utilities.waitForElements(listAmounts);
	}
	
	
	/**
	 * Below method will verify that the number of values is equal to the number of labels
	 * @param expected count, Integer
	 */
	public void verifyCountOfValues(int count) {
		
		try {
			
			// To verify count of labels and amounts
			int labelCount = listLabels.size();
			int amountCount = listAmounts.size();
			Assert.assertEquals(labelCount, count, "Labels -> Expected Count "+count+ "| Acutal Count: "+labelCount);
			Assert.assertEquals(amountCount, count, "Amounts -> Expected Count "+count+ "| Acutal Count: "+amountCount);
			
			// To verify label text (Value 1, Value 2, Value 3, Value 4, Value 5)
			int expectedNumberInLabel=0;
			for (int i=0; i<labelCount; i++) {
				String label = listLabels.get(i).getText().trim();
				expectedNumberInLabel=i+1;
				Assert.assertEquals(label, "Value "+expectedNumberInLabel,
						"Label -> Expected: Value "+expectedNumberInLabel+ "| Acutal: "+label);
			}
			
		} catch (Exception e) {
			Reporter.log("Count is not equal to "+count);
		}
		
	}
	
	
	/**
	 * Below method will verify that the amounts are greater than minimum value
	 * @param minimum value, Integer
	 */
	public void verifyValuesAreGreaterThan(int minimumValue) {
		try {
			for (int i=0; i<listAmounts.size(); i++) {
				String label = listLabels.get(i).getText();
				String amount = listAmounts.get(i).getText().trim();
				String amountMinusDollar = amount.replaceAll("$", "");
				String amountMinusCommas = amountMinusDollar.replaceAll(",", "");
				Assert.assertTrue(Integer.parseInt(amountMinusCommas) > minimumValue,
						"Label: "+label+ "'s Amount is greater than "+minimumValue);
			}
		} catch (Exception e) {
			Reporter.log("Amount is not greater than "+minimumValue);
		}
		
	}
	
	
	/**
	 * Below method will verify that the amounts are formatted as currencies
	 * @param expected currency, Char
	 */
	public void verifyValuesAreFormattedAs(String expectedCurrency) {
		
		try {
			
			for (int i=0; i<listAmounts.size(); i++) {
				String label = listLabels.get(i).getText();
				String amount = listAmounts.get(i).getText().trim();
				
				// verify dollar sign
				String acutalCurrency = amount.substring(0, 1);
				Assert.assertEquals(acutalCurrency, expectedCurrency,
						"Label: "+label+"| Expected Currency: "+expectedCurrency+ "| Acutal Currency: "+acutalCurrency);
				
				// verify value contains only numbers with 2 decimals
				String amountMinusDollar = amount.replaceAll("$", "");
				String amountMinusCommas = amountMinusDollar.replaceAll(",", "");
				Assert.assertEquals(amountMinusCommas.matches("^\\d+\\.\\d{2}$"), true,
						"Amount contains only numbers with 2 decimals");
			}
			
		} catch (Exception e) {
			Reporter.log("Actual Currency is not "+expectedCurrency);
		}
		
	}
	
	
	/**
	 * Below method will verify that the total balance matches the sum of values
	 * @param none
	 */
	public void verifyTotalBalance() {
		
		try {
			
			// calculate sum of values
			float total = Float.parseFloat("0.00");
			for (int i=0; i<listAmounts.size(); i++) {
				String amount = listAmounts.get(i).getText().trim();
				String amountMinusDollar = amount.replaceAll("$", "");
				String amountMinusDollarAndCommas = amountMinusDollar.replaceAll(",", "");
				float amt = Float.parseFloat(amountMinusDollarAndCommas);
				total = total + amt;
			}
			String sumOfValues = Utilities.getDecimalFormat(total, "##.00");
			
			// get total balance
			Assert.assertEquals(labelTotalBalance.isDisplayed(), true);
			String balance = amountTotalBalance.getText();
			String balanceMinusDollar = balance.replaceAll("$", "");
			String balanceMinusDollarAndCommas = balanceMinusDollar.replaceAll(",", "");
			float bal = Float.parseFloat(balanceMinusDollarAndCommas);
			String totalBalance = Utilities.getDecimalFormat(bal, "##.00");
			
			// verify total balance is equal to sum of values
			Assert.assertEquals(sumOfValues, totalBalance, "Sum of Values: "+sumOfValues+ " | Total Balance: "+totalBalance);
			
		} catch (Exception e) {
			Reporter.log("Total balance is incorrect");
		}
		
	}
	
	
}
