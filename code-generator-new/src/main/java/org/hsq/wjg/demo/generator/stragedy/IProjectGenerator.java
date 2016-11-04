package org.hsq.wjg.demo.generator.stragedy;

import org.hsq.wjg.demo.generator.pojo.ProjectInfo;

import java.io.IOException;

/**
 * Created by wujigang on 2016/11/1.
 */
public interface IProjectGenerator {
    void generator(ProjectInfo projectInfo) throws IOException;
}
