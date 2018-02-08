package HW1;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args){

        Handler handler = new Handler();
        IHandle handleProxy = (IHandle) Proxy.newProxyInstance(Handler.class.getClassLoader(), Handler.class.getInterfaces(),
                new MyInvocationHandler(handler));
        handleProxy.start();

    }

}
