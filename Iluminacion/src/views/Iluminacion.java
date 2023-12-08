package views;

import com.jogamp.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import models.ModeloCamara;
import models.ModeloCubo;
import models.Modelo12;
import models.ModelosLista;
import models.FigureModel;
import models.Model20;
import models.Modelo8;
import models.ModeloRedondo;


public class Iluminacion extends JFrame implements Runnable, WindowListener {

	@Override
        
	public void run() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 700);
		setLocationRelativeTo(null);
		setFocusable(true);
		setVisible(true);

		animator.start();
	}

	public Iluminacion() {
		initComponents();
	}

	private void initComponents() {

		camara = new ModeloCamara(2, 2, 8, 45, 0, 0.1f, 20, 0, 0, 0, 0, 1, 0);

		leftPanel = new JPanel();
                leftPanel.setBackground(new java.awt.Color(168,116,64));
		centerPanel = new JPanel();
                centerPanel.setBackground(new java.awt.Color(168,116,64));
		rightPanel = new JPanel();
                rightPanel.setBackground(new java.awt.Color(168,116,64));
		leftPanelTitle = new JLabel();

		buttonGroup = new ButtonGroup();
		btnEsfera = new JButton();
		btnCubo = new JButton();
		btnDodecahedro = new JButton();
		btnIcosahedro = new JButton();
		btnOctahedro = new JButton();
		btnAyuda = new JButton();

		canvasPanel = new PanelIluminacion(camara);
		animator = new FPSAnimator(canvasPanel, PanelIluminacion.FPS, true);
		scrollLogger = new JScrollPane();
		logger = new JTextArea();

		rightPanelTitle = new JLabel();
		scrollFiguras = new JScrollPane();
		listFiguras = new JList<>(new ModelosLista());

		addWindowListener(this);

		setTitle("Iluminación");
		setResizable(false);
		setLayout(new BorderLayout());

		KeyboardFocusManager.
						getCurrentKeyboardFocusManager().
						addPropertyChangeListener("focusOwner", (PropertyChangeEvent e) -> {
							//System.out.println(e.toString());
							requestFocusInWindow();
						});

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				var keyCode = e.getKeyCode();
				var camMov = 0.1f;
				var selectedModel = listFiguras.getSelectedValue();

				if (e.isShiftDown()) {
					// Camara movement
					switch (keyCode) {
						case VK_W -> {
							camara.addY(camMov);
						}
						case VK_UP -> {
							camara.addFov(camMov);
						}
						case VK_S -> {
							camara.addY(-camMov);
						}
						case VK_DOWN -> {
							camara.addFov(-camMov);
						}
						case VK_D -> {
							camara.addX(camMov);
						}
						case VK_A -> {
							camara.addX(-camMov);
						}
						case VK_Q -> {
							camara.addZ(camMov);
						}
						case VK_E -> {
							camara.addZ(-camMov);
						}
					}

				} else if (e.isControlDown()) {

					// Point movement (Head movement)
					switch (keyCode) {
						case VK_W -> {
							camara.addYPoint(camMov);
						}
						case VK_UP -> {
							camara.addFar(camMov);
						}
						case VK_S -> {
							camara.addYPoint(-camMov);
						}
						case VK_DOWN -> {
							camara.addFar(-camMov);
						}
						case VK_D -> {
							camara.addXPoint(camMov);
						}
						case VK_A -> {
							camara.addXPoint(-camMov);
						}
						case VK_Q -> {
							camara.addZPoint(camMov);
						}
						case VK_E -> {
							camara.addZPoint(-camMov);
						}
					}

				} else {

					if (selectedModel == null) {
						return;
					}

					// Seleccionar movimiento de la figura
					switch (keyCode) {
						case VK_W -> {
							selectedModel.addY(0.05f);
						}
						case VK_S -> {
							selectedModel.addY(-0.05f);
						}
						case VK_D -> {
							selectedModel.addX(0.05f);
						}
						case VK_A -> {
							selectedModel.addX(-0.05f);
						}
						case VK_Q -> {
							selectedModel.addZ(0.05f);
						}
						case VK_E -> {
							selectedModel.addZ(-0.05f);
						}
						case VK_C -> {
							var figureColor = JColorChooser.showDialog(null, "Color de la figura", selectedModel.getAmbientColor());

							if (figureColor == null) {
								return;
							}

							selectedModel.setAmbientColor(figureColor);
							selectedModel.setDifuseColor(figureColor);
							selectedModel.setEmissionColor(figureColor);
							selectedModel.setSpecularColor(figureColor);
						}
						case VK_L -> {
							if (selectedModel.isLight()) {
								if (selectedModel.unconvertLight()) {
									logger.append("La figura " + selectedModel.getName() + " ya no es luz.\n");
								}
							} else {
								if (!selectedModel.convertLight()) {
									logger.append("La figura " + selectedModel.getName() + " no se pudo convertir en luz.\n");
								} else {
									logger.append("La figura " + selectedModel.getName() + " ahora es luz.\n");
								}
							}
						}
					}
				}

				repaint();
				canvasPanel.setCamara(camara);
			}
		});

		/* leftPanel componentes y diseño */
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		leftPanelTitle.setText("Figuras");
                leftPanelTitle.setForeground(new java.awt.Color(105, 19, 6));
                leftPanelTitle.setForeground(new java.awt.Color(105, 19, 6));
		leftPanelTitle.setFont(new Font("Georgia", Font.BOLD, 24));

		btnEsfera.setText("Esfera");
                btnEsfera.setBackground(new java.awt.Color(255,222,173));
                btnEsfera.setForeground(new java.awt.Color(210,105,30));
		btnEsfera.setFont(new Font("Georgia", Font.BOLD, 16));

		btnEsfera.addActionListener((ActionEvent e) -> {

			logger.append("Crea una esfera.\n");

			currentFigure = new ModeloRedondo();

			currentFigure.translate(canvasPanel.getCamPointing());

			listModel.addElement(currentFigure);
			canvasPanel.setModel(listModel);

			logger.append(currentFigure.getName() + " creada.\n");

			currentFigure = null;
		});

		btnCubo.setText("Cubo");
                btnCubo.setBackground(new java.awt.Color(255,222,173));
                btnCubo.setForeground(new java.awt.Color(210,105,30));
		btnCubo.setFont(new Font("Georgia", Font.BOLD, 16));
		btnCubo.addActionListener((ActionEvent e) -> {

			logger.append("Crea un cubo.\n");

			currentFigure = new ModeloCubo();

			currentFigure.translate(canvasPanel.getCamPointing());

			listModel.addElement(currentFigure);
			canvasPanel.setModel(listModel);

			logger.append(currentFigure.getName() + " creado.\n");

			currentFigure = null;

		});

		btnDodecahedro.setText("Dodecahedro");
                btnDodecahedro.setBackground(new java.awt.Color(255,222,173));
                btnDodecahedro.setForeground(new java.awt.Color(210,105,30));
		btnDodecahedro.setFont(new Font("Georgia", Font.BOLD, 16));
		btnDodecahedro.addActionListener((ActionEvent e) -> {

			logger.append("Crea un dodecahedro.\n");

			currentFigure = new Modelo12();

			currentFigure.translate(canvasPanel.getCamPointing());

			listModel.addElement(currentFigure);
			canvasPanel.setModel(listModel);

			logger.append(currentFigure.getName() + " creado.\n");

			currentFigure = null;

		});

		btnIcosahedro.setText("Icosahedro");
                btnIcosahedro.setBackground(new java.awt.Color(255,222,173));
                btnIcosahedro.setForeground(new java.awt.Color(210,105,30));
		btnIcosahedro.setFont(new Font("Georgia", Font.BOLD, 16));
		btnIcosahedro.addActionListener((ActionEvent e) -> {

			logger.append("Crea un icosahedro.\n");

			currentFigure = new Model20();

			currentFigure.translate(canvasPanel.getCamPointing());

			listModel.addElement(currentFigure);
			canvasPanel.setModel(listModel);

			logger.append(currentFigure.getName() + " creado.\n");

			currentFigure = null;

		});

		btnOctahedro.setText("Octahedro");
                btnOctahedro.setBackground(new java.awt.Color(255,222,173));
                btnOctahedro.setForeground(new java.awt.Color(210,105,30));
		btnOctahedro.setFont(new Font("Georgia", Font.BOLD, 16));
		btnOctahedro.addActionListener((ActionEvent e) -> {

			logger.append("Crea un octahedro.\n");

			currentFigure = new Modelo8();

			currentFigure.translate(canvasPanel.getCamPointing());

			listModel.addElement(currentFigure);
			canvasPanel.setModel(listModel);

			logger.append(currentFigure.getName() + " creado.\n");

			currentFigure = null;

		});

		btnAyuda.setText("¡Ayuda!");
                btnAyuda.setBackground(new java.awt.Color(255,222,173));
                btnAyuda.setForeground(new java.awt.Color(210,105,30));
		btnAyuda.setFont(new Font("Georgia", Font.BOLD, 16));
		btnAyuda.addActionListener((ActionEvent e) -> {

			logger.append("Se va a abierto en menu de ayuda.\n");

			String message = "Uso: \n";
			message += " Al dar clic a algun boton de figura, esta se creara,\n";
			message += " Encima de la esfera que se muestra.\n";
			message += " Nota: Esa esfera representa donde apunta la vision de la camara.\n";
			
			message += "Controles:\n";
			message += " - Shift + (W,A,S,D,Q,E): Cambia la posición de la camara.\n";
			message += " - Ctrl + (W,A,S,D,Q,E): Cambia el punto que ve la camara.\n";
			message += " - Ctrl + (↑, ↓): Cambia la distancia de visión de la camara.\n";
			message += " - W,A,S,D,Q,E: Cambia la posición de la figura seleccionada.\n";
			message += " - C: Cambia el color de la figura seleccionada.\n";
			message += " - L: Habilita/deshabilita la luz en la figura seleccionada.\n";

			JOptionPane.showMessageDialog(this, message, "¿Cómo funciona?", JOptionPane.INFORMATION_MESSAGE);

		});

		buttonGroup.add(btnEsfera);
		buttonGroup.add(btnCubo);
		buttonGroup.add(btnDodecahedro);
		buttonGroup.add(btnIcosahedro);
		buttonGroup.add(btnOctahedro);
		buttonGroup.add(btnAyuda);

		leftPanel.add(leftPanelTitle);
		leftPanel.add(btnEsfera);
		leftPanel.add(btnCubo);
		leftPanel.add(btnDodecahedro);
		leftPanel.add(btnIcosahedro);
		leftPanel.add(btnOctahedro);
		leftPanel.add(btnAyuda);

		/* centerPanel componentes y diseño */
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(Color.WHITE);

		canvasPanel.setBackground(centerPanel.getBackground());
		canvasPanel.setBorder(new LineBorder(Color.pink, 2));

		logger.setRows(10);
		logger.setLineWrap(true);
		logger.setEditable(false);

		scrollLogger.setViewportView(logger);

		centerPanel.add(canvasPanel, BorderLayout.CENTER);
		centerPanel.add(scrollLogger, BorderLayout.PAGE_END);

		/* rightPanel componentes y diseño */
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		rightPanelTitle.setText("Historial del programa");
                rightPanelTitle.setForeground(new java.awt.Color(105, 19, 6));
		rightPanelTitle.setFont(new Font("Georgia", Font.BOLD, 24));

		listModel = (ModelosLista) listFiguras.getModel();
		listFiguras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		listFiguras.addListSelectionListener((ListSelectionEvent e) -> {

			listModel.unselectAll();

			var selectedModel = listFiguras.getSelectedValue();

			if (selectedModel == null) {
				return;
			}

			selectedModel.setSelected(true);

			logger.append(selectedModel.getName() + " seleccionado.\n");

		});

		scrollFiguras.setViewportView(listFiguras);
		rightPanel.add(rightPanelTitle);
		rightPanel.add(scrollFiguras);

		add(leftPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);

	}

	@Override

	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		new Thread() {
			@Override
			public void run() {
				if (animator.isStarted()) {
					animator.stop();
				}
				System.exit(0);
			}
		}.start();
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	// Definición Variables
	private ModeloCamara camara;
	private FigureModel currentFigure;

	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;
	private JLabel leftPanelTitle;

	/* leftPanel componentes */
	private ButtonGroup buttonGroup;
	private JButton btnEsfera;
	private JButton btnCubo;
	private JButton btnDodecahedro;
	private JButton btnIcosahedro;
	private JButton btnOctahedro;
	private JButton btnAyuda;

	/* centerPanel componentes */
	private PanelIluminacion canvasPanel;
	private FPSAnimator animator;
	private JScrollPane scrollLogger;
	private JTextArea logger;

	/* rightPanel componentes */
	private JLabel rightPanelTitle;
	private JScrollPane scrollFiguras;
	private JList<FigureModel> listFiguras;
	private ModelosLista listModel;

}
