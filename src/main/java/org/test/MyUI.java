package org.test;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		final VerticalLayout layout = new VerticalLayout();

		final Grid<Item> grid = new Grid<>(Item.class);
		grid.setWidth("100%");
		ListDataProvider<Item> dataProvider = DataProvider.ofCollection(generateItems());
		ConfigurableFilterDataProvider<Item,Void,SerializablePredicate<Item>> filterDataProvider = dataProvider.withConfigurableFilter();

		grid.setDataProvider(filterDataProvider);

		final TextField searchTerm = new TextField();
		searchTerm.setCaption("Enter search term:");
		searchTerm.addValueChangeListener(e -> {
			if (e.getValue().length() < 3 || e.getValue().trim().isEmpty()) {
				// short and empty filters are ignored
				filterDataProvider.setFilter(null);
			} else {
				filterDataProvider.setFilter(item -> item.getName().contains(e.getValue()));
			}
		});
		layout.addComponent(searchTerm);
		
		layout.addComponent(grid);

		setContent(layout);
	}

	private Collection<Item> generateItems() {
		return Arrays.asList(new Item("foo1"), new Item("foo2"), new Item("bar3"), new Item("bar4"));
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
