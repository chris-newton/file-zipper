import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileCompressionGUI{
	private JFrame frame;
	private File src, dst;
	private JButton compressButton, decompressButton, browseButton1, browseButton2;
	private JPanel mainPanel, compPanel, decompPanel;
	private JLabel browseLabel1, browseLabel2;
	private JTextField decompressPathTextField;
	
    public FileCompressionGUI() {
        initialize();
        JButton[] buttonArray = {compressButton, decompressButton, browseButton1, browseButton2};
        
        for (JButton b : buttonArray) {
	        b.setFont(new Font("Serif", Font.BOLD, 14));
	        b.setBackground(new Color(0, 140, 186));
	        b.setForeground(new Color(255, 255, 255));
	        b.setFocusable(false);
        }
        frame.add(mainPanel);
        frame.setVisible(true);
    }
 
    private void browseButton1ActionPerformed(ActionEvent evt) {
        JFileChooser fchooser = new JFileChooser();
        fchooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fchooser.showOpenDialog(null);
        
        if (fchooser.getSelectedFile() != null) {
            src = fchooser.getSelectedFile();
            String filename = src.getPath();
            int length = filename.length();
            
            if (length > 10) {
            	browseLabel1.setText(filename.substring(length));
            } else {
            	browseLabel1.setText(filename);
            }
        }
    }
    
    private void browseButton2ActionPerformed(ActionEvent evt) {
        JFileChooser fchooser = new JFileChooser();
        fchooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fchooser.showOpenDialog(null);
        
        if (fchooser.getSelectedFile() != null) {
            src = fchooser.getSelectedFile();
            String filename = src.getPath();
            int length = filename.length();
            
            if (length > 10) {
            	browseLabel2.setText(filename.substring(length));
            } else {
            	browseLabel2.setText(filename);
            }
        }
    }
    
    private void compressButtonActionPerformed(ActionEvent evt) {
        JFileChooser fchooser = new JFileChooser();
        fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fchooser.showOpenDialog(null);
        
        if (fchooser.getSelectedFile() != null) {
            dst = fchooser.getSelectedFile();
            String filename = dst.getPath();
            System.out.println("dst" + filename);
        }
        Compression.compress(src, dst);
    }
    
    private void decompressButtonActionPerformed(ActionEvent evt) {
        if (src == null) {
            System.err.println("Error: no source file selected to decompress");
            return;
        }

	    JFileChooser fchooser = new JFileChooser();
	    fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    fchooser.showOpenDialog(null);
	    
	    if (fchooser.getSelectedFile() != null) {
	        dst = fchooser.getSelectedFile();
	        System.out.println("dst" + dst);
	    }

	    Compression.decompress(src, dst);
	    
  
    }
    
    private void initialize() {
    	frame = new JFrame();
    	frame.setTitle("Huffman Encoding File Compressor");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLocationRelativeTo(null);
    	frame.setResizable(false);
    	frame.setSize(400, 250);
    	
        decompressPathTextField = new JTextField();
        
    	browseButton1 = new JButton();
    	browseButton2 = new JButton();
        compressButton = new JButton();
        decompressButton = new JButton();
        
        browseLabel1 = new JLabel();
        browseLabel2 = new JLabel();
        
        mainPanel = new JPanel(new BorderLayout());
    	compPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    	decompPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    	
    	browseButton1.setText("Browse");
    	browseButton1.setToolTipText("Select a file to compress");
        browseButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButton1ActionPerformed(evt);
            }
        });
        
        browseButton2.setText("Browse");
    	browseButton2.setToolTipText("Select a file to decompress");
        browseButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButton2ActionPerformed(evt);
            }
        });
        
        compressButton.setText("Compress");
        compressButton.setToolTipText("Select a destination for the compressed file");
        compressButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	compressButtonActionPerformed(evt);
            }
        });
        
        decompressButton.setText("Decompress");
        decompressButton.setToolTipText("Select a destination for the decompressed file");
        decompressButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	decompressButtonActionPerformed(evt);
            }
        });
        
        compPanel.add(browseButton1);
        compPanel.add(compressButton);
        compPanel.add(browseLabel1);
        
        decompPanel.add(browseButton2);
        decompPanel.add(decompressButton);
        compPanel.add(browseLabel2);
        
        mainPanel.add(compPanel, BorderLayout.CENTER);
        mainPanel.add(decompPanel, BorderLayout.SOUTH);
    }
    
}
