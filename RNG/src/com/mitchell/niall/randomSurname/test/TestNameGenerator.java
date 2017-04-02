package com.mitchell.niall.randomSurname.test;

import com.mitchell.niall.randomSurname.Name;
import com.mitchell.niall.randomSurname.NameGenerator;

public class TestNameGenerator {

	public static void main(String[] args) {
		NameGenerator generator = new NameGenerator();
		Name name = generator.getRandom();
		System.out.println("\t" + name);
		name = generator.getRandom();
		Name name2 = null;
		System.out.println("\t" + name);
		long start = 0L;
		long end = 0L;
		long binTime = 0L;
		long itTime = 0L;
		int ri = 0;
		int RUNS = 1000;
		long[][] times = new long[RUNS][2];
		for( int i = 0; i < 100; i++) {
			ri = generator.randomIndex();
			System.out.println("Run " + i + "\tRandomIndex = " + ri);
			start = System.nanoTime();
			name = generator.getRandom(ri);
			end = System.nanoTime();
			binTime = end-start;
			start = System.nanoTime();
			name2 = generator.getNameIteratively(ri);
			end = System.nanoTime();
			itTime = end - start;
			System.out.println("Binary: " + binTime + "\titerative: "+ itTime + "\t" + name + "\t" + name2);
			times[i][0] = binTime;
			times[i][1] = itTime;
		}
	}

}
