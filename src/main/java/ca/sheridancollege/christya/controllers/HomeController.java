package ca.sheridancollege.christya.controllers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.christya.beans.Bike;
import ca.sheridancollege.christya.beans.Manufacturer;
import ca.sheridancollege.christya.database.DatabaseAccess;

@Controller
public class HomeController {

	@Autowired
	private DatabaseAccess da;

	// main page
	@GetMapping("/")
	public String goHome() {
		return "index";
	}

	// add a bike initial load
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("bike", new Bike());
		model.addAttribute("bikeList", da.getBikes());
		model.addAttribute("manufacturerList", da.getManufacturers());
		return "insert";
	}

	// add a bike post form submit and insert into database SQL statements
	@PostMapping("/postInsert")
	public String postInsert(Model modelAt, @RequestParam int manufacturerID, @RequestParam String model,
			@RequestParam int year, @RequestParam String colour, @RequestParam double price) {
		da.insertBike(manufacturerID, model, year, colour, price);
		modelAt.addAttribute("bike", new Bike());
		modelAt.addAttribute("bikeList", da.getBikes());
		modelAt.addAttribute("manufacturerList", da.getManufacturers());
		return "insert";
	}

	// update a bike initial load
	@GetMapping("/update")
	public String update(Model model) {
		model.addAttribute("bike", new Bike());
		model.addAttribute("bikeList", da.getBikes());
		model.addAttribute("modelList", da.getModels());
		return "update";
	}

	// update a bike post form submit and update into database SQL statements
	@PostMapping("/postUpdate")
	public String postUpdate(Model modelAt, @RequestParam String model, @RequestParam double price) {
		da.updateBike(model, price);
		modelAt.addAttribute("bike", new Bike());
		modelAt.addAttribute("bikeList", da.getBikes());
		modelAt.addAttribute("modelList", da.getModels());
		return "update";
	}

	// delete a bike initial load
	@GetMapping("/secure/delete")
	public String delete(Model model) {
		model.addAttribute("bike", new Bike());
		model.addAttribute("bikeList", da.getBikes());
		return "/secure/delete";
	}
	
	// delete a bike initial load
		@PostMapping("/secure/postDelete")
		public String postDelete(Model model, @RequestParam int bikeID) {
			da.deleteBike(bikeID);
			model.addAttribute("bike", new Bike());
			model.addAttribute("bikeList", da.getBikes());
			//Prompts Error message if record is not found in database
			model.addAttribute("error", da.getError());
			return "/secure/delete";
		}
		
		@GetMapping("/login")
		public String login(){  
			return "login";
		}
		
		@GetMapping("/permission-denied")
		public String permissionDenied(){
			return "/error/permission-denied";
		}

}
