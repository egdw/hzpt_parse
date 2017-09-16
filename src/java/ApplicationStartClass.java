import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by hdy on 2017/9/16.
 */
@SpringBootApplication
@ComponentScan("com.*")
public class ApplicationStartClass {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStartClass.class, args);
    }
}
