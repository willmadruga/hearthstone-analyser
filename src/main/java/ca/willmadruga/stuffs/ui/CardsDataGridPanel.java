package ca.willmadruga.stuffs.ui;

import ca.willmadruga.stuffs.helpers.JsonImporter;
import ca.willmadruga.stuffs.persistence.CardEntity;
import ca.willmadruga.stuffs.persistence.Repos.CardsRepo;
import com.google.gwt.dom.builder.shared.AnchorBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by wmadruga on 21/11/15.
 */
@SpringComponent
@UIScope
public class CardsDataGridPanel extends VerticalLayout {

    private final CardsRepo cardsRepo;

    private final JsonImporter jsonImporter;

    private final Grid grid;

    @Autowired
    public CardsDataGridPanel(final CardsRepo cardsRepo, final JsonImporter jsonImporter) {

        this.cardsRepo = cardsRepo;
        this.jsonImporter = jsonImporter;

        grid = createGrid(cardsRepo);

        final Button loadDataBtn = new Button("Load Data");
        loadDataBtn.setDisableOnClick(true);

        loadDataBtn.addClickListener(event -> {
            try {
                jsonImporter.importData();
                grid.requestRepaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addComponent(loadDataBtn);
        addComponent(new Label("")); // temporary... TODO: change css.
        addComponent(grid);
        setSizeFull();
        setExpandRatio(grid, 1.0f);
    }

    private Grid createGrid(CardsRepo cardsRepo) {
        final Grid grid = new Grid();
        grid.setSizeFull();
        grid.setColumnReorderingAllowed(true);
        grid.setResponsive(true);

        final BeanItemContainer dataSource = new BeanItemContainer(CardEntity.class, cardsRepo.findAll());
        grid.setContainerDataSource(dataSource);
        grid.setColumns("setName", "cardIdentifier", "name", "type", "cost", "attack", "health", "text");
        grid.setColumnOrder("setName");

        return grid;
    }

}
