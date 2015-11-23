
package ca.willmadruga.stuffs.ui;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import ca.willmadruga.stuffs.helpers.JsonImporter;
import ca.willmadruga.stuffs.persistence.CardEntity;
import ca.willmadruga.stuffs.persistence.Repos.CardsRepo;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

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

        final Button loadDataBtn = new Button("(Re)load json");
        loadDataBtn.setDisableOnClick(true);

        // Java 1.8 lambda :)
        // loadDataBtn.addClickListener(event -> {
        // try {
        // jsonImporter.importData();
        // grid.requestRepaint();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // });

        // Java 1.7
        loadDataBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(final Button.ClickEvent clickEvent) {
                try {
                    jsonImporter.importData();
                    grid.requestRepaint(); // FIXME: this is not happening. Couldn't find a render method to force reload yet.
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        addComponent(loadDataBtn);
        addComponent(new Label("")); // temporary... TODO: change css.
        addComponent(grid);
        setSizeFull();
        setExpandRatio(grid, 1.0f);
    }

    private Grid createGrid(final CardsRepo cardsRepo) {
        final Grid grid = new Grid();
        grid.setSizeFull();
        grid.setColumnReorderingAllowed(true);
        grid.setResponsive(true);

        final BeanItemContainer dataSource = new BeanItemContainer(CardEntity.class, cardsRepo.findAll());
        grid.setContainerDataSource(dataSource);
        grid.setColumns("setName", "cardIdentifier", "name", "type", "cost", "attack", "health", "mechanics", "text");
        grid.setColumnOrder("setName");

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        grid.addSelectionListener(getGridSelectionEvent(cardsRepo, grid));

        return grid;
    }

    // Java 1.7
    private SelectionEvent.SelectionListener getGridSelectionEvent(final CardsRepo cardsRepo, final Grid grid) {
        return new SelectionEvent.SelectionListener() {

            @Override
            public void select(final SelectionEvent selectionEvent) {

                final Object selected = ((Grid.SingleSelectionModel) grid.getSelectionModel()).getSelectedRow();

                if (selected == null) {
                    Notification.show("nothing selected...");
                } else {
                    final Item selectedItem = grid.getContainerDataSource().getItem(selected);
                    final String cardIdentifier = (String) selectedItem.getItemProperty("cardIdentifier").getValue();

                    final CardEntity selectedCard = cardsRepo.findByCardIdentifier(cardIdentifier);

                    if (selectedCard == null) {
                        Notification.show("Error retrieving card info.");
                    } else {
                        // open a dialog to show detailed card's info.
                        final Panel cardPanel = new CardPanel(selectedCard.getName());
                        addComponent(cardPanel);
                    }

                }

            }
        };
    }

}
