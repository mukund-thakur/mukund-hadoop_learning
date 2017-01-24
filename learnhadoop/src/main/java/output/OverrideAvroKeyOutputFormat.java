package output;

import org.apache.avro.mapreduce.AvroKeyOutputFormat;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileAlreadyExistsException;
import org.apache.hadoop.mapred.InvalidJobConfException;
import org.apache.hadoop.mapreduce.JobContext;

import java.io.IOException;

public class OverrideAvroKeyOutputFormat extends AvroKeyOutputFormat
{
    @Override
    public void checkOutputSpecs(JobContext job) throws FileAlreadyExistsException, IOException
    {
        Path outDir = getOutputPath(job);
        if (outDir == null) {
            throw new InvalidJobConfException("Output directory not set.");
        }
    }
}
