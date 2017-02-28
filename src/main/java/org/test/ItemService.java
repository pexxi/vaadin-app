package org.test;

import java.util.Collection;

import com.vaadin.data.provider.ListDataProvider;

public class ItemService<T> extends ListDataProvider<T> {

	public ItemService(Collection<T> items) {
		super(items);
	}

}
