
package com.example.demo_es.repository;

import com.example.demo_es.entity.Address;
import com.example.demo_es.repository.CutomerAddressRespository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CutomerAddressRespositoryImpl implements CutomerAddressRespository {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Address> TermByName(String name) {
        //指定查询条件
        SearchQuery searchQuery=new NativeSearchQuery(QueryBuilders.termQuery("name",name));
        //查询条件    实体类的类型
        List<Address> addresses = elasticsearchTemplate.queryForList(searchQuery, Address.class);
        return addresses;
    }


    @Override
    public List<Address> TermByNameBySources(String name, String... sources) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("name", name)) //指定查询条件
                .withFields(sources)  //指定返回字段
                .build();
        List<Address> addresses = elasticsearchTemplate.queryForList(searchQuery, Address.class);
        return addresses;
    }

    @Override
    public List<Address> TermByNameBySourcesByOrder(String filed, String name, String... sources) {
        FieldSortBuilder sortBuilder = SortBuilders.fieldSort(filed).order(SortOrder.ASC);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("name", name))
                .withFields(sources)
                .withSort(sortBuilder)  //排序
                .build();
        List<Address> books = elasticsearchTemplate.queryForList(searchQuery, Address.class);
        return  books;
    }

//    @Override
//    public List<Address> TermByNameBySourcesByOrderByPage(Integer page, Integer size, String filed, String name, String... sources) {
//
//        FieldSortBuilder sortBuilder = SortBuilders.fieldSort(filed).order(SortOrder.ASC);
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.termQuery("name", name))
//                .withFields(sources)
//                .withSort(sortBuilder)  //排序
//                .withPageable(PageRequest.of(page,size))
//                .build();
//        List<Address> books = elasticsearchTemplate.queryForList(searchQuery, Address.class);
//        return  books;
//    }
//
//    @Override
//    public List<Address> TermByNameBySourcesByOrderByPageByFilter(String user_id, Integer page, Integer size, String filed, String name, String... sources) {
//        FieldSortBuilder sortBuilder = SortBuilders.fieldSort(filed).order(SortOrder.ASC);
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withFilter(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("content",user_id)))
//                .withQuery(QueryBuilders.termQuery("name", name))
//                .withFields(sources)
//                .withSort(sortBuilder)  //排序
//                .withPageable(PageRequest.of(page,size))
//                .build();
//        List<Address> books = elasticsearchTemplate.queryForList(searchQuery, Address.class);
//        return  books;
//    }
//
    @Override
    public List<Address> TermByHighLightByPage(Integer page, Integer size, String name) {
        HighlightBuilder.Field nameField = new HighlightBuilder
                .Field("*")
                .preTags("<span style='color:red'>")
                .postTags("</span>").requireFieldMatch(false);
        //构建查询条件
        SearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("name",name))
                .withPageable(PageRequest.of(page,size))
                .withHighlightFields(nameField)
                .build();                                                                                  //对查询过后的结果做再处理
        AggregatedPage<Address> books = elasticsearchTemplate.queryForPage(nativeSearchQuery, Address.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                //获取相应后的所有文档
                SearchHits searchHits = response.getHits();
                SearchHit[] hits = searchHits.getHits();
                List<Address> books = new ArrayList<Address>();
                //遍历所有的文档
                for (SearchHit hit : hits) {
                    Address book = new Address();
                    //原始map
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    book.setZip_code(sourceAsMap.get("zip_code").toString());
                    book.setPhone(sourceAsMap.get("phone").toString());
                    book.setName(sourceAsMap.get("name").toString());
                    book.setUser_id(sourceAsMap.get("user_id").toString());
                    book.setLocal(sourceAsMap.get("local").toString());
                    //高亮  筛选高亮字段
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    if (highlightFields.get("name") != null) {
                        String nameHighlight = highlightFields.get("name").getFragments()[0].toString();
                        book.setName(nameHighlight);
                    }
                    if (highlightFields.get("phone") != null) {
                        String contentHighlight = highlightFields.get("phone").getFragments()[0].toString();
                        book.setPhone(contentHighlight);
                    }
                    if (highlightFields.get("zip_code") != null) {
                        String contentHighlight = highlightFields.get("zip_code").getFragments()[0].toString();
                        book.setZip_code(contentHighlight);
                    }
                    books.add(book);
                }
                return new AggregatedPageImpl<T>((List<T>)books);
            }
        });
        return books.getContent();


    }
}
