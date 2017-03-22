package sample.demo.netty;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.demo.netty.core.Configs;
import sample.demo.netty.core.ServerManager;

import java.util.Date;

public class SampleApplication {

    public static void main(String[] args) throws Exception {
        Configs.load();

        ApplicationContext context
                = new ClassPathXmlApplicationContext("application-context.xml");

        System.out.println("--------------------------------------->");
        System.out.println(new Date(context.getStartupDate()));
        final ServerManager serverManager = new ServerManager();
        serverManager.startup();
        System.out.println("startup okay.");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("shutdown...");
            serverManager.shutdown();
            System.out.println("shutdown okay.");
        }));

    }

}


