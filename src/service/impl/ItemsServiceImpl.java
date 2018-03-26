package service.impl;

import java.util.List;

import mapper.ItemsMapper;
import mapper.ItemsMapperCustom;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import po.Items;
import po.ItemsCustom;
import po.ItemsQueryVo;
import service.ItemsService;

public class ItemsServiceImpl implements ItemsService{
@Autowired
private ItemsMapper itemsMapper;
@Autowired
private ItemsMapperCustom itemsMapperCustom;
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception {
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}
	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		Items items=itemsMapper.selectByPrimaryKey(id);
		ItemsCustom itemsCustom=new ItemsCustom();
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
		
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom)
			throws Exception {
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}
	@Override
	public void batchDeletes(Integer[] items_id) throws Exception {
		itemsMapperCustom.batchDeletes(items_id);
		
	}
		


}
