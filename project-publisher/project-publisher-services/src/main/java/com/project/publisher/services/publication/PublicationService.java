package com.project.publisher.services.publication;

import com.project.publisher.response.PublicationResponse;
import com.project.publisher.services.discussion.DiscussionService;
import com.project.publisher.services.survey.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationService {

    private final DiscussionService discussionService;
    private final SurveyService surveyService;

    public List<PublicationResponse> getAll() {
        List<PublicationResponse> list = new ArrayList<>();
        list.addAll(discussionService.search(null));
        list.addAll(surveyService.search(null));
        list.sort((o1, o2) -> {
            if (o1.getCreated().isAfter(o2.getCreated())) {
                return -1;
            } else if (o1.getCreated().isBefore(o2.getCreated())) {
                return 1;
            } else {
                return 0;
            }
        });
        return list;
    }

}
