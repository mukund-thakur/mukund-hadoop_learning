import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import org.apache.avro.mapred.AvroJob;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapreduce.AvroKeyInputFormat;
import org.apache.avro.mapreduce.AvroKeyOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import output.OverrideAvroKeyOutputFormat;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class ReadWriteAvroFactMr extends Configured implements Tool
{

    public static class ReadAvroMapper extends Mapper<AvroKey<GenericRecord>,NullWritable ,AvroKey<GenericRecord> , NullWritable> {
        @Override
        protected void map(AvroKey<GenericRecord> key, NullWritable value, Context context) throws IOException, InterruptedException
        {
            Schema schema =  new Schema.Parser().parse("{\"name\" : \"order_l1_mr_read\", \"type\" : \"record\", \"fields\" : [ {\"name\" : \"order_billing_amount\", \"type\" : [\"double\" , \"null\"] }, { \"name\" : \"order_id\", \"type\" : [\"long\" , \"null\"] } ] }");
            GenericRecord record = new GenericData.Record(schema);
            record.put("order_billing_amount", key.datum().get("order_billing_amount"));
            record.put("order_id", key.datum().get("order_id"));
            context.write(new AvroKey<GenericRecord>(record) ,NullWritable.get());
        }
    }
    @Override
    public int run(String[] args) throws Exception
    {

        Job job = Job.getInstance(this.getConf());
        String inputPath = job.getConfiguration().get("fdp.client.mr.path.order_l1_fact");
        String outputPath = job.getConfiguration().get("fdp.client.mr.path.order_l1_mr_fact");
        Schema outputSchemaCls = new Schema.Parser().parse(job.getConfiguration().get("avro.schema.output.key"));
        job.setJobName("Read from order_l1_fact and write into order_l1_mr_fact");
        job.setJarByClass(ReadWriteAvroFactMr.class);
        job.setInputFormatClass(AvroKeyInputFormat.class);
        job.setOutputFormatClass(OverrideAvroKeyOutputFormat.class);
        job.setMapperClass(ReadAvroMapper.class);
        //org.apache.avro.mapreduce.AvroJob.setInputKeySchema(job, inputSchemaCls);
        org.apache.avro.mapreduce.AvroJob.setOutputKeySchema(job, outputSchemaCls);
        job.setNumReduceTasks(0);
        FileInputFormat.setInputPaths(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args)
    {
        int res = 0;
        try {
            res = ToolRunner.run(new Configuration(), new ReadWriteAvroFactMr(), args);
        }
        catch (Exception e) {
            e.printStackTrace();
            res = -1;
        }
        System.exit(res);
    }
    private void removeOutputPathIfExists(Path path) throws IOException
    {
        FileSystem fileSystem = FileSystem.get(this.getConf());
        if (fileSystem.exists(path)) {
            fileSystem.delete(path,true);
        }
    }

    public void printClassPath()
    {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader) loader).getURLs();
        System.out.println("ClassPath Start");
        for (URL url : urls) {
            System.out.println(url.getFile());
        }
        System.out.println("Classpath end");

    }
}
