package voxels.map;

import com.jme3.math.Vector3f;

public class Coord3 {
	public int x,y,z;
	
	public Coord3(int xx, int yy, int zz) {
		x = xx; y = yy; z = zz;
	}
	
	public Vector3f toVector3f() {
		return new Vector3f(x,y,z);
	}

	public Coord3(int a) { 
		this(a,a,a); 
	}
	
	public Coord3(double _x, double _y, double _z) { 
		this((int) _x, (int) _y,(int) _z); 
	}

	public Coord3 multy(Coord3 other) {
		// make a new coord3 by multiplying this one and 'other' component-wise
	}
	public Coord3 multy(int i) {
		// make a new coord3 by multiplying this one by i (each component)
	}
	public Coord3 divideBy(Coord3 other) {
		// same idea as multy
	}
	public Coord3 divideBy(int i) {
		// same idea as multy
	}
	public Coord3 add(Coord3 other) {
		// same idea as multy		
	}
	public Coord3 minus (Coord3 other) {
		// same idea as multy		
	}
	public boolean greaterThan(Coord3 other) {
		// return true if all components x, y, and z are each respectively
		// greater than other's x, y and z
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other instanceof Coord3) {
			return x == ((Coord3) other).x && y == ((Coord3) other).y && z == ((Coord3) other).z;
		}
		return false;
	}
	
	
}
