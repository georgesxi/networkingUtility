import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NetworkUtility extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JPanel rootPanel;
    private JButton externalIPButton;
    private JButton internalIPButton;
    private JTextField text_portRange;
    private JButton scanPortsButton;
    private JTextField text_targetIP;
    private String portHint = "Enter here your port number or port range";
    private String IPHint = "Enter here the IP you wish to scan";
    private Font gainFont = new Font("Tahoma", Font.PLAIN, 11);
    private Font lostFont = new Font("Tahoma", Font.ITALIC, 11);

    public NetworkUtility() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //Set Windows Title & size
        setTitle("Network Utility Tool");
        setSize(400, 300);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        //When internal IP button is clicked do this:
        internalIPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String internalIP = "";
                try {
                    internalIP = GetIPAddress.getLocalIP();
                    JOptionPane.showMessageDialog(rootPanel, "Your internal IP is: " + internalIP);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        //When external IP button is clicked do this:

        externalIPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String externalIP = "";

                try {
                    externalIP = GetIPAddress.getExternalIP();
                    JOptionPane.showMessageDialog(rootPanel, "Your external IP is: " + externalIP);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        //Set gray hint in text boxes and handle focus changes

        text_portRange.setText(portHint);
        text_portRange.setFont(lostFont);
        text_portRange.setForeground(Color.GRAY);
        text_portRange.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (text_portRange.getText().equals(portHint)) {
                    text_portRange.setText("");
                    text_portRange.setFont(lostFont);


                } else {
                    text_portRange.setText(text_portRange.getText());
                    text_portRange.setFont(gainFont);
                    text_portRange.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (text_portRange.getText().equals(portHint) || text_portRange.getText().length() == 0) {
                    text_portRange.setText(portHint);
                    setFont(lostFont);
                    setForeground(Color.GRAY);
                    // super.focusLost(e);
                } else {
                    text_portRange.setText(text_portRange.getText());
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                }

            }
        });

        text_targetIP.setText(IPHint);
        text_targetIP.setFont(lostFont);
        text_targetIP.setForeground(Color.GRAY);
        text_targetIP.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (text_targetIP.getText().equals(IPHint)) {
                    text_targetIP.setText("");
                    text_targetIP.setFont(gainFont);
                } else {
                    text_targetIP.setText(text_targetIP.getText());
                    setFont(gainFont);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (text_targetIP.getText().equals(IPHint) || text_targetIP.getText().length() == 0) {
                    text_targetIP.setText(IPHint);
                    setFont(lostFont);
                    setForeground(Color.GRAY);
                    super.focusLost(e);
                } else {
                    text_targetIP.setText(text_targetIP.getText());
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                }

            }
        });

        // Call PortScanner class to scan specific port and IP


        scanPortsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text_targetIP.getText().equals(IPHint) || text_portRange.getText().equals(portHint)) {
                    JOptionPane.showMessageDialog(rootPanel, "Please enter both Port and IP number");


                } else {
                    String ip = text_targetIP.getText();
                    int port = Integer.parseInt(text_portRange.getText());
                    try {

                        String portResult = PortScanner.PortScanner(ip, port, 100);
                        JOptionPane.showMessageDialog(rootPanel, portResult);
                    } catch (Exception e1) {
                        e1.printStackTrace();


                    }
                }

            }
        });
    }

    private void onOK() {
        // just close the window
        dispose();
    }

    private void onCancel() {
        // no function needed when cancel is clicked
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
