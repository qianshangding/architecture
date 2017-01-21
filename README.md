## Architecture
----

##目录
* [arch-mybatis](#arch-mybatis)
* [arch-redis](#arch-redis)
* [arch-thrid](#arch-thrid)

arch-mybatis
-----------
arch-mybatis主要实现打印sql和sql执行阀值超过多少后打印错误日志。在项目中使用分两步
第一：引入依赖
```xml
        <dependency>
            <groupId>com.architecture.mybatis</groupId>
            <artifactId>arch-mybatis</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
```
第二：在mybaits加入plugin
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
