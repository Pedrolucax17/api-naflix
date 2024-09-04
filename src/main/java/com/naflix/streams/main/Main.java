package com.naflix.streams.main;

import com.naflix.streams.model.*;
import com.naflix.streams.repository.SerieRepository;
import com.naflix.streams.service.ConsumeApi;
import com.naflix.streams.service.ConvertData;

import java.util.*;

import java.util.stream.Collectors;

public class Main {

    private ConvertData convertData = new ConvertData();

    private Scanner sc = new Scanner(System.in);
    private final String BASE_URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=1e8a0a02";
    private List<Serie> serieList = new ArrayList<>();

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
        Serie serie = new Serie(dataSerie);
        serieRepository.save(serie);
        serieList.add(serie);
        System.out.println(dataSerie);
    }

    private void searchEpisodeBySerie(){
        listAllSeries();

        if (serieList.isEmpty()){
            System.out.println("A lista está vazia");
        }else{
            System.out.println("A lista não está vazia");
        }

        System.out.println("Escolha uma série pelo nome: ");
        var nomeSerie = sc.nextLine();
        System.out.println(nomeSerie);

        System.out.println(serieList);

        Optional<Serie> serieOptional = serieList.stream().filter(s -> s.getTitle().toLowerCase().contains(nomeSerie.toLowerCase())).findFirst();
        System.out.println(serieOptional);
        if (serieOptional.isPresent()){
            Serie serieEncontrada = serieOptional.get();
            List<DataSeason> seasons = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalSeason(); i++){
                String json = ConsumeApi.consumeApi(BASE_URL + serieEncontrada.getTitle() + "&season=" + i + API_KEY);
                DataSeason season = convertData.getDatas(json, DataSeason.class);
                seasons.add(season);
            }

            seasons.forEach(System.out::println);

            List<Episode> episodes = seasons.stream().flatMap(s -> s.episodes().stream().map(
                    e -> new Episode(s.numberSeason(), e)
            )).collect(Collectors.toList());

            serieEncontrada.setEpisodes(episodes);
            serieRepository.save(serieEncontrada);

        }else{
            System.out.println("Série não encontrada!");
        }
    }

    private void listAllSeries(){
        List<Serie> series = serieRepository.findAll();
        series.stream().sorted(Comparator.comparing(Serie::getCategory)).forEach(System.out::println);
    }


}
