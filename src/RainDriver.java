import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.Scanner;
public class RainDriver {

	static int gamma[] = {
			  0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			  0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			  0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			  0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			  0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			  0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			  0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			  0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			  0x00,0x00,0x01,0x01,0x01,0x01,0x01,0x01,
			  0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,
			  0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,
			  0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,
			  0x01,0x01,0x01,0x01,0x01,0x01,0x02,0x02,
			  0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,
			  0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,
			  0x02,0x02,0x02,0x02,0x02,0x03,0x03,0x03,
			  0x03,0x03,0x03,0x03,0x03,0x03,0x03,0x03,
			  0x03,0x03,0x03,0x03,0x03,0x03,0x03,0x04,
			  0x04,0x04,0x04,0x04,0x04,0x04,0x04,0x04,
			  0x04,0x04,0x04,0x04,0x04,0x04,0x05,0x05,
			  0x05,0x05,0x05,0x05,0x05,0x05,0x05,0x05,
			  0x05,0x05,0x05,0x06,0x06,0x06,0x06,0x06,
			  0x06,0x06,0x06,0x06,0x06,0x06,0x06,0x07,
			  0x07,0x07,0x07,0x07,0x07,0x07,0x07,0x07,
			  0x07,0x07,0x08,0x08,0x08,0x08,0x08,0x08,
			  0x08,0x08,0x08,0x08,0x09,0x09,0x09,0x09,
			  0x09,0x09,0x09,0x09,0x09,0x0a,0x0a,0x0a,
			  0x0a,0x0a,0x0a,0x0a,0x0a,0x0a,0x0b,0x0b,
			  0x0b,0x0b,0x0b,0x0b,0x0b,0x0b,0x0c,0x0c,
			  0x0c,0x0c,0x0c,0x0c,0x0c,0x0c,0x0d,0x0d,
			  0x0d,0x0d,0x0d,0x0d,0x0d,0x0e,0x0e,0x0e,
			  0x0e,0x0e,0x0e,0x0e,0x0f,0x0f,0x0f,0x0f
			};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner myKeyboard = new Scanner(System.in);
		
		boolean run = true;
		
		System.out.print("Enter the directory containing the images: ");
		String fdir = myKeyboard.nextLine();
		int timing = -1;
		System.out.print("Enter a timing for the files? (y/n) "); 
		if(myKeyboard.nextLine().equalsIgnoreCase("y")) {
			System.out.print("Enter a timing value (0-15): ");
			timing = myKeyboard.nextInt();
			
		}
		
		File folder = new File(fdir + "/");
		
		for(final File fileEntry : folder.listFiles()) {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(fileEntry);
			} catch (IOException e) {
				String workingDir = System.getProperty("user.dir");
			    System.out.println("The file " + fileEntry.getName() + " could not be loaded");
			    //e.printStackTrace();
			    continue;
			}
			
			String output = "";
			
			for(int i = 0; i < 32; i++) {
				for(int j = 0; j < 32; j++) {
					Color colo = new Color(img.getRGB(i, j), true);
					output += Integer.toHexString(gamma[colo.getRed()]) + Integer.toHexString(gamma[colo.getGreen()]) + Integer.toHexString(gamma[colo.getBlue()]);  
					System.out.print(Integer.toHexString(gamma[colo.getRed()]));
					System.out.print(Integer.toHexString(gamma[colo.getGreen()]));
					System.out.print(Integer.toHexString(gamma[colo.getBlue()]));
				}
			}
			
			if(timing >= 0) {
				output += Integer.toHexString(timing);
			}
			
			String fOut = fileEntry.getName() + ".txt";
						
			PrintWriter myFile = null;
			
			try {
				 myFile = new PrintWriter(fOut); 
			} catch(FileNotFoundException e) {
				
				System.err.println("Could not save file " + fOut);
				System.exit(0);
			}
			myFile.print(output);
			myFile.close();
			
			System.out.println("File " + fOut + " was saved successfully");
			
		}
		
		/*
		while(true) {
			
			
			System.out.print("Enter the name of the file: ");
			String fPath = myKeyboard.nextLine();
			
			
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File(fPath));
			} catch (IOException e) {
				String workingDir = System.getProperty("user.dir");
			    System.out.println("The image " + fPath + "was not found in the current working directory : " + workingDir);
			    //e.printStackTrace();
			    System.exit(0);
			}
			
			
			/*
			
			Color col = new Color(img.getRGB(14, 13), true);
			System.out.println(Integer.toHexString(col.getRed()));
			System.out.println(Integer.toHexString(col.getGreen()));
			System.out.println(Integer.toHexString(col.getBlue())); */
			
			/*
			System.out.print("byte creep[32][32][3] = {\n");
			for(int i=0; i<32;i++) {
				//System.out.print("prog_char string_" + i +"[] PROGMEM = \"");
				System.out.print("\t{");
				for(int j=0;j<32;j++) {
					//System.out.print("matrix.drawPixel(" + i + "," + j + ",matrix.Color888(");
					System.out.print("{");
					Color colo = new Color(img.getRGB(i, j), true);
					System.out.print("0x" + Integer.toHexString(colo.getRed()) + ",");
					System.out.print("0x" + Integer.toHexString(colo.getGreen()) + ",");
					System.out.print("0x" + Integer.toHexString(colo.getBlue()));
					System.out.print("}" + (j==31?"":","));
					
				}
				System.out.print("}" + (i==31?"":",") +"\n");
				//System.out.print("\n");
				//System.out.print("\"\n");
			}
			System.out.print("\n};");
			*/ /*
			String output = "";
			
			for(int i = 0; i < 32; i++) {
				for(int j = 0; j < 32; j++) {
					Color colo = new Color(img.getRGB(i, j), true);
					output += Integer.toHexString(gamma[colo.getRed()]) + Integer.toHexString(gamma[colo.getGreen()]) + Integer.toHexString(gamma[colo.getBlue()]);  
					System.out.print(Integer.toHexString(gamma[colo.getRed()]));
					System.out.print(Integer.toHexString(gamma[colo.getGreen()]));
					System.out.print(Integer.toHexString(gamma[colo.getBlue()]));
				}
			}
			
		
			System.out.print("\n");
			System.out.print("Enter the name of the output file: ");
			
			String fOut = myKeyboard.nextLine();
			
			if(!fOut.endsWith(".txt")) {
				fOut += ".txt";
			}
			
			
			PrintWriter myFile = null;
			
			try {
				 myFile = new PrintWriter(fOut); 
			} catch(FileNotFoundException e) {
				
				System.err.println("Could not save file " + fOut);
				System.exit(0);
			}
			myFile.print(output);
			myFile.close();
			
			System.out.println("File " + fOut + " was saved successfully");
			
			System.out.print("Again? y/n: ");
			if(!myKeyboard.nextLine().equalsIgnoreCase("y")) {
				run = false;
			}
		} */
		System.out.println("Goodbye!");
		myKeyboard.close();
	}

}
