package main;

import com.aliyun.openservices.log.common.Logs;
import com.github.charleslzq.aliyun.loghub.annotation.LogHubListener;
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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TestController {

    @Autowired
    private LogHubProducerTemplate logHubProducerTemplate;

    private LogHubProjectTemplate logHubProjectTemplate;

    private LogHubStoreTemplate logHubStoreTemplate;

    private LogHubTopicTemplate logHubTopicTemplate;

    @Autowired
    @LogHubProject(project = "charleslzqsample")
    public void setLogHubProjectTemplate(LogHubProjectTemplate logHubProjectTemplate) {
        this.logHubProjectTemplate = logHubProjectTemplate;
    }

    @Autowired
    public TestController(
            @LogHubStore(project = "charleslzqsample", store = "testloghub") LogHubStoreTemplate logHubStoreTemplate,
            @LogHubTopic(project = "charleslzqsample", store = "testloghub", topic = "topic") LogHubTopicTemplate logHubTopicTemplate
    ) {
        this.logHubStoreTemplate = logHubStoreTemplate;
        this.logHubTopicTemplate = logHubTopicTemplate;
    }

    @RequestMapping(value = "/log", method = POST)
    public void test(@RequestParam String content) {
        logHubProducerTemplate.send("charleslzqsample", "testloghub", "default", Arrays.asList(content));
        logHubProjectTemplate.send("testloghub", "project", Arrays.asList(content));
        logHubStoreTemplate.send("store", Arrays.asList(content));
        logHubTopicTemplate.send(Arrays.asList(content));
    }

    @LogHubListener(configName = "testConfig", groupFilterBeanNames = "print")
    public void handle(Logs.Log log) {
        System.out.println(log.getContentsList().stream()
                .collect(Collectors.toMap(
                        Logs.Log.Content::getKey,
                        Logs.Log.Content::getValue
                )));
    }
}
