import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
class TextEditor extends JFrame implements ActionListener {
    // Text component
    JTextArea t;

    // Frame
    JFrame f;

    // Constructor
    TextEditor()
        {
            // Create a frame
            f = new JFrame("TextEditor");

            try {
                // Set metal look and feel
                UIManager.setLookAndFeel("javax.swing.play.metal.MetalLookAndFeel");

                // Set theme to ocean
                MetalLookAndFeel.setCurrentTheme(new OceanTheme());
            }
            catch (Exception e) {
            }

            // Text component
            t = new JTextArea();

            // Create a menubar
            JMenuBar mb = new JMenuBar();

            // Create a menu for menu
            JMenu m1 = new JMenu("File");

            // Create menu items
            JMenuItem mi1 = new JMenuItem("New");
            JMenuItem mi2 = new JMenuItem("Open");
            JMenuItem mi3 = new JMenuItem("Save");
            JMenuItem mi9 = new JMenuItem("Print");

            // Add action listener
            mi1.addActionListener(this);
            mi2.addActionListener(this);
            mi3.addActionListener(this);
            mi9.addActionListener(this);

            m1.add(mi1);
            m1.add(mi2);
            m1.add(mi3);
            m1.add(mi9);

            // Create a menu for menu
            JMenu m2 = new JMenu("Edit");

            // Create menu items
            JMenuItem mi4 = new JMenuItem("Cut");
            JMenuItem mi5 = new JMenuItem("Copy");
            JMenuItem mi6 = new JMenuItem("Paste");

            // Add action listener
            mi4.addActionListener(this);
            mi5.addActionListener(this);
            mi6.addActionListener(this);

            m2.add(mi4);
            m2.add(mi5);
            m2.add(mi6);

            JMenuItem mc = new JMenuItem("Close");

            mc.addActionListener(this);

            mb.add(m1);
            mb.add(m2);
            mb.add(mc);

            f.setJMenuBar(mb);
            f.add(t);
            f.setSize(500, 500);
            f.show();
        }

    // If a button is pressed
    public void actionPerformed(ActionEvent e)
        {
            String s = e.getActionCommand();

            switch (s)
                {
                    case "Cut":
                        t.cut();
                        JOptionPane.showMessageDialog(f, "All text has been cut");
                        break;
                    case "Copy":
                        t.copy();
                        JOptionPane.showMessageDialog(f, "All text has been copied");
                        break;
                    case "Paste":
                        t.paste();
                        JOptionPane.showMessageDialog(f, "Your text has been pasted");
                        break;
                    case "Save":
                    {
                        // Create an object of JFileChooser class
                        JFileChooser j = new JFileChooser("f:");

                        // Invoke the showsSaveDialog function to show the save dialog
                        int r = j.showSaveDialog(null);

                        if (r == JFileChooser.APPROVE_OPTION)
                            {

                                // Set the label to the path of the selected directory
                                File fi = new File(j.getSelectedFile().getAbsolutePath());

                                try
                                    {
                                        // Create a file writer
                                        FileWriter wr = new FileWriter(fi, false);

                                        // Create buffered writer to write
                                        BufferedWriter w = new BufferedWriter(wr);

                                        // Write
                                        w.write(t.getText());

                                        w.flush();
                                        w.close();
                                        JOptionPane.showMessageDialog(f, "Your work is saved");
                                    } catch (Exception evt)
                                    {
                                        JOptionPane.showMessageDialog(f, evt.getMessage());
                                    }
                            }
                        // If the user cancelled the operation
                        else
                            JOptionPane.showMessageDialog(f, "Operation Cancelled");
                        break;
                    }
                    case "Print":
                        try
                            {
                                // print the file
                                t.print();
                                JOptionPane.showMessageDialog(f, "File is printed");
                            } catch (Exception evt)
                            {
                                JOptionPane.showMessageDialog(f, "Print Operation Cancelled");
                            }
                        break;
                    case "Open":
                    {
                        // Create an object of JFileChooser class
                        JFileChooser j = new JFileChooser("f:");

                        // Invoke the showsOpenDialog function to show the save dialog
                        int r = j.showOpenDialog(null);

                        // If the user selects a file
                        if (r == JFileChooser.APPROVE_OPTION)
                            {
                                // Set the label to the path of the selected directory
                                File fi = new File(j.getSelectedFile().getAbsolutePath());

                                try
                                    {
                                        // String
                                        String s1 = "", sl = "";

                                        // File reader
                                        FileReader fr = new FileReader(fi);

                                        // Buffered reader
                                        BufferedReader br = new BufferedReader(fr);

                                        // Initialize sl
                                        sl = br.readLine();

                                        // Take the input from the file
                                        while ((s1 = br.readLine()) != null)
                                            {
                                                sl = sl + "\n" + s1;
                                            }

                                        // Set the text
                                        t.setText(sl);
                                        JOptionPane.showMessageDialog(f, "Your file is ready");
                                    } catch (Exception evt)
                                    {
                                        JOptionPane.showMessageDialog(f, evt.getMessage());
                                    }
                            }
                        // If the user cancelled the operation
                        else
                            JOptionPane.showMessageDialog(f, "Operation Cancelled");
                        break;
                    }
                    case "New":
                        JOptionPane.showMessageDialog(f, "New File Created");
                        t.setText("");
                        break;
                    case "Close":
                        JOptionPane.showMessageDialog(f, "Closing the editor");
                        f.setVisible(false);
                        break;
                }
        }

    // Main class
    public static void main(String[] args)
        {
            new TextEditor();
        }
}