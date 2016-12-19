package leiloesmvn;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class _04_TesteAutomatizado {

	private WebDriver driver;

	@Test
	public void deveAdicionarUmUsuario() {
		driver.get("http://localhost:8080/leiloesmvn/usuarios");
		driver.findElement(By.linkText("Novo Usuário")).click();
		WebElement nome = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));
		nome.sendKeys("Usuario Teste");
		email.sendKeys("usuario@teste.com");
		nome.submit();
		boolean achouNome = driver.getPageSource().contains("Usuario Teste");
		boolean achouEmail = driver.getPageSource().contains("usuario@teste.com");
		assertTrue(achouNome);
		assertTrue(achouEmail);
	}

	@After
	public void encerra() {
		driver.close();
	}

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
	}

	@Test
	public void naoDeveAdicionarUmUsuarioSemNome() {
		driver.get("http://localhost:8080/leiloesmvn/usuarios");
		driver.findElement(By.linkText("Novo Usuário")).click();
		WebElement email = driver.findElement(By.name("usuario.email"));
		email.sendKeys("usuario@teste.com");
		email.submit();
		assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));
	}

	@Test
	public void naoDeveAdicionarUmUsuarioSemNomeOuEmail() {
		driver.get("http://localhost:8080/leiloesmvn/usuarios");
		driver.findElement(By.linkText("Novo Usuário")).click();
		WebElement email = driver.findElement(By.name("usuario.email"));
		email.submit();
		assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));
		assertTrue(driver.getPageSource().contains("E-mail obrigatorio!"));
	}

}
