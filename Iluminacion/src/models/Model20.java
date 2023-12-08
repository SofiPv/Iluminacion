package models;

import static com.jogamp.opengl.GL.GL_FRONT;
import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_EMISSION;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SHININESS;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SPECULAR;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;

public class Model20 extends FigureModel {

	public Model20() {
		super();
		this.name = "Icosahedron";
	}

	public Model20(float x, float y, float z, float h, Color strokeColor, Color ambientColor, Color difuseColor, Color specularColor, int shininess, Color emissionColor) {
		super("Icosahedron", x, y, z, h, strokeColor, ambientColor, difuseColor, specularColor, shininess, emissionColor);
	}

	public Model20(float x, float y, float z, float h, Color ambientColor, Color difuseColor, Color specularColor, int shininess, Color emissionColor) {
		super("Icosahedron", x, y, z, h, ambientColor, difuseColor, specularColor, shininess, emissionColor);
	}

	public Model20(float x, float y, float z, Color strokeColor, Color ambientColor, Color difuseColor, Color specularColor, int shininess, Color emissionColor) {
		super("Icosahedron", x, y, z, strokeColor, ambientColor, difuseColor, specularColor, shininess, emissionColor);
	}

	public Model20(float x, float y, float z, Color ambientColor, Color difuseColor, Color specularColor, int shininess, Color emissionColor) {
		super("Icosahedron", x, y, z, ambientColor, difuseColor, specularColor, shininess, emissionColor);
	}

	@Override
	public void draw(GL2 gl, GLUT glut) {
		super.draw(gl, glut);

		// Draw stoke if selected
		if (this.isSelected()) {
			gl.glPushMatrix();
			{
				gl.glMaterialfv(GL_FRONT, GL_AMBIENT, CambiarColor.convertToFME(strokeColor), 0);
				gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, CambiarColor.convertToFME(strokeColor), 0);
				gl.glMaterialfv(GL_FRONT, GL_SPECULAR, CambiarColor.convertToFME(strokeColor), 0);
				gl.glMateriali(GL_FRONT, GL_SHININESS, shininess);
				gl.glMaterialfv(GL_FRONT, GL_EMISSION, CambiarColor.convertToFME(strokeColor), 0);

				gl.glTranslatef(x, y, z);
				gl.glLineWidth(5);
				glut.glutWireIcosahedron();
			}
			gl.glPopMatrix();
		}

		// Configure material properties
		gl.glPushMatrix();
		{
			gl.glMaterialfv(GL_FRONT, GL_AMBIENT, CambiarColor.convertToFME(ambientColor), 0);
			gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, CambiarColor.convertToFME(difuseColor), 0);
			gl.glMaterialfv(GL_FRONT, GL_SPECULAR, CambiarColor.convertToFME(specularColor), 0);
			gl.glMateriali(GL_FRONT, GL_SHININESS, shininess);
			gl.glMaterialfv(GL_FRONT, GL_EMISSION, CambiarColor.convertToFME(emissionColor), 0);

			gl.glTranslatef(x, y, z);
			glut.glutSolidIcosahedron();
		}
		gl.glPopMatrix();

	}

}
