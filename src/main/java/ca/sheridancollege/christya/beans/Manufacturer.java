package ca.sheridancollege.christya.beans;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer implements Serializable{
	
	private int manufacturerID;
	private String manufacturer;
}
