package com.mitchell.niall.randomSurname;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NameGenerator {
	public static final String PATH = "bin/com/mitchell/niall/randomSurname/data";
	public static final String FN = "Names_Census2010.csv";
	
	public static final Random RAND = new Random(System.nanoTime());

	ArrayList<Name> _names = null;
	Tree _valueTree = null;
	static int _high = 0;
	
	public static int getH() {
		return _high;
	}
	
	public NameGenerator() {
		System.out.println("NameGenerator() constructor");
		long freeMemStart = Runtime.getRuntime().freeMemory();
		System.out.println("\tFree Memory: " + freeMemStart);
		long start = System.nanoTime();
		_names = load();
		long freeMemLoad = Runtime.getRuntime().freeMemory();
		System.out.println("\tFree Memory after Load: " + freeMemLoad);
		System.out.println("\tList uses " + (freeMemStart - freeMemLoad ));
		System.out.println("NameGenerator contains " + _names.size() + " entries");
		long end = System.nanoTime();
		System.out.println("Loaded in " + (end-start) + " nanoseconds");
		start = System.nanoTime();
		if(verify(_names)) 
			System.out.println("Verified");
		else
			System.out.println("Verification failed");
		end = System.nanoTime();
		System.out.println("Verified in " + (end-start) + " nanoseconds");
		_valueTree = new Tree(_names);
		System.out.println("NameGenerator() constructor exits");
	}
	
	private ArrayList<Name> load() {
		System.out.println("\tNameGenerator().load()");
		ArrayList<Name> names = new ArrayList<>();
		File f = new File(PATH,FN);
		if( f.exists()) {
			System.out.println("\tUsing " + FN);
			if( f.canRead()) {
				System.out.println("\tReading " + FN);
				FileReader fr = null;
				BufferedReader br = null;
				String datum = null;
				try {
					fr = new FileReader(f);
					br = new BufferedReader(fr);
					while((datum = br.readLine()) != null) {
						if( ! datum.startsWith(Name.COMMENT)) {
							names.add(new Name(datum));
						}
					}
					System.out.println("\tClosing " + FN);
					br.close();
					fr.close();
				}catch(FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				}catch(IOException ioe) {
					ioe.printStackTrace();
				}
			} else
				System.err.println("\tCannot read file: " + f.getAbsolutePath());
		} else
			System.err.println("\tFile does not exist: " + f.getAbsolutePath());
		_high = names.get(names.size()-1).get_high();
		System.out.println("\tNameGenerator().load() exits");
		return names;
	}

	private boolean verify(ArrayList<Name> list) {
		System.out.println("\tNameGenerator().verify()");
		int low = 0;
		int high = 0;
		int id = 0;
		for( Name n : list) {
			if( low != n.get_low()) {
				System.out.println("\tLow mismatch");
				System.out.println(n);
				return false;
			}
			high = low + n.get_number();
			low += n.get_number();
			if( high-1 != n._high) {
				System.out.println("\tHigh mismatch");
				System.out.println(n);
				return false;
			}
			if( id++ != n.get_id() ) {
				System.out.println("\tID mismatch");
				System.out.println(n);
				return false;
				
			}
		}
		System.out.println("\tNo low mismatches");
		System.out.println("\tNo high mismatches");
		System.out.println("\tNo ID mismatches");
		System.out.println("\tNameGenerator().verify() exits");

		return true;
	}
	long _time = 0L;
	public long getTime() {
		return _time;
	}
	
	public Name getRandom(int randomIndex) {
		Name result = null;
		long start = System.nanoTime();
		result = _valueTree.get(randomIndex);
		long end = System.nanoTime();
		System.out.println("Found random entry in " + (end-start) + " nanoseconds");
		return result;
		
	}
	
	public Name getRandom() {
		Name result = null;
		long start = System.nanoTime();
		result = _valueTree.get(randomIndex());
		long end = System.nanoTime();
		System.out.println("Found random entry in " + (end-start) + " nanoseconds");
		return result;
	}
	
	
	public int randomIndex() {
		return RAND.nextInt(_high);
	}
/*	
	public String getNameIteratively() {
		long start = System.nanoTime();
		long end = 0L;
		int value = RAND.nextInt(_high);
		for( Name n : _names) {
			if( n.get_low() <= value && value <= n.get_high()) {
				end = System.nanoTime();
//				System.out.println("Found in " + (end-start) + " nanos");
				_time = end-start;
				return n.get_name();
			}
		}
		end = System.nanoTime();
		System.out.println("Failed in " + (end-start) + " nanos");
		end = System.currentTimeMillis();
		
		return "null";
	}
*/
	public Name getNameIteratively(int ri) {
		for( Name n : _names) {
			if( n.get_low() <= ri && ri <= n.get_high()) {
				return n;
			}
		}
		return null;
	}

	public ArrayList<Name> getNames() {
		return _names;
	}
}
