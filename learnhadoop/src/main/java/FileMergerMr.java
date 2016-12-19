import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class FileMergerMr extends Configured implements Tool {

    public static LongWritable one = new LongWritable(1);

    public static class FileMergerMapper extends Mapper<LongWritable, Text,NullWritable, Text> {

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            context.write(NullWritable.get(),value);
        }
    }
    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = this.getConf();
        Job job = new Job(conf);
        job.setJarByClass(FileMergerMr.class);
        job.setMapperClass(FileMergerMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);
        FileInputFormat.addInputPaths(job, "/user/mukund/input1,/user/mukund/input3,/user/mukund/input4");
        FileOutputFormat.setOutputPath(job, new Path("/user/mukund/merge"));
        job.waitForCompletion(true);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new FileMergerMr(), args);
        System.exit(res);
    }
}
