package controller;

import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class BaseTeste {
	@AfterClass
	public static void finalizaLog() {
		LOGGER = null;
		System.out.println("-----------------------------------");
		System.out.println("---- LOGS FINALIZADOS--------------");
		System.out.println("-----------------------------------");

	}

	@BeforeClass
	public static void iniciaLog() {
		System.out.println("-----------------------------------");
		System.out.println("----INICIANDO LOGS-----------------");
		System.out.println("-----------------------------------");
		LOGGER = Logger.getLogger(TesteAvaliador.class.getSimpleName());
	}

	protected static Logger LOGGER;

	protected void logMethodName() {
		LOGGER.info(Thread.currentThread().getStackTrace()[2].getMethodName());
	}
}
