package com.example.InvestmentServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс, точка запуска сервера
 */
@SpringBootApplication
public class InvestmentServerApplication {
	/**
	 * Основной метод запуска сервера
	 * @param args аргументы
	 */
	public static void main(String[] args) {
		SpringApplication.run(InvestmentServerApplication.class, args);
	}
}
