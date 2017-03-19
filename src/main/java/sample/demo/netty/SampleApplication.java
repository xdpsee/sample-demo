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
        System.out.println(new Date(context.getStartupDate()).toLocaleString());
        System.out.println("Starting ...");
        final ServerManager serverManager = new ServerManager();
        serverManager.startup();
        System.out.println("Startup Okay.");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown...");
            serverManager.shutdown();
            System.out.println("Shutdown Okay.");
        }));

    }

}


