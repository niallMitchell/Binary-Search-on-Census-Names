package com.mitchell.niall.randomSurname;

import java.util.ArrayList;


public class Tree {
	
	static Node _root = null;
	ArrayList<Name> _list = null;
	long _usedMem = 0L;

	public Tree(ArrayList<Name> data) {
		System.out.print("Tree() Constructor");
		long freeMemStart = Runtime.getRuntime().freeMemory();
		System.out.println("\tFree Memory: " + freeMemStart);
		long start = System.nanoTime();
		_list = data;
		int lowI = 0;
		int highI =data.size()-1;
		int midIndex = getMidIndex(lowI,highI,data);
		_root = new Node(data.get(midIndex));
		_root._left = satB(lowI,midIndex-1,data);
		_root._right = satB(midIndex+1,highI,data);
		long freeMemTree = Runtime.getRuntime().freeMemory();
		System.out.println("\tFree Memory after Tree() constructor: " + freeMemTree);
		System.out.println("\tTree uses " + (freeMemStart - freeMemTree ));
		_usedMem = (freeMemStart-freeMemTree);
		long end = System.nanoTime();
		System.out.println("Tree() finished in " + (end-start) + " nanoseconds");
		System.out.print("Tree() Constructor exits");
	}
	
	Node satB(int start, int end, ArrayList<Name> list) {
		if( start > end)
			return null;
		int mid = getMidIndex(start,end,list);
		if( mid != -1 ) {
		Node node = new Node(list.get(mid));
		node._left = satB(start,mid-1,list);
		node._right = satB(mid+1,end,list);
		return node;
		}
		return null;
	}
	
	int getMidIndex( int l, int h, ArrayList<Name> list) {
		int lv = list.get(l).get_low();
		int hv = list.get(h).get_high();
		int mv = (hv+lv)/2;
		for( int i = l; i <= h; i++) {
			if( list.get(i).get_low() <= mv && mv <= list.get(i).get_high())
				return i;
		}
		return -1;
	}
	
	void preOrderPrint(Node n) {
		if( n == null)
			return;
		System.out.println(n._uid + "|" + n._datum.get_rank() + "|" + n._datum.get_name());
		preOrderPrint(n._left);
		preOrderPrint(n._right);
	}
	
	public Name get(int rv) {
		return find(_root,rv);
	}
	
	Name preorder(Node n, String name) {
		if( n == null)
			return null;
		if( n._datum.get_name().compareTo(name) == 0)
			return n._datum;
		Name result = preorder(n._left,name);
		if( result != null)
			return result;
		result = preorder(n._right, name);
		return result;
	}
	
	public Name get(String name) {
		if( name == null)
			return null;
		if( name.length() == 0)
			return null;
		return preorder(_root,name);
	}
	
	private Name find(Node n, int rv) {
		if( n._datum.get_low() <= rv && rv <= n._datum.get_high())
			return n._datum;
		Name r = null;
		if( rv < n._datum.get_low())
			return find(n._left,rv);
		return find(n._right,rv);
	}
	

}


