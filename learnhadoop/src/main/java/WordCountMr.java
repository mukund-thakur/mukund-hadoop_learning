import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
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
import java.util.StringTokenizer;

public class WordCountMr extends Configured implements Tool {

    public static class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer stringTokenizer = new StringTokenizer(value.toString());
            while (stringTokenizer.hasMoreTokens()) {
                context.write(new Text(stringTokenizer.nextToken()),new IntWritable(1));
            }
        }
    }

    public static class WordCountReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum=0;
            while(values.iterator().hasNext()) {
                sum += values.iterator().next().get();
            }
            context.write(key,new IntWritable(sum));
        }
    }
    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = this.getConf();
        Job job = new Job(this.getConf());
        job.setJobName("Word Count mapreduce");
        job.setJarByClass(WordCountMr.class);
        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(WordCountReducer.class);
        job.setReducerClass(WordCountReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setNumReduceTasks(5);
        FileInputFormat.setInputPaths(job,new Path("/user/mukund/test"),new Path("/user/mukund/input1"),new Path("/user/mukund/input2"));
        FileOutputFormat.setOutputPath(job,new Path("/user/mukund/out"));
        return job.waitForCompletion(true) ? 0 : 1;
    }


    public static void main(String[] args) throws Exception {
        int  res = ToolRunner.run(new Configuration(),new WordCountMr(),args);
        System.exit(res);
    }
}
