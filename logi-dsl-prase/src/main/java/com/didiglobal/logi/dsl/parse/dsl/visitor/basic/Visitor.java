package com.didiglobal.logi.dsl.parse.dsl.visitor.basic;

import com.didiglobal.logi.dsl.parse.dsl.ast.DslNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.aggr.*;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.key.FieldNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.key.IdentityNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.key.StringNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.logic.*;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeList;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.script.Script;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.JsonNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ObjectNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.QueryStringValueNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.StringListNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.query.*;
import com.didiglobal.logi.dsl.parse.dsl.ast.root.*;

public interface Visitor {
    public void visit(DslNode node);

    /*---- root -----*/
    public void visit(From node);

    public void visit(Size node);

    public void visit(Sort node);

    public void visit(Source node);

    public void visit(Timeout node);

    public void visit(Highlight node);

    public void visit(IndicesBoost node);

    public void visit(MinScore node);

    public void visit(PostFilter node);

    public void visit(Profile node);

    public void visit(TerminateAfter node);

    public void visit(TrackScores node);

    public void visit(Rescore node);

    public void visit(Fields node);

    public void visit(IndexConstraints node);

    public void visit(Body node);

    public void visit(ScriptFields node);

    public void visit(Suggest node);

    public void visit(Docs node);

    public void visit(Scroll node);

    public void visit(ScrollId node);

    public void visit(Index node);

    public void visit(IgnoreUnavailable node);

    public void visit(FieldDataFields node);

    public void visit(Type node);

    public void visit(SearchType node);

    public void visit(Explain node);

    public void visit(QueryBinary node);

    public void visit(TimeFieldName node);

    public void visit(Title node);

    public void visit(FieldFormatMap node);

    /*---- common -----*/
    public void visit(FieldNode node);

    public void visit(IdentityNode node);

    public void visit(StringNode node);

    public void visit(JsonNode node);

    public void visit(ObjectNode node);


    public void visit(NodeMap node);

    public void visit(NodeList node);

    public void visit(StringListNode node);



    /*---- logic -----*/
    public void visit(Bool node);

    public void visit(Must node);

    public void visit(Should node);

    public void visit(MustNot node);

    public void visit(Filter node);

    public void visit(Not node);

    public void visit(And node);

    public void visit(Or node);



    /*---- aggs -----*/
    public void visit(Aggs node);

    public void visit(AvgBucket node);

    public void visit(DateHistoGram node);

    public void visit(Sum node);

    public void visit(AggrTerms node);

    public void visit(Avg node);

    public void visit(TopHits node);

    public void visit(Cardinality node);

    public void visit(ValueCount node);

    public void visit(Max node);

    public void visit(Min node);

    public void visit(ScriptedMetric node);

    public void visit(BucketSelector node);

    public void visit(AggrRange node);

    public void visit(Stats node);

    public void visit(Percentiles node);

    public void visit(AggrNested node);

    public void visit(PercentileRanks node);

    public void visit(SignificantTerms node);

    public void visit(Histogram node);

    public void visit(Children node);

    public void visit(Sampler node);

    public void visit(ReverseNested node);

    public void visit(AggrGeoBounds node);

    public void visit(AggrGeoCentroid node);

    public void visit(AggrGeoDistance node);

    public void visit(AggrGeohashGrid node);

    public void visit(AggrMissing node);

    public void visit(DateRange node);

    public void visit(ExtendedStats node);

    public void visit(Global node);

    public void visit(IpRange node);


    /*---- query -----*/
    public void visit(Query node);

    public void visit(Term node);

    public void visit(Terms node);

    public void visit(Range node);

    public void visit(Filtered node);

    public void visit(Queryquery node);

    public void visit(Match node);

    public void visit(Wildcard node);

    public void visit(Prefix node);

    public void visit(Exists node);

    public void visit(Regexp node);

    public void visit(HasParent node);

    public void visit(GeoShape node);

    public void visit(QueryString node);

    public void visit(FunctionScore node);

    public void visit(ConstantScore node);

    public void visit(GeoBbox node);

    public void visit(Nested node);

    public void visit(GeoDistance node);

    public void visit(MatchAll node);

    public void visit(MatchNone node);

    public void visit(Ids node);

    public void visit(HasChild node);

    public void visit(Missing node);

    public void visit(MatchPhrasePrefix node);

    public void visit(MoreLikeThis node);

    public void visit(MultiMatch node);

    public void visit(Fuzzy node);

    public void visit(Boosting node);

    public void visit(Positive node);

    public void visit(Negative node);

    public void visit(Common node);

    public void visit(DisMax node);

    public void visit(HighlightQuery node);

    /*---- script -----*/
    public void visit(Script node);

    public void visit(QueryStringValueNode node);
}
