package com.ironman.client;

import com.ironman.client.tools.ParamsTools;

import org.junit.Test;


/**
 * Created by Administrator on 2015/5/18.
 */
public class TestParamsTool {

    @Test
    public void test_parmas_tools(){
        TestParamsObject object  = new TestParamsObject();
        object.x = 1;
        object.xxx = 3;
        object.setFuck("fff");
        object.setY(0.222f);
        System.out.print(ParamsTools.toUrl(object));

    }
}
