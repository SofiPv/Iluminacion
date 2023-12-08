package models;

public class Puntos3D extends Objeto3D {

	public Puntos3D(String name, float x, float y, float z, float h) {
		super(name, x, y, z, h);
	}

	public Puntos3D(String name, float x, float y, float z) {
		super(name, x, y, z);
	}

	@Override
	public String toString() {
		return name + super.toString();
	}

}
