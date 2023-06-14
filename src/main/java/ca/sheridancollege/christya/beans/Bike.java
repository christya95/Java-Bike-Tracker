package ca.sheridancollege.christya.beans;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bike implements Serializable{
	
	private int bikeID;
	private int manufacturerID;
	private String model;
	private int year;
	private String colour;
	private double price;
}
