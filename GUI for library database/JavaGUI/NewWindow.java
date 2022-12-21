import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;

public class NewWindow {

  JFrame newWindow = new JFrame();
  JPanel table;

  NewWindow(JPanel panel) {
      newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      newWindow.setLayout(new FlowLayout());
      table = panel;
      newWindow.add(table);
      newWindow.pack();
      newWindow.setVisible(true);
  }

public static void createNewWindow() {

  }

}
