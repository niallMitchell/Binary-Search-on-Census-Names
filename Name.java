package com.mitchell.niall.randomSurname;

import java.util.StringTokenizer;

public class Name {
	public static final String DELIM = ",";
	public static final String COMMENT = "#";
	
	static int SUM = 0;
	static int ID = 0;
	
	String _name = null;
	int _rank = 0;
	int _id = -1;
	int _number = 0;
	int _low = -1;
	int _high = -1;

	public Name(String datum) {
		if( ! datum.startsWith(COMMENT)) {
			_id = ID++;
			StringTokenizer tkz = new StringTokenizer(datum, DELIM);
			_name = tkz.nextToken();
			try {
				_rank = Integer.parseInt(tkz.nextToken());
				_number = Integer.parseInt(tkz.nextToken());
			}catch(NumberFormatException nfe) {
				System.out.println("NFE at line " + (_id+1) + "\"" + datum + "\"");
			}
			_low = SUM;
			SUM += _number;
			_high = _low + _number-1; 
		}
	}

	public String get_name() {
		return _name;
	}

	public int get_rank() {
		return _rank;
	}

	public int get_id() {
		return _id;
	}

	public int get_number() {
		return _number;
	}

	public int get_low() {
		return _low;
	}

	public int get_high() {
		return _high;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(_id + "|");
		sb.append(_name + "|");
		sb.append(_number+ "|");
		sb.append(_low + "|");
		sb.append(_high + "|");
		return sb.toString();
	}

}

