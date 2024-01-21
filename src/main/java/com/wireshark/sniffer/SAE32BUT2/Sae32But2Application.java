package com.wireshark.sniffer.SAE32BUT2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
@RestController

public class Sae32But2Application {
   private String DELIMITER=",";
	@GetMapping("/data")
	public List<Map<String,String>> getTraces(){
		//Lire un fichier csv
		//Source https://www.baeldung.com/java-csv-file-array

		//Creer une liste de map
		List<Map<String,String>> donnees = new ArrayList<>();
		//declarer un compteur
		int counter = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/data.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				//eviter la premier ligne du fichier
				if (counter == 0){
					counter ++;
					continue;
				}
				Map<String,String> lineDeDonnees = new HashMap<>();
				String[] values = line.split(DELIMITER);
				lineDeDonnees.put("ID", values[0]);
				lineDeDonnees.put("Time", values[1]);
				lineDeDonnees.put("Source", values[2]);
				lineDeDonnees.put("Destination", values[3]);
				lineDeDonnees.put("Protocol", values[4]);
				lineDeDonnees.put("Length", values[5]);
				lineDeDonnees.put("Info", values[6]);
				donnees.add(lineDeDonnees);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return donnees;

	}

	public static void main(String[] args) {
		SpringApplication.run(Sae32But2Application.class, args);
	}

}
