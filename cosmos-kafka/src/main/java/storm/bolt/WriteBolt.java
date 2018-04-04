package storm.bolt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;


public class WriteBolt extends BaseBasicBolt {

    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(WriteBolt.class);

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        //获取上一个组件所声明的Field
        String text = input.getStringByField("write");
        try {
            System.out.println("结果：text="+text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }



}