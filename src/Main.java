import javax.swing.*;

public class Main {

    private JTextField text_portRange;


    public static void main(String args[]) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NetworkUtility myForm = new NetworkUtility();
                myForm.setVisible(true);
            }
        });
    }
}