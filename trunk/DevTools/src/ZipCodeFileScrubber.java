import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZipCodeFileScrubber {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		try {
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			List<String> list = new ArrayList<String>();
			String line = "";
			while((line = br.readLine()) != null ) {
				String[] cols = line.split("\\t");
				if(cols.length == 1) {
					throw new Exception("ALREADY SCRUBBED");
				}
				if(!cols[3].equals("")) {
					StringBuilder sb = new StringBuilder();
					sb.append(cols[1]).append(",")
						.append(cols[8]).append(",").append(cols[9])
						.append(",").append(cols[2]).append(",")
						.append(cols[4]);
					list.add(sb.toString() + "\n");
				}
			}
			br.close();
			DataOutputStream os = new DataOutputStream(new FileOutputStream(f, false));
			for (String string : list) {
				os.write(string.getBytes());
				os.flush();
			}
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("FINISHED!");
	}

}
