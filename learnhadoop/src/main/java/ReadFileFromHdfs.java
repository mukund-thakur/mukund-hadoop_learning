import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ReadFileFromHdfs extends Configured implements Tool{
    public static void main(String[] args) throws Exception {
        int res= ToolRunner.run(new Configuration(),new ReadFileFromHdfs(),args);
        System.exit(res);
    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf= this.getConf();
        FileSystem fs= FileSystem.get(conf);
        System.out.printf("Connecting to {}" , fs.getHomeDirectory());
        ContentSummary contentSummary = fs.getContentSummary(new Path("/user"));
        System.out.printf(String.valueOf(contentSummary.getFileCount()));
        return 0;
    }
}
