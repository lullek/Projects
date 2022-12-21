import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class GUIFrame extends JFrame implements ActionListener {


  Connection connection;

  JLabel header;

  JTextField bookID;
  JTextField bookTitle;
  JTextField bookPages;
  JButton bookButton;

  JTextField userID;
  JTextField userName;
  JTextField userAddress;
  JTextField userEmail;
  JButton userButton;

  JTextField userIDToMakeStudentOrAdmin;
  JTextField programOrDepartment;
  JComboBox makeStudOrAdm;
  DefaultComboBoxModel<String> makeChoicesCB;
  JButton makeButton;

  JTextField removeID;
  JComboBox removeCB;
  DefaultComboBoxModel<String> removeChoicesCB;
  JButton removeButton;



  JTextField searchTextBook;
  JComboBox searchCB;
  DefaultComboBoxModel<String> bookCB;
  DefaultComboBoxModel<String> studentCB;
  DefaultComboBoxModel<String> adminCB;
  JCheckBox bookCheckBox;
  JCheckBox studentCheckBox;
  JCheckBox adminCheckBox;
  JButton searchButton;

  JTextField idToUpdate;
  JTextField updateText;
  JComboBox updateCB;
  DefaultComboBoxModel<String> bookUpdateCB;
  DefaultComboBoxModel<String> studentUpdateCB;
  DefaultComboBoxModel<String> adminUpdateCB;
  JCheckBox bookCheckBox2;
  JCheckBox studentCheckBox2;
  JCheckBox adminCheckBox2;
  JButton updateButton;

  JButton showBooksButton;
  JButton showStudentsButton;
  JButton showAdminsButton;
  JButton showUnassignedButton;


  GUIFrame(){

    connection = null;
    //String dbURL = "jdbc:postgresql://localhost:5432/DD1368";
    //String userName = "postgres";
    //String password = "pass";

    connectToPSQL();

    //this.getContentPane().setBackground(Color.black);

    header = new JLabel("Library Database Tool", SwingConstants.CENTER);
    header.setBounds(10, 0, 900, 40);
   // header.setText("Library Database Tool");
    header.setFont(new Font("Times new roman", Font.BOLD, 27));
    //header.setHorizontalTextPosition(JLabel.CENTER);


    bookID = new JTextField();
    //bookID.setPreferredSize(new Dimension(250, 40));
    bookID.setBounds(10, 60, 250, 40);
    bookID.setText("Book ID");
    bookTitle = new JTextField();
    //bookTitle.setPreferredSize(new Dimension(250, 40));
    bookTitle.setBounds(270, 60, 250, 40);
    bookTitle.setText("Title");
    bookPages = new JTextField();
    //bookPages.setPreferredSize(new Dimension(250, 40));
    bookPages.setBounds(530, 60, 250, 40);
    bookPages.setText("Number of pages");
    bookButton = new JButton("Add book");
    bookButton.setBounds(790, 60, 100, 40);
    bookButton.setBorder(BorderFactory.createEtchedBorder());
    bookButton.addActionListener(this);

    userID = new JTextField();
    //bookID.setPreferredSize(new Dimension(250, 40));
    userID.setBounds(10, 160, 180, 40);
    userID.setText("User ID");
    userName = new JTextField();
    //bookTitle.setPreferredSize(new Dimension(250, 40));
    userName.setBounds(203, 160, 180, 40);
    userName.setText("Name");
    userAddress = new JTextField();
    //bookPages.setPreferredSize(new Dimension(250, 40));
    userAddress.setBounds(396, 160, 180, 40);
    userAddress.setText("Address");
    userEmail = new JTextField();
    //bookPages.setPreferredSize(new Dimension(250, 40));
    userEmail.setBounds(589, 160, 180, 40);
    userEmail.setText("Email");
    userButton = new JButton("Add user");
    userButton.setBounds(790, 160, 100, 40);
    userButton.setBorder(BorderFactory.createEtchedBorder());
    userButton.addActionListener(this);

    userIDToMakeStudentOrAdmin = new JTextField();
    userIDToMakeStudentOrAdmin.setBounds(10, 210, 250, 40);
    userIDToMakeStudentOrAdmin.setText("Make ID");
    programOrDepartment = new JTextField();
    programOrDepartment.setBounds(270, 210, 250, 40);
    programOrDepartment.setText("Program/department");
    String[] availableMakeCriteria = {"Student", "Admin"};
    makeChoicesCB = new DefaultComboBoxModel<>(availableMakeCriteria);
    makeStudOrAdm = new JComboBox();
    makeStudOrAdm.setModel(makeChoicesCB);
    makeStudOrAdm.setBounds(530, 210, 250, 40);
    makeButton = new JButton("Make");
    makeButton.setBounds(790, 210, 100, 40);
    makeButton.setBorder(BorderFactory.createEtchedBorder());
    makeButton.addActionListener(this);

    removeID = new JTextField();
    //bookIDRemove.setPreferredSize(new Dimension(250, 40));
    removeID.setBounds(10, 310, 250, 40);
    removeID.setText("Remove ID");
    String[] availableSearchCriteria = {"Books", "Users"};
    removeChoicesCB = new DefaultComboBoxModel<>(availableSearchCriteria);
    removeCB = new JComboBox();
    removeCB.setModel(removeChoicesCB);
    removeCB.setBounds(270, 310, 250, 40);
    removeButton = new JButton("Remove");
    removeButton.setBounds(530, 310, 100, 40);
    removeButton.setBorder(BorderFactory.createEtchedBorder());
    removeButton.addActionListener(this);

    searchTextBook = new JTextField();
    searchTextBook.setBounds(10, 360, 250, 40);
    searchTextBook.setText("Search criteria");
    String[] availableBookCriteria = {"BookID", "Title", "Author"};
    String[] availableStudentCriteria = {"Program", "UserID", "Name"};
    String[] availableAdminCriteria = {"Department", "UserID", "Name"};
    bookCB = new DefaultComboBoxModel<>(availableBookCriteria);
    studentCB = new DefaultComboBoxModel<>(availableStudentCriteria);
    adminCB = new DefaultComboBoxModel<>(availableAdminCriteria);
    //bookCriteria = new JComboBox(availableBookCriteria);
    searchCB = new JComboBox();
    searchCB.setModel(bookCB);
    searchCB.setBounds(270, 360, 250, 40);
    //userCriteria = new JComboBox(availableUserCriteria);
    //userCriteria.setBounds(270, 210, 250, 40);
    bookCheckBox = new JCheckBox("Books");
    //bookCheckBox.setLocation(655,210);
    bookCheckBox.setBounds(530, 360, 70, 40);
    bookCheckBox.setSelected(true);
    bookCheckBox.setFont(new Font("Times new roman", Font.PLAIN, 14));
    //bookCheckBox.setText("Books");
    bookCheckBox.addActionListener(this);
    studentCheckBox = new JCheckBox("Students");
    //bookCheckBox.setLocation(530,210);
    studentCheckBox.setBounds(600, 360, 80, 40);
    studentCheckBox.setSelected(false);
    studentCheckBox.setFont(new Font("Times new roman", Font.PLAIN, 14));
    //userCheckBox.setText("Users");
    studentCheckBox.addActionListener(this);
    adminCheckBox = new JCheckBox("Admins");
    adminCheckBox.setBounds(680, 360, 80, 40);
    adminCheckBox.setSelected(false);
    adminCheckBox.setFont(new Font("Times new roman", Font.PLAIN, 14));
    adminCheckBox.addActionListener(this);
    searchButton = new JButton("Search");
    searchButton.setBounds(790, 360, 100, 40);
    searchButton.setBorder(BorderFactory.createEtchedBorder());
    searchButton.addActionListener(this);

    idToUpdate = new JTextField();
    idToUpdate.setBounds(10, 480, 250, 40);
    idToUpdate.setText("Update ID");
    updateText = new JTextField();
    updateText.setBounds(10, 540, 250, 40);
    updateText.setText("New info");
    String[] availableBookUpdateCriteria = {"Title", "Pages"};
    String[] availableStudentUpdateCriteria = {"Program", "Name", "Address", "E-mail"};
    String[] availableAdminUpdateCriteria = {"Department", "Name", "Address", "E-mail"};
    bookUpdateCB = new DefaultComboBoxModel<>(availableBookUpdateCriteria);
    studentUpdateCB = new DefaultComboBoxModel<>(availableStudentUpdateCriteria);
    adminUpdateCB = new DefaultComboBoxModel<>(availableAdminUpdateCriteria);
    //bookCriteria = new JComboBox(availableBookCriteria);
    updateCB = new JComboBox();
    updateCB.setModel(bookUpdateCB);
    updateCB.setBounds(270, 480, 250, 40);
    bookCheckBox2 = new JCheckBox("Books");
    //bookCheckBox.setLocation(655,210);
    bookCheckBox2.setBounds(270, 540, 70, 40); //-260
    bookCheckBox2.setSelected(true);
    bookCheckBox2.setFont(new Font("Times new roman", Font.PLAIN, 14));
    //bookCheckBox.setText("Books");
    bookCheckBox2.addActionListener(this);
    studentCheckBox2 = new JCheckBox("Students");
    //bookCheckBox.setLocation(530,210);
    studentCheckBox2.setBounds(340, 540, 80, 40);
    studentCheckBox2.setSelected(false);
    studentCheckBox2.setFont(new Font("Times new roman", Font.PLAIN, 14));
    //userCheckBox.setText("Users");
    studentCheckBox2.addActionListener(this);
    adminCheckBox2 = new JCheckBox("Admins");
    adminCheckBox2.setBounds(430, 540, 80, 40);
    adminCheckBox2.setSelected(false);
    adminCheckBox2.setFont(new Font("Times new roman", Font.PLAIN, 14));
    adminCheckBox2.addActionListener(this);
    updateButton = new JButton("Update");
    updateButton.setBounds(530, 510, 100, 40);
    updateButton.setBorder(BorderFactory.createEtchedBorder());
    updateButton.addActionListener(this);

    showBooksButton = new JButton("Show books");
    showBooksButton.setBounds(10, 660, 100, 40);
    showBooksButton.setBorder(BorderFactory.createEtchedBorder());
    showBooksButton.addActionListener(this);
    showStudentsButton = new JButton("Show students");
    showStudentsButton.setBounds(120, 660, 100, 40);
    showStudentsButton.setBorder(BorderFactory.createEtchedBorder());
    showStudentsButton.addActionListener(this);
    showAdminsButton = new JButton("Show admins");
    showAdminsButton.setBounds(230, 660, 100, 40);
    showAdminsButton.setBorder(BorderFactory.createEtchedBorder());
    showAdminsButton.addActionListener(this);
    showUnassignedButton = new JButton("Show unassigned users");
    showUnassignedButton.setBounds(350, 660, 175, 40);
    showUnassignedButton.setBorder(BorderFactory.createEtchedBorder());
    showUnassignedButton.addActionListener(this);

    this.add(header);

    this.add(bookID);
    this.add(bookTitle);
    this.add(bookPages);
    this.add(bookButton);

    this.add(userID);
    this.add(userName);
    this.add(userAddress);
    this.add(userEmail);
    this.add(userButton);

    this.add(userIDToMakeStudentOrAdmin);
    this.add(programOrDepartment);
    this.add(makeStudOrAdm);
    this.add(makeButton);


    this.add(removeID);
    this.add(removeCB);
    this.add(removeButton);

    this.add(searchTextBook);
    this.add(searchCB);
    this.add(bookCheckBox);
    this.add(studentCheckBox);
    this.add(adminCheckBox);
    this.add(searchButton);

    this.add(idToUpdate);
    this.add(updateText);
    this.add(updateCB);
    this.add(bookCheckBox2);
    this.add(studentCheckBox2);
    this.add(adminCheckBox2);
    this.add(updateButton);

    this.add(showBooksButton);
    this.add(showStudentsButton);
    this.add(showAdminsButton);
    this.add(showUnassignedButton);

    //this.add(showUsersButton);

    //window = new NewWindow();

    //this.pack();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBackground(Color.BLACK);
    this.setLayout(null);
    this.setSize(900, 750);
    this.setVisible(true);

  }


  public void connectToPSQL() {

    String dbURL = "jdbc:postgresql://localhost:5432/DD1368";
    String userName = "postgres";
    String password = "pass";

    try {
      //Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(dbURL, userName, password);
      System.out.println("Connection to database successful.");
      //connection.close();

    } catch(Exception e) {
      System.out.println("Error when trying to connect to database");

    }
  }

  public void insertBook(int bookID, String title, int pages) {
    String insertBookSQL = "INSERT INTO books (bookID,title,pages) VALUES ("
    + bookID + ",'" + title + "'," + pages + ");";

    try {
    Statement statement = connection.createStatement();
    statement.executeUpdate(insertBookSQL);
    showUpdateMessage();
    System.out.println("Book inserted successfully.");
  } catch(Exception e) {
    System.out.println("Error: Could not insert book.");
    showErrorAddMessage();
  }
  }

  public void insertUser(int userID, String name, String address, String email) {
    String insertUserSQL = "INSERT INTO users (userID,name,address,email) VALUES ("
            + userID + ",'" + name + "','" + address + "','" + email + "');";

    System.out.println(insertUserSQL);

    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(insertUserSQL);
      showUpdateMessage();
      System.out.println("User inserted successfully.");
    } catch(Exception e) {
      System.out.println("Error: Could not insert user.");
      showErrorAddMessage();
    }
  }

  public void makeUserStudentOrAdmin(int userIdToMake, String programOrDepartment, String table) {

    String makeUserSQL = "";
    int userID = userIdToMake;

    if(table.equals("Student")) {
      makeUserSQL = "INSERT INTO students (userID, program) VALUES ("
              + userID + ",'" + programOrDepartment + "');";
    }

    if(table.equals("Admin")) {
      makeUserSQL = "INSERT INTO admins (userID, department, phonenumber) VALUES ("
              + userID + ",'" + programOrDepartment + "', NULL);";
    }

    System.out.println(makeUserSQL);

    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(makeUserSQL);
      showUpdateMessage();
      System.out.println("User updated successfully.");
    } catch(Exception e) {
      System.out.println("Error: Could not update user.");
      showErrorAddMessage();
    }
  }

  public void deleteRow(int rowID, String table) {
    int choice = showConfirmMessage();
    String idType = "";
    String extraQuery1 = "";
    String extraQuery2 = "";

    if(choice == 0) {

      if(table.equals("Books")) {
        idType = "bookID";
      }
      if(table.equals("Users")) {
        idType = "userID";

        extraQuery1 = "DELETE FROM students WHERE " + idType + " = "
                + rowID + ";";

        extraQuery2 = "DELETE FROM admins WHERE " + idType + " = "
                + rowID + ";";

        System.out.println(extraQuery1);
        System.out.println(extraQuery2);
      }

      String deleteRowSQL = "DELETE FROM " + table + " WHERE " + idType + " = "
              + rowID + ";";

      System.out.println(deleteRowSQL);

      try {
        Statement statement = connection.createStatement();
        if(table.equals("Users")) {
          Statement statement2 = connection.createStatement();
          Statement statement3 = connection.createStatement();
          statement2.executeUpdate(extraQuery1);
          statement3.executeUpdate(extraQuery2);
        }
        statement.executeUpdate(deleteRowSQL);
        showUpdateMessage();
        System.out.println("Row successfully deleted.");
      } catch (Exception e) {
        System.out.println("Error: Could not delete row.");
      }
    }
  }

  //public void showBooks() {
   // String columns[] = {"BookID", "Title", "Pages"};
   // String table = "books";
   // String toCount = "bookID";
    //createBookTable();
  //}


  public void showBooks(){
    String showBooksQuery = "SELECT*FROM books ORDER BY title;";
    String countSQL = "SELECT count(bookID) FROM books;";

    try {
      PreparedStatement ps = connection.prepareStatement(countSQL);
      ResultSet rs = ps.executeQuery();
      rs.next();
      int count = rs.getInt(1);

      Statement statement = connection.createStatement();
      ResultSet books = statement.executeQuery(showBooksQuery);
      String columns[] = {"BookID", "Title", "Pages"};
      String data[][] = new String[count][columns.length];

      int i = 0;
      while(books.next()) {
        int bID = books.getInt("bookID");
        String name = books.getString("title");
        int pgs = books.getInt("pages");

        data[i][0] = "" + bID;
        data[i][1] = name;
        data[i][2] = "" + pgs;

        i++;
      }

      createTableAndOpenWindow(columns, data);

    } catch(Exception e) {
      System.out.println("Error when trying to show books.");
    }
  }

  public void showStudents() {

    String showStudentsQuery = "SELECT*FROM users NATURAL JOIN students ORDER BY name;";
    String countSQL = "SELECT count(userID) FROM users NATURAL JOIN students;";

    try {
      PreparedStatement ps = connection.prepareStatement(countSQL);
      ResultSet rs = ps.executeQuery();
      rs.next();
      int count = rs.getInt(1);

      Statement statement = connection.createStatement();
      ResultSet users = statement.executeQuery(showStudentsQuery);
      String columns[] = {"UserID", "Name", "Address", "Email", "Program"};
      String data[][] = new String[count][columns.length];

      int i = 0;
      while(users.next()) {
        int uID = users.getInt("userID");
        String name = users.getString("name");
        String adress = users.getString("address");
        String email = users.getString("email");
        String program = users.getString("program");

        data[i][0] = "" + uID;
        data[i][1] = name;
        data[i][2] = adress;
        data[i][3] = email;
        data[i][4] = program;

        i++;
      }

      createTableAndOpenWindow(columns, data);

    } catch(Exception e) {
      System.out.println("Error when trying to show students.");
    }

  }

  public void showAdmins() {

    String showAdminsQuery = "SELECT*FROM users NATURAL JOIN admins ORDER BY name;";
    String countSQL = "SELECT count(userID) FROM users NATURAL JOIN admins;";

    try {
      PreparedStatement ps = connection.prepareStatement(countSQL);
      ResultSet rs = ps.executeQuery();
      rs.next();
      int count = rs.getInt(1);

      Statement statement = connection.createStatement();
      ResultSet users = statement.executeQuery(showAdminsQuery);
      String columns[] = {"UserID", "Name", "Address", "Email", "Department"};
      String data[][] = new String[count][columns.length];

      int i = 0;
      while(users.next()) {
        int uID = users.getInt("userID");
        String name = users.getString("name");
        String adress = users.getString("address");
        String email = users.getString("email");
        String department = users.getString("department");

        data[i][0] = "" + uID;
        data[i][1] = name;
        data[i][2] = adress;
        data[i][3] = email;
        data[i][4] = department;

        i++;
      }

      createTableAndOpenWindow(columns, data);

    } catch(Exception e) {
      System.out.println("Error when trying to show admins.");
    }

  }

  public void showUnassigned() {

    String showAdminsQuery = "SELECT*FROM users NATURAL JOIN (SELECT userID FROM users EXCEPT ((SELECT userID FROM students) UNION (SELECT userID FROM admins))) AS relevantids;";
    String countSQL = "SELECT count(userID) FROM users NATURAL JOIN admins;";

    try {
      PreparedStatement ps = connection.prepareStatement(countSQL);
      ResultSet rs = ps.executeQuery();
      rs.next();
      int count = rs.getInt(1);

      Statement statement = connection.createStatement();
      ResultSet users = statement.executeQuery(showAdminsQuery);
      String columns[] = {"UserID", "Name", "Address", "Email"};
      String data[][] = new String[count][columns.length];

      int i = 0;
      while(users.next()) {
        int uID = users.getInt("userID");
        String name = users.getString("name");
        String adress = users.getString("address");
        String email = users.getString("email");

        data[i][0] = "" + uID;
        data[i][1] = name;
        data[i][2] = adress;
        data[i][3] = email;

        i++;
      }

      createTableAndOpenWindow(columns, data);

    } catch(Exception e) {
      System.out.println("Error when trying to show users.");
    }

  }

  public void createTableAndOpenWindow(String[] columns, String[][] data){
    DefaultTableModel model = new DefaultTableModel(data, columns);
    JTable table = new JTable(model);
    table.setShowGrid(true);
    table.setShowVerticalLines(true);
    JScrollPane pane = new JScrollPane(table);
    JPanel panel = new JPanel();
    panel.add(pane);

    NewWindow window = new NewWindow(panel);
  }

  public void searchBooks() {
    String criteria = (String) searchCB.getSelectedItem();
    String searchText = searchTextBook.getText();
    String searchBooksQuery;
    String countSQL;
    if(criteria.equals("BookID")) {
      searchBooksQuery = "SELECT*FROM books NATURAL JOIN author WHERE " + criteria + " = " + searchText + ";";
      countSQL = "SELECT count(bookID) FROM books NATURAL JOIN author WHERE " + criteria + " = " + searchText + ";";
    } else {
      searchBooksQuery = "SELECT*FROM books NATURAL JOIN author WHERE LOWER(" + criteria + ") LIKE LOWER('%" + searchText + "%');";
      countSQL = "SELECT count(bookID) FROM books NATURAL JOIN author WHERE LOWER(" + criteria + ") LIKE LOWER('%" + searchText + "%');";
    }
    System.out.println(searchBooksQuery);
    System.out.println(countSQL);
    try {
      PreparedStatement ps = connection.prepareStatement(countSQL);
      ResultSet rs = ps.executeQuery();
      rs.next();
      int count = rs.getInt(1);

      Statement statement = connection.createStatement();
      ResultSet books = statement.executeQuery(searchBooksQuery);
      String columns[] = {"BookID", "Title", "Pages", "Author"};
      String data[][] = new String[count][columns.length];

      int i = 0;
      while(books.next()) {
        int bID = books.getInt("bookID");
        String name = books.getString("title");
        int pgs = books.getInt("pages");
        String atr = books.getString("author");

        data[i][0] = "" + bID;
        data[i][1] = name;
        data[i][2] = "" + pgs;
        data[i][3] = atr;

        i++;
      }

      createTableAndOpenWindow(columns, data);

    } catch(Exception e) {
      System.out.println("Error when trying to search books.");
      showErrorNotFoundMessage();
    }

  }

  public void searchStudents() {
    String criteria = (String) searchCB.getSelectedItem();
    String searchText = searchTextBook.getText();
    String searchStudentsQuery;
    String countSQL;
    if(criteria.equals("UserID")) {
      searchStudentsQuery = "SELECT*FROM users NATURAL JOIN students WHERE " + criteria + " = " + searchText + ";";
      countSQL = "SELECT count(userID) FROM users NATURAL JOIN students WHERE " + criteria + " = " + searchText + ";";
    } else {
      searchStudentsQuery = "SELECT*FROM users NATURAL JOIN students WHERE LOWER(" + criteria + ") LIKE LOWER('%" + searchText + "%');";
      countSQL = "SELECT count(userID) FROM users NATURAL JOIN students WHERE LOWER(" + criteria + ") LIKE LOWER('%" + searchText + "%');";
    }
    System.out.println(searchStudentsQuery);
    System.out.println(countSQL);
    try {
      PreparedStatement ps = connection.prepareStatement(countSQL);
      ResultSet rs = ps.executeQuery();
      rs.next();
      int count = rs.getInt(1);

      Statement statement = connection.createStatement();
      ResultSet students = statement.executeQuery(searchStudentsQuery);
      String columns[] = {"UserID", "Name", "Address", "Email", "Program"};
      String data[][] = new String[count][columns.length];

      int i = 0;
      while(students.next()) {
        int uID = students.getInt("userID");
        String name = students.getString("name");
        String addr = students.getString("address");
        String em = students.getString("email");
        String prog = students.getString("program");

        data[i][0] = "" + uID;
        data[i][1] = name;
        data[i][2] = addr;
        data[i][3] = em;
        data[i][4] = prog;

        i++;
      }

      createTableAndOpenWindow(columns, data);

    } catch(Exception e) {
      System.out.println("Error when trying to search students.");
      showErrorNotFoundMessage();
    }

  }

  public void searchAdmins() {
    String criteria = (String) searchCB.getSelectedItem();
    String searchText = searchTextBook.getText();
    String searchAdminsQuery;
    String countSQL;
    if(criteria.equals("UserID")) {
      searchAdminsQuery = "SELECT*FROM users NATURAL JOIN admins WHERE " + criteria + " = " + searchText + ";";
      countSQL = "SELECT count(userID) FROM users NATURAL JOIN admins WHERE " + criteria + " = " + searchText + ";";
    } else {
      searchAdminsQuery = "SELECT*FROM users NATURAL JOIN admins WHERE LOWER(" + criteria + ") LIKE LOWER('%" + searchText + "%');";
      countSQL = "SELECT count(userID) FROM users NATURAL JOIN admins WHERE LOWER(" + criteria + ") LIKE LOWER('%" + searchText + "%');";
    }
    System.out.println(searchAdminsQuery);
    System.out.println(countSQL);
    try {
      PreparedStatement ps = connection.prepareStatement(countSQL);
      ResultSet rs = ps.executeQuery();
      rs.next();
      int count = rs.getInt(1);

      Statement statement = connection.createStatement();
      ResultSet admins = statement.executeQuery(searchAdminsQuery);
      String columns[] = {"UserID", "Name", "Address", "Email", "Department"};
      String data[][] = new String[count][columns.length];

      int i = 0;
      while(admins.next()) {
        int uID = admins.getInt("userID");
        String name = admins.getString("name");
        String addr = admins.getString("address");
        String em = admins.getString("email");
        String dep = admins.getString("department");

        data[i][0] = "" + uID;
        data[i][1] = name;
        data[i][2] = addr;
        data[i][3] = em;
        data[i][4] = dep;

        i++;
      }

      createTableAndOpenWindow(columns, data);

    } catch(Exception e) {
      System.out.println("Error when trying to search admins.");
      showErrorNotFoundMessage();
    }

  }

  public void updateTable() {
    String attribute = (String) updateCB.getSelectedItem();
    String updID = idToUpdate.getText();
    String updText = updateText.getText();
    String updateQuery = "";

    if(bookCheckBox2.isSelected()) {
      if(attribute.equals("Pages")) {
        updateQuery = "UPDATE books SET " + attribute + " = " + updText + " WHERE bookID = " + updID + ";";
      } else {
        updateQuery = "UPDATE books SET " + attribute + " = '" + updText + "' WHERE bookID = " + updID + ";";
      }
    }

    if(studentCheckBox2.isSelected()) {

      if(attribute.equals("Program")) {

        updateQuery = "UPDATE students SET " + attribute + " = '" + updText + "' WHERE userID = " + updID + ";";

      } else {

        updateQuery = "UPDATE users SET " + attribute + " = '" + updText + "' WHERE userID = " + updID + ";";

      }
    }

    if(adminCheckBox2.isSelected()) {
      if(attribute.equals("Department")) {

        updateQuery = "UPDATE admins SET " + attribute + " = '" + updText + "'' WHERE userID = " + updID + ";";

      } else {

        updateQuery = "UPDATE users SET " + attribute + " = '" + updText + "' WHERE userID = " + updID + ";";

      }
    }

    System.out.println(updateQuery);
    try {

      Statement statement = connection.createStatement();
      statement.executeUpdate(updateQuery);
      showUpdateMessage();
      System.out.println("User updated successfully.");

    } catch(Exception e) {
      System.out.println("Error when trying to update.");
      showErrorAddMessage();
    }

  }

  public void showErrorMessage() {
    JOptionPane.showMessageDialog(null, "Wrong inputs, try again.", "Error", JOptionPane.WARNING_MESSAGE);
  }

  public void showErrorNotFoundMessage() {
    JOptionPane.showMessageDialog(null, "Nothing found.", "Error", JOptionPane.WARNING_MESSAGE);
  }

  public void showErrorAddMessage() {
    JOptionPane.showMessageDialog(null, "Could not add.", "Error", JOptionPane.WARNING_MESSAGE);
  }

  public void showUpdateMessage() {
    JOptionPane.showMessageDialog(null, "Database successfully updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
  }

  public int showConfirmMessage() {
    return JOptionPane.showConfirmDialog(null, "Are you sure you want to delete.", "Confirm", JOptionPane.YES_NO_OPTION);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == bookButton) {
      try {
      int idOfBook = Integer.parseInt(bookID.getText());
      String title = bookTitle.getText();
      int pages = Integer.parseInt(bookPages.getText());
      insertBook(idOfBook, title, pages);
    } catch(Exception a) {
        System.out.println("Error: Wrong input parameters");
        showErrorMessage();
      }
    }

    if(e.getSource() == userButton) {
      try {
        int idOfUser = Integer.parseInt(userID.getText());
        String name = userName.getText();
        String address = userAddress.getText();
        String email = userEmail.getText();
        insertUser(idOfUser, name, address, email);
      } catch(Exception a) {
        System.out.println("Error: Wrong input parameters");
        showErrorMessage();
      }
    }

    if(e.getSource() == makeButton) {
      try {
        int idOfUser = Integer.parseInt(userIDToMakeStudentOrAdmin.getText());
        String progOrDep = programOrDepartment.getText();
        String studOrAdm = (String) makeStudOrAdm.getSelectedItem();
        makeUserStudentOrAdmin(idOfUser, progOrDep, studOrAdm);
      } catch(Exception a) {
        System.out.println("Error: Wrong input parameters");
        showErrorMessage();
      }
    }

    if(e.getSource() == removeButton) {
      try {
      int idOfRowToRemove = Integer.parseInt(removeID.getText());
      String table = (String) removeCB.getSelectedItem();
      deleteRow(idOfRowToRemove, table);
    } catch(Exception b) {
        System.out.println("Error: Wrong input parameters");
        showErrorMessage();
      }
    }

    if(e.getSource() == showBooksButton) {
      try {
        showBooks();
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

    if(e.getSource() == bookCheckBox){
      try {
        studentCheckBox.setSelected(false);
        adminCheckBox.setSelected(false);
        searchCB.setModel(bookCB);
        this.add(searchCB);
        repaint();
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

    if(e.getSource() == studentCheckBox){
      try {
        bookCheckBox.setSelected(false);
        adminCheckBox.setSelected(false);
        searchCB.setModel(studentCB);
        this.add(searchCB);
        repaint();
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

    if(e.getSource() == adminCheckBox){
      try {
        bookCheckBox.setSelected(false);
        studentCheckBox.setSelected(false);
        searchCB.setModel(adminCB);
        this.add(searchCB);
        repaint();
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

    if(e.getSource() == bookCheckBox2){
      try {
        studentCheckBox2.setSelected(false);
        adminCheckBox2.setSelected(false);
        updateCB.setModel(bookUpdateCB);
        this.add(updateCB);
        repaint();
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

    if(e.getSource() == studentCheckBox2){
      try {
        bookCheckBox2.setSelected(false);
        adminCheckBox2.setSelected(false);
        updateCB.setModel(studentUpdateCB);
        this.add(updateCB);
        repaint();
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

    if(e.getSource() == adminCheckBox2){
      try {
        bookCheckBox2.setSelected(false);
        studentCheckBox2.setSelected(false);
        updateCB.setModel(adminUpdateCB);
        this.add(updateCB);
        repaint();
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

    if(e.getSource() == searchButton) {
      try {
        if(bookCheckBox.isSelected()) {
          searchBooks();
        }

        if(studentCheckBox.isSelected()) {
          searchStudents();
        }

        if(adminCheckBox.isSelected()) {
          searchAdmins();
        }
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

    if(e.getSource() == updateButton) {
      try {
        //if(bookCheckBox.isSelected()) {
          updateTable();
        //}

        //if(studentCheckBox.isSelected()) {
        //  searchStudents();
        //}

        //if(adminCheckBox.isSelected()) {
        //  searchAdmins();
        //}
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

    if(e.getSource() == showStudentsButton) {
      try {
        showStudents();
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

    if(e.getSource() == showAdminsButton) {
      try {
        showAdmins();
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

    if(e.getSource() == showUnassignedButton) {
      try {
        showUnassigned();
      } catch(Exception b) {
        System.out.println("Error");
      }
    }

  }

}
