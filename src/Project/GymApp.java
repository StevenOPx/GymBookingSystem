package Project;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/***
 * Gym Booking System Mini-Project created on 15/11/2021
 * @author Steven Oprisan
 * @version 1.0
 */

public class GymApp extends JFrame implements ActionListener {

        JMenu membershipMenu;
        JMenu bookingMenu;
        JMenu exitMenu;
        JLabel mainImg;
        JPanel mainPanel;
        JPanel buttonShort;
        JButton membershipButton;
        JButton bookingButton;

        TitledBorder titledBorder;
        ArrayList<Membership> memberships = new ArrayList<>();
        private Membership membership;

        ArrayList<Booking> bookings = new ArrayList<>();
        private Booking booking;

        public GymApp() {

            Login login = new Login();

            setTitle("Gym Booking System");
            setSize(400,350);
            setLocationRelativeTo(null);
            setResizable(false);

            setIconImage(new ImageIcon(getClass().getResource("gymlogo.png")).getImage());

            Container mainPane = getContentPane();
            setLayout(new FlowLayout());

            createFileMenu();
            createMembershipMenu();
            createBookingMenu();
            createExitMenu();

            JMenuBar menuBar = new JMenuBar();
            setJMenuBar(menuBar);
            menuBar.setBackground(Color.GRAY);

            /**
             * Addition of file menu
             */
            menuBar.add(membershipMenu);
            menuBar.add(bookingMenu);
            menuBar.add(exitMenu);

            /**
             * Adding JPanel, displaying the window
             */
            mainPanel = new JPanel();
            mainPanel.validate();

            mainPanel.add(Box.createVerticalStrut(15));
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            /**
             * Add main image label
             */
            mainImg = new JLabel();
            mainImg.setIcon(new ImageIcon(getClass().getResource("gymimage.jpg")));

            mainImg.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(mainImg);

            buttonShort = new JPanel();

            titledBorder = new TitledBorder("Shortcut Menu");
            titledBorder.setTitlePosition(TitledBorder.CENTER);
            titledBorder.setTitleColor(Color.BLACK);

            buttonShort.setPreferredSize(new Dimension(400,50));
            buttonShort.setBorder(titledBorder);
            buttonShort.setLayout(new GridLayout(1,2));

            membershipButton = new JButton("Register Member");
            membershipButton.addActionListener(this);
            buttonShort.add(membershipButton);

            bookingButton = new JButton("Make Booking");
            bookingButton.addActionListener(this);
            buttonShort.add(bookingButton);

            add(mainPanel);
            add(buttonShort);

            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);

            open();
        }

        public void createFileMenu() {
            addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e) {
                    int option = JOptionPane.showConfirmDialog(null, "Would you like to exit? ",
                            "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(option == JOptionPane.YES_OPTION) {
                        try {
                            save();
                            JOptionPane.showMessageDialog(null, "Successfully saved data",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                        catch(IOException e1) {
                            JOptionPane.showMessageDialog(null, "File was not saved",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            e1.printStackTrace();
                        }

                        System.exit(0);
                        }
                    }
                });
            }

    public static void main(String[] args) {
        new GymApp();

        Login login = new Login();
        login.setVisible(true);

    }

        public void createMembershipMenu() {
            JMenuItem item;

            membershipMenu = new JMenu("Membership");
            membershipMenu.setMnemonic(KeyEvent.VK_M);

            item = new JMenuItem("Register Member");
            item.setMnemonic(KeyEvent.VK_R);
            item.addActionListener(this);
            membershipMenu.add(item);

            item = new JMenuItem("View Member");
            item.setMnemonic(KeyEvent.VK_V);
            item.addActionListener(this);
            membershipMenu.add(item);

            item = new JMenuItem("Delete Member");
            item.addActionListener(this);
            item.setMnemonic(KeyEvent.VK_D);
            membershipMenu.add(item);
        }

        public void createBookingMenu() {
            JMenuItem item;

            bookingMenu = new JMenu("Booking");
            bookingMenu.setMnemonic(KeyEvent.VK_B);

            item = new JMenuItem("Booking Type");
            item.setMnemonic(KeyEvent.VK_T);
            item.addActionListener(this);
            bookingMenu.add(item);

            item = new JMenuItem("Booking Location");
            item.setMnemonic(KeyEvent.VK_O);
            item.addActionListener(this);
            bookingMenu.add(item);

            item = new JMenuItem("Booking Length");
            item.setMnemonic(KeyEvent.VK_L);
            item.addActionListener(this);
            bookingMenu.add(item);

            item = new JMenuItem("Booking Time");
            item.setMnemonic(KeyEvent.VK_D);
            item.addActionListener(this);
            bookingMenu.add(item);
        }

        public void createExitMenu() {
            JMenuItem exit = new JMenuItem("Exit");
            exitMenu = new JMenu("Exit");
            exitMenu.setToolTipText("Exit");
            exitMenu.setMnemonic(KeyEvent.VK_X);

            exitMenu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    System.exit(0);
                }
            });
        }

        public void save() throws IOException {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("membership.dat"));
            os.writeObject(memberships);
            os.close();

            ObjectOutputStream os1 = new ObjectOutputStream(new FileOutputStream("booking.dat"));
            os1.close();
        }

        public void open() {
            try {
                File fileMem = new File("membership.dat");
                File fileBook = new File("booking.dat");

                if (fileMem.exists()) {
                    ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileMem));
                    memberships = (ArrayList<Membership>) is.readObject();
                    is.close();

                    JOptionPane.showMessageDialog(null, fileMem.getName() + " file loaded into Gym Booking System",
                            "Open", JOptionPane.INFORMATION_MESSAGE);
                } else if (fileBook.exists()) {
                    ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileBook));
                    is.close();

                    JOptionPane.showMessageDialog(null, fileBook.getName() + " file loaded into Gym Booking System",
                            "Open", JOptionPane.INFORMATION_MESSAGE);

                    new GymApp();
                    dispose();

                } else {
                    fileMem.createNewFile();
                    JOptionPane.showMessageDialog(null, "File was created!",
                            "Created" + fileMem.getName() + " file", JOptionPane.INFORMATION_MESSAGE);

                    fileBook.createNewFile();
                    JOptionPane.showMessageDialog(null, "File was created!",
                            "Created" + fileBook.getName() + " file", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch (FileNotFoundException fnfe) {
                JOptionPane.showMessageDialog(null, "File not found in this system...",
                        "Error", JOptionPane.ERROR_MESSAGE);
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "There was a problem reading the file...",
                        "Error", JOptionPane.ERROR_MESSAGE);
                ioe.printStackTrace();
            } catch (ClassNotFoundException cnfe) {
                JOptionPane.showMessageDialog(null, "Class of object deserialized not discovered to match in this system...",
                        "Error", JOptionPane.ERROR_MESSAGE);
                cnfe.printStackTrace();
            }
        }

        public void registerMember() {
            final String[] memberList = {"Joe Bloggs", "Sophie Hayes", "Esteban Jones", "Mason Hernandez", "Abbie Costello"};
            final String[] membershipTypeList = {"Beginner", "Intermediate", "Advanced"};
            final String[] lengthList = {"3 Months", "6 Months", "1 Year"};
            String memberName;
            int memberAge = 0;
            String membershipType;
            String length;

            memberName = (String) JOptionPane.showInputDialog(null, "Member name",
                    "Member", JOptionPane.QUESTION_MESSAGE, null, memberList, memberList[0]);

            memberAge = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Age",
                    "Member", JOptionPane.QUESTION_MESSAGE));

            if(memberAge < 16) {
                JOptionPane.showMessageDialog(null, "Person is too young to join the gym...",
                        "Error", JOptionPane.ERROR_MESSAGE);

                System.exit(0);
            }
            else {
                JOptionPane.showMessageDialog(null, "Person can join the gym!");
            }

            membershipType = (String) JOptionPane.showInputDialog(null, "Select membership type",
                    "Member", JOptionPane.QUESTION_MESSAGE, null, membershipTypeList, membershipTypeList[0]);


            length = (String) JOptionPane.showInputDialog(null, "Select membership length",
                    "Member", JOptionPane.QUESTION_MESSAGE,  null, lengthList, lengthList[0]);

            membership = new Membership(memberName, memberAge, membershipType, length);
            memberships.add(membership);

            JOptionPane.showMessageDialog(null, "Member '" + memberName + "' created a Membership");
        }
        public void viewMember() {
            JComboBox memberCombo = new JComboBox();
            JTextArea output = new JTextArea();

            output.setText("Member Details: \n\n");

            if(memberships.size() < 1) {
                JOptionPane.showMessageDialog(null, "No member has been added yet. Please Register a member",
                        "No member registered", JOptionPane.ERROR_MESSAGE);
            }

            else {
                Iterator<Membership> iterator = memberships.iterator();

                while (iterator.hasNext()) {
                    memberCombo.addItem(iterator.next().getMemberName() + "\n");
                }

                JOptionPane.showMessageDialog(null, memberCombo, "Choose a member to view details",
                        JOptionPane.PLAIN_MESSAGE);

                int chosen = memberCombo.getSelectedIndex();
                output.append(memberships.get(chosen).toString());

                JOptionPane.showMessageDialog(null, output, "Member Details",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
        public void deleteMember() {
            JComboBox memberList = new JComboBox();

            for(Membership m : memberships) {
                memberList.addItem(m.getMemberName());
            }

            JOptionPane.showMessageDialog(null, "Select member that is to be deleted",
                    "Delete Member", JOptionPane.INFORMATION_MESSAGE);

            JOptionPane.showMessageDialog(null, memberList,
                    "Delete Member", JOptionPane.INFORMATION_MESSAGE);

            int chosen = memberList.getSelectedIndex();

            memberships.remove(chosen);

            JOptionPane.showMessageDialog(null, "Member Deleted",
                    "Deleted", JOptionPane.INFORMATION_MESSAGE);
        }


        public void bookingType() {
            final String[] bookingTypeList = {"Gym Class", "Yoga Class", "Boxing Class", "Aerobics Class", "Crossfit Class"};
            final String[] bookingLocationList = {"Main Hall", "Studio", "Gym Basement"};
            final String[] dayOfWeekList = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            String bookingType;
            String bookingLocation;
            int bookingLength;
            String dayOfWeek;

            bookingType = (String) JOptionPane.showInputDialog(null, "Select Booking Type",
                    "Booking", JOptionPane.QUESTION_MESSAGE, null, bookingTypeList, bookingTypeList[0]);

            bookingLocation = (String) JOptionPane.showInputDialog(null, "Select Booking Location",
                    "Booking", JOptionPane.QUESTION_MESSAGE, null, bookingLocationList, bookingLocationList[0]);

            bookingLength = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter length of class:",
                    "Booking", JOptionPane.QUESTION_MESSAGE));

            dayOfWeek = (String) JOptionPane.showInputDialog(null, "Select day of week: ",
                         "Booking", JOptionPane.QUESTION_MESSAGE, null, dayOfWeekList, dayOfWeekList[0]);

            booking = new Booking(bookingType, bookingLocation, bookingLength, dayOfWeek);
            bookings.add(booking);

            JOptionPane.showMessageDialog(null, "Member '" + bookingType + "' created a Booking");
        }
        public void bookingLocation() {
            JComboBox locationCombo = new JComboBox();
            JTextArea output = new JTextArea();

            output.setText("Booking Details: \n\n");

            if(bookings.size() < 1) {
                JOptionPane.showMessageDialog(null, "No booking has been made yet. Please Book a class",
                        "No booking made", JOptionPane.ERROR_MESSAGE);
            }

            else {
                Iterator<Booking> iterator = bookings.iterator();

                while (iterator.hasNext()) {
                    locationCombo.addItem(iterator.next().getBookingLocation() + "\n");
                }

                JOptionPane.showMessageDialog(null, locationCombo, "Choose a booking to view details",
                        JOptionPane.PLAIN_MESSAGE);

                int chosen = locationCombo.getSelectedIndex();
                output.append(bookings.get(chosen).toString());

                JOptionPane.showMessageDialog(null, output, "Booking Location",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
        public void bookingLength() {
            JComboBox lengthCombo = new JComboBox();
            JTextArea output = new JTextArea();

            output.setText("Booking Details: \n\n");

            if(bookings.size() < 1) {
                JOptionPane.showMessageDialog(null, "No booking has been made yet. Please Book a class",
                        "No booking made", JOptionPane.ERROR_MESSAGE);
            }

            else {
                Iterator<Booking> iterator = bookings.iterator();

                while (iterator.hasNext()) {
                    lengthCombo.addItem(iterator.next().getBookingLength() + "\n");
                }

                JOptionPane.showMessageDialog(null, lengthCombo, "Choose a booking to view details",
                        JOptionPane.PLAIN_MESSAGE);

                int chosen = lengthCombo.getSelectedIndex();
                output.append(bookings.get(chosen).toString());

                JOptionPane.showMessageDialog(null, output, "Booking Length",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
        public void dayOfWeek() {
            JComboBox dayCombo = new JComboBox();
            JTextArea output = new JTextArea();

            output.setText("Booking Details: \n\n");

            if(bookings.size() < 1) {
                JOptionPane.showMessageDialog(null, "No booking has been made yet. Please Book a class",
                        "No booking made", JOptionPane.ERROR_MESSAGE);
            }

            else {
                Iterator<Booking> iterator = bookings.iterator();

                while (iterator.hasNext()) {
                    dayCombo.addItem(iterator.next().getDayOfWeek() + "\n");
                }

                JOptionPane.showMessageDialog(null, dayCombo, "Choose a booking to view details",
                        JOptionPane.PLAIN_MESSAGE);

                int chosen = dayCombo.getSelectedIndex();
                output.append(bookings.get(chosen).toString());

                JOptionPane.showMessageDialog(null, output, "Booking Day of Week",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String menuName = e.getActionCommand();

            if(menuName == "Register Member" || e.getSource() == membershipButton) {
                registerMember();
            }

            else if(menuName == "View Member") {
                viewMember();
            }

            else if(menuName == "Delete Member") {
                deleteMember();
            }

            else if (menuName == "Booking Type" || e.getSource() == bookingButton) {
                bookingType();
            }

            else if (menuName == "Booking Location") {
                bookingLocation();
            }

            else if (menuName == "Booking Length") {
                bookingLength();
            }

            else if (menuName == "Booking Time") {
                dayOfWeek();
            }
        }
}

