package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controller.validation.ValidGroup1;
import controller.validation.ValidGroup2;
import po.ItemsCustom;
import po.ItemsQueryVo;
import service.ItemsService;

@Controller
@RequestMapping("/items")
public class ItemsController {
	@Autowired
	private ItemsService itemsService;
	@RequestMapping (value="/queryItems",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView queryItems(HttpServletRequest requst,ItemsQueryVo itemsQueryVo) throws Exception {
		List<ItemsCustom> itemsList=itemsService.findItemsList(itemsQueryVo);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/itemsList");	
		return modelAndView;
	}
@RequestMapping(value="/editItems",method={RequestMethod.POST,RequestMethod.GET})
public String editItems(Model model,Integer id) throws Exception{
	ItemsCustom itemsCustom=itemsService.findItemsById(id);
	model.addAttribute("itemsCustom", itemsCustom);
	return "items/editItems";
	
} 
@RequestMapping(value="/editItemsSubmit",method={RequestMethod.POST,RequestMethod.GET})
public String editItemsSubmit(@Validated(value = {ValidGroup1.class,ValidGroup2.class}) ItemsCustom itemsCustom,BindingResult bindingResult, Model model) throws Exception{
	//获取校验错误信息
	if(bindingResult.hasErrors()){
	    // 输出错误信息
	    List<ObjectError> allErrors = bindingResult.getAllErrors();
        //在console输出错误信息
	    for (ObjectError objectError :allErrors){
	        // 输出错误信息
	        System.out.println(objectError.getDefaultMessage());
	    }
	    // 将错误信息传到页面
	    model.addAttribute("allErrors", allErrors);

	    //可以直接使用model将提交pojo回显到页面
	    model.addAttribute("itemsCustom", itemsCustom);

	    // 出错重新到商品修改页面
	    return "items/editItems";
	}
	if(itemsCustom!=null){
//		if(itemsCustom.getCreatetime()!=null&&itemsCustom.getName()!=null&&itemsCustom.getPrice()!=null){
			itemsService.updateItems(itemsCustom.getId(),itemsCustom);
			return "success";
//		}
//		else{
//			return "error";
//		}
	}
	else{
		
		return "error";
	}
		
}
@RequestMapping(value="/deleteItems",method={RequestMethod.POST,RequestMethod.GET})
public String deleteItems(Integer[] items_id) throws Exception{
	if(items_id!=null){
		itemsService.batchDeletes(items_id);
	}
	return "forward:queryItems.action";
}

@RequestMapping (value="/editItemsQuery",method={RequestMethod.POST,RequestMethod.GET})
public ModelAndView editItemsQuery(HttpServletRequest requst,ItemsQueryVo itemsQueryVo) throws Exception {
	List<ItemsCustom> itemsList=itemsService.findItemsList(itemsQueryVo);
	ModelAndView modelAndView=new ModelAndView();
	modelAndView.addObject("itemsList", itemsList);
	modelAndView.setViewName("items/editItemsQuery");	
	return modelAndView;
}

	@SuppressWarnings("unused")
	@RequestMapping(value="/editItemsAllSubmit",method={RequestMethod.POST,RequestMethod.GET})
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo, Model model) throws Exception{
		List<ItemsCustom> itemsList=itemsQueryVo.getItemsList();
			if(itemsQueryVo!=null){
				 for(int i=0;i<itemsList.size();i++){
		        	 ItemsCustom itemsCustom=itemsList.get(i);
		        	 Integer id=itemsCustom.getId();
		        	 if(id==null||itemsCustom.getCreatetime()==null||itemsCustom.getName()==null){
		        		 return "error";
		        	 }
		        	  itemsService.updateItems(id,itemsCustom);
		         }
				 return "success";
				}
			else return "error"; 
		}
}
