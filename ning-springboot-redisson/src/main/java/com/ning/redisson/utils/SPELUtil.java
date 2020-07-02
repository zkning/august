package com.ning.redisson.utils;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * zkning spel
 */
public class SPELUtil {
    private static ExpressionParser expressionParser = new SpelExpressionParser();

    public static <T> T parse(String expressionStr, String[] parameterNames, Object[] args, Map<String, Object> varMap, Class<T> clazz) {
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariables(varMap);
        if (null != parameterNames) {
            for (int index = 0; index < parameterNames.length; index++) {
                evaluationContext.setVariable(parameterNames[index], args[index]);
            }
        }
        Expression expression = expressionParser.parseExpression(expressionStr);
        return expression.getValue(evaluationContext, clazz);
    }

    public static <T> T parse(String expressionStr, String[] parameterNames, Object[] args, Class<T> clazz) {
        return parse(expressionStr, parameterNames, args, new HashMap<>(), clazz);
    }

    public static <T> T parse(String expressionStr, Map<String, Object> varMap, Class<T> clazz) {
        return parse(expressionStr, null, null, varMap, clazz);
    }

    public static <T> T parse(String expressionStr, StandardEvaluationContext evaluationContext, Class<T> clazz) {
        Expression expression = expressionParser.parseExpression(expressionStr);
        return expression.getValue(evaluationContext, clazz);
    }

}
