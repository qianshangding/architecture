//package com.architecture.third.mybatis.monitor;
//
//import org.apache.ibatis.cache.CacheKey;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.SqlCommandType;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Properties;
//
///**
// * 数据库时延监控
// * Created by zhengyu on 2016/10/19.
// */
//@Intercepts({
//        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
//        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
//                RowBounds.class, ResultHandler.class}),
//        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
//                RowBounds.class, ResultHandler.class,
//                CacheKey.class, BoundSql.class})})
//public class SqlMonitorManager implements Interceptor {
//
//    private static final Logger logger = LoggerFactory.getLogger(SqlMonitorManager.class);
//
//    private static int MAX_ALLOW_TIME = 1000;
//
//    private boolean sqlMonitor = true;
//
//
//    //超过一定时间答应Error日志
//    private int allowTime = MAX_ALLOW_TIME;
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//
//        if (!sqlMonitor) {
//            return invocation.proceed();
//        }
//
//        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
//
//        if (mappedStatement == null) {
//            return invocation.proceed();
//        }
//
//        String sqlId = mappedStatement.getId();
//        Transaction transaction = Cat.newTransaction("Nova.Sql", sqlId);
//
//        //获取SQL类型
//        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
//        transaction.addData("method", sqlCommandType.name().toLowerCase());
//
//        Object returnValue = null;
//        int resultCode = 200;
//        long start = System.currentTimeMillis();
//
//        try {
//            returnValue = invocation.proceed();
//
//            transaction.setStatus(Message.SUCCESS);
//        } catch (Exception e) {
//
//            Event event = Cat.newEvent("Nova.Sql.Error", sqlId);
//            if (NullMessage.EVENT != event) {
//                event.setStatus(e);
//                event.addData("exception", e.getMessage());
//                completeEvent(event);
//                transaction.addChild(event);
//                transaction.setStatus(e.getClass().getSimpleName());
//            }
//
//            resultCode = -1;
//            throw e;
//
//        } finally {
//            long end = System.currentTimeMillis();
//            long time = end - start;
//
//            if (time >= allowTime) {
//                logger.error(sqlId + ",code=" + resultCode + ",sql消耗时间=" + time);
//            } else if (time > 30) {
//                logger.warn(sqlId + ",code=" + resultCode + ",sql消耗时间=" + time);
//            } else {
//                logger.info(sqlId + ",code=" + resultCode + ",sql消耗时间=" + time);
//            }
//            transaction.complete();
//        }
//        return returnValue;
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//        if (properties == null) {
//            return;
//        }
//        if (properties.containsKey("sql_monitor")) {
//            String value = properties.getProperty("sql_monitor");
//            if (Boolean.TRUE.toString().equals(value)) {
//                this.sqlMonitor = true;
//            }
//        }
//        if (properties.containsKey("overtime_print_error")) {
//            String value = properties.getProperty("overtime_print_error");
//
//            if (value == null || "".equals(value.trim())) {
//                allowTime = MAX_ALLOW_TIME;
//            }
//            try {
//                allowTime = Integer.parseInt(value.trim());
//            } catch (Exception ex) {
//                allowTime = MAX_ALLOW_TIME;
//            }
//        }
//    }
//
//    private void completeEvent(Event event) {
//        AbstractMessage message = (AbstractMessage) event;
//        message.setCompleted(true);
//    }
//
//}