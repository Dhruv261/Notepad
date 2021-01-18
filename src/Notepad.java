import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.spi.FileTypeDetector;
import java.io.FileOutputStream;

@SuppressWarnings("serial")
public class Notepad extends JFrame {
    

    private JPanel contentPane;
    @SuppressWarnings("unused")
    private boolean zoomer;
    @SuppressWarnings("unused")
    private double zoomFactor = 1;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Notepad frame = new Notepad();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Notepad() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0,0,0,0));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        setTitle("Untitled - Notepad");
        JTextArea textArea = new JTextArea();
        contentPane.add(textArea, BorderLayout.CENTER);
        textArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane (textArea);
        getContentPane().add(scroll); //Object of Jpanel

        getContentPane().add(scroll); //Object of Jpanel

        JMenuBar menuBar = new JMenuBar();
        scroll.setColumnHeaderView(menuBar);

        JMenu mnNewMenu = new JMenu("File");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("New");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setTitle("Untitled - Notepad");
                
                JFileChooser j = new JFileChooser("");
                j.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int response;
                j.setFileFilter(new FileTypeFilter(".txt","Text File"));
                response=j.showOpenDialog(null);
                File file;
                if (response==JFileChooser.APPROVE_OPTION) {
                    file = j.getSelectedFile();
                    String fileName = file.getPath();

                    String line = "";

                    BufferedReader br = null;

                     try 
                     {
                            br = new BufferedReader(new FileReader(fileName));
                                while( (line = br.readLine()) != null){
                                textArea.append(line);
                               if ("".equals(line)) {
								textArea.append(""+'\n');
							}
                                }
                     } 
                    catch (FileNotFoundException e1) {} 
                    catch (IOException e1) {}
                }
                textArea.setText("");
            }
            
            
           
        });
        mnNewMenu.add(mntmNewMenuItem);
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                JFileChooser x = new JFileChooser("");
//                x.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//                int response = x.showOpenDialog(null);
//
//                if(response==JFileChooser.APPROVE_OPTION){
//                	try(FileWriter fw = new FileWriter(x.getSelectedFile()+".txt")){
//                    fw.write(toString());
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
                	
                	JFileChooser fileChooser = new JFileChooser();
                    int retval = fileChooser.showSaveDialog(null);
                    fileChooser.setFileFilter(new FileTypeFilter(".txt","Text File"));
                    if (retval == JFileChooser.APPROVE_OPTION) {
                      File file = fileChooser.getSelectedFile();
                      if (file == null) {
                        return;
                      }
                      if (!file.getName().toLowerCase().endsWith(".txt")) {
                        file = new File(file.getParentFile(), file.getName() + ".txt");
                      }
                      try {
                        textArea.write(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
                       
                      } catch (Exception e9) {
                        e9.printStackTrace();
                      }
                    }
                	
                	
                	
                	
                }
            
        });
        mnNewMenu.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Open");
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser("");
                j.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int response;

                response=j.showOpenDialog(null);
                File file;
                if (response==JFileChooser.APPROVE_OPTION) {
                    file = j.getSelectedFile();
                    String fileName = file.getPath();

                    String line = "";

                    BufferedReader br = null;

                     try 
                     {
                            br = new BufferedReader(new FileReader(fileName));
                                while( (line = br.readLine()) != null){
                                textArea.append(line);
                               if ("".equals(line)) {
								textArea.append(""+'\n');
							}
                                }
                     } 
                    catch (FileNotFoundException e1) {} 
                    catch (IOException e1) {}
                }

            }


        });
        mnNewMenu.add(mntmNewMenuItem_2);
        mnNewMenu.addSeparator();
        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnNewMenu.add(mntmNewMenuItem_3);

        JMenu mnNewMenu_1 = new JMenu("Edit");
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Cut");
        mntmNewMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem_4);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Copy");
        mntmNewMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem_5);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("Paste");
        mntmNewMenuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem_6);

        JMenu mnNewMenu_2 = new JMenu("Help");
        menuBar.add(mnNewMenu_2);

        JMenuItem mntmNewMenuItem_7 = new JMenuItem("About");
        mntmNewMenuItem_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                About abt = new About();
                abt.setVisible(true);

            }
        });
        mnNewMenu_2.add(mntmNewMenuItem_7);


    }

}
