package ca.willmadruga.stuffs;

import ca.willmadruga.stuffs.helpers.JsonImporter;
import ca.willmadruga.stuffs.persistence.Repos.CardsRepo;
import ca.willmadruga.stuffs.persistence.Repos.MechanicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Autowired
    private JsonImporter jsonImporter;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(final CardsRepo cardsRepo, final MechanicsRepo mechanicsRepo) {
        return (args) -> {

            jsonImporter.importData(cardsRepo, mechanicsRepo);

            //jsonImporter.validateData(cardsRepo);

        };

    }


}
