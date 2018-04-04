package storm;

/**
 * @Author:thomas
 * @Date: 2018/4/3 21:02
 * @Description:
 */

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordSplit {

    /**
     * 发射数据源
     *
     * @author Administrator
     *
     */
    public static class WordReaderSpout extends BaseRichSpout {

        SpoutOutputCollector _collector;
        InputStreamReader isr;
        boolean isEnd = false;

        @Override
        public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
            String inputFile = "D:/input/people.txt";
            try {
                isr = new InputStreamReader(new FileInputStream(inputFile));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            _collector = collector;
        }

        @Override
        public void nextTuple() {
            // 读取文件一次就无需再读了
            if (isEnd) {
                System.out.println("*******Spout is over, no neccessary to emit.*********");
                return;
            }

            // 读本地文件，一行发射一次
            String line = null;
            try (BufferedReader br = new BufferedReader(isr)) {
                while ((line = br.readLine()) != null) {
                    System.out.printf("line : %s", line);
                    _collector.emit(new Values(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                isEnd = true; // 文件读完了
            }

        }

        @Override
        public void ack(Object id) {
        }

        @Override
        public void fail(Object id) {
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("word"));
        }

    }

    /**
     * 处理上面发射过来的数据源
     *
     * @author Administrator
     *
     */
    public static class SplitWordBolt extends BaseRichBolt {

        private OutputCollector outputCollector;

        @Override
        public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
            this.outputCollector = collector;
        }

        @Override
        public void execute(Tuple tuple) {
            String sentence = tuple.getString(0); // 一次处理一行
            IKSegmenter ikSeg = new IKSegmenter(new StringReader(sentence), true); // 智能分词
            try {
                for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
                    outputCollector.emit(new Values(lexeme.getLexemeText()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("word"));
        }

    }

    /**
     * 统计从上面取到的分词，关键人名统计后的放到result.txt
     *
     * @author Administrator
     *
     */
    public static class WordCountBolt extends BaseBasicBolt {
        Map<String, Integer> counts = new HashMap<String, Integer>();
        String out;
        Set<String> keyName = new HashSet<>();

        @Override
        public void prepare(Map stormConf, TopologyContext context) {
            out = "D:/out/result.txt";

            // 判断result文件是否已存在，是则先删掉，以待新建
            File outFile = new File(out);
            if (outFile.exists()) {
                outFile.delete();
            }

            // 读字典文件并放入一个set，以备参照set里的人名读取统计结果，写入result.txt文件
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(WordSplit.class.getClassLoader().getResourceAsStream("myext.dic")))) {
                String peopleName = null;
                while ((peopleName = br.readLine()) != null) {
                    keyName.add(peopleName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void execute(Tuple tuple, BasicOutputCollector collector) {
            String word = tuple.getString(0); // 每次统计一个分词
            Integer count = counts.get(word);
            if (count == null)
                count = 0;
            count++;
            counts.put(word, count);
            collector.emit(new Values(word, count));
        }

        @Override
        public void cleanup() {
            // 最后时刻，输出关键人名的统计结果到result.txt文件
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)))) {
                for (Map.Entry<String, Integer> keyWord : counts.entrySet()) {
                    if (keyName.contains(keyWord.getKey())) {
                        bw.write(keyWord.getKey() + " : " + keyWord.getValue() + "\r\n");
                        bw.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("word", "count"));
        }
    }

    /**
     * 输出分词结果到本地文件，过程数据放在tmp文件
     *
     * @author Administrator
     *
     */
    public static class SaveOutput extends BaseRichBolt {
        String temp;

        @Override
        public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
            temp = "D:/out/tmp" + System.currentTimeMillis();

            // 判断tmp文件是否已存在，是则先删掉，以待新建
            File tempFile = new File(temp);
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }

        @Override
        public void execute(Tuple input) {
            // 从上面获取分词的累计次数
            String name = input.getStringByField("word");
            Integer counts = input.getIntegerByField("count");

            // 输出分词统计过程追加到tmp文件
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp, true)))) {
                bw.write(name + " : " + counts + "\r\n");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            // TODO Auto-generated method stub

        }
    }

    public static void main(String[] args) throws Exception {

        TopologyBuilder builder = new TopologyBuilder(); // 新建一个拓扑

        builder.setSpout("spout", new WordReaderSpout(), 1); // 设置数据源

        // 读取spout里的数据，进行split处理
        builder.setBolt("split", new SplitWordBolt(), 10).shuffleGrouping("spout");

        // 读取split后的数据，进行count处理
        builder.setBolt("count", new WordCountBolt(), 10).fieldsGrouping("split", new Fields("word"));

        // 保存计算结果
        builder.setBolt("save", new SaveOutput(), 10).allGrouping("count");

        Config conf = new Config();
        conf.setDebug(true);

        conf.setMaxTaskParallelism(1);

        // 有参数则到集群跑，没有则在本地跑
        if (args != null && args.length > 0) {
            conf.setNumWorkers(3);
            StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
        } else {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("word-split", conf, builder.createTopology());
            Thread.sleep(300000); // 5分钟后自动结束
            cluster.shutdown();
        }
    }

}
