package leiloesmvn;

import org.junit.After;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	protected FirefoxDriver driver;

	@After
	public void encerra() {
		driver.close();
	}

	public void inicializa() {
		this.driver = new FirefoxDriver();
		driver.get("http://localhost:8080/leiloesmvn/apenas-teste/limpa");
	}
}
