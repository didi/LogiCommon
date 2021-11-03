package com.didiglobal.logi.elasticsearch.client.config;

import com.didiglobal.logi.elasticsearch.client.request.index.putalias.PutAliasNode;
import com.didiglobal.logi.elasticsearch.client.request.index.putalias.PutAliasType;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodessetting.ESClusterNodesSettingResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.getalias.ESIndicesGetAliasResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.getindex.ESIndicesGetIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.gettemplate.ESIndicesGetTemplateResponse;
import com.didiglobal.logi.elasticsearch.client.response.setting.template.TemplateConfig;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.didiglobal.logi.elasticsearch.client.TestUtils.client;
import static com.didiglobal.logi.elasticsearch.client.TestUtils.index;

public class ConfigTest {


    @Test
    public void getClusterSetting() throws Exception {
        ESClusterNodesSettingResponse resp;
        resp = client.admin().cluster()
                .prepareNodesSetting()
                .execute().get();

        String respStr = resp.toString();
        assert respStr.contains("cluster_name");
    }


    @Test
    public void updateClusterSetting() throws Exception {
        assert client.admin().cluster()
                .prepareUpdateSettings()
                .addPersistent("cluster.routing.rebalance.enable", "all")
                .execute().get().getAcknowledged();

        assert client.admin().cluster()
                .prepareUpdateSettings()
                .addPersistent("cluster.routing.rebalance.enable", "none")
                .execute().get().getAcknowledged();


    }


    @Test
    public void error() throws Exception {
//        client.admin().indices().prepareDeleteIndex(index).execute().get();
//        client.admin().indices().prepareDeleteTemplate(index).execute().get().getAcknowledged();
//        client.admin().indices().preparePutIndex(index).execute().get().getAcknowledged();
    }


    @Test
    public void deleteTemplate() throws Exception {
        Map<String, String> settings = new HashMap<>();
        settings.put("index.number_of_shards", "1");

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setTemplate(index);
        templateConfig.setOrder(10l);
        templateConfig.setSetttings(settings);

        assert client.admin().indices().preparePutTemplate(index).setTemplateConfig(templateConfig).execute().get().getAcknowledged();
        assert client.admin().indices().prepareDeleteTemplate(index).execute().get().getAcknowledged();

        boolean isException = false;
        try {
            client.admin().indices().prepareDeleteTemplate(index).execute().get().getAcknowledged();
        } catch (Throwable t) {
            isException = true;
        }

        assert isException;
    }


    @Test
    public void putTemplate() throws Exception {
        try {
            client.admin().indices().prepareDeleteTemplate(index).execute().get().getAcknowledged();
        } catch (Throwable t) {
        }


        Map<String, String> settings = new HashMap<>();
        settings.put("index.number_of_shards", "1");

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setTemplate(index);
        templateConfig.setOrder(10l);
        templateConfig.setSetttings(settings);

        assert client.admin().indices().preparePutTemplate(index).setTemplateConfig(templateConfig).execute().get().getAcknowledged();


        ESIndicesGetTemplateResponse response = client.admin().indices().prepareGetTemplate("*").execute().get();
        assert response.toString().contains("number_of_shards");
        assert client.admin().indices().prepareDeleteTemplate(index).execute().get().getAcknowledged();
    }

    @Test
    public void putIndex() throws Exception {
        try {
            client.admin().indices().prepareDeleteIndex(index).execute().get();
        } catch (Throwable t) {
        }

        assert client.admin().indices().preparePutIndex(index).execute().get().getAcknowledged();
        assert client.admin().indices().prepareDeleteIndex(index).execute().get().getAcknowledged();
    }


    @Test
    public void deleteIndex() throws Exception {
        try {
            client.admin().indices().prepareDeleteIndex(index).execute().get();
        } catch (Throwable t) {
        }

        assert client.admin().indices().preparePutIndex(index).execute().get().getAcknowledged();
        assert client.admin().indices().prepareDeleteIndex(index).execute().get().getAcknowledged();

        boolean isException = false;
        try {
            client.admin().indices().prepareDeleteIndex(index).execute().get();
        } catch (Throwable t) {
            isException = true;
        }

        assert isException;
    }


    @Test
    public void getAlias() throws Exception {
        ESIndicesGetAliasResponse response = client.admin().indices().prepareAlias(null).execute().get();
        System.out.println(response);
    }

    @Test
    public void putAlias() throws Exception {
        try {
            client.admin().indices().preparePutIndex(index).execute().get().getAcknowledged();
        } catch (Throwable t) {
        }

        PutAliasNode putAliasNode = new PutAliasNode();
        putAliasNode.setIndex(index);
        putAliasNode.setAlias("helloworld_test");
        putAliasNode.setType(PutAliasType.ADD);
        assert client.admin().indices().preparePutAlias().addPutAliasNode(putAliasNode).execute().get().getAcknowledged();

        ESIndicesGetAliasResponse response = client.admin().indices().prepareAlias(null).execute().get();
        assert response.toString().contains("helloworld_test");
        assert client.admin().indices().prepareDeleteIndex(index).execute().get().getAcknowledged();
    }


    @Test
    public void getIndexConfig() throws Exception {
        try {
            client.admin().indices().preparePutIndex(index).execute().get().getAcknowledged();
        } catch (Throwable t) {
        }
        ESIndicesGetIndexResponse response = client.admin().indices().prepareGetIndex(index).settings(true).execute().get();
        System.out.println(response);

        assert client.admin().indices().prepareDeleteIndex(index).execute().get().getAcknowledged();
    }


    @Test
    public void updateIndexSettings() throws Exception {
        try {
            client.admin().indices().preparePutIndex(index).execute().get().getAcknowledged();
        } catch (Throwable t) {
        }
        assert client.admin().indices().prepareUpdateSettings(index).addSettings("number_of_replicas", "0").execute().get().getAcknowledged();
        ESIndicesGetIndexResponse response = client.admin().indices().prepareGetIndex(index).settings(true).execute().get();
        System.out.println(response);
        assert response.toString().contains("number_of_replicas");

        assert client.admin().indices().prepareDeleteIndex(index).execute().get().getAcknowledged();
    }


    @Test
    public void exists() throws Exception {
        try {
            client.admin().indices().preparePutIndex(index).execute().get().getAcknowledged();
        } catch (Throwable t) {
        }

        assert client.admin().indices().prepareExists(index).execute().get().isExists();

        assert client.admin().indices().prepareDeleteIndex(index).execute().get().getAcknowledged();

        assert !client.admin().indices().prepareExists(index).execute().get().isExists();

    }
}

