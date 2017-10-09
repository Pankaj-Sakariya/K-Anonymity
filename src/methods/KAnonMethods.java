package methods;

import table.Tuple;
import taxonomy.TaxonomyTree;

import java.util.*;

public class KAnonMethods
{
	private double dataLoss = 0;
	private int size = 0;
	private int k = 0;
	private int currentK = 0;
	private ArrayList<Tuple> table, outputTable, baseTable;
	private Tuple headers;

	//Preconditon: 	Valid ArrayList of tuples given as input
	//Postcondtion:	Object initialised
	//Status:		Coded and efficient
	//Written by:	Moten
	public KAnonMethods(ArrayList<Tuple> input, int k)
	{
		headers = input.get(0); //Store the header
		table = input;
		table.remove(0); //Remove the header from the working table
		baseTable = table;
		outputTable = table;
		size = table.size();
		this.k = k;
	}

	//Preconditon: 	methods.KAnonMethods initialised
	//Postcondtion:	K-Anonymised table calculated
	//Status:		
	//Written by:	Moten
	public void makeKAnon() //Main algorithm
	{
		currentK = evaluateKAnon(table);

		if (currentK == k) //Check to see if table is already K-Anonymous
			return;

		assignTaxonomy(table); //Assign the taxonomy trees for all tuples

		table = maximize(table); //Maximize the value of all tuples
		/*
		currentK = evaluateKAnon(outputTable);
		while (currentK >= k) //Keep improving data as long as the next reduction is still K-Anonymous
		{
			table = outputTable; //Table is now the reduced (and known K-Anonymous table)
			outputTable = reduceEC(table); //Further improve all tuples
			currentK = evaluateKAnon(outputTable);
		}
		outputTable = table; //Set the output table to be equal to the last known good value
		*/
	}

	//Preconditon: 	methods.KAnonMethods initialised
	//Postcondtion:	Output table returned
	//Status:		Coded and efficient
	//Written by:	Moten
	public ArrayList<Tuple> getOutput() //If we want to re add the headers then do it here
	{
		return outputTable;
	}

	//Preconditon: 	methods.KAnonMethods initialised
	//Postcondtion:	Current K value of table returned
	//Status:		Work in Progress - Efficient at the moment but potentially inaccurate (to be improved)
	//Written by:	Moten
	private int evaluateKAnon(ArrayList<Tuple> input) // ~0.15 second runtime for 100000 records on dual core i5 @2.7Ghz
	{
		int min = 10000000;
		ArrayList<Integer> values = new ArrayList<Integer>();
		ArrayList<Integer> occurence = new ArrayList<Integer>();
		for (int i = 0; i < input.size(); i++)
		{
			Integer temp = input.get(i).getHash(); 
			int location = values.indexOf(temp);
			if (location == -1)
			{
				values.add(temp);
				occurence.add(1);
			}
			else
				occurence.set(location, occurence.get(location)+1);
			
		}

		for (int i = 0; i < occurence.size(); i++)
		{
			if (min > occurence.get(i))
				min = occurence.get(i);
		}

		return min;
	}

	//Preconditon: 	methods.KAnonMethods initialised
	//Postcondtion:	Current K value of output table returned
	//Status:		Coded and efficient
	//Written by:	Moten
	public int getCurrentK()
	{
		if (currentK == 0)
			currentK = evaluateKAnon(outputTable);
		return currentK;
	}

	//Preconditon: 	evaluateDataLoss() run at least once
	//Postcondtion:	Data loss value returned as a percentage
	//Status:		Coded and efficient
	//Written by:	Moten
	public double getDataLoss()
	{
		evaluateDataLoss();
		return dataLoss;
	}

	//Preconditon: 	methods.KAnonMethods initialised
	//Postcondtion:	Specific tuple value returned
	//Status:		Coded and efficient
	//Written by:	Moten
	public Tuple getTuple(int i)
	{
		return table.get(i);
	}

	//Preconditon: 	makeKAnon() run at least once
	//Postcondtion:	Data loss calculated and stored in object
	//Status:		Incomplete
	//Written by:	
	private void evaluateDataLoss() 
	{
		dataLoss = 0;
	}

	//Preconditon: 	methods.KAnonMethods initialised
	//Postcondtion:	All tuples in table improved in accuracy by 1 step
	//Status:		Incomplete
	//Written by:	
	private ArrayList<Tuple> reduceEC(ArrayList<Tuple> input)
	{

		return table;
	}

	//Preconditon: 	methods.KAnonMethods initialised
	//Postcondtion:	All tuples in table set to maximum anonymity
	//Status:		Incomplete
	//Written by:	
	private ArrayList<Tuple> maximize(ArrayList<Tuple> input)
	{
		return null;
	}

	//Preconditon: 	methods.KAnonMethods initialised
	//Postcondtion:	Taxonomy trees are assigned to all tuples
	//Status:		Incomplete
	//Written by:	
	private ArrayList<Tuple> assignTaxonomy(ArrayList<Tuple> input)
	{
		ArrayList<TaxonomyTree> trees = importTrees();

		return null;
	} 

	//Preconditon: 	methods.KAnonMethods initialised
	//Postcondtion:	Taxonomy trees are imported from file and returned
	//Status:		Incomplete
	//Written by:	
	private ArrayList<TaxonomyTree> importTrees()
	{
		Scanner console;
		ArrayList<TaxonomyTree> output = new ArrayList<TaxonomyTree>(); //Output arraylist of taxonomy trees. Each one relates to a field


		return output;
	}

	//Preconditon: 	methods.KAnonMethods initialised
	//Postcondtion:	Average run time of method printed to console
	//Status:		Coded
	//Written by:	Moten
	private void testTime()
	{
		long millis;
		long totalTime = 0;
		for (int i = 0; i < 100; i++)
		{
			millis = System.currentTimeMillis(); // Start run timer

			evaluateKAnon(table); //METHOD TO BE TESTED

			totalTime += System.currentTimeMillis()-millis;
		}
		totalTime = totalTime/100;
		System.out.println("Average run time of evaluateKAnon: "+totalTime);
	}
}