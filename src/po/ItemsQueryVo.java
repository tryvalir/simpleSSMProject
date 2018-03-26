package po;

import java.util.List;

public class ItemsQueryVo {
	//包装类，直接用于包装商品信息而直接用于paramenterType用于sql
	private Items items;
	private ItemsCustom itemsCustom;
	private List<ItemsCustom> itemsList;
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}
	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}

	

}
