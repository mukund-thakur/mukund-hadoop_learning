import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.AvroCollector;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroMapper;
import org.apache.avro.mapred.Pair;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.mapreduce.AvroKeyInputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class TweetCountMr extends Configured implements Tool
{
    public static class TweetContMapper extends AvroMapper<Tweet, Pair<CharSequence, Integer>>
    {
        private static Integer ONE = Integer.valueOf(1);

        @Override
        public void map(Tweet datum, AvroCollector<Pair<CharSequence, Integer>> collector, Reporter reporter) throws IOException
        {
            collector.collect(new Pair<CharSequence, Integer>(datum.getUsername(), ONE));
        }
    }

    public static class TweetCountGenericMapper extends Mapper<AvroKey<GenericRecord>, NullWritable, Text, IntWritable>
    {

        private static Integer ONE = Integer.valueOf(1);

        @Override
        protected void map(AvroKey<GenericRecord> key, NullWritable value, Context context) throws IOException, InterruptedException
        {
            context.write(new Text(key.datum().get("username").toString()), new IntWritable(ONE));
        }
    }

    public static void main(String[] args) throws Exception
    {
        int res = ToolRunner.run(new Configuration(), new TweetCountMr(), args);
        System.exit(res);
    }

    @Override
    public int run(String[] args) throws Exception
    {
//        printClassPath();
        if (args.length < 2 ) {
            System.out.println("Please provide input and output paths");
            System.exit(1);
        }
//        JobConf conf = new JobConf(Tweet.class);
//        conf.setJobName("Tweet count Mapreduce");
//        conf.set("mapred.job.queue.name","fdp");
//        AvroJob.setMapperClass(conf, TweetCountGenericMapper.class);
//        AvroJob.setInputSchema(conf, Tweet.getClassSchema());
//        AvroJob.setOutputSchema(conf, Pair.getPairSchema(Schema.create(Schema.Type.STRING), Schema.create(Schema.Type.INT)));
//        conf.setNumReduceTasks(0);
//        FileInputFormat.setInputPaths(conf, new Path(args[0]));
//        FileOutputFormat.setOutputPath(conf , new Path(args[1]));
//        RunningJob job = JobClient.runJob(conf);
//        job.waitForCompletion();
//        return job.isSuccessful() == true ? 1 : 0;
        printClassPath();
        removeOutputPathIfExists(new Path(args[1]));
        Job job = Job.getInstance(this.getConf());

        job.setJarByClass(TweetCountMr.class);
        job.setMapperClass(TweetCountGenericMapper.class);

        job.setInputFormatClass(AvroKeyInputFormat.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        AvroJob.setInputKeySchema(job, Tweet.getClassSchema());

        job.setOutputFormatClass(TextOutputFormat.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setNumReduceTasks(0);

        return job.waitForCompletion(true) ? 0 : 1;
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

    private void removeOutputPathIfExists(Path path) throws IOException
    {
        FileSystem fileSystem = FileSystem.get(this.getConf());
        if (fileSystem.exists(path)) {
            fileSystem.delete(path,true);
        }
    }


}
