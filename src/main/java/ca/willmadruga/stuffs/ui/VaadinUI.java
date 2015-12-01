
package ca.willmadruga.stuffs.ui;

import com.vaadin.ui.Label;
import org.springframework.beans.factory.annotation.Autowired;

import ca.willmadruga.stuffs.helpers.JsonImporter;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by wmad on 2015-11-21.
 */
@SpringUI
@Theme("valo")
// @PreserveOnRefresh
public class VaadinUI extends UI {

    @Autowired
    private CardsDataGridPanel dataGrid;

    @Autowired
    private JsonImporter jsonImporter;

    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout content = new VerticalLayout();
        final HorizontalLayout bottom = new HorizontalLayout();
        final MenuBar menubar = new MenuBar();

        setContent(content);

        content.addComponent(menubar);
        content.addComponent(new Label(""));
        content.addComponent(bottom);

        bottom.addComponent(dataGrid);
        bottom.setSizeFull();

        createMenuActions(content, menubar);

    }

    private void createMenuActions(final VerticalLayout content, final MenuBar menubar) {

        final MenuBar.MenuItem reloadJsonMenuItem = menubar.addItem("(Re)load JSON", null, null);
        reloadJsonMenuItem.setCommand(new MenuBar.Command() {

            @Override
            public void menuSelected(final MenuBar.MenuItem menuItem) {
                try {

                    // jsonImporter.importData();
                    Notification.show("Data (re)loaded", Notification.Type.TRAY_NOTIFICATION);
                    content.removeComponent(dataGrid);
                    dataGrid.reloadData();

                } catch (Exception e) {
                    Notification.show("Unable to load json.", Notification.Type.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });

    }

}
