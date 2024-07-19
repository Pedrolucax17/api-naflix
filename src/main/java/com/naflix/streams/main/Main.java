package com.naflix.streams.main;

import com.naflix.streams.model.*;
import com.naflix.streams.service.ConsumeApi;
import com.naflix.streams.service.ConvertData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private ConvertData convertData = new ConvertData();

    private Scanner sc = new Scanner(System.in);
    private final String BASE_URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=1e8a0a02";
    private List<DataSerie> serieList = new ArrayList<>();

    public void showMenu(){

        int option = -1;

        while(option != 0){
            String menu = """
                1 - Buscar séries
                2 - Buscar episódios
                
                0 - Sair                                 
                """;

            System.out.println(menu);
            option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 0:
                    System.out.println("Saindo");
                    System.exit(0);
                    break;
                case 1:
                    searchSeries();
                    break;
                case 2:
                    listAllSeries();
                    break;
                default:
                    System.out.println("Escolha uma opção válida");
            }

//            System.out.println("Digite a série que você deseja: ");
//            String serie = sc.nextLine();
//            String json = ConsumeApi.consumeApi(BASE_URL + serie + API_KEY);
//            DataSerie searchSerie = convertData.getDatas(json, DataSerie.class);
//            System.out.println(json);
//            System.out.println(searchSerie);
//
//            List<DataSeason> seasons = new ArrayList<>();
//
//            System.out.println("***TEMPORADAS***");
//            for(int i = 1; i <= searchSerie.totalSeasons(); i++){
//                String newJson = ConsumeApi.consumeApi(BASE_URL + serie + "&season=" + i + API_KEY);
//                DataSeason season = convertData.getDatas(newJson, DataSeason.class);
//                seasons.add(season);
//                System.out.println(season);
//            }
//
//            System.out.println("***EPISODIOS***");
////        seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));
//
//            List<DataEpisode> episodes = seasons.stream()
//                    .flatMap(t -> t.episodes().stream()).collect(Collectors.toList());
//
//
//            System.out.println("***TOP 5***");
//            episodes.stream()
//                    .filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
//                    .sorted(Comparator.comparing(DataEpisode::imdbRating).reversed())
//                    .limit(5)
//                    .forEach(System.out::println);
//
//            System.out.println("MINHA CLASSE EPISODE");
//            List<Episode> episodeList = seasons.stream()
//                    .flatMap(t -> t.episodes().stream().map(d -> new Episode(t.numberSeason(), d))).collect(Collectors.toList());
//
//            episodeList.forEach(System.out::println);
        }

    }

    private DataSerie getDataSeries(){
        System.out.println("Digite a série que você deseja: ");
        String serie = sc.nextLine();
        String json = ConsumeApi.consumeApi(BASE_URL + serie + API_KEY);
        return convertData.getDatas(json, DataSerie.class);
    }

    private void searchSeries(){
        DataSerie dataSerie = getDataSeries();
        serieList.add(dataSerie);
        System.out.println(dataSerie);
    }

    private void listAllSeries(){
        List<Serie> series;
        series = serieList.stream().map(d -> new Serie(d)).collect(Collectors.toList());
        series.stream().sorted(Comparator.comparing(Serie::getCategory)).forEach(System.out::println);
    }


}
