package pl.net.kopczynski.spark.test;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * Created by tkopczynski on 21.02.15.
 */
public class SimpleApp {

    public static void main(String[] args) {
        String logFile = "/home/tkopczynski/lib/spark-1.2.1/README.md";
        SparkConf conf = new SparkConf().setAppName("SimpleApp");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> logData = sc.textFile(logFile).cache();

        long numAs = logData.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(final String s) throws Exception {
                return s.contains("a");
            }
        }).count();

        long numBs = logData.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(final String s) throws Exception {
                return s.contains("b");
            }
        }).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
    }
}
