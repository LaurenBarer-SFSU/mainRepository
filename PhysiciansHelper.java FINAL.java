import java.util.*;

import java.io.*;

public class PhysiciansHelper {


	   static Scanner scan = new Scanner(System.in);
	  private Map<String, List<String>> symptomChecker;

	public PhysiciansHelper()   {
		symptomChecker = new TreeMap<String, List<String>>();
	}
	public void processDatafile()  throws FileNotFoundException  {
		System.out.println("Enter Filename : ");

		String filename = scan.nextLine();
		Scanner viewer = new Scanner(new File(filename));



		            while(viewer.hasNextLine()) {

		            	String dash = viewer.nextLine().toLowerCase().trim();

			if(!dash.isEmpty()) {

				Scanner audit = new Scanner(dash);
				audit.useDelimiter(":");
				String diseases = audit.next().trim();


				audit.useDelimiter(",");

				while(audit.hasNext()) {
					String symptoms = audit.next().replaceAll("[^A-Za-z ]", "").trim();

					if(!symptomChecker.containsKey(symptoms)) {
						symptomChecker.put(symptoms, new ArrayList<>());
					}
					symptomChecker.get(symptoms).add(diseases.trim());
				}
			}
		}

		for(String symptoms: symptomChecker.keySet()) {
			System.out.println(symptoms + "=" + Arrays.toString(symptomChecker.get(symptoms).toArray()));
		}
	}
	public void processSymptoms()   {

		System.out.println(" ===============================================");

		System.out.println("symptomChecker map:");

		for(String symptoms: symptomChecker.keySet()) {
			System.out.println(symptoms);
		}
		System.out.println(" ===============================================");
		ArrayList<String> patientSymptoms = new ArrayList<>();
		System.out.println("Enter symptoms: ");

		String userSymptoms = scan.nextLine();
		Scanner audit = new Scanner(userSymptoms.toLowerCase());

		audit.useDelimiter(",");

		while(audit.hasNext()) {
			String sympt = audit.next().trim();

			if(!symptomChecker.keySet().contains(sympt)) {
				System.out.println("=>invalid symptom:" + sympt);

			     }
			else if(patientSymptoms.contains(sympt)) {
				      System.out.println("=>duplicate symptom:" + sympt);
			     }

			else {
				    patientSymptoms.add(sympt);
			}
		}
		System.out.println("==============================================");
		System.out.println("Patient Symptoms list: " + Arrays.toString(patientSymptoms.toArray()));

		int maximumCount = 0;
		HashMap<String, Integer> illnessFreq = new HashMap<>();

		for(String sympt: patientSymptoms) {

			for(String disease: symptomChecker.get(sympt)) { if(!illnessFreq.containsKey(disease)) {

				illnessFreq.put(disease, 0);

			}

				illnessFreq.put(disease, illnessFreq.get(disease) + 1);

				if(maximumCount < illnessFreq.get(disease)) {

					       maximumCount = illnessFreq.get(disease);
				}
			}
		}
		System.out.println("Possible illnesses that matches any symptom are:");

		    for(int i=1; i<=maximumCount; i++) {
			    System.out.println("===> Disease(s) match " + i + " symptom(s)");

			    for(String disea: illnessFreq.keySet()) {
				if(illnessFreq.get(disea) == i) {
					System.out.println(disea);
				}
			}
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		PhysiciansHelper lookup = new PhysiciansHelper();
		lookup.processDatafile();
		lookup.processSymptoms();
	}
}
