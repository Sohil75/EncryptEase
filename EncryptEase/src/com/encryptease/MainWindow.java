package com.encryptease;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.accessibility.AccessibleContext;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;

import java.awt.Choice;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Toolkit;

public class MainWindow  extends AES implements ActionListener{
	//variables for the main page
	JFrame frmEncryption=new JFrame();
	 JTextField plaintextarea = new JTextField();                      // text area to enter plain text
	JButton encryptbutton = new JButton("Encrypt");                    //button for encryption
	 JTextField encryptedarea= new JTextField();                       //text area for encrypted text 
	Button button = new Button("decryption");                          // button for going to next screen
	
	
	
	JFrame  frmDecryption = new JFrame();
	JTextField ciphertextarea=new JTextField();                           //text area to enter cipher text
	JTextField decryptedtextarea=new JTextField();                        // text area to get decrypted text
	JButton decryptbutton=new JButton();                                  // button for decryption
	Button button2 = new Button("encryption");                            // button to go to the previous screen
	 
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "MySecretKey12345";
    private static final String INIT_VECTOR = "RandomInitVector";

    String ent;
    
    
    //flash screen
    JFrame frmEncryptease = new JFrame();
	JPanel flashPanel = new JPanel();
	JLabel IconLabel = new JLabel("");
	JLabel HeadingLabel = new JLabel("EncryptEase");
	Button BtnEncryption = new Button("Encryption");
	Button btnDecrypt = new Button("Decryption");
	JLabel captionLabel = new JLabel("Protect and Unlock Your Data in Seconds with EncryptEase.");
	
	
    //frame for Encryption process
	/**
	 * @wbp.parser.entryPoint
	 */
	public void encryptionframe() {
		//frame description
		frmEncryption.getContentPane().setLayout(null);
		frmEncryption.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/encryptease/icon.png")));
		frmEncryption.setTitle("Encryption");
		frmEncryption.setForeground(new Color(204, 153, 153));
		frmEncryption.setResizable(false);
		frmEncryption.setSize(812,480);
		//panel description
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		panel.setBounds(0, 0, 798, 443);
		panel.setLayout(null);
		frmEncryption.getContentPane().add(panel);
		// description for plain text area 
		plaintextarea.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 20));
		plaintextarea.setBounds(301, 118, 223, 55);
		panel.add(plaintextarea);
		plaintextarea.setColumns(10);
		
		encryptedarea.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 25));
		encryptedarea.setBounds(234, 284, 366, 90);
		encryptedarea.setColumns(10);
		panel.add(encryptedarea);
		
		JLabel lblNewLabel = new JLabel("Plain Text :");
		lblNewLabel.setForeground(new Color(51, 204, 255));
		lblNewLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(139, 126, 152, 23);
		panel.add(lblNewLabel);
		
		encryptbutton.setForeground(new Color(0, 51, 51));
		encryptbutton.setBackground(new Color(51, 204, 255));
		encryptbutton.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		encryptbutton.setBounds(322, 200, 166, 48);
		panel.add(encryptbutton);
		
		JLabel lblNewLabel_1 = new JLabel("Encrypted :");
		lblNewLabel_1.setForeground(new Color(51, 204, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel_1.setBounds(45, 300, 168, 42);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Encryption");
		lblNewLabel_2.setForeground(new Color(51, 204, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Poor Richard", Font.BOLD | Font.ITALIC, 60));
		lblNewLabel_2.setBounds(139, 10, 479, 63);
		panel.add(lblNewLabel_2);
		
		button.setForeground(new Color(255, 250, 240));
		button.setBackground(new Color(51, 204, 255));
		button.setFont(new Font("Segoe Print", Font.ITALIC, 13));
		button.setBounds(636, 389, 120, 33);
		panel.add(button);
		
		frmEncryption.setVisible(true);
		encryptbutton.addActionListener(this);                     // assign the action listener to the button
		button.addActionListener(this);
	}
	public void decryptionframe() {
		
		frmDecryption.setVisible(true);
		decryptedtextarea.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 25));
		decryptedtextarea.setBounds(234, 284, 366, 90);
		decryptedtextarea.setColumns(10);
		frmEncryption.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/encryptease/icon.png")));
		frmDecryption.getContentPane().setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 250, 240));
		panel1.setBounds(0, 0, 798, 443);
		frmDecryption.getContentPane().add(panel1);
		panel1.setLayout(null);
		
		frmDecryption.setTitle("Decryption");
		frmDecryption.setForeground(new Color(204, 153, 153));
		frmDecryption.setResizable(false);
		frmDecryption.setSize(812,480);
		
		ciphertextarea.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 20));
		ciphertextarea.setBounds(301, 118, 223, 55);
		panel1.add(ciphertextarea);
		ciphertextarea.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Decryption Text :");
		lblNewLabel.setForeground(new Color(51, 204, 255));
		lblNewLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(113, 126, 178, 23);
		panel1.add(lblNewLabel);

		decryptbutton.setText("Decrypt");
		decryptbutton.setForeground(new Color(0, 51, 51));
		decryptbutton.setBackground(new Color(51, 204, 255));
		
		
		decryptbutton.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		decryptbutton.setBounds(322, 200, 166, 48);
		panel1.add(decryptbutton);
		
		panel1.add(decryptedtextarea);
		
		JLabel lblNewLabel_1 = new JLabel("Decrypted  :");
		lblNewLabel_1.setForeground(new Color(51, 204, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel_1.setBounds(45, 300, 168, 42);
		panel1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Decryption");
		lblNewLabel_2.setForeground(new Color(51, 204, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Poor Richard", Font.BOLD | Font.ITALIC, 60));
		lblNewLabel_2.setBounds(139, 10, 479, 63);
		panel1.add(lblNewLabel_2);
		
		button2.setBackground(new Color(51, 204, 255));
		button2.setFont(new Font("Segoe Print", Font.ITALIC, 13));
		button2.setBounds(10, 380, 106, 21);
		button2.setForeground(new Color(255, 250, 240));
		panel1.add(button2);
		
		button2.addActionListener(this);
		
		decryptbutton.addActionListener(this);                                      
		
	}
	// Method for encryption
	public static String encrypt(String value) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));      
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance(ALGORITHM);                     
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        byte[] encryptedBytes = cipher.doFinal(value.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
	//Method for decryption
    public static String decrypt(String encryptedValue) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedValue);

        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        return new String(decryptedBytes);
    }
    
    
    public void flashFrame() {
		frmEncryptease.setSize(750,580);
		//panel 
		flashPanel.setBackground(new Color(255, 250, 240));
		frmEncryptease.getContentPane().add(flashPanel, BorderLayout.CENTER);
		flashPanel.setLayout(null);
		//wallpaper
		IconLabel.setIcon(new ImageIcon("C:\\Users\\Sohail\\OneDrive\\Desktop\\icon.png"));
		IconLabel.setBounds(141, 0, 524, 400);
		flashPanel.add(IconLabel);
		//title
		HeadingLabel.setForeground(new Color(51, 204, 255));
		HeadingLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 35));
		HeadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HeadingLabel.setBounds(10, 10, 295, 58);
		flashPanel.add(HeadingLabel);
		//encrypt window button
		BtnEncryption.setForeground(new Color(255, 250, 240));
		BtnEncryption.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 15));
		BtnEncryption.setBackground(new Color(51, 204, 255));
		BtnEncryption.setBounds(198, 406, 186, 58);
		flashPanel.add(BtnEncryption);
		//decrypt window button
		btnDecrypt.setForeground(new Color(255, 250, 240));
		btnDecrypt.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 15));
		btnDecrypt.setBackground(new Color(51, 204, 255));
		btnDecrypt.setBounds(470, 406, 186, 58);
		flashPanel.add(btnDecrypt);
		// caption and app label
		captionLabel.setFont(new Font("Yu Gothic Medium", Font.ITALIC, 18));
		captionLabel.setForeground(new Color(51, 204, 255));
		captionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		captionLabel.setBounds(160, 486, 555, 42);
		flashPanel.add(captionLabel);
		frmEncryptease.setResizable(false);
		frmEncryptease.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/encryptease/icon.png")));
		frmEncryptease.setTitle("EncryptEase");
		frmEncryptease.setVisible(true);
		
		BtnEncryption.addActionListener(this);
		btnDecrypt.addActionListener(this);
	}
	
    
    
    
    //method to perform action 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object pr=e.getSource();
		//button to launch encryption frame
		if(pr==BtnEncryption) {
			frmEncryptease.setVisible(false);
			encryptionframe();
		}
		//button to launch decryption Frame
		else if(pr==btnDecrypt){
			frmEncryptease.setVisible(false);
			decryptionframe();
		}
		//function for encryption
		if(pr==encryptbutton) {
			System.out.println("pass1");
			String pt=plaintextarea.getText();
			try {
				ent=encrypt(pt);
				encryptedarea.setText(ent);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			}
		// on click launch the decryption frame
		else if(pr==button) {
			frmEncryption.setVisible(false);
			decryptionframe();	
		}
		// on click launch the encryption frame
		else if(pr==button2) {
			frmDecryption.setVisible(false);
			encryptionframe();	
		}
		//function for decryption
		else if(pr==decryptbutton) {
				System.out.println("pass2");
				String dt=ciphertextarea.getText();
				try {
					dt=decrypt(ent);
					decryptedtextarea.setText(dt);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		}
	
