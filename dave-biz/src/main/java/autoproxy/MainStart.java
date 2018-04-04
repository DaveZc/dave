package autoproxy;

import java.lang.reflect.Proxy;

/**
 *
 * @author chen.z
 * @date 2018/4/3
 */
public class MainStart {

    public static void main(String[] args) {

        IMyInterfac iMyInterfac =  new MyInterfaceImpl();

        MyHandler proxyHandler = new MyHandler(iMyInterfac);

        IMyInterfac newIMyInterfac = (IMyInterfac)Proxy.newProxyInstance(
                proxyHandler.getClass().getClassLoader(),
                iMyInterfac.getClass().getInterfaces(),
                proxyHandler);

        newIMyInterfac.doSomething();

    }

}
