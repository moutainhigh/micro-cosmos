package guanwang.demo;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * Created by thomas on 2018/2/8 14:59.
 */
public class Pipe {

    public static void main(String[] args) throws Exception{
        Properties prop = new Properties();
        prop.put(StreamsConfig.APPLICATION_ID_CONFIG,"stream-pipe");
        prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        prop.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        prop.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());

        final StreamsBuilder builder = new StreamsBuilder();

        builder.stream("streams-plaintext-input").to("streams-pipe-output");

        final Topology topology  = builder.build();
        final KafkaStreams streams = new KafkaStreams(topology,prop);

        final CountDownLatch latch = new CountDownLatch(1);

        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook"){
            @Override
            public void run() {
                streams.close();;
                latch.countDown();
            }
        });

        try{
            streams.start();
            latch.await();
        }catch (Exception e){
            System.exit(1);
        }
        System.exit(0);

    }
}
