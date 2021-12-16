package Animal.AtilanoIgnacioManuel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Animal.*;    // import all packages
import Animal.Herbivore.Giraffe;
import Animal.Carnivore.Lion;

/* Makes me nostalgic of Qt */

public class Frame extends JFrame implements ActionListener
{
    /* ==================== VARIABLES ====================*/

    // Primitive variables
    private static int m_input;
    private static Scanner m_fileReader;
    private static File m_inputFile, m_outputFolder;
    private static ArrayList<Animal> m_animalList = new ArrayList<Animal>();
    private static ArrayList<Run> m_threadList = new ArrayList<Run>();

    // JavaFX related variables
    private static JButton m_inputButton, m_outputButton, m_finishedButton;
    private static JTextField m_lionInput, m_giraffeInput, m_threadsInput;
    private static boolean m_threadFlag, m_lionFlag, m_giraffeFlag, m_inputFlag, m_outputFolderFlag = false;

    /* ==================== VARIABLES ====================*/

    /* ==================== CONSTRUCTOR ==================== */
    Frame()
    {
        // Configure JFrame settings
        this.setTitle("File Chooser GUI Project");
        this.setLayout(new GridLayout(0,1));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        // Button select
        m_inputButton = new JButton("Select File");  // let user select file with button
        m_inputButton.addActionListener(this);         // let the button add itself to JFrame
        // inputBut.setBackground(Color.decode("#F7FFF7")); // consider refactoring to a linear gradient


        m_outputButton = new JButton("Select Output Folder");
        // outputBut.setBackground(Color.decode("#003249"));    // consider refactoring to a linear gradient
        m_outputButton.addActionListener(this);

        // Configure finished button
        m_finishedButton = new JButton("Finished");   // let user see this is the finished button
        m_finishedButton.addActionListener(this);       // let button add itself to JFrame

        // Get no. of lions from user
        m_lionInput = new JTextField("How many lions?");
        m_lionInput.setHorizontalAlignment(JTextField.CENTER);
        m_lionInput.setBackground(Color.decode("#9AD1D4"));

        /* Had classmate show me that adding focus is a good idea */
        m_lionInput.addFocusListener
                (new FocusAdapter()
                    {
                        public void focusGained(FocusEvent e)
                        {
                            if(m_lionInput.getText().equalsIgnoreCase("How many lions?"))
                                m_lionInput.setText(null);
                        }

                        public void focusLost(FocusEvent e)
                        {
                            if(m_lionInput.getText().equals(""))
                            {
                                m_lionInput.setText("How many lions?");
                                m_lionFlag = false;
                            }
                            else
                                m_lionFlag = true;
                        }
                    }
                );

        m_lionInput.addKeyListener
                (new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e)
                        {
                            char c= e.getKeyChar();
                                // long if condition
                            if(!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                                e.consume();
                        }
                    }
                );

        m_giraffeInput = new JTextField("How many Giraffes?");
        m_giraffeInput.setHorizontalAlignment(JTextField.CENTER);
        m_giraffeInput.setBackground(Color.decode("#CCDBDC"));

        // Once more, add focus on giraffe
        m_giraffeInput.addFocusListener
                (new FocusAdapter() {
                    public void focusGained(FocusEvent e) {
                        if (m_giraffeInput.getText().equalsIgnoreCase("How many Giraffes?"))
                            m_giraffeInput.setText(null);
                    }

                    public void focusLost(FocusEvent e)
                    {
                        if(m_giraffeInput.getText().equals(""))
                        {
                            m_giraffeInput.setText("How many Giraffes?");
                            m_giraffeFlag = false;
                        }
                        else
                            m_giraffeFlag = true;
                    }

                 }
                );

        // Ensure it is an integer
        m_giraffeInput.addKeyListener(new KeyAdapter()
        {
            public void KeyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if(!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                    e.consume();
            }
        });

        // Get input for number of threads
        m_threadsInput = new JTextField("How many Threads?");
        m_threadsInput.setHorizontalAlignment(JTextField.CENTER);
        m_threadsInput.setBackground(Color.decode("#007EA7"));
        m_threadsInput.addFocusListener(new FocusAdapter()
            {
                public void focusGained(FocusEvent e)
                {
                    if(m_threadsInput.getText().equalsIgnoreCase("How many Threads?"))
                        m_threadsInput.setText(null);
                }

                public void focusLost(FocusEvent e)
                {
                    if(m_threadsInput.getText().equals(""))
                    {
                        m_threadsInput.setText("How many Threads?");
                        m_threadFlag = false;
                    }
                    else
                        m_threadFlag = true;
                }
            }
        );

        m_threadsInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                    e.consume();
            }
        }
        );

        // Now that the buttons are properly configured
        // Add them to the JFrame
        this.add(m_inputButton);
        this.add(m_outputButton);
        this.add(m_giraffeInput);
        this.add(m_lionInput);
        this.add(m_threadsInput);
        this.add(m_finishedButton);

        // Better formatting when declared here
        this.setSize(500, 500);
        this.setVisible(true);
    }
    /* ==================== CONSTRUCTOR ==================== */

    /* ==================== MANAGE EVENTS ==================== */

    /* Implement ActionListener */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // if the input button is clicked
        if(e.getSource() == m_inputButton)
        {
            // Begin file chooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION)
            {
                m_inputFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                m_inputButton.setText(m_inputFile.getPath());
                m_inputFlag = true;
            }
            else
            {
                m_inputFlag = false;
                m_inputButton.setText("Select File");
            }
        }

        // Output button
        if(e.getSource() == m_outputButton)
        {
            // File chooser with settings
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int response = fileChooser.showOpenDialog(null);

            // Verify the path is valid
            if(response == JFileChooser.APPROVE_OPTION)
            {
                m_outputFolder = new File(fileChooser.getSelectedFile().getAbsolutePath());
                m_outputButton.setText(m_outputFolder.getPath());
                m_outputFolderFlag = true;
            }
            else
            {
                m_outputFolderFlag = false;
                m_outputButton.setText("Select Output Folder");

            }
        }

        // Finished Button
        if(e.getSource() == m_finishedButton)
        {
            // clear to be safe
            m_animalList.clear();

            // Check flags to make sure everything is in order
            if (m_inputFlag || m_outputFolderFlag || m_threadFlag || m_lionFlag || m_giraffeFlag)
            {
                /* get text from giraffe button and parse it to an integer
                * to store into a variable and do that for the other variables
                * that are alike */
                m_input = Integer.parseInt(m_giraffeInput.getText());
                createAnimal("Giraffe");

                m_input = Integer.parseInt(m_lionInput.getText());
                createAnimal("Lion");

                m_input = Integer.parseInt(m_threadsInput.getText());

                Threads();
                startThreads();
            }
            else
            {
                System.out.println("All the data fields were not entered\nPlease try again");
            }
        }

    }

    public static void createAnimal(String animal)
    {
        try
        {
            m_fileReader = new Scanner(m_inputFile);
            if(animal.equalsIgnoreCase("Giraffe"))
            {
                for(int x = m_input; x > 0; --x)
                    m_animalList.add(new Giraffe(m_fileReader.nextLine()));
            }
            else if(animal.equalsIgnoreCase("Lion"))
            {
                for(int x = m_input; x > 0; --x)
                    m_animalList.add(new Lion(m_fileReader.nextLine()));
            }
            m_fileReader.close();     // good practice to close
        }
        catch(Exception e) { System.err.println("Error creating animal"); }
    }

    public static void Threads()
    {
        for(int x = 0; x < m_input; ++x)
        {
            Run run = Run.createAndStart
                    ("thread " + x, m_outputFolder.getAbsolutePath() + "/output" +
                            + x + ".txt", m_animalList);
            m_threadList.add(run);
        }
    }

    public static void startThreads()
    {
        for(int x = 0; x < m_input; ++x)
        {
            try
            {
                m_threadList.get(x).getThread().join();   // this was sooo ticky
            }
            catch(Exception e) { e.printStackTrace(); }
        }
    }

    /* ==================== MANAGE EVENTS ==================== */

}
