package org.test;

import java.util.Arrays;
import java.util.Collection;

import com.vaadin.data.provider.ListDataProvider;

public class ItemService extends ListDataProvider<Item> {

	public ItemService(Collection<Item> items) {
		super(items);
	}

	public ItemService() {
		super(Arrays.asList(new Item("test1"), new Item("test2"), new Item("test3")));
	}

}
