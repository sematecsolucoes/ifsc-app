package leiloesmvn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class _01_TesteAutomatizado {
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com.br/");

		WebElement query = driver.findElement(By.name("q"));
		query.sendKeys("pesquisa");
		query.submit();
		driver.close();
	}
}
