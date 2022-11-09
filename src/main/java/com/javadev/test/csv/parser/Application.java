package com.javadev.test.csv.parser;
/**
 * @Author: Ranjan Kumar (rnjnkr[at]gmail[dot]com)
 * This application accepts the file path of the CSV file as CLI and
 * reads and processes and writes to the STDOUT.
 * It can be run as
 * @<code> java -jar order-parser.jar --file=<absolute path of file> </code>
 * Please note the CLI argument key must be `file` only.
 * PS: To run it inside the IDE, go to run configurations and provide the
 * file path program or CLI argument as '--file=<pathToFile>'
 * Don't forget the double hyphen '--'
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}


}
