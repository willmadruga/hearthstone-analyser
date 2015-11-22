package ca.willmadruga.stuffs;

import ca.willmadruga.stuffs.domain.CardModel;
import ca.willmadruga.stuffs.domain.SetModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {

            final ObjectMapper mapper = new ObjectMapper();
            final File jsonFile = new File("AllSets.json");

            final List<SetModel> setModelList = process(mapper, jsonFile);


        };

    }


    private List<SetModel> process(final ObjectMapper mapper, final File jsonFile) throws IOException {

        final List<SetModel> setModelList = new ArrayList<>();

        final JsonNode node = mapper.readTree(jsonFile);

        final Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {

            final String fieldName = (String) fieldNames.next();
            final List<JsonNode> cards = node.findValues(fieldName);
            final SetModel setModel = new SetModel();

            setModel.setSetName(fieldName);
            setModel.setList(new ArrayList<>());

            for (JsonNode content : cards) {
                final Iterator cardIterator = content.elements();
                while (cardIterator.hasNext()) {

                    final ObjectNode card = (ObjectNode) cardIterator.next();

                    final String cardJson = card.toString();
                    final CardModel cardModel = mapper.readValue(cardJson, CardModel.class);
                    setModel.getList().add(cardModel);
                }

                setModelList.add(setModel);
            }

        }

        return setModelList;

    }

}
