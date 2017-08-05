import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;


class Copy {

public static void main(String[] commandArgs) {
	long start = System.currentTimeMillis();
	deepCopy(commandArgs[0],commandArgs[1]);
	long end = System.currentTimeMillis();
	System.out.println("\n Elapsed Time: " + (end - start));
}

public static void deepCopy(String fromPath, String destPath) {
	File fileSrc = new File(fromPath);
	
	File[] listOfFile = fileSrc.listFiles();
	byte[] fileBytes = new byte[500];
	
	for(File file : listOfFile) {
		if(file.isDirectory()) {
			File fileDest = new File(destPath + File.separator  +file.getName());
			fileDest.mkdir();
			deepCopy(file.getAbsolutePath(),fileDest.getAbsolutePath());
		} else {
			
			File fileOut = new File(destPath + File.separator + file.getName());
			FileOutputStream fileOutput = null;
			FileInputStream fileInput = null;
			try {
				fileOutput = new FileOutputStream(fileOut);
				fileInput = new FileInputStream(file);
				while(fileInput.read(fileBytes) != -1) {
					fileOutput.write(fileBytes);
				}
				System.out.print(".");
					

			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fileOutput.close();
					fileInput.close();
				} catch(Exception e) {
					e.printStackTrace();
				}	
			}

		}
	}
}

}
