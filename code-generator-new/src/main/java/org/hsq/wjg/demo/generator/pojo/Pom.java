package org.hsq.wjg.demo.generator.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujigang on 2016/11/1.
 */
public class Pom {
    private Dependency parent;
    private Module module;
    private List<Dependency> dependencies;
    private List<String> subModules;

    public Dependency getParent() {
        return parent;
    }

    public void setParent(Dependency parent) {
        this.parent = parent;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public List<String> getSubModules() {
        return subModules;
    }

    public void setSubModules(List<String> subModules) {
        this.subModules = subModules;
    }

    public void addSubModules(String ... subModuleNames) {
        if (null == subModuleNames || subModuleNames.length <= 0) {
            return ;
        }
        if (null == this.subModules) {
            subModules = new ArrayList<String>();
        }
        for (String subModule : subModuleNames) {
            subModules.add(subModule);
        }
    }

    public void addDepenedency(Dependency dependency) {
        if (null == dependencies || dependencies.size() <= 0) {
            dependencies = new ArrayList<Dependency>();
        }
        dependencies.add(dependency);
    }

    public void addDepenedency(List<Dependency> dependencies) {
        if (null == dependencies || dependencies.size() <= 0) {
            dependencies = new ArrayList<Dependency>();
        }
        this.dependencies.addAll(dependencies);
    }
}
