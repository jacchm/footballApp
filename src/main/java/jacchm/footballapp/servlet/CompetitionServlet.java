package jacchm.footballapp.servlet;

import jacchm.footballapp.util.JsonUtil;
import jacchm.footballapp.pojo.competition.Competition;
import jacchm.footballapp.pojo.competition.CompetitionsInput;
import jacchm.footballapp.repository.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/competitions")
public class CompetitionServlet {

    CompetitionRepository competitionRepository;
    private final Logger logger = LoggerFactory.getLogger(CompetitionServlet.class);


    @GetMapping("/all")
    public String updateCompetitionDatabase() {
        File file = new File("D:\\JavaProjects\\footballapp\\src\\main\\" +
                "resources\\dataFromExternalApi\\EnglishFromApi.json");
        CompetitionsInput competitionsInput;
        try {
            competitionsInput = JsonUtil.fromJsonFile(file, CompetitionsInput.class);

            for (Competition competition : competitionsInput.getCompetitions()) {
                    competitionRepository.save(competition);
            }

            logger.info("Competitions have been successfully added to the database");
            return "Competitions have been successfully added to the database";

        } catch (IOException e) {
            logger.error("Error has been encountered. ");
            return "Something went wrong";
        }
    }



    /*static {
        System.out.println("CompetitionController static block running: ");
        try {

            competitionsInput = JsonUtil.fromJsonFile(file, CompetitionsInput.class);

            System.out.println(competitionsInputFile.getCompetitions().get(0).toString());


        *//*  System.out.println("--------- Leagues data -----------");

            System.out.println(competitionsInputFile.getCount() + " leagues detected");

            for (Competition competition : competitionsInputFile.getCompetitions()) {
                System.out.println("----------------------------");
                System.out.println("Competition id: " + competition.getId());
                System.out.println("League country id: " + competition.getArea().getId());
                System.out.println("League country name: " + competition.getArea().getName());
                System.out.println("League country code: " + competition.getArea().getCountryCode());
                System.out.println("League country: " + competition.getArea().getEnsignUrl());
                System.out.println("League Name: " + competition.getName());
                System.out.println("League Code: " + competition.getCode());
                System.out.println("EnsignUrl: " + competition.getEnsignUrl());
                System.out.println("League plan: " + competition.getPlan());

                if (competition.getCurrentSeason() != null) {
                    System.out.println("League current season start date: " + competition.getCurrentSeason().getStartDate());
                    System.out.println("League current season end date: " + competition.getCurrentSeason().getEndDate());
                    System.out.println("League current season current matchday: " + competition.getCurrentSeason().getCurrentMatchday());

                    if (competition.getCurrentSeason().getWinner() != null) {
                        System.out.println("League current season winner id: " + competition.getCurrentSeason().getWinner().getId());
                        System.out.println("League current season winner name: " + competition.getCurrentSeason().getWinner().getName());
                        System.out.println("League current season winner short name: " + competition.getCurrentSeason().getWinner().getShortName());
                        System.out.println("League current season winner crest url: " + competition.getCurrentSeason().getWinner().getCrestURL());
                        System.out.println("League current season winner tla: " + competition.getCurrentSeason().getWinner().getTla());
                    }
                }

                System.out.println("League number of available seasons: " + competition.getNumberOfAvailableSeasons());
                System.out.println("League last time updated: " + competition.getLastUpdated());
            }
*//*

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

