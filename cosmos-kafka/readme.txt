主要功能 参照pom.xml依赖

1. vertx

2. kafka

https://github.com/thomas-xun-yi/kafka-streams-examples/blob/4.0.0-post/src/main/java/io/confluent/examples/streams/AnomalyDetectionLambdaExample.java

3. avro

======
【异常1】

/15 17:41:31 INFO zkclient.ZkEventThread: Starting ZkClient event thread.
18/03/15 17:41:32 ERROR producer.SyncProducer: Producer connection to localhost:9092 unsuccessful
java.net.ConnectException: Connection refused: connect
	at sun.nio.ch.Net.connect0(Native Method)
	at sun.nio.ch.Net.connect(Net.java:454)
	at sun.nio.ch.Net.connect(Net.java:446)
	at sun.nio.ch.SocketChannelImpl.connect(SocketChannelImpl.java:648)
	at kafka.network.BlockingChannel.connect(Unknown Source)
	at kafka.producer.SyncProducer.connect(Unknown Source)
	at kafka.producer.SyncProducer.getOrMakeConnection(Unknown Source)
	at kafka.producer.SyncProducer.kafka$producer$SyncProducer$$doSend(Unknown Source)
	at kafka.producer.SyncProducer$$anonfun$send$1$$anonfun$apply$mcV$sp$1.apply$mcV$sp(Unknown Source)
	at kafka.producer.SyncProducer$$anonfun$send$1$$anonfun$apply$mcV$sp$1.apply(Unknown Source)
	at kafka.producer.SyncProducer$$anonfun$send$1$$anonfun$apply$mcV$sp$1.apply(Unknown Source)
	at kafka.metrics.KafkaTimer.time(Unknown Source)
	at kafka.producer.SyncProducer$$anonfun$send$1.apply$mcV$sp(Unknown Source)
	at kafka.producer.SyncProducer$$anonfun$send$1.apply(Unknown Source)
	at kafka.producer.SyncProducer$$anonfun$send$1.apply(Unknown Source)
	at kafka.metrics.KafkaTimer.time(Unknown Source)
	at kafka.producer.SyncProducer.send(Unknown Source)
	at kafka.producer.async.DefaultEventHandler.kafka$producer$async$DefaultEventHandler$$send(Unknown Source)
	at kafka.producer.async.DefaultEventHandler$$anonfun$dispatchSerializedData$2.apply(Unknown Source)
	at kafka.producer.async.DefaultEventHandler$$anonfun$dispatchSerializedData$2.apply(Unknown Source)
	at scala.collection.TraversableLike$WithFilter$$anonfun$foreach$1.apply(TraversableLike.scala:772)
	at scala.collection.mutable.HashMap$$anonfun$foreach$1.apply(HashMap.scala:98)
	at scala.collection.mutable.HashMap$$anonfun$foreach$1.apply(HashMap.scala:98)

解决方案：
原因：是在集群环境里执行了单机的命令
解决方法：

1)vi producer.properties

#metadata.broker.list=localhost:9092
metadata.broker.list=192.168.11.222:9092,192.168.11.221:9092,192.168.11.220:9092
2)./kafka-console-producer.sh --broker-list IP1:9092,IP2:9092,IP3:9092 --topic topic1

参照：http://blog.csdn.net/zhangketuan/article/details/46806883


18/03/15 18:01:48 INFO utils.VerifiableProperties: Verifying properties
18/03/15 18:01:48 INFO utils.VerifiableProperties: Property metadata.broker.list is overridden to localhost:9092
18/03/15 18:01:48 INFO utils.VerifiableProperties: Property request.timeout.ms is overridden to 30000
18/03/15 18:01:48 INFO utils.VerifiableProperties: Property client.id is overridden to group1
18/03/15 18:01:48 INFO client.ClientUtils$: Fetching metadata from broker id:0,host:localhost,port:9092 with correlation id 5 for 1 topic(s) Set(topic1)

=============================================================================================

Kafka Connect VS Producer Consumer

其实Kafka Connect的本质就是将Kafka Client包装了一层，并对开发者提供统一的实现接口。
Source Connector对应Producer，Sink Connector对应Consumer。

Kafka Connect的优点

1.对开发者提供了统一的实现接口
2.开发，部署和管理都非常方便，统一
3.使用分布式模式进行水平扩展，毫无压力
4.在分布式模式下可以通过Rest Api提交和管理Connectors
5.对offset自动管理，只需要很简单的配置，而不像Consumer中需要开发者处理
6.流式/批式处理的支持
- - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

kafka技术内幕


作者：半兽人
链接：http://www.orchome.com/kafka/index?spm=a2c4e.11153940.blogcont69501.5.6b095fb6c6Iifu
来源：OrcHome
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

kafka节点之间如何复制备份的？
kafka消息是否会丢失？为什么？
kafka最合理的配置是什么？
kafka的leader选举机制是什么？
kafka对硬件的配置有什么要求？
kafka的消息保证有几种方式？





























