package File_format;

import java.io.File;
/**
 * Class that helps read the destination of the file import
 *
 */
public class CsvFileHelper {
/**
 * Return a ResourcePath Of File source
 * @param fileName Destination of the Csv file to import
 * @return dossierPath of Destination
 */
    public static String getResourcePath(String fileName) {
       final File f = new File("");
       final String dossierPath = f.getAbsolutePath() + File.separator + fileName;
       return dossierPath;
   }
/**
 * 
 * @param fileName Destination of the Csv file to import
 * 
 * @return File with the location of the csv file.
 */
   public static File getResource(String fileName) {
       final String completeFileName = getResourcePath(fileName);
       File file = new File(completeFileName);
       return file;
   }
}