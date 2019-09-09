package com.example.boot_start_learning.chapter14;

import javax.script.ScriptEngineManager;

/**
 * @author no one
 * @version 1.0
 * @since 2019/08/02
 */
public class ListScriptEngines {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        manager.getEngineFactories().forEach(scriptEngineFactory -> {
            String engineName = scriptEngineFactory.getEngineName();
            String languageName = scriptEngineFactory.getLanguageName();
            String version = scriptEngineFactory.getEngineVersion();
            System.out.println("Engine name: " + engineName + " language: " + languageName + " version: " + version);
        });
    }
}
