package ca.willmadruga.stuffs.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wmad on 2015-11-21.
 */
@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    @Autowired
    private CardsDataGridPanel dataGrid;


    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout contentCenter = new VerticalLayout();
        contentCenter.setSizeFull();
        contentCenter.addComponent(dataGrid);

        final HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.addComponent(contentCenter);
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);


        setContent(mainLayout);

    }

}
