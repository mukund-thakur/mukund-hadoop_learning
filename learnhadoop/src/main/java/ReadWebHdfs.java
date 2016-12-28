import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReadWebHdfs
{
    public static void main(String[] args) throws IOException
    {
        URL url = new URL("http://10.47.2.157:50070/webhdfs/v1/projects/fdp/tmp/mukund/mr.jar?op=open");
        FileOutputStream outputStream = new FileOutputStream("/tmp/mr.jar");
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
//            for (String line; (line = reader.readLine()) != null;) {
//                System.out.println(line);
//            }
//        }
        Object object = url.getContent();
        InputStreamReader  fp = new InputStreamReader(url.openStream(), "UTF-8");
        int nRead = 0;
        int totalRead = 0;
        char[] buffer = new char[1000];
        while ((nRead = fp.read(buffer, 0, 1000)) != -1) {
            for (int i = 0; i < nRead; i++) {
                outputStream.write(buffer[i]);
            }
            totalRead += nRead;
        }
        fp.close();
    }
}
