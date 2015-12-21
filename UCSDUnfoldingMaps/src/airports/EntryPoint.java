package airports;


public class EntryPoint {
	
	public static void main(String[] args) {
		
		final long startTime = System.nanoTime();
		
		HandleAirportData airportData = new HandleAirportData();
		airportData.loadData("data/airports.dat");

		final long endTime = System.nanoTime();
		
		System.out.println("Loading airports time: " + (endTime - startTime) + "ns "  + airportData.getAirports().size());
		
		System.out.println(airportData.getElement(airportData.getAirports().size()-1));
		
		// -----------------------------------------------------------------------------------------------------------
		final long startTime1 = System.nanoTime();
		
		 /*Airports airport = airportData.findAirportLinear("Plovdiv");
		 if(airport!=null){
			 System.out.println( airport.getId() + " Code: " + airport.getCode3());
		 }
		 
		*/
		airportData.sortByCitySelectionAlgorithm();
		final long endTime1 = System.nanoTime();
		
		System.out.println("Sort time: " + (endTime1 - startTime1) + "ns" );
		
		//---------------------------------------------------------------------------------------------------------------
		
		/*final long startTime2 = System.nanoTime();
		
		Airports airport2 = airportData.findAirportBinary("Plovdiv");
		 
		 if(airport2!=null){
			 System.out.println( airport2.getId() + " Code: " + airport2.getCode3());
		 }
		 
		
		final long endTime2 = System.nanoTime();
		
		System.out.println("Binary search time: " + (endTime2 - startTime2) + "ns" );
		*/
		//------------------------------------------------------------------------------------------------------------------
		
		
		//airportData.writeDataToFile("data/aiportsOut.txt");
		System.out.println("Done!");
	}
}
