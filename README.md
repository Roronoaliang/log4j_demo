# log4j

标签 ：常用框架

---

## log4j是什么，能做什么
　　log4j是一个开源的日志框架，通过log4j，可以控制**特定级别**的日志信息以**特定的格式**输送到**特定的终端**。即使用log4j能更加灵活和方便的输出日志信息，从而达到分析系统运行状况和记录线上问题的目的。

## 几个主要组件
　　log4j有几个主要的组件，如：loggers,appenders，layouts和levels等，这几个组件分别对应日志的写入、日志输出终端、日志的布局模式和日志的输出级别。

* **Logger**：Logger是日志记录器的抽象，负责日志的写入，在程序是通过操作该对象实例完成日志记录的功能。log4j的Logger体系可以有多个logger，并且能通过名称自动维护多个logger的继承关系，在Logger体系中，有唯一一个根logger对象，是所有logger的祖先，如果不指定子logger的配置项，那么子logger将会继承父logger的属性。

* **Appender**：Appender对象负责指定日志信息的输出目的地，log4j提供了如下几个常用的实现类：

|实现类|输出目的地|
|---|---|---|
|AsyncAppender|采用异步的方式记录日志
|WriterAppender|将日志信息以流的格式发送到任意指定的地方|
|ConsoleAppender|控制台|
|FileAppender|文件|
|DailyRollingFileAppender|输出到文件，每天都产生一个日志文件|
|RollingFileAppender|输出到文件,文件大小达到指定值时产生新的日志文件|


* **Layout**:Layout对象负责指定日志信息的输出格式，log4j提供了以下几种常用的布局格式：

|实现类|布局模式|
|---|---|
|HTMLLayout|以HTML表格形式布局|
|PatternLayout|以指定的格式进行布局，可以更灵活的指定布局模式|
|SimpleLayout|简单布局模式，包含日志信息的级别和信息字符串|
|TTCCLayout|包含日志产生的时间、线程、类别等信息|

当使用指定的格式布局时，采用特定的格式输出符号可以灵活的指定输出格式：

|参数|含义|
|---|---|
|%m|输出代码中指定的消息|
|%M|输出调用logger的方法名|
|%p|输出优先级|
|%r|输入自应用启动到输出该log信息耗费的毫秒数|
|%c|输出logger名字空间的全称，也可以加上{层数}指定具体的层数|
|%C|输出调用logger的全类名|
|%F|输出调用logger的源文件名
|%t|输出产生该日志线程的线程名|
|%n|输出一个回车换行符|
|%d|输出日志时间点的日期或时间，默认格式为ISO8601，也可以指定具体格式，比如:%d{yyyy MM dd HH:mm:ss.SSS}|
|%l|输出日志事件的发生的位置，包括类名、线程名以及所在的代码的行数|
|%x|按线程堆栈顺序输出日志（？）|
|%X|按线程映射表输入日志，常用于多个客户端连接一台服务器，方便区分是哪个客户端产生的日志。|

* **Level**：Level用来控制logger的日志级别，未指定level的logger将继承它最近的父logger的日志级别，根logger的默认记录级别是**INFO**。log4j自带了8种日志级别，级别从低到高分别是：

> 
ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF

* 其他重要对象如：**Filter**对象用来分析日志信息，过滤掉不需要记录的日志；**LogManager**对象用于管理日志框架，负责从系统级的配置文件或类中读取初始配置参数。

## 配置与使用

### log4j简单使用步骤

1. 导入相应的jar包
2. 编写配置文件，可以采用properties或xml格式，甚至可以直接采用java代码配置，但不建议。
3. 在代码中获取Logger对象，如果将配置文件放到classpath路径下则不需要显示读取，否则可以通过代码或其他配置如借助spring读取配置文件，设置log4j的环境。
```
BasicConfigurator.configure ()： 
PropertyConfigurator.configure (String configFilename)   
DOMConfigurator.configure ( String filename ) 
```
4. 调用logger对象的相应方法，写入不同级别的日志信息，如：

```    
private Logger logger = Logger.getLoger(Test.class);
public void method() {
    logger.info("this is a info information.");
}
```

### properties配置文件示例

　　log4j的配置主要是指定logger的level、定义appender和layout。
　　为Logger对象添加Appender对象的语法如下：
> 
Log4j.logger.[logger-name] = level, apppender1, appender2...n

　　为Appender对象添加layout对象的语法如下：
> Log4j.appender.[appender-name].layout = org.apache.log4j.XXLayout 

　　以下采用properties的配置方式实现(不具备实际应用参考意义)：定义三个logger对象，其中根logger输出debug以上级别的日志信息到控制台，子logger1输出INFO级别以上日志信息到文件中，且每天产生一个新文件，子logger2输出error级别以上日志信息到文件中，超过4KB则新建一个日志文件。

```
#配置根logger,指定level、appender
log4j.rootLogger = DEBUG,stout
#指定Appender stout的输出终端类型和layout
log4j.appender.stout = org.apache.log4j.ConsoleAppender
log4j.appender.stout.Target = System.out
log4j.appender.stout.layout = org.apache.log4j.PatternLayout
#指定具体的输出格式
log4j.appender.stout.layout.conversionPattern = %d{yyyy MM dd HH:mm:ss} [Level:%p] %m [Method:%M]%n


#配置子logger，当在com.liang.log4j_demo.service包下的类中使用logger并且没有指定具体的子logger名称时使用该logger
log4j.logger.com.liang.log4j_demo.service = INFO,dailyRollingFile
#设置不从父logger继承appender,默认为true
log4j.additivity.com.liang.log4j_demo.service = FALSE
log4j.appender.dailyRollingFile = org.apache.log4j.DailyRollingFileAppender
#指定日志文件位置
log4j.appender.dailyRollingFile.File = logs/log.log
#设置appender对象的阀值
#log4j.appender.dailyRollingFile.Threshold = INFO
log4j.appender.dailyRollingFile.layout = org.apache.log4j.PatternLayout
log4j.appender.dailyRollingFile.layout.conversionPattern = [Level:%p] %x%l%m  [Time:%d{yyyy-MM-dd HH:mm:ss.SSS}]%n


##配置子logger，当在com.liang.log4j_demo.dao包下的类没有指定具体使用的子logger时使用该logger
log4j.logger.com.liang.log4j_demo.dao = ERROR,rollingFile
log4j.additivity.com.liang.log4j_demo.dao = FALSE
log4j.appender.rollingFile = org.apache.log4j.RollingFileAppender
log4j.appender.rollingFile.file = logs/error.log
#设置文件最大为4KB,超过该值则回滚日志文件
log4j.appender.rollingFile.MaxFileSize = 4KB
#输出文件最大序号为2
log4j.appender.file.maxBackupIndex=2
log4j.appender.rollingFile.layout = org.apache.log4j.PatternLayout
log4j.appender.rollingFile.layout.conversionPattern = %n%n%d{yyyy-MM-dd HH:mm:ss}  [Level: %p]  [Position: %c]  [Number: %L]%n%n%m
```

### FileAppender、RollingFileAppender、DailyRollingFileAppender配置
**FileAppender是用来将日志记录到文件的**，RollingFileAppender和DailyRollingFileAppender都是其子类，FileAppender具有的属性如下：
|属性|说明|
|---|---|
|immediateFlush|默认为true，表明每次日志追加操作都将输出流刷新到文件中|
|encoding|指定编码，默认使用平台相关的编码|
|threshold|appender对象设置的阀值，log流程的第一步就是检查该级别|
|Filename|日志文件名|
|fileAppend|默认是true，表示将日志信息追加到文件末尾|
|bufferedIO|默认为false，表示是否打开缓冲区|
|bufferSize|当设置了bufferedIO为true时才生效，默认缓冲区大小为8kb|

**RollingFileAppender可以用来将日志写入多个文件**，设置文件的大小达到一定阀值是回滚，除了与FileAppender相同的属性外，RollingFileAppender还拥有以下属性：

|属性|说明|
|---|---|
|maxFileSize|最大文件大小，当日志文件大于设定的值时发生回滚，默认为10MB|
|maxBackupIndex|表示备份文件的个数，默认为1|

DailyRollingFileAppender可以用来每天生成新的日志文件，除了继承FileAppender的所有属性外，还增加了一个DatePattern属性，用来表明什么时间回滚文件，默认情况下在每天的午夜回滚文件。

|DatePattern|说明|
|---|---|
|'.'yyyy-MM|在本月末，下月初回滚文件|
|'.'yyyy-MM-dd|在每天午夜回滚文件，默认值|
|'.'yyyy-MM-dd-a|在每天中午和午夜回滚文件|
|'.'yyyy-MM-dd-HH|在每个整点回滚文件|
|'.'yyyy-MM-dd-HH-mm|在每分钟回滚文件|
|'.'yyyy-ww|根据地域，在每周的第一天回滚文件|

## log4j的工作流程

1. **Threshold检查**：当调用了logger.debug()等方法后，Log4j会使用当前的logger对象所在的体系机构中设置的门槛去检查日志的级别，如果级别不够就作废此次日志请求。
2. **Level检查**：使用当前Logger上设置或者继承得到的Level级别检查日志级别，如果级别不够则作废此次日志请求。
3. **创建LoggingEvent对象**：如果级别审核通过，则创建一个日志事件对象LoggingEvent，该对象封装了本次日志所有相关参数信息，如日志内容，日志时间等。
4. **执行Appender**：将LoggingEvent对象交给当前Logger所有起作用的Appender对象，并调用所有appender对象上的doAppend()方法处理日志信息。
5. **格式化日志信息**：使用每一个Append对象绑定的Layout对象来格式化日志信息。
6. **输出日志信息**：通过格式化后得到的String对象交由给appender对象将其输出到指定的终端上，比如控制台或者文件。
　　
![](http://ww1.sinaimg.cn/large/0065HC85gw1f4n1dn0m8dj30ex08ewey.jpg)

## 日志记录规则建议

> 
1. 记录的信息必须是含义明确的完整语句
2. 推荐记录业务流程信息，在业务开始和业务流程结束时打印接收和发送出去的信息，严禁在内部函数多次打印消息内容
3. 推荐记录函数的关键参数，关键数据结构
4. 推荐记录导致业务错误的异常栈空间
5. 不推荐记录函数出入口 （？）
6. 不推荐记录行号%L、全类名%C、源文件名%F、方法名%M等位置信息(代价高)


