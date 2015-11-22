package ca.willmadruga.stuffs.ui;

import ca.willmadruga.stuffs.persistence.CardEntity;
import ca.willmadruga.stuffs.persistence.Repos.CardsRepo;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wmadruga on 21/11/15.
 */
@SpringComponent
@UIScope
public class CardsDataGridPanel extends VerticalLayout {

    private final CardsRepo cardsRepo;

    @Autowired
    public CardsDataGridPanel(final CardsRepo cardsRepo) {
        this.cardsRepo = cardsRepo;

        final Grid grid = createGrid(cardsRepo);
        addComponent(grid);
        setSizeFull();
    }

    private Grid createGrid(CardsRepo cardsRepo) {
        final Grid grid = new Grid();
        grid.setSizeFull();
        grid.setColumnReorderingAllowed(true);
        grid.setResponsive(true);

        final BeanItemContainer dataSource = new BeanItemContainer(CardEntity.class, cardsRepo.findAll());
        grid.setContainerDataSource(dataSource);
        grid.setColumns("setName", "cardIdentifier", "name", "type", "cost", "attack", "health");
        grid.setColumnOrder("setName");
        
        return grid;
    }

}
