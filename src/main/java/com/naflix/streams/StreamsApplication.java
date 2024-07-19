package com.naflix.streams;

import com.naflix.streams.main.Main;
import com.naflix.streams.model.DataSerie;
import com.naflix.streams.model.Serie;
import com.naflix.streams.repository.SerieRepository;
import com.naflix.streams.service.ConsumeApi;
import com.naflix.streams.service.ConvertData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamsApplication implements CommandLineRunner {
	@Autowired
	private SerieRepository serieRepository;

	public static void main(String[] args) {
		SpringApplication.run(StreamsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Main main = new Main(serieRepository);
        main.showMenu();
	}
}
