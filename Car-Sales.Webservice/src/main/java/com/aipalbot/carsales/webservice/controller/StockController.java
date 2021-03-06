package com.aipalbot.carsales.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aipalbot.carsales.webservice.constant.CommonConstant;
import com.aipalbot.carsales.webservice.entity.StockEntity;
import com.aipalbot.carsales.webservice.model.CarItem;
import com.aipalbot.carsales.webservice.request.CarRequest;
import com.aipalbot.carsales.webservice.response.CarResponse;
import com.aipalbot.carsales.webservice.service.CarService;


@CrossOrigin(origins = CommonConstant.REACT_APP_URL)
@RestController
@RequestMapping("stock/")
public class StockController {

	@Autowired
	CarService carService;
	
	
	//stock/new
	@PostMapping("new")
	public String addNewItem(@RequestBody CarRequest request) {
		//extract needed attributes
		CarItem item = new CarItem(request.getVin());
		item.setMilleage(request.getMilleage());
		item.setStatus(request.isStatus());
		item.setPrice(request.getPrice());
		item.setImgUrl(request.getImgUrl());
		item.setEngine(request.getEngine());
		item.setYear(request.getYear());
		item.setMaker(request.getMaker());
	
	
		
		String response = carService.addItem(item);
		
		return response;
	}
	
	//stock/update
	@PutMapping("update")
	public String updateItem(@RequestBody CarRequest request) {
	
	
		
		return request.getModelName() + " is successfully updated";
	}
	
	
	//stock/item/24324234
	@GetMapping("item/{vin}")
	public CarResponse  getItem(@PathVariable String vin ) {
		CarItem carItem = carService.getCar(vin);
		CarResponse response = new CarResponse();
		response.setPrice(carItem.getPrice());
		response.setYear(carItem.getYear());
		response.setMaker(carItem.getMaker());
		response.setVin(vin);
		
		return response;
	}
	
	//stock/item?modelName=camry&maker=toyota
	@GetMapping("item")
	public CarResponse  getItemByModelMaker(@RequestParam(value = "model") String modelName, 
			@RequestParam String maker) {
		
		return null;
	}
	
	
	@GetMapping("items")
	public List<CarItem> getItems(){
		List<CarItem> items = carService.retrieveItems();
		
		return items;
	}
	@GetMapping("items/all")
	public List<StockEntity> getAllItems(){
		List<StockEntity> items = carService.retrieveCarEntities();
		
		return items;
	}
	

}
