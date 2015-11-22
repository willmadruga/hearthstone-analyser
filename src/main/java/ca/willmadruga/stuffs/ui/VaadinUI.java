package ca.willmadruga.stuffs.ui;

import ca.willmadruga.stuffs.persistence.CardEntity;
import ca.willmadruga.stuffs.persistence.Repos.CardsRepo;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wmad on 2015-11-21.
 */
@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    @Autowired
    private CardsRepo cardsRepo;

    private Grid cardsGrid;

    @Override
    protected void init(VaadinRequest request) {

        cardsGrid = new Grid();
        cardsGrid.setHeight("100%");
        cardsGrid.setWidth("100%");
        cardsGrid.setColumnReorderingAllowed(true);
        cardsGrid.setResponsive(true);
        cardsGrid.setContainerDataSource(new BeanItemContainer(CardEntity.class, cardsRepo.findAll()));
        setContent(cardsGrid);
    }
}
