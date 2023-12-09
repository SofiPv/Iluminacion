package test;

import static com.jogamp.opengl.fixedfunc.GLLightingFunc.*;
import java.awt.Color;
import java.util.Arrays;
import models.GlLight;
import models.ModeloRedondo;


public class Main {

	public static void main(String[] args) {

		ModeloRedondo sp = new ModeloRedondo(0, 0, 0, Color.magenta, Color.yellow, Color.yellow, 10, Color.pink);
		ModeloRedondo sp2 = new ModeloRedondo(0, 0, 0, Color.magenta, Color.yellow, Color.yellow, 10, Color.pink);

		sp.setLight(GL_LIGHT0);
		sp2.setLight(GL_LIGHT1);
		sp.setLight(0);

		System.out.println(sp.isLight());
		System.out.println(sp2.isLight());

		System.out.println(Arrays.toString(GlLight.values()));

	}

}
