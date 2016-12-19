package leiloesmvn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class _02_TesteAutomatizadoChrome {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:/ambiente/chromedriver.exe");
		// System.setProperty("webdriver.chrome.driver", "/opt/google/chrome/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com.br/");

		WebElement query = driver.findElement(By.name("q"));
		query.sendKeys("pesquisa");
		query.submit();
		driver.close();

	}
}
