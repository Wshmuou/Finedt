package com.fine.finedt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.fine.finedt.baidu.retrofit.ResultDTO;
import com.fine.finedt.baidu.retrofit.jsonUtils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test1(){
//        String obj={"success":0,"msg":"保存成功","size":0,"mlatLngs":null}"";
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setSuccess(0);
        resultDTO.setMsg("保存成功");
        resultDTO.setSize(0);
//        resultDTO.setMlatLngs(null);
        String json=JSONObject.toJSONString(resultDTO);
//        Log.d("hhh",json);
        ResultDTO resultDTO1=JSON.parseObject(json,ResultDTO.class);
    }

    @Test
    public void test2(){
        String json = "{\"fail\":null,\"updateTimestamp\":\"1484096131863\",\"productName\":\"json测试\"}";
        String r="{\"success\":0,\"msg\":\"保存成功\",\"size\":0,\"mlatLngs\":null}";
        String s1="[{\"eid\":1,\"ename\":\"drugs\",\"latlngses\":[{\"aid\":3,\"mlatitude\":31.08338313831131,\"mlongitude\":121.52507801515839,\"mposition\":2},{\"aid\":1,\"mlatitude\":31.08291923558821,\"mlongitude\":121.52336225163432,\"mposition\":0},{\"aid\":2,\"mlatitude\":31.081403804089838,\"mlongitude\":121.5241258113178,\"mposition\":1}]},{\"eid\":2,\"ename\":\"Gaddy\",\"latlngses\":[{\"aid\":6,\"mlatitude\":31.081991423281682,\"mlongitude\":121.52373055689341,\"mposition\":2},{\"aid\":4,\"mlatitude\":31.08347591858151,\"mlongitude\":121.5241527604831,\"mposition\":0},{\"aid\":5,\"mlatitude\":31.0820996685221,\"mlongitude\":121.52294004804463,\"mposition\":1}]},{\"eid\":3,\"ename\":\"HFCS\",\"latlngses\":[{\"aid\":9,\"mlatitude\":31.083653747177053,\"mlongitude\":121.52511394737878,\"mposition\":2},{\"aid\":7,\"mlatitude\":31.081249166850615,\"mlongitude\":121.52427852325451,\"mposition\":1},{\"aid\":8,\"mlatitude\":31.083290357949625,\"mlongitude\":121.52260767500594,\"mposition\":0}]},{\"eid\":4,\"ename\":\"Hadji\",\"latlngses\":[{\"aid\":11,\"mlatitude\":31.083267162844916,\"mlongitude\":121.52290411582423,\"mposition\":0},{\"aid\":12,\"mlatitude\":31.0836305521619,\"mlongitude\":121.52502411682778,\"mposition\":2},{\"aid\":10,\"mlatitude\":31.081728541465186,\"mlongitude\":121.52466479462379,\"mposition\":1}]},{\"eid\":5,\"ename\":\"48884\",\"latlngses\":[{\"aid\":15,\"mlatitude\":31.08356096708217,\"mlongitude\":121.52509598126858,\"mposition\":2},{\"aid\":13,\"mlatitude\":31.081055869944283,\"mlongitude\":121.52414377742802,\"mposition\":1},{\"aid\":14,\"mlatitude\":31.083305821349597,\"mlongitude\":121.52267055639165,\"mposition\":0}]},{\"eid\":6,\"ename\":\"Jeff huh\",\"latlngses\":[{\"aid\":17,\"mlatitude\":31.083437260146706,\"mlongitude\":121.52290411582423,\"mposition\":0},{\"aid\":16,\"mlatitude\":31.083584162114466,\"mlongitude\":121.52532055764608,\"mposition\":2},{\"aid\":18,\"mlatitude\":31.08172080963594,\"mlongitude\":121.52388326883012,\"mposition\":1}]},{\"eid\":7,\"ename\":\"585\",\"latlngses\":[{\"aid\":20,\"mlatitude\":31.083553235403468,\"mlongitude\":121.52541937125217,\"mposition\":2},{\"aid\":21,\"mlatitude\":31.082857381719013,\"mlongitude\":121.52275140388754,\"mposition\":0},{\"aid\":19,\"mlatitude\":31.081473390764593,\"mlongitude\":121.52472767600949,\"mposition\":1}]}] ";

        String s2="{\"success\":0,\"msg\":\"下載成功\",\"size\":0,\"mlatLngs\":[{\"eid\":1,\"ename\":\"drugs\",\"latlngses\":[{\"aid\":2,\"mlatitude\":31.081403804089838,\"mlongitude\":121.5241258113178,\"mposition\":1},{\"aid\":1,\"mlatitude\":31.08291923558821,\"mlongitude\":121.52336225163432,\"mposition\":0},{\"aid\":3,\"mlatitude\":31.08338313831131,\"mlongitude\":121.52507801515839,\"mposition\":2}]},{\"eid\":2,\"ename\":\"Gaddy\",\"latlngses\":[{\"aid\":6,\"mlatitude\":31.081991423281682,\"mlongitude\":121.52373055689341,\"mposition\":2},{\"aid\":4,\"mlatitude\":31.08347591858151,\"mlongitude\":121.5241527604831,\"mposition\":0},{\"aid\":5,\"mlatitude\":31.0820996685221,\"mlongitude\":121.52294004804463,\"mposition\":1}]},{\"eid\":3,\"ename\":\"HFCS\",\"latlngses\":[{\"aid\":9,\"mlatitude\":31.083653747177053,\"mlongitude\":121.52511394737878,\"mposition\":2},{\"aid\":7,\"mlatitude\":31.081249166850615,\"mlongitude\":121.52427852325451,\"mposition\":1},{\"aid\":8,\"mlatitude\":31.083290357949625,\"mlongitude\":121.52260767500594,\"mposition\":0}]},{\"eid\":4,\"ename\":\"Hadji\",\"latlngses\":[{\"aid\":10,\"mlatitude\":31.081728541465186,\"mlongitude\":121.52466479462379,\"mposition\":1},{\"aid\":11,\"mlatitude\":31.083267162844916,\"mlongitude\":121.52290411582423,\"mposition\":0},{\"aid\":12,\"mlatitude\":31.0836305521619,\"mlongitude\":121.52502411682778,\"mposition\":2}]},{\"eid\":5,\"ename\":\"48884\",\"latlngses\":[{\"aid\":15,\"mlatitude\":31.08356096708217,\"mlongitude\":121.52509598126858,\"mposition\":2},{\"aid\":14,\"mlatitude\":31.083305821349597,\"mlongitude\":121.52267055639165,\"mposition\":0},{\"aid\":13,\"mlatitude\":31.081055869944283,\"mlongitude\":121.52414377742802,\"mposition\":1}]},{\"eid\":6,\"ename\":\"Jeff huh\",\"latlngses\":[{\"aid\":17,\"mlatitude\":31.083437260146706,\"mlongitude\":121.52290411582423,\"mposition\":0},{\"aid\":16,\"mlatitude\":31.083584162114466,\"mlongitude\":121.52532055764608,\"mposition\":2},{\"aid\":18,\"mlatitude\":31.08172080963594,\"mlongitude\":121.52388326883012,\"mposition\":1}]},{\"eid\":7,\"ename\":\"585\",\"latlngses\":[{\"aid\":21,\"mlatitude\":31.082857381719013,\"mlongitude\":121.52275140388754,\"mposition\":0},{\"aid\":19,\"mlatitude\":31.081473390764593,\"mlongitude\":121.52472767600949,\"mposition\":1},{\"aid\":20,\"mlatitude\":31.083553235403468,\"mlongitude\":121.52541937125217,\"mposition\":2}]}]}";

        String s3="{\"success\":0,\"msg\":\"下載成功\",\"size\":0,\"mlatLngs\":[{\"eid\":1,\"ename\":\"drugs\",\"latlngses\":[{\"aid\":2,\"mlatitude\":31.081403804089838,\"mlongitude\":121.5241258113178,\"mposition\":1},{\"aid\":1,\"mlatitude\":31.08291923558821,\"mlongitude\":121.52336225163432,\"mposition\":0},{\"aid\":3,\"mlatitude\":31.08338313831131,\"mlongitude\":121.52507801515839,\"mposition\":2}]},{\"eid\":2,\"ename\":\"Gaddy\",\"latlngses\":[{\"aid\":6,\"mlatitude\":31.081991423281682,\"mlongitude\":121.52373055689341,\"mposition\":2},{\"aid\":4,\"mlatitude\":31.08347591858151,\"mlongitude\":121.5241527604831,\"mposition\":0},{\"aid\":5,\"mlatitude\":31.0820996685221,\"mlongitude\":121.52294004804463,\"mposition\":1}]},{\"eid\":3,\"ename\":\"HFCS\",\"latlngses\":[{\"aid\":9,\"mlatitude\":31.083653747177053,\"mlongitude\":121.52511394737878,\"mposition\":2},{\"aid\":7,\"mlatitude\":31.081249166850615,\"mlongitude\":121.52427852325451,\"mposition\":1},{\"aid\":8,\"mlatitude\":31.083290357949625,\"mlongitude\":121.52260767500594,\"mposition\":0}]},{\"eid\":4,\"ename\":\"Hadji\",\"latlngses\":[{\"aid\":10,\"mlatitude\":31.081728541465186,\"mlongitude\":121.52466479462379,\"mposition\":1},{\"aid\":11,\"mlatitude\":31.083267162844916,\"mlongitude\":121.52290411582423,\"mposition\":0},{\"aid\":12,\"mlatitude\":31.0836305521619,\"mlongitude\":121.52502411682778,\"mposition\":2}]},{\"eid\":5,\"ename\":\"48884\",\"latlngses\":[{\"aid\":15,\"mlatitude\":31.08356096708217,\"mlongitude\":121.52509598126858,\"mposition\":2},{\"aid\":14,\"mlatitude\":31.083305821349597,\"mlongitude\":121.52267055639165,\"mposition\":0},{\"aid\":13,\"mlatitude\":31.081055869944283,\"mlongitude\":121.52414377742802,\"mposition\":1}]},{\"eid\":6,\"ename\":\"Jeff huh\",\"latlngses\":[{\"aid\":17,\"mlatitude\":31.083437260146706,\"mlongitude\":121.52290411582423,\"mposition\":0},{\"aid\":16,\"mlatitude\":31.083584162114466,\"mlongitude\":121.52532055764608,\"mposition\":2},{\"aid\":18,\"mlatitude\":31.08172080963594,\"mlongitude\":121.52388326883012,\"mposition\":1}]},{\"eid\":7,\"ename\":\"585\",\"latlngses\":[{\"aid\":21,\"mlatitude\":31.082857381719013,\"mlongitude\":121.52275140388754,\"mposition\":0},{\"aid\":19,\"mlatitude\":31.081473390764593,\"mlongitude\":121.52472767600949,\"mposition\":1},{\"aid\":20,\"mlatitude\":31.083553235403468,\"mlongitude\":121.52541937125217,\"mposition\":2}]}]}";

        ResultDTO resultDTO = JSONObject.parseObject(r,ResultDTO.class, Feature.IgnoreAutoType);
        ResultDTO resultDTO2 = JSONObject.parseObject(s2,ResultDTO.class, Feature.IgnoreAutoType);
        ResultDTO resultDTO3 = JSONObject.parseObject(s3,ResultDTO.class, Feature.IgnoreAutoType);

        List<ResultDTO> resultDTO1 = JSONObject.parseArray(s1,ResultDTO.class);

        // ③忽略null输出
        System.out.println(JSON.parse(r));

        // ④
        System.out.println(JSONObject.toJSON(json));
    }

    @Test
    public void test3(){
        String s3="{\"success\":0,\"msg\":\"下載成功\",\"size\":0,\"mlatLngs\":[{\"eid\":1,\"ename\":\"drugs\",\"latlngses\":[{\"aid\":2,\"mlatitude\":31.081403804089838,\"mlongitude\":121.5241258113178,\"mposition\":1},{\"aid\":1,\"mlatitude\":31.08291923558821,\"mlongitude\":121.52336225163432,\"mposition\":0},{\"aid\":3,\"mlatitude\":31.08338313831131,\"mlongitude\":121.52507801515839,\"mposition\":2}]},{\"eid\":2,\"ename\":\"Gaddy\",\"latlngses\":[{\"aid\":6,\"mlatitude\":31.081991423281682,\"mlongitude\":121.52373055689341,\"mposition\":2},{\"aid\":4,\"mlatitude\":31.08347591858151,\"mlongitude\":121.5241527604831,\"mposition\":0},{\"aid\":5,\"mlatitude\":31.0820996685221,\"mlongitude\":121.52294004804463,\"mposition\":1}]},{\"eid\":3,\"ename\":\"HFCS\",\"latlngses\":[{\"aid\":9,\"mlatitude\":31.083653747177053,\"mlongitude\":121.52511394737878,\"mposition\":2},{\"aid\":7,\"mlatitude\":31.081249166850615,\"mlongitude\":121.52427852325451,\"mposition\":1},{\"aid\":8,\"mlatitude\":31.083290357949625,\"mlongitude\":121.52260767500594,\"mposition\":0}]},{\"eid\":4,\"ename\":\"Hadji\",\"latlngses\":[{\"aid\":10,\"mlatitude\":31.081728541465186,\"mlongitude\":121.52466479462379,\"mposition\":1},{\"aid\":11,\"mlatitude\":31.083267162844916,\"mlongitude\":121.52290411582423,\"mposition\":0},{\"aid\":12,\"mlatitude\":31.0836305521619,\"mlongitude\":121.52502411682778,\"mposition\":2}]},{\"eid\":5,\"ename\":\"48884\",\"latlngses\":[{\"aid\":15,\"mlatitude\":31.08356096708217,\"mlongitude\":121.52509598126858,\"mposition\":2},{\"aid\":14,\"mlatitude\":31.083305821349597,\"mlongitude\":121.52267055639165,\"mposition\":0},{\"aid\":13,\"mlatitude\":31.081055869944283,\"mlongitude\":121.52414377742802,\"mposition\":1}]},{\"eid\":6,\"ename\":\"Jeff huh\",\"latlngses\":[{\"aid\":17,\"mlatitude\":31.083437260146706,\"mlongitude\":121.52290411582423,\"mposition\":0},{\"aid\":16,\"mlatitude\":31.083584162114466,\"mlongitude\":121.52532055764608,\"mposition\":2},{\"aid\":18,\"mlatitude\":31.08172080963594,\"mlongitude\":121.52388326883012,\"mposition\":1}]},{\"eid\":7,\"ename\":\"585\",\"latlngses\":[{\"aid\":21,\"mlatitude\":31.082857381719013,\"mlongitude\":121.52275140388754,\"mposition\":0},{\"aid\":19,\"mlatitude\":31.081473390764593,\"mlongitude\":121.52472767600949,\"mposition\":1},{\"aid\":20,\"mlatitude\":31.083553235403468,\"mlongitude\":121.52541937125217,\"mposition\":2}]}]}";
        ResultDTO resultDTO3 = JSONObject.parseObject(s3,ResultDTO.class, Feature.IgnoreAutoType);
        jsonUtils.getElectricToCache(resultDTO3.getMlatLngs().get(0));
    }
}