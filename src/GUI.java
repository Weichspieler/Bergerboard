/**
 * Sir Berger Board
 * 
 * Copyright 2016  <admin@kellerkinder>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public boolean disable;
	private int posX = 32;	//startwert posX
	private int posY = 80;	//startwert posY
	private int anzahlBtn = 0;
	private int zY = 0;		// zähler für posX
	
	ArrayList<JButton> 	myButton = new ArrayList<JButton>();
	ArrayList<String>	eventPath = new ArrayList<String>();
	
	Properties props = new Properties();

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	private jaggob derJaggob = new jaggob();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File file = new File("config.xml");
					GUI frame = new GUI();
					frame.setVisible(true);
					if (file.exists() == true) {
					frame.laden();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setBackground(Color.DARK_GRAY);
		setTitle("Sir Berger Board V0.3.5 alpha Kevin Edition");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1126, 702);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Sir Berger Board");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,"Sir Berger Board v0.3.5 \n Changelog: \n -automatisches speichern und laden \n  der Buttons implementiert \n © 2016 Kellerkinder \n © 2016 Jaggob Edition by Seilo \n www.kellerkinder.xyz","Über",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		label.setForeground(Color.WHITE);
		label.setBackground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(new Font("Arial", Font.ITALIC, 77));
		label.setBounds(109, 24, 645, 84);
		contentPane.add(label);
		
		JLabel lblKellerkinderSoftwareDevelopment = new JLabel(" © 2016 Kellerkinder");
		lblKellerkinderSoftwareDevelopment.setForeground(Color.BLUE);
		lblKellerkinderSoftwareDevelopment.setFont(new Font("Arial", Font.ITALIC, 20));
		lblKellerkinderSoftwareDevelopment.setBounds(32, 626, 409, 37);
		contentPane.add(lblKellerkinderSoftwareDevelopment);
		
		//öffnet Jaggob		-> Problem mit der Übergabe von Name und Path
		JButton btnOpenJaggob = new JButton("open Jaggob");
		btnOpenJaggob.setEnabled(false);
		btnOpenJaggob.setFont(new Font("Arial", Font.PLAIN, 17));
		btnOpenJaggob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				derJaggob.setVisible(true);
			}
		});
		btnOpenJaggob.setBounds(851, 110, 249, 23);
		contentPane.add(btnOpenJaggob);
		
		//ruft gnButton auf
		JButton btnNewButton = new JButton("new Button");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(anzahlBtn == 40){
					JOptionPane.showMessageDialog(null, "Maximal Anzahl erreicht!", "Error", JOptionPane.ERROR_MESSAGE);
					btnNewButton.setEnabled(false);
				}else{
				gnButton();	
				}
			}
		});
		btnNewButton.setBounds(851, 76, 249, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(851, 14, 249, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Dateiname");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(764, 17, 77, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Buttonname");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(764, 48, 77, 17);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(851, 45, 249, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				speichern();
			}
		});
		btnSave.setBounds(10, 24, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnLoad = new JButton("load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				laden();
			}
		});
		btnLoad.setBounds(10, 57, 89, 23);
		contentPane.add(btnLoad);
		
	}

	/**
	 * generiert den Button und ActionListener,eventPath hinzufügen
	 */
	public void gnButton(){
			generatePos();
			eventPath.add(setPath());							//speicher den eingegebenen Pfad in der Array List
			JButton button = new JButton();
			myButton.add(button);
			button.setText(setName());							//setzte den Button Text auf Wert von setName()
			contentPane.add(button);							//fügt Butoon zur cPane hinzu
			button.setFont(new Font("Arial", Font.PLAIN, 18)); 	//Schriftart und Größe
            button.setBounds(getPosX(), getPosY(), 200, 50); 				//position (x, y(j+160), width, height)			
            button.setVisible(true);						
			button.addActionListener(							//erstellt actionListener
		            new ActionListener(){
		                public void actionPerformed(ActionEvent e){
		                    SoundJLayer soundToPlay = new SoundJLayer(eventPath.get(myButton.indexOf(e.getSource())));
		                    soundToPlay.play();
		                }
		            }   
		            );
			speichern();
			textField.setText("");
			textField_1.setText("");
	}
	
	private void generatePos(){
		if(zY == 8){
			posY = 140;	//setzt posY zurück
			posX = posX + 210; 
			zY = 1;
		}else{
			posY = posY + 60;
			zY++;
		}
		anzahlBtn++;
	}
	
	private int getPosX(){
		return posX;
	}
	
	private int getPosY(){
		return posY;
	}
	
	private String setName(){
		String Name = textField_1.getText();
		return Name;
	}
	private String setPath() {
		String path = textField.getText();
		return path;
	}
	
	//wird für jaggob benötigt
	public String getName(String Name){
		System.out.println("Name:" + Name);
		return Name;
	}
//	
	public String getPath(String Path){
		System.out.println("Pfad: " + Path);
		return Path;
	}
		
		/**
		 * speichert die Daten in xml
		 */
		public void speichern(){
			
			File configFile = new File("config.xml");	//neue Datei "config.xml"
			try {
				String size = Integer.toString(myButton.size());
				props.setProperty("size", size);
				
				for(int i = 0; i < myButton.size(); i++){
				String btnName = "btnName"+i;
				String datName = "datName"+i;
				props.setProperty(btnName, myButton.get(i).getText());	//setzt pfad in propselement
				props.setProperty(datName, eventPath.get(i));
				}
				OutputStream outputStream = new FileOutputStream(configFile);	//neuer outputstream
				props.storeToXML(outputStream, "BergerBoard_Btn");
				outputStream.close();
				System.out.println("speichern erfolgreich");
			} catch (IOException e) {
				System.out.println("beim speichern ist ein fehler aufgetreten!");
				e.printStackTrace();
			}
		}
		
		/**
		 * läd die datei aus der xml
		 */
		public void laden(){
			
			File configFile = new File("config.xml");
			try {
				InputStream inputStream = new FileInputStream(configFile);
				props.loadFromXML(inputStream);
				int size = Integer.parseInt(props.getProperty("size"));
				for(int i = 0; i < size; i++){	
					String btnName = "btnName"+i;
					String datName = "datName"+i;
					
					generatePos();
					eventPath.add(props.getProperty(datName));							//speicher den eingegebenen Pfad in der Array List
					JButton button = new JButton();
					myButton.add(button);
					button.setText(props.getProperty(btnName));							//setzte den Button Text auf Wert von setName()
					contentPane.add(button);							//fügt Butoon zur cPane hinzu
					button.setFont(new Font("Arial", Font.PLAIN, 18)); 	//Schriftart und Größe
		            button.setBounds(getPosX(), getPosY(), 200, 50); 				//position (x, y(j+160), width, height)			
		            button.setVisible(true);						
					button.addActionListener(							//erstellt actionListener
				            new ActionListener(){
				                public void actionPerformed(ActionEvent e){
				                    SoundJLayer soundToPlay = new SoundJLayer(eventPath.get(myButton.indexOf(e.getSource())));
				                    soundToPlay.play();
				                }
				            }   
				            );
				}
				System.out.println("laden erfolgreich");
				inputStream.close();
			} catch (IOException e) {
				System.out.println("beim laden ist ein Fehler aufgetreten!");
				e.printStackTrace();
			}
		}
	
	class SoundJLayer extends PlaybackListener implements Runnable
	{
	    private String filePath;
	    private AdvancedPlayer player;
	    private Thread playerThread;    

	    public SoundJLayer(String filePath)
	    {
	        this.filePath = filePath;
	    }

	    public void play()
	    {
	        try
	        {
	            String urlAsString = 
	                "file:///" 
	                + new java.io.File(".").getCanonicalPath()          + "/" 
	                + this.filePath;

	            this.player = new AdvancedPlayer
	            (
	                new java.net.URL(urlAsString).openStream(),
	                javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice()
	            );

	            this.player.setPlayBackListener(this);

	            this.playerThread = new Thread(this, "AudioPlayerThread");

	            this.playerThread.start();
	        }
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	        }
	    }

	    // PlaybackListener members

	    public void playbackStarted(PlaybackEvent playbackEvent)
	    {
	        System.out.println("playbackStarted");
	    }

	    public void playbackFinished(PlaybackEvent playbackEvent)
	    {
	        System.out.println("playbackEnded");
	    }    

	    // Runnable members

	    public void run()
	    {
	        try
	        {
	            this.player.play();
	        }
	        catch (javazoom.jl.decoder.JavaLayerException ex)
	        {
	            ex.printStackTrace();
	        }

	    }
	}
}
