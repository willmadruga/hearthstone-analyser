
package ca.willmadruga.stuffs.ui;

import org.springframework.beans.factory.annotation.Autowired;

import ca.willmadruga.stuffs.helpers.JsonImporter;
import ca.willmadruga.stuffs.persistence.CardEntity;
import ca.willmadruga.stuffs.persistence.Repos.CardsRepo;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

/**
 * Created by wmadruga on 21/11/15.
 */
@SpringComponent
@UIScope
public class CardsDataGridPanel extends Panel {

    private final CardsRepo cardsRepo;

    private final JsonImporter jsonImporter;

    private Grid grid;

    @Autowired
    public CardsDataGridPanel(final CardsRepo cardsRepo, final JsonImporter jsonImporter) {

        this.cardsRepo = cardsRepo;
        this.jsonImporter = jsonImporter;

        grid = createGrid(cardsRepo);

        setContent(grid);
        setSizeFull();

    }

    public void reloadData() {
        this.grid = createGrid(this.cardsRepo);
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
                        final CardWindow cardPanel = new CardWindow(selectedCard);

                        UI.getCurrent().addWindow(cardPanel);
                    }

                }
            }
        };
    }

}
