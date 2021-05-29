import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame implements Runnable {
	private JButton b1,b2;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mPlik,mObliczanie;
	private JMenuItem miZamknij,miOblicz,miWyczysc;
	private AbstractAction zamknijAction,obliczAction,wyczyscAction;
	private DefaultComboBoxModel<AlgorytmPrzeliczania> model;
	private JComboBox<AlgorytmPrzeliczania> jednostki;
	private MainFrame parentFrame;
	private AlgorytmPrzeliczania[] pozycje= {
			new AlgorytmPrzeliczania() {
				
				@Override
				public String toString() {
					return "kg->lb";
				}
				@Override
				Double przelicz(Double val) {
					return val* 2.2046;
				}
			},
			new AlgorytmPrzeliczania() {
				@Override
				public String toString() {
					return "Celcjusz->Fahrenheit";
				}
				@Override
				Double przelicz(Double val) {
					// TODO Auto-generated method stub
					return val*1.8000+32.;
				}
			},
			new AlgorytmPrzeliczania() {
				@Override
				public String toString() {
					return "l->ga";
				}
				@Override
				Double przelicz(Double val) {
					// TODO Auto-generated method stub
					return val*0.26417;
				}
			},
			new AlgorytmPrzeliczania() {
				@Override
				public String toString() {
					return "cm->in";
				}
				@Override
				Double przelicz(Double val) {
					// TODO Auto-generated method stub
					return val* 0.39370;
				}
			}
			};
	private JTextField dane;
	private JLabel wynik; 
	public MainFrame(String title) {
		super(title);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension dim=tk.getScreenSize();
		setSize(dim.width/2,dim.height/2);
		contentPane=new JPanel();
		setContentPane(contentPane);
		parentFrame=this;
		zamknijAction=new ZamknijAction();
		obliczAction=new ObliczAction();
		wyczyscAction=new WyczyscAction();

		b2=new JButton(obliczAction);
		add(b2);
		menuBar=new JMenuBar();
		mPlik=new JMenu("Plik");
		mObliczanie=new JMenu("Obliczanie");
		menuBar.add(mPlik);
		menuBar.add(mObliczanie);
		setJMenuBar(menuBar);
		miZamknij=new JMenuItem(zamknijAction);
		miOblicz=new JMenuItem(obliczAction);
		miWyczysc=new JMenuItem(wyczyscAction);
		mPlik.add(miZamknij);
		mObliczanie.add(miOblicz);
		mObliczanie.add(miWyczysc);
		model=new DefaultComboBoxModel<>(pozycje);
		jednostki=new JComboBox<>(model);
		add(jednostki);
		dane=new JTextField(6);
		wynik=new JLabel();
		add(dane);
		add(wynik);
		FrameCloseAdapter adapter=new FrameCloseAdapter();
		addWindowListener(adapter);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new MainFrame("Okno główne"));
	}

	@Override
	public void run() {
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	class ZamknijAction extends AbstractAction{
		public ZamknijAction() {
			putValue(AbstractAction.NAME, "Zamknij");
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Z"));
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			parentFrame.dispatchEvent(new WindowEvent(parentFrame, WindowEvent.WINDOW_CLOSING));
		}
		
	}
	
	class ObliczAction extends AbstractAction{
		public ObliczAction() {
			putValue(NAME, "Oblicz");
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl O"));
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String txt=dane.getText();
			if(txt.length()>0) {
				try {
				Double val=Double.parseDouble(txt);
				Double result=((AlgorytmPrzeliczania)jednostki.getSelectedItem()).przelicz(val);
				wynik.setText(result.toString());
				}
				catch(NumberFormatException w) {
					JOptionPane.showMessageDialog(parentFrame, "Wprowadzono niepoprawny format liczby");
					dane.setText("");
				}
			}
		}
		
	}
	
	class WyczyscAction extends AbstractAction{
		public WyczyscAction() {
			putValue(NAME, "Wyczyść");
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl W"));
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			dane.setText("");
			wynik.setText("");
		}
		
	}
	
	abstract class AlgorytmPrzeliczania{
		abstract Double przelicz(Double val);
	}
	
	class FrameCloseAdapter extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(parentFrame, "Czy na pewno chcesz zamknąć aplikację ?"))
				System.exit(0);
		}
	}
}

//b1=new JButton("Przycisk 1");
//add(b1);
//ButtonClickListener listener=new ButtonClickListener();
//b1.addActionListener(listener);

//class ButtonClickListener implements ActionListener{
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		JOptionPane.showMessageDialog(null, "Klikinięto przycisk");
//	}
//	
//}
