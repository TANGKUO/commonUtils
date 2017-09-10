package com.tangkuo.cn.abstractclass;
/** 
 * 抽象类
 *   饮料 (容量, 打开())  
 *      -- Tea  (type)
 *      -- Milk (name)
 * 1 包含抽象方法的类一定是抽象类
 * 2 抽象类用来表达抽象概念的
 * 3 抽象类只能被继承, 不能直接实例
 * 4 抽象类可以定义变量, 引用具体类型实例
 * 5 抽象类的具体实例是多态的
 * 6 继承抽象类时候, 必须实现父类的抽象方法.
 *   抽象类规定了子类的行为!
 */
public class abstractDemo1 {
	public static void main(String[] args) {
		//new Drink();//编译错误, 不能创建抽象类实例
		Drink d1 = new Tea(); 
		Drink d2 = new Milk();
		drink(d1); drink(d2);//是饮料都能喝
	}
	public static void drink(Drink d){//喝多态饮料
		System.out.println(d.capacity);//0
		d.open();
	}
}
abstract class Drink{//饮料
	int capacity;//容量
	public abstract void open();//抽象打开
}
class Tea extends Drink{
	public void open() {//抽象方法的实现
		System.out.println("打开茶!"); 
	}
}
class Milk extends Drink{
	public void open() {
		System.out.println("打开奶瓶!"); 
	}
}

