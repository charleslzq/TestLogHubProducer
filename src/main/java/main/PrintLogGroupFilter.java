package main;

import com.aliyun.openservices.log.common.LogGroupData;
import com.github.charleslzq.aliyun.loghub.listener.filter.LogGroupFilter;
import org.springframework.stereotype.Component;

@Component(value = "print")
public class PrintLogGroupFilter implements LogGroupFilter{
    @Override
    public boolean accept(LogGroupData logGroupData) {
        System.out.println(logGroupData.GetLogGroup().getTopic());
        return true;
    }
}
