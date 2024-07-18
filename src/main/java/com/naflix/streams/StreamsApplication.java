package com.naflix.streams;

import com.naflix.streams.model.DataSerie;
import com.naflix.streams.model.Serie;
import com.naflix.streams.service.ConsumeApi;
import com.naflix.streams.service.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StreamsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConvertData convertData = new ConvertData();
		String json = ConsumeApi.consumeApi("https://www.omdbapi.com/?t=the+boys&apikey=1e8a0a02");
		DataSerie serie = convertData.getDatas(json, DataSerie.class);
		Serie serieEntity = new Serie(serie);
		System.out.println(serieEntity);
		System.out.println(serie);
	}
}
