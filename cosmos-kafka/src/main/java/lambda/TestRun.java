package lambda;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestRun {

	public static void main(String[] args) {
		new Thread(
				()->{
					System.out.println("thomas");
				}
				).start();
		
		////////////////////////////////////////
		String[] strArr = {"java8","new","features","stream","api"};
		
		System.out.println(Stream.of(strArr).filter(w->w.length()>3).count());
		System.out.println(Arrays.asList(strArr).parallelStream().filter(w->w.length()>3).count());
		
		//////////////////////////////////////////////////////////////
		List<Transaction> transactions = Arrays.asList();
			
		Arrays.stream(strArr);
		Stream.of(strArr);
		Arrays.asList(strArr).stream();
		
		IntStream.of(new int[]{1,2,3}).forEach(System.out::println);
		
		////////////////////
	     Collectors.toList();
	     Collectors.toCollection(ArrayList::new);
	     
	     String[] words = new String[]{"a","b","c"};
	     System.out.println(Arrays.asList(words).stream().map(word -> word.toUpperCase()).collect(Collectors.toList()));
	     System.out.println(Arrays.asList(words).stream().map(String::toUpperCase).collect(Collectors.toList()));
	     
	     Double[] num = new Double[]{-1.0,-2.0,-3.0,-4.0};
	     System.out.println(Arrays.asList(num).stream().map(Math::abs).collect(Collectors.toList()));
		
	     
	     Stream<List<Integer>> inputStream = Stream.of(
	    		 Arrays.asList(1),
	    		 Arrays.asList(2, 3),
	    		 Arrays.asList(4, 5, 6)
	    		 );
	    		Stream<Integer> outputStream = inputStream.
	    		flatMap((childList) -> childList.stream());
	    		
	    		System.out.println(outputStream.collect(Collectors.toList()));

		//forEach 是 terminal 操作，因此它执行后，Stream 的元素就被“消费”掉了，
		// 你无法对一个 Stream 进行两次 terminal 运算。下面的代码是错误的：
		try{
			IntStream stream = IntStream.of(1,2,3,4);
			stream.forEach(tmp-> System.out.println(tmp));
			// stream.forEach(tmp-> System.out.println(tmp));
		}catch (Exception e){
			e.printStackTrace();
		}

//		相反，具有相似功能的 intermediate 操作 peek 可以达到上述目的。如下是出现在该 api javadoc 上的一个示例。
//		清单 13. peek 对每个元素执行操作并返回一个新的 Stream
		System.out.println(
				Stream.of("two","three","four")
						.filter(tmp->tmp.length()>3)
						.peek(tmp-> System.out.println("length >3:"+tmp))
						.map(String::toUpperCase)
						.peek(tmp-> System.out.println("touppercase:"+tmp))
						.collect(Collectors.toList())
		);
		//打印结果
//		length >3:three
//		touppercase:THREE
//		length >3:four
//		touppercase:FOUR
//		[THREE, FOUR]

//		这里比较重点的是它的返回值类型：Optional。这也是一个模仿 Scala 语言中的概念，
//		作为一个容器，它可能含有某值，或者不包含。使用它的目的是尽可能避免 NullPointerException。


		//Stream函数式操作流元素集合
		List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
//		System.out.println(
//				Stream.of(nums)
//				.filter(num1->num1 != null)
//				.peek(System.out::println)
//				.distinct()
//				.skip(2)
//				.limit(4)
////				.mapToInt(Math.sqrt(2))
//				.peek(System.out::println)
//				.collect(Collectors.toList())

//		);

		System.out.println("求和："+nums
				                 .stream()//转成Stream
				                 .filter(team -> team!=null)//过滤
				                 .distinct()//去重
				                 .mapToInt(num2->num2*2)//map操作
				                 .skip(2)//跳过前2个元素
				                 .limit(4)//限制取前4个元素
				                 .peek(System.out::println)//流式处理对象函数
				                 .sum());//

		/////
		Optional<String> optional = Optional.ofNullable("thomas");
		if(optional.isPresent()){
			System.out.println(optional.get());
			optional.orElse("thomas is not here");
		}
		Optional<String> name = Optional.ofNullable("thomas");
		//map方法执行传入的lambda表达式参数对Optional实例的值进行修改。
		//为lambda表达式的返回值创建新的Optional实例作为map方法的返回值。
		Optional<String> upperName = name.map((value) -> value.toUpperCase());
		System.out.println(upperName.orElse("No value found"));


		//flatMap与map（Function）非常类似，区别在于传入方法的lambda表达式的返回类型。
//map方法中的lambda表达式返回值可以是任意类型，在map函数返回之前会包装为Optional。
//但flatMap方法中的lambda表达式返回值必须是Optionl实例。
		upperName = name.flatMap((value) -> Optional.of(value.toUpperCase()));
		System.out.println(upperName.orElse("No value found"));//输出SANAULLA

		//filter方法检查给定的Option值是否满足某些条件。
//如果满足则返回同一个Option实例，否则返回空Optional。
		Optional<String> longName = name.filter((value) -> value.length() > 6);
		System.out.println(longName.orElse("The name is less than 6 characters"));//输出Sanaulla

//另一个例子是Optional值不满足filter指定的条件。
		Optional<String> anotherName = Optional.of("Sana");
		Optional<String> shortName = anotherName.filter((value) -> value.length() > 6);
//输出：name长度不足6字符
		System.out.println(shortName.orElse("The name is less than 6 characters"));





	}

}
