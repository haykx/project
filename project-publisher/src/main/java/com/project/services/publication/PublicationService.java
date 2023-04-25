package com.project.services.publication;

import com.project.entities.response.PublicationResponse;
import com.project.services.discussion.DiscussionService;
import com.project.services.query.DiscussionQuery;
import com.project.services.query.SurveyQuery;
import com.project.services.survey.SurveyService;
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
        list.addAll(discussionService.search(new DiscussionQuery()));
        list.addAll(surveyService.search(new SurveyQuery()));
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
