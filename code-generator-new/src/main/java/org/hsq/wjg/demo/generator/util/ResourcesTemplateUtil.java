package org.hsq.wjg.demo.generator.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by BF100177 on 2016/8/15.
 */
public class ResourcesTemplateUtil {
    /**
     * 初始化路径
     * */
    static {
        //采用classPath方式读取路径
        Velocity.addProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.addProperty("input.encoding", "UTF-8");
        Velocity.addProperty("output.encoding", "UTF-8");
        Velocity.init();
    }
    /**
     * 设置模版
     * @param parameters
     * @param path
     * @return
     * */
    public static String getTemplateContent(Map<String,Object> parameters, String path)throws RuntimeException{
        VelocityContext context = new VelocityContext();
        StringWriter writer = new StringWriter();
        String result = null;
       try{
           if(!isEmpty(parameters)){
               for(Map.Entry<String, Object> entry : parameters.entrySet()){
                   context.put(entry.getKey(), entry.getValue());
               }
           }
           Template template = Velocity.getTemplate(path);
           template.merge(context, writer);
           result = writer.toString();
       }finally {
           try {
               writer.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       return result;
    }
    public static boolean isEmpty(Map map) {
        return null == map || map.size() <= 0;
    }

}
