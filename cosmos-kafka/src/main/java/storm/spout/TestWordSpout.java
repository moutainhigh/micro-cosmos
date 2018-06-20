package storm.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;
import java.util.Random;

/**
 * @Author:thomas
 * @Date: 2018/4/3 19:39
 * @Description:
 */
public class TestWordSpout extends BaseRichSpout{

    SpoutOutputCollector _collector;

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        _collector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        Utils.sleep(100);
        final String[] words = new String[]{"thomas","nathan","mike","jackson","golda","bertels"};
        final Random random = new Random();
        final String word = words[random.nextInt(words.length)];
        System.out.println("发送数据 word="+word);
        _collector.emit(new Values(word));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }
}
