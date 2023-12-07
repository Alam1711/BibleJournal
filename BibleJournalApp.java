import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.*;




public class BibleJournalApp {
    private static List<BibleVerse> bibleVerses;
    private List<BibleVerse> dailyVerses;
    private static final String txtFile = "verses.txt";
    

    public BibleJournalApp() {
        // Beginner Verses
        bibleVerses = new ArrayList<>();
        bibleVerses = loadBibleVersesFromTxt(txtFile);
        // bibleVerses.add(new BibleVerse("Genesis", 1, 1, "In the beginning...", "NIV", new ArrayList<>()));
        // bibleVerses.add(new BibleVerse("Psalm", 23, 1, "The Lord is my shepherd...", "NIV", new ArrayList<>()));
        //Set Daily Verses
        dailyVerses = new ArrayList<>();
        dailyVerses.add(new BibleVerse("Philippians", 2, 3, " Do nothing out of selfish ambition or vain conceit. Rather, in humility value others above yourselves,", "NIV", null));
        dailyVerses.add(new BibleVerse("Psalms", 23, 5, "You prepare a table before me in the precense of my enemies. You anoit my head with oil. my cup overflows", "NIV", null));
        dailyVerses.add(new BibleVerse("John", 15, 5, "I am the vine, you are the branches. If you remain in me and I in you, you will bear much fruit; apart from me you can do nothing", "NIV", null));
        dailyVerses.add(new BibleVerse("Joshua", 1, 9, "Have I not commanded you? Be strong and corageous. Do not be afraid; do not be discouraged, for the Lord your God will be with you wherever you go.", "NIV", null));
        dailyVerses.add(new BibleVerse("Mark", 10, 45, "For even the son of man cam not to be served but to serve others and to ive his life as a ransom for many.", "NIV", null));
        dailyVerses.add(new BibleVerse("Philippians", 2, 17, "But even if I am being poured out like a drink offering on the sacrifice and service coming from your faith, I am glad and rejoice with all of you", "NIV", null));
        dailyVerses.add(new BibleVerse("Jeremiah", 29, 11, "For I know the plans I have for you declares the Lord, plans to prosper you and not to harm you, plans to give you a hope and a future.", "NIV", null));
        
        // Render App
        landingPage();
    }

        //App Render Method
        private void landingPage() {
            JFrame frame = new JFrame("Java Bible Journal App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 400);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
    
            // Create a panel to hold components
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(Color.WHITE);
    
            // Create a title label
            JLabel titleLabel = new JLabel("Java Bible Journal App");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(titleLabel);
    
            // Add some space
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
    
            // Create a welcome message label
            JLabel welcomeLabel = new JLabel("Welcome to your Bible Journal App!");
            welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(welcomeLabel);

            // Add some space
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
    
            // App description
            JTextArea description = new JTextArea(
                    "The Java Bible Journal App is like your go-to buddy for getting into the Bible vibe! It's super cool for church peeps who want to feel more connected spiritually. You can check out and save Bible verses, jot down your own prayers, and even get a daily boost of inspiration. Plus, you and your church buddies can team up to remember verses together, making it feel like a big spiritual hangout. It's an easy and awesome way to dive into the Bible, think about it every day, and strengthen your faith – all with this cool app! ");
            description.setLineWrap(true);
            description.setEditable(false);
            description.setWrapStyleWord(true);
            description.setSize(200, 40);
            description.setFont(new Font("Arial", Font.PLAIN, 14));
            description.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(description);

            // Add Verses
            JButton addVerses = new JButton("Add Verse");
            addVerses.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addBibleVersesPage();
                }
            });

            // View Verses
            JButton viewMyVersesButton = new JButton("View My Verses");
            viewMyVersesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openVersesWindow();
                }
            });

            // Daily Lesson plans
            JButton viewLessons = new JButton("Lesson Plans");
            viewLessons.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openLessons();
                }
            });

            // View Daily Bread Verses
            JButton viewDailyBreadButton = new JButton("Today's Verse");
            viewDailyBreadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayDailyVerse();
                }
            });

            // View Prayer Journal Button
            JButton viewPrayerJournalButton = new JButton("View Prayer Journal");
            viewPrayerJournalButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openPrayerJournal();
                }
            });



            // Add the form, buttons, and button panel to the main frame
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(addVerses);
            buttonPanel.add(viewMyVersesButton);
            buttonPanel.add(viewDailyBreadButton);
            buttonPanel.add(viewLessons);
            buttonPanel.add(viewPrayerJournalButton);
            
    
            // Show content
            frame.getContentPane().add(panel);
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
            frame.setVisible(true);
        }

        private void addBibleVersesPage() {
            // Create the main frame
            JFrame frame = new JFrame("Bible Journal App");
            
            frame.setSize(500, 400);

            // Create form panel
            JPanel formPanel = new JPanel();

            formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
            JComboBox<BibleVerse> verseComboBox = new JComboBox<>();
            for (BibleVerse verse : bibleVerses) {
                verseComboBox.addItem(verse);
            }
            formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

            JTextField bookField = new JTextField("", SwingConstants.LEFT);
            JTextField chapterField = new JTextField("", SwingConstants.LEFT);
            JTextField verseField = new JTextField("", SwingConstants.LEFT);
            JTextField textField = new JTextField("", SwingConstants.LEFT);
            JTextField versionField = new JTextField("", SwingConstants.LEFT);
            JCheckBox pastorField = new JCheckBox();
            JCheckBox memberField = new JCheckBox();
            JCheckBox familyField = new JCheckBox();



            //Form Layout
            formPanel.add(new JLabel("My Bible App:", SwingConstants.CENTER));
            formPanel.setFont(new Font("Arial", Font.BOLD, 104)); 
            formPanel.add(new JLabel("Book:", SwingConstants.LEFT));
            formPanel.add(bookField);
            formPanel.add(new JLabel("Chapter:", SwingConstants.LEFT));
            formPanel.add(chapterField);
            formPanel.add(new JLabel("Verse:", SwingConstants.LEFT));
            formPanel.add(verseField);
            formPanel.add(new JLabel("Text:", SwingConstants.LEFT));
            formPanel.add(textField);
            formPanel.add(new JLabel("Select Version", SwingConstants.LEFT));
            formPanel.add(versionField);
            formPanel.add(new JLabel("Select Your verse partners", SwingConstants.LEFT));
            formPanel.add(new JLabel("Pastor", SwingConstants.LEFT));
            formPanel.add(pastorField);
            formPanel.add(new JLabel("Member", SwingConstants.RIGHT));
            formPanel.add(memberField);
            formPanel.add(new JLabel("Family", SwingConstants.RIGHT));
            formPanel.add(familyField);


            // Button to add Verses
            JButton addButton = new JButton("Add Bible Verse");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Partner> mates = new ArrayList<>();
                    if (pastorField.isSelected()) {
                        mates.add(new Partner("Pastor", verseField.getText(), false));

                    }
                    if (familyField.isSelected()) {
                        mates.add(new Partner("Family", verseField.getText(), false));

                    }
                    if (memberField.isSelected()) {
                        mates.add(new Partner("Member", verseField.getText(), false));

                    }
                    System.out.println(mates);
                    addBibleVerse(bookField.getText(), Integer.parseInt(chapterField.getText()), Integer.parseInt(verseField.getText()), textField.getText(), versionField.getText(), mates);
                    updateComboBox(verseComboBox);
                    saveBibleVersestoTxt(bibleVerses);
                    openVersesWindow();
                };
            });
            

            // View Verses
            JButton viewMyVersesButton = new JButton("View My Verses");
            viewMyVersesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openVersesWindow();
                }
            });




            // Add the form, buttons, and button panel to the main frame
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(addButton);
            buttonPanel.add(viewMyVersesButton);



            frame.getContentPane().add(formPanel, BorderLayout.CENTER);
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

            // Show frame
            frame.setVisible(true);
        }

        //Save Verses to txt file
        public static void saveBibleVersestoTxt(List<BibleVerse> bibleVerses) {
            final String txtFile = "verses.txt";
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(txtFile)))) {
                System.out.println("Bible verses saved to file1111.");
                for (BibleVerse verse : bibleVerses) {
                    if (verse != null) {  // Check if verse is not null
                        writer.println(verse.toString());
                    }
                }
                System.out.println("Bible verses saved to file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        

        private void openVersesWindow() {
            // Create a new frame for viewing stored verses
            JFrame versesFrame = new JFrame("Stored Bible Verses");
            versesFrame.setSize(900, 900);
            versesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
            // Create a panel to hold checkboxes and labels
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
            // Display stored verses
            for (BibleVerse verse : bibleVerses) {
                if (verse != null) {  // Check if verse is not null
        
                    // Create a panel for each verse
                    JPanel versePanel = new JPanel();
                    versePanel.setLayout(new BoxLayout(versePanel, BoxLayout.Y_AXIS));
                    versePanel.setSize(900, 20);
        
                    // Add verse information to the text area
                    JTextArea verseTextArea = new JTextArea();
                    verseTextArea.setSize(900, 20);
                    verseTextArea.setEditable(false);
                    verseTextArea.append(verse.getBook() + " " + verse.getChapter() + ":" + verse.getVerse() + "\n");
                    verseTextArea.append(verse.getText() + "\n\n");
        
                    // Add checkboxes to the verse panel
                    ArrayList<Partner> mates = verse.getMates();
                    if (mates != null) {
                        versePanel.add(new JLabel("Memorization Partner List:", SwingConstants.LEFT));
                        for (int i = 0; i < mates.size(); i++) {
                            String checkBoxName = "checkBox_" + i;
                            JCheckBox checkBox = new JCheckBox(mates.get(i).getPartnerType());
                            checkBox.setName(checkBoxName);
                            versePanel.add(checkBox);
                            //TODO Add complete verse button
                        }
                    } else {
                        versePanel.add(new JLabel("Solo Memorization Verse", SwingConstants.LEFT));
                    }
        
                    // Add verse text area and checkboxes panel to the main panel
                    mainPanel.add(verseTextArea);
                    mainPanel.add(versePanel);
        
                    mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));  
                }
            }
        
            // Add the main panel to the frame
            versesFrame.add(new JScrollPane(mainPanel));
        
            // Set frame visibility
            versesFrame.setVisible(true);
        }
        

    private void openPrayerJournal() {
        // Create a new frame for viewing Journal
        JFrame journalFrame = new JFrame("Stored Bible Verses");
        journalFrame.setSize(900, 900);
        journalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a text area to display and edit Journal
        JTextArea journalText = new JTextArea();
        journalText.setEditable(true);

        // Load text from file 
        String loadedText = loadJournal();
        journalText.setText(loadedText);

        // Save Journal Button
        JButton saveButton = new JButton("Save Verses");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveJournal(journalText.getText());
            }
        });

         // Add components to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        journalFrame.getContentPane().add(new JScrollPane(journalText), BorderLayout.CENTER);
        journalFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Set frame visibility
        journalFrame.setVisible(true);
    }   

     private void openLessons() {
    // Create a new frame for viewing stored verses
    JFrame versesFrame = new JFrame("Stored Bible Verses");
    versesFrame.setSize(900, 900);
    versesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Create a text area to display and edit stored verses
    JTextArea versesTextArea = new JTextArea();
    versesTextArea.setEditable(true);

    // Load text from file 
    String loadedText = loadLessons();
    versesTextArea.setText(loadedText);

    // Create a button to save the text to a file
    JButton saveButton = new JButton("Save Lessons");
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveLessons(versesTextArea.getText());
        }
    });

    // Add components to the frame
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(saveButton);

    versesFrame.getContentPane().add(new JScrollPane(versesTextArea), BorderLayout.CENTER);
    versesFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    // Set frame visibility
    versesFrame.setVisible(true);
}

private String loadJournal() {
    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader("user_journal.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        JOptionPane.showMessageDialog(null, "Loading Journal...");
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading Journal, Try again!");
    }
    return content.toString();
}


    private String loadLessons() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("user_lessons.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            JOptionPane.showMessageDialog(null, "Loading Lesson Plans...");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading Lesson Plans, Try again!");
        }
        return content.toString();
    }
    
    private void saveJournal(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_journal.txt"))) {
            writer.write(text);
            JOptionPane.showMessageDialog(null, "Saved Journal");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving Journal");
        }
    }

    private void saveLessons(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_lessons.txt"))) {
            writer.write(text);
            JOptionPane.showMessageDialog(null, "Saved Lessons");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving Lessons");
        }
    }

    private void addBibleVerse(String book, int chapter, int verse, String text, String version, ArrayList<Partner> mates) {
        BibleVerse newVerse = new BibleVerse(book, chapter, verse, text, version, mates);
        bibleVerses.add(newVerse);
    }

    private void updateComboBox(JComboBox<BibleVerse> verseComboBox) {
        // Update the JComboBox with the latest Bible verses
        verseComboBox.removeAllItems();
        for (BibleVerse verse : bibleVerses) {
            verseComboBox.addItem(verse);
        }
    }

    private void displayDailyVerse() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        
        int i = today.getValue() - 1; 

        if (i >= 0 && i < dailyVerses.size()) {
            //Days of the Week Array
        String[] daysOfWeek = {
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
        };
            BibleVerse todayVerse = dailyVerses.get(i);
            JOptionPane.showMessageDialog(null, daysOfWeek[i] + " Daily Bread Verse:\n" + todayVerse.getBook() +" " + todayVerse.getChapter() + ":" + todayVerse.getVerse() + " " + todayVerse.getVersion() + " :" + todayVerse.getText());
        } else {
            JOptionPane.showMessageDialog(null, "No verse available for today.");
        }
    }

    public static List<BibleVerse> loadBibleVersesFromTxt(String filePath) {
        List<BibleVerse> loadedVerses = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
    
            while ((line = reader.readLine()) != null) {
                // Parse each line and create a BibleVerse object
                BibleVerse verse = parseBibleVerseFromLine(line);
    
                // Add the verse to the list
                loadedVerses.add(verse);
            }
    
            System.out.println("Bible verses loaded from file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return loadedVerses;
    }
    
    private static BibleVerse parseBibleVerseFromLine(String line) {
        // Skip lines that do not start with "Book:"
        if (!line.startsWith("Book: ")) {
            System.err.println("Error: Invalid line format - Skipping: " + line);
            return null;
        }
    
        // Split the line into parts based on the format used in the toString method
        String[] parts = line.split("::");
        System.out.println(parts.length);
        System.out.println(parts[0]);
        System.out.println(parts[1]);
        System.out.println(parts[2]);
        System.out.println(parts[3]);
        System.out.println(parts[4]);
        System.out.println(parts[5]);
    
        // Ensure that there are enough parts to extract information
        if (parts.length >= 5) {
            // Extract information from each part
            String book = parts[0].substring("Book: ".length()).trim();
            int chapter = Integer.parseInt(parts[1].substring("Chapter: ".length()).trim());
            int verse = Integer.parseInt(parts[2].substring("Verse: ".length()).trim());
            String text = parts[3].substring("Text: ".length()).trim();
            String version = parts[4].substring("Version: ".length()).trim();
    
            ArrayList<Partner> mates = new ArrayList<>();
            if (parts.length > 5 && parts[5].startsWith("Memorization Partners-")) {
                for (int i = 6; i < parts.length; i++) {
                    String[] mateParts = parts[i].substring(2).split(": ");
                    String partnerType = mateParts[0].trim();
                    String partnerVerse = mateParts[1].trim();
                    mates.add(new Partner(partnerType, partnerVerse, false));
                }
            }
    
            // Create and return a BibleVerse object
            return new BibleVerse(book, chapter, verse, text, version, mates);
        } else {
            // Handle the case where the line format is invalid
            System.err.println("Error: Invalid line format - Not enough parts: " + line);
            return null; // Or throw an exception, depending on your requirements
        }
    }
    
    
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BibleJournalApp();
            }
        });
    }
}



