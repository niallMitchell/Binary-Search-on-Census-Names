package com.mitchell.niall.randomSurname;

public class Node {
	static int IDs = 0;
	
	Name _datum = null;
	Node _left = null;
	Node _right = null;
	int _uid = -1;
	
	public Node(Name datum) {
		_datum = datum;
		_uid = IDs++;
	}
}