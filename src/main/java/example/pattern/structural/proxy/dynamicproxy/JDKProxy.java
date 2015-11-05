package example.pattern.structural.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK的动态代理机制只能代理实现了接口的类
 * 这是一个代理类（增强CountImpl实现类）
 *
 * @author Administrator
 */
public class JDKProxy implements InvocationHandler {
  private Object target;

  /**
   * 绑定委托对象并返回一个代理类
   *
   * @param target
   */
  public Object bind(Object target) {
    this.target = target;
    //取得代理对象
    //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
    return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }

  @Override
  /**
   * 调用方法
   */
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("事物开始");
    //执行方法
    Object result = method.invoke(target, args);
    System.out.println("事物结束");
    return result;
  }
}