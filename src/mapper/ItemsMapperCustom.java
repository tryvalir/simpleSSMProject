package mapper;

import java.util.List;

import po.ItemsCustom;
import po.ItemsQueryVo;

public interface ItemsMapperCustom {
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	public ItemsCustom findItemsById(Integer id) throws Exception;
	public void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;
	public void batchDeletes(Integer[] items_id)throws Exception;
	public void batchUpdate(List<ItemsCustom> itemsList)throws Exception;
}
