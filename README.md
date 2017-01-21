## Architecture
----

##目录
* [arch-mybatis](#arch-mybatis)
* [arch-redis](#arch-redis)
* [arch-thrid](#arch-thrid)

横线
-----------
```xml
    <plugins>
        <plugin interceptor="com.architecture.mybatis.monitor.SqlMonitorPlugin">
            <!--是否监控-->
            <property name="sql_monitor" value="true"/>
            <!--是否打印SQL语句，默认为false -->
            <property name="sql_show" value="true"/>
            <!--sql时延的阈值,超过会打印error日志数据   -->
            <property name="overtime_print_error" value="300"/>
        </plugin>
    </plugins>
```
