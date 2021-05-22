import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements Runnable {
    private JButton b2;
    private JPanel contentPanel;
    private JMenuBar menuBar;
    private JMenu menuPlik, menuObliczenia;
    private JMenuItem miZamknij, miOblicz, miWyczysc;
    private AbstractAction zamknijAction,obliczAction,wyczyscAction;

    public MainFrame(String title) {
        super(title);
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension dim=tk.getScreenSize();
        setSize(dim.width/2, dim.height/2);
        zamknijAction=new ZamknijAction();
        obliczAction=new ObliczAction();
        wyczyscAction=new WyczyscAction();
        contentPanel=new JPanel();
        setContentPane(contentPanel);
        b2=new JButton(obliczAction);
        add(b2);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Kliknięto przycisk "+((JButton)e.getSource()).getText());
            }
        });

        menuBar=new JMenuBar();
        menuPlik=new JMenu("Plik");
        menuObliczenia=new JMenu("Obliczenia");
        menuBar.add(menuPlik);
        menuBar.add(menuObliczenia);
        miZamknij=new JMenuItem(zamknijAction);
        menuPlik.add(miZamknij);
        miOblicz=new JMenuItem(obliczAction);
        menuObliczenia.add(miOblicz);
        miWyczysc=new JMenuItem(wyczyscAction);
        menuObliczenia.add(miWyczysc);

        setJMenuBar(menuBar);
    }

    public static void main (String[] args){
        EventQueue.invokeLater(new MainFrame("Okno główne"));
    }
    public void run(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class ZamknijAction extends AbstractAction{
        public ZamknijAction(){
            putValue(NAME,"Zamknij");
            putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl Z"));
        }
        public void actionPerformed(ActionEvent e){

        }
    }
    class ObliczAction extends AbstractAction{
        public ObliczAction(){
            putValue(NAME,"Oblicz");
            putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl O"));
        }
        public void actionPerformed(ActionEvent e){

        }
    }
    class WyczyscAction extends AbstractAction{
        public WyczyscAction(){
            putValue(NAME,"Wyczysc");
            putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl W"));
        }
        public void actionPerformed(ActionEvent e){

        }
    }
}

//    add(b1);
//   ButtonClickListener listener=new ButtonClickListener();
//   b1.addActionListener(listener);
//b1=new JButton();


//  class ButtonClickListener implements ActionListener{
//     public void actionPerformed(ActionEvent e){
//          JOptionPane.showMessageDialog(null,"Kliknięto przycisk "+((JButton)e.getSource()).getText());
//      }
//  }

