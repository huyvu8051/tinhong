package com.bobvu.tinhong.config;

import com.bobvu.tinhong.cassandra.profile.UserMapper;
import com.bobvu.tinhong.cassandra.repository.UserRepository;
import com.bobvu.tinhong.cassandra.user.Gender;
import com.bobvu.tinhong.cassandra.user.Passion;
import com.bobvu.tinhong.cassandra.user.User;
import com.bobvu.tinhong.elasticsearch.user.UserESRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {


    private final UserRepository userRepository;
    private final UserESRepository userESRepository;

    private final UserMapper userMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Random rand = new Random();
        Faker faker = new Faker(new Locale("vi-VN"));

        List<User> users = new ArrayList<>();
        List<com.bobvu.tinhong.elasticsearch.user.User> esUsers = new ArrayList<>();


        for (int i = 0; i < 1000; i++) {

            int i1 = rand.nextInt(40);

            int i2 = rand.nextInt(3);
            int i3 = rand.nextInt(3);

            int i4 = rand.nextInt(3);
            int i5 = rand.nextInt(3);

            com.bobvu.tinhong.cassandra.user.User build = User.builder().username(faker.internet().emailAddress()).fullName(faker.name().fullName()).avatar(getRandomImgUrl()).gender(i1 % 2 == 0 ? Gender.FEMALE : Gender.MALE)

                    .pictures(Arrays.asList(getRandomImgUrl(), "https://i.imgur.com/lpzlDQv.jpg", "https://i.imgur.com/pAZ8UUQ.jpg", "https://i.imgur.com/qfLln70.jpg"))
                    .lat(37.421998333333335 + i2 - i3)
                    .lon(-122.084 + i4 - i5).passions(Arrays.asList(Passion.getRandom(), Passion.getRandom(), Passion.getRandom(), Passion.getRandom(), Passion.getRandom())).about("Chúng ta của hiện tại" + i).company("Heo khô đi những kỉ niệm xưa kia" + i).jobDescription("Ngày mai, người luyến lưu theo những giấc mơ từng có " + i).school("Liệu có ta?" + i).roles(Arrays.asList("user"))

                    .distance(5000).maxAge(70).minAge(15).genderToShow(Arrays.asList(Gender.FEMALE, Gender.MALE)).yearOfBirth(1982 + i1).build();
            users.add(build);


            esUsers.add(userMapper.toESEntity(build));

        }

//        userRepository.saveAll(users);
//        userESRepository.saveAll(esUsers);


    }

    private String getRandomImgUrl() {
        List<String> imgUrls = new ArrayList<>();

        imgUrls.add("https://i.imgur.com/lpzlDQv.jpg");
        imgUrls.add("https://i.imgur.com/pAZ8UUQ.jpg");
        imgUrls.add("https://i.imgur.com/qfLln70.jpg");
        imgUrls.add("https://i.pinimg.com/564x/ba/ef/8c/baef8c84567c3ebadce92439a04bd387.jpg");
        imgUrls.add("https://i.pinimg.com/564x/8b/f5/62/8bf5626b6ac0d1be07fd63d0ad413012.jpg");
        imgUrls.add("https://i.pinimg.com/564x/61/e8/e7/61e8e7e634c7cf80dc255c93578ea56c.jpg");
        imgUrls.add("https://i.pinimg.com/564x/eb/56/11/eb5611b3a7e67fab261dee88df25b19a.jpg");
        imgUrls.add("https://i.pinimg.com/564x/80/3a/4e/803a4ecc6a5eb15a165218d481b3e077.jpg");
        imgUrls.add("https://i.pinimg.com/originals/f3/60/31/f36031f081af4dcfd7f25476c7e9f56b.jpg");
        imgUrls.add("https://i.pinimg.com/564x/9d/69/1d/9d691dc8d36789197f69431fadfb77e7.jpg");
        imgUrls.add("https://i.pinimg.com/564x/4a/5e/7d/4a5e7deed283397043b3c394fb00f565.jpg");
        imgUrls.add("https://i.pinimg.com/564x/5d/a2/70/5da27003cba271fe295a787ea3fba410.jpg");

        Random random = new Random();
        int rand = random.nextInt(6);

        return imgUrls.get(rand);
    }
}