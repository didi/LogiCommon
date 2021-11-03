package com.didiglobal.logi.elasticsearch.client.parser.query_string.parser;


import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.QSFieldNode;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.QSNode;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.QSValueNode;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op.*;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op.common.QSBinaryOpNode;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op.common.QSSingleOpNode;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op.logic.QSANDNode;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op.logic.QSORNode;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op.logic.QSParenNode;

import java.util.Stack;

public class QSNodeBuilder {

    private Stack<QSNode> stack = new Stack<>();

    /**
     * 转换节点
     *
     * @return QSNode
     * @throws ParseException e
     */
    public QSNode toNode() throws ParseException {
        if (stack.size() != 1) {
            throw new ParseException("parse error");
        }

        return stack.pop();
    }

    /**
     * 添加上一级节点
     *
     * @param node node
     * @param boost boost
     * @throws ParseException e
     */
    public void addParen(QSNode node, Token boost) throws ParseException {
        QSParenNode pn = new QSParenNode("");
        pn.setNode(node);
        pn.setBoost(boost);

        putStack(pn);
    }

    /**
     * 添加字段节点
     *
     * @param src src
     * @throws ParseException e
     */
    public void addFieldEq(Token src) throws ParseException {
        QSFieldNode qfn = new QSFieldNode(getImage(src));
        QSEQNode qen = new QSEQNode();
        qen.setLeft(qfn);

        putStack(qen);
    }

    /**
     * 添加And节点
     *
     * @param src src
     * @throws ParseException e
     */
    public void addAnd(Token src) throws ParseException {
        QSANDNode an = new QSANDNode(getImage(src));

        putStack(an);
    }

    /**
     * 添加OR节点
     *
     * @param src src
     * @throws ParseException e
     */
    public void addOr(Token src) throws ParseException {
        QSORNode rn = new QSORNode(getImage(src));

        putStack(rn);
    }

    /**
     * 添加-节点
     *
     * @param src src
     * @throws ParseException e
     */
    public void addMinus(Token src) throws ParseException {
        QSMinusNode mn = new QSMinusNode(getImage(src));

        putStack(mn);
    }

    /**
     * 添加NOT节点
     *
     * @param src src
     * @throws ParseException e
     */
    public void addNot(Token src) throws ParseException {
        QSNotNode nn = new QSNotNode(getImage(src));

        putStack(nn);
    }

    /**
     * 添加加号节点
     *
     * @param src src
     * @throws ParseException e
     */
    public void addPlus(Token src) throws ParseException {
        QSPlusNode pn = new QSPlusNode(getImage(src));

        putStack(pn);
    }

    /**
     * 添加值节点
     *
     * @param src src
     * @param fuzzySlop fuzzySlop
     * @param boost boost
     * @throws ParseException e
     */
    public void addValue(Token src, Token fuzzySlop, Token boost) throws ParseException {
        QSValueNode vn = new QSValueNode(getImage(src), getImage(fuzzySlop), getImage(boost));

        putStack(vn);
    }

    /**
     * 添加range 范围查询节点
     *
     * @param start start
     * @param end end
     * @param startInc startInc
     * @param endInc endInc
     * @throws ParseException e
     */
    public void addRange(Token start, Token end, boolean startInc, boolean endInc) throws ParseException {
        QSValueNode lvn = new QSValueNode(getImage(start), null, null);
        QSValueNode rvn = new QSValueNode(getImage(end), null, null);

        QSRangeNode rangeNode = new QSRangeNode(startInc, endInc);
        rangeNode.setLeft(lvn);
        rangeNode.setRight(rvn);

        putStack(rangeNode);
    }

    /**
     * 得到token中具体内容
     *
     * @param t t
     * @return String
     * @throws ParseException e
     */
    private String getImage(Token t) throws ParseException {
        if (t == null) {
            return null;
        }
        return StringUtils.discardEscapeChar(t.image);
    }

    /**
     * 节点压入栈
     *
     * @param node node
     * @throws ParseException e
     */
    private void putStack(QSNode node) throws ParseException {

        // 如果栈为空，则入栈
        if (stack.size() == 0) {
            stack.push(node);
            return;
        }

        // 如果节点没有解析完整，则先入栈
        if (!node.completeParse()) {
            stack.push(node);
            return;
        }

        // 得到栈顶元素，如果已经是解析完整的节点
        QSNode sn = stack.peek();
        if (sn.completeParse()) {
            stack.pop();

            // 两个连续的常量，默认中间是OR
            QSORNode or = new QSORNode("OR");
            or.setLeft(sn);
            or.setRight(node);

            putStack(or);
            return;
        }

        // 如果栈顶元素是单目运算符节点，则弹出栈顶元素，并把当前元素设置到单目运算符节点，并再次入栈
        if (sn instanceof QSSingleOpNode) {
            stack.pop();
            ((QSSingleOpNode) sn).setNode(node);

            putStack(sn);
            return;
        }

        // 如果栈顶元素是双目原酸符节点，则弹出栈顶元素
        if (sn instanceof QSBinaryOpNode) {
            stack.pop();
            QSBinaryOpNode bop = (QSBinaryOpNode) sn;

            // 设置双目运算符的左右节点，再压入栈
            if (bop.getNeedValue() == 2) {
                if (stack.size() == 0) {
                    throw new ParseException("parse error, size==0 node:" + node.getSource());
                }
                QSNode left = stack.pop();

                ((QSBinaryOpNode) sn).setLeft(left);
            }

            bop.setRight(node);

            putStack(bop);
            return;
        }

        throw new ParseException("parse error, node:" + node.getSource());
    }

}
