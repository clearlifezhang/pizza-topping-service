package com.clearlife.datainject;


import com.clearlife.datainject.redis.config.RedisConfig;
import com.clearlife.datainject.redis.entity.UserToppingCount;
import com.clearlife.datainject.redis.repository.UserToppingCountDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
@SpringBootApplication
@RestController
@RequestMapping("/Topping")
public class DataInjectApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DataInjectApplication.class, args);
    }
}

/*

    static final String[] TOPPINGS = {"MushRoom", "Avocado", "Pepperoni",
            "Mushroom",
            "Extra cheese",
            "Sausage",
            "Onion",
            "Black olives",
            "Green pepper",
            "Fresh garlic"};
    static final String[] USERS = {"John", "Tom", "Jack", "Jason", "Wong", "Tian"};

      // MyRedisService service = new MyRedisService();
        /*
        PlaceHolder to retrieve data from API call
        RestTemplate restTemplate = new RestTemplate();
        CustomResponse response = restTemplate.getForObject("https://api.example.com/data", CustomResponse.class);
        Here I mock the streamed data from API, and compute the metrics and persist in Redis

UserToppingCountDao dao = new UserToppingCountDao();
        do {
                Thread.sleep(500);
                int topIdx = ThreadLocalRandom.current().nextInt(TOPPINGS.length-1);
                int userIdx = ThreadLocalRandom.current().nextInt(USERS.length-1);
                int count = ThreadLocalRandom.current().nextInt(100);
                UserToppingCount ninput = new UserToppingCount( 1, USERS[userIdx]+"_" + TOPPINGS[topIdx], count);
                dao.save(ninput);
                } while (true);
 */