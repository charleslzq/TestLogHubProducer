package main;

import com.github.charleslzq.aliyun.loghub.annotation.LogHubProject;
import com.github.charleslzq.aliyun.loghub.annotation.LogHubStore;
import com.github.charleslzq.aliyun.loghub.annotation.LogHubTopic;
import com.github.charleslzq.aliyun.loghub.producer.LogHubProducerTemplate;
import com.github.charleslzq.aliyun.loghub.producer.LogHubProjectTemplate;
import com.github.charleslzq.aliyun.loghub.producer.LogHubStoreTemplate;
import com.github.charleslzq.aliyun.loghub.producer.LogHubTopicTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TestController {

    @Autowired
    private LogHubProducerTemplate logHubProducerTemplate;

    @Autowired
    @LogHubProject(project = "charleslzqsample")
    private LogHubProjectTemplate logHubProjectTemplate;

    @Autowired
    @LogHubStore(project = "charleslzqsample", store = "testloghub")
    private LogHubStoreTemplate logHubStoreTemplate;

    @Autowired
    @LogHubTopic(project = "charleslzqsample", store = "testloghub", topic = "topic")
    private LogHubTopicTemplate logHubTopicTemplate;

    @RequestMapping(value = "/log", method = POST)
    public void test(@RequestParam String content) {
        logHubProducerTemplate.send("charleslzqsample", "testloghub", "default", Arrays.asList(content));
        logHubProjectTemplate.send("testloghub", "project", Arrays.asList(content));
        logHubStoreTemplate.send("store", Arrays.asList(content));
        logHubTopicTemplate.send(Arrays.asList(content));
    }
}
