
package ca.willmadruga.stuffs.ui;

import ca.willmadruga.stuffs.persistence.CardEntity;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

/**
 * Created by wmadruga on 2015-11-23.
 */
public class CardWindow extends Window {

    final CardEntity cardContent;

    public CardWindow(final CardEntity card) {

        super(card.getName());
        this.cardContent = card;

        final FormLayout form = new FormLayout();
        setContent(form);

        PropertysetItem item = new PropertysetItem();

        item.addItemProperty("cardIdentifier", new ObjectProperty(card.getCardIdentifier()));


        FieldGroup binder = new FieldGroup(item);
        form.addComponent(binder.buildAndBind("Card ID", "cardIdentifier"));
        binder.bindMemberFields(form);

    }
}
