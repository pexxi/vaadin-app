package org.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        
        final VerticalLayout innerLayout = new VerticalLayout();
        innerLayout.setWidth("10%");
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Search");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        
        innerLayout.addComponents(name, button);
        
        final VerticalLayout innerLayout2 = new VerticalLayout();
        innerLayout2.setSizeFull();
        
        final TextField searchTerm = new TextField();
        name.setCaption("Enter search term:");
        innerLayout2.addComponent(searchTerm);

        Grid<Item> grid = new Grid<>(Item.class);
        grid.setWidth("100%");
        grid.setDataProvider(new ItemService());
        innerLayout2.addComponent(grid);

        layout.addComponents(innerLayout, innerLayout2);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
