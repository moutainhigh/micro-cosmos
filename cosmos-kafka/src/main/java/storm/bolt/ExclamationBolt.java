package storm.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @Author:thomas
 * @Date: 2018/4/3 19:46
 * @Description:
 */
public class ExclamationBolt extends BaseRichBolt {

    OutputCollector _collector;
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector  = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        System.out.println("word:"+tuple.getString(0)+"!!!");
        this._collector.emit(tuple,new Values(tuple.getString(0)+"!!!"));
        this._collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
