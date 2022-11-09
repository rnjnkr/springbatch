package com.javadev.test.csv.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * This application accepts the file path of the CSV file as CLI argument
 * and reads and processes and writes to the STDOUT.

 * It can be run as <p>
 * {@code java -jar order-parser.jar --file=<absolute path of file>} <p>
 * Please note the CLI argument key must be `file` only.
 * <p>To run it inside the IDE, go to run configurations and provide the
 * file path program or CLI argument as '--file=<pathToFile>' i.e.
 * don't forget the double hyphen '--'.
 * <p>PS: A sample CSV file can be found under the resources folder.
 * @author  Ranjan Kumar
 * @Email: rnjnkr [at] gmail [dot] com
 */

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}


}
