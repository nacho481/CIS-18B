package Animal.AtilanoIgnacioManuel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BorderFactory;

import Animal.*;    // import all packages
import Animal.Herbivore.Giraffe;
import Animal.Carnivore.Lion;

/* Makes me nostalgic of Qt */

public class Frame extends JFrame implements ActionListener
{
    /* ==================== VARIABLES ====================*/

    // Primitive variables
    private static int input;
    private static Scanner fileReader;
    private static File inputFile, outputFolder;
    private static ArrayList<Animal> animalList = new ArrayList<Animal>();
    private static ArrayList<Run> threadList = new ArrayList<Run>();

    // JavaFX related variables
    private static JButton inputBut, outputBut, finishedButton;
    private static JTextField lionInput, giraffeInput, threadsInput;
    private static boolean isThreadInput, isLionInput, isGiraffeInput, isInputFile, isOutputFolder = false;

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
        inputBut = new JButton("Select File");  // let user select file with button
        inputBut.addActionListener(this);         // let the button add itself to JFrame
        // inputBut.setBackground(Color.decode("#F7FFF7")); // consider refactoring to a linear gradient


        outputBut = new JButton("Select Output Folder");
        // outputBut.setBackground(Color.decode("#003249"));    // consider refactoring to a linear gradient
        outputBut.addActionListener(this);

        // Configure finished button
        finishedButton = new JButton("Finished");   // let user see this is the finished button
        finishedButton.addActionListener(this);       // let button add itself to JFrame

        // Get no. of lions from user
        lionInput = new JTextField("How many lions?");
        lionInput.setHorizontalAlignment(JTextField.CENTER);
        lionInput.setBackground(Color.decode("#9AD1D4"));

        /* Had classmate show me that adding focus is a good idea */
        lionInput.addFocusListener
                (new FocusAdapter()
                    {
                        public void focusGained(FocusEvent e)
                        {
                            if(lionInput.getText().equalsIgnoreCase("How many lions?"))
                                lionInput.setText(null);
                        }

                        public void focusLost(FocusEvent e)
                        {
                            if(lionInput.getText().equals(""))
                            {
                                lionInput.setText("How many lions?");
                                isLionInput = false;
                            }
                            else
                                isLionInput = true;
                        }
                    }
                );

        lionInput.addKeyListener
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

        giraffeInput = new JTextField("How many Giraffes?");
        giraffeInput.setHorizontalAlignment(JTextField.CENTER);
        giraffeInput.setBackground(Color.decode("#CCDBDC"));



        // Make UI Easier to use and understand

        // Once more, add focus on giraffe
        giraffeInput.addFocusListener
                (new FocusAdapter() {
                    public void focusGained(FocusEvent e) {
                        if (giraffeInput.getText().equalsIgnoreCase("How many Giraffes?"))
                            giraffeInput.setText(null);
                    }

                    public void focusLost(FocusEvent e)
                    {
                        if(giraffeInput.getText().equals(""))
                        {
                            giraffeInput.setText("How many Giraffes?");
                            isGiraffeInput = false;
                        }
                        else
                            isGiraffeInput = true;
                    }

                 }
                );

        // Ensure it is an integer
        giraffeInput.addKeyListener(new KeyAdapter()
        {
            public void KeyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if(!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                    e.consume();
            }
        });

        // Get input for number of threads
        threadsInput = new JTextField("How many Threads");
        threadsInput.setHorizontalAlignment(JTextField.CENTER);
        threadsInput.setBackground(Color.decode("#007EA7"));
        threadsInput.addFocusListener(new FocusAdapter()
            {
                public void FocusGained(FocusEvent e)
                {
                    if(threadsInput.getText().equalsIgnoreCase("How many Threads?"))
                        threadsInput.setText(null);
                }

                public void focusLost(FocusEvent e)
                {
                    if(threadsInput.getText().equals(""))
                    {
                        threadsInput.setText("How many Threads");
                        isThreadInput = false;
                    }
                    else
                        isThreadInput = true;
                }
            }
        );

        threadsInput.addKeyListener(new KeyAdapter() {
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
        this.add(inputBut);
        this.add(outputBut);
        this.add(giraffeInput);
        this.add(lionInput);
        this.add(threadsInput);
        this.add(finishedButton);

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
        if(e.getSource() == inputBut)
        {
            // Begin file chooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION)
            {
                inputFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                inputBut.setText(inputFile.getPath());
                isInputFile = true;
            }
            else
            {
                isInputFile = false;
                inputBut.setText("Select File");
            }
        }

        // Output button
        if(e.getSource() == outputBut)
        {
            // File chooser with settings
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int response = fileChooser.showOpenDialog(null);

            // Verify the path is valid
            if(response == JFileChooser.APPROVE_OPTION)
            {
                outputFolder = new File(fileChooser.getSelectedFile().getAbsolutePath());
                outputBut.setText(outputFolder.getPath());
                isOutputFolder = true;
            }
            else
            {
                isOutputFolder = false;
                outputBut.setText("Select Output Folder");

            }
        }

        // Finished Button
        if(e.getSource() == finishedButton)
        {
            // clear list after use
            animalList.clear();
            // Check to make sure all fields are filled out and start main functions
            // isThreadInput isLionInput isGiraffeInput isInputFile isOutputFolder
            // isInputFile isOutputFolder isThreadInput isLionInput
            if ((isInputFile || isOutputFolder || isThreadInput || isLionInput || isGiraffeInput))
            {
                /* get text from giraffee button and parse it to an integer
                * to store into a variable and do that for the other variables
                * that are alike */
                input = Integer.parseInt(giraffeInput.getText());
                createAnimal("Giraffe");

                input = Integer.parseInt(lionInput.getText());
                createAnimal("Lion");

                input = Integer.parseInt(threadsInput.getText());

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
            fileReader = new Scanner(inputFile);
            if(animal.equalsIgnoreCase("Giraffee"))
            {
                for( int x = input; x > 0; --x)
                    animalList.add(new Giraffe(fileReader.nextLine()));
            }
            else if(animal.equalsIgnoreCase("Lion"))
            {
                for(int x = input; x > 0; --x)
                    animalList.add(new Lion(fileReader.nextLine()));
            }
            fileReader.close();     // good practice to close
        }
        catch(Exception e)
        {
            System.err.println("Error creating animal");
        }
    }

    public static void Threads()
    {
        for(int x = 0; x < input; ++x)
        {
            Run run = Run.createAndStart
                    ("thread " + x, outputFolder.getAbsolutePath() + "/output" +
                            + x + ".txt", animalList);
            threadList.add(run);
        }
    }

    public static void startThreads()
    {
        for(int x = 0;x < input; ++x)
        {
            try
            {
                threadList.get(x).getThread().join();   // this was sooo ticky
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /* ==================== MANAGE EVENTS ==================== */

}
