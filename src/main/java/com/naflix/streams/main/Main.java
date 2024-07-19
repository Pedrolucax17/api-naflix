package com.naflix.streams.main;

import com.naflix.streams.model.*;
import com.naflix.streams.repository.SerieRepository;
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

    private SerieRepository serieRepository;

    public Main(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void showMenu(){

        int option = -1;

        while(option != 0){
            String menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3- Listar todas as séries
                
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
                    searchEpisodeBySerie();
                    break;
                case 3:
                    listAllSeries();
                    break;
                default:
                    System.out.println("Escolha uma opção válida");
            }

        }

    }

    private DataSerie getDataSeries(){
        System.out.println("Digite a série que você deseja: ");
        String serie = sc.nextLine();
        String json = ConsumeApi.consumeApi(BASE_URL + serie.replace(" ", "+") + API_KEY);
        return convertData.getDatas(json, DataSerie.class);
    }

    private void searchSeries(){
        DataSerie dataSerie = getDataSeries();
        serieList.add(dataSerie);
        System.out.println(dataSerie);
    }

    private void searchEpisodeBySerie(){
        DataSerie dataSerie = getDataSeries();
        List<DataSeason> seasons = new ArrayList<>();

        for(int i = 1; i <= dataSerie.totalSeasons(); i++){
                String json = ConsumeApi.consumeApi(BASE_URL + dataSerie.title() + "&season=" + i + API_KEY);
                DataSeason season = convertData.getDatas(json, DataSeason.class);
                seasons.add(season);
        }

        seasons.forEach(System.out::println);
    }

    private void listAllSeries(){
        List<Serie> series;
        series = serieList.stream().map(d -> new Serie(d)).collect(Collectors.toList());
        series.stream().sorted(Comparator.comparing(Serie::getCategory)).forEach(System.out::println);
    }


}
