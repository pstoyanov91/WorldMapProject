package airports;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class HandleAirportData {
	
	//List of airports 
	private List<Airports> airports = new LinkedList<Airports>();
	
	
	// Read airports from file and loads them to a list
	public List<Airports> loadData(String pathToFile){
		
		Scanner fileReader = null;
		
		try{
			//System.out.println(new java.io.File("").getAbsolutePath());
			
			fileReader = new Scanner(new File(pathToFile));
			String currLine = null;
			
			while(fileReader.hasNextLine()){
				currLine = fileReader.nextLine();
				String[] columns = currLine.split(",");
				
				//System.out.println(Arrays.deepToString(columns));
				int id = Integer.parseInt(columns[0]);
				String city = columns[2].replace("\"", "");
				String country = columns[3].replace("\"", "");
				String code = columns[4].replace("\"", "");
				
				//System.out.println(id+" " + city + " - " + country + " - " + " " + code );
				
				//Add airport to the list
				Airports airport = new Airports(id, city, country, code);
				this.airports.add(airport);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(fileReader!=null){
				fileReader.close();
			}
		}
		
		return this.airports;
	}
	
	/*
	 * Linear search algorithm for finding 
	 * 
	 * city code in array of airports
	 * 
	 * */
	
	public Airports findAirportLinear(String city){
		
		for(Airports airport : this.airports){
			
			String airportCity = airport.getCity();
			
			if(airportCity.equals(city)){
				return airport;
			}
		}
		
		return null;
	}
	
	/*
	 * Binary search algorithm for finding city code in array of airports.
	 * 
	 * IMPORTANT: The array has to be sorted!
	 */
	public Airports findAirportBinary(String city){
		int low = 0;
		int high = this.airports.size()-1;
		int mid;
		
		while (low<=high) {
			
			/*
			 * First we subtract low from high to get the range and then we add it to low.
			 * So this means (high - low) is the range
			 * and then we add it to low to get the position of this range
			 * 
			 * We do this instead of (high+low)/2 so that we can escape overflowing 
			 * when the array is really big and adding high + low could exceed the integer value range.
			 */
			mid = low + (high - low)/2;
			
			// Find if a the city we're trying to find is alphabetically less.
			int compare = city.compareTo(this.airports.get(mid).getCity());
			
			if(compare < 0){
				high = mid-1;
			}else if(compare > 0){
				low = mid+1;
			}else{
				return this.airports.get(mid);
			}
		}
		
		return null;
		
	}
	
	//Getter
	public List<Airports> getAirports(){
		return this.airports;
	}

}
