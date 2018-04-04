package storm.topology;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.testing.TestWordSpout;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;
import storm.bolt.ExclamationBolt;

/**
 * @Author:thomas
 * @Date: 2018/4/3 19:58
 * @Description:
 * http://mangocool.com/1490838498837.html
 */
public class ExclamationTopology {
    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        //实例化TopologyBuilder类。
        TopologyBuilder builder = new TopologyBuilder();
        // 设置喷发节点并分配并发数，该并发数将会控制该对象在集群中的线程数。
        builder.setSpout("word",new TestWordSpout(),10);
        // 设置数据处理节点并分配并发数。指定该节点接收喷发节点的策略为随机方式。
        builder.setBolt("exclaim1",new ExclamationBolt(),3).shuffleGrouping("word");
        builder.setBolt("exclaim2",new ExclamationBolt(),2).shuffleGrouping("exclaim1");

        Config config = new Config();
        config.setDebug(true);


        if(args != null && args.length > 3){
            config.setNumWorkers(3);
            StormSubmitter.submitTopology(args[0],config,builder.createTopology());
        }else{
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test",config,builder.createTopology());
            Utils.sleep(10000);
            cluster.killTopology("test");
            cluster.shutdown();
        }
    }
}
