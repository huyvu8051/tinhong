package com.bobvu.tinhong.cassandra.match;

import com.bobvu.tinhong.cassandra.profile.ProfileResponse;
import com.bobvu.tinhong.cassandra.profile.UserMapper;
import com.bobvu.tinhong.cassandra.user.Gender;
import com.bobvu.tinhong.cassandra.user.Passion;
import com.bobvu.tinhong.elasticsearch.user.User;
import com.bobvu.tinhong.elasticsearch.user.UserESRepository;
import lombok.AllArgsConstructor;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final UserESRepository userESRepository;

    private final double defaultRadius = 999999999999d;
    private final UserMapper userMapper;
    private final ElasticsearchOperations esOperations;


    @Override
    public PageResponse<ProfileResponse> findAllSuitablePerson(com.bobvu.tinhong.cassandra.user.User user, FindSuitablePersonRequest request) {

        GeoDistanceQueryBuilder geoDistanceQueryBuilder = new GeoDistanceQueryBuilder("location")
                .distance(user.getDistance() + "", DistanceUnit.KILOMETERS)
                .point(request.getLat(), request.getLon());

        int year = Calendar.getInstance().get(Calendar.YEAR);

        RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("yearOfBirth")
                .gte(year - user.getMaxAge())
                .lte(year - user.getMinAge());


        BoolQueryBuilder boolQueryBuilderGender = new BoolQueryBuilder();

        for (Gender gender : user.getGenderToShow()) {
            boolQueryBuilderGender.should(new MatchQueryBuilder("gender", gender));
        }

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
                .filter(geoDistanceQueryBuilder)
                .filter(boolQueryBuilderGender)
                .filter(rangeQueryBuilder)
                .minimumShouldMatch(1);

        for (Passion passion : user.getPassions()) {
            boolQueryBuilder.should(new MatchQueryBuilder("passions", passion));
        }

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build();

        SearchHits<User> searchHits = esOperations.search(searchQuery, User.class, IndexCoordinates.of("user"));

        long totalHits = searchHits.getTotalHits();
        List<ProfileResponse> collect = searchHits.get().map(e -> userMapper.toDto(e.getContent())).collect(Collectors.toList());

        PageResponse<ProfileResponse> response = new PageResponse<>();
        response.setList(collect);
        response.setTotalElement(totalHits);

        return response;


    }

}
