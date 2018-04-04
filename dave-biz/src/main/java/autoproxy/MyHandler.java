package autoproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author chen.z
 * @date 2018/4/3
 */
public class MyHandler implements InvocationHandler {

    private Object object;

    public MyHandler() {
    }

    public MyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before ...");
        Object result = method.invoke(object, args);
        System.out.println("after ...");
        return null;
    }

}
